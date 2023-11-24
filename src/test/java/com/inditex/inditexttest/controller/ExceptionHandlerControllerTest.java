package com.inditex.inditexttest.controller;

import com.inditex.inditexttest.dto.StandardResponseDto;
import com.inditex.inditexttest.exception.BadRequestException;
import com.inditex.inditexttest.exception.ProductPriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionHandlerControllerTest {

    private final ExceptionHandlerController exceptionHandlerController = new ExceptionHandlerController();

    @Test
    public void testHandleProductPriceNotFoundException() {
        final String errorMessage = "Product price not found";
        final ProductPriceNotFoundException exception = new ProductPriceNotFoundException(errorMessage);
        final ResponseEntity<StandardResponseDto<?>> response = exceptionHandlerController.handler(exception).block();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody().getPayload());
        assertNull(response.getBody().getMessage());
        assertEquals(errorMessage, response.getBody().getError());
    }

    @Test
    public void testHandleBadRequestException() {
        final String errorMessage = "Bad request";
        final BadRequestException exception = new BadRequestException(errorMessage);
        final ResponseEntity<StandardResponseDto<?>> response = exceptionHandlerController.handler(exception).block();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody().getPayload());
        assertNull(response.getBody().getMessage());
        assertEquals(errorMessage, response.getBody().getError());
    }

    @Test
    public void testHandleRuntimeException() {
        final String errorMessage = "Internal server error";
        final RuntimeException exception = new RuntimeException(errorMessage);
        final ResponseEntity<StandardResponseDto<?>> response = exceptionHandlerController.handler(exception).block();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody().getPayload());
        assertNull(response.getBody().getMessage());
        assertEquals(errorMessage, response.getBody().getError());
    }

    @Test
    public void testHandleGenericException() {
        final String errorMessage = "Generic error";
        final Exception exception = new Exception(errorMessage);
        final ResponseEntity<StandardResponseDto<?>> response = exceptionHandlerController.handler(exception).block();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody().getPayload());
        assertNull(response.getBody().getMessage());
        assertEquals(errorMessage, response.getBody().getError());
    }
}
