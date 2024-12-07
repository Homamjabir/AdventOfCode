package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.*;

public class Day07 {

    private static void part1(List<String> input) {
        long acc = 0;
        for(String line : input) {
            String[] strings = line.split(": ");
            long org = Long.parseLong(strings[0]);
            List<Long> nums = Arrays.stream(strings[1].split(" "))
                    .map(Long::parseLong)
                    .toList();

            if (lineValid(org, nums)) {
                acc += org;
            }
        }
        System.out.println("Part 1: " + acc);
    }

    private static boolean lineValid(long org, List<Long> nums) {
        return calc(nums, 0, nums.get(0), org);
    }

    private static boolean calc(List<Long> nums, int index, long currentValue, long orgValue) {
        if (index == nums.size() - 1) {
            return orgValue == currentValue;
        }

        long nextValue = nums.get(index + 1);

        if(calc(nums, index + 1, currentValue * nextValue, orgValue)) {
            return true;
        }

        if(calc(nums, index + 1, currentValue + nextValue, orgValue)) {
            return true;
        }

        return false;
    }

    private static void part2(List<String> input) {
        long acc = 0;
        for(String line : input) {
            String[] strings = line.split(": ");
            long org = Long.parseLong(strings[0]);
            List<Long> nums = Arrays.stream(strings[1].split(" "))
                    .map(Long::parseLong)
                    .toList();

            if (lineValid2(org, nums)) {
                acc += org;
            }
        }
        System.out.println("Part 1: " + acc);
    }

    private static boolean lineValid2(long org, List<Long> nums) {
        return calc2(nums, 0, nums.get(0), org);
    }

    private static boolean calc2(List<Long> nums, int index, long currentValue, long orgValue) {
        if (index == nums.size() - 1) {
            return orgValue == currentValue;
        }

        long nextValue = nums.get(index + 1);

        if(calc2(nums, index + 1, currentValue * nextValue, orgValue)) {
            return true;
        }

        if(calc2(nums, index + 1, currentValue + nextValue, orgValue)) {
            return true;
        }

        if(calc2(nums, index + 1, concatNumbers(currentValue, nextValue), orgValue)) {
            return true;
        }

        return false;
    }

    private static long concatNumbers(long num1, long num2) {
        String concatenatedString = String.valueOf(num1) + String.valueOf(num2);

        return Long.parseLong(concatenatedString);
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input07.txt");
        part1(input);
        part2(input);
    }
}
