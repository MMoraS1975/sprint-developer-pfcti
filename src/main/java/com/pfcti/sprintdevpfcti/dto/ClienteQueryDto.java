package com.pfcti.sprintdevpfcti.dto;

import com.pfcti.sprintdevpfcti.dto.enums.ClienteQueryType;
import lombok.Data;

@Data
public class ClienteQueryDto {

    private String textoBusqueda;
    private ClienteQueryType clienteQueryType;
}
