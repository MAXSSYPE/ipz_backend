package com.myproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author Dudka Maxym
 * @version 12.0.2
 * Class for db
 */

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * crops mapping
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }

    /**
     * postgres source for spring
     *
     * @return dataSource
     */
    public static DataSource posgresqlDataSource() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("data.sql"));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("132435465");
        resourceDatabasePopulator.setScripts(new ClassPathResource("data.sql"));

        return dataSource;
    }
}
