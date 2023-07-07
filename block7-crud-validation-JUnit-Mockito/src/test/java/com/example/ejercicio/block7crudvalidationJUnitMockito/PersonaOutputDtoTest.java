import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaOutputDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonaOutputDtoTest {

    @Test
    public void testMyMethod() {
        PersonaOutputDto outputDto = new PersonaOutputDto();
        outputDto.setId(1);
        outputDto.setNombre("John");
        outputDto.setEdad(30);
        outputDto.setPoblacion("City");

        // Lógica de prueba en la clase que utiliza PersonaOutputDto
        // ...
        // Por ejemplo, si tienes un método en MyClass que utiliza PersonaOutputDto
        // puedes invocarlo aquí y realizar las aserciones necesarias

        assertEquals(1, outputDto.getId());
        assertEquals("John", outputDto.getNombre());
        assertEquals(30, outputDto.getEdad());
        assertEquals("City", outputDto.getPoblacion());
    }
}
