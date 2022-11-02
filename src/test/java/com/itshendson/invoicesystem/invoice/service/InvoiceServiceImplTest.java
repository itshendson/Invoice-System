package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.controller.InvoiceController;
import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepositoryMock;

    @InjectMocks
    private InvoiceServiceImpl invoiceServiceTest;

    private Invoice dummyInvoiceOne;
    private Invoice dummyInvoiceTwo;
    private Invoice dummyInvalidInvoiceOne;
    private ResponseEntity<Object> errorMessage;

    @BeforeEach
    void setUp() {
        dummyInvoiceOne = new Invoice(null, "Dummy Company One", new BigDecimal(0), new ArrayList<>());
        dummyInvoiceTwo = new Invoice(null, "Dummy Company Two", new BigDecimal(0), new ArrayList<>());
    }

    @DisplayName("Get all invoices")
    @Test
    void getAllInvoice() {
        List<Invoice> invoices = Arrays.asList(dummyInvoiceOne, dummyInvoiceTwo);

        when(invoiceRepositoryMock.findAll()).thenReturn(invoices);

        assertEquals(invoices, invoiceServiceTest.getAllInvoice());
    }

    @DisplayName("Create a valid invoice")
    @Test
    void createValidInvoice() {
        when(invoiceRepositoryMock.save(dummyInvoiceOne)).thenReturn(dummyInvoiceOne);

        assertEquals(dummyInvoiceOne, invoiceServiceTest.createInvoice(dummyInvoiceOne));
    }

    @DisplayName("Create an invalid invoice. Should still return an invoice. Validation check happens at controller.")
    @Test
    void createInvalidInvoiceWithShortCompanyName() {
        dummyInvalidInvoiceOne = new Invoice(null, "X", new BigDecimal(0), new ArrayList<>());

        when(invoiceRepositoryMock.save(dummyInvalidInvoiceOne)).thenReturn(dummyInvalidInvoiceOne);

        assertEquals(dummyInvalidInvoiceOne, invoiceServiceTest.createInvoice(dummyInvalidInvoiceOne));
    }
}