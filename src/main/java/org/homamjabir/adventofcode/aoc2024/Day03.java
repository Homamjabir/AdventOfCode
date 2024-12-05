package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day03 {

    private static void part1(List<String> input) {
        int acc = 0;
        for (String s : input) {

            String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                String[] nums = matcher.group()
                        .replace("mul(", "")
                        .replace(")", "")
                        .split(",");
                acc += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
            }
        }
        System.out.println("Part 1: " + acc);
    }

    private static void part2(List<String> input) {
        int acc = 0;
        boolean flag = true;

        for (String s : input) {

            String regex = "mul\\(\\d{1,3},\\d{1,3}\\)|don't\\(\\)|do\\(\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                if (matcher.group().equals("don't()")) {
                    flag = false;
                } else if (matcher.group().equals("do()")) {
                    flag = true;
                } else {
                    if (flag) {
                        String[] nums = matcher.group()
                                .replace("mul(", "")
                                .replace(")", "")
                                .split(",");
                        acc += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
                    }
                }
            }
        }
        System.out.println("Part 2: " + acc);
    }

    private static void part2_shorter(List<String> input) {
        int acc = 0;

        String result = input.stream().collect(Collectors.joining()).replaceAll("don't\\(\\).*?do\\(\\)", "");

        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(result);

        while (matcher.find()) {
            String[] nums = matcher.group()
                    .replace("mul(", "")
                    .replace(")", "")
                    .split(",");
            acc += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
        }

        System.out.println("Part 2: " + acc);
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input03.txt");
        part1(input);
        part2(input);
        part2_shorter(input);
    }
}