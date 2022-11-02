package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @BeforeEach
    void setUp() {
        dummyInvoiceOne = new Invoice(null, "Dummy Company One", new BigDecimal(0), new ArrayList<>());
        dummyInvoiceTwo = new Invoice(null, "Dummy Company Two", new BigDecimal(0), new ArrayList<>());
    }

    @DisplayName("Get all invoices.")
    @Test
    void getAllInvoice() {
        List<Invoice> invoices = Arrays.asList(dummyInvoiceOne, dummyInvoiceTwo);

        when(invoiceRepositoryMock.findAll()).thenReturn(invoices);

        assertEquals(invoices, invoiceServiceTest.getAllInvoice());
    }

    @Test
    void createInvoice() {
    }
}