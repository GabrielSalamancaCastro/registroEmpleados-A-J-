package com.cidenet.prueba.Repository;

import com.cidenet.prueba.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado,Long> {

    @Query("SELECT e FROM Empleado e WHERE e.numeroIdentificacion = ?1")
    Empleado findEmpleadoByDocumento(String documento);

    @Query("SELECT e FROM Empleado e WHERE e.primerNombre = ?1")
    List<Empleado> findEmpleadoByPrimerNombre(String primerNombre);

    @Query("SELECT e FROM Empleado e WHERE e.primerApellido = ?1")
    List<Empleado> findEmpleadoByPrimerApellido(String primerApellido);

    @Query("SELECT e FROM Empleado e WHERE e.tipoIdentificacion = ?1")
    List<Empleado> findEmpleadoByTipoDocumento(String tipoIdentificacion);

    @Query("SELECT e FROM Empleado e WHERE e.paisEmpleo = ?1")
    List<Empleado> findEmpleadoByPaisEmpleo(String paisEmpleo);

    @Query("SELECT e FROM Empleado e WHERE e.area = ?1")
    List<Empleado> findEmpleadoByArea(String area);

    @Query("SELECT e FROM Empleado e WHERE e.correoElectronico = ?1")
    Empleado findEmpleadoByCorreoElectronico(String correoElectronico);


}
