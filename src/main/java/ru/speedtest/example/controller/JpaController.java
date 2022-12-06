package ru.speedtest.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.speedtest.example.service.JpaService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jpa")
public class JpaController {
    private final JpaService speedTestJpaService;

    @PostMapping
    public void executeSpeedTestJpa() {
        speedTestJpaService.usesStreamInJpa();
    }

    @PostMapping("/cache-fetch-size")
    public void executeStreamInJpaCacheableTrueFetchSize() {
        speedTestJpaService.usesStreamInJpaCacheableTrueFetchSize();
    }

    @PostMapping("/read-only")
    public void executeStreamInJpaWithReadOnlyAndCacheableFalse() {
        speedTestJpaService.usesStreamInJpaWithReadOnlyAndCacheableFalse();
    }

    @PostMapping("/read-only-and-cache-fetch-size")
    public void executeStreamInJpaWithReadOnlyAndCacheableTrueFetchSize() {
        speedTestJpaService.usesStreamInJpaWithReadOnlyAndCacheableTrueFetchSize();
    }

    @PostMapping("/read-only-fetch-size")
    public void executeStreamReadOnlyAndCacheableFalseSizeFetch() {
        speedTestJpaService.usesStreamReadOnlyAndCacheableFalseSizeFetch();
    }

    @PostMapping("/fetch-size")
    public void executeStreamSizeFetch() {
        speedTestJpaService.usesStreamFetchSize();
    }
}
