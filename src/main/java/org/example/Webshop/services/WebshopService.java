package org.example.Webshop.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Webshop.domain.Customer;
import org.example.Webshop.domain.Payments;
import org.example.Webshop.domain.Report1;
import org.example.Webshop.domain.WebshopReport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WebshopService {
    private static Logger logger = LogManager.getLogger(WebshopService.class);

    public List<Customer> combineCustomerAndPayments(List<Customer> customers, List<Payments> payments) {
        logger.info("Combining customers and payments");

        customers.forEach(customer -> {
            List<Payments> matchingPayments = payments.stream()
                    .filter(payment -> customer.getWebshopId().equals(payment.getWebshopId()) && customer.getUserId().equals(payment.getUserId()))
                    .toList();

            if (customer.getPayments() == null) {
                customer.setPayments(new ArrayList<>());
            }

            customer.getPayments().addAll(matchingPayments);
        });
        return customers;
    }

    // WebshopService.java
    public List<Report1> generateReportSumCustomerPurchase(List<Customer> customers) {
        logger.info("Generating report");

        return customers.stream()
                .map(customer -> new Report1(
                        customer.getName(),
                        customer.getAddress(),
                        customer.getPayments().stream()
                                .mapToDouble(Payments::getAmount)
                                .sum()
                ))
                .collect(Collectors.toList());
    }

    // WebshopService.java
    public List<Report1> getTopCustomers(List<Report1> reports) {
        logger.info("Getting top customers");

        return reports.stream()
                .sorted(Comparator.comparingDouble(Report1::getTotalPurchase).reversed())
                .limit(2)
                .collect(Collectors.toList());
    }

    public List<WebshopReport> generateWebshopReport(List<Customer> customers) {
        logger.info("Generating webshop report");

        return customers.stream()
                .flatMap(customer -> customer.getPayments().stream())
                .collect(Collectors.groupingBy(Payments::getWebshopId,
                        Collectors.groupingBy(Payments::getPaymentMethod,
                                Collectors.summingDouble(Payments::getAmount))))
                .entrySet().stream()
                .map(entry -> new WebshopReport(
                        entry.getKey(),
                        entry.getValue().getOrDefault("card", 0.0),
                        entry.getValue().getOrDefault("transfer", 0.0)
                ))
                .collect(Collectors.toList());
    }

}
