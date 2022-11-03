package com.itshendson.invoicesystem.invoice.exception;

public class InvalidCompanyNameException extends RuntimeException{

    public InvalidCompanyNameException() {
        super("Company name must be minimum 3 letters long.");
    }
}
