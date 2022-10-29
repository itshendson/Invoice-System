package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice createInvoice(Invoice invoice);

    List<Invoice> getAllInvoice();
}
