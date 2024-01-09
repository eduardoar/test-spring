package com.rosales.springtest.application.gateways;


import com.rosales.springtest.domain.entity.Price;
import com.rosales.springtest.domain.entity.PriceDTO;

public interface IPriceGateway {
    Price findPrice(PriceDTO priceDTO);

}
