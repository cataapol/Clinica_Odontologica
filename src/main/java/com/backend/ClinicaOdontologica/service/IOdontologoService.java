package com.backend.ClinicaOdontologica.service;

import com.backend.ClinicaOdontologica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService{

    Odontologo registraOdontologo(Odontologo odontologo);

    Odontologo buscarOdontologoPorId(int id);

    List<Odontologo> listarTodosLosOdontologos();


}
