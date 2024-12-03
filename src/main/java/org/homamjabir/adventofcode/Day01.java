package org.homamjabir.adventofcode;

import org.homamjabir.adventofcode.util.FileReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day01 {

    /**
     * Separates the input into two lists and sorts them, then compares the abs between each element
     */
    public static void part1(List<String> input) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for(String s : input) {
            String[] result = s.split("\\s+");
            list1.add(Integer.parseInt(result[0]));
            list2.add(Integer.parseInt(result[1]));
        }
        list1.sort(Integer::compareTo);
        list2.sort(Integer::compareTo);

        int acc = 0;
        for(int i = 0; i < list1.size(); i++) {
            acc += Math.abs(list1.get(i) - list2.get(i));
        }

        System.out.println("Part 1: " + acc);

    }

    /**
     * Separates the input into two lists and sorts them, then multiple
     */
    public static void part2(List<String> input) {
        List<String> list1 = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        for(String s : input) {
            String[] result = s.split("\\s+");
            list1.add(result[0]);

            if(map.get(result[1]) == null) {
                map.put(result[1], 1);
            } else {
                map.put(result[1], map.get(result[1]) + 1);
            }
        }
        int acc = 0;
        for(String s : list1) {
            if(map.get(s) != null) {
                acc += Integer.parseInt(s) * map.get(s);
            }
        }

        System.out.println("Part 2: " + acc);
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("input01.txt");
        part1(input);
        part2(input);
    }
}