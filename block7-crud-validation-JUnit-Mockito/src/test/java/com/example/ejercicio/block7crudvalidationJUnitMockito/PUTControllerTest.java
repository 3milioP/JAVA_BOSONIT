import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.PUTController;
import com.example.ejercicio.block7crudvalidationJUnitMockito.domain.Persona;
import com.example.ejercicio.block7crudvalidationJUnitMockito.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PUTControllerTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PUTController putController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdatePersona_ValidInput() {
        int id = 1;
        Persona personaInput = new Persona();
        personaInput.setNombre("John");
        personaInput.setEdad(30);
        personaInput.setPoblacion("City");

        Persona existingPersona = new Persona();
        existingPersona.setId(id);

        when(personaRepository.findById(id)).thenReturn(Optional.of(existingPersona));
        when(personaRepository.save(any(Persona.class))).thenReturn(existingPersona);

        ResponseEntity<Object> response = putController.updatePersona(id, personaInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Persona);

        Persona updatedPersona = (Persona) response.getBody();
        assertEquals(personaInput.getNombre(), updatedPersona.getNombre());
        assertEquals(personaInput.getEdad(), updatedPersona.getEdad());
        assertEquals(personaInput.getPoblacion(), updatedPersona.getPoblacion());

        verify(personaRepository, times(1)).findById(id);
        verify(personaRepository, times(1)).save(any(Persona.class));
    }

    @Test
    void testUpdatePersona_InvalidInput() {
        int id = 1;
        Persona personaInput = new Persona();
        personaInput.setNombre(null);
        personaInput.setEdad(-10);
        personaInput.setPoblacion(null);

        Persona existingPersona = new Persona();
        existingPersona.setId(id);

        when(personaRepository.findById(id)).thenReturn(Optional.of(existingPersona));

        ResponseEntity<Object> response = putController.updatePersona(id, personaInput);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        verify(personaRepository, times(1)).findById(id);
        verify(personaRepository, never()).save(any(Persona.class));
    }

    @Test
    void testUpdatePersona_PersonaNotFound() {
        int id = 1;
        Persona personaInput = new Persona();
        personaInput.setNombre("John");
        personaInput.setEdad(30);
        personaInput.setPoblacion("City");

        when(personaRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = putController.updatePersona(id, personaInput);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(personaRepository, times(1)).findById(id);
        verify(personaRepository, never()).save(any(Persona.class));
    }
}
