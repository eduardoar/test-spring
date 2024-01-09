package com.rosales.springtest.unit;

import com.rosales.springtest.domain.entity.Price;
import com.rosales.springtest.infrastructure.gateways.mapper.PriceEntityMapper;
import com.rosales.springtest.infrastructure.persistence.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceEntityMapperTest {

    @InjectMocks
    private PriceEntityMapper priceEntityMapper;

    private static final Long ID_PRICE = 1L;
    private static final Long ID_BRAND = 1L;
    private static final LocalDateTime START_DATE = LocalDateTime.now();
    private static final LocalDateTime END_DATE = LocalDateTime.now().plusDays(1);
    private static final Long ID_PRICE_RATE = 1L;
    private static final Long ID_PRODUCT = 1L;
    private static final Integer PRIORITY = 1;
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal("50.00");
    private static final String CURRENCY = "EUR";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenPrice_whenMappingToEntity_thenMappedCorrectly() {

        Price price = createSamplePrice();

        PriceEntity priceEntity = priceEntityMapper.toEntity(price);

        assertEquals(price.getIdProduct(), priceEntity.getIdProduct());
        assertEquals(price.getIdBrand(), priceEntity.getIdBrand());
        assertEquals(price.getStartDate(), priceEntity.getStartDate());
        assertEquals(price.getEndDate(), priceEntity.getEndDate());
        assertEquals(price.getIdPriceRate(), priceEntity.getIdPriceRate());
        assertEquals(price.getIdProduct(), priceEntity.getIdProduct());
        assertEquals(price.getPriority(), priceEntity.getPriority());
        assertEquals(price.getProductPrice(), priceEntity.getProductPrice());
        assertEquals(price.getCurrency(), priceEntity.getCurrency());

    }

    @Test
    void givenPriceEntity_whenMappingToDomainObj_thenMappedCorrectly() {

        PriceEntity priceEntity = createSamplePriceEntity();

        Price price = priceEntityMapper.toDomainObj(priceEntity);

        assertEquals(priceEntity.getIdProduct(), price.getIdProduct());
        assertEquals(priceEntity.getIdBrand(), price.getIdBrand());
        assertEquals(priceEntity.getStartDate(), price.getStartDate());
        assertEquals(priceEntity.getEndDate(), price.getEndDate());
        assertEquals(priceEntity.getIdPriceRate(), price.getIdPriceRate());
        assertEquals(priceEntity.getIdProduct(), price.getIdProduct());
        assertEquals(priceEntity.getPriority(), price.getPriority());
        assertEquals(priceEntity.getProductPrice(), price.getProductPrice());
        assertEquals(priceEntity.getCurrency(), price.getCurrency());
    }

    private Price createSamplePrice() {
        return new Price(ID_PRICE,
                ID_BRAND,
                START_DATE,
                END_DATE,
                ID_PRICE_RATE,
                ID_PRODUCT,
                PRIORITY,
                PRODUCT_PRICE,
                CURRENCY);
    }

    private PriceEntity createSamplePriceEntity() {
        return new PriceEntity(ID_PRICE,
                ID_BRAND,
                START_DATE,
                END_DATE,
                ID_PRICE_RATE,
                ID_PRODUCT,
                PRIORITY,
                PRODUCT_PRICE,
                CURRENCY);
    }

}
