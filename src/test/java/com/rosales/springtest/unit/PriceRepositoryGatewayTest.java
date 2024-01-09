package com.rosales.springtest.unit;

import com.rosales.springtest.domain.entity.Price;
import com.rosales.springtest.domain.entity.PriceDTO;
import com.rosales.springtest.exception.ModelNotFoundException;
import com.rosales.springtest.infrastructure.controller.mapper.PriceDTOMapper;
import com.rosales.springtest.infrastructure.controller.request.PriceRequest;
import com.rosales.springtest.infrastructure.controller.response.PriceResponse;
import com.rosales.springtest.infrastructure.gateways.mapper.PriceEntityMapper;
import com.rosales.springtest.infrastructure.gateways.PriceRepositoryGateway;
import com.rosales.springtest.infrastructure.persistence.IPriceRepository;
import com.rosales.springtest.infrastructure.persistence.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryGatewayTest {

    @Mock
    private IPriceRepository priceRepository;
    @InjectMocks
    private PriceRepositoryGateway priceRepositoryGateway;
    @InjectMocks
    private PriceDTOMapper priceDTOMapper;
    @Mock
    private PriceEntityMapper priceEntityMapper;
    private PriceEntity priceEntity;
    private Price price;
    private static final LocalDateTime VALID_APPLICATION_DATE = LocalDateTime.parse("2020-06-14T10:00:00");
    private static final Long VALID_PRODUCT_ID = 35455L;
    private static final Long INVALID_PRODUCT_ID = 35456L;
    private static final Long VALID_BRAND_ID = 1L;

    @BeforeEach
    void setup() {
        priceEntity = new PriceEntity();
        priceEntity.setIdPrice(1L);
        priceEntity.setIdBrand(1L);
        priceEntity.setStartDate(LocalDateTime.parse("2020-06-14T10:00:00"));
        priceEntity.setEndDate(LocalDateTime.parse("2020-06-14T18:00:00"));
        priceEntity.setIdPriceRate(1L);
        priceEntity.setIdProduct(35455L);
        priceEntity.setPriority(0);
        priceEntity.setProductPrice(BigDecimal.valueOf(38.95));
        priceEntity.setCurrency("EUR");

        price = new Price();
        price.setIdPrice(1L);
        price.setIdBrand(1L);
        price.setStartDate(LocalDateTime.parse("2020-06-14T10:00:00"));
        price.setEndDate(LocalDateTime.parse("2020-06-14T18:00:00"));
        price.setIdPriceRate(1L);
        price.setIdProduct(35455L);
        price.setPriority(0);
        price.setProductPrice(BigDecimal.valueOf(38.95));
        price.setCurrency("EUR");
    }

    @Test
    void should_FindPrice_ForValidInput() {
        //given
        PriceRequest validPriceRequest = createValidPriceRequestDto();
        mockPriceRepositoryToReturnPrice();
        PriceDTO priceDTO = priceDTOMapper.toPriceDto(validPriceRequest);
        given(priceEntityMapper.toDomainObj(any(PriceEntity.class))).willReturn(price);

        //when
        Price price = priceRepositoryGateway.findPrice(priceDTO);
        PriceResponse priceResponse = priceDTOMapper.toResponse(price);

        //then
        assertThat(priceResponse).isNotNull();
    }

    @Test
    void should_ThrowException_ForInvalidInput() {
        //given
        PriceRequest invalidPriceRequest = createInvalidPriceRequestDto();
        mockPriceRepositoryToReturnEmptyOptional();
        PriceDTO priceDTO = priceDTOMapper.toPriceDto(invalidPriceRequest);

        //then
        assertThrows(ModelNotFoundException.class, () -> {
            priceRepositoryGateway.findPrice(priceDTO);
        });
    }

    private PriceRequest createValidPriceRequestDto() {
        PriceRequest validPriceRequest = new PriceRequest();
        validPriceRequest.setApplicationDate(VALID_APPLICATION_DATE);
        validPriceRequest.setIdProduct(VALID_PRODUCT_ID);
        validPriceRequest.setIdBrand(VALID_BRAND_ID);
        return validPriceRequest;
    }

    private PriceRequest createInvalidPriceRequestDto() {
        PriceRequest invalidPriceRequest = new PriceRequest();
        invalidPriceRequest.setApplicationDate(VALID_APPLICATION_DATE);
        invalidPriceRequest.setIdProduct(INVALID_PRODUCT_ID);
        invalidPriceRequest.setIdBrand(VALID_BRAND_ID);
        return invalidPriceRequest;
    }

    private void mockPriceRepositoryToReturnPrice() {
        given(priceRepository.findFirstByProductAndBrandAndApplicationDate(
                VALID_PRODUCT_ID,
                VALID_BRAND_ID,
                VALID_APPLICATION_DATE))
                .willReturn(Optional.ofNullable(priceEntity));
    }

    private void mockPriceRepositoryToReturnEmptyOptional() {
        given(priceRepository.findFirstByProductAndBrandAndApplicationDate(
                INVALID_PRODUCT_ID,
                VALID_BRAND_ID,
                VALID_APPLICATION_DATE))
                .willReturn(Optional.empty());
    }

}
