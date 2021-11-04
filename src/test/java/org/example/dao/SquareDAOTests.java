package org.example.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@RunWith(MockitoJUnitRunner.class)
public class SquareDAOTests {
    private static PostgreSQLContainer postgreSQLContainer;

    private SquareDAO squareDAO;

    @BeforeClass
    public static void beforeClass() throws Exception {
        postgreSQLContainer = new PostgreSQLContainer("postgres:9.6.12");
        postgreSQLContainer.start();
        //run liquibase or flyway
    }

    @AfterClass
    public static void afterClass() throws Exception {
        postgreSQLContainer.stop();
    }

    @Before
    public void setUp() throws Exception {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(postgreSQLContainer.getJdbcUrl());
        config.setUsername(postgreSQLContainer.getUsername());
        config.setPassword(postgreSQLContainer.getPassword());
        config.setSchema(postgreSQLContainer.getDatabaseName());
        DataSource dataSource = new HikariDataSource(config);
        squareDAO = new SquareDAO(dataSource);
    }

    @Test
    public void query() {
        double byObjectId = squareDAO.getByObjectId(1);
    }
}
