package com.pfcti.sprintdevpfcti.repository;

import com.pfcti.sprintdevpfcti.model.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

    List<Cliente> findClientesByPaisAndCuentas_EstadoIsTrue(String pais);

    @Query(value = "select c from Cliente c where c.apellidos = :apellidos")
    List<Cliente> buscarPorApellidos(String apellidos);

    @Query(value = "select nombre, apellidos, cedula, telefono, id from TCLIENTE where apellidos =:apellidos", nativeQuery = true)
    List<Tuple> buscarPorApellidosQueryNativo(String apellidos);

    @Modifying
    @Query(value = "update Cliente c set c.nombre =:nombre where c.apellidos =:apellidos")
    void updateClienteByQuery(String nombre, String apellidos);

    List<Cliente> findByApellidosAndAndNombre(String apellidos, String nombre);

    List<Cliente> findClientesByCedula(String cedula);

    List<Cliente> findClientesByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombres, String apellidos);

    // ejercicio individual 1
    //List<Cliente> findClientesByPaisAndTarjeta_EstadoIsFalse(String pais);


}

