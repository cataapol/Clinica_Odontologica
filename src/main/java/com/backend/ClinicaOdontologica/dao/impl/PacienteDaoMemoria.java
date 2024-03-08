package com.backend.ClinicaOdontologica.dao.impl;


import com.backend.ClinicaOdontologica.dao.IDao;
import com.backend.ClinicaOdontologica.model.Domicilio;
import com.backend.ClinicaOdontologica.model.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class PacienteDaoMemoria implements IDao<Paciente> {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoMemoria.class);
    private List<Paciente> pacienteRepository;


    public PacienteDaoMemoria(List<Paciente> pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public List<Paciente> listarTodos() {
        LOGGER.info("Se encontraron los pacientes: " + pacienteRepository);
        return new ArrayList<>(pacienteRepository);
    }


    @Override
    public Paciente registrar(Paciente paciente) {
        int id = pacienteRepository.size() + 1;
        Domicilio domicilioGuardado = paciente.getDomicilio();
        domicilioGuardado.setId(id);
        Paciente pacienteGuardado = new Paciente(id, paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaIngreso(), domicilioGuardado);

        pacienteRepository.add(paciente);
        LOGGER.info("Paciente guardado: " + pacienteGuardado);
        return pacienteGuardado;
    }

    @Override
    public Paciente buscarPorId(int id) {
        for (Paciente paciente : pacienteRepository) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null; // No se encontró el paciente
    }

}
