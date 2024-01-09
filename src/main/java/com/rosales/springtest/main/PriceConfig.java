package com.rosales.springtest.main;

import com.rosales.springtest.application.gateways.IPriceGateway;
import com.rosales.springtest.application.usecases.PriceInteractor;
import com.rosales.springtest.infrastructure.controller.mapper.PriceDTOMapper;
import com.rosales.springtest.infrastructure.gateways.mapper.PriceEntityMapper;
import com.rosales.springtest.infrastructure.gateways.PriceRepositoryGateway;
import com.rosales.springtest.infrastructure.persistence.IPriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceConfig {

    @Bean
    PriceInteractor priceInteractor(IPriceGateway priceGateway) {
        return new PriceInteractor(priceGateway);
    }

    @Bean
    IPriceGateway priceGateway(IPriceRepository priceRepository, PriceEntityMapper priceEntityMapper) {
        return new PriceRepositoryGateway(priceRepository, priceEntityMapper);
    }

    @Bean
    PriceEntityMapper priceEntityMapper() {
        return new PriceEntityMapper();
    }

    @Bean
    PriceDTOMapper priceDTOMapper() {
        return new PriceDTOMapper();
    }

}
