package com.backend.ClinicaOdontologica.controller;



import com.backend.ClinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.ClinicaOdontologica.exception.BadRequestException;
import com.backend.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.backend.ClinicaOdontologica.service.ITurnoService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //-----------------------------------------------


    @PostMapping("registrar")
    public ResponseEntity<TurnoSalidaDto> registrar(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        return new ResponseEntity<>(turnoService.registrar(turnoEntradaDto), HttpStatus.CREATED);
    }

    @GetMapping("/turnoId")
    public ResponseEntity<TurnoSalidaDto> buscarPorId(@RequestParam Long id) {
        return new ResponseEntity<>(turnoService.buscarPorId(id), HttpStatus.OK);
    }


    @GetMapping("/buscarTodos")
    public ResponseEntity<List<TurnoSalidaDto>> listarTodos() {
        return new ResponseEntity<>(turnoService.listarTodos(), HttpStatus.OK);
    }


    @DeleteMapping("/eliminarTurno")
    public ResponseEntity<?> eliminarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
    }



    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto, @RequestParam Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>( turnoService.modificarTurno(turnoEntradaDto, id), HttpStatus.CREATED);
    }
}
