package com.backend.ClinicaOdontologica.controller;


import com.backend.ClinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.ClinicaOdontologica.exception.BadRequestException;
import com.backend.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.ClinicaOdontologica.service.IPacienteService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/pacientes")
public class PacienteController {


    private IPacienteService pacienteService; //Inyeccion de dependencia de Interfaz pacienteService

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }



    //-----------------------



    //POST
    @PostMapping("/registrar")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto paciente) throws BadRequestException {
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }




    //GET
    @GetMapping("/PacienteId")
    public ResponseEntity<PacienteSalidaDto> buscarPacientePorID(@RequestParam Long id){
         return new ResponseEntity<>(pacienteService.buscarPorId(id), HttpStatus.OK);
    }



    @GetMapping("/buscarTodos")
    public ResponseEntity<List<PacienteSalidaDto>> listarTodosLosPacientes(){
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }






    @DeleteMapping("{id}") //
        public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPacientePorId(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }




    //PUT
    @PutMapping("/actualizar/${id}")
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto, @RequestParam Long id) throws ResourceNotFoundException {
       return new ResponseEntity<>( pacienteService.modificarPaciente(pacienteEntradaDto, id), HttpStatus.CREATED);
    }























}
