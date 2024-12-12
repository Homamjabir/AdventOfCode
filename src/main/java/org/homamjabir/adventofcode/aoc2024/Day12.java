package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Day12 {

    private static void part1(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Map<Character, Integer> plantTypeRunningCostMap = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    int price = bfs(map, visited, i, j);
                    if (plantTypeRunningCostMap.containsKey(map[i][j]))
                        plantTypeRunningCostMap.put(map[i][j], plantTypeRunningCostMap.get(map[i][j]) + price);
                    else
                        plantTypeRunningCostMap.put(map[i][j], price);
                }
            }
        }
        int totalPrice = 0;
        for (Integer plantPrice : plantTypeRunningCostMap.values())
            totalPrice += plantPrice;

        System.out.println("Part 1: " + totalPrice);
    }

    private static int bfs(char[][] map, boolean[][] visited, int startX, int startY) {
        int rows = map.length;
        int cols = map[0].length;
        char plantType = map[startX][startY];
        int area = 0;
        int perimeter = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            area++;

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                    if (map[newX][newY] == plantType && !visited[newX][newY]) {
                        queue.add(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    } else if (map[newX][newY] != plantType) {
                        perimeter++;
                    }
                } else {
                    perimeter++;
                }
            }
        }
        return perimeter * area;
    }


    private static void part2(List<String> input) {

        System.out.println("Part 2: " + 0);
    }


    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input12.txt");
        int rows = input.size();
        int cols = input.get(0).length();
        char[][] map = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            map[i] = input.get(i).toCharArray();
        }

        part1(map);
        part2(input);
    }
}