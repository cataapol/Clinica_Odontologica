package com.backend.ClinicaOdontologica.controller;

import com.backend.ClinicaOdontologica.entity.Odontologo;
import com.backend.ClinicaOdontologica.service.IOdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")

public class OdontologoController {


    private IOdontologoService odontologoService; //Inyeccion de dependencia de Interfaz pacienteService

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    //-----------------------


    //POST
    @PostMapping("registrar")
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo) {
        return odontologoService.registraOdontologo(odontologo);
    }


    //GET
    @GetMapping("/odontologoId")
    public Odontologo buscarOdontologoPorID(@RequestParam int id) {
        return odontologoService.buscarOdontologoPorId(id);
    }


    @GetMapping("/buscarTodos")
    public List<Odontologo> listarTodosLosPacientes() {
        return odontologoService.listarTodosLosOdontologos();
    }


    //DELETE
    @DeleteMapping("{id}")
    public void eliminarOdontologo(@PathVariable int id) {
        //pacienteService.eliminarPaciente(id)
    }


    //PUT
    @PutMapping("/actualizar/${id}")
    public Odontologo actualizarPaciente(@RequestBody Odontologo odontologo) {
        return null; //pacienteService.actualizar(paciente)
    }

}
