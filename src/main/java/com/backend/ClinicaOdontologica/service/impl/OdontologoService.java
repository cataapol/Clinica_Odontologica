package com.backend.ClinicaOdontologica.service.impl;


import com.backend.ClinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.ClinicaOdontologica.entity.Odontologo;
import com.backend.ClinicaOdontologica.repository.IOdontologoRepository;
import com.backend.ClinicaOdontologica.service.IOdontologoService;
import com.backend.ClinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository odontologoRepository;

    private ModelMapper modelMapper;


    public OdontologoService(IOdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OdontologoSalidaDto registraOdontologo(OdontologoEntradaDto odontologoEntradaDto) {

        LOGGER.info("OdontologoEntradaDto: {}", JsonPrinter.toString(odontologoEntradaDto));

        Odontologo odontologoEntity = modelMapper.map(odontologoEntradaDto, Odontologo.class);

        Odontologo odontologoConId = odontologoRepository.save(odontologoEntity);

        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoConId, OdontologoSalidaDto.class);

        LOGGER.info("Se creó un nuevo odontólogo: {}", JsonPrinter.toString(odontologoSalidaDto));

        return odontologoSalidaDto;

    }


    @Override
    public List<OdontologoSalidaDto> listarTodosLosOdontologos() {

        List<Odontologo> odontologos = odontologoRepository.findAll();
        List<OdontologoSalidaDto> odontologosSalidaDto = new ArrayList<>();

        for (Odontologo odontologo : odontologos) {

            OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologo, OdontologoSalidaDto.class);

            odontologosSalidaDto.add(odontologoSalidaDto);
        }
        LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologosSalidaDto));

        return odontologosSalidaDto;
    }


    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {

        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);

        OdontologoSalidaDto odontologoEncontrado = null;

        if (odontologoBuscado != null) {

            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);

            LOGGER.info("Odontólogo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));


        } else LOGGER.error("No se ha encontrado al odontólogo..");

        return odontologoEncontrado;
    }

}
