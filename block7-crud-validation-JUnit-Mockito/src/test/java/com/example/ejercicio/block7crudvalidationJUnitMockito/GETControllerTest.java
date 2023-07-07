import com.example.ejercicio.block7crudvalidationJUnitMockito.application.PersonaServiceImpl;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.GETController;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaOutputDto;
import com.example.ejercicio.block7crudvalidationJUnitMockito.domain.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class GETControllerTest {

    @Mock
    private PersonaServiceImpl personaService;

    @InjectMocks
    private GETController getController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPersonaById_PersonaFound() {
        int id = 1;
        PersonaOutputDto expectedOutputDto = new PersonaOutputDto();

        when(personaService.getPersonaById(id)).thenReturn(expectedOutputDto);

        ResponseEntity<PersonaOutputDto> response = getController.getPersonaById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOutputDto, response.getBody());

        verify(personaService, times(1)).getPersonaById(id);
    }

    @Test
    public void testGetPersonaById_PersonaNotFound() {
        int id = 1;

        when(personaService.getPersonaById(id)).thenThrow(new RuntimeException());

        ResponseEntity<PersonaOutputDto> response = getController.getPersonaById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(personaService, times(1)).getPersonaById(id);
    }

    @Test
    public void testGetAllPersonas() {
        int pageNumber = 0;
        int pageSize = 4;
        List<PersonaOutputDto> expectedOutputDtos = new ArrayList<>();

        when(personaService.getAllPersonas(pageNumber, pageSize)).thenReturn(expectedOutputDtos);

        Iterable<PersonaOutputDto> actualOutputDtos = getController.getAllPersonas(pageNumber, pageSize);

        assertEquals(expectedOutputDtos, actualOutputDtos);

        verify(personaService, times(1)).getAllPersonas(pageNumber, pageSize);
    }

    @Test
    public void testGetPersonasByNombre() {
        String nombre = "John";
        List<Persona> expectedPersonas = new ArrayList<>();

        when(personaService.getPersonasByNombre(nombre)).thenReturn(expectedPersonas);

        ResponseEntity<List<Persona>> response = getController.getPersonasByNombre(nombre);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPersonas, response.getBody());

        verify(personaService, times(1)).getPersonasByNombre(nombre);
    }
}
