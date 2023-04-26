package com.pfcti.sprintdevpfcti.api;

import com.pfcti.sprintdevpfcti.dto.ClienteDto;
import com.pfcti.sprintdevpfcti.dto.CuentaDto;
import com.pfcti.sprintdevpfcti.service.CuentaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/cuenta")
public class CuentaApi {

    CuentaService cuentaService;
    @PostMapping
    public void guardarCuenta(@Valid @RequestBody CuentaDto clienteDto){
        log.info("cliente de cliente : {}", clienteDto);
//        cuentaService.creacionDeCuenta(cuentaDto);
    }

}
