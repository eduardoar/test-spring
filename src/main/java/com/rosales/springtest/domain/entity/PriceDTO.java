package com.rosales.springtest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class PriceDTO {

    private LocalDateTime applicationDate;

    private Long idProduct;

    private Long idBrand;

}
