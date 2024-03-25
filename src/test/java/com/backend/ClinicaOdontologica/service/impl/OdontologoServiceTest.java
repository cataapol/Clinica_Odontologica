package com.backend.ClinicaOdontologica.service.impl;

import com.backend.ClinicaOdontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.ClinicaOdontologica.dto.salida.OdontologoSalidaDto;
import com.backend.ClinicaOdontologica.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:applicationTest.properties")
class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;


    @Test
    @Order(1)
    public void deberiaRegistrarseOdontologoYRetornarSuMatriculaYNombre() {


        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("1243L52", "Ernesto", "Fernandez");



        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registraOdontologo(odontologoEntradaDto);



        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getMatricula());
        assertNotNull(odontologoSalidaDto.getNombre());

    }

    @Test
    @Order(3)
    public void deberiaModificarElOdontologoPorIdExitosamente () {



            Long idOdontologoExistente = 1L;


            OdontologoEntradaDto datosAModificar = new OdontologoEntradaDto("5678M90", "Carlos", "Mancini");


            OdontologoSalidaDto odontologoModificado = null;


        try {
            odontologoModificado = odontologoService.modificarOdontologo(datosAModificar, idOdontologoExistente);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }




        assertNotNull(odontologoModificado);

        assertNotNull(odontologoModificado.getMatricula());
        assertEquals("5678M90", odontologoModificado.getMatricula());


        assertNotNull(odontologoModificado.getNombre());
        assertEquals("Carlos", odontologoModificado.getNombre());


    }


    @Test
    @Order(2)
    public void deberiaEncontrarElUsuarioAlBuscarloPorId() {

        OdontologoSalidaDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(1L);

        assertNotNull(odontologoEncontrado);


    }




}