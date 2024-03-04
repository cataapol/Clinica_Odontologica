package com.backend.ClinicaOdontologica.service.impl;


import com.backend.ClinicaOdontologica.dao.IDao;
import com.backend.ClinicaOdontologica.entity.Odontologo;
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
    public Odontologo registrar(Odontologo odontologo) {
        return odontologoIDao.registrar(odontologo);
    }

    @Override
    public Odontologo buscarPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoIDao.listarTodos();
    }

}
