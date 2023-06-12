package com.example.ejercicio.block7crudvalidation.repository;

import com.example.ejercicio.block7crudvalidation.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, String> {
}
