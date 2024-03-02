package com.backend.ClinicaOdontologica.service;



import com.backend.ClinicaOdontologica.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente registrarPaciente(Paciente paciente);
    List<Paciente> listarPacientes();
    Paciente buscarPorId(int id);
}
