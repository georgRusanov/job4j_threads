package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return getContent(x -> true);
    }

    public String getContentWithoutUnicode() {
        return getContent(x -> x < 0x80);
    }

    public String getContent(Predicate<Integer> filter) {
        StringBuilder output = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            int data;
            while ((data = br.read()) != 0) {
                if (filter.test(data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
