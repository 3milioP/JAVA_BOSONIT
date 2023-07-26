package com.ejercicio.block16springcloud.Backend.Viaje.Repository;

import com.ejercicio.block16springcloud.Backend.Viaje.Domain.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
}
