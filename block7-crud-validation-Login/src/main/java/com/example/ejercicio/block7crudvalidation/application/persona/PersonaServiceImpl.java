package com.example.ejercicio.block7crudvalidation.application.persona;

import com.example.ejercicio.block7crudvalidation.application.estudiante.EstudianteService;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaDetailOutputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.persona.PersonaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.domain.Profesor;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ejercicio.block7crudvalidation.repository.EstudianteRepository;
import com.example.ejercicio.block7crudvalidation.repository.PersonaRepository;
import com.example.ejercicio.block7crudvalidation.repository.ProfesorRepository;
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

    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    EstudianteService estudianteService;


    @Override
    public void personaInicial() {
        Persona persona = new Persona();
        persona.setUsuario("admin");
        persona.setPassword("admin");
        persona.setName("admin");
        persona.setSurname("admin");

    }
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

    /*@Override
    public PersonaOutputDTO getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra el id " + id))
                .personaToPersonaOutputDTO();
    }*/

    @Override
    public PersonaDetailOutputDTO getPersonaById(int id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una persona con el ID " + id));

        PersonaDetailOutputDTO personaDTO = new PersonaDetailOutputDTO();
        personaDTO.setId_persona(persona.getId_persona());
        personaDTO.setUsuario(persona.getUsuario());
        personaDTO.setName(persona.getName());
        personaDTO.setSurname(persona.getSurname());
        personaDTO.setCompany_email(persona.getCompany_email());
        personaDTO.setPersonal_email(persona.getPersonal_email());
        personaDTO.setCity(persona.getCity());
        personaDTO.setActive(persona.getActive());
        personaDTO.setCreated_date(persona.getCreated_date());
        personaDTO.setImagen_url(persona.getImagen_url());
        personaDTO.setTermination_date(persona.getTermination_date());

        Optional<Profesor> optionalProfesor = profesorRepository.findByPersona(persona);
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findByPerson(persona);


        if (!optionalProfesor.isEmpty()) {
            Profesor profesor = optionalProfesor.get();
            personaDTO.setProfesor(profesor.profesorToProfesorOutputDTO());

        }

        // Verificar si es estudiante y agregar los datos de estudiante correspondientes
        if (!optionalEstudiante.isEmpty()) {
            Estudiante estudiante = optionalEstudiante.get();
            personaDTO.setEstudiante(estudiante.estudianteToEstudianteSImpleOutputDTO());
        }

        return personaDTO;
    }

    @Override
    public Iterable<PersonaOutputDTO> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent().stream()
                .map(Persona::personaToPersonaOutputDTO).toList();
    }

    @Override
    public Optional<Persona> getPersonasByUsuario(String usuario) {
        Optional<Persona> personas = personaRepository.findByUsuario(usuario);
        if (personas.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado: " + usuario);
        }
        return personas;
    }

    @Override
    public ResponseEntity<String> deletePersonaById(int id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (personaOptional.isPresent()) {
            Persona persona = personaOptional.get();

            boolean estaAsociada = false;

            Optional<Profesor> profesores = profesorRepository.findById(persona.getProfesor().getId_professor());
            if (!profesores.isEmpty()) {
                estaAsociada = true;
            } else {
                Optional<Estudiante> estudiantes = estudianteRepository.findById(persona.getEstudiante().getId_student());
                if (!estudiantes.isEmpty()) {
                    estaAsociada = true;
                }
            }

            if (estaAsociada) {
                return ResponseEntity.badRequest().body("La Persona está asociada a un Profesor o Estudiante y no se puede borrar");
            }

            // Si no está asociada, se puede eliminar
            personaRepository.deleteById(id);
            return ResponseEntity.ok().body("Persona con id " + id + " ha sido borrada");
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