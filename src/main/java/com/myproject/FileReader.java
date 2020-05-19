package com.myproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

/**
 * @author Dudka Maxym
 * @version 12.0.2
 * Class to access files
 */

public class FileReader {
    private static final String ERROR_MESSAGE = "File name cannot be null!";
    private static final String FILE_NOT_FOUND = "File not found!";


    public String readFile(String fileName, String joiner) throws IOException {
        return String.join(joiner, readFile(fileName));
    }
    
    protected List<String> readFile(String fileName) throws IOException {
        if (fileName == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        List<String> result; 
        InputStream inputStream = getClass()
            .getClassLoader()
            .getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IOException(FILE_NOT_FOUND);
        }
        result = (new BufferedReader(new InputStreamReader(inputStream, UTF_8)))
            .lines()
            .collect(toList());
        inputStream.close();
        return result;
    }
    
}
