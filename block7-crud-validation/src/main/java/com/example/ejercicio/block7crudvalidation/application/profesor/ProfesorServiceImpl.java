package com.example.ejercicio.block7crudvalidation.application.profesor;

import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
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
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public ProfesorOutputDTO addProfesorToPersona(ProfesorInputDTO profesorInputDTO) {

        Persona persona = personaRepository.findById(profesorInputDTO.getId_persona()).orElseThrow();
        Profesor profesor = new Profesor(profesorInputDTO);

        persona.setProfesor(profesor);
        profesor.setId_persona((persona));

        return profesorRepository.save(profesor).profesorToProfesorOutputDTO();

    }
    @Override
    public void addEstudianteToProfesor(int student_id, int profesor_id) {
        Estudiante estudiante = estudianteRepository.findById(student_id).orElseThrow();
        Profesor profesor = profesorRepository.findById(profesor_id).orElseThrow();

        estudiante.setId_profesor(profesor);
        profesor.getId_estudiante().add(estudiante);
    }
    @Override
    public ProfesorOutputDTO getProfesorById(int id) {
        return profesorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra el profesir con id " +id))
                .profesorToProfesorOutputDTO();
    }

    @Override
    public Iterable<ProfesorOutputDTO> getAllProfesores(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return profesorRepository.findAll((pageRequest)).getContent().stream()
                .map((Profesor::profesorToProfesorOutputDTO)).toList();
    }

    @Override
    public ResponseEntity<String> deleteProfesorById(int id) {
        Optional<Profesor> optionalProfesor = profesorRepository.findById(id);

        if (optionalProfesor.isPresent()){
            Profesor profesor = optionalProfesor.get();
            Persona persona = profesor.getId_persona();

            persona.setProfesor(null);

            profesorRepository.deleteById(id);
            return ResponseEntity.ok().body("Profesor con id "+ id + "ha sido borrado");
        } else {
            throw new EntityNotFoundException("No se encuentra al profesor con id " + id + " en la base de datos");
        }
    }

    @Override
    public ResponseEntity<String> updateProfesor(int id, ProfesorInputDTO profesorInput) {
        Optional<Profesor> optionalProfesor = profesorRepository.findById(id);
        Optional<Persona> optionalPersona = personaRepository.findById(profesorInput.getId_persona());

        if (optionalProfesor.isPresent()) {
            Profesor profesor = optionalProfesor.get();
            profesor.setComents(profesorInput.getComents());
            profesor.setBranch(profesorInput.getBranch());

            if (optionalPersona.isPresent()) {
                profesor.setId_persona(optionalPersona.get());
            }


            try {
                profesorRepository.save(profesor);
                return ResponseEntity.ok().body("Profesor con id: " + id + " ha sido actualizado");

            } catch (UnprocessableEntityException ex) {
                throw ex;
            }

        } else {
            throw new EntityNotFoundException("Profesor con id: " + id + " no ha sido encontrado");
        }
    }
}
