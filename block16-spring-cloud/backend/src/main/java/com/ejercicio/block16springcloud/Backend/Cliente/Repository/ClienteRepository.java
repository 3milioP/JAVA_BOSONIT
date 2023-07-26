package com.ejercicio.block16springcloud.Backend.Cliente.Repository;
import com.ejercicio.block16springcloud.Backend.Cliente.Domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
