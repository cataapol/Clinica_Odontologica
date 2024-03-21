package com.backend.ClinicaOdontologica.service;



import com.backend.ClinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.ClinicaOdontologica.exception.BadRequestException;
import com.backend.ClinicaOdontologica.exception.ResourceNotFoundException;


import java.util.List;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) throws BadRequestException;
    List<PacienteSalidaDto> listarPacientes();
    PacienteSalidaDto buscarPorId(Long id);

    void eliminarPacientePorId(Long id) throws ResourceNotFoundException;
}
