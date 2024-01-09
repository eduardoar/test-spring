package com.rosales.springtest.unit;

import com.rosales.springtest.infrastructure.controller.request.PriceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {
    private final MockMvc mockMvc;
    private static final String PATH = "/api/v1/prices/applicationDate/{applicationDate}/product/{idProduct}/brand/{idBrand}";

    @Autowired
    public PriceControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testInvalidPriceRequest() throws Exception {
        // Test invalid idProduct
        testInvalidPriceRequest(this.createRequest(LocalDateTime.parse("2020-06-14T10:00:00"), 0L, 1L));
        // Test invalid idBrand
        testInvalidPriceRequest(this.createRequest(LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, 0L));
    }

    private void testInvalidPriceRequest(PriceRequest priceRequest) throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get(PATH, priceRequest.getApplicationDate(), priceRequest.getIdProduct(), priceRequest.getIdBrand()))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));

    }

    private PriceRequest createRequest(LocalDateTime applicationDate, Long idProduct, Long idBrand){
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setApplicationDate(applicationDate);
        priceRequest.setIdProduct(idProduct);
        priceRequest.setIdBrand(idBrand);

        return priceRequest;
    }

}
