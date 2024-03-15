package com.backend.ClinicaOdontologica.service.impl;


import com.backend.ClinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.ClinicaOdontologica.entity.Turno;
import com.backend.ClinicaOdontologica.repository.ITurnoRepository;
import com.backend.ClinicaOdontologica.service.ITurnoService;
import com.backend.ClinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private ITurnoRepository turnoRepository;

    private ModelMapper modelMapper;


    public TurnoService(ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoSalidaDto registrar(TurnoEntradaDto turnoEntradaDto) {
        LOGGER.info("TurnoEntradaDto: {}", JsonPrinter.toString(turnoEntradaDto));

        Turno turnoEntity = modelMapper.map(turnoEntradaDto, Turno.class);

        Turno turnoID = turnoRepository.save(turnoEntity);

        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoID, TurnoSalidaDto.class);

        LOGGER.info("Turno registrado con éxito!: {}", JsonPrinter.toString(turnoSalidaDto));

        return turnoSalidaDto;
    }



    @Override
    public TurnoSalidaDto buscarPorId(Long id) {
        Turno turnoEntity = turnoRepository.findById(id).orElse(null);

        TurnoSalidaDto turnoEncontrado = null;

        if(turnoEncontrado != null) {

            turnoEncontrado =  modelMapper.map(turnoEntity, TurnoSalidaDto.class);

            LOGGER.info("Se ha encontrado el turno: {}", JsonPrinter.toString(turnoEncontrado));

        } else LOGGER.error("No se ha encontrado el turno...");

        return turnoEncontrado;
    }




    @Override
    public List<TurnoSalidaDto> listarTodos() {
        //Ejecucion de metodo
        List<Turno> turnos = turnoRepository.findAll();

        //ArrayList de tipo dto
        List<TurnoSalidaDto> turnosSalidaDto = new ArrayList<>();

        //Iteramos sobre turnos
        for (Turno turno: turnos) {
            //Se cambia tipo de dato de Entity (paciente) a SalidaDTO
            TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
            //Se añade a la lista de salidaDto los resultados "parseados"
            turnosSalidaDto.add(turnoSalidaDto);
        }
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnosSalidaDto));
        return turnosSalidaDto;
    }



    private void configureMapping(){
        modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                .addMappings(mapper -> mapper.map(TurnoEntradaDto::getOdontologoId, Turno::setOdontologo))  //lambda function (arrow function)
                .addMappings(mapper -> mapper.map(TurnoEntradaDto::getPacienteId, Turno::setPaciente));  //la


        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> mapper.map(Turno:: getOdontologo, TurnoSalidaDto::setOdontologoSalidaDto))
                .addMappings(mapper -> mapper.map(Turno:: getPaciente, TurnoSalidaDto::setPacienteSalidaDto));
    }

}
