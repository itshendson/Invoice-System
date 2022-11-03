package com.itshendson.invoicesystem.invoice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itshendson.invoicesystem.invoice.model.Invoice;
import com.itshendson.invoicesystem.invoice.service.InvoiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.xmlunit.builder.Input;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InvoiceServiceImpl invoiceServiceImplMock;

    private Invoice dummyInvoiceOne;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllInvoice() {
    }

    @Test
    void whenCreateValidInvoice_returnStatus201() throws Exception {
        dummyInvoiceOne = new Invoice(null, "ABC Inc.", new BigDecimal(0), new ArrayList<>());
        String body = objectMapper.writeValueAsString(dummyInvoiceOne);

        mockMvc.perform(post("/api/v1/invoice")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    void whenCreateInvoice_withTwoLetterName_returnStatus400() throws Exception {
        dummyInvoiceOne = new Invoice(null, "ab", new BigDecimal(0), new ArrayList<>());
        String body = objectMapper.writeValueAsString(dummyInvoiceOne);

        mockMvc.perform(post("/api/v1/invoice")
                        .contentType("application/json")
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCreateInvoice_withEmptyName_returnStatus400() throws Exception {
        dummyInvoiceOne = new Invoice(null, "", new BigDecimal(0), new ArrayList<>());
        String body = objectMapper.writeValueAsString(dummyInvoiceOne);

        mockMvc.perform(post("/api/v1/invoice")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isBadRequest());
    }
}