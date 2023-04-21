package com.pfcti.sprintdevpfcti.repository;

import com.pfcti.sprintdevpfcti.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {
    void deleteAllByCliente_Id(int clienteID);
}
