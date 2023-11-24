package com.inditex.inditexttest.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductPriceNotFoundException extends RuntimeException{

    private HttpStatus status = HttpStatus.NOT_FOUND;
    public ProductPriceNotFoundException(String message) {
        super(message);
    }
}
