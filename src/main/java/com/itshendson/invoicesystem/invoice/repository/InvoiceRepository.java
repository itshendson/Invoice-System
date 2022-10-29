package com.itshendson.invoicesystem.invoice.repository;

import com.itshendson.invoicesystem.invoice.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
