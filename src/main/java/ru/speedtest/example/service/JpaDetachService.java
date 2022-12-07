package ru.speedtest.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.speedtest.example.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileWriter;

@Service
@RequiredArgsConstructor
public class JpaDetachService {

    @PersistenceContext
    private EntityManager entityManager;

    private final JpaRepository speedTestRepository;
    private final FileService fileService;

    @Transactional(readOnly = true)
    public void usesStreamInJpaWithReadOnlyAndCacheableTrueFetchSize() {
        long startTime = System.currentTimeMillis();
        FileWriter fileWriter = fileService.createFileWriter("JpaWithReadOnlyAndCacheableTrue.txt");
        speedTestRepository.findAllWithStreamReadOnlyAndCacheableTrueFetchSize().forEach(i -> {
            int hashCode = i.getHashCodeAllObject();
            fileService.writeHashCode(fileWriter, hashCode);
            entityManager.detach(i);
        });
        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);

        fileService.closeFileWriter(fileWriter);
    }

    @Transactional
    public void usesStreamInJpaCacheableTrueFetchSize() {
        long startTime = System.currentTimeMillis();
        FileWriter fileWriter = fileService.createFileWriter("jpaCacheableTrue.txt");
        speedTestRepository.findAllWithStreamCacheableTrueFetchSize().forEach(i -> {
            int hashCode = i.getHashCodeAllObject();
            fileService.writeHashCode(fileWriter, hashCode);
            entityManager.detach(i);
        });
        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);

        fileService.closeFileWriter(fileWriter);
    }

    @Transactional(readOnly = true)
    public void usesStreamReadOnlyAndCacheableFalseSizeFetch() {
        long startTime = System.currentTimeMillis();
        FileWriter fileWriter = fileService.createFileWriter("jpaStreamReadOnlyAndCacheableFalseSizeFetch.txt");
        speedTestRepository.findAllWithStreamReadOnlyAndCacheableFalseSizeFetch().forEach(i -> {
            int hashCode = i.getHashCodeAllObject();
            fileService.writeHashCode(fileWriter, hashCode);
            entityManager.detach(i);
        });
        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);

        fileService.closeFileWriter(fileWriter);
    }

    @Transactional
    public void usesStreamFetchSize() {
        long startTime = System.currentTimeMillis();
        FileWriter fileWriter = fileService.createFileWriter("jpaStreamFetchSize.txt");
        speedTestRepository.findAllWithStreamFetchSize().forEach(i -> {
            int hashCode = i.getHashCodeAllObject();
            fileService.writeHashCode(fileWriter, hashCode);
            entityManager.detach(i);
        });
        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);

        fileService.closeFileWriter(fileWriter);
    }
}
