package io.dh.spring.emailrender.dao;

import io.dh.spring.emailrender.domain.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class EmailConfigDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public  Map<String,Object> getMapById(int ecId){
        String sql = "select * from email_config where id = ?";
        RowMapper mapper = new ColumnMapRowMapper();
        Map<String,Object> result = jdbcTemplate.queryForMap(sql,ecId);
        return result;
    }


    public  EmailConfig getEmailConfigById(int ecId){
        String sql = "select * from email_config where id = ?";
        RowMapper mapper = new EmailConfigMapper();
        return (EmailConfig)jdbcTemplate.queryForObject(sql,mapper,ecId);
    }

    public  List<Map<String,Object>> getResult(String sql){
        RowMapper mapper = new ColumnMapRowMapper();
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }
    private static class EmailConfigMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            EmailConfig emailConfig = new EmailConfig();
            emailConfig.setId(resultSet.getInt("id"));
            emailConfig.setEmailConfigDesc(resultSet.getString("email_config_desc"));
            emailConfig.setContentSql(resultSet.getString("content_sql"));
            emailConfig.setColumnMapping(resultSet.getString("column_mapping"));
            emailConfig.setFromAddress(resultSet.getString("from_address"));
            emailConfig.setTo(resultSet.getString("to"));
            emailConfig.setIsActive(resultSet.getInt("is_active"));
            emailConfig.setCreatedTime(resultSet.getDate("created_time"));
            return emailConfig;
        }
    }
}
