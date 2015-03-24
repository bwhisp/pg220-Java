package exceptions;

@SuppressWarnings("serial")
public class CommandeException extends Exception {

	public CommandeException() {
		System.out.println("Deux commandes différentes ne peuvent avoir des dimensions identiques");
	}
	
}