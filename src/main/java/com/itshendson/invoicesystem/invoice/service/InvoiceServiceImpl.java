package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.insert(invoice);
    }
}
