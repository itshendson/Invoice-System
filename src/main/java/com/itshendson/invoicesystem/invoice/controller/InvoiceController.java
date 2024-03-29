package com.itshendson.invoicesystem.invoice.controller;

import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        Invoice returnedInvoice = invoiceServiceImpl.createInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedInvoice);
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<Invoice> findInvoiceById(@PathVariable("id") String invoiceId) {
        Invoice returnedInvoice = invoiceServiceImpl.findInvoiceById(invoiceId);
        return ResponseEntity.status(HttpStatus.OK).body(returnedInvoice);
    }

    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<String> deleteInvoiceById(@PathVariable("id") String invoiceId) {
        invoiceServiceImpl.deleteInvoiceById(invoiceId);
        return ResponseEntity.status(HttpStatus.OK).body("Invoice deleted.");
    }

    @PutMapping("/invoice/{id}")
    public ResponseEntity<Invoice> UpdateInvoice(@PathVariable("id") String invoiceId, @RequestBody Invoice invoice) {
        Invoice returnedInvoice = invoiceServiceImpl.updateInvoice(invoiceId, invoice);
        return ResponseEntity.status(HttpStatus.OK).body(returnedInvoice);
    }
}
