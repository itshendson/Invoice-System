package com.itshendson.invoicesystem.invoice.service;

import com.itshendson.invoicesystem.invoice.exception.InvoiceNotFoundException;
import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepositoryMock;

    @InjectMocks
    private InvoiceServiceImpl invoiceServiceTest;

    private Invoice dummyInvoiceA;
    private Invoice dummyInvoiceB;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllInvoice() {
        dummyInvoiceA = new Invoice(null, "Dummy Company", new BigDecimal(0), new ArrayList<>());
        dummyInvoiceB = new Invoice(null, "Dummy Company", new BigDecimal(0), new ArrayList<>());
        List<Invoice> invoices = Arrays.asList(dummyInvoiceA, dummyInvoiceB);

        when(invoiceRepositoryMock.findAll()).thenReturn(invoices);

        assertEquals(invoices, invoiceServiceTest.getAllInvoice());
    }

    @Test
    void whenCreateValidInvoice_ReturnInvoice() {
        dummyInvoiceA = new Invoice(null, "Dummy Company", new BigDecimal(0), new ArrayList<>());
        when(invoiceRepositoryMock.save(dummyInvoiceA)).thenReturn(dummyInvoiceA);

        assertEquals(dummyInvoiceA, invoiceServiceTest.createInvoice(dummyInvoiceA));
    }

    @Test
    void whenFindExistingInvoiceById_ReturnInvoice() {
        dummyInvoiceA = new Invoice(null, "Dummy Company", new BigDecimal(0), new ArrayList<>());
        dummyInvoiceA.setInvoiceId("1S");

        when(invoiceRepositoryMock.findById("1S")).thenReturn(Optional.ofNullable(dummyInvoiceA));

        assertEquals(dummyInvoiceA, invoiceServiceTest.findInvoiceById("1S"));
    }

    @Test
    void whenFindNonExistentInvoiceById_ReturnException() {
        doThrow(new InvoiceNotFoundException()).when(invoiceRepositoryMock).findById("99S");

        assertThrows(InvoiceNotFoundException.class, () -> invoiceServiceTest.findInvoiceById("99S"));
    }

    @Test
    void whenDeleteExistingInvoiceById_ReturnInvoice() {
        dummyInvoiceA = new Invoice(null, "Dummy Company", new BigDecimal(0), new ArrayList<>());
        dummyInvoiceA.setInvoiceId("1S");

        when(invoiceRepositoryMock.save(dummyInvoiceA)).thenReturn(dummyInvoiceA);

        assertEquals(dummyInvoiceA, invoiceServiceTest.createInvoice(dummyInvoiceA));

        doThrow(new InvoiceNotFoundException()).when(invoiceRepositoryMock).findById("1S");

        assertThrows(InvoiceNotFoundException.class, () -> invoiceServiceTest.deleteInvoiceById("1S"));
    }

    @Test
    void whenDeleteNonExistentInvoiceById_ReturnException() {
        doThrow(new InvoiceNotFoundException()).when(invoiceRepositoryMock).findById("1S");

        assertThrows(InvoiceNotFoundException.class, () -> invoiceServiceTest.deleteInvoiceById("1S"));
    }

    @Test
    void whenUpdateNonExistentInvoice_ReturnException() {
        // when
        doThrow(new InvoiceNotFoundException()).when(invoiceRepositoryMock).findById("1S");
        // then
        assertThrows(InvoiceNotFoundException.class, () -> invoiceServiceTest.findInvoiceById("1S"));
    }

    @DisplayName("Update student's first name")
    @Test
    void updateStudentFirstNameOnly() {
        // given
        dummyInvoiceA = new Invoice(null, "Old name", new BigDecimal(0), new ArrayList<>());
        dummyInvoiceB = new Invoice(null, "New name", new BigDecimal(0), new ArrayList<>());

        // when
        when(invoiceRepositoryMock.findById("1S")).thenReturn(Optional.ofNullable(dummyInvoiceA));
        when(invoiceRepositoryMock.save(Mockito.any())).thenReturn(dummyInvoiceB);

        // then
        Invoice updatedInvoice = invoiceServiceTest.updateInvoice("1S", dummyInvoiceB);
        assertEquals(dummyInvoiceB.getCompanyName(), updatedInvoice.getCompanyName());
        verify(invoiceRepositoryMock, times(1)).save(Mockito.any());
    }
}