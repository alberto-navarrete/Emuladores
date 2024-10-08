package com.stefanini.emuladoresFG.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * Controlador de ejemplo llamado HomeController.
 * 
 * Este controlador maneja las solicitudes GET y POST para mostrar un
 * mensaje de "Hola Mundo".
 * 
 * @author ANG
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    /**
     * Método GET que devuelve un mensaje de "Hola Mundo".
     * 
     * @return Un mensaje de saludo "Hola Mundo".
     */
	@Operation(summary = "Muestra un mensaje de Saludo: Hola mundo")
    @GetMapping("/hola")
    public String holaMundo() {
        return "Hola mundo. ";
    }

    /**
     * Método POST que recibe un mensaje del cuerpo de la solicitud y lo devuelve.
     * 
     * @param mensaje El mensaje enviado en la solicitud POST.
     * @return El mensaje recibido.
     */
    @Operation(summary = "Hola mundo, entrega un saludo junto con el parametro enviado.")
    @GetMapping("/{id}")
    public String findById(@Parameter(description = "Mensaje a mostrar en el Hola Mundo") 
      @PathVariable String mensaje) {
        return "Hola Mundo: "+mensaje;
    }
}
