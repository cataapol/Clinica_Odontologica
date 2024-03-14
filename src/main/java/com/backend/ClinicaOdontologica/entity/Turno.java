package com.backend.ClinicaOdontologica.entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime fechaYHora;

//-----------------------------------
    public Turno() {
    }

    public Turno(int id, Odontologo odontologo, Paciente paciente, LocalDateTime fechaYHora) {
        this.id = id;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fechaYHora = fechaYHora;
    }


//-------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }


}
