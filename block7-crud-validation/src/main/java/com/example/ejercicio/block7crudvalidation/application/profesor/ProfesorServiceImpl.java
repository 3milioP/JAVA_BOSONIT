package com.example.ejercicio.block7crudvalidation.application.profesor;

import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.profesor.ProfesorOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Profesor;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ejercicio.block7crudvalidation.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public ProfesorOutputDTO addProfesor(ProfesorInputDTO profesor) {
        return profesorRepository.save(new Profesor(profesor)).profesorToProfesorOutputDTO();

    }
    @Override
    public ProfesorOutputDTO getProfesorById(String id) {
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
    public ResponseEntity<String> deleteProfesorById(String id) {
        if (profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);

            return ResponseEntity.ok().body("Profesor con id "+ id + "ha sido borrado");
        } else {
            throw new EntityNotFoundException("No se encuentra al profesor con id " + id + " en la base de datos");
        }
    }

    @Override
    public ResponseEntity<String> updateProfesor(String id, ProfesorInputDTO profesorInput) {
        Optional<Profesor> optionalProfesor = profesorRepository.findById(id);
        if (optionalProfesor.isPresent()) {
            Profesor profesor = optionalProfesor.get();
            profesor.setId_persona(profesorInput.getId_persona());
            profesor.setComents(profesorInput.getComents());
            profesor.setBranch(profesorInput.getBranch());

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
