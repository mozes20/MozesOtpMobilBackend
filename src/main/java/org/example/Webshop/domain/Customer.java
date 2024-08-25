package org.example.Webshop.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
public class Customer {
    @CsvBindByPosition(position = 0)
    private String webshopId;

    @CsvBindByPosition(position = 1)
    private String userId;

    @CsvBindByPosition(position = 2)
    private String name;

    @CsvBindByPosition(position = 3)
    private String address;

    private ArrayList<Payments> payments;
}
