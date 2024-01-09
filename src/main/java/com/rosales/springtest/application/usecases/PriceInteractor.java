package com.rosales.springtest.application.usecases;

import com.rosales.springtest.application.gateways.IPriceGateway;
import com.rosales.springtest.domain.entity.Price;
import com.rosales.springtest.domain.entity.PriceDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceInteractor {

    private final IPriceGateway priceGateway;
    public Price findPrice(PriceDTO priceDTO) {
        return priceGateway.findPrice(priceDTO);
    }

}
