package com.rosales.springtest.infrastructure.controller;

import com.rosales.springtest.application.usecases.PriceInteractor;
import com.rosales.springtest.domain.entity.Price;
import com.rosales.springtest.domain.entity.PriceDTO;
import com.rosales.springtest.infrastructure.controller.mapper.PriceDTOMapper;
import com.rosales.springtest.infrastructure.controller.request.PriceRequest;
import com.rosales.springtest.infrastructure.controller.response.PriceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {

    private final PriceInteractor priceInteractor;
    private final PriceDTOMapper priceDTOMapper;

    @GetMapping("/applicationDate/{applicationDate}/product/{idProduct}/brand/{idBrand}")
    public ResponseEntity<PriceResponse> findPrice(@Valid PriceRequest priceRequest) {
        log.info("Executing PriceController::findPrice method with parameters: {}", priceRequest);

        PriceDTO priceDTO = priceDTOMapper.toPriceDto(priceRequest);
        Price price = priceInteractor.findPrice(priceDTO);
        PriceResponse priceResponse = priceDTOMapper.toResponse(price);

        log.info("PriceController::findPrice method executed successfully. Response: {}", priceResponse);
        return ResponseEntity.ok(priceResponse);
    }

}
