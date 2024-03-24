package com.backend.ClinicaOdontologica.dto.entrada;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull; //ES CON JAVAX - VER REPOSITORIO POM.XML
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {

    @NotNull(message = "La matricula no puede ser nula")
    @NotBlank(message = "Debe especificarse la matricula del odontologo..")
    @Size(min = 5, max = 50, message = "El campo debe tener mínimo 5 caracteres")
    private String matricula;

    @Size(min = 5, max = 50, message = "El campo debe tener mínimo 5 caracteres")
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odontologo..")

    private String nombre;

    @Size(min = 5, max = 50, message = "El campo debe tener mínimo 5 caracteres")
    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odontologo..")
    private String apellido;




    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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






}
