package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.exception.InvoiceNotFoundException;
import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        if (!invoiceOptional.isPresent()) throw new InvoiceNotFoundException();
        return invoiceOptional.get();
    }

    @Override
    public void deleteInvoiceById(String invoiceId) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        if (!invoiceOptional.isPresent()) throw new InvoiceNotFoundException();
        invoiceRepository.deleteById(invoiceId);
    }

    @Override
    public Invoice updateInvoice(String invoiceId, Invoice newInvoice) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoiceId);
        if (!invoiceOptional.isPresent()) throw new InvoiceNotFoundException();

        Invoice existingInvoice = invoiceOptional.get();

        if (Objects.nonNull(newInvoice.getDate())) {
            existingInvoice.setDate(newInvoice.getDate());
        }

        if (Objects.nonNull(newInvoice.getCompanyName())) {
            existingInvoice.setCompanyName(newInvoice.getCompanyName());
        }

        if (Objects.nonNull(newInvoice.getInvoiceAmount())) {
            existingInvoice.setInvoiceAmount(newInvoice.getInvoiceAmount());
        }

        return invoiceRepository.save(existingInvoice);
    }
}
