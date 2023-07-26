package com.ejercicio.backendFront.backendFront.Ticket.Domain;


import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.ClienteInputDTO;
import com.ejercicio.backendFront.backendFront.Ticket.Controller.DTO.ClienteOutputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Cliente {
    @Id
    @GeneratedValue
    int id;
    String nombre;
    String apellido;
    int edad;
    String email;
    int telefono;

    public Cliente(ClienteInputDTO clienteInputDTO) {
        this.nombre = clienteInputDTO.getNombre();
        this.apellido = clienteInputDTO.getApellido();
        this.edad = clienteInputDTO.getEdad();
        this.email = clienteInputDTO.getEmail();
        this.telefono = clienteInputDTO.getTelefono();
    }

    public ClienteOutputDTO clienteToClienteOutputDTO() {
        return new ClienteOutputDTO(
                this.id,
                this.nombre,
                this.apellido,
                this.edad,
                this.email,
                this.telefono
        );
    }

}
