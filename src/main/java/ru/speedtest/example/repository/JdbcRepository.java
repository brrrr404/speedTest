package ru.speedtest.example.repository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.speedtest.example.entity.SpeedTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcRepository {
    private final static String MAIN_QUERY = "select * from test";

    private final JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public List<SpeedTest> getSpeedTestList() {
        Query query = entityManager.createNativeQuery(MAIN_QUERY);
        return mapResult(query.getResultList());
    }

    @SneakyThrows
    public List<SpeedTest> getDataWithJdbcTemplate(int fetchSize, boolean prReadOnly, boolean prAutoCommit) {
        jdbcTemplate.setFetchSize(fetchSize);
        jdbcTemplate.getDataSource().getConnection().setReadOnly(prReadOnly);
        jdbcTemplate.getDataSource().getConnection().setAutoCommit(prAutoCommit);
        return jdbcTemplate.query(MAIN_QUERY, ROW_MAPPER);
    }

    private List<SpeedTest> mapResult(List<Object> resultList) {
        List<SpeedTest> speedTestEntityList = new ArrayList<>();
        for (Object row : resultList) {
            Object[] rowData = (Object[]) row;
            Long id = ((Integer) rowData[0]).longValue();
            String str1 = (String) rowData[1];
            String str2 = (String) rowData[2];
            String str3 = (String) rowData[3];
            String str4 = (String) rowData[4];
            String str5 = (String) rowData[5];
            String str6 = (String) rowData[6];
            SpeedTest dto = new SpeedTest(id, str1, str2, str3, str4, str5, str6);
            speedTestEntityList.add(dto);

        }
        return speedTestEntityList;
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
