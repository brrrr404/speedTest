package ru.speedtest.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.speedtest.example.service.JdbcService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    private final JdbcService jdbcService;

    @PostMapping
    public void executeJdbcDefault() {
        jdbcService.executeJdbcRespone();
    }

    @PostMapping("/fetch-size")
    public void executeJdbcDefaultWithFetchSize() {
        jdbcService.executeJdbcResponeWithFetchSize();
    }
    
    @PostMapping("/template/pr")
    public void executeJdbcTemplateWithCustomFetchSize(
                                                       @RequestParam(name = "prReadOnly") Boolean prReadOnly,
                                                       @RequestParam(name = "prAutoCommit") Boolean prAutoCommit) {
        jdbcService.executeJdbcTemplateRespone(prReadOnly, prAutoCommit, "jdbcTemplateTest.txt");
    }

    @PostMapping("/template-transactional/pr")
    public void executeJdbcTemplateWithCustomFetchSizeTransaction(
                                                       @RequestParam(name = "prReadOnly") Boolean prReadOnly,
                                                       @RequestParam(name = "prAutoCommit") Boolean prAutoCommit) {
        jdbcService.executeJdbcTemplateResponeWithTransaction(prReadOnly, prAutoCommit);
    }
}
