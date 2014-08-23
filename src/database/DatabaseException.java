package database;

/*
 * Created on 4-abr-2005
 */

/**
 * @author Carlos Segovia
 */
 
 /**
 * Excepcinhn para la base de datos   
 */
 
public class DatabaseException extends Exception{
	
	/**
	 *Constructor de la clase
	 */
	public DatabaseException(){
		super();	
	}
	
	
	/**
	 *Constructor de la clase
	 *@param s Mensaje asociado a la excepcinhn
	 */
	public DatabaseException(String s){
		super(s);	
	}		
}