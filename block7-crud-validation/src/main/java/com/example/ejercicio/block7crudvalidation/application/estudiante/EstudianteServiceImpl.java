package com.example.ejercicio.block7crudvalidation.application.estudiante;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante.EstudianteInputDTO;
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

import static java.util.stream.Collectors.toList;

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
        estudianteRepository.save(estudiante);

        return new EstudianteOutputSimpleDTO(estudiante.getId_student(), estudiante.getId_persona().getId_persona(), estudiante.getNum_hours_week(),
        estudiante.getComents(), estudiante.getBranch());

}
    @Override
    public EstudianteOutputSimpleDTO getEstudianteById(String type, String id) {
        return estudianteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra el Estudiante con id " +id))
                .estudianteToEstudianteOutputDTO(type);
    }

    @Override
    public Iterable<EstudianteOutputSimpleDTO> getAllEstudiantes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return estudianteRepository.findAll((pageRequest)).getContent().stream()
                .map((Estudiante::estudianteToEstudianteSImpleOutputDTO)).toList();
    }

    @Override
    public ResponseEntity<String> deleteEstudianteById(String id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);

            return ResponseEntity.ok().body("Estudiante con id "+ id + "ha sido borrado");
        } else {
            throw new EntityNotFoundException("No se encuentra al estudiante con id " + id + " en la base de datos");
        }
    }

    @Override
    public ResponseEntity<String> updateEstudiante(String id, EstudianteInputDTO estudianteInput) {
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