package com.inditex.inditexttest.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BadRequestException extends RuntimeException{

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    public BadRequestException(String message) {
        super(message);
    }
}
