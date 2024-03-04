package com.backend.ClinicaOdontologica.controller;


import com.backend.ClinicaOdontologica.entity.Paciente;
import com.backend.ClinicaOdontologica.service.IPacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {


    private IPacienteService pacienteService; //Inyeccion de dependencia de Interfaz pacienteService

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }



    //-----------------------

    @GetMapping("/buscarPorId")
    public String buscarPorID(Model model, @RequestParam int id){ //@RequestParam -> el parametro id esta requerido en la peticion post

        //Nuevo objeto llamando a la capa de Servicio para que  realice la acción del método
        Paciente paciente = pacienteService.buscarPorId(id);

        //EnviandoINFO a la vista
            model.addAttribute("nombre", paciente.getNombre());
            model.addAttribute("apellido", paciente.getApellido());

        return "paciente"; //VISTA ex: paciente.html

    }







    //Se espera que model sea utilizado para agregar información relacionada con el paciente antes de mostrarla en la vista.





    @GetMapping("/buscarTodos")
    public List<Paciente> listarTodosLosPacientes(){
        return pacienteService.listarPacientes();
    }
    //----------------------

    @PostMapping("/registrarPaciente")
    public String registrar(Model model, @RequestBody Paciente paciente){


        Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);


        return "paciente";


    }
}
