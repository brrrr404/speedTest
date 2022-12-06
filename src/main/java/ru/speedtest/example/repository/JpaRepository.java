package ru.speedtest.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ru.speedtest.example.entity.SpeedTest;

import javax.persistence.QueryHint;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.*;

@Repository
public interface JpaRepository extends org.springframework.data.jpa.repository.JpaRepository<SpeedTest, Long> {

    //read only on
    @QueryHints(value = {
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = HINT_READONLY, value = "true")
    })
    @Query("select n from SpeedTest n")
    Stream<SpeedTest> findAllWithStreamReadOnlyAndCacheableFalse();


    //fetch-size = 50000, read only on, cacheable off
    @QueryHints(value = {
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = HINT_READONLY, value = "true"),
            @QueryHint(name = HINT_FETCH_SIZE, value = "50000")
    })
    @Query("select n from SpeedTest n")
    Stream<SpeedTest> findAllWithStreamReadOnlyAndCacheableFalseSizeFetch();


    //fetch-size = 50000, read only on, cacheable on
    @QueryHints(value = {
            @QueryHint(name = HINT_CACHEABLE, value = "true"),
            @QueryHint(name = HINT_READONLY, value = "true"),
            @QueryHint(name = HINT_FETCH_SIZE, value = "50000")
    })
    @Query("select n from SpeedTest n")
    Stream<SpeedTest> findAllWithStreamReadOnlyAndCacheableTrueFetchSize();


    //fetch-size = 50000, read only off, cacheable on
    @QueryHints(value = {
            @QueryHint(name = HINT_CACHEABLE, value = "true"),
            @QueryHint(name = HINT_READONLY, value = "false"),
            @QueryHint(name = HINT_FETCH_SIZE, value = "50000")
    })
    @Query("select n from SpeedTest n")
    Stream<SpeedTest> findAllWithStreamCacheableTrueFetchSize();


    //fetch-size = 50000
    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value = "50000")
    })
    @Query("select n from SpeedTest n")
    Stream<SpeedTest> findAllWithStreamFetchSize();


    //default
    @Query("select n from SpeedTest n")
    Stream<SpeedTest> findAllWithStream();
}
