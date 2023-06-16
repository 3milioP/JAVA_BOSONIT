package com.example.ejercicio.block7crudvalidation.domain;

import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaDetailOutputDTO;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaOutputDTO;

import java.util.Date;

@Entity
@Table(name = "Persona")
@Getter
@Setter
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    @Column(name="id_persona")
    int id_persona;
    @Size(min=6, max=10)
    @OneToOne
    @JoinColumn(name="person")
    Estudiante estudiante;
    @OneToOne
    @JoinColumn(name="persona")
    Profesor profesor;
    @NotNull
    String usuario;
    @NotNull
    String password;
    @NotNull
    String name;
    String surname;
    @NotNull
    String company_email;
    @NotNull
    String personal_email;
    @NotNull
    String city;
    @NotNull
    Boolean active;
    @NotNull
    Date created_date;
    String imagen_url;
    Date termination_date;

    public void validarPersona() throws UnprocessableEntityException {
        if (usuario == null) { throw new UnprocessableEntityException("Usuario no puede ser nulo"); }
        if (usuario.length() < 6) { throw new UnprocessableEntityException("El nombre debe contener entre 6 y 10 caracteres");}
        if (usuario.length() > 10) { throw new UnprocessableEntityException("Longitud de usuario no puede ser mayor a 10 carácteres");}
        if (password == null) { throw new UnprocessableEntityException("Debes introducir una contraseña");}
        if (name == null) { throw new UnprocessableEntityException("Debes introducir un nombre"); }
        if (company_email == null) { throw new UnprocessableEntityException("Debes introducir un mail de compañía"); }
        if (personal_email == null) { throw new UnprocessableEntityException("Debes introducir un mail personal"); }
        if (city == null) { throw new UnprocessableEntityException("Debes introducir una ciudad"); }
        if (active == null) { throw new UnprocessableEntityException("Debes introducir un valor"); }
        if (created_date == null) { throw new UnprocessableEntityException("Debes introducir una fecha"); }
    }

    public Persona(PersonaInputDTO personaInputDTO) {
        this.usuario = personaInputDTO.getUsuario();
        this.password = personaInputDTO.getPassword();
        this.name = personaInputDTO.getName();
        this.surname = personaInputDTO.getSurname();
        this.company_email = personaInputDTO.getCompany_email();
        this.personal_email = personaInputDTO.getPersonal_email();
        this.city = personaInputDTO.getCity();
        this.active = personaInputDTO.getActive();
        this.created_date = personaInputDTO.getCreated_date();
        this.imagen_url = personaInputDTO.getImagen_url();
        this.termination_date = personaInputDTO.getTermination_date();
    }

    public PersonaOutputDTO personaToPersonaOutputDTO() {
        return new PersonaOutputDTO(
                this.id_persona,
                this.usuario,
                this.name,
                this.surname,
                this.company_email,
                this.personal_email,
                this.city,
                this.active,
                this.created_date,
                this.imagen_url,
                this.termination_date
        );
    }

}