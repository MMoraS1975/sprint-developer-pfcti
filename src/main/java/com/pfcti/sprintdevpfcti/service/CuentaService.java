package com.pfcti.sprintdevpfcti.service;

import com.pfcti.sprintdevpfcti.criteria.CuentaSpecification;
import com.pfcti.sprintdevpfcti.dto.ClienteDto;
import com.pfcti.sprintdevpfcti.dto.CuentaDto;
import com.pfcti.sprintdevpfcti.model.Cliente;
import com.pfcti.sprintdevpfcti.model.Cuenta;
import com.pfcti.sprintdevpfcti.repository.ClienteRepository;
import com.pfcti.sprintdevpfcti.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;
    private ClienteRepository clienteRepository;

    public List<CuentaDto> busquedaDinamicamentePorCriteriosCuentas(CuentaDto cuentaDtoFilter){
        return cuentaRepository.findAll(cuentaSpecification.buildFilter(cuentaDtoFilter))
                .stream()
                .map(this::fromCuentaToCuentaDto)
                .collect(Collectors.toList());
    }

    private CuentaDto fromCuentaToCuentaDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }

    public void insertarCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setNumero(cuentaDto.getNumero());
        cuenta.setCliente(clienteRepository.getById(cuentaDto.getClienteId()));
        cuentaRepository.save(cuenta);
    }

}
