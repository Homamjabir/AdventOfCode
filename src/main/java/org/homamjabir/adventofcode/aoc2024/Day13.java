package org.homamjabir.adventofcode.aoc2024;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.List;

public class Day13 {

    private static void part1(List<String> input) {
        long[][] equation = new long[2][3];
        long acc = 0;
        for (String s : input) {
            if (s.startsWith("Button A")) {
                String[] button = s.substring(10).split(", ");
                equation[0][0] = Long.parseLong(button[0].substring(2));
                equation[1][0] = Long.parseLong(button[1].substring(2));
            } else if (s.startsWith("Button B")) {
                String[] button = s.substring(10).split(", ");
                equation[0][1] = Long.parseLong(button[0].substring(2));
                equation[1][1] = Long.parseLong(button[1].substring(2));
            } else if (s.startsWith("Prize:")) {
                String[] button = s.substring(7).split(", ");
                equation[0][2] = Long.parseLong(button[0].substring(2));
                equation[1][2] = Long.parseLong(button[1].substring(2));

                acc += findWholeNumberSolutions(equation);
            }
        }


        System.out.println("Part 1: " + acc);
    }

    public static long findWholeNumberSolutions(long[][] equation) {
        long a1 = equation[0][0], b1 = equation[0][1], c1 = equation[0][2];
        long a2 = equation[1][0], b2 = equation[1][1], c2 = equation[1][2];

        long determinant = a1 * b2 - a2 * b1;

        long x = (c1 * b2 - c2 * b1) / determinant;
        long y = (a1 * c2 - a2 * c1) / determinant;

        if (((x * equation[0][0]) + (y * equation[0][1]) == equation[0][2])
        && ((x * equation[1][0]) + (y * equation[1][1]) == equation[1][2])) {
            return x * 3 + y;
        }
        return 0;
    }


    private static void part2(List<String> input) {
        long[][] equation = new long[2][3];
        long acc = 0;
        for (String s : input) {
            if (s.startsWith("Button A")) {
                String[] button = s.substring(10).split(", ");
                equation[0][0] = Long.parseLong(button[0].substring(2));
                equation[1][0] = Long.parseLong(button[1].substring(2));
            } else if (s.startsWith("Button B")) {
                String[] button = s.substring(10).split(", ");
                equation[0][1] = Long.parseLong(button[0].substring(2));
                equation[1][1] = Long.parseLong(button[1].substring(2));
            } else if (s.startsWith("Prize:")) {
                String[] button = s.substring(7).split(", ");
                equation[0][2] = Long.parseLong(button[0].substring(2)) + 10000000000000L;
                equation[1][2] = Long.parseLong(button[1].substring(2)) + 10000000000000L;

                acc += findWholeNumberSolutions(equation);
            }
        }

        System.out.println("Part 2: " + acc);
    }


    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input13.txt");

        part1(input);
        part2(input);
    }
}