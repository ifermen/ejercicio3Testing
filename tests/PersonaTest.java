

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exception.PersonaIncorrectoException;


class PersonaTest {
	
	private static Persona sut;
	
	@BeforeAll
	public static void setup() {
		try {
			sut=new Persona("Pepe",20,'H',85,1.75);			
		}catch(Exception e) {
			
		}
	}

	@DisplayName("Constructor Persona()")
	@Test
	void testPersona() {
		try {
			Persona sut = new Persona();
			assertEquals(sut.getNombre(),"");
			assertEquals(sut.getEdad(),0);
			assertEquals(sut.getSexo(),Persona.getSexoDef());
			assertEquals(sut.getPeso(),0);
			assertEquals(sut.getAltura(),0);
		} catch (PersonaIncorrectoException e) {
			e.printStackTrace();
		}
	}

	@DisplayName("Constructor Persona(String nombre, int edad, char sexo)")
	@ParameterizedTest
	@MethodSource("testPersonaStringIntCharProvider")
	void testPersonaStringIntChar(String nombre,int edad,char sexo,char sexoCorrecto) {
		try {
			Persona sut=new Persona(nombre,edad,sexo);
			assertEquals(sut.getNombre(),nombre);
			assertEquals(sut.getEdad(),edad);
			assertEquals(sut.getSexo(),sexoCorrecto);
			assertEquals(sut.getPeso(),0);
			assertEquals(sut.getAltura(),0);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stream<Arguments> testPersonaStringIntCharProvider(){
		return Stream.of(
				//Testeo de nombre
					Arguments.of("Pepe",50,'H','H'),
					Arguments.of("",50,'H','H'),
					Arguments.of("Pepe",0,'H','H'),
				//Testeo del género
					Arguments.of("Pepe",50,'h','H'),
					Arguments.of("Pepe",50,'m','H'),
					Arguments.of("Pepe",50,'M','M'),
					Arguments.of("Pepe",50,'2','H')
				);
	}

	@DisplayName("Constructor Persona(String nombre, int edad, char sexo, double peso, double altura)")
	@ParameterizedTest
	@MethodSource("testPersonaStringIntCharDoubleDoubleProvider")
	void testPersonaStringIntCharDoubleDouble(String nombre,int edad,char sexo,double peso,double altura,char sexoCorrecto) {
		try {
			Persona sut=new Persona(nombre,edad,sexo,peso,altura);
			assertEquals(sut.getNombre(),nombre);
			assertEquals(sut.getEdad(),edad);
			assertEquals(sut.getSexo(),sexoCorrecto);
			assertEquals(sut.getPeso(),peso);
			assertEquals(sut.getAltura(),altura);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stream<Arguments> testPersonaStringIntCharDoubleDoubleProvider(){
		return Stream.of(
				//Testeo de nombre
					Arguments.of("Pepe",50,'H',80,1.81,'H'),
					Arguments.of("",50,'H',80,1.81,'H'),
				//Testeo de peso y altura
					Arguments.of("Pepe",50,'H',0,1.81,'H'),
					Arguments.of("Pepe",50,'H',80,0,'H'),
					Arguments.of("Pepe",50,'H',0,0,'H'),
					Arguments.of("Pepe",0,'H',80,1.81,'H'),
				//Testeo del género
					Arguments.of("Pepe",50,'h',80,1.81,'H'),
					Arguments.of("Pepe",50,'m',80,1.81,'H'),
					Arguments.of("Pepe",50,'M',80,1.81,'M'),
					Arguments.of("Pepe",50,'2',80,1.81,'H')
				);
	}
	
	@DisplayName("Exception PersonaIncorrectoException")
	@ParameterizedTest
	@MethodSource("testPersonaIncorrectoExceptionProvider")
	void testPersonaIncorrectoException(String nombre,int edad,char sexo,double peso,double altura) {
		assertThrows(PersonaIncorrectoException.class, () -> {
			new Persona(nombre,edad,sexo,peso,altura);
		});
	}
	
	public static Stream<Arguments> testPersonaIncorrectoExceptionProvider(){
		return Stream.of(
				//Testeo nombre incorrecto
					Arguments.of(null,50,'H',80,1.81,'H'),
				//Testeo edad incorrecta
					Arguments.of("Pepe",-50,'H',80,1.81,'H'),
				//Testeo altura incorrecta
					Arguments.of("Pepe",-50,'H',-5,1.81,'H'),
				//Testeo peso incorrecta
					Arguments.of("Pepe",-50,'H',80,-1.81,'H')
				);
	}

	@DisplayName("SetNombre(String nombre)")
	@ParameterizedTest
	@MethodSource("testSetNombreProvider")
	void testSetNombre(String nuevoNombre) {
		sut.setNombre(nuevoNombre);
		assertEquals(nuevoNombre,sut.getNombre());
	}
	
	public static Stream<Arguments> testSetNombreProvider(){
		return Stream.of(
					Arguments.of("Manolo")
				);
	}

	@DisplayName("SetEdad(int edad)")
	@ParameterizedTest
	@MethodSource("testSetEdadProvider")
	void testSetEdad(int nuevaEdad) {
		sut.setEdad(nuevaEdad);
		assertEquals(nuevaEdad,sut.getEdad());
	}
	
	public static Stream<Arguments> testSetEdadProvider(){
		return Stream.of(
					Arguments.of(25)
				);
	}

	@Test
	void testSetSexo() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPeso() {
		fail("Not yet implemented");
	}

	@Test
	void testSetAltura() {
		fail("Not yet implemented");
	}

	@Test
	void testCalcularIMC() {
		fail("Not yet implemented");
	}

	@Test
	void testEsMayorDeEdad() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
