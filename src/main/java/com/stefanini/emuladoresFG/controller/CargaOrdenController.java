package com.stefanini.emuladoresFG.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.emuladoresFG.service.CargaOrdenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sincronizaOrden")
public class CargaOrdenController {
	
	@Autowired
	CargaOrdenService cargaOrdenService;

	/**
     * Método GET que devuelve un mensaje de "Hola Mundo".
     * 
     * @return Un mensaje de saludo "Hola Mundo".
     */
	@Operation(summary = "Muestra un mensaje de Saludo: Hola mundo")
    @GetMapping("/hola")
    public String holaMundo() {
        return cargaOrdenService.getHelloMessage();
    }
	
    @Operation(summary = "Sincroniza Orden: Se encarga de RECIBIR la informacion de "
    		+ "las ordenes a las sucursales que corresponden y actualizar la Base de Datos "
    		+ "de la sucursal.")
    @PostMapping("/{ordenId}")
    public void sincronizaOrden(
    		@Parameter(description = "Orden a cargar") 
    		String orden) {
    	
    	log.info("Recibiendo solicitud de Sincronizacion de Orden ODEN RECIBIDA {}",orden);
    	//TODO Implementar funcion
    }
}
