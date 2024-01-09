package com.rosales.springtest.infrastructure.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class PriceRequest {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime applicationDate;

    @NotNull
    @Min(1)
    private Long idProduct;

    @NotNull
    @Min(1)
    private Long idBrand;

}