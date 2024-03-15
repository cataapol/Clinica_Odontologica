package com.backend.ClinicaOdontologica.service;

import com.backend.ClinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.TurnoSalidaDto;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto registrar(TurnoEntradaDto turnoEntradaDto);

    TurnoSalidaDto buscarPorId(Long id);

    List<TurnoSalidaDto> listarTodos();

}
