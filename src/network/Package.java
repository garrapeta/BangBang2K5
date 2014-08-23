/*
 * Created on 12-abr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package network;

import game.logicEngine.LogicActor;

import java.io.Serializable;

/**
 * @author usuario_local
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Package implements Serializable{
	char identifier;
	private String cadena;
	private LogicActor estado;
	 
	public Package(char ident,LogicActor est){
		identifier = ident;
		cadena=null;	
		estado = est;
	}
	public Package (String cad, LogicActor est){
		identifier=' ';
		cadena = cad;
		estado = est;
	}
	public Package (char ident,String cad){
		identifier =ident;
		cadena = cad;
		estado = null;
	}
	
	public Package (char ident,String cad,LogicActor est){
		identifier =ident;
		cadena = cad;
		estado = est;
	}
	
	public String dameCadena(){return cadena;}	
	
	/**
	 * @param actor
	 */

	public LogicActor dameEstado(){return estado;}


	public char getIdentifier() {return identifier;}
}