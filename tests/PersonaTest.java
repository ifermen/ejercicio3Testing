

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

	@DisplayName("constructor Persona()")
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

	@DisplayName("constructor Persona(String nombre, int edad, char sexo)")
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

	@DisplayName("constructor Persona(String nombre, int edad, char sexo, double peso, double altura)")
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
	
	@DisplayName("exception PersonaIncorrectoException")
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

	@DisplayName("setNombre(String nombre)")
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

	@DisplayName("setEdad(int edad)")
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

	@DisplayName("setSexo(char sexo)")
	@ParameterizedTest
	@MethodSource("testSetSexoProvider")
	void testSetSexo(char nuevoSexo,char sexoEsperado) {
		sut.setSexo(nuevoSexo);
		assertEquals(nuevoSexo,sut.getSexo());
	}
	
	public static Stream<Arguments> testSetSexoProvider(){
		return Stream.of(
					Arguments.of('H','H'),
					Arguments.of('M','M'),
					Arguments.of('J','H'),
					Arguments.of('h','H'),
					Arguments.of('m','H')
				);
	}

	@DisplayName("setPeso(double peso)")
	@ParameterizedTest
	@MethodSource("testSetPesoProvider")
	void testSetPeso(double nuevoPeso) {
		sut.setPeso(nuevoPeso);
		assertEquals(nuevoPeso,sut.getPeso());
	}
	
	public static Stream<Arguments> testSetPesoProvider(){
		return Stream.of(
					Arguments.of(96.5)
				);
	}

	@DisplayName("setAltura(double altura)")
	@ParameterizedTest
	@MethodSource("testSetAlturaProvider")
	void testSetAltura(double nuevoAltura) {
		sut.setAltura(nuevoAltura);
		assertEquals(nuevoAltura,sut.getAltura());
	}
	
	public static Stream<Arguments> testSetAlturaProvider(){
		return Stream.of(
					Arguments.of(1.50)
				);
	}

	@DisplayName("calcularIMC():int")
	@ParameterizedTest
	@MethodSource("testCalcularIMCProvider")
	void testCalcularIMC(double peso, double altura,int expectedResult) {
		sut.setAltura(altura);
		sut.setPeso(peso);
		assertEquals(expectedResult,sut.calcularIMC());
	}
	
	public static Stream<Arguments> testCalcularIMCProvider(){
		return Stream.of(
					Arguments.of(80,1.80,Persona.PESO_IDEAL),
					Arguments.of(60,1.80,Persona.INFRAPESO),
					Arguments.of(80,1.60,Persona.SOBREPESO)
				);
	}

	@DisplayName("esMayorDeEdad():boolean")
	@ParameterizedTest
	@MethodSource("testEsMayorDeEdadProvider")
	void testEsMayorDeEdad(int edad,boolean esMayorEdad) {
		sut.setEdad(edad);
		assertEquals(esMayorEdad,sut.esMayorDeEdad());
	}
	
	public static Stream<Arguments> testEsMayorDeEdadProvider(){
		return Stream.of(
					Arguments.of(80,true),
					Arguments.of(18,true),
					Arguments.of(17,false),
					Arguments.of(5,false)
				);
	}

	@DisplayName("testToString():String")
	@ParameterizedTest
	@MethodSource("testToStringProvider")
	void testToString(String nombre,int edad,char sexo,double peso,double altura,String result) {
		try {
			sut = new Persona(nombre,edad,sexo,peso,altura);			
		}catch (PersonaIncorrectoException e) {
			e.printStackTrace();
		}
		assertEquals(result,sut.toString());
	}
	
	public static Stream<Arguments> testToStringProvider(){
		return Stream.of(
				//Da error devido a la aleatoriedad del DNI
					Arguments.of("Ivan",23,'H',80,1.80,"Informacion de la persona:\n"
			                + "Nombre: " + "Ivan" + "\n"
			                + "Sexo: " + "hombre" + "\n"
			                + "Edad: " + 23 + " años\n"
			                + "DNI: " + sut.getDNI() + "\n"
			                + "Peso: " + 80 + " kg\n"
			                + "Altura: " + 1.80 + " metros\n")
				);
	}

}
