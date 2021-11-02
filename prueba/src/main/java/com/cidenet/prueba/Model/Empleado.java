package com.cidenet.prueba.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Table(name = "empleados")
public class Empleado {


        /* ==================== ATRIBUTOS ==========================*/

        @Id
        @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "empleado_sequence")
        @SequenceGenerator(name = "empleado_sequence", sequenceName = "empleado_sequence")
        private Long id;

        private String primerApellido;
        private String segundoApellido;
        private String primerNombre;
        private String otrosNombre;
        private String paisEmpleo;
        private String tipoIdentificacion;
        private String numeroIdentificacion;
        private String correoElectronico;
        private LocalDate fechaIngreso;
        private String area;
        private String estado;
        private final LocalDateTime fechaHoraIngreso = LocalDateTime.now();



        /* ==================== GETTERS AND SETTERS ==========================*/

        public Long getId() {
            return id;
        }

        public String getPrimerApellido() {
            return primerApellido;
        }

        public void setPrimerApellido(String primerApellido) {
            this.primerApellido = primerApellido;
        }

        public String getSegundoApellido() {
            return segundoApellido;
        }

        public void setSegundoApellido(String segundoApellido) {
            this.segundoApellido = segundoApellido;
        }

        public String getPrimerNombre() {
            return primerNombre;
        }

        public void setPrimerNombre(String primerNombre) {
            this.primerNombre = primerNombre;
        }

        public String getOtrosNombre() {
            return otrosNombre;
        }

        public void setOtrosNombre(String otrosNombre) {
            this.otrosNombre = otrosNombre;
        }

        public String getPaisEmpleo() {
            return paisEmpleo;
        }

        public void setPaisEmpleo(String paisEmpleo) {
            this.paisEmpleo = paisEmpleo;
        }

        public String getTipoIdentificacion() {
            return tipoIdentificacion;
        }

        public void setTipoIdentificacion(String tipoIdentificacion) {
            this.tipoIdentificacion = tipoIdentificacion;
        }

        public String getNumeroIdentificacion() {
            return numeroIdentificacion;
        }

        public void setNumeroIdentificacion(String numeroIdentificacion) {
            this.numeroIdentificacion = numeroIdentificacion;
        }

        public String getCorreoElectronico() {
            return correoElectronico;
        }

        public void setCorreoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
        }

        public LocalDate getFechaIngreso() {
            return fechaIngreso;
        }

        public void setFechaIngreso(LocalDate fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public LocalDateTime getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    /* ==================== METODOS ==========================*/

        public void construirEmail(){
            switch (getPaisEmpleo()){
                case "COLOMBIA":
                    setCorreoElectronico(getPrimerNombre().toLowerCase()+"."+getPrimerApellido().toLowerCase()+"."+getNumeroIdentificacion()+"@cidenet.com.co");
                    break;
                case "USA":
                    setCorreoElectronico(getPrimerNombre().toLowerCase()+"."+getPrimerApellido().toLowerCase()+"."+getNumeroIdentificacion()+"@cidenet.com.us");
                    break;
            }
        }




        /* ==================== CONSTRUCTOR ==========================*/

        public Empleado(String primerApellido, String segundoApellido, String primerNombre, String otrosNombre, String paisEmpleo, String tipoIdentificacion, String numeroIdentificacion, LocalDate fechaIngreso,String area, String estado) {
            this.primerApellido = primerApellido;
            this.segundoApellido = segundoApellido;
            this.primerNombre = primerNombre;
            this.otrosNombre = otrosNombre;
            this.paisEmpleo = paisEmpleo;
            this.tipoIdentificacion = tipoIdentificacion;
            this.numeroIdentificacion = numeroIdentificacion;
            this.fechaIngreso = fechaIngreso;
            this.area=area;
            this.estado = estado;
        }

        public Empleado() {}

    }

