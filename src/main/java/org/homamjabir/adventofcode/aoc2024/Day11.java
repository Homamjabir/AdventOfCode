package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day11 {

    private static void part1(List<Long> input) {

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < input.size(); j++) {
                Long stone = input.get(j);
                if(stone == 0)
                    input.set(j, 1L);
                else if (String.valueOf(stone).length() % 2 == 0 ) {
                    String s = String.valueOf(stone);
                    Long firstHalf = Long.parseLong(s.substring(0, s.length()/2));
                    Long secondHalf = Long.parseLong(s.substring(s.length()/2));
                    input.set(j, firstHalf);
                    input.add(++j, secondHalf);
                } else
                    input.set(j, stone * 2024);
            }

        }
        System.out.println("Part 1: " + input.size());
    }


    private static void part2(List<Long> input) {
        HashMap<Long, Long> map = new HashMap<>();
        for (Long aLong : input) {
            if (map.containsKey(aLong))
                map.put(aLong, map.get(aLong) + 1);
            else
                map.put(aLong, 1L);
        }

        for (int i = 0; i < 75; i++) {
            HashMap<Long, Long> newMap = new HashMap<>();

            for (Map.Entry<Long, Long> entry : map.entrySet()) {

                if (entry.getKey() == 0) {
                    updateMap(newMap, entry, 1L);
                } else if (String.valueOf(entry.getKey()).length() % 2 == 0) {
                    String s = String.valueOf(entry.getKey());
                    Long firstHalf = Long.parseLong(s.substring(0, s.length() / 2));
                    Long secondHalf = Long.parseLong(s.substring(s.length() / 2));
                    updateMap(newMap, entry, firstHalf);
                    updateMap(newMap, entry, secondHalf);

                } else {
                    updateMap(newMap, entry, entry.getKey() * 2024);
                }
            }
            map = newMap;
        }

        Long acc = 0L;
        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            acc += entry.getValue();
        }
        System.out.println("Part 2: " + acc);
    }

    private static void updateMap(HashMap<Long, Long> map, Map.Entry<Long, Long> entry, Long newKey) {
        if (map.containsKey(newKey))
            map.put(newKey, map.get(newKey) + (entry.getValue()));
        else
            map.put(newKey, entry.getValue());
    }


    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input11.txt");

        List<Long> stones = Arrays.stream(input.get(0).split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<Long> stonesPart2 = new ArrayList<>(stones);

        part1(stones);
        part2(stonesPart2);
    }
}