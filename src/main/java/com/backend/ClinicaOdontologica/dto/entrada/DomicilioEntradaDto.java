package com.backend.ClinicaOdontologica.dto.entrada;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.*;

@JsonIgnoreProperties() //Ignorar datos recibidos por json que no se pueden mappear
public class DomicilioEntradaDto {

    @NotNull(message = "La calle no puede ser nula")
    @NotBlank(message = "Debe especificarse la calle del paciente..")
    @Size(min = 5, max = 50, message = "El campo debe tener mínimo 5 caracteres")
    private String calle;


    @Positive(message = "El numero del domicilio no puede ser nulo o menor a cero")
    @Digits(integer = 5, fraction = 10, message = "El campo debe tener mínimo 5 numeros")
    private int numero;

    @NotNull(message = "La localidad no puede ser nula")
    @NotBlank(message = "Debe especificarse la localidad del paciente..")
    @Size(min = 5, max = 50, message = "El campo debe tener mínimo 5 caracteres")
    private String localidad;


    @NotNull(message = "La provincia no puede ser nula")
    @NotBlank(message = "Debe especificarse la provincia del paciente..")
    @Size(min = 5, max = 50, message = "El campo debe tener mínimo 5 caracteres")
    private String provincia;


    public DomicilioEntradaDto() {
    }

    public DomicilioEntradaDto(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }


    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
