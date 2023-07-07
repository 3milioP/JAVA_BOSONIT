import com.example.ejercicio.block7crudvalidationJUnitMockito.application.PersonaService;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.DELETEController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DELETEControllerTest {

    @Mock
    private PersonaService personaService;

    @InjectMocks
    private DELETEController deleteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeletePersonaById_PersonaFound() {
        int id = 1;

        ResponseEntity<String> response = deleteController.deletePersonaById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("persona with id: " + id + " was deleted", response.getBody());

        verify(personaService, times(1)).deletePersonaById(id);
    }

    @Test
    public void testDeletePersonaById_PersonaNotFound() {
        int id = 1;

        doThrow(new RuntimeException()).when(personaService).deletePersonaById(id);

        ResponseEntity<String> response = deleteController.deletePersonaById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(personaService, times(1)).deletePersonaById(id);
    }
}
