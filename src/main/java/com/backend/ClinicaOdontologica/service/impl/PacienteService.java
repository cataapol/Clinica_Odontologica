package com.backend.ClinicaOdontologica.service.impl;




import com.backend.ClinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.ClinicaOdontologica.entity.Paciente;
import com.backend.ClinicaOdontologica.repository.IPacienteRepository;
import com.backend.ClinicaOdontologica.service.IPacienteService;
import com.backend.ClinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    }


    //--------------------------------


    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
        //Log de los datos recibidos
        LOGGER.info("PacienteEntradaDto: {}", JsonPrinter.toString(pacienteEntradaDto));

        //Mapping de pacienteEntradaDto a Paciente.class para poder mandar a capa de persistencia
        Paciente pacienteEntity = modelMapper.map(pacienteEntradaDto, Paciente.class);

        //Ejecucion del metodo y obtencion del ID
        Paciente pacienteEntityConId = pacienteRepository.save(pacienteEntity);

        //Pasando la entidad CON ID a PacienteSalidaDto
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteEntityConId, PacienteSalidaDto.class);

        LOGGER.info("PacienteSalidaDto: {}", JsonPrinter.toString(pacienteSalidaDto));
        return pacienteSalidaDto;

    }


    @Override
    public List<PacienteSalidaDto> listarPacientes() {

        //FORMA NRO DOS
        //Almacenamiento de list con ejecucion de metodo
        List<Paciente> pacientes = pacienteRepository.findAll();

        //ArrayList de tipo pacienteSalidaDto
        List<PacienteSalidaDto> pacientesSalidaDto = new ArrayList<>();

        //Iteramos sobre pacientes
        for (Paciente paciente: pacientes) {
            //Se cambia tipo de dato de Entity (paciente) a SalidaDTO
            PacienteSalidaDto pacienteSalidaDto = modelMapper.map(paciente, PacienteSalidaDto.class);
            //Se añade a la lista de salidaDto los resultados "parseados"
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




    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));  //lambda function (arrow function)


        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente:: getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }



}
