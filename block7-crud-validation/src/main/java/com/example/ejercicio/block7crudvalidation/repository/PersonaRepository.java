package com.example.ejercicio.block7crudvalidation.repository;

import com.example.ejercicio.block7crudvalidation.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
   List<Persona> findByUsuario(String usuario);
}
