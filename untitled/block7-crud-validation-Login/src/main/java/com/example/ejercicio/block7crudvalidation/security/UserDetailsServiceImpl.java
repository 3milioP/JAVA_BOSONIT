package com.example.ejercicio.block7crudvalidation.security;

import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Persona persona = personaRepository
                .findByUsuario(nombre)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con el nombre " + nombre + " no existe"));

        return new UserDetailsImpl(persona);
    }
}