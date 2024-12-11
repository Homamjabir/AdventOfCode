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

        List<String> emptySpaceList = new ArrayList<>();
        int firstIndex = -1;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == -1 && firstIndex == -1)
                firstIndex = i;
            else if (nums.get(i) != -1 && firstIndex != -1) {
                emptySpaceList.add(firstIndex + "," + (i - 1));
                firstIndex = -1;
            }
        }

        List<String> fileList = new ArrayList<>();
        int fileId = -1;
        int fileFirstIndex = -1;
        for (int i = 0; i < nums.size(); i++) {
            if(nums.get(i) != -1 && fileId == -1) {
                fileId = nums.get(i);
                fileFirstIndex = i;
            } else if (fileFirstIndex != -1) {
                if(nums.get(i) == -1) {
                    fileList.add(fileId + ":" + fileFirstIndex + "," + (i - 1));
                    fileId = -1;
                    fileFirstIndex = -1;
                } else if (nums.get(i) != fileId) {
                    fileList.add(fileId + ":" + fileFirstIndex + "," + (i - 1));
                    fileId = -1;
                    fileFirstIndex = -1;
                    i--;
                } else if(i + 1 == nums.size()) {
                    fileList.add(fileId + ":" + fileFirstIndex + "," + i);
                }

            }
        }


        for (int i = fileList.size()-1; i >= 0; i--) {
            String[] idAndIndex = fileList.get(i).split(":");
            String[] index = idAndIndex[1].split(",");

            int fileSize = (Integer.parseInt(index[1]) - Integer.parseInt(index[0]));
            for (int j = 0; j < emptySpaceList.size(); j++) {

                String[] emptySpaceIndex = emptySpaceList.get(j).split(",");

                if(Integer.parseInt(emptySpaceIndex[0]) >= Integer.parseInt(index[0]))
                    break;

                int emptySpaceSize = (Integer.parseInt(emptySpaceIndex[1]) - Integer.parseInt(emptySpaceIndex[0]));

                if(fileSize <= emptySpaceSize) {
                    for (int k = Integer.parseInt(emptySpaceIndex[0]); k <= Integer.parseInt(emptySpaceIndex[0]) + fileSize; k++) {
                        nums.set(k, Integer.parseInt(idAndIndex[0]));
                    }
                    for (int k = Integer.parseInt(index[0]); k <= Integer.parseInt(index[1]); k++) {
                        nums.set(k, -1);
                    }
                    if(fileSize == emptySpaceSize) {
                        emptySpaceList.remove(j);
                    }

                    else {
                        emptySpaceList.set(j, (Integer.parseInt(emptySpaceIndex[0]) + fileSize + 1) + "," + emptySpaceIndex[1]);
                    }
                    break;
                }
            }
        }

        long acc = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) != -1)
                acc += (long) nums.get(i) * i;

        }
        System.out.println("Part 2: " + acc);
    }

    public static void main(String[] args) throws IOException {
        List<String> input = FileReaderUtil.readLinesFromFile("/2024/input09.txt");
        part1(input);
        part2(input);
    }
}