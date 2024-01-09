package com.rosales.springtest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Price {

    private Long idPrice;

    private Long idBrand;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long idPriceRate;

    private Long idProduct;

    private Integer priority;

    private BigDecimal productPrice;

    private String currency;

}
