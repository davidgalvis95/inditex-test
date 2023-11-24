package com.inditex.inditexttest.repository;

import com.inditex.inditexttest.model.CataloguePrices;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface CataloguePricesRepository extends ReactiveCrudRepository<CataloguePrices, Long> {

    @Query("select c.product_id, c.brand_id, c.price_list, c.start_date, c.end_date, c.priority, c.price, c.currency " +
            "from catalogue_prices c where brand_id = :brandId and product_id = :productId and c.start_date < :applicableDate and c.end_date > :applicableDate " +
            "order by c.priority desc limit 1")
    Mono<CataloguePrices> findCataloguePricesMatchingCriteria(final @Param("brandId")  Integer brandId,
                                                              final @Param("productId")  Integer productId,
                                                              final @Param("applicableDate") LocalDateTime applicableDate);
}