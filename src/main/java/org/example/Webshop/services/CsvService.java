package org.example.Webshop.services;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

public class CsvService {
    private static Logger logger = LogManager.getLogger(CsvService.class);

    public <T> List<T> readCsv(Path filePath, Class<T> type) throws Exception {
        logger.info("Reading from file: " + filePath.toString());
        List<T> elements = new CsvToBeanBuilder<T>(new FileReader(filePath.toString()))
                .withType(type).withSeparator(';')
                .build().parse();

        return elements;
    }

    public <T> void writeCsv(Path filePath, List<T> elements) {
        logger.info("Writing to file: " + filePath.toString());

        try (Writer writer = new FileWriter(filePath.toString())) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(';')
                    .build();

            beanToCsv.write(elements);
        } catch (Exception e) {
            logger.error("Error writing to file: " + e.getMessage());
        }
    }
}
