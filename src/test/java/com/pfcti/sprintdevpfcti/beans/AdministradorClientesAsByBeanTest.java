package com.pfcti.sprintdevpfcti.beans;

import com.pfcti.sprintdevpfcti.dto.ClienteDto;
import com.pfcti.sprintdevpfcti.dto.ClienteQueryDto;
import com.pfcti.sprintdevpfcti.dto.enums.ClienteQueryType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AdministradorClientesAsByBeanTest {

    @Autowired
    private AdministradorClientes defaultNombres;

    @Autowired
    @Qualifier("defaultNombres")
    private AdministradorClientes administradorClientes;

    @Test
    void obtenerListaClientesPorCriterio() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("ROBERTO");
        clienteQueryDto.setClienteQueryType(ClienteQueryType.NOMBRES);

        List<ClienteDto> clienteDtos = defaultNombres.obtenerListaClientesPorCriterio(clienteQueryDto);
        clienteDtos.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertTrue(clienteDtos.size() == 1);
    }

    @Test
    void obtenerListaClientesPorCriterioConAnotacion() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("ROBERTO");
        clienteQueryDto.setClienteQueryType(ClienteQueryType.NOMBRES);

        List<ClienteDto> clienteDtos = administradorClientes.obtenerListaClientesPorCriterio(clienteQueryDto);
        clienteDtos.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertTrue(clienteDtos.size() == 1);
    }

    @Test
    void obtenerListaClientesPorCriterioConAnotacion2() {
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("ROBERTO");
        clienteQueryDto.setClienteQueryType(ClienteQueryType.NOMBRES);

        List<ClienteDto> clienteDtos = administradorClientes.obtenerListaClientesPorCriterio(clienteQueryDto);
        clienteDtos.forEach(clienteDto -> {
            System.out.println("Cliente: " + clienteDto.getApellidos());
        });
        assertTrue(clienteDtos.size() == 1);
    }
}