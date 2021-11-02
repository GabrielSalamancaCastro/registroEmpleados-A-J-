package com.cidenet.prueba.Controller;

import com.cidenet.prueba.Model.Empleado;
import com.cidenet.prueba.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("empleados")
public class EmpleadoController {
    /* ==================== ATRIBUTOS ==========================*/
    private EmpleadoService empleadoService;
    java.util.logging.Logger logger = Logger.getLogger(String.valueOf(EmpleadoService.class));

    /* ==================== CONSTRUCTOR ==========================*/

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }


    /* ==================== METODOS GET ==========================*/
    @GetMapping("/all")
    public List<Empleado> getAllEmpleados(){
        logger.info("Buscando la lista de todos los empleados");
        return empleadoService.findAll();
    }

    @GetMapping("/{id}")
    public Empleado getEmpleadoByID(@PathVariable Long id){
        logger.info("Buscando empleado por ID");
        return empleadoService.findById(id).orElse(null);
    }

    @GetMapping("/documento/{documento}")
    public Empleado getEmpleadoByDocumento(@PathVariable String documento){
        logger.info("Buscando empleado por documento");
        return empleadoService.findEmpleadoByDocumento(documento);
    }

    @GetMapping("/primerApellido/{primerApellido}")
    public List<Empleado> getEmpleadoByPrimerApellido(@PathVariable String primerApellido){
        logger.info("Buscando empleado por primer apellido");
        return empleadoService.findEmpleadoByPrimerApellido(primerApellido);
    }

    @GetMapping("/primerNombre/{primerNombre}")
    public List<Empleado> getEmpleadoByPrimerNombre(@PathVariable String primerNombre){
        logger.info("Buscando empleado por primer nombre");
        return empleadoService.findEmpleadoByPrimerNombre(primerNombre);
    }

    @GetMapping("/tipoDocumento/{tipoDocumento}")
    public List<Empleado> getEmpleadoByTipoDocumento(@PathVariable String tipoDocumento){
        logger.info("Buscando empleado por tipo de documento");
        return empleadoService.findEmpleadoByTipoDocumento(tipoDocumento);
    }

    @GetMapping("/paisEmpleo/{paisEmpleo}")
    public List<Empleado> getEmpleadoByPaisEmpleo(@PathVariable String paisEmpleo){
        logger.info("Buscando empleado por pais de empleo");
        return empleadoService.findEmpleadoByPaisEmpleo(paisEmpleo);
    }

    @GetMapping("/email/{email}")
    public Empleado getEmpleadoByCorreoElectronico(@PathVariable String email){
        logger.info("Buscando empleado por correo electronico");
        return empleadoService.findEmpleadoByCorreoElectronico(email);
    }

    @GetMapping("/area/{area}")
    public List<Empleado> getEmpleadoByArea(@PathVariable String area){
        logger.info("Buscando empleado por area");
        return empleadoService.findEmpleadoByArea(area);
    }



    /* ==================== METODOS POST ==========================*/

    @PostMapping("/save")
    public ResponseEntity<?> saveEmpleado(@RequestBody Empleado empleado){

        ResponseEntity response;

        if (empleadoService.findEmpleadoByDocumento(empleado.getNumeroIdentificacion()) != null){
            response = new ResponseEntity("El empleado ya existe, cambielo por favor", HttpStatus.CONFLICT);
        }
        else if(empleado.getPrimerApellido().equals(empleado.getPrimerApellido().toUpperCase())==false){
            response = new ResponseEntity( "Los nombres y apellidos deben ser digitados en Mayusculas", HttpStatus.NOT_ACCEPTABLE);
        }
        else if(empleado.getSegundoApellido().equals(empleado.getSegundoApellido().toUpperCase())==false){
            response = new ResponseEntity( "Los nombres y apellidos deben ser digitados en Mayusculas", HttpStatus.NOT_ACCEPTABLE);
        }
        else if(empleado.getPrimerNombre().equals(empleado.getPrimerNombre().toUpperCase())==false){
            response = new ResponseEntity( "Los nombres y apellidos deben ser digitados en Mayusculas", HttpStatus.NOT_ACCEPTABLE);
        }
        else if(empleado.getOtrosNombre().equals(empleado.getOtrosNombre().toUpperCase())==false){
            response = new ResponseEntity( "Los nombres y apellidos deben ser digitados en Mayusculas", HttpStatus.NOT_ACCEPTABLE);
        }
        else if(empleado.getFechaIngreso().isAfter(LocalDate.now())){
            response = new ResponseEntity( "La fecha de ingreso debe ser previa a la fecha actual", HttpStatus.NOT_ACCEPTABLE);

        }
        else if (empleado.getPrimerApellido().isEmpty() || empleado.getSegundoApellido().isEmpty() || empleado.getPrimerNombre().isEmpty() || empleado.getNumeroIdentificacion().isEmpty()){
            response = new ResponseEntity( "Los campos no pueden estar vacios", HttpStatus.NOT_ACCEPTABLE);
        }
        else{

            response = new ResponseEntity(empleadoService.save(empleado),HttpStatus.OK);
        }
        return response;
    }


    @PostMapping("post/test")
    public List<Empleado> test(){
        Empleado empleado1= new Empleado("SALAMANCA","CASTRO","GABIEL","EDUARDO","COLOMBIA","Cedula de ciudadania", "123456789", LocalDate.of(2020,02,14),"Desarrollo","ACTIVO");
        Empleado empleado2= new Empleado("MERCADO","NAVARRO","CRISTIAN","ANDRES","USA","Cedula de ciudadania", "11112222", LocalDate.of(2020,02,14),"Recursos Humanos","ACTIVO");
        Empleado empleado3= new Empleado("OSORIO","AVENDAÑO","JUAN","IGNACIO","COLOMBIA","Pasaporte", "1212121212", LocalDate.of(2020,02,14),"Administracion","ACTIVO");

        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(empleadoService.save(empleado1));
        empleados.add(empleadoService.save(empleado2));
        empleados.add(empleadoService.save(empleado3));
        return empleados;
    }

    /* ==================== METODO PUT ==========================*/

    @PutMapping("/update")
    public  ResponseEntity<?> updateEmpleado(@RequestBody Empleado empleado){
        ResponseEntity response;
        if(empleadoService.findById(empleado.getId()).isPresent()){
            response= new ResponseEntity(empleadoService.update(empleado), HttpStatus.OK);
        }else{
            response=new ResponseEntity("El empleado no existe",HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /* ==================== METODO DELETE ==========================*/

    @DeleteMapping("/delete/{id}")
    public void deleteEmpleado(@PathVariable Long id){
        empleadoService.delete(id);
    }

}
