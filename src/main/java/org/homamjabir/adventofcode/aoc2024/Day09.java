package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day09 {

    private static void part1(List<String> input) {
        boolean isFile = true;
        int fileIndex = 0;

        List<Integer> nums = new ArrayList<>();
        for(int i = 0; i < input.get(0).length(); i++) {
            int num = Character.getNumericValue(input.get(0).charAt(i));

            for(int j = 0; j < num; j++) {
                nums.add(isFile ? fileIndex : -1);
            }

            if(isFile)
                fileIndex++;
            isFile = !isFile;
        }

        int p1 = 0;
        int p2 = nums.size() - 1;
        while (p1 < p2) {
            if (nums.get(p1) == -1) {
                if (nums.get(p2) != -1) {
                    nums.set(p1, nums.get(p2));
                    nums.set(p2, -1);
                    p1++;
                }
                p2--;
            } else {
                p1++;
            }
        }

        long acc = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == -1)
                break;
            acc += (long) nums.get(i) * i;
        }

        System.out.println("Part 1: " + acc);
    }

    private static void part2(List<String> input) {
        int acc = 0;

        System.out.println("Part 2: " + acc);
    }


    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input09.txt");
        part1(input);
        part2(input);
    }
}