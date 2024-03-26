package com.backend.ClinicaOdontologica.dto.entrada;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TurnoEntradaDto {


    @NotNull(message = "El odontologo asignado al turno no puede ser nulo")
    private Long OdontologoId;

    @NotNull(message = "El paciente asignado al turno no puede ser nulo")
    private Long PacienteId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "La fecha del turno no puede ser previa al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    @JsonProperty("dia_hora_ingreso")
    private LocalDateTime fechaYHora;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(Long odontologoId, Long pacienteId, LocalDateTime fechaYHora) {
        OdontologoId = odontologoId;
        PacienteId = pacienteId;
        this.fechaYHora = fechaYHora;
    }


    public Long getOdontologoId() {
        return OdontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        OdontologoId = odontologoId;
    }

    public Long getPacienteId() {
        return PacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        PacienteId = pacienteId;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
