package com.example.ejercicio.block7crudvalidation.application.persona;

import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ejercicio.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO persona) {
        Persona nuevaPersona = new Persona(persona);

        try {
            nuevaPersona.validarPersona();
        } catch (UnprocessableEntityException ex) {
            throw ex;
        }

        return personaRepository.save(nuevaPersona).personaToPersonaOutputDTO();
    }

    @Override
    public PersonaOutputDTO getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra el id " + id))
                .personaToPersonaOutputDTO();
    }

    @Override
    public Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent().stream()
                .map(Persona::personaToPersonaOutputDTO).toList();
    }

    @Override
    public List<Persona> getPersonasByUsuario(String usuario) {
        List<Persona> personas = personaRepository.findByUsuario(usuario);
        if (personas.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado: " + usuario);
        }
        return personas;
    }

    @Override
    public ResponseEntity<String> deletePersonaById(int id) {
        if (personaRepository.existsById(id)) {
            personaRepository.deleteById(id);

            return ResponseEntity.ok().body("Persona con id "+ id + "ha sido borrada");
        } else {
            throw new EntityNotFoundException("No se encuentra a la Persona con id " + id + " en la base de datos");
        }
    }

    @Override
    public ResponseEntity<String> updatePersona(int id, PersonaInputDTO personaInput) {
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        if (optionalPersona.isPresent()) {
            Persona persona = optionalPersona.get();
            // Actualizar los valores de la persona con los valores recibidos en personaInput
            persona.setUsuario(personaInput.getUsuario());
            persona.setPassword(personaInput.getPassword());
            persona.setName(personaInput.getName());
            persona.setSurname(personaInput.getSurname());
            persona.setCompany_email(personaInput.getCompany_email());
            persona.setPersonal_email(personaInput.getPersonal_email());
            persona.setCity(personaInput.getCity());
            persona.setActive(personaInput.getActive());
            persona.setCreated_date(personaInput.getCreated_date());
            persona.setImagen_url(personaInput.getImagen_url());
            persona.setTermination_date(personaInput.getTermination_date());

            try {
                persona.validarPersona();
                personaRepository.save(persona);
                return ResponseEntity.ok().body("Persona con id: " + id + " ha sido actualizada");

            } catch (UnprocessableEntityException ex) {
                throw ex;
            }

        } else {
            throw new EntityNotFoundException("Persona con id: " + id + " no ha sido encontrada");
        }
    }
}