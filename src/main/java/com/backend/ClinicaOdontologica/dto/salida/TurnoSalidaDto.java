package com.backend.ClinicaOdontologica.dto.salida;

import com.backend.ClinicaOdontologica.entity.Odontologo;
import com.backend.ClinicaOdontologica.entity.Paciente;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private int id;

    private OdontologoSalidaDto odontologoSalidaDto;

    private PacienteSalidaDto pacienteSalidaDto;

    private LocalDateTime fechaYHora;


    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(int id, OdontologoSalidaDto odontologoSalidaDto, PacienteSalidaDto pacienteSalidaDto, LocalDateTime fechaYHora) {
        this.id = id;
        this.odontologoSalidaDto = odontologoSalidaDto;
        this.pacienteSalidaDto = pacienteSalidaDto;
        this.fechaYHora = fechaYHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OdontologoSalidaDto getOdontologoSalidaDto() {
        return odontologoSalidaDto;
    }

    public void setOdontologoSalidaDto(OdontologoSalidaDto odontologoSalidaDto) {
        this.odontologoSalidaDto = odontologoSalidaDto;
    }

    public PacienteSalidaDto getPacienteSalidaDto() {
        return pacienteSalidaDto;
    }

    public void setPacienteSalidaDto(PacienteSalidaDto pacienteSalidaDto) {
        this.pacienteSalidaDto = pacienteSalidaDto;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
