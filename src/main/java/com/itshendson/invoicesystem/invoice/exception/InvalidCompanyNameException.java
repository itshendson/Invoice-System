package com.itshendson.invoicesystem.invoice.exception;

public class InvalidCompanyNameException extends RuntimeException{

    public InvalidCompanyNameException(String companyName) {
        super("Company name " + companyName + " be at least 3 characters long!!!");
    }
}
