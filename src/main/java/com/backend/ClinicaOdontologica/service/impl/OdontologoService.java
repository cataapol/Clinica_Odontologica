package com.backend.ClinicaOdontologica.service.impl;


import com.backend.ClinicaOdontologica.dao.IDao;
import com.backend.ClinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.ClinicaOdontologica.model.Odontologo;
import com.backend.ClinicaOdontologica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }


    @Override
    public OdontologoSalidaDto registraOdontologo(OdontologoEntradaDto odontologo) {
       return odontologoIDao.registrar(odontologo);
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }

    @Override
    public List<OdontologoSalidaDto> listarTodosLosOdontologos() {
        return odontologoIDao.listarTodos();
    }
}
