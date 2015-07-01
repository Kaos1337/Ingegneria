package it.univr.is.database;

public class WrongParamNumberSQLException extends Exception {

	public WrongParamNumberSQLException(){
		super("Il numero di parametri attesi dalla query non combacia con il numero di "
				+ "parametri passati come argomento.");
	}
	
	public WrongParamNumberSQLException(String error){
		super(error);
	}
	
	public WrongParamNumberSQLException(int attesi, int passati){
		super("Il numero di parametri attesi dalla query non combacia con il numero di "
				+ "parametri passati come argomento. Attesi: " + attesi + " Passati: " + passati + ".");
	}
	
}
