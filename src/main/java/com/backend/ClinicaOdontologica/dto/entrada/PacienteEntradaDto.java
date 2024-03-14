package com.backend.ClinicaOdontologica.dto.entrada;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.time.LocalDate;

public class PacienteEntradaDto {

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del paciente..")
    @Size(min = 5, max = 50, message = "El campo debe tener mínimo 5 caracteres")
    private String nombre;

    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del paciente..")
    @Size(min = 5, max = 50, message = "El campo debe tener mínimo 5 caracteres")
    private String apellido;

    @Positive(message = "El dni del paciente no puede ser nulo o menor a cero")
    private int dni;


    @FutureOrPresent( message = "La fecha de ingreso no puede ser previa al dia de hoy.")
    @NotNull(message = "Se debe especificar la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("fecha_ingreso") //Nombre del dato de JSON que se va a consumir
    private LocalDate fechaIngreso;

    @NotNull(message = "El domicilio del paciente no puede ser nulo")
    @Valid
    private DomicilioEntradaDto domicilioEntradaDto;


    public PacienteEntradaDto() {
    }

    public PacienteEntradaDto(String nombre, String apellido, int dni, LocalDate fechaIngreso, DomicilioEntradaDto domicilioEntradaDto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilioEntradaDto = domicilioEntradaDto;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioEntradaDto getDomicilioEntradaDto() {
        return domicilioEntradaDto;
    }

    public void setDomicilioEntradaDto(DomicilioEntradaDto domicilioEntradaDto) {
        this.domicilioEntradaDto = domicilioEntradaDto;
    }
}
