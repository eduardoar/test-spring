package com.rosales.springtest.integration;

import com.rosales.springtest.infrastructure.controller.request.PriceRequest;
import com.rosales.springtest.infrastructure.controller.response.PriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {
    private final MockMvc mockMvc;
    private static final Long VALID_ID_PRODUCT = 35455L;
    private static final Long VALID_ID_BRAND = 1L;
    private static final String PATH = "/api/v1/prices/applicationDate/{applicationDate}/product/{idProduct}/brand/{idBrand}";

    @Autowired
    public PriceControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testValidPriceRequest() throws Exception {
        testValidPriceRequest(this.createRequest(LocalDateTime.parse("2020-06-14T10:00:00"), VALID_ID_PRODUCT, VALID_ID_BRAND), this.createResponse(1L, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(35.50)));
        testValidPriceRequest(this.createRequest(LocalDateTime.parse("2020-06-14T16:00:00"), VALID_ID_PRODUCT, VALID_ID_BRAND), this.createResponse(2L, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), BigDecimal.valueOf(25.45)));
        testValidPriceRequest(this.createRequest(LocalDateTime.parse("2020-06-14T21:00:00"), VALID_ID_PRODUCT, VALID_ID_BRAND), this.createResponse(1L, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(35.50)));
        testValidPriceRequest(this.createRequest(LocalDateTime.parse("2020-06-15T10:00:00"), VALID_ID_PRODUCT, VALID_ID_BRAND), this.createResponse(3L, LocalDateTime.parse("2020-06-15T00:00:00"), LocalDateTime.parse("2020-06-15T11:00:00"), BigDecimal.valueOf(30.50)));
        testValidPriceRequest(this.createRequest(LocalDateTime.parse("2020-06-16T21:00:00"), VALID_ID_PRODUCT, VALID_ID_BRAND), this.createResponse(4L, LocalDateTime.parse("2020-06-15T16:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), BigDecimal.valueOf(38.95)));

    }

    private void testValidPriceRequest(PriceRequest priceRequest, PriceResponse priceResponse) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get(PATH, priceRequest.getApplicationDate(), priceRequest.getIdProduct(), priceRequest.getIdBrand()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idProduct").value(priceResponse.getIdProduct()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idBrand").value(priceResponse.getIdBrand()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.idPriceRate").value(priceResponse.getIdPriceRate()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value(formatDateTime(priceResponse.getStartDate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value(formatDateTime(priceResponse.getEndDate())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(priceResponse.getPrice()));
    }

    private PriceRequest createRequest(LocalDateTime applicationDate, Long idProduct, Long idBrand){
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setApplicationDate(applicationDate);
        priceRequest.setIdProduct(idProduct);
        priceRequest.setIdBrand(idBrand);

        return priceRequest;
    }

    private PriceResponse createResponse(Long priceRate, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setIdProduct(VALID_ID_PRODUCT);
        priceResponse.setIdBrand(VALID_ID_BRAND);
        priceResponse.setIdPriceRate(priceRate);
        priceResponse.setStartDate(startDate);
        priceResponse.setEndDate(endDate);
        priceResponse.setPrice(price);

        return priceResponse;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return dateTime.format(formatter);
    }
}
