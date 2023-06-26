package com.example.ejercicio.block11uploaddownloadfiles.fIchero.repository;

import com.example.ejercicio.block11uploaddownloadfiles.fIchero.domain.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FicheroRepository extends JpaRepository <Fichero, Integer> {
    Optional<Fichero> findByNombre(String nombre);
}
