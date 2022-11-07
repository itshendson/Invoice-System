package com.itshendson.invoicesystem.invoice.controller;

import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @GetMapping("/invoice")
    public ResponseEntity<List<Invoice>> getAllInvoice() {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceServiceImpl.getAllInvoice());
    }

    @PostMapping("/invoice")
    public ResponseEntity<Invoice> createInvoice(@RequestBody @Valid Invoice invoice) {
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceServiceImpl.createInvoice(invoice));
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<Invoice> findInvoiceById(@PathVariable("id") String invoiceId) {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceServiceImpl.findInvoiceById(invoiceId));
    }

    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<String> deleteInvoiceById(@PathVariable("id") String invoiceId) {
        invoiceServiceImpl.deleteInvoiceById(invoiceId);
        return ResponseEntity.status(HttpStatus.OK).body("Invoice deleted.");
    }

}
