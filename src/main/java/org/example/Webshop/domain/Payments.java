package org.example.Webshop.domain;

import com.opencsv.bean.CsvBindAndJoinByPosition;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Payments {
    @CsvBindByPosition(position = 0)
    private String webshopId;

    @CsvBindByPosition(position = 1)
    private String userId;

    @CsvBindByPosition(position = 2)
    private String paymentMethod;

    @CsvBindByPosition(position = 3)
    private Double amount;

    @CsvBindByPosition(position = 4)
    private String bankAccountNumber;

    @CsvBindByPosition(position = 5)
    private String cardNumber;

    @CsvDate(value = "yyyy.MM.dd")
    @CsvBindByPosition(position = 6)
    private Date paymentDate;
}
