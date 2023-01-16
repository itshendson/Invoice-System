package com.itshendson.invoicesystem.invoice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itshendson.invoicesystem.invoice.exception.InvoiceNotFoundException;
import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.service.InvoiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceServiceImpl invoiceServiceImplMock;

    private Invoice invoice1;
    private Invoice invoice2;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        invoice1 = new Invoice(null, "company1", new BigDecimal(0), new ArrayList<>());
        invoice2 = new Invoice(null,  null, new BigDecimal(0), new ArrayList<>());
    }

    @Test
    void testGetAllInvoice() throws Exception{
        mockMvc.perform(get("/api/v1/invoice")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateInvoiceSuccess() throws Exception {
        mockMvc.perform(post("/api/v1/invoice")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(invoice1)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateInvoiceBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/invoice")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(invoice2)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFindInvoiceByIdSuccess() throws Exception {
        mockMvc.perform(get("/api/v1/invoice/{id}", "111111")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindInvoiceByIdNotFound() throws Exception {
        when(invoiceServiceImplMock.findInvoiceById("111111")).thenThrow(new InvoiceNotFoundException());

        mockMvc.perform(get("/api/v1/invoice/{id}", "111111")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteInvoiceSuccess() throws Exception {
        mockMvc.perform(delete("/api/v1/invoice/{id}", "111111")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateInvoiceSuccess() throws Exception {
        mockMvc.perform(put("/api/v1/invoice/{id}", "1S")
                .contentType("application/json")
                .content(mapper.writeValueAsString(invoice1)))
                .andExpect(status().isOk());
    }
}