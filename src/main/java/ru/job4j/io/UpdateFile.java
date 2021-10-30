package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateFile {
    public void saveContent(File file, String content) {
        synchronized (file) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
