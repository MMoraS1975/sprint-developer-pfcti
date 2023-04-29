package com.pfcti.sprintdevpfcti.service;

import com.pfcti.sprintdevpfcti.criteria.CuentaSpecification;
import com.pfcti.sprintdevpfcti.dto.ClienteDto;
import com.pfcti.sprintdevpfcti.dto.CuentaDto;
import com.pfcti.sprintdevpfcti.dto.NotificationDto;
import com.pfcti.sprintdevpfcti.jms.publishers.NotificationPubSubSender;
import com.pfcti.sprintdevpfcti.jms.senders.NotificationSender;
import com.pfcti.sprintdevpfcti.model.Cliente;
import com.pfcti.sprintdevpfcti.model.Cuenta;
import com.pfcti.sprintdevpfcti.repository.ClienteRepository;
import com.pfcti.sprintdevpfcti.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;
    private ClienteRepository clienteRepository;
    private NotificationSender notificationSender;
    private ClienteService clienteService;

    private NotificationPubSubSender notificationPubSubSender;

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

    public void sendNotification(CuentaDto cuentaDto) {
        ClienteDto clienteDto = clienteService.obtenerCliente(cuentaDto.getClienteId());
        NotificationDto notificacionDto = new NotificationDto();
        notificacionDto.setPhoneNumber(clienteDto.getTelefono());
        notificacionDto.setMailBody("Estimado " + clienteDto.getNombre() + " " + clienteDto.getApellidos() + " tu cuenta número " + cuentaDto.getNumero() + " se ha creado con éxito.");
        this.notificationSender.sendMail(notificacionDto);
    }
    private static String getMailBody(CuentaDto cuentaDto, ClienteDto clienteDto) {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Estimado ");
        bodyBuilder.append(clienteDto.getNombre());
        bodyBuilder.append(" ");
        bodyBuilder.append(clienteDto.getApellidos());
        bodyBuilder.append(" tu cuenta número ");
        bodyBuilder.append(cuentaDto.getNumero());
        bodyBuilder.append(" se ha creado con éxito.");
        return bodyBuilder.toString();

    }

    public void creacionDeCuentaYNotificacion(CuentaDto cuentaDto) {
        insertarCuenta(cuentaDto);
        sendNotification(cuentaDto);
    }

    private Cuenta fromDtoToCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        BeanUtils.copyProperties(cuentaDto, cuenta);
        Cliente cliente = new Cliente();
        cliente.setId(cuentaDto.getClienteId());
        cuenta.setCliente(cliente);
        return cuenta;
    }

    public void sendCreateAccountNotification(CuentaDto cuentaDto) {
        log.info("Empezando envío de notificaciones");
        Message<CuentaDto> message = MessageBuilder.withPayload(cuentaDto).build();
        Message<String> result = notificationPubSubSender.sendNotification(message);
        log.info("Resultado envío notificación: {}", result.getPayload());
    }

    public void creacionDeCuentaPublishSub(CuentaDto cuentaDto) {
        insertarCuenta(cuentaDto);
        sendCreateAccountNotification(cuentaDto);
    }

}
