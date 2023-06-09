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
        System.out.println(">>>>>> Lista antes de insertar:  "+ listaClientes.size());
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("MAINOR");
        clienteDto.setApellidos("MARA");
        clienteDto.setCedula("2525224");
        clienteDto.setTelefono("50685524587");
        clienteService.insertarCliente(clienteDto);
        listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        System.out.println(">>>>> Lista despues de insertar: " + listaClientes.size());

        assertEquals(3, listaClientes.size());
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
        List<ClienteDto> clienteDtos = clienteService.obtenerClientes();
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getApellidos()));
        assertEquals(2, clienteDtos.size());
    }

    @Test
    void obtenerClientesPorCodigoISOPaisYCuentasActivas() {
        List<ClienteDto> clienteDtos = clienteService.obtenerClientesPorCodigoISOPaisYCuentasActivas("CR");
        clienteDtos.forEach(clienteDto -> {System.out.println("Cliente: " +clienteDto.getApellidos());});
        assertEquals(1, clienteDtos.size());
    }

    @Test
    void eliminarCliente() {
        clienteService.eliminarCliente(1);
        assertEquals(1,1);
    }

    @Test
    void buscarPorApellidos() {
        List<ClienteDto> clienteDtos = clienteService.buscarPorApellidos("SANCHEZ");
        clienteDtos.forEach(clienteDto -> {System.out.println("Cliente: "+clienteDto.getApellidos());});
        assertEquals(1,clienteDtos.size());
    }

    @Test
    void buscarPorApellidosQueryNativo() {
        List<ClienteDto> clienteDtos = clienteService.buscarPorApellidosQueryNativo("SANCHEZ");
        clienteDtos.forEach(clienteDto -> {System.out.println("Cliente: "+clienteDto.getApellidos());});
        assertEquals(1,clienteDtos.size());
    }

    @Test
    void updateClienteByQuery() {
        ClienteDto clienteDtoOriginal = clienteService.buscarPorApellidos("SANCHEZ").get(0);
        System.out.println("NOMBRE ORIGINAL: " + clienteDtoOriginal.getNombre());
        clienteService.updateClienteByQuery("CAMBIO EL NOMBRE", "SANCHEZ");
        ClienteDto clienteDtoCambiado = clienteService.buscarPorApellidos("SANCHEZ").get(0);
        System.out.println("NOMBRE CAMBIADO: " + clienteDtoCambiado.getNombre());
        assertNotEquals(clienteDtoOriginal.getNombre(), clienteDtoCambiado.getNombre());
    }

    @Test
    void findByApellidosAndAndNombre() {
        List<ClienteDto> clienteDtos = clienteService.findByApellidosAndAndNombre("SANCHEZ", "RAUL");
        System.out.println("CLIENTE ENCONTRADO: " +clienteDtos.get(0).getApellidos());
        assertFalse(clienteDtos.isEmpty());
        assertEquals("SANCHEZ", clienteDtos.get(0).getApellidos());
    }

    @Test
    void busquedaDinamicaPorCriterios() {
        List<ClienteDto> clienteDtos = clienteService.busquedaDinamicaPorCriterios(new ClienteDto());
        assertFalse(clienteDtos.isEmpty());
        clienteDtos.forEach(clienteDto -> {System.out.println("Cliente: " +clienteDto.getApellidos());});
        assertTrue(clienteDtos.size() >=2);

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        clienteDtos = clienteService.busquedaDinamicaPorCriterios(clienteDto);
        clienteDtos.forEach(clienteDto2 -> {System.out.println("Cliente: " +clienteDto2.getApellidos() + clienteDto2.getNombre());});
        assertTrue(clienteDtos.size() == 4);

        clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        clienteDto.setNombre("HORACIO");
        clienteDtos = clienteService.busquedaDinamicaPorCriterios(clienteDto);
        clienteDtos.forEach(clienteDto3 -> {System.out.println("Cliente: " +clienteDto3.getApellidos());});
        assertTrue(clienteDtos.size() == 1);


        clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        clienteDto.setCedula("111");
        clienteDtos = clienteService.busquedaDinamicaPorCriterios(clienteDto);
        clienteDtos.forEach(clienteDto4 -> {System.out.println("Cliente: " +clienteDto4.getApellidos());});
        assertTrue(clienteDtos.size() == 1);

        clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        clienteDto.setNombre("HORACIO");
        clienteDto.setCedula("1111");
        clienteDtos = clienteService.busquedaDinamicaPorCriterios(clienteDto);
        clienteDtos.forEach(clienteDto5 -> {System.out.println("Cliente: " +clienteDto5.getApellidos());});
        assertTrue(clienteDtos.size() == 1);

    }

    // ejercicio individual 1
//    @Test
//    void obtenerClientesPorCodigoISOPaisYTarjetaInactiva() {
//        List<ClienteDto> clienteDtos = clienteService.obtenerClientesPorCodigoISOPaisYTarjetaInactiva("CR");
//        clienteDtos.forEach(clienteDto -> {System.out.println("Cliente: " +clienteDto.getApellidos());});
//        assertEquals(1, clienteDtos.size());
//    }
//--------------------------------

//    @Test
//    void obtenerClientes() {
//        ClienteDto clienteDto = new ClienteDto();
//        clienteDto.setId(3);
//        clienteDto.setNombre("ALBERTO");
//        clienteDto.setApellidos("SALAZAR");
//        clienteDto.setCedula("12311111111");
//        clienteDto.setTelefono("09997999999");
//        clienteService.actualizarCliente(clienteDto);
//        clienteService
//                .obtenerClientes()
//                .forEach( cliente -> System.out.println("Cliente: "+ cliente.getApellidos()));
//        assertEquals(1,1);

        //clienteService.obtenerClientes()
        //        .stream()
        //        .map(
        //        cliente -> {
        //            System.out.println(">>>>>>> Cliente :" + cliente.getApellidos());
        //            return cliente;
        //        }
        //).collect(Collectors.toList());
   // }
}