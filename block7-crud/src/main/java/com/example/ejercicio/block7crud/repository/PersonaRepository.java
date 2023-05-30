package com.example.ejercicio.block7crud.repository;

import com.example.ejercicio.block7crud.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByNombre(String nombre);
}
