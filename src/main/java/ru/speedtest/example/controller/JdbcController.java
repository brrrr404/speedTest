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
    
    @PostMapping("/template/{fetchSize}/pr")
    public void executeJdbcTemplateWithCustomFetchSize(@PathVariable
                                                           Integer fetchSize,
                                                       @RequestParam(name = "prReadOnly") Boolean prReadOnly,
                                                       @RequestParam(name = "prAutoCommit") Boolean prAutoCommit) {
        jdbcService.executeJdbcTemplateRespone(fetchSize, prReadOnly, prAutoCommit, "jdbcTemplateTest.txt");
    }

    @PostMapping("/template-transactional/{fetchSize}/pr")
    public void executeJdbcTemplateWithCustomFetchSizeTransaction(@PathVariable
                                                       Integer fetchSize,
                                                       @RequestParam(name = "prReadOnly") Boolean prReadOnly,
                                                       @RequestParam(name = "prAutoCommit") Boolean prAutoCommit) {
        jdbcService.executeJdbcTemplateResponeWithTransaction(fetchSize, prReadOnly, prAutoCommit);
    }
}
