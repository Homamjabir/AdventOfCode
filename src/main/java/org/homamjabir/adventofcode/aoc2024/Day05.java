package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day05 {

    private static void part1(List<String> input) {
        Set<String> set = new HashSet<>();
        int acc = 0;
        for (String s : input) {
            if (s.contains("|"))
                set.add(s);
            if (s.contains(",")) {
                boolean flag = true;
                String[] nums = s.split(",");
                for (int j = 0; j < nums.length; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (set.contains(nums[k] + "|" + nums[j])) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    acc += Integer.parseInt(nums[nums.length / 2]);
                }
            }

        }
        System.out.println("Part 1: " + acc);
    }

    private static void part2(List<String> input) {
        Set<String> set = new HashSet<>();
        int acc = 0;
        for (String s : input) {
            if (s.contains("|"))
                set.add(s);
            if (s.contains(",")) {
                boolean flag = false;
                String[] nums = s.split(",");
                for (int j = 0; j < nums.length; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (set.contains(nums[k] + "|" + nums[j])) {
                            String temp = nums[j];
                            nums[j] = nums[k];
                            nums[k] = temp;
                            flag = true;
                        }
                    }
                }
                if (flag) {
                    acc += Integer.parseInt(nums[nums.length / 2]);
                }
            }
        }
        System.out.println("Part 2: " + acc);
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input05.txt");
        part1(input);
        part2(input);
    }
}