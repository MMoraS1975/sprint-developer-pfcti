package com.pfcti.sprintdevpfcti.repository;

import com.pfcti.sprintdevpfcti.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    void deleteAllByCliente_Id(int clienteId);
}
