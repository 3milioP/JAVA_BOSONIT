import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaOutputDto;
import com.example.ejercicio.block7crudvalidationJUnitMockito.domain.Persona;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonaTest {

    @Test
    public void testPersonaToPersonaOutputDto() {
        Persona persona = new Persona();
        persona.setId(1);
        persona.setNombre("John");
        persona.setEdad(30);
        persona.setPoblacion("City");

        PersonaOutputDto expectedOutputDto = new PersonaOutputDto(1, "John", 30, "City");

        PersonaOutputDto actualOutputDto = persona.personaToPersonaOutputDto();

        assertEquals(expectedOutputDto, actualOutputDto);
    }
}
