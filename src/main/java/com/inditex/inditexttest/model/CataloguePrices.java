package com.inditex.inditexttest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("catalogue_prices")
public class CataloguePrices {
    @Id
    @Column("price_list")
    private Integer priceList;

    @Column("brand_id")
    private Integer brandId;

    @Column("start_date")
    private LocalDateTime startDate;

    @Column("end_date")
    private LocalDateTime endDate;

    @Column("product_id")
    private Integer productId;

    private Integer priority;

    private Double price;

    private String currency;

    @Transient
    private Brand brand;
}
