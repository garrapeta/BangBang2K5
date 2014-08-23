package database;

import java.util.List;

/**
 *@author Carlos Segovia
 */
 
 /**
 *Interface para el manejo de los casos de uso mnhs corrientes 
 *para nuestra base de datos
 */
 
public interface SqlManager{
	
	
	/**
	 *Funcinhn que inicia las tablas de la base de datos
	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public void initDB() throws DatabaseException;
			
	
	/**
	 *Funcinhn que crea un nuevo usuario.
	 *@return Devuelve si se ha podido completar la operacinhn
	 *@param user Nombre del usuario que se crea
	 *@param password Password asociado al usuario que se crea
	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public boolean createUser(String user, String password) throws DatabaseException;
	
	
	/**
	 *Funcinhn que sirve para entrar en una sesinhn.
	 *@return Devuelve si se ha podido completar la operacinhn
	 *@param user Nombre del usuario que intenta entrar
	 *@param password Password introducido
	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public boolean beginSession(String user, String password) throws DatabaseException;	
	
	
	/**
	 *Funcinhn que cambia el password de un usuario.
	 *@return Devuelve si se ha podido completar la operacinhn
	 *@param user Nombre del usuario cuyo password se quiere cambiar
	 *@param oldP Password antiguo
 	 *@param newP Password nuevo
 	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public boolean changePassword(String user, String oldP, String newP) throws DatabaseException;
	
	
	/**
	 *Funcinhn que elimina un usuario y todos sus personajes de la base de datos.
	 *@return Devuelve si se ha podido completar la operacinhn
	 *@param user Nombre del usuario a eliminar
	 *@param password Password del usuario a eliminar
	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public boolean deleteUser(String user,String password) throws DatabaseException;

	
	/**
	 *Funcinhn que crea un nuevo jugador para un usuario.
	 *@return Devuelve si se ha podido completar la operacinhn
	 *@param user Nombre del usuario propietario del jugador a crear
	 *@param name Nombre del jugador que se crea
	 *@param race Entero correspondiente a la raza que se crea
	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public boolean createPlayer(String user,String name,int race) throws DatabaseException;
	
	
	/**
	 *Funcinhn que devuelve todos los jugadores de un usuario.
	 *@return Devuelve todos los jugadores del usuario user
	 *@param user Nombre del usuario cuyos jugadores se quieren determinar
	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public List getPlayers(String user) throws DatabaseException;
	
		
	/**
	 *Funcinhn que elimina un jugador.
	 *@return Devuelve si se ha podido completar la operacinhn
	 *@param name Nombre del jugador que se elimina
	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public void deletePlayer(String name) throws DatabaseException;
		
	
	/**
	 *Funcinhn que actualiza el estado de un jugador en la base de datos.
	 *Se usarnh para actualizar un jugador de forma separada a otro.
	 *@return Devuelve si se ha podido completar la operacinhn
	 *@param p Jugador que se actualiza
	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public void updatePlayer(DBPlayer p) throws DatabaseException;
	
	
	/**
	 *Funcinhn que actualiza el estado de una lista de jugadores en la base de datos.
	 *@return Devuelve si se ha podido completar la operacinhn
	 *@param listOfPlayers Lista de jugadores a actualizar
	 *@throws DatabaseException Excepcinhn que tiene que ver con la conexinhn o una sentencia SQL incorrecta
	 */
	public void updatePlayers(List listOfPlayers) throws DatabaseException;	
}
