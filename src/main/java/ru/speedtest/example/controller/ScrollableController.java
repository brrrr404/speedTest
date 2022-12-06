package ru.speedtest.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.speedtest.example.service.ScrollableResultsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scrollable")
public class ScrollableController {

    private final ScrollableResultsService scrollableService;

    @PostMapping
    public void executeScrollable() {
        scrollableService.executeScrollableResults();
    }
}
