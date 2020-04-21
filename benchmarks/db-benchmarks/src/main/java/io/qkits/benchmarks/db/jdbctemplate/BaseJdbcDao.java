package io.qkits.benchmarks.db.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BaseJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getResult(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getQueryResults(String sql, String... arguments) {
        return jdbcTemplate.queryForList(sql, arguments);
    }

    public <T> List<T> getQueryBeans(String sql, Class resultClazz, String... arguments) {
        return jdbcTemplate.queryForList(sql, resultClazz, arguments);
    }

    public void saveOrUpdate(String sql){
        jdbcTemplate.execute(sql);
    }

}