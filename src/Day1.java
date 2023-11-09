import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {

    public static void main(String[] args) throws IOException {
        int highestCals = 0;
        int secondHighestCals = 0;
        int thirdHighestCals = 0;
        int accumulator = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/input/Day1.txt"))) {
            String line;
            while (true) {
                line = br.readLine();

                if(line == null) {
                    if (accumulator > highestCals) {
                        thirdHighestCals = secondHighestCals;
                        secondHighestCals = highestCals;
                        highestCals = accumulator;
                    } else if (accumulator > secondHighestCals) {
                        thirdHighestCals = secondHighestCals;
                        secondHighestCals = accumulator;
                    } else if (accumulator > thirdHighestCals) {
                        thirdHighestCals = accumulator;
                    }
                    break;
                } else if(line.isEmpty()) {
                    if (accumulator > highestCals) {
                        thirdHighestCals = secondHighestCals;
                        secondHighestCals = highestCals;
                        highestCals = accumulator;
                    } else if (accumulator > secondHighestCals) {
                        thirdHighestCals = secondHighestCals;
                        secondHighestCals = accumulator;
                    } else if (accumulator > thirdHighestCals) {
                        thirdHighestCals = accumulator;
                    }
                    accumulator = 0;
                } else {
                    accumulator += Integer.parseInt(line);
                }
            }
        }
        System.out.println("Part 1: " + highestCals);
        System.out.println("Part 2: " + (highestCals + secondHighestCals + thirdHighestCals));
    }
}