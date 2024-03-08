package com.backend.ClinicaOdontologica.controller;

import com.backend.ClinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.ClinicaOdontologica.model.Odontologo;
import com.backend.ClinicaOdontologica.service.IOdontologoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologo) {
        return new ResponseEntity<>(odontologoService.registraOdontologo(odontologo), HttpStatus.CREATED);
    }


    //GET
    @GetMapping("/odontologoId")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorID(@RequestParam int id) {
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }


    @GetMapping("/buscarTodos")
    public ResponseEntity<List<OdontologoSalidaDto>> listarTodosLosPacientes() {
        return new ResponseEntity<>(odontologoService.listarTodosLosOdontologos(),HttpStatus.OK);
    }


    //DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable int id) {
        //odontologoService.eliminarOdontologo(id)
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }


    //PUT
    @PutMapping("/actualizar/${id}")
    public Odontologo actualizarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologo) {
        return null; //odontologoService.actualizar(odontologo) ResponseEntity status .OK
    }


}
