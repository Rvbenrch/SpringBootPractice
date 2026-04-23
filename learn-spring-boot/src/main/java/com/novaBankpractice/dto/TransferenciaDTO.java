package com.novaBankpractice.dto;

import lombok.Data;

@Data
public class TransferenciaDTO {
    private Long idOrigen;
    private Long idDestino;
    private Double cantidad;
}