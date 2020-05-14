package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;
import java.util.logging.Logger;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Main {

    private static final Logger logger = Logger.getLogger(String.valueOf(Main.class));

    public static void main(String[] s) {
        SpringApplication.run(Main.class, s);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("data.sql"));
        DatabasePopulatorUtils.execute(databasePopulator, WebConfig.posgresqlDataSource());
    }
}
