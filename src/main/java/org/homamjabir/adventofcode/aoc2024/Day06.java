package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 {

    private static void part1HashSet(List<String> input) {
        int acc = 0;
        int x = 0;
        int y = 0;
        Set<String> obstacles = new HashSet<>();

        for(int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if(input.get(i).charAt(j) == '#')
                    obstacles.add(i + "," + j);
                else if (input.get(i).charAt(j) == '^') {
                    x = i;
                    y = j;
                }
            }
        }
        Set<String> pathFound = new HashSet<>();

        int xDirArr[] = {-1, 0, 1, 0};
        int yDirArr[] = {0, 1, 0, -1};
        int dirArrIndex = 0;
        while (x >= 0 && x < input.size() && y >= 0 && y < input.get(0).length()) {
            if (obstacles.contains(x + "," + y)) {
                x -= xDirArr[dirArrIndex];
                y -= yDirArr[dirArrIndex];

                dirArrIndex = dirArrIndex == 3 ? 0 : dirArrIndex + 1;

            } else if(!pathFound.contains(x + "," + y)) {
                pathFound.add(x + "," + y);
                acc++;
            }
            x += xDirArr[dirArrIndex];
            y += yDirArr[dirArrIndex];
        }
        System.out.println("Part 1: " + acc);
    }

    private static void part12dArray(List<String> input) {
        char[][] charArray = input.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int x = 0;
        int y = 0;

        boolean flag = false;
        for(int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                if (charArray[i][j] == '^') {
                    x = i;
                    y = j;
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        int xDirArr[] = {-1, 0, 1, 0};
        int yDirArr[] = {0, 1, 0, -1};
        int dirArrIndex = 0;

        int acc = 0;
        while (x >= 0 && x < input.size() && y >= 0 && y < input.get(0).length()) {
            if (charArray[x][y] == '#') {
                x -= xDirArr[dirArrIndex];
                y -= xDirArr[dirArrIndex];

                dirArrIndex = dirArrIndex == 3 ? 0 : dirArrIndex + 1;

            } else if (charArray[x][y] != ('X')) {
                charArray[x][y] = 'X';
                acc++;
            }
            x += xDirArr[dirArrIndex];
            y += yDirArr[dirArrIndex];
        }

        System.out.println("Part 1: " + acc);
    }

    private static void part2(List<String> input) {
        char[][] charArray = input.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int x = 0;
        int y = 0;

        boolean flag = false;
        for(int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                if (charArray[i][j] == '^') {
                    x = i;
                    y = j;
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        int[] xDirArr = {-1, 0, 1, 0};
        int[] yDirArr = {0, 1, 0, -1};
        int dirArrIndex = 0;

        int acc = 0;
        for(int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                charArray[x][y] = '#';
                int test = 0;
                while (x >= 0 && x < input.size() && y >= 0 && y < input.get(0).length()) {
                    if (charArray[x][y] == '#') {
                        x -= xDirArr[dirArrIndex];
                        y -= xDirArr[dirArrIndex];

                        dirArrIndex = dirArrIndex == 3 ? 0 : dirArrIndex + 1;

                    } else if (charArray[x][y] != ('X')) {
                        charArray[x][y] = 'X';
                    } else if (charArray[x][y] == ('X')) {
                        test++;
                    }
                    if (test == 10000) {
                        break;
                    }
                    x += xDirArr[dirArrIndex++];
                    y += yDirArr[dirArrIndex++];
                }
                charArray[x][y] = input.get(x).charAt(y);
                if(test == 10000) {
                    acc++;
                }
            }
        }
        System.out.println("Part 2: " + acc);
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input06.txt");
        part12dArray(input);
        part2(input);
    }
}