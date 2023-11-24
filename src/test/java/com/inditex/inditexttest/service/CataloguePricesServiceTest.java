package com.inditex.inditexttest.service;

import com.inditex.inditexttest.dto.ProductPriceDto;
import com.inditex.inditexttest.exception.ProductPriceNotFoundException;
import com.inditex.inditexttest.model.Brand;
import com.inditex.inditexttest.model.CataloguePrices;
import com.inditex.inditexttest.repository.BrandRepository;
import com.inditex.inditexttest.repository.CataloguePricesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CataloguePricesServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CataloguePricesRepository cataloguePricesRepository;

    @InjectMocks
    private CataloguePricesService cataloguePricesService;

    @Test
    public void testGetProductPriceWithinDateRange() {
        final Integer brandId = 1;
        final Integer productId = 123;
        final LocalDateTime applicableDate = LocalDateTime.now();
        final LocalDateTime startDate = applicableDate.minusDays(1);
        final LocalDateTime endDate = applicableDate.plusDays(1);

        final CataloguePrices cataloguePrices = new CataloguePrices();
        cataloguePrices.setBrandId(brandId);
        cataloguePrices.setProductId(productId);
        cataloguePrices.setStartDate(startDate);
        cataloguePrices.setEndDate(endDate);
        cataloguePrices.setPriceList(1);
        cataloguePrices.setPrice(50.0);
        cataloguePrices.setCurrency("EUR");

        final ProductPriceDto expectedProductPriceDto = ProductPriceDto.builder()
                .productId(productId)
                .brandId(brandId)
                .brandName("ZARA")
                .priceList(1)
                .startDate(startDate)
                .endDate(endDate)
                .price("50.0 EUR")
                .build();

        when(cataloguePricesRepository.findCataloguePricesMatchingCriteria(brandId, productId, applicableDate))
                .thenReturn(Mono.just(cataloguePrices));

        when(brandRepository.findById(brandId))
                .thenReturn(Mono.just(new Brand(brandId, "ZARA")));

        final Mono<ProductPriceDto> result = cataloguePricesService.getProductPriceWithinDateRange(brandId, productId, applicableDate);

        result.blockOptional().ifPresent(productPriceDto -> {
            assertEquals(productPriceDto, expectedProductPriceDto);
        });
    }

    @Test
    public void testGetProductPriceWithinDateRange_NoPricesFound() {
        final Integer brandId = 1;
        final Integer productId = 123;
        final LocalDateTime applicableDate = LocalDateTime.now();

        when(cataloguePricesRepository.findCataloguePricesMatchingCriteria(brandId, productId, applicableDate))
                .thenReturn(Mono.empty());

        try {
            cataloguePricesService.getProductPriceWithinDateRange(brandId, productId, applicableDate).block();
        }catch (ProductPriceNotFoundException e) {
            assertEquals("No prices were found for brand id: 1, product id: 123 and date: " + applicableDate, e.getMessage());
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        }
    }
}
