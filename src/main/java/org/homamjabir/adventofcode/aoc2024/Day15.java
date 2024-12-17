package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;

import java.util.List;

public class Day15 {

    private static void part1(List<String> input) {
        int cols = input.get(0).length();
        int rows = 0;
        StringBuilder movements = new StringBuilder();

        for(String line : input) {
            if (line.startsWith("#"))
                rows++;
            else if(line.contains("<") || line.contains("v") || line.contains(">") || line.contains("^"))
                movements.append(line);
        }

        char[][] field = new char[rows][cols];

        int[] pos = new int[2];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(input.get(i).charAt(j) == '@') {
                    pos[0] = i;
                    pos[1] = j;
                }
                field[i][j] = input.get(i).charAt(j);
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};


        for(int i = 0; i < movements.length(); i++) {
            char dir = movements.charAt(i);
            int posX = pos[0];
            int posY = pos[1];

            int dirIndex = -1;

            if (dir == '^') {
                dirIndex = 0;
            } else if (dir == '>') {
                dirIndex = 1;
            } else if (dir == 'v') {
                dirIndex = 2;
            } else if (dir == '<') {
                dirIndex = 3;
            }
            pos = move(field, posX, posY, dx[dirIndex], dy[dirIndex]);
        }

        int acc = 0;
        for(int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == 'O')
                    acc += 100 * i + j;
            }

        }

        System.out.println("Part 1: " + acc);
    }

    private static int[] move(char[][] field, int posX, int posY, int dx, int dy) {
        if(field[posX + dx][posY + dy] == '.') {
            field[posX][posY] = '.';
            field[posX + dx][posY + dy] = '@';
            
            return new int[]{posX + dx, posY + dy};
        } else if(field[posX + dx][posY + dy] == 'O') {
            return moveObstacle(field, posX + dx, posY + dy, dx, dy, 1);
        }
        return new int[]{posX, posY};
    }

    private static int[] moveObstacle(char[][] field, int posX, int posY, int dx, int dy, int amountOfObstacles) {
        if (field[posX + dx][posY + dy] == '.') {
            field[posX + dx][posY + dy] = 'O';
            field[posX - ((amountOfObstacles-1) * dx)][posY - ((amountOfObstacles-1) * dy)] = '@';
            field[posX - ((amountOfObstacles) * dx)][posY - ((amountOfObstacles) * dy)] = '.';
            return new int[]{posX - ((amountOfObstacles-1) * dx), posY - ((amountOfObstacles-1) * dy)};

        } else if (field[posX + dx][posY + dy] == 'O') {
            amountOfObstacles++;
            return moveObstacle(field,
                    posX + dx,
                    posY + dy,
                    dx,
                    dy,
                    amountOfObstacles);
        }
        return new int[]{posX - ((amountOfObstacles) * dx), posY - ((amountOfObstacles) * dy)};
    }

    private static void print(char[][] field) {
        for(int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void part2(List<String> input) {
        System.out.println("Part 2: " + 0);
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input15.txt");

        part1(input);
        part2(input);
    }
}