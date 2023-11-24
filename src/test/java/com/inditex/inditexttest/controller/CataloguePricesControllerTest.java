package com.inditex.inditexttest.controller;


import com.inditex.inditexttest.InditextTestApplication;
import com.inditex.inditexttest.dto.ProductPriceDto;
import com.inditex.inditexttest.dto.StandardResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.inditex.inditexttest.util.ApiPaths.BASE_PATH;

@Slf4j
@SpringBootTest(classes = InditextTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CataloguePricesControllerTest {

    private WebTestClient client;

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        this.client
                = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .baseUrl(BASE_PATH)
                .build();
    }

    @Test
    @Order(10)
    public void testGetProductPriceOn0614At10ForProduct35455AndBrand1() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14-00:00:00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31-23:59:59", formatter);
        final ProductPriceDto productPriceDto = ProductPriceDto.builder()
                .productId(35455)
                .brandId(1)
                .brandName("ZARA")
                .priceList(1)
                .startDate(startDate)
                .endDate(endDate)
                .price("35.5 EUR")
                .build();

        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35455&brandId=1&targetDate=2020-06-14-10:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getError() == null && response.getMessage() == null
                        && (Objects.equals(response.getPayload(), productPriceDto)))
                .thenCancel()
                .verify();
    }

    @Test
    @Order(20)
    public void testGetProductPriceOn0614At16ForProduct35455AndBrand1() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14-15:00:00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2020-06-14-18:30:00", formatter);
        final ProductPriceDto productPriceDto = ProductPriceDto.builder()
                .productId(35455)
                .brandId(1)
                .brandName("ZARA")
                .priceList(2)
                .startDate(startDate)
                .endDate(endDate)
                .price("25.45 EUR")
                .build();

        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35455&brandId=1&targetDate=2020-06-14-16:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getError() == null && response.getMessage() == null
                        && (Objects.equals(response.getPayload(), productPriceDto)))
                .thenCancel()
                .verify();
    }

    @Test
    @Order(30)
    public void testGetProductPriceOn0614At21ForProduct35455AndBrand1() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-14-00:00:00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31-23:59:59", formatter);
        final ProductPriceDto productPriceDto = ProductPriceDto.builder()
                .productId(35455)
                .brandId(1)
                .brandName("ZARA")
                .priceList(1)
                .startDate(startDate)
                .endDate(endDate)
                .price("35.5 EUR")
                .build();

        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35455&brandId=1&targetDate=2020-06-14-21:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getError() == null && response.getMessage() == null
                        && (Objects.equals(response.getPayload(), productPriceDto)))
                .thenCancel()
                .verify();
    }

    @Test
    @Order(40)
    public void testGetProductPriceOn0615At10ForProduct35455AndBrand1() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-15-00:00:00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2020-06-15-11:00:00", formatter);
        final ProductPriceDto productPriceDto = ProductPriceDto.builder()
                .productId(35455)
                .brandId(1)
                .brandName("ZARA")
                .priceList(3)
                .startDate(startDate)
                .endDate(endDate)
                .price("30.5 EUR")
                .build();

        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35455&brandId=1&targetDate=2020-06-15-10:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getError() == null && response.getMessage() == null
                        && (Objects.equals(response.getPayload(), productPriceDto)))
                .thenCancel()
                .verify();
    }

    @Test
    @Order(50)
    public void testGetProductPriceOn0615At21ForProduct35455AndBrand1() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        final LocalDateTime startDate = LocalDateTime.parse("2020-06-15-16:00:00", formatter);
        final LocalDateTime endDate = LocalDateTime.parse("2020-12-31-23:59:59", formatter);
        final ProductPriceDto productPriceDto = ProductPriceDto.builder()
                .productId(35455)
                .brandId(1)
                .brandName("ZARA")
                .priceList(4)
                .startDate(startDate)
                .endDate(endDate)
                .price("38.95 EUR")
                .build();

        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35455&brandId=1&targetDate=2020-06-15-21:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getError() == null && response.getMessage() == null
                        && (Objects.equals(response.getPayload(), productPriceDto)))
                .thenCancel()
                .verify();
    }

    @Test
    @Order(60)
    public void testGetProductPriceWhenNotFoundForDate() {
        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35455&brandId=1&targetDate=2021-01-01-00:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getPayload() == null && response.getMessage() == null
                        && response.getError().equals("No prices were found for brand id: 1, product id: 35455 and date: 2021-01-01T00:00"))
                .thenCancel()
                .verify();
    }

    @Test
    @Order(70)
    public void testGetProductPriceWhenNotFoundForProductId() {
        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35451&brandId=1&targetDate=2020-06-15-21:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getPayload() == null && response.getMessage() == null
                        && response.getError().equals("No prices were found for brand id: 1, product id: 35451 and date: 2020-06-15T21:00"))
                .thenCancel()
                .verify();
    }

    @Test
    @Order(80)
    public void testGetProductPriceWhenNotFoundForBrandId() {
        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35455&brandId=2&targetDate=2020-06-15-21:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getPayload() == null && response.getMessage() == null
                                && response.getError().equals("No prices were found for brand id: 2, product id: 35455 and date: 2020-06-15T21:00"))
                .thenCancel()
                .verify();
    }

    @Test
    @Order(90)
    public void testGetProductPriceWhenWrongBrandIdType() {
        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35455&brandId=x&targetDate=2020-06-15-21:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getPayload() == null && response.getMessage() == null
                                && response.getError().equals("For input string: \"x\""))
                .thenCancel()
                .verify();
    }

    @Test
    @Order(100)
    public void testGetProductPriceWhenWrongDateParamFormat() {
        final Flux<StandardResponseDto<ProductPriceDto>> result = this.client
                .get()
                .uri("?productId=35455&brandId=1&targetDate=2020-06-15T21:00:00")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .returnResult(new ParameterizedTypeReference<StandardResponseDto<ProductPriceDto>>() {
                })
                .getResponseBody();

        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getPayload() == null && response.getMessage() == null
                                && response.getError().equals("Text '2020-06-15T21:00:00' could not be parsed at index 10")
                )
                .thenCancel()
                .verify();
    }
}