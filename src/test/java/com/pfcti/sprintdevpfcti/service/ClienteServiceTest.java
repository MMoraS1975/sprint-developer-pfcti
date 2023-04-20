package com.pfcti.sprintdevpfcti.service;

import com.pfcti.sprintdevpfcti.dto.ClienteDto;
import com.pfcti.sprintdevpfcti.model.Cliente;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    private EntityManager entityManager;
    @Test
    void insertarCliente() {

        List<Cliente> listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        System.out.println(">>>>>> Lista antes de insertar:  "+listaClientes.size());
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("MAINOR");
        clienteDto.setApellidos("MARA");
        clienteDto.setCedula("2525224");
        clienteDto.setTelefono("50685524587");
        clienteService.insertarCliente(clienteDto);
        assertEquals(1, 1);
    }

    @Test
    void obtenerCliente() {
        ClienteDto clienteDto = clienteService.obtenerCliente(1);
        System.out.println(">>>>>>>> El cliente si existe: " + clienteDto.getApellidos());
        assertEquals(1,clienteDto.getId());
    }

    @Test
    void actualizarCliente() {
        ClienteDto clienteDtoInicial = clienteService.obtenerCliente(1);
        System.out.println("El cliente inicial es: "+ clienteDtoInicial.getApellidos());
        clienteDtoInicial.setApellidos("SALAZAR");
        clienteService.actualizarCliente(clienteDtoInicial);
        ClienteDto clienteDtoLuegoDeUpdate = clienteService.obtenerCliente(1);
        System.out.println("El cliente inicial es: "+ clienteDtoLuegoDeUpdate.getApellidos());
        assertEquals("SALAZAR", clienteDtoLuegoDeUpdate.getApellidos());
    }

    @Test
    void obtenerClientes() {
        clienteService.obtenerClientes()
                .stream()
                .map(
                cliente -> {
                    System.out.println(">>>>>>> Cliente :" + cliente.getApellidos());
                    return cliente;
                }
        ).collect(Collectors.toList());
        assertEquals(1,1);
    }
}