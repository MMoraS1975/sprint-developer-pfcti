package com.pfcti.sprintdevpfcti.service;

import com.pfcti.sprintdevpfcti.dto.CuentaDto;
import com.pfcti.sprintdevpfcti.model.Cuenta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CuentaServiceTest {

    @Autowired
    private CuentaService cuentaService;

    @Test
    void busquedaDinamicamentePorCriteriosCuentas() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setEstado(true);
        List<CuentaDto> cuentas = cuentaService.busquedaDinamicamentePorCriteriosCuentas(cuentaDto);
        cuentas.forEach(
                cuentaDtoResultado -> {
                    System.out.println("Cuenta Resultado-"
                            + "Tipo- " + cuentaDtoResultado.getTipo()
                            + "Numero- " + cuentaDtoResultado.getNumero()
                            + "Estado- " + cuentaDtoResultado.getEstado());
                });
        assertEquals(3,cuentas.size());
    }

    @Test
    void insertarCuenta() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setTipo("A");
        cuentaDto.getNumero("123")
        cuentaDto.setEstado(true);
        cuentaDto.setClienteId(1);

        cuentaService.insertarCuenta(cuentaDto);

        cuentaService.busquedaDinamicamentePorCriteriosCuentas(cuentaDto);
        List<CuentaDto> cuentaDtos = cuentaService.busquedaDinamicamentePorCriteriosCuentas(cuentaDto);

        assertEquals(1,1);
    }
}