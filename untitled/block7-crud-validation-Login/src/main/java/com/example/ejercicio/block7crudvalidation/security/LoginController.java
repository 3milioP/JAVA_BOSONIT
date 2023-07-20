package com.example.ejercicio.block7crudvalidation.security;

import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    TokenUtils jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        String usuario = requestMap.get("usuario");
        String password = requestMap.get("password");

        Optional<Persona> person = personaRepository.findByUsuario(usuario);
        Persona persona = person.get();

        if (!persona.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Usuario o contraseña incorrectos");
        }

        String role = Boolean.TRUE.equals(persona.getAdmin()) ? "ROLE_ADMIN" : "ROLE_USER";
        return new ResponseEntity<>(jwtTokenUtil.createToken(usuario, role), HttpStatus.OK);
    }


}
