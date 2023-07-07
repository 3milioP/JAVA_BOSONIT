import com.example.ejercicio.block7crudvalidationJUnitMockito.application.PersonaService;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.POSTController;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaInputDto;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class POSTControllerTest {

    @Mock
    private PersonaService personaService;

    @InjectMocks
    private POSTController postController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPersona() {
        PersonaInputDto personaInputDto = new PersonaInputDto();
        personaInputDto.setNombre("John");

        PersonaOutputDto expectedOutputDto = new PersonaOutputDto();

        when(personaService.addPersona(any(PersonaInputDto.class))).thenReturn(expectedOutputDto);

        ResponseEntity<PersonaOutputDto> response = postController.addPersona(personaInputDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedOutputDto, response.getBody());
        assertEquals(URI.create("/persona"), response.getHeaders().getLocation());

        verify(personaService, times(1)).addPersona(personaInputDto);
    }
}
