package com.rosales.springtest.infrastructure.gateways.mapper;

import com.rosales.springtest.domain.entity.Price;
import com.rosales.springtest.infrastructure.persistence.PriceEntity;
import org.springframework.beans.BeanUtils;

public class PriceEntityMapper {

    public PriceEntity toEntity(Price price) {
        PriceEntity priceEntity = new PriceEntity();
        BeanUtils.copyProperties(price, priceEntity);
        return priceEntity;
    }

    public Price toDomainObj(PriceEntity priceEntity) {
        Price price = new Price();
        BeanUtils.copyProperties(priceEntity, price);
        return price;
    }

}
