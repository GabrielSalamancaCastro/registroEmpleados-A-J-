package com.cidenet.prueba.Service;

import com.cidenet.prueba.Model.Empleado;
import com.cidenet.prueba.Repository.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EmpleadoService implements IEmpleadoService <Empleado>{

    /* ==================== ATRIBUTOS ==========================*/

    private IEmpleadoRepository empleadoRepository;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(EmpleadoService.class));

    /* ==================== CONSTRUCTOR ==========================*/

    @Autowired
    public EmpleadoService(IEmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }


    /* ==================== METODOS DE LA INTERFACE ==========================*/
    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado save(Empleado empleado) {
        empleado.construirEmail();
        return empleadoRepository.save(empleado);
    }

    @Override
    public void delete(Long id) {
        if(findById(id).isPresent()){
            empleadoRepository.deleteById(id);
            logger.info("El empleado a sido eliminado exitosamente");
        }else{
            logger.info("El empleado no existe");
        }
    }

    @Override
    public String update(Empleado empleado) {
        Empleado empleadoUpdate = empleadoRepository.findById(empleado.getId()).get();
        empleadoUpdate.setPrimerApellido(empleado.getPrimerApellido());
        empleadoUpdate.setSegundoApellido(empleado.getSegundoApellido());
        empleadoUpdate.setPrimerNombre(empleado.getPrimerNombre());
        empleadoUpdate.setOtrosNombre(empleado.getOtrosNombre());
        empleadoUpdate.setPaisEmpleo(empleado.getPaisEmpleo());
        empleadoUpdate.setTipoIdentificacion(empleado.getTipoIdentificacion());
        empleadoUpdate.setNumeroIdentificacion(empleado.getNumeroIdentificacion());
        empleadoUpdate.setFechaIngreso(empleado.getFechaIngreso());
        empleadoUpdate.setEstado(empleado.getEstado());


        empleadoRepository.save(empleadoUpdate);
        logger.info("El empleado con id:" + empleado.getId() + "se ha actualizado");

        return "El empleado con id: " + empleado.getId() + "se ha actualizado";
    }

    /* ==================== OTROS METODOS ==========================*/

    public Empleado findEmpleadoByDocumento(String documento){
        return empleadoRepository.findEmpleadoByDocumento(documento);
    }

    public List<Empleado> findEmpleadoByPrimerNombre(String primerNombre){
        return empleadoRepository.findEmpleadoByPrimerNombre(primerNombre);
    }

    public List<Empleado> findEmpleadoByPrimerApellido(String primerApellido){
        return empleadoRepository.findEmpleadoByPrimerApellido(primerApellido);
    }

    public List<Empleado> findEmpleadoByTipoDocumento(String tipoIdentificacion){
        return empleadoRepository.findEmpleadoByTipoDocumento(tipoIdentificacion);
    }

    public List<Empleado> findEmpleadoByPaisEmpleo(String paisEmpleo){
        return empleadoRepository.findEmpleadoByPaisEmpleo(paisEmpleo);
    }

    public Empleado findEmpleadoByCorreoElectronico(String correoElectronico){
        return empleadoRepository.findEmpleadoByCorreoElectronico(correoElectronico);
    }

    public List<Empleado> findEmpleadoByArea(String area){
        return empleadoRepository.findEmpleadoByArea(area);
    }




}
