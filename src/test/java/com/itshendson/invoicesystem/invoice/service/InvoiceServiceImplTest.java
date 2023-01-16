package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.exception.InvoiceNotFoundException;
import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepositoryMock;

    @InjectMocks
    private InvoiceServiceImpl invoiceServiceTest;

    private Invoice invoice1;
    private Invoice invoice2;
    private List<Invoice> invoices;

    @BeforeEach
    void setUp() {
        invoice1 = new Invoice(null, "company1", new BigDecimal(0), new ArrayList<>());
        invoice2 = new Invoice(null, "company2", new BigDecimal(0), new ArrayList<>());
        invoices = Arrays.asList(invoice1, invoice2);
    }

    @Test
    void testGetAllInvoice() {
        when(invoiceRepositoryMock.findAll()).thenReturn(invoices);
        List<Invoice> actual = invoiceServiceTest.getAllInvoice();
        assertEquals(invoices, actual);
    }

    @Test
    void testGetAllInvoice_ReturnEmptyList() {
        when(invoiceRepositoryMock.findAll()).thenReturn(Collections.emptyList());
        List<Invoice> actual = invoiceServiceTest.getAllInvoice();
        assertTrue(actual.isEmpty());
    }

    @Test
    void testCreateInvoice() {
        when(invoiceRepositoryMock.save(invoice1)).thenReturn(invoice1);
        Invoice actual = invoiceServiceTest.createInvoice(invoice1);
        assertEquals(invoice1, actual);
    }

    @Test
    void testFindInvoiceById_ReturnInvoice() {
        when(invoiceRepositoryMock.findById("12345")).thenReturn(Optional.ofNullable(invoice1));
        Invoice actual = invoiceServiceTest.findInvoiceById("12345");
        assertEquals(invoice1, actual);
    }

    @Test
    void testFindInvoiceById_ReturnNotFoundException() {
        when(invoiceRepositoryMock.findById("12345")).thenThrow(new InvoiceNotFoundException());
        assertThrows(InvoiceNotFoundException.class, () -> invoiceServiceTest.findInvoiceById("12345"));
    }

    @Test
    void testDeleteInvoiceById() {
        when(invoiceRepositoryMock.findById("12345")).thenReturn(Optional.ofNullable(invoice1));
        invoiceServiceTest.deleteInvoiceById("12345");
        verify(invoiceRepositoryMock, times(1)).delete(invoice1);
    }

    @Test
    void testDeleteInvoiceById_ReturnNotFoundException() {
        when(invoiceRepositoryMock.findById("12345")).thenThrow(new InvoiceNotFoundException());
        assertThrows(InvoiceNotFoundException.class, () -> invoiceServiceTest.deleteInvoiceById("12345"));
        verify(invoiceRepositoryMock, times(0)).delete(invoice1);
    }

    @Test
    void testUpdateInvoice_ReturnNewInvoice() {
        when(invoiceRepositoryMock.findById("12345")).thenReturn(Optional.ofNullable(invoice1));
        when(invoiceRepositoryMock.save(Mockito.any())).thenReturn(invoice2);

        // then
        Invoice newInvoice = invoiceServiceTest.updateInvoice("12345", invoice2);
        assertEquals(invoice2.getCompanyName(), newInvoice.getCompanyName());
        verify(invoiceRepositoryMock, times(1)).save(Mockito.any());
    }

    @Test
    void testUpdateInvoice_ReturnNotFoundException() {
        when(invoiceRepositoryMock.findById("11111")).thenThrow(InvoiceNotFoundException.class);
        assertThrows(InvoiceNotFoundException.class, () -> invoiceServiceTest.updateInvoice("11111", invoice2));
        verify(invoiceRepositoryMock, times(0)).save(invoice2);
    }
}