package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
        System.out.println("Part 2: " + 0);
    }


    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input11.txt");

        List<Long> stones = Arrays.stream(input.get(0).split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        part1(stones);
        part2(stones);
    }
}