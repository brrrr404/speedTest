package ru.speedtest.example.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileService {

    private static final String FILE_PATH = "C:\\Users\\emelianov-aa\\IdeaProjects\\SpringBootApp-master\\hashfiles\\";


    public void writeHashCode(FileWriter file, int hashCode) {
        try {
            file.write(hashCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileWriter createFileWriter(String fileName) {
        try {
            File file = new File(FILE_PATH + fileName);
            return new FileWriter(file, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeFileWriter(FileWriter fileWriter){
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
