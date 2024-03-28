package com.backend.ClinicaOdontologica.service.impl;

import com.backend.ClinicaOdontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.ClinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.ClinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.ClinicaOdontologica.dto.entrada.TurnoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.DomicilioSalidaDto;
import com.backend.ClinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.ClinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.ClinicaOdontologica.dto.salida.TurnoSalidaDto;
import com.backend.ClinicaOdontologica.entity.Domicilio;
import com.backend.ClinicaOdontologica.entity.Odontologo;
import com.backend.ClinicaOdontologica.entity.Paciente;
import com.backend.ClinicaOdontologica.entity.Turno;
import com.backend.ClinicaOdontologica.exception.BadRequestException;
import com.backend.ClinicaOdontologica.exception.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:applicationTest.properties")
class TurnoServiceTest {


    @Autowired
    TurnoService turnoService;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;




    @Test
    @Order(1)
    public void deberiaDevolverUnaListaVaciaDeTurnos(){

        List<TurnoSalidaDto> turnos = turnoService.listarTodos();

        assertTrue(turnos.isEmpty());
    }


    @Test
    @Order(2)
    public void deberiaRegistrarUnTurnoExitosamentey() throws BadRequestException {

        DomicilioEntradaDto domicilioPaciente = new DomicilioEntradaDto("Calle", 24, "Localidad", "Provincia");


        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto();
        pacienteEntradaDto.setNombre("Catalina");
        pacienteEntradaDto.setApellido("Pol");
        pacienteEntradaDto.setDni(2435425);
        pacienteEntradaDto.setFechaIngreso(LocalDate.of(2024, 6,7));
        pacienteEntradaDto.setDomicilioEntradaDto(domicilioPaciente);

        PacienteSalidaDto pacienteRegister = pacienteService.registrarPaciente(pacienteEntradaDto);



        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto();
        odontologoEntradaDto.setNombre("Dr, Juan");
        odontologoEntradaDto.setApellido("Fernandez");
        odontologoEntradaDto.setMatricula("1324L");

        OdontologoSalidaDto odontologoRegister = odontologoService.registraOdontologo(odontologoEntradaDto);




        LocalDateTime fechaYHora = LocalDateTime.now();
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(pacienteRegister.getId(),odontologoRegister.getId(), fechaYHora);


        TurnoSalidaDto turnoSalidaDto = turnoService.registrar(turnoEntradaDto);


        assertEquals(1L, turnoSalidaDto.getId());
    }


    @Test
    @Order(3)
    void deberiaEliminarElTurnoConElId_1L() {
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L,1L, LocalDateTime.of (2024,6,13, 12,5 ));

        try{
            turnoService.eliminarTurno(1L);
        }catch (Exception e){
            e.printStackTrace();
        }


        assertNull(turnoService.buscarPorId(1L));
    }
}

