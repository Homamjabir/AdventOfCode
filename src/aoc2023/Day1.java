package aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day1 {

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    private static void part1() throws IOException {
        int acc = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/aoc2023/input/Day1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int first = -1;
                int last = -1;
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        if (first == -1)
                            first = Character.getNumericValue(c);
                        else
                            last = Character.getNumericValue(c);
                    }
                }
                acc += first * 10 + (last == -1 ? first : last);
            }
        }
        System.out.println(acc);
    }

    private static void part2() throws IOException {
        Map<String, Integer> wordToNumber = new HashMap<>();
        wordToNumber.put("one", 1);
        wordToNumber.put("two", 2);
        wordToNumber.put("three", 3);
        wordToNumber.put("four", 4);
        wordToNumber.put("five", 5);
        wordToNumber.put("six", 6);
        wordToNumber.put("seven", 7);
        wordToNumber.put("eight", 8);
        wordToNumber.put("nine", 9);

        int acc = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/aoc2023/input/Day1.txt"))) {
            String asd;
            while ((asd = br.readLine()) != null) {
                int first = -1;
                int last = -1;
                String line = formatLine(asd);
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isDigit(c)) {
                        if (first == -1)
                            first = Character.getNumericValue(c);
                        else
                            last = Character.getNumericValue(c);
                    }
                }
                acc += first * 10 + (last == -1 ? first : last);
            }
        }
        System.out.println(acc);
    }

    private static String formatLine(String line) {
        return line
                .replace("one", "one1one")
                .replace("two", "two2two")
                .replace("three", "three3three")
                .replace("four", "four4four")
                .replace("five", "five5five")
                .replace("six", "six6six")
                .replace("seven", "seven7seven")
                .replace("eight", "eight8eight")
                .replace("nine", "nine9nine");
    }
}