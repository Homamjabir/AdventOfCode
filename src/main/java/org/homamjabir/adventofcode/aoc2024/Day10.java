package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day10 {

    private static void part1(int[][] map) {
        int totalScore = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    totalScore += calculateTrailheadScore(map, i, j);
                }
            }
        }

        System.out.println("Part 1: " + totalScore);
    }

    private static int calculateTrailheadScore(int[][] map, int startX, int startY) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        int score = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (map[x][y] == 9) {
                score++;
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols
                        && !visited[newX][newY] && map[newX][newY] == map[x][y] + 1) {
                    queue.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }

        return score;
    }

    private static void part2(int[][] map) {
        System.out.println("Part 2: " + 0);
    }


    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input10.txt");

        int rows = input.size();
        int cols = input.get(0).length();

        int[][] map = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = input.get(i).charAt(j) - '0';
            }
        }

        part1(map);
        part2(map);
    }
}