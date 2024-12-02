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

    public static void part2_bruteForce(List<String> input) {
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
                    if(isLineSafe(faultyLevel)) {
                        isLineSafe = true;
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

    private static boolean isLineSafe(String[] level) {
        boolean isLineSafe = true;

        boolean isLineIncreasing = (Integer.parseInt(level[0]) < Integer.parseInt(level[1]));

        for(int i = 0; i < level.length-1; i++) {
            int current = Integer.parseInt(level[i]);
            int next = Integer.parseInt(level[i+1]);
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
        part2_bruteForce(input);
    }
}