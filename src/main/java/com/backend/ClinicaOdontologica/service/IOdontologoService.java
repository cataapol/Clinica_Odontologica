package com.backend.ClinicaOdontologica.service;

import com.backend.ClinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.OdontologoSalidaDto;

import java.util.List;

public interface IOdontologoService{

    OdontologoSalidaDto registraOdontologo(OdontologoEntradaDto odontologo);

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    List<OdontologoSalidaDto> listarTodosLosOdontologos();


}
