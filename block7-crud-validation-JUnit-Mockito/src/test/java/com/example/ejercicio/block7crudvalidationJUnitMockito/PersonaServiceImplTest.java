package com.example.ejercicio.block7crudvalidationJUnitMockito;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaInputDto;
import com.example.ejercicio.block7crudvalidationJUnitMockito.controller.dto.PersonaOutputDto;
import com.example.ejercicio.block7crudvalidationJUnitMockito.domain.Persona;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import com.example.ejercicio.block7crudvalidationJUnitMockito.application.PersonaServiceImpl;
import com.example.ejercicio.block7crudvalidationJUnitMockito.repository.PersonaRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PersonaServiceImplTest {

	@Mock
	PersonaRepository personaRepositoryMock;

	@InjectMocks
	PersonaServiceImpl personaServiceMock;

	@Test
	public void testAddPersona() {
// Datos de prueba
		PersonaInputDto inputDto = new PersonaInputDto(1,"Emilio",35,"Sevilla");

// Configuración del mock
		Persona personaGuardada = new Persona(1, 35,"Emilio","Sevilla");
		when(personaRepositoryMock.save(any(Persona.class))).thenReturn(personaGuardada);

// Llamada al método que quieres probar
		PersonaOutputDto outputDto = personaServiceMock.addPersona(inputDto);

// Verificar el resultado
		assertEquals(personaGuardada.personaToPersonaOutputDto(), outputDto);

// Verificar las interacciones con el mock
		verify(personaRepositoryMock).save(any(Persona.class));
	}

	@Test
	public void testGetPersonaById() {
// Datos de prueba
		int id = 1;
		Persona personaEncontrada = new Persona(id, 35,"Emilio","Sevilla");

// Configuración del mock
		when(personaRepositoryMock.findById(id)).thenReturn(Optional.of(personaEncontrada));

// Llamada al método que quieres probar
		PersonaOutputDto outputDto = personaServiceMock.getPersonaById(id);

// Verificar el resultado
		assertEquals(personaEncontrada.personaToPersonaOutputDto(), outputDto);

// Verificar las interacciones con el mock
		verify(personaRepositoryMock).findById(id);
	}

	@Test
	public void testDeletePersonaById() {
// Datos de prueba
		int id = 1;
		Persona personaEncontrada = new Persona(id, 35,"Emilio","Sevilla");

// Configuración del mock
		when(personaRepositoryMock.findById(id)).thenReturn(Optional.of(personaEncontrada));

// Llamada al método que quieres probar
		personaServiceMock.deletePersonaById(id);

// Verificar las interacciones con el mock
		verify(personaRepositoryMock).findById(id);
		verify(personaRepositoryMock).deleteById(id);
	}

	@Test
	public void testGetAllPersonas() {
// Datos de prueba
		int pageNumber = 0;
		int pageSize = 10;
		List<Persona> personas = Arrays.asList(
				new Persona(1, 35,"Emilio","Sevilla"),
				new Persona(2, 40,"Paco","Albacete")
		);

// Configuración del mock
		when(personaRepositoryMock.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(personas));

// Llamada al método que quieres probar
		Iterable<PersonaOutputDto> output = personaServiceMock.getAllPersonas(pageNumber, pageSize);

// Verificar el resultado
		List<PersonaOutputDto> outputList = StreamSupport.stream(output.spliterator(), false)
				.collect(Collectors.toList());
		assertEquals(personas.size(), outputList.size());

// Verificar las interacciones con el mock
		verify(personaRepositoryMock).findAll(any(PageRequest.class));
	}

	@Test
	public void testUpdatePersona() {
// Datos de prueba
		int id = 1;
		PersonaInputDto inputDto = new PersonaInputDto(1,"Emilio",35,"Sevilla");
		Persona personaEncontrada = new Persona(id,35,"Emilio","Sevilla");

// Configuración del mock
		when(personaRepositoryMock.findById(id)).thenReturn(Optional.of(personaEncontrada));
		when(personaRepositoryMock.save(any(Persona.class))).thenReturn(personaEncontrada);

// Llamada al método que quieres probar
		PersonaOutputDto outputDto = personaServiceMock.updatePersona(id, inputDto);

// Verificar el resultado
		assertEquals(personaEncontrada.personaToPersonaOutputDto(), outputDto);

// Verificar las interacciones con el mock
		verify(personaRepositoryMock).findById(id);
		verify(personaRepositoryMock).save(any(Persona.class));
	}

	@Test
	public void testGetPersonasByNombre() {
// Datos de prueba
		String nombre = "John Doe";
		List<Persona> personasEncontradas = Arrays.asList(
				new Persona(1, 35,"Emilio","Sevilla"),
				new Persona(2, 35,"Emilio","Sevilla")
		);

// Configuración del mock
		when(personaRepositoryMock.findByNombre(nombre)).thenReturn(personasEncontradas);

// Llamada al método que quieres probar
		List<Persona> output = personaServiceMock.getPersonasByNombre(nombre);

// Verificar el resultado
		assertEquals(personasEncontradas.size(), output.size());

// Verificar las interacciones con el mock
		verify(personaRepositoryMock).findByNombre(nombre);
	}
}