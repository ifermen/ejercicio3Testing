package exception;

public class PersonaIncorrectoException extends Exception{

	private static final String MESAGE = "El nombre no puede estar vacio";
	
	public PersonaIncorrectoException() {
		this(MESAGE);
	}

	public PersonaIncorrectoException(String message) {
		super(message);
	}
	
}
