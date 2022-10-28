package com.itshendson.invoicesystem.invoice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Document
public class Invoice {

    @Id
    private long invoiceId;
    private Date date;
    private String customerName;
    private BigDecimal invoiceAmount;
    private ArrayList<ItemRecord> services;

    public Invoice(long invoiceId, Date date, String customerName, BigDecimal invoiceAmount, ArrayList<ItemRecord> services) {
        this.invoiceId = invoiceId;
        this.date = new Date();
        this.customerName = customerName;
        this.invoiceAmount = new BigDecimal("0");
        this.services = new ArrayList<>();
    }
}
