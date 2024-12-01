package org.homamjabir.adventofcode.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {
    static String inputFilesBasePath = "src/main/resources/inputs/";

    public static List<String> readLinesFromFile(String filename) throws IOException {
        List<String> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilesBasePath + filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return input;
    }
}