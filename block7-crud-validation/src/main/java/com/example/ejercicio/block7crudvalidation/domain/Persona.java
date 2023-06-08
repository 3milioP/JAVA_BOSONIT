package com.example.ejercicio.block7crudvalidation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.PersonaOutputDTO;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    int id_persona;
    @Size(min=6, max=10)
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

    public void validarPersona() throws Exception {
        if (usuario == null) { throw new Exception("Usuario no puede ser nulo"); }
        if (usuario.length() < 6) { throw new Exception("El nombre debe contener entre 6 y 10 caracteres");}
        if (usuario.length() > 10) { throw new Exception("Longitud de usuario no puede ser mayor a 10 carácteres");}
        if (password == null) { throw new Exception("Debes introducir una contraseña");}
        if (name == null) { throw new Exception("Debes introducir un nombre"); }
        if (company_email == null) { throw new Exception("Debes introducir un mail de compañía"); }
        if (personal_email == null) { throw new Exception("Debes introducir un mail personal"); }
        if (city == null) { throw new Exception("Debes introducir una ciudad"); }
        if (active == null) { throw new Exception("Debes introducir un valor"); }
        if (created_date == null) { throw new Exception("Debes introducir una fecha"); }
    }

    public Persona(PersonaInputDTO personaInputDTO) throws Exception {
        this.id_persona = personaInputDTO.getId_persona();
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
        validarPersona();
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