package aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day2 {

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    private static void part1() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/aoc2023/input/Day2.txt"))) {
            Map<String, Integer> map = new HashMap<>();
            map.put("red", 12);
            map.put("green", 13);
            map.put("blue", 14);

            int acc = 0;
            String line;

            while ((line = br.readLine()) != null) {
                String[] result = line.split("[:;]");
                int gameId = Integer.parseInt(result[0].split(" ")[1]);

                boolean valid = true;

                for (int i = 1; i < result.length && valid; i++) {
                    String[] temp = result[i].split(",");
                    for (String pull : temp) {
                        String[] parts = pull.split(" ");
                        int value = Integer.parseInt(parts[1]);
                        if (map.get(parts[2]) < value) {
                            valid = false;
                            break;
                        }
                    }
                }
                if (valid)
                    acc += gameId;
            }

            System.out.println(acc);
        }
    }

    private static void part2() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/aoc2023/input/Day2.txt"))) {
            Map<String, Integer> map = new HashMap<>();
            int acc = 0;
            resetMap(map);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.substring(7).split("[;,]");
                for(String turn : result ) {
                    String[] asd = turn.split(" ");
                    if(map.get(asd[2]) < Integer.parseInt(asd[1])) {
                        map.put(asd[2], Integer.parseInt(asd[1]));
                    }
                }
                acc += (map.get("red") * map.get("green") * map.get("blue"));
                resetMap(map);
            }
            System.out.println(acc);
        }
    }

    private static void resetMap(Map<String, Integer> map) {
        map.put("red", 0);
        map.put("green", 0);
        map.put("blue", 0);
    }
}
