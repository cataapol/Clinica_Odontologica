package com.backend.ClinicaOdontologica.service.impl;

import com.backend.ClinicaOdontologica.dto.entrada.DomicilioEntradaDto;
import com.backend.ClinicaOdontologica.dto.entrada.PacienteEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.PacienteSalidaDto;
import com.backend.ClinicaOdontologica.exception.BadRequestException;
import com.backend.ClinicaOdontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:applicationTest.properties")
class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;


    @Test
    @Order(1)
    public void deberiaRegistrarsePacienteDeNombreJuanYRetornarSuId() {

        //arrange
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan" , "Fernandez", 123456, LocalDate.of(2024, 4, 13), new DomicilioEntradaDto("Calle", 1234, "La plata","Buenos Aires"));


        //act
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);


        //assert
        assertNotNull(pacienteSalidaDto);
        assertNotNull(pacienteSalidaDto.getId());
        assertEquals("Juan", pacienteSalidaDto.getNombre());

    }


//    @Test
//    @Order(2)
//   public void deberiaEliminarsePacienteConId1(){
//
//        try{
//            pacienteService.eliminarPacientePorId(1L);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        //??
//        assertDoesNotThrow(ResourceNotFoundException.class, () -> pacienteService.eliminarPacientePorId(1L) );
//
//    }




    @Test
    @Order(2)
    public void deberiaDevolverUnaListaVaciaDePacientes(){
        List<PacienteSalidaDto> pacientes = pacienteService.listarPacientes();

        assertTrue(pacientes.isEmpty());
    }



}