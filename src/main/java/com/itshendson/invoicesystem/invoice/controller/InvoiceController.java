package com.itshendson.invoicesystem.invoice.controller;

import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @PostMapping("/invoice")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceServiceImpl.createInvoice(invoice);
    }
}
