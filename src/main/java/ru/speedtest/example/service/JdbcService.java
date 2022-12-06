package ru.speedtest.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.speedtest.example.repository.JdbcRepository;

import java.io.FileWriter;

@Service
@RequiredArgsConstructor
public class JdbcService {
    private final JdbcRepository speedTestJbdsRepository;
    private final FileService fileService;

    public void executeJdbcRespone() {
        long startTime = System.currentTimeMillis();
        FileWriter fileWriter = fileService.createFileWriter("jdbcTest.txt");
        speedTestJbdsRepository.getSpeedTestList().forEach(i -> {
            int hashCode = i.getHashCodeAllObject();
            fileService.writeHashCode(fileWriter, hashCode);
        });
        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);
    }

    public void executeJdbcTemplateRespone(int fetchSize, boolean prReadOnly, boolean prAutoCommit, String fileName) {
        long startTime = System.currentTimeMillis();
        FileWriter fileWriter = fileService.createFileWriter(fileName);
        speedTestJbdsRepository.getDataWithJdbcTemplate(fetchSize, prReadOnly, prAutoCommit)
                .forEach(i -> {
                    int hashCode = i.getHashCodeAllObject();
                    fileService.writeHashCode(fileWriter, hashCode);
                });
        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);
    }

    @Transactional(readOnly = true)
    public void executeJdbcTemplateResponeWithTransaction(Integer fetchSize,
                                                          Boolean prReadOnly,
                                                          Boolean prAutoCommit) {
        executeJdbcTemplateRespone(fetchSize, prReadOnly, prAutoCommit, "jdbcTemplateTransactionalTest.txt");
    }
}
