package exceptions;

@SuppressWarnings("serial")
public class DimException extends Exception{

	public DimException() {
		System.out.println("La longueur doit obligatoirement être supérieure ou égale à la largeur.");
	}

}