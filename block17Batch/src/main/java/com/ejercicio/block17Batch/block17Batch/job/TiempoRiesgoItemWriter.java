package com.ejercicio.block17Batch.block17Batch.job;

import com.ejercicio.block17Batch.block17Batch.TiempoRiesgo.TiempoRiesgo;
import com.ejercicio.block17Batch.block17Batch.TiempoRiesgo.repository.TiempoRiesgoRepository;
import jakarta.transaction.Transactional;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public
class TiempoRiesgoItemWriter implements ItemWriter<TiempoRiesgo> {
    private final TiempoRiesgoRepository tiempoRiesgoRepository;

    public TiempoRiesgoItemWriter(TiempoRiesgoRepository tiempoRiesgoRepository) {
        this.tiempoRiesgoRepository = tiempoRiesgoRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends TiempoRiesgo> items) throws Exception {
        tiempoRiesgoRepository.saveAll(items);
    }
}