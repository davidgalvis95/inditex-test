package com.inditex.inditexttest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
@AllArgsConstructor
public class ProductPriceDto {

    Integer productId;

    Integer brandId;

    String brandName;

    Integer priceList;

    LocalDateTime startDate;

    LocalDateTime endDate;

    String price;

    @Override
    public String toString() {
        return "ProductPriceDto{ " +
                "productId: "+ productId +
                ", brandId: " + brandId +
                ", brandName: " + brandName +
                ", priceList: " + priceList +
                ", startDate: " + startDate +
                ", endDate: " + endDate +
                ", price: " + price +
                "}";
    }
}
