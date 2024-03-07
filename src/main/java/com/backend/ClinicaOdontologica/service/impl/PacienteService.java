package com.backend.ClinicaOdontologica.service.impl;



import com.backend.ClinicaOdontologica.dao.IDao;
import com.backend.ClinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.ClinicaOdontologica.entity.Paciente;
import com.backend.ClinicaOdontologica.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        return pacienteIDao.registrar(paciente);
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        return null;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        return pacienteIDao.listarTodos();
    }

    @Override
    public PacienteSalidaDto buscarPorId(int id) {
        return pacienteIDao.buscarPorId(id);
    }
}
