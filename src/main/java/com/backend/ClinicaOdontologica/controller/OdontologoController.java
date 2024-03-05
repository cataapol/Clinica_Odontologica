package com.backend.ClinicaOdontologica.controller;

import com.backend.ClinicaOdontologica.entity.Odontologo;
import com.backend.ClinicaOdontologica.service.IOdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/odontologos")

public class OdontologoController {


    private IOdontologoService odontologoService; //Inyeccion de dependencia de Interfaz pacienteService

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }



    //-----------------------

    @PostMapping("/registrarOdontologo")
    public String registrar(Model model, @RequestBody Odontologo odontologo){


        Odontologo paciente1 = odontologoService.registraOdontologo(odontologo);


        return "paciente";

    }


    @GetMapping("/buscarPorId")
    public String buscarPorID(Model model, @RequestParam int id){ //@RequestParam -> el parametro id esta requerido en la peticion post

        //Nuevo objeto llamando a la capa de Servicio para que  realice la acción del método
        Odontologo odontologo =  odontologoService.buscarOdontologoPorId(id);


        //Enviando INFO a la vista
        model.addAttribute("nroMatricula", odontologo.getNroMatricula());


        return "paciente"; //VISTA ex: paciente.html

    }







    //Se espera que model sea utilizado para agregar información relacionada con el paciente antes de mostrarla en la vista.





    @GetMapping("/buscarTodos")
    public String listarTodosLosOdontologos(Model model) {
        List<Odontologo> odontologos = odontologoService.listarTodosLosOdontologos();

        model.addAttribute("odontologos", odontologos);

        return "paciente";

    }
    //----------------------
}