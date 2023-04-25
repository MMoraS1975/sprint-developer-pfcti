package com.pfcti.sprintdevpfcti.beans;

import com.pfcti.sprintdevpfcti.dto.ClienteDto;
import com.pfcti.sprintdevpfcti.dto.ClienteQueryDto;

import java.util.List;

public interface BuscadorCliente {
    List<ClienteDto>obtenerListaClientes(ClienteQueryDto clienteQueryDto);
}
