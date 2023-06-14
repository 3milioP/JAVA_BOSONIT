package com.example.ejercicio.block7crudvalidation.application.estudiante;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteOutputSimpleDTO;
import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import com.example.ejercicio.block7crudvalidation.domain.Persona;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ejercicio.block7crudvalidation.repository.EstudianteRepository;
import com.example.ejercicio.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public EstudianteOutputSimpleDTO addEstudiante(EstudianteInputDTO estudianteInputDTO) {

        Persona persona = personaRepository
                .findById(estudianteInputDTO.getId_persona()).orElseThrow();
        Estudiante estudiante = new Estudiante(estudianteInputDTO);

            persona.setEstudiante(estudiante);
            estudiante.setId_persona(persona);

        return estudianteRepository.save(estudiante).estudianteToEstudianteSImpleOutputDTO();

}
    @Override
    public EstudianteOutputSimpleDTO getEstudianteById(String type, int id) {
        return estudianteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra el Estudiante con id " +id))
                .estudianteToEstudianteOutputDTO(type);
    }

    @Override
    public EstudianteOutputDTO getEstudianteByid2(int id) {
        return estudianteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("NO se encuentra el estudiante con id "+id))
                .estudianteFullDT();
    }

    @Override
    public Iterable<EstudianteOutputSimpleDTO> getAllEstudiantes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return estudianteRepository.findAll((pageRequest)).getContent().stream()
                .map((Estudiante::estudianteToEstudianteSImpleOutputDTO)).toList();
    }

    @Override
    public ResponseEntity<String> deleteEstudianteById(int id) {
        Optional<Estudiante> optionalEstudiante = estudianteRepository.findById(id);

        if (optionalEstudiante.isPresent()){
            Estudiante estudiante = optionalEstudiante.get();
            Persona persona = estudiante.getId_persona();

            persona.setEstudiante(null);

            estudianteRepository.deleteById(id);

            return ResponseEntity.ok().body("Estudiante con id "+ id + "ha sido borrado");
        } else {
            throw new EntityNotFoundException("No se encuentra al estudiante con id " + id + " en la base de datos");
        }
    }

    @Override
    public ResponseEntity<String> updateEstudiante(int id, EstudianteInputDTO estudianteInput) {
        Optional<Estudiante> optionalStudent = estudianteRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Estudiante student = optionalStudent.get();
            student.setId_persona(personaRepository.findById(estudianteInput.getId_persona()).get());
            student.setNum_hours_week(estudianteInput.getNum_hours_week());
            student.setComents(estudianteInput.getComents());
            student.setBranch(estudianteInput.getBranch());

            try {
               estudianteRepository.save(student);
                return ResponseEntity.ok().body("Estudiante con id: " + id + " ha sido actualizado");

            } catch (UnprocessableEntityException ex) {
                throw ex;
            }

        } else {
            throw new EntityNotFoundException("Estudiante con id: " + id + " no ha sido encontrado");
        }
    }

}