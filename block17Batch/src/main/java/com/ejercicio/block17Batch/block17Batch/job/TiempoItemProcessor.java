package com.ejercicio.block17Batch.block17Batch.job;

import com.ejercicio.block17Batch.block17Batch.Tiempo.Tiempo;
import com.ejercicio.block17Batch.block17Batch.TiempoRiesgo.TiempoRiesgo;
import org.springframework.batch.item.ItemProcessor;

public class TiempoItemProcessor implements ItemProcessor<Tiempo, TiempoRiesgo> {
    @Override
    public TiempoRiesgo process(Tiempo tiempo) throws Exception {
        // Lógica para calcular el riesgo y crear el objeto TiempoRiesgo
        TiempoRiesgo tiempoRiesgo = new TiempoRiesgo();
        tiempoRiesgo.setTiempo(tiempo);
        tiempoRiesgo.setFechaPrediccion(tiempo.getFecha().plusDays(1)); // Ejemplo: agregar 1 día para la fecha de predicción
        tiempoRiesgo.setRiesgo(calcularRiesgo(tiempo.getTemperatura())); // Ejemplo: calcular el riesgo según la temperatura
        return tiempoRiesgo;
    }

    private String calcularRiesgo(int temperatura) {

        if (temperatura > 35) {
            return "ALTO";
        } else if (temperatura > 32) {
            return "MEDIO";
        } else {
            return "BAJO";
        }
    }
}