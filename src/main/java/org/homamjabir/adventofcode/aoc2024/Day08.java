package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.*;

public class Day08 {

    private static void part1(List<String> input) {
        Map<Character, List<String>> map = new HashMap<>();

        for(int i = 0; i < input.size(); i++) {
            for(int j = 0; j < input.get(i).length(); j++) {
                if(input.get(i).charAt(j) != '.') {
                    if(map.get(input.get(i).charAt(j)) == null)
                        map.put(input.get(i).charAt(j), new ArrayList<>(List.of(i + "," + j)));
                    else {
                        map.get(input.get(i).charAt(j)).add(i + "," + j);
                    }
                }
            }
        }

        Set<String> uniqueCords = new HashSet<>();

        for(List<String> list : map.values()) {
            for(int i = 0; i < list.size(); i++) {
                for(int j = i + 1; j < list.size(); j++) {
                    int[] cord1 = Arrays.stream(list.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
                    int[] cord2 = Arrays.stream(list.get(j).split(",")).mapToInt(Integer::parseInt).toArray();

                    int x = cord1[0] - cord2[0];
                    int y = cord1[1] - cord2[1];

                    if(isValidCord(cord1[0] + x, cord1[1] + y, input.size(), input.get(0). length()))
                        uniqueCords.add((cord1[0] + x) + "," + (cord1[1] + y));

                    if(isValidCord(cord2[0] - x, cord2[1] - y, input.size(), input.get(0). length()))
                        uniqueCords.add((cord2[0] - x) + "," + (cord2[1] - y));
                }
            }
        }

        System.out.println("Part 1: " + uniqueCords.size());
    }

    private static boolean isValidCord(int c1, int c2, int xLen, int yLen) {
        return (0 <= c1 && c1 < xLen
                && 0 <= c2 && c2 < yLen);
    }

    private static void part2(List<String> input) {
        Map<Character, List<String>> map = new HashMap<>();

        for(int i = 0; i < input.size(); i++) {
            for(int j = 0; j < input.get(i).length(); j++) {
                if(input.get(i).charAt(j) != '.') {
                    if(map.get(input.get(i).charAt(j)) == null)
                        map.put(input.get(i).charAt(j), new ArrayList<>(List.of(i + "," + j)));
                    else {
                        map.get(input.get(i).charAt(j)).add(i + "," + j);
                    }
                }
            }
        }

        Set<String> uniqueCords = new HashSet<>();

        for(List<String> list : map.values()) {
            for(int i = 0; i < list.size(); i++) {
                for(int j = i + 1; j < list.size(); j++) {
                    int[] cord1 = Arrays.stream(list.get(i).split(",")).mapToInt(Integer::parseInt).toArray();
                    int[] cord2 = Arrays.stream(list.get(j).split(",")).mapToInt(Integer::parseInt).toArray();

                    int x = cord1[0] - cord2[0];
                    int y = cord1[1] - cord2[1];

                    uniqueCords.add(cord1[0] + "," + cord1[1]);
                    uniqueCords.add(cord2[0] + "," + cord2[1]);

                    while (true) {
                        if(isValidCord(cord1[0] + x, cord1[1] + y, input.size(), input.get(0). length()))
                            uniqueCords.add((cord1[0] + x) + "," + (cord1[1] + y));
                        else
                            break;
                        cord1[0] = cord1[0] + x;
                        cord1[1] = cord1[1] + y;
                    }

                    while (true) {
                        if(isValidCord(cord2[0] - x, cord2[1] - y, input.size(), input.get(0). length()))
                            uniqueCords.add((cord2[0] - x) + "," + (cord2[1] - y));
                        else
                            break;
                        cord2[0] = cord2[0] - x;
                        cord2[1] = cord2[1] - y;
                    }
                }
            }
        }

        System.out.println("Part 2: " + uniqueCords.size());
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input08.txt");
        part1(input);
        part2(input);
    }
}
