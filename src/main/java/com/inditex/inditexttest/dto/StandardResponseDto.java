package com.inditex.inditexttest.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class StandardResponseDto<T> {
    private T payload;
    private String message;
    private String error;
}
