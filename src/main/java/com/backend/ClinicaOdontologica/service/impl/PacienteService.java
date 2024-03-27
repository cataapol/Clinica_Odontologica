package com.backend.ClinicaOdontologica.service.impl;




import com.backend.ClinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.ClinicaOdontologica.entity.Paciente;
import com.backend.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.ClinicaOdontologica.repository.IPacienteRepository;
import com.backend.ClinicaOdontologica.service.IPacienteService;
import com.backend.ClinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);


    private IPacienteRepository pacienteRepository;

    private ModelMapper modelMapper;



    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }




    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {


        LOGGER.info("PacienteEntradaDto: {}", JsonPrinter.toString(pacienteEntradaDto));

        Paciente pacienteEntity = modelMapper.map(pacienteEntradaDto, Paciente.class);

        Paciente pacienteConId = pacienteRepository.save(pacienteEntity);



        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteConId, PacienteSalidaDto.class);

        LOGGER.info("Se creó un nuevo paciente: {}", JsonPrinter.toString(pacienteSalidaDto));

        return pacienteSalidaDto;

    }


    @Override
    public List<PacienteSalidaDto> listarPacientes() {


        List<Paciente> pacientes = pacienteRepository.findAll();


        List<PacienteSalidaDto> pacientesSalidaDto = new ArrayList<>();


        for (Paciente paciente: pacientes) {

            PacienteSalidaDto pacienteSalidaDto = modelMapper.map(paciente, PacienteSalidaDto.class);

            pacientesSalidaDto.add(pacienteSalidaDto);
        }
        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacientesSalidaDto ));
        return pacientesSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPorId(Long id) {

        Paciente pacienteBuscadoEntity = pacienteRepository.findById(id).orElse(null); //Optional -> nullable

        PacienteSalidaDto pacienteEncontrado = null;

        if (pacienteBuscadoEntity != null) {

            pacienteEncontrado = modelMapper.map(pacienteBuscadoEntity, PacienteSalidaDto.class);

            LOGGER.info("Paciente encontrado: " + JsonPrinter.toString(pacienteEncontrado));


        } else LOGGER.error("No se ha encontrado al paciente..");

        return pacienteEncontrado;
    }


    @Override
    public void eliminarPacientePorId(Long id) throws ResourceNotFoundException {

        if (buscarPorId(id) != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Paciente eliminado {} ",  id);
        } else {
            LOGGER.error("No se ha encontrado el paciente {} ",  id);
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }
    }


    @Override
    public PacienteSalidaDto modificarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id) throws ResourceNotFoundException {
        Paciente pacienteRecibido = modelMapper.map(pacienteEntradaDto, Paciente.class);
        Paciente pacienteActualizado = pacienteRepository.findById(id).orElse(null);

        PacienteSalidaDto pacienteSalidaDto = null;

        if (pacienteActualizado != null) {
            pacienteActualizado.setNombre(pacienteRecibido.getNombre());
            pacienteActualizado.setApellido(pacienteRecibido.getApellido());
            pacienteActualizado.setDni(pacienteRecibido.getDni());
            pacienteActualizado.setFechaIngreso(pacienteRecibido.getFechaIngreso());
            pacienteActualizado.getDomicilio().setNumero(pacienteRecibido.getDomicilio().getNumero());
            pacienteActualizado.getDomicilio().setLocalidad(pacienteRecibido.getDomicilio().getLocalidad());
            pacienteActualizado.getDomicilio().setProvincia(pacienteRecibido.getDomicilio().getProvincia());

            pacienteRepository.save(pacienteActualizado);

            pacienteSalidaDto = modelMapper.map(pacienteActualizado, PacienteSalidaDto.class);
            LOGGER.warn("Paciente modificado! {}", JsonPrinter.toString(pacienteSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el paciente ya que el mismo no existe");
            throw new ResourceNotFoundException("No es posible actualizar el paciente ya que no se encuentra en nuestra base de datos. (Paciente " + id + " )");
        }


        return pacienteSalidaDto;
    }






    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));


        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente:: getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }


}
