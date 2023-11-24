package com.inditex.inditexttest.controller;

import com.inditex.inditexttest.dto.StandardResponseDto;
import com.inditex.inditexttest.exception.BadRequestException;
import com.inditex.inditexttest.exception.ProductPriceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ProductPriceNotFoundException.class)
    public Mono<ResponseEntity<StandardResponseDto<?>>> handler(ProductPriceNotFoundException ex) {
        Arrays.stream(ex.getStackTrace()).forEach(stack -> log.warn(String.valueOf(stack)));
        return Mono.just(new ResponseEntity<>(buildStandardResponse(ex.getMessage(), null), ex.getStatus()));
    }

    @ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<StandardResponseDto<?>>> handler(BadRequestException ex) {
        Arrays.stream(ex.getStackTrace()).forEach(stack -> log.warn(String.valueOf(stack)));
        return Mono.just(new ResponseEntity<>(buildStandardResponse(ex.getMessage(), null), ex.getStatus()));
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<StandardResponseDto<?>>> handler(RuntimeException ex) {
        Arrays.stream(ex.getStackTrace()).forEach(stack -> log.warn(String.valueOf(stack)));
        return Mono.just(new ResponseEntity<>(buildStandardResponse(ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<StandardResponseDto<?>>> handler(Exception ex) {
        Arrays.stream(ex.getStackTrace()).forEach(stack -> log.warn(String.valueOf(stack)));
        return Mono.just(new ResponseEntity<>(buildStandardResponse(ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR));
    }


    private StandardResponseDto<?> buildStandardResponse(final String errorMessage, final String customMessage) {
        return new StandardResponseDto<>(
                null,
                customMessage,
                errorMessage
        );
    }
}
