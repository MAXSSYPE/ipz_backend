package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * @author Dudka Maxym
 * @version 12.0.2
 * Main class
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Main {

    /**
     * main
     */
    public static void main(String[] s) {
        SpringApplication.run(Main.class, s);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("data.sql"));
        DatabasePopulatorUtils.execute(databasePopulator, WebConfig.posgresqlDataSource());
       /* Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.YEAR, -1); // from 1 year ago

        Stock google = YahooFinance.get("GOOG");
        List<HistoricalQuote> googleHistQuotes = google.getHistory(from, to, Interval.DAILY);
        for (HistoricalQuote googleHistQuote : googleHistQuotes) {
            //logger.info(googleHistQuote.toString());
            System.out.println(googleHistQuote.getClose());
        }*/
    }
}
