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
    public PersonaDetailOutputDTO getPersonaById(int id, boolean includeProfesor) {
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

        // Verificar si es profesor y agregar los datos de profesor correspondientes
        if (includeProfesor && persona.getProfesor() != null) {
            EstudianteOutputDTO estudianteDTO = estudianteService.getEstudianteByid2(persona.getEstudiante().getId_student());
            personaDTO.setEstudiante(estudianteDTO);

            /*Profesor profesor = persona.getProfesor();
            ProfesorOutputDTO profesorDTO = new ProfesorOutputDTO();
            profesorDTO.setId_professor(profesor.getId_professor());
            profesorDTO.setComents(profesor.getComents());
            profesorDTO.setBranch(profesor.getBranch());
            // Agregar otros atributos de Profesor si los hay
            personaDTO.setProfesor(profesorDTO);*/
        }

        // Verificar si es estudiante y agregar los datos de estudiante correspondientes
        if (persona.getEstudiante() != null) {
            Estudiante estudiante = persona.getEstudiante();
            EstudianteOutputDTO estudianteDTO = new EstudianteOutputDTO();
            estudianteDTO.setId_student(estudiante.getId_student());
            estudianteDTO.setComents(estudiante.getComents());
            estudianteDTO.setNum_hours_week(estudiante.getNum_hours_week());
            estudianteDTO.setBranch(estudiante.getBranch());
            // Agregar otros atributos de Estudiante si los hay
            personaDTO.setEstudiante(estudianteDTO);
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
    public List<Persona> getPersonasByUsuario(String usuario) {
        List<Persona> personas = personaRepository.findByUsuario(usuario);
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

            if (personaInput.getId_profesor() != null) {
                Optional<Profesor> optionalProfesor = profesorRepository.findById(personaInput.getId_profesor().getId_professor());
                if (optionalProfesor.isPresent()) {
                    Profesor profesor = optionalProfesor.get();
                    persona.setProfesor(profesor);
                } else {
                    throw new EntityNotFoundException("Profesor con id: " + personaInput.getId_profesor() + " no ha sido encontrado");
                }
            }

            if (personaInput.getId_estudiante() != null) {
                Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(personaInput.getId_estudiante().getId_student());
                if (optionalEstudiante.isPresent()) {
                    Estudiante estudiante = optionalEstudiante.get();
                    persona.setEstudiante(estudiante);
                } else {
                    throw new EntityNotFoundException("Estudiante con id: " + personaInput.getId_estudiante() + " no ha sido encontrado");
                }
            }

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