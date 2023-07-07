package com.example.ejercicio.block7crudvalidationJUnitMockito;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaInputDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonaInputDtoTest {

    @Test
    public void testMyMethod() {
        PersonaInputDto inputDto = new PersonaInputDto();
        inputDto.setId(1);
        inputDto.setNombre("John");
        inputDto.setEdad(30);
        inputDto.setPoblacion("City");

        // Lógica de prueba en la clase que utiliza PersonaInputDto
        // ...
        // Por ejemplo, si tienes un método en MyClass que utiliza PersonaInputDto
        // puedes invocarlo aquí y realizar las aserciones necesarias

        assertEquals(1, inputDto.getId());
        assertEquals("John", inputDto.getNombre());
        assertEquals(30, inputDto.getEdad());
        assertEquals("City", inputDto.getPoblacion());
    }
}
