package org.example.Webshop.controllers;

import org.example.Webshop.domain.Customer;
import org.example.Webshop.domain.Payments;
import org.example.Webshop.domain.Report1;
import org.example.Webshop.domain.WebshopReport;
import org.example.Webshop.services.CsvService;
import org.example.Webshop.services.WebshopService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Webshop {
    private final Path customerFile;
    private final Path paymentsFile;
    private CsvService csvService = new CsvService();
    private WebshopService webshopService = new WebshopService();

    private List<Customer> customers;

    public Webshop(Path customerFile, Path paymentsFile) {
        this.customerFile = customerFile;
        this.paymentsFile = paymentsFile;
    }

    public void run() {
        try {
            this.customers= webshopService.combineCustomerAndPayments(
                    csvService.readCsv(customerFile, Customer.class),
                    csvService.readCsv(paymentsFile, Payments.class)
            );
            for (Customer customer : customers) {
                System.out.println(customer);
            }
            List<Report1> report1 = webshopService.generateReportSumCustomerPurchase(customers);
            csvService.writeCsv(Paths.get("report01.csv"), report1);

            List<Report1> topCustomers = webshopService.getTopCustomers(report1);
            csvService.writeCsv(Paths.get("top.csv"), topCustomers);

            List<WebshopReport> webshopReport = webshopService.generateWebshopReport(customers);
            csvService.writeCsv(Paths.get("report02.csv"), webshopReport);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
