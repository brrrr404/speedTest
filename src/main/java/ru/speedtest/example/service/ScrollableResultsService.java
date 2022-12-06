package ru.speedtest.example.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.speedtest.example.entity.SpeedTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileWriter;

@Service
@RequiredArgsConstructor
public class ScrollableResultsService {

    private final static String MAIN_QUERY = "select t from SpeedTest t";

    private final FileService fileService;

    @PersistenceContext
    private EntityManager entityManager;


    public void executeScrollableResults() {
        long startTime = System.currentTimeMillis();

        Session session = (Session) entityManager.getDelegate();
        ScrollableResults scResults = session.createQuery(MAIN_QUERY)
                .scroll(ScrollMode.SCROLL_INSENSITIVE);
        FileWriter fileWriter = fileService.createFileWriter("scrollable.txt");

        while (scResults.next()) {
            SpeedTest c = (SpeedTest) scResults.get(0);
            int hashCode = c.getHashCodeAllObject();
            fileService.writeHashCode(fileWriter, hashCode);
        }
        scResults.close();

        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);
        session.close();
    }

}
