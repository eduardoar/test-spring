package com.rosales.springtest.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT price FROM PriceEntity price " +
            "WHERE price.idProduct = :idProduct " +
            "AND price.idBrand = :idBrand " +
            "AND (:applicationDate BETWEEN price.startDate AND price.endDate) " +
            "AND price.priority = ( " +
                "SELECT max(price.priority) FROM PriceEntity price " +
                "WHERE price.idProduct = :idProduct " +
                "AND price.idBrand = :idBrand " +
                "AND (:applicationDate BETWEEN price.startDate AND price.endDate) " +
            ")"
    )
    Optional<PriceEntity> findFirstByProductAndBrandAndApplicationDate(@Param("idProduct") Long idProduct, @Param("idBrand") Long idBrand, @Param("applicationDate") LocalDateTime applicationDate);

}
