package com.itshendson.invoicesystem.invoice.controller;

import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @GetMapping("/invoice")
    public ResponseEntity<List<Invoice>> getAllInvoice() {
        return ResponseEntity.status(200).body(invoiceServiceImpl.getAllInvoice());
    }

    @PostMapping("/invoice")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.status(201).body(invoiceServiceImpl.createInvoice(invoice));
    }

}
