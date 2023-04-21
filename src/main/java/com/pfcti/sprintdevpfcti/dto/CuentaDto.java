package com.pfcti.sprintdevpfcti.dto;

import lombok.Data;

@Data
public class CuentaDto {
    private int id;
    private String numero;
    private String tipo;
    private boolean estado;
    private int clienteId;
}
