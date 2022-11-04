package com.itshendson.invoicesystem.invoice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.service.InvoiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InvoiceServiceImpl invoiceServiceImplMock;

    private Invoice dummyInvoice;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllInvoice() throws Exception{
        mockMvc.perform(get("/api/v1/invoice")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void whenCreateValidInvoice_returnStatus201() throws Exception {
        dummyInvoice = new Invoice(null, "ABC Inc.", new BigDecimal(0), new ArrayList<>());
        String body = objectMapper.writeValueAsString(dummyInvoice);

        mockMvc.perform(post("/api/v1/invoice")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    void givenTwoLetterName_whenCreateInvoice_returnStatus400() throws Exception {
        dummyInvoice = new Invoice(null, "ab", new BigDecimal(0), new ArrayList<>());
        String body = objectMapper.writeValueAsString(dummyInvoice);

        mockMvc.perform(post("/api/v1/invoice")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenEmptyName_whenCreateInvoice_returnStatus400() throws Exception {
        dummyInvoice = new Invoice(null, "", new BigDecimal(0), new ArrayList<>());
        String body = objectMapper.writeValueAsString(dummyInvoice);

        mockMvc.perform(post("/api/v1/invoice")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenFindInvoiceByExistingId_returnStatus200() throws Exception {
        dummyInvoice = new Invoice(null, "", new BigDecimal(0), new ArrayList<>());
        dummyInvoice.setInvoiceId("1S");

        mockMvc.perform(get("/api/v1/invoice/{id}", "1S")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}