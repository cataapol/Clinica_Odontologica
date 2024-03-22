package com.backend.ClinicaOdontologica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //Es un advisor (avisador). Avisa al controlador que hacer cuando se lanzan las excepciones dentro
public class GlobalExceptionHandler extends Exception{

    @ExceptionHandler(ResourceNotFoundException.class) //Entre llaves porque se puede definir mas de una (del mismo tipo)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public Map<String, String> manejarResourceNotFound(ResourceNotFoundException resourceNotFoundException) {

        Map<String, String> mensaje = new HashMap<>();

        mensaje.put("mensaje", "Recurso no encontrado: " + resourceNotFoundException.getMessage());
        return mensaje;

    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarBadRequest(BadRequestException badRequestException) {
        Map<String, String> mensajeBR = new HashMap<>();
        mensajeBR.put("mensaje", "Petición mal realizada: " + badRequestException.getMessage());
        return mensajeBR;
    }



    @ExceptionHandler(MethodArgumentNotValidException.class) //Exception para validaciones de dto.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarMethodNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> mensajeMNV = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(e -> {

            String nombreCampo = ((FieldError) e).getField(); //nombre del campo en el cual se genero el error

            String errorMsg = e.getDefaultMessage();


            mensajeMNV.put(nombreCampo, errorMsg);
        });

        return mensajeMNV;
    }


}
