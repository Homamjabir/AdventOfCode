package org.homamjabir.adventofcode;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.List;

public class Day02 {

    public static void part1(List<String> input) {
        int acc = 0;

        for(String line : input) {
            String [] level = line.split("\\s+");

            if(isLineSafe(level)) {
                acc++;
            }
        }
        System.out.println("Part 1: " + acc);

    }

    public static void part2(List<String> input) {
        int acc = 0;

        for(String line : input) {
            String [] level = line.split("\\s+");

            boolean isLineSafe = isLineSafe(level);

            if(!isLineSafe) {
                for (int i = 0; i < level.length; i++) {
                    String[] faultyLevel = new String[level.length - 1];
                    int index = 0;
                    for (int j = 0; j < level.length; j++) {
                        if (j != i) {
                            faultyLevel[index++] = level[j];
                        }
                    }

                    isLineSafe = isLineSafe(faultyLevel);

                    if(isLineSafe) {
                        break;
                    }
                }
            }

            if(isLineSafe) {
                acc++;
            }
        }
        System.out.println("Part 2: " + acc);
    }

    private static boolean isLineSafe(String[] levels) {
        boolean isLineSafe = true;

        boolean isLineIncreasing = (Integer.parseInt(levels[0]) < Integer.parseInt(levels[1]));

        for(int i = 0; i < levels.length-1; i++) {
            int current = Integer.parseInt(levels[i]);
            int next = Integer.parseInt(levels[i+1]);
            int delta = Math.abs(current - next);

            if((delta > 3 || delta == 0) || current < next != isLineIncreasing) {
                isLineSafe = false;
                break;
            }
        }

        return isLineSafe;
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("input02.txt");
        part1(input);
        part2(input);
    }
}