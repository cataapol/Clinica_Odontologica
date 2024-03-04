package com.backend.ClinicaOdontologica.service;

import com.backend.ClinicaOdontologica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    Odontologo registrar (Odontologo odontologo);

    Odontologo buscarPorId (int id);

    List<Odontologo> listarTodos ();
}
