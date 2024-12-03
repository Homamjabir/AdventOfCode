package org.homamjabir.adventofcode;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    public static void part1(List<String> input) {
        int acc = 0;
        for (String s : input) {

            String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                String[] nums = matcher.group()
                        .replace("mul", "")
                        .replace("(", "")
                        .replace(")", "")
                        .split(",");
                acc += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
            }
        }
        System.out.println("Part 1: " + acc);
    }

    public static void part2(List<String> input) {
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

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("input03.txt");
        part1(input);
        part2(input);
    }
}