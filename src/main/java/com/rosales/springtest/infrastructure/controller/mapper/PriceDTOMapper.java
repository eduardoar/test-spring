package com.rosales.springtest.infrastructure.controller.mapper;

import com.rosales.springtest.domain.entity.Price;
import com.rosales.springtest.domain.entity.PriceDTO;
import com.rosales.springtest.infrastructure.controller.request.PriceRequest;
import com.rosales.springtest.infrastructure.controller.response.PriceResponse;

public class PriceDTOMapper {

    public PriceResponse toResponse(Price price) {
        return new PriceResponse(price.getIdProduct(),
                price.getIdBrand(),
                price.getIdPriceRate(),
                price.getStartDate(),
                price.getEndDate(),
                price.getProductPrice());
    }

    public PriceDTO toPriceDto(PriceRequest priceRequest) {
        return new PriceDTO(priceRequest.getApplicationDate(),
                priceRequest.getIdProduct(),
                priceRequest.getIdBrand());
    }

}
