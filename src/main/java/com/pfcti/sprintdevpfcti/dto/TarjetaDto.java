package com.pfcti.sprintdevpfcti.dto;

import com.pfcti.sprintdevpfcti.model.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class TarjetaDto {
    private int id;
    private String numero;
    private String tipo;
    private boolean estado;
    private int clienteId;
}
