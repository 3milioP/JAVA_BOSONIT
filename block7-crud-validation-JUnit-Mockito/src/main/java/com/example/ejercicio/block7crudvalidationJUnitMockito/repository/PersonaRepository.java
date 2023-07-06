package com.example.ejercicio.block7crudvalidationJUnitMockito.repository;

import com.example.ejercicio.block7crudvalidationJUnitMockito.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByNombre(String nombre);
}
