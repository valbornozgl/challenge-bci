package com.bci.challenge.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime fecha;
    private Integer codigo;
    private String detail;

    public ErrorResponse(Integer codigo, String detail) {
        super();
        this.fecha = LocalDateTime.now();
        this.codigo = codigo;
        this.detail = detail;
    }

}
