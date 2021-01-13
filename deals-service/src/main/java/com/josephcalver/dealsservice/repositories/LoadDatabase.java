package com.josephcalver.dealsservice.repositories;

import com.josephcalver.dealsservice.models.Deal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(DealsRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Deal(
                    "f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a", "e254f8c-c442-4ebe-a82a-e2fc1d1ff78a",
                    "Growth Equity", "Project Bluebird", "11/02/2019", "Active",
                    "ECP Fund I", "John Stanmore", 15, "CAD")));
            log.info("Preloading " + repository.save(new Deal(
                    "t9876f8c-c338-4abc-zf6a-ttt1", "e254f8c-c442-4ebe-a82a-e2fc1d1ff78a",
                    "Growth Equity", "Project Spitfire", "17/03/2017", "Cold",
                    "ECP Fund II", "Bryan Montecute", 5, "USD"
                    )));
            log.info("Preloading " + repository.save(new Deal(
                    "38777179-7094-4200-9d61-edb101c6ea84", "442adb6e-fa58-47f3-9ca2-ed1fecdfe86c",
                    "Management Buyout", "Project Monet", "10/05/2020", "Active",
                    "ECP Fund I", "Nelson Granger", 20, "USD"
                    )));
        };
    }
}
