package com.example.ejercicio.block7crudvalidation.application.estudiante_asignatura;

import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaInputDTO;
import com.example.ejercicio.block7crudvalidation.controller.dto.estudiante_asignatura.Estudiante_AsignaturaOutputDTO;
import com.example.ejercicio.block7crudvalidation.domain.Estudiante;
import com.example.ejercicio.block7crudvalidation.domain.Estudiante_Asignatura;
import com.example.ejercicio.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.ejercicio.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ejercicio.block7crudvalidation.repository.EstudianteRepository;
import com.example.ejercicio.block7crudvalidation.repository.Estudiante_AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstudianteAsignaturaServiceImpl implements Estudiante_AsignaturaService {

    @Autowired
    Estudiante_AsignaturaRepository estudianteAsignaturaRepository;
    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public Estudiante_AsignaturaOutputDTO addEstudianteAsignatura(Estudiante_AsignaturaInputDTO estudianteAsignatura) {
        return estudianteAsignaturaRepository.save(new Estudiante_Asignatura(estudianteAsignatura)).estudianteAsignaturaToEstudianteAsignaturaOutputDTO();

    }
    @Override
    public Estudiante_AsignaturaOutputDTO getEstudianteAsignaturaById(int id) {
        return estudianteAsignaturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encuentra la asignatura con id " +id))
                .estudianteAsignaturaToEstudianteAsignaturaOutputDTO();
    }

    @Override
    public Iterable<Estudiante_Asignatura> getAsignaturasEstudiante(int idEstudiante) {
        Optional<Estudiante> asignaturasEstudiante = estudianteRepository.findById(idEstudiante);

        if (asignaturasEstudiante.isPresent()) {
            Iterable<Estudiante_Asignatura> asignaturas = asignaturasEstudiante.get().getEstudianteAsignaturas();

            return asignaturas;
        } else {
            throw new EntityNotFoundException("Asignaturas de estudiante con id de estudiante: " + idEstudiante + " no han sido encontradas");
        }

    }

    @Override
    public Iterable<Estudiante_AsignaturaOutputDTO> getAllEstudiantesAsignatura(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return estudianteAsignaturaRepository.findAll((pageRequest)).getContent().stream()
                .map(Estudiante_Asignatura::estudianteAsignaturaToEstudianteAsignaturaOutputDTO).toList();
    }

    @Override
    public ResponseEntity<String> deleteEstudianteAsignaturaById(int id) {
        if (estudianteAsignaturaRepository.existsById(id)) {
            estudianteAsignaturaRepository.deleteById(id);

            return ResponseEntity.ok().body("Asignatura de estudiante con id "+ id + "ha sido borrada");
        } else {
            throw new EntityNotFoundException("No se encuentra la asignatura de estudiante con id " + id + " en la base de datos");
        }
    }

    @Override
    public ResponseEntity<String> updateEstudianteAsignatura(int id, Estudiante_AsignaturaInputDTO estudianteAsignaturaInputDTO) {
        Optional<Estudiante_Asignatura> optionalEstudianteAsignatura = estudianteAsignaturaRepository.findById(id);
        if (optionalEstudianteAsignatura.isPresent()) {
            Estudiante_Asignatura estudianteAsignatura = optionalEstudianteAsignatura.get();
            estudianteAsignatura.setId_student(estudianteAsignaturaInputDTO.getEstudiantes());
            estudianteAsignatura.setAsignatura(estudianteAsignaturaInputDTO.getAsignatura());
            estudianteAsignatura.setComents(estudianteAsignaturaInputDTO.getComents());
            estudianteAsignatura.setInitial_date(estudianteAsignaturaInputDTO.getInitial_date());
            estudianteAsignatura.setFinish_date(estudianteAsignaturaInputDTO.getFinish_date());

            try {
                estudianteAsignaturaRepository.save(estudianteAsignatura);
                return ResponseEntity.ok().body("Asignaturas de estudiante con id: " + id + " han sido actualizadas");

            } catch (UnprocessableEntityException ex) {
                throw ex;
            }

        } else {
            throw new EntityNotFoundException("Asignaturas de estudiante con id: " + id + " no han sido encontradas");
        }
    }
}
