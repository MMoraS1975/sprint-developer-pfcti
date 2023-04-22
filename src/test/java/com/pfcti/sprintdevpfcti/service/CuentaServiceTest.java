package com.pfcti.sprintdevpfcti.service;

import com.pfcti.sprintdevpfcti.dto.CuentaDto;
import com.pfcti.sprintdevpfcti.model.Cuenta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


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
                    System.out.println("Cuenta Resultado" + cuentaDtoResultado.getTipo()
                    + cuentaDtoResultado.getNumero());
                });
    }
}