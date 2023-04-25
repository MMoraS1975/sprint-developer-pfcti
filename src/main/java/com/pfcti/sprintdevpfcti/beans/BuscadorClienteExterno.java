package com.pfcti.sprintdevpfcti.beans;

import com.pfcti.sprintdevpfcti.dto.ClienteDto;
import com.pfcti.sprintdevpfcti.dto.ClienteQueryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("sistemaExterno")
public class BuscadorClienteExterno implements BuscadorCliente{
    @Override
    public List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto) {
        return new ArrayList<>();
    }
}
