package com.pfcti.sprintdevpfcti.repository;

import com.pfcti.sprintdevpfcti.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
