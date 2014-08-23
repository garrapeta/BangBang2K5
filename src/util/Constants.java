/*
 * Created on 08-may-2005
 *
 */
package util;

/**
 * @author Balder
 *
 */
public class Constants {
	
	//ctes de los estados en los que puede estar un turno
    public static final int PLAYING = 0;
    public static final int ESCAPING = 1;
    public static final int END = 2;
    public static final int INTERLUDE = 3;
    public static final int END_TURN_TIME = 4;

    //ctes de los tiempos de turno
    public static final int ESCAPE_TIME = 3;
    public static final int TURN_TIME = 30;
    public static final int INTERLUDE_TIME = 1;
    
    //ctes de resultado de partida
    public static final int NO_WINNER = -1;
    public static final int DRAW_MATCH = 0;
    public static final int WINNER = 1;
	
	//constantes de los valores maximos de viento de escenario
	public static final float ELEURON_MAX_WIND = 4f;
	public static final float NODRUBAZ_MAX_WIND = 0;
	public static final float OCB5_MAX_WIND = 2f;
	public static final float SILICOSIS_MAX_WIND = 3f;
	public static final float ASTEROIDS_MAX_WIND = 3f;
	
	//constantes de nombre de escenario
	public static final int ELEURON_STAGE = 1;//0
	public static final int NODRUBAZ_STAGE = 3;
	public static final int OCB5_STAGE = 4;
	public static final int SILICOSIS_STAGE = 2;
	public static final int ASTEROIDS_STAGE = 5;//4
	
	//constantes de nombre de escenario
	public static final int RACE_ELEO = 100;
	public static final int RACE_NINFA = 101;
	public static final int RACE_BIOMORPH = 102;
	public static final int RACE_SCRAPER = 103;
	public static final int RACE_GMARINE = 104;
	
	//constantes de los tipos de arma
	public static final int WEAP_RECTILINEAR=1;
	public static final int WEAP_PARABOLIC=2; 
	public static final int WEAP_GRENADE=3;
	public static final int WEAP_MELEE=4;
	public static final int WEAP_SPECIAL=5;
	
	//constantes del grado del arama
	public static final int POW_1=1;
	public static final int POW_2=2; 
	public static final int POW_3=3;
	
	//constantes de comunicacion
	public static final int BYTE_BUFFER_SIZE = 100;
	public static final int BYTE_BUFFER_SIZE_LARGE = 400;
	
	//constantes de cabecerea de paquete
	public static final char PACK_WIND = '>';
	public static final char PACK_CHAT = '*';
	public static final char PACK_ACTOR_STATE = '#';
	public static final char PACK_SHOOT = '&';
	public static final char PACK_ROOM_REQUEST = '%';
	public static final char PACK_CREATE_MATCH = '/';
	public static final char PACK_JOIN_MATCH = '(';
	public static final char PACK_START_MATCH = ')';
	public static final char PACK_TURN_STATE = '<';
	public static final char PACK_ACTIVE_PLAYER = '+';
	public static final char PACK_CONFIRM_INTERLUDE = '$';
	public static final char PACK_REMOVE_PLAYER = '-';
	public static final char PACK_END_TURN = '?';
	public static final char PACK_CHANGE_TURN = 'c';
	public static final char PACK_PLAYER_DISCONNECT = '@';
	public static final char PACK_CONFIRM_PLAYER_START = '=';
}



