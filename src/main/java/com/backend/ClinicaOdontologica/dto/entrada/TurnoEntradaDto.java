package com.backend.ClinicaOdontologica.dto.entrada;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TurnoEntradaDto {


    @NotNull(message = "El odontologo asignado al turno no puede ser nulo")
    @Valid
    private OdontologoEntradaDto odontologoEntradaDto;

    @NotNull(message = "El paciente asignado al turno no puede ser nulo")
    @Valid
    private PacienteEntradaDto pacienteEntradaDto;

    @FutureOrPresent( message = "La fecha del turno no puede ser previa al dia de hoy.")
    @NotNull(message = "Se debe especificar la fecha y hora del turno")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime fechaYHora;


    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(OdontologoEntradaDto odontologoEntradaDto, PacienteEntradaDto pacienteEntradaDto, LocalDateTime fechaYHora) {
        this.odontologoEntradaDto = odontologoEntradaDto;
        this.pacienteEntradaDto = pacienteEntradaDto;
        this.fechaYHora = fechaYHora;
    }

    public OdontologoEntradaDto getOdontologoEntradaDto() {
        return odontologoEntradaDto;
    }

    public void setOdontologoEntradaDto(OdontologoEntradaDto odontologoEntradaDto) {
        this.odontologoEntradaDto = odontologoEntradaDto;
    }

    public PacienteEntradaDto getPacienteEntradaDto() {
        return pacienteEntradaDto;
    }

    public void setPacienteEntradaDto(PacienteEntradaDto pacienteEntradaDto) {
        this.pacienteEntradaDto = pacienteEntradaDto;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
