package com.rosales.springtest.infrastructure.gateways;

import com.rosales.springtest.application.gateways.IPriceGateway;
import com.rosales.springtest.domain.entity.Price;
import com.rosales.springtest.domain.entity.PriceDTO;
import com.rosales.springtest.exception.ModelNotFoundException;
import com.rosales.springtest.infrastructure.gateways.mapper.PriceEntityMapper;
import com.rosales.springtest.infrastructure.persistence.IPriceRepository;
import com.rosales.springtest.infrastructure.persistence.PriceEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class PriceRepositoryGateway implements IPriceGateway {

    private final IPriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Override
    public Price findPrice(PriceDTO priceDTO) {
        log.info("Executing PriceRepositoryGateway::findPrice method with parameter: {}", priceDTO);

        Objects.requireNonNull(priceDTO, "priceDTO must not be null");

        Optional<PriceEntity> priceEntityOpt = priceRepository.findFirstByProductAndBrandAndApplicationDate(
                priceDTO.getIdProduct(),
                priceDTO.getIdBrand(),
                priceDTO.getApplicationDate());

        if (priceEntityOpt.isEmpty()) {
            log.info("No records found finding PriceRepositoryGateway::findPrice method with parameter: {}", priceDTO);
            throw new ModelNotFoundException("No records found");
        }

        PriceEntity priceEntity = priceEntityOpt.get();

        log.info("PriceRepositoryGateway::findPrice method executed successfully. Response: {}", priceEntity);
        return priceEntityMapper.toDomainObj(priceEntity);
    }

}
