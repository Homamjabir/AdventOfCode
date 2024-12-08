package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

        int[][] dirArr = {{-1, 0, 1, 0},{0, 1, 0, -1}};
        
        int dirArrIndex = 0;
        while (x >= 0 && x < input.size() && y >= 0 && y < input.get(0).length()) {
            if (obstacles.contains(x + "," + y)) {
                x -= dirArr[0][dirArrIndex];
                y -= dirArr[1][dirArrIndex];

                dirArrIndex = dirArrIndex == 3 ? 0 : dirArrIndex + 1;

            } else if(!pathFound.contains(x + "," + y)) {
                pathFound.add(x + "," + y);
                acc++;
            }
            x += dirArr[0][dirArrIndex];
            y += dirArr[1][dirArrIndex];
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

        int[][] dirArr = {{-1, 0, 1, 0},{0, 1, 0, -1}};

        int dirArrIndex = 0;

        int acc = 0;
        while (x >= 0 && x < input.size() && y >= 0 && y < input.get(0).length()) {
            if (charArray[x][y] == '#') {
                x -= dirArr[0][dirArrIndex];
                y -= dirArr[1][dirArrIndex];

                dirArrIndex = dirArrIndex == 3 ? 0 : dirArrIndex + 1;

            } else if (charArray[x][y] != ('X')) {
                charArray[x][y] = 'X';
                acc++;
            }
            x += dirArr[0][dirArrIndex];
            y += dirArr[1][dirArrIndex];
        }

        System.out.println("Part 1: " + acc);
    }

    private static void part2(List<String> input) {
        int x = 0;
        int xOrginal = 0;
        int y = 0;
        int yOrignal = 0;
        Set<String> obstacles = new HashSet<>();

        for(int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if(input.get(i).charAt(j) == '#')
                    obstacles.add(i + "," + j);
                else if (input.get(i).charAt(j) == '^') {
                    x = i;
                    xOrginal = i;
                    y = j;
                    yOrignal = j;
                }
            }
        }

        Set<String> pathFound = new HashSet<>();

        int[][] dirArr = {{-1, 0, 1, 0},{0, 1, 0, -1}};

        int dirArrIndex = 0;

        List<String> visitedCords = new ArrayList<>();

        while (x >= 0 && x < input.size() && y >= 0 && y < input.get(0).length()) {
            if (obstacles.contains(x + "," + y)) {
                x -= dirArr[0][dirArrIndex];
                y -= dirArr[1][dirArrIndex];

                dirArrIndex = dirArrIndex == 3 ? 0 : dirArrIndex + 1;

            } else if(!pathFound.contains(x + "," + y)) {
                pathFound.add(x + "," + y);
                visitedCords.add(x + "," + y);
            }
            x += dirArr[0][dirArrIndex];
            y += dirArr[1][dirArrIndex];
        }
        
        char[][] charArray = input.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int acc = 0;
        for(String visitedCord : visitedCords) {
            int[] cords = Arrays.stream(visitedCord.split(",")).mapToInt(Integer::parseInt).toArray();
            charArray[cords[0]][cords[1]] = '#';
            if(!guardExistsMap(charArray, xOrginal, yOrignal))
                acc++;
            charArray[cords[0]][cords[1]] = '.';
        }
        

        System.out.println("Part 2: " + acc);
    }
    
    private static boolean guardExistsMap(char[][] charArray, int guardXPosition, int guardYPosition) {
        int[][] dirArr = {{-1, 0, 1, 0},{0, 1, 0, -1}};
        int dirArrIndex = 0;

        HashMap<String, Character> map = new HashMap<>();

        while (guardXPosition >= 0 && guardXPosition < charArray.length && guardYPosition >= 0 && guardYPosition < charArray[guardXPosition].length) {
            if (charArray[guardXPosition][guardYPosition] == '#') {

                if(map.get(guardXPosition + "," + guardYPosition) != null
                && map.get(guardXPosition + "," + guardYPosition) == getGuardDir(dirArr[0][dirArrIndex], dirArr[1][dirArrIndex]))
                    return false;

                map.put(guardXPosition + "," + guardYPosition, getGuardDir(dirArr[0][dirArrIndex], dirArr[1][dirArrIndex]));

                guardXPosition -= dirArr[0][dirArrIndex];
                guardYPosition -= dirArr[1][dirArrIndex];

                dirArrIndex = dirArrIndex == 3 ? 0 : dirArrIndex + 1;

            }
            guardXPosition += dirArr[0][dirArrIndex];
            guardYPosition += dirArr[1][dirArrIndex];
        }
        return true;
    }

    private static char getGuardDir(int x, int y) {
        if(x == -1 && y == 0)
            return '^';
        if(x == 0 && y == 1)
            return '>';
        if(x == 1 && y == 0)
            return 'v';
        if(x == 0 && y == -1)
            return '<';
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input06.txt");
        part12dArray(input);
        part1HashSet(input);
        part2(input);
    }
}