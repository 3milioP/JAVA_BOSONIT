package com.example.ejercicio.block7crudvalidation.repository;

import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

}
