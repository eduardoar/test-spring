package com.rosales.springtest.integration;

import com.rosales.springtest.exception.ModelNotFoundException;
import com.rosales.springtest.infrastructure.persistence.IPriceRepository;
import com.rosales.springtest.infrastructure.persistence.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.function.Executable;

@DataJpaTest
class PriceRepositoryGatewayTest {

    private final IPriceRepository priceRepository;

    private static final Long VALID_PRODUCT_ID = 35455L;
    private static final Long INVALID_PRODUCT_ID = 35456L;
    private static final Long VALID_BRAND_ID = 1L;
    @Autowired
    public PriceRepositoryGatewayTest(IPriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Test
    void should_FindPrice_ForValidInput() {

        //given
        String valueDate = "2020-06-14 10:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime applicationDate = LocalDateTime.parse(valueDate, formatter);
        Long idProduct = VALID_PRODUCT_ID;
        Long idBrand = VALID_BRAND_ID;

        //when
        PriceEntity priceRequest = priceRepository.findFirstByProductAndBrandAndApplicationDate(
                idProduct, idBrand, applicationDate )
                .orElseThrow(() -> new ModelNotFoundException("No records found"));

        //then
        assertThat(priceRequest).isNotNull();

    }

    @Test
    void should_ThrowException_ForInvalidInput() {

        //given
        String valueDate = "2020-06-14 10:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime applicationDate = LocalDateTime.parse(valueDate, formatter);
        Long idProduct = INVALID_PRODUCT_ID;
        Long idBrand = VALID_BRAND_ID;

        //when
        Executable executable = () -> priceRepository.findFirstByProductAndBrandAndApplicationDate(
                        idProduct, idBrand, applicationDate )
                .orElseThrow(() -> new ModelNotFoundException("No records found"));

        //then
        assertThrows(ModelNotFoundException.class, executable);

    }


}
