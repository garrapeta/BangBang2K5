package database;

import java.sql.*;
import java.util.*;

/**
 *@author Carlos Segovia
 */
 
 /**
 *Implementacinhn del interface SqlManager para MySql 4.1
 */
 
public class ImpSqlManager implements SqlManager{
	
	private final String dBName="base1";//nombre de la base de datos
		//podrnha ser un atributo y pasarse al constructor
	private Connection con;
	
	/**
	 *Constructor de la clase
	 *@param driver Nombre del driver que utilizaremos para conectar java con la base de datos
	 *@param url Url de la base de datos
	 *@param user Nombre del usuario con el que nos conectaremos a la base de datos
	 *@param password Password del usuario con el que nos conectaremos a la base de datos
	 */
	public ImpSqlManager(String driver,String url,String user, String password) throws DatabaseException{
		this.con=this.createConnection(driver,url,user,password);
	}
		
	
	public void initDB() throws DatabaseException{
		try{
			this.con.setAutoCommit(false);
			Statement s=this.con.createStatement();
			s.executeUpdate("CREATE TABLE USERS"+
					" (User char(12) PRIMARY KEY, "+
						"Password char(12) NOT NULL)");
			s.close();
			s=this.con.createStatement();
			s.executeUpdate("CREATE TABLE PLAYERS "+
						"(Name char(12) PRIMARY KEY, "+
						"User char(12) NOT NULL REFERENCES USERS (User), "+
						"Race tinyint NOT NULL, "+
						"Score int NOT NULL, "+
						"Wins smallint NOT NULL, "+
						"Loses smallint NOT NULL, "+
						"Ties smallint NOT NULL)");
			s.close();
			this.con.commit();
			this.con.setAutoCommit(true);
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());	
		}
	}
	
	/**
	 *Funcinhn que crea un nuevo usuario.
	 *Los parnhmetros deben tener una longitud entre doce y seis
	 *o la operacinhn se rechaza. Los parnhmetros se insertan
	 *en maynhsculas en la base de datos.
	 */
	public boolean createUser(String user, String password) throws DatabaseException{
		
		try{
			boolean aux=( (user.length()<13) && (user.length()>5) &&
						(password.length()<13) && (password.length()>5) &&
						(!(this.existsUser(user.toUpperCase())))  );
			if (aux){
				PreparedStatement s=this.con.prepareStatement(
						"INSERT INTO USERS(User, Password)"+
						" VALUES (?,?)");
				s.setString(1,user.toUpperCase());
				s.setString(2,password.toUpperCase());
				s.execute();
				s.clearParameters();
				s.close();
			}
			return aux;
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());	
		}
	}
	
	public boolean beginSession(String user, String password) throws DatabaseException {
		String aux;
		aux=this.getPassword(user.toUpperCase());
		return ((aux.equals(password.toUpperCase()))&&(!(aux.equals(""))));
	}
	
	public boolean changePassword(String user, String oldP, String newP) throws DatabaseException{

		try{
			boolean c;
			String aux;
			aux=this.getPassword(user.toUpperCase());
			if ((aux.equals(oldP.toUpperCase()))&&(!(aux.equals("")))){
				PreparedStatement s=this.con.prepareStatement("UPDATE USERS"+
						" SET Password=? "+
						"WHERE User=?");
				
				s.setString(1,newP.toUpperCase());
				s.setString(2,user.toUpperCase());
				s.execute();
				s.clearParameters();
				s.close();
				c=true;
			}	
			else{ 
				c=false;
			}
			return c;
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());
		}	
	}
	
	
	public boolean deleteUser(String user,String password) throws DatabaseException{
		try{	
			boolean truePassword;
			truePassword=this.beginSession(user,password);
			if (truePassword){
				this.con.setAutoCommit(false);
				PreparedStatement s=this.con.prepareStatement(
						"DELETE FROM USERS WHERE User=?");
				s.setString(1,user.toUpperCase());
				s.execute();
				s.clearParameters();
				s.close();
				s=this.con.prepareStatement(
						"DELETE FROM PLAYERS WHERE User=?");
				s.setString(1,user.toUpperCase());
				s.execute();
				s.clearParameters();
				s.close();
				this.con.commit();
				this.con.setAutoCommit(true);
			}
			return truePassword;
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());		
	    }
	}
	/**
	 *Funcinhn que crea un nuevo jugador para un usuario.
	 *Los parnhmetros deben tener una longitud de entre seis y doce
	 *o la operacinhn se rechaza. El nombre del jugador y del usuario
	 *se introduce en maynhsculas.
	 */
	public boolean createPlayer(String name,String user,int race) throws DatabaseException{
		try{
		 	boolean aux;
		 	if ((name.length()<6) || (name.length()>12)  ||
				(user.length()<6) || (user.length()>12))
		 		aux=false;
			else if(!(this.existsUser(user)))
				aux=false;
			else if (this.existsPlayer(name)) 
				aux=false;	
			else{
				PreparedStatement s=this.con.prepareStatement(
					"INSERT INTO PLAYERS(Name,User,Race,Score,Wins,Loses,Ties)"+
					"VALUES (?,?,?,0,0,0,0)");
				s.setString(1,name.toUpperCase());
				s.setString(2,user.toUpperCase());
				s.setInt(3,race);
				s.execute();
				s.clearParameters();
				s.close();
				aux=true;
			}
			return aux;
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());		
		}	
	}	
		
	
	public List getPlayers(String user) throws DatabaseException{
		try{	
			PreparedStatement s=this.con.prepareStatement(
					"SELECT * FROM PLAYERS WHERE User=?");
			s.setString(1,user.toUpperCase());
			ResultSet r = s.executeQuery();
			List l = this.resultSetToList(r);
			System.out.println(l.size());
			r.close();
			s.clearParameters();
			s.close();
			return l;
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());		
	    } 
	}


	public void deletePlayer(String name) throws DatabaseException{
		try{	
			PreparedStatement s=this.con.prepareStatement(
					"DELETE FROM PLAYERS WHERE Name=?");
			s.setString(1,name.toUpperCase());
			s.execute();
			s.clearParameters();
			s.close();
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());	
		} 	
	}

	
	//La raza, el nombre y el user no son modificables.
	public void updatePlayer(DBPlayer p) throws DatabaseException{
		try{
			PreparedStatement s=this.con.prepareStatement(
				"UPDATE PLAYERS SET Score= ?, "+
				"Wins= ?, Loses= ?, Ties= ?, "+
				"WHERE Name= ?");
			s.setInt(1,p.getScore());
			s.setShort(2,p.getWins());
			s.setShort(3,p.getLoses());
			s.setShort(4,p.getTies());
			s.setString(5,p.getName().toUpperCase());
			s.execute();
			s.clearParameters();
			s.close();
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());	
		}
	}		
	
	//actualiza la lista o ninguno
	public void updatePlayers(List listOfPlayers) throws DatabaseException{
		try{
			con.setAutoCommit(false);
			ListIterator it=listOfPlayers.listIterator();
			while (it.hasNext()){
				this.updatePlayer((DBPlayer)(it.next()));
			}	
			con.commit();
			con.setAutoCommit(true);
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());	
		}
	}	
	
	//Funcinhn auxiliar que crea una conexinhn con la base de datos 
	private Connection createConnection(String driver,String url,String user, String password) throws DatabaseException{
		try{
			//se registra el driver en la clase Class	
			Class.forName(driver).newInstance();
		}
		catch(ClassNotFoundException e){
			throw new DatabaseException("Driver no reconocido");	
		}
		catch(InstantiationException e){
			throw new DatabaseException("Driver no reconocido");	
		}
		catch(IllegalAccessException e){
			throw new DatabaseException("Driver no reconocido");	
		}
		try{
			//se hace la conexinhn a la base de datos con la url, usuario y 
			//contrasenha elegidas
			return DriverManager.getConnection(url,user,password);
		}
		catch(SQLException e){
			throw new DatabaseException("Problemas con la conexinhn; contrasenha, usuario"
				+ " o url de la base de datos errnhneo.");
		}	
	}
	
	
	//Funcinhn auxiliar que consulta la existencia de un usuario en la 
	//base de datos
	private boolean existsUser(String user) throws DatabaseException{
		 
		 try{
			PreparedStatement s=this.con.prepareStatement(
					"SELECT * FROM USERS WHERE User=?");
			s.setString(1,user.toUpperCase());
			ResultSet r=s.executeQuery();
			boolean aux=r.next();
			r.close();
			s.clearParameters();
			s.close();
			return aux;
		 }
		 catch(SQLException e){
		 	throw new DatabaseException(e.getMessage());	
		 }
	}
	
	
	//Funcinhn auxiliar que consulta la existencia de un jugador 
	//en la base de datos
	private boolean existsPlayer(String name) throws DatabaseException{
		try{
			PreparedStatement s=this.con.prepareStatement(
					"SELECT * FROM PLAYERS WHERE Name=?");
			s.setString(1,name.toUpperCase());
		 	ResultSet r=s.executeQuery();
		 	boolean aux= r.next();
		 	r.close();
		 	s.clearParameters();
		 	s.close();
		 	return aux;
		} 
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());	
	    }	
	}
	
	
	//Funcinhn auxiliar que devuelve el password de un usuario
	private String getPassword(String user) throws DatabaseException{
		try{
			String aux;
			if (this.existsUser(user)){
				PreparedStatement s=this.con.prepareStatement(
						"SELECT Password FROM USERS WHERE User=?");
				s.setString(1,user.toUpperCase());
				ResultSet r=s.executeQuery();
				r.next();
				aux = r.getString(1);
				r.close();
				s.clearParameters();
				s.close();
			}	
			else{ 
		 		aux = "";
			}
			return aux;
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());	
	 	} 
	}
	
	
	private List resultSetToList(ResultSet r)throws DatabaseException{
		try{
			DBPlayer p=null;
			List l=new ArrayList(5);
			while (r.next()){
				p=new ImpDBPlayer(r.getString("Name"),r.getByte("Race"));
				p.setScore(r.getInt("Score"));
				p.setWins(r.getShort("Wins"));
				p.setLoses(r.getShort("Loses"));
				p.setTies(r.getShort("Ties"));
				l.add(p);
			}
			return l;
		}
		catch(SQLException e){
			throw new DatabaseException(e.getMessage());
		}
	}	
}
