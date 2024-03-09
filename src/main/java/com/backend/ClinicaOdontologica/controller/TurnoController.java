package com.backend.ClinicaOdontologica.controller;

import com.backend.ClinicaOdontologica.service.ITurnoService;

public class TurnoController {

    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //-----------------------------------------------
}
