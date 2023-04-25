package com.pfcti.sprintdevpfcti.beans;

import com.pfcti.sprintdevpfcti.dto.ClienteDto;
import com.pfcti.sprintdevpfcti.dto.ClienteQueryDto;
import com.pfcti.sprintdevpfcti.dto.enums.ClienteQueryType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuscadorClienteBddTest {

    @Autowired
    private BuscadorCliente baseDeDatos;

    @Autowired
    @Qualifier("baseDeDatos")
    private BuscadorCliente buscadorClienteQualifier;

    @Autowired
    @Qualifier("sistemaExterno")
    private BuscadorCliente buscadorClienteQualifierSistemaExterno;

    @Test
    void obtenerListaClientes() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("ROBERTO");
        clienteQueryDto.setClienteQueryType(ClienteQueryType.NOMBRES);

        List<ClienteDto> clienteDtos = baseDeDatos.obtenerListaClientes(clienteQueryDto);
        clienteDtos.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertTrue(clienteDtos.size() == 1);
    }

    @Test
    void obtenerListaClientesQualifier() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("ROBERTO");
        clienteQueryDto.setClienteQueryType(ClienteQueryType.NOMBRES);

        List<ClienteDto> clienteDtos = buscadorClienteQualifier.obtenerListaClientes(clienteQueryDto);
        clienteDtos.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertTrue(clienteDtos.size() == 1);
    }

    @Test
    void obtenerListaClientesQualifierSistemaExterno() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("ROBERTO");
        clienteQueryDto.setClienteQueryType(ClienteQueryType.NOMBRES);

        List<ClienteDto> clienteDtos = buscadorClienteQualifierSistemaExterno.obtenerListaClientes(clienteQueryDto);
        clienteDtos.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertTrue(clienteDtos.size() ==0);
    }

}