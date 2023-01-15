package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.exception.InvoiceNotFoundException;
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
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice findInvoiceById(String invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException("Invoice " + invoiceId + " not found."));
    }

    @Override
    public void deleteInvoiceById(String invoiceId) {
        invoiceRepository.delete(invoiceRepository.findById(invoiceId).orElseThrow(InvoiceNotFoundException::new));
    }

    @Override
    public Invoice updateInvoice(String invoiceId, Invoice newInvoice) {
        Invoice databaseInvoice = invoiceRepository.findById(invoiceId).orElseThrow(InvoiceNotFoundException::new);

        if (newInvoice.getDate() != null) {
            databaseInvoice.setDate(newInvoice.getDate());
        }

        if (newInvoice.getCompanyName() != null) {
            databaseInvoice.setCompanyName(newInvoice.getCompanyName());
        }

        if (newInvoice.getInvoiceAmount() != null) {
            databaseInvoice.setInvoiceAmount(newInvoice.getInvoiceAmount());
        }

        return invoiceRepository.save(databaseInvoice);
    }
}
