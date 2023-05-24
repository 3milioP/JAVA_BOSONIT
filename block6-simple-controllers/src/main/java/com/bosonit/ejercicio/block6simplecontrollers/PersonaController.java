package com.bosonit.ejercicio.block6simplecontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonaController {

	@GetMapping("/user/{nombre}")
	public String getUser(@PathVariable String nombre) {
		return "Hola " + nombre;
	}

	@PostMapping("/useradd")
	public ResponseEntity<Persona> addUser(@RequestBody Persona persona) {
		int nuevaEdad = persona.getEdad() + 1;
		persona.setEdad(nuevaEdad);
		return new ResponseEntity<>(persona, HttpStatus.OK);
	}

}