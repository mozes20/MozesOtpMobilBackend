package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Webshop.controllers.Webshop;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws URISyntaxException {
        Webshop webshop = null;
        try {
            webshop = new Webshop(
                    Paths.get("src/main/resources/csv/customer.csv"),
                    Paths.get("src/main/resources/csv/payments.csv")
            );
        } catch (Exception e) {
            logger.error("Error reading file: " + e.getMessage());
        }
        webshop.run();
    }
}