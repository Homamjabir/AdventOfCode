package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day14 {

    private static class Guard {
        final int vX;
        final int vY;

        int posX;
        int posY;

        Guard (int vX, int vY, int posX, int posY) {
            this.vX = vX;
            this.vY = vY;
            this.posX = posX;
            this.posY = posY;
        }

        void setPosX(int i) {
            posX = i;
        }
        void setPosY(int i) {
            posY = i;
        }
    }

    private static void part1(List<Guard> guards) {
        int cols = 101;
        int rows = 103;

        for(int i = 0; i < 100; i++) {

            for(Guard guard : guards) {

                int newX = guard.posX + guard.vX;
                int newY = guard.posY + guard.vY;

                if (newX < 0) {
                    newX = cols + newX;
                } else if (newX >= cols) {
                    newX = (newX - cols);
                }

                if (newY < 0) {
                    newY = rows + newY;
                } else if (newY >= rows) {
                    newY = (newY - rows);
                }
                guard.setPosX(newX);
                guard.setPosY(newY);
            }
        }

        int[] quadArr = {0, 0, 0 ,0};
        int middleY = rows / 2;
        int middleX = cols / 2;

        for(Guard guard : guards) {
            if (guard.posY != middleY && guard.posX != middleX) {
               if(guard.posX < middleX && guard.posY < middleY)
                   quadArr[0]++;
               if(guard.posX > middleX && guard.posY < middleY)
                   quadArr[1]++;
               if(guard.posX < middleX && guard.posY > middleY)
                   quadArr[2]++;
               if(guard.posX > middleX && guard.posY > middleY)
                   quadArr[3]++;
            }
        }
        int result = quadArr[0] * quadArr[1] * quadArr[2] * quadArr[3];
        System.out.println("Part 1: " + result);
    }

    private static void part2(List<Guard> guards) {
        int cols = 101;
        int rows = 103;

        for(int i = 0; i < 1000000; i++) {

            for(Guard guard : guards) {

                int newX = guard.posX + guard.vX;
                int newY = guard.posY + guard.vY;

                if (newX < 0) {
                    newX = cols + newX;
                } else if (newX >= cols) {
                    newX = (newX - cols);
                }

                if (newY < 0) {
                    newY = rows + newY;
                } else if (newY >= rows) {
                    newY = (newY - rows);
                }
                guard.setPosX(newX);
                guard.setPosY(newY);
            }
            if(christmasTreeFound(guards, rows, cols)) {
                System.out.println(i);
                break;
            }
        }

        System.out.println("Part 2: " + 0);
    }

    private static boolean christmasTreeFound(List<Guard> guards, int rows, int cols) {
        int[][] field = new int[rows][cols];

        for(Guard guard: guards) {
            field[guard.posY][guard.posX]++;
        }

        for(int i = 0; i < field.length; i++) {
            int counter = 0;
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == 0) {
                    counter++;
                }
            }
            if(counter == 68) {
                print(field);
                return true;
            }
        }
        return false;


    }

    private static void print(int[][] field) {
        for(int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if(field[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print(field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input14.txt");

        List<Guard> guards = new ArrayList<>();

        for (String s : input) {
            String[] parsedString = s.split(" ");
            String[] pos = parsedString[0].replace("p=", "").split(",");
            String[] vel = parsedString[1].replace("v=", "").split(",");
            guards.add(new Guard(Integer.parseInt(vel[0]), Integer.parseInt(vel[1]), Integer.parseInt(pos[0]), Integer.parseInt(pos[1])));
        }

        part1(guards);
        part2(guards);
    }
}