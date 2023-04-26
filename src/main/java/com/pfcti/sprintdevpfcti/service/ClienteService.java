package com.pfcti.sprintdevpfcti.service;

import com.pfcti.sprintdevpfcti.criteria.ClienteSpecification;
import com.pfcti.sprintdevpfcti.dto.ClienteDto;
import com.pfcti.sprintdevpfcti.model.Cliente;
import com.pfcti.sprintdevpfcti.repository.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;
    private CuentaRepository cuentaRepository;
    private TarjetaRepository tarjetaRepository;
    private InversionRepository inversionRepository;
    private DireccionRepository direccionRepository;
    private ClienteSpecification clienteSpecification;

    public void insertarCliente(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setPais(clienteDto.getPais());
        clienteRepository.save(cliente);
    }

    public ClienteDto obtenerCliente(int clienteId){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> {
                    throw new RuntimeException("Cliente no existe");
                }
        );
    ClienteDto clienteDto = new ClienteDto();
    clienteDto.setId(cliente.getId());
    clienteDto.setNombre(cliente.getNombre());
    clienteDto.setApellidos(cliente.getApellidos());
    clienteDto.setCedula(cliente.getCedula());
    clienteDto.setTelefono(cliente.getTelefono());
    return clienteDto;
    }

    public void actualizarCliente (ClienteDto clienteDto){
        Cliente cliente = clienteRepository.findById(clienteDto.getId())
                .orElseThrow(() -> {
                            throw new RuntimeException("Cliente no existe");
                        }
                );
        cliente.setCedula(clienteDto.getCedula());
        cliente.setId(clienteDto.getId());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setNombre(clienteDto.getNombre());
        clienteRepository.save(cliente);
    }

    public List<ClienteDto> obtenerClientes() {
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAll();
        clientes.forEach(cliente -> {
            clienteDtos.add(fromClienteToClienteDto(cliente));
        });
        return clienteDtos;
    }


    public List<ClienteDto> obtenerClientesPorCodigoISOPaisYCuentasActivas(String codigoISOPais){
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Cliente> cliente = clienteRepository.findClientesByPaisAndCuentas_EstadoIsTrue(codigoISOPais);
        cliente.forEach(clientes ->{
            clienteDtos.add(fromClienteToClienteDto(clientes));
        });
        return clienteDtos;
    }

    public void eliminarCliente(Integer clienteId){
        direccionRepository.deleteAllByCliente_Id(clienteId);
        cuentaRepository.deleteAllByCliente_Id(clienteId);
        tarjetaRepository.deleteAllByCliente_Id(clienteId);
        inversionRepository.deleteAllByCliente_Id(clienteId);
        clienteRepository.deleteById(clienteId);
    }

    public List<ClienteDto> buscarPorApellidos(String apellidos){
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.buscarPorApellidos(apellidos);
        clientes.forEach(cliente -> clienteDtos.add(fromClienteToClienteDto(cliente)));
        return clienteDtos;
    }

    public List<ClienteDto> buscarPorApellidosQueryNativo(String apellidos){
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Tuple> tuples = clienteRepository.buscarPorApellidosQueryNativo(apellidos);
        tuples.forEach(tuple ->{
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setApellidos((String) tuple.get("apellidos"));
            clienteDto.setNombre((String) tuple.get("nombre"));
            clienteDto.setCedula((String) tuple.get("cedula"));
            clienteDto.setTelefono((String) tuple.get("telefono"));
            clienteDto.setId((Integer) tuple.get("id"));
            clienteDtos.add(clienteDto);
        });
        return clienteDtos;
    }

    public void updateClienteByQuery(String nombre, String apellidos){
        clienteRepository.updateClienteByQuery(nombre, apellidos);
    }

    public List<ClienteDto> findByApellidosAndAndNombre(String apellidos, String nombre){
        return clienteRepository
                .findByApellidosAndAndNombre(apellidos,nombre)
                .stream()
                .map(this::fromClienteToClienteDto)
                .collect(Collectors.toList());
    }

    private ClienteDto fromClienteToClienteDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        //clienteDto.setId(cliente.getId());
        //clienteDto.setNombre(cliente.getNombre());
        //clienteDto.setApellidos(cliente.getApellidos());
        //clienteDto.setCedula(cliente.getCedula());
        //clienteDto.setTelefono(cliente.getTelefono());
        return clienteDto;
    }

    // ejercicio individual 1
//    public List<ClienteDto> obtenerClientesPorCodigoISOPaisYTarjetaInactiva(String codigoISOPais){
//        List<ClienteDto> clienteDtos = new ArrayList<>();
//        List<Cliente> cliente = clienteRepository.findClientesByPaisAndTarjeta_EstadoIsFalse(codigoISOPais);
//        cliente.forEach(clientes ->{
//            clienteDtos.add(fromClienteToClienteDto(clientes));
//        });
//        return clienteDtos;
//    }

    public List<ClienteDto> busquedaDinamicaPorCriterios(ClienteDto clienteDtoFilter){
        return clienteRepository
                .findAll(clienteSpecification.buildFilter(clienteDtoFilter))
                .stream()
                .map(this::fromClienteToClienteDto)
                .collect(Collectors.toList());
    }

}