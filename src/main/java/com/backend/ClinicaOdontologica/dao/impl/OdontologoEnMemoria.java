package com.backend.ClinicaOdontologica.dao.impl;

import com.backend.ClinicaOdontologica.dao.IDao;
import com.backend.ClinicaOdontologica.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OdontologoEnMemoria implements IDao<Odontologo> { //Nuevo IDAO??
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoEnMemoria.class);
    private List<Odontologo> odontologoRepository;


    public OdontologoEnMemoria(List<Odontologo> odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {

        int id = odontologoRepository.size() + 1;
        Odontologo odontologoRegistrado = new Odontologo(id, odontologo.getNroMatricula(), odontologo.getNombre(), odontologo.getApellido());

        odontologoRepository.add(odontologoRegistrado);
        LOGGER.info("El odontólogo se registró: " + odontologoRegistrado);


        return odontologoRegistrado;

    }


    //--------------------------------------------------


    @Override
    public List<Odontologo> listarTodos() {

        LOGGER.info("Los odontólogos encontrados son: " + odontologoRepository);

        return odontologoRepository;
    }


    @Override
    public Odontologo buscarPorId(int id) {
        return null;
    }
}








