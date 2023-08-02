package com.ejercicio.block17Batch.block17Batch.TiempoRiesgo;

import com.ejercicio.block17Batch.block17Batch.Tiempo.Tiempo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TiempoRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne
    Tiempo tiempo;
    LocalDate fechaPrediccion;
    String riesgo;
}
