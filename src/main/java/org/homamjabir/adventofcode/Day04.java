package org.homamjabir.adventofcode;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 {

    public static void part1(List<String> input) {
        int acc = 0;

        for (int rowIndex = 0; rowIndex < input.size(); rowIndex++) {
            String line = input.get(rowIndex);
            for (int i = 0; i < line.length(); i++) {
                char currentChar = line.charAt(i);
                if (currentChar == 'X' || currentChar == 'S') {
                    String searchString = currentChar == 'X' ? "XMAS" : "SAMX";
                    acc += checkDirection(input, rowIndex, i, searchString, 0, 1)
                            + checkDirection(input, rowIndex, i, searchString, 1, 0)
                            + checkDirection(input, rowIndex, i, searchString, 1, 1)
                            + checkDirection(input, rowIndex, i, searchString, 1, -1);
                }
            }
        }
        System.out.println("Part 1: " + acc);
    }

    private static int checkDirection(List<String> lines, int startRow, int startCol, String searchString, int rowInc, int colInc) {
        StringBuilder sb = new StringBuilder(4);
        int row = startRow;
        int col = startCol;

        while (row < lines.size() && col >= 0 && col < lines.get(row).length() && sb.length() != 4 && searchString.startsWith(sb.toString())) {
            sb.append(lines.get(row).charAt(col));
            row += rowInc;
            col += colInc;
        }

        return searchString.contentEquals(sb) ? 1 : 0;
    }

    public static void part2(List<String> input) {
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("input04.txt");
        part1(input);
        part2(input);
    }
}