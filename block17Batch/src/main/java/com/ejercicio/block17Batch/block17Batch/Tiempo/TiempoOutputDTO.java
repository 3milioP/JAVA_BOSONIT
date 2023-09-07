package com.ejercicio.block17Batch.block17Batch.Tiempo;

import java.time.LocalDate;
import java.util.Date;

public class TiempoOutputDTO {
    String localidad;
    LocalDate fecha;
    int temperatura;

    public TiempoOutputDTO(Tiempo tiempo) {
        this.localidad = tiempo.getLocalidad();
        this.fecha = tiempo.getFecha();
        this.temperatura = tiempo.getTemperatura();
    }
}
