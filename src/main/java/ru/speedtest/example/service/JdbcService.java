package ru.speedtest.example.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.speedtest.example.entity.SpeedTest;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcService {
    private final FileService fileService;

    private final static String MAIN_QUERY = "select * from test";

    private static final String HOST = "192.168.15.53";
    private static final String PORT = "5432";
    private static final String DB_NAME = "speed_test";
    private static final String LOGIN = "its";
    private static final String PASS = "its";

    private final JdbcTemplate jdbcTemplate;

    private Connection dbConn = null;

    @SneakyThrows
    private Connection getDbConnection() {
        String connStr = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB_NAME + "?autoReconnect=true";
        Class.forName("org.postgresql.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);

        return dbConn;
    }

    @SneakyThrows
    public void executeJdbcRespone() {
        long startTime = System.currentTimeMillis();
        FileWriter fileWriter = fileService.createFileWriter("jdbcTest.txt");

        PreparedStatement statement = getDbConnection().prepareStatement(MAIN_QUERY);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Long id = result.getLong("id");
            String str1 = result.getString("test_1");
            String str2 = result.getString("test_2");
            String str3 = result.getString("test_3");
            String str4 = result.getString("test_4");
            String str5 = result.getString("test_5");
            String str6 = result.getString("test_6");
            SpeedTest speedTest = new SpeedTest(id, str1, str2, str3, str4, str5, str6);
            int hashCode = speedTest.getHashCodeAllObject();
            fileService.writeHashCode(fileWriter, hashCode);
        }
        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);

        fileService.closeFileWriter(fileWriter);
    }

    @SneakyThrows
    public void executeJdbcResponeWithFetchSize() {
        long startTime = System.currentTimeMillis();
        FileWriter fileWriter = fileService.createFileWriter("jdbcTest.txt");

        PreparedStatement statement = getDbConnection().prepareStatement(MAIN_QUERY);
        statement.setFetchSize(100);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Long id = result.getLong("id");
            String str1 = result.getString("test_1");
            String str2 = result.getString("test_2");
            String str3 = result.getString("test_3");
            String str4 = result.getString("test_4");
            String str5 = result.getString("test_5");
            String str6 = result.getString("test_6");
            SpeedTest speedTest = new SpeedTest(id, str1, str2, str3, str4, str5, str6);
            int hashCode = speedTest.getHashCodeAllObject();
            fileService.writeHashCode(fileWriter, hashCode);
        }
        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);

        fileService.closeFileWriter(fileWriter);
    }

    @SneakyThrows
    public void executeJdbcTemplateRespone(boolean prReadOnly, boolean prAutoCommit, String fileName) {
        long startTime = System.currentTimeMillis();
        FileWriter fileWriter = fileService.createFileWriter(fileName);
        getDataWithJdbcTemplate(prReadOnly, prAutoCommit).forEach(i -> {
            int hashCode = i.getHashCodeAllObject();
            fileService.writeHashCode(fileWriter, hashCode);
        });
        long endTime = System.currentTimeMillis();
        String allTimeInSeconds = (endTime - startTime) / 1000 + " seconds";
        System.out.println(allTimeInSeconds);

        fileService.closeFileWriter(fileWriter);
    }

    @Transactional(readOnly = true)
    public void executeJdbcTemplateResponeWithTransaction(Boolean prReadOnly,
                                                          Boolean prAutoCommit) {
        executeJdbcTemplateRespone(prReadOnly, prAutoCommit, "jdbcTemplateTransactionalTest.txt");
    }


    @SneakyThrows
    private List<SpeedTest> getDataWithJdbcTemplate(boolean prReadOnly, boolean prAutoCommit) {
        jdbcTemplate.setFetchSize(100);
        jdbcTemplate.getDataSource().getConnection().setReadOnly(prReadOnly);
        jdbcTemplate.getDataSource().getConnection().setAutoCommit(prAutoCommit);
        return jdbcTemplate.query(MAIN_QUERY, ROW_MAPPER);
    }

    private RowMapper<SpeedTest> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new SpeedTest(resultSet.getLong("id"),
                resultSet.getString("test_1"),
                resultSet.getString("test_2"),
                resultSet.getString("test_3"),
                resultSet.getString("test_4"),
                resultSet.getString("test_5"),
                resultSet.getString("test_6"));
    };
}
