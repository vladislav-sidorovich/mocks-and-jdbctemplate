package org.example.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.postgresql.gss.GSSOutputStream;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@RunWith(MockitoJUnitRunner.class)
public class SquareDAOTests {
    private static PostgreSQLContainer postgreSQLContainer;
    private static DataSource dataSource;

    private SquareDAO squareDAO;

    @BeforeClass
    public static void beforeClass() throws Exception {
        postgreSQLContainer = new PostgreSQLContainer("postgres:14");
        postgreSQLContainer.start();

        //run liquibase or flyway
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(postgreSQLContainer.getJdbcUrl());
        config.setUsername(postgreSQLContainer.getUsername());
        config.setPassword(postgreSQLContainer.getPassword());
        config.setSchema(postgreSQLContainer.getDatabaseName());

        dataSource = new HikariDataSource(config);
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:sql")
                .load();
        flyway.migrate();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        postgreSQLContainer.stop();
    }

    @Before
    public void setUp() throws Exception {
        squareDAO = new SquareDAO(dataSource);
    }

    @Test
    public void query() {
        double byObjectId = squareDAO.getByObjectId(1);
    }
}
