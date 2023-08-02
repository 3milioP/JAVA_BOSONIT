package com.ejercicio.block17Batch.block17Batch.Tiempo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tiempo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String localidad;
    LocalDate fecha;
    int temperatura;
}
