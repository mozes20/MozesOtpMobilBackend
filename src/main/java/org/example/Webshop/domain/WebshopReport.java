package org.example.Webshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WebshopReport {
    private String webshop;
    private double cardPurchase;
    private double transferPurchase;
}
