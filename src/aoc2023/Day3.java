package aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException {
//        part1();
        part2();
    }

    private static void part1() throws IOException {
        List<String> field = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/aoc2023/input/Day3.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                field.add(line.strip());
            }
        }

        int acc = 0;

        for (String line : field) {
            StringBuilder number = new StringBuilder();
            for (int j = 0; j < line.length(); j++) {
                if (Character.isDigit(line.charAt(j))) {
                    number.append(line.charAt(j));
                    if (j == line.length() - 1) {
                        if (isPartNumber(field.indexOf(line), j - number.length(), number.length(), field)) {
                            acc += Integer.parseInt(number.toString());
                        }
                    }
                } else {
                    if (!number.isEmpty()) {
                        if (isPartNumber(field.indexOf(line), j - number.length(), number.length(), field)) {
                            acc += Integer.parseInt(number.toString());
                        }
                        number.setLength(0);
                    }
                }
            }
        }
        System.out.println(acc);
    }

    private static boolean isPartNumber(int row, int col, int numLength, List<String> field) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j < (col + numLength + 1); j++) {
                if (!(i < 0 || field.size() <= i
                        || j < 0 || field.get(0).length() < j)) {
                    if (!Character.isDigit(field.get(i).charAt(j))
                            && field.get(i).charAt(j) != '.') {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void part2() throws IOException {
        List<String> field = new ArrayList<>();

        List<String> indexes = new ArrayList<>();

        long acc = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/aoc2023/input/Day3.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                field.add(line.strip());

                for (int i = 0; i < field.get(field.size() - 1).length(); i++) {
                    if (field.get(field.size() - 1).charAt(i) == '*') {
                        indexes.add((field.size() - 1) + "-" + i);
                    }
                }
            }
        }

        for (String index : indexes) {
            String[] tmp = index.split("-");
            int row = Integer.parseInt(tmp[0]);
            int col = Integer.parseInt(tmp[1]);
            List<Long> nums = new ArrayList<>();
            for (int k = (row - 1); k <= (row + 1); k++) {
                for (int l = (col - 1); l <= (col + 1); l++) {
                    if (Character.isDigit(field.get(k).charAt(l))) {
                        nums.add(getInteger(field.get(k), l));
                        l = getLastIndex(field.get(k), l);
                    }
                }
            }
            if (nums.size() == 2) {
                acc += nums.get(0) * nums.get(1);
            }
            nums.clear();
        }
        System.out.println(acc);

    }

    private static int getLastIndex(String row, int colIndex) {
        int lastIndex = 0;
        for (int i = colIndex+1; i < row.length(); i++) {
            if (!Character.isDigit(row.charAt(i)) || i == row.length()-1) {
                lastIndex = i - 1;
                break;
            }
        }
        return lastIndex;
    }

    private static Long getInteger(String row, int colIndex) {
        int firstIndex = 0;
        for (int i = colIndex; i > -1; i--) {
            if (!Character.isDigit(row.charAt(i))) {
                firstIndex = i + 1;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();

        for (int i = firstIndex; i < row.length(); i++) {
            if (Character.isDigit(row.charAt(i)))
                sb.append(row.charAt(i));
            else {
                break;
            }
        }
        return Long.parseLong(sb.toString());
    }
}