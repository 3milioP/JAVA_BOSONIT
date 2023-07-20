package com.example.ejercicio.block7crudvalidation.security;

import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements CommandLineRunner {

    private final PersonaRepository personaRepository;

    @Autowired
    public DatosIniciales(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        insertarRegistrosDeEjemplo();
    }

    private void insertarRegistrosDeEjemplo() {
        // Datos de ejemplo
        PersonaInputDTO person1 = new PersonaInputDTO(null,null,"paco","paco","name1","surname1","company_email1","personal_email1","city1",true,null,"imagen_url1",null,true);
        PersonaInputDTO person2 = new PersonaInputDTO(null,null,"lolo","lolo","name2","surname2","company_email2","personal_email2","city2",true,null,"imagen_url2",null,false);

        Persona persona1 = new Persona(person1);
        Persona persona2 = new Persona(person2);

        // Guardar los registros en la base de datos
        personaRepository.save(persona1);
        personaRepository.save(persona2);
    }
}
