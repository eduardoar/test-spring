package com.rosales.springtest.infrastructure.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceResponse {

    private Long idProduct;

    private Long idBrand;

    private Long idPriceRate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal price;

}
