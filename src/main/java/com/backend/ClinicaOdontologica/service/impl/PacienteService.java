package com.backend.ClinicaOdontologica.service.impl;



import com.backend.ClinicaOdontologica.dao.IDao;
import com.backend.ClinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.ClinicaOdontologica.model.Paciente;
import com.backend.ClinicaOdontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);


    private IDao<Paciente> pacienteIDao;

    private ModelMapper modelMapper;




    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
        configureMapping(); //Se llama al metodo
    }

    //--------------------------------


    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        return pacienteIDao.registrar(paciente);
    }


    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        return pacienteIDao.listarTodos();
    }

    @Override
    public PacienteSalidaDto buscarPorId(int id) {
        return pacienteIDao.buscarPorId(id);
    }




    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));  //function lambda (arrow function)


        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente:: getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }}
