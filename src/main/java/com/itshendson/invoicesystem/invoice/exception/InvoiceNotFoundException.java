package com.itshendson.invoicesystem.invoice.exception;

public class InvoiceNotFoundException extends RuntimeException{

    public InvoiceNotFoundException() {
        super("Invoice not found.");
    }

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
