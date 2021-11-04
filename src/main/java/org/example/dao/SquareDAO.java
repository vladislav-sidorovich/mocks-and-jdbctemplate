package org.example.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SquareDAO {
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SquareDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public double getByObjectId(int id) {
        String sql = "select square from cache_table where id = ?";

        Map<String, Integer> params = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql,
                params,
                Double.class);
    }

    private Double execute(String sql, int id, String columnName) {
        try {
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return  resultSet.getDouble(columnName);
                }

                throw new IllegalArgumentException("Not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
