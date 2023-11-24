package com.inditex.inditexttest.controller;

import com.inditex.inditexttest.dto.ProductPriceDto;
import com.inditex.inditexttest.dto.StandardResponseDto;
import com.inditex.inditexttest.exception.BadRequestException;
import com.inditex.inditexttest.service.CataloguePricesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.inditex.inditexttest.util.ApiPaths.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH)
public class CataloguePricesController {

    private final CataloguePricesService cataloguePricesService;

    public CataloguePricesController(CataloguePricesService cataloguePricesService) {
        this.cataloguePricesService = cataloguePricesService;
    }

    @GetMapping
    public Mono<ResponseEntity<StandardResponseDto<ProductPriceDto>>> getProductPriceMatchingParams(
            @RequestParam(name = "productId") String productId,
            @RequestParam(name = "brandId") String brandId,
            @RequestParam(name = "targetDate") String targetDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(targetDate, formatter);
            int brandIdNumber = Integer.parseInt(brandId);
            int productIdNumber = Integer.parseInt(productId);
            return cataloguePricesService.getProductPriceWithinDateRange(brandIdNumber, productIdNumber, localDateTime)
                    .map(response -> ResponseEntity.status(HttpStatus.OK)
                            .body(StandardResponseDto.<ProductPriceDto>builder()
                                    .payload(response)
                                    .build()
                            )
                    );
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
