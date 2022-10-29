package com.itshendson.invoicesystem.invoice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Document
public class Invoice {

    @Id
    private String invoiceId;
    private Date date;
    @NotBlank(message = "Customer name cannot be empty.")
    private String customerName;
    private BigDecimal invoiceAmount;
    private ArrayList<ItemRecord> services;

    public Invoice(Date date, String customerName, BigDecimal invoiceAmount, ArrayList<ItemRecord> services) {
        this.date = new Date();
        this.customerName = customerName;
        this.invoiceAmount = new BigDecimal("0");
        this.services = new ArrayList<>();
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public ArrayList<ItemRecord> getServices() {
        return services;
    }

    public void setServices(ArrayList<ItemRecord> services) {
        this.services = services;
    }
}
