package com.ejercicio.block17Batch.block17Batch.job;

import com.ejercicio.block17Batch.block17Batch.Tiempo.Tiempo;
import com.ejercicio.block17Batch.block17Batch.Tiempo.repository.TiempoRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public
class ErroresItemWriter implements ItemWriter<Tiempo> {
    private final TiempoRepository tiempoRepository;

    public ErroresItemWriter(TiempoRepository tiempoRepository) {
        this.tiempoRepository = tiempoRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends Tiempo> items) throws Exception {
        tiempoRepository.saveAll(items);
    }
}