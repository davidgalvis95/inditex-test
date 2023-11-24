package com.inditex.inditexttest.service;

import com.inditex.inditexttest.dto.ProductPriceDto;
import com.inditex.inditexttest.exception.ProductPriceNotFoundException;
import com.inditex.inditexttest.model.CataloguePrices;
import com.inditex.inditexttest.repository.BrandRepository;
import com.inditex.inditexttest.repository.CataloguePricesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
public class CataloguePricesService {

    private final BrandRepository brandRepository;
    private final CataloguePricesRepository cataloguePricesRepository;

    public CataloguePricesService(final BrandRepository brandRepository, final CataloguePricesRepository cataloguePricesRepository) {
        this.brandRepository = brandRepository;
        this.cataloguePricesRepository = cataloguePricesRepository;
    }

    public Mono<ProductPriceDto> getProductPriceWithinDateRange(final Integer brandId,
                                                                final Integer productId,
                                                                final LocalDateTime applicableDate) {
        log.info("Fetching product price available for product id: " + productId + "brand id: " + brandId + " and date: " + applicableDate);
        return cataloguePricesRepository.findCataloguePricesMatchingCriteria(brandId, productId, applicableDate)
                .switchIfEmpty(Mono.error(new ProductPriceNotFoundException(
                        "No prices were found for brand id: " + brandId + ", product id: " + productId +
                                " and date: " + applicableDate)))
                .flatMap(this::getCataloguePricesWithBrand)
                .map(this::mapCataloguePricesToProductPriceDto);
    }

    private Mono<CataloguePrices> getCataloguePricesWithBrand(final CataloguePrices productPrice) {
        if (productPrice.getBrandId() == null) {
            return Mono.just(productPrice);
        }
        return brandRepository.findById(productPrice.getBrandId())
                .map(brand -> {
                    productPrice.setBrand(brand);
                    return productPrice;
                });
    }

    private ProductPriceDto mapCataloguePricesToProductPriceDto(final CataloguePrices productPrice) {
        return ProductPriceDto.builder()
                .productId(productPrice.getProductId())
                .brandId(productPrice.getBrandId())
                .brandName(productPrice.getBrand().getName())
                .priceList(productPrice.getPriceList())
                .startDate(productPrice.getStartDate())
                .endDate(productPrice.getEndDate())
                .price(productPrice.getPrice() + " " + productPrice.getCurrency())
                .build();
    }
}
