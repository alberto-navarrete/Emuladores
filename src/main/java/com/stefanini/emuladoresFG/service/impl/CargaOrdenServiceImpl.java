package com.stefanini.emuladoresFG.service.impl;

import org.springframework.stereotype.Service;

import com.stefanini.emuladoresFG.service.CargaOrdenService;

@Service
public class CargaOrdenServiceImpl implements CargaOrdenService {

	@Override
	public String getHelloMessage() {
        return "Hola Mundo";
    }
	
}
