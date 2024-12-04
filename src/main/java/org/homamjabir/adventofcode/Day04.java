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
                    acc += horizontalCheck(line, i, searchString)
                            + verticalCheck(input, rowIndex, i, searchString)
                            + diagonalCheck_1(input, rowIndex, i, searchString)
                            + diagonalCheck_2(input, rowIndex, i, searchString);
                }
            }
        }
        System.out.println("Part 1: " + acc);
    }

    private static int horizontalCheck(String line, int startCol, String searchString) {
        StringBuilder sb = new StringBuilder(4);

        while (startCol < line.length() && sb.length() != 4 && searchString.startsWith(sb.toString())) {
            sb.append(line.charAt(startCol));

            startCol++;
        }

        return searchString.contentEquals(sb) ? 1 : 0;
    }

    private static int verticalCheck(List<String> lines, int startRow, int startCol, String searchString) {
        StringBuilder sb = new StringBuilder(4);

        while (startRow < lines.size() && sb.length() != 4 && searchString.startsWith(sb.toString())) {
            sb.append(lines.get(startRow).charAt(startCol));
            
            startRow++;
        }

        return searchString.contentEquals(sb) ? 1 : 0;
    }

    private static int diagonalCheck_1(List<String> lines, int startRow, int startCol, String searchString) {
        StringBuilder sb = new StringBuilder(4);

        while (startRow < lines.size() && startCol < lines.get(startRow).length() && sb.length() != 4 && searchString.startsWith(sb.toString())) {
            sb.append(lines.get(startRow).charAt(startCol));

            startRow++;
            startCol++;
        }

        return searchString.contentEquals(sb) ? 1 : 0;
    }

    private static int diagonalCheck_2(List<String> lines, int startRow, int startCol, String searchString) {
        StringBuilder sb = new StringBuilder(4);

        while (startRow < lines.size() && 0 <= startCol && sb.length() != 4 && searchString.startsWith(sb.toString())) {
            sb.append(lines.get(startRow).charAt(startCol));

            startRow++;
            startCol--;
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