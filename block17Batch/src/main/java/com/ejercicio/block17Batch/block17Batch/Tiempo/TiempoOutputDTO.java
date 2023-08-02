package com.ejercicio.block17Batch.block17Batch.Tiempo;

public class TiempoOutputDTO {
    String localidad;
    String fecha;
    String temperatura;

    public TiempoOutputDTO(Tiempo tiempo) {
        this.localidad = tiempo.getLocalidad();
        this.fecha = tiempo.getFecha();
        this.temperatura = tiempo.getTemperatura();
    }
}
