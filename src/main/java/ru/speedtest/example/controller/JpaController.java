package ru.speedtest.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.speedtest.example.service.JpaDetachService;
import ru.speedtest.example.service.JpaService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jpa")
public class JpaController {
    private final JpaService speedTestJpaService;
    private final JpaDetachService jpadetachService;

    @PostMapping("/cache-fetch-size")
    public void executeStreamInJpaCacheableTrueFetchSize() {
        speedTestJpaService.usesStreamInJpaCacheableTrueFetchSize();
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


    ////// detach

    @PostMapping("/detach/cache-fetch-size")
    public void executeStreamInJpaCacheableTrueFetchSizeDetach() {
        jpadetachService.usesStreamInJpaCacheableTrueFetchSize();
    }

    @PostMapping("/detach/read-only-and-cache-fetch-size")
    public void executeStreamInJpaWithReadOnlyAndCacheableTrueFetchSizeDetach() {
        jpadetachService.usesStreamInJpaWithReadOnlyAndCacheableTrueFetchSize();
    }

    @PostMapping("/detach/read-only-fetch-size")
    public void executeStreamReadOnlyAndCacheableFalseSizeFetchDetach() {
        jpadetachService.usesStreamReadOnlyAndCacheableFalseSizeFetch();
    }

    @PostMapping("/detach/fetch-size")
    public void executeStreamSizeFetchDetach() {
        jpadetachService.usesStreamFetchSize();
    }
}
