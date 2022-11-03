package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.exception.InvalidCompanyNameException;
import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        if (invoice.getCompanyName() == null || invoice.getCompanyName().length() < 3) throw new InvalidCompanyNameException(invoice.getCompanyName());
        return invoiceRepository.save(invoice);
    }
}
