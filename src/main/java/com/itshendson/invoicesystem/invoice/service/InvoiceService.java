package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice createInvoice(Invoice invoice);

    List<Invoice> getAllInvoice();

    Invoice findInvoiceById(String invoiceId);

    void deleteInvoiceById(String invoiceId);
}
