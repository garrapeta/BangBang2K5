/*
 * Created on 22-feb-2005
 *
 */
package game.logicEngine;

import game.ScreenInterface;

import java.awt.Rectangle;
import java.awt.geom.*;
import java.io.Serializable;
import java.nio.ByteBuffer;

import util.Constants;
import util.Direction;

/**
 * Clase que encapsula e implementa la lnhgica de los actores
 *  
 */
public class LogicActor implements LogicActorInterface, Serializable {

	/*
	 * Constantes que establecen los estados del actor IMPORTANTE: NO CAMBIAR EL
	 * ORDEN DE LOS ESTADOS NI SU NnhMERO, QUE TIENE QUE VER CON SU PRIORIDAD.
	 */

	/**
	 * Expresa cualquier estado. (Un actor nunca se encontrara en STATE_ANY)
	 */

	public static final int STATE_ANY = -1;
	
	/**
	 * El actorAmmo ha golpeado a pobre un ActorPlayer 
	 */
	public static final int STATE_MAKING_DAMAGE = 199;

	public static final int STATE_SHOOTING = 200;

	/**
	 * Estado disparando
	 */
	public static final int STATE_ENDSHOOTING = 201;

	/**
	 * Estado fin de disparo
	 */
	public static final int STATE_DYING = 202;
	
	

	/**
	 * Estado "siendo borrado".
	 */
	public static final int STATE_REMOVING = 208;

	/**
	 * Estado "explotando". Para los ActorAmmo
	 */
	public static final int STATE_BANGBANGING = 209;

	/**
	 * Estado parado
	 */
	public static final int STATE_STILL = 210;

	/**
	 * Estado movinhndose
	 */
	public static final int STATE_MOVING = 211;

	/**
	 * Estado "salto".
	 */
	public static final int STATE_JUMP = 212;
	
	/**
	 * Estado "tomando impulso".
	 */
	public static final int STATE_PREJUMP = 203;

	/**
	 * Estado "canhda".
	 */
	public static final int STATE_FALLING = 213;

	/**
	 * Un actor sufre danho debido a una explosion y sale volando.
	 * Un actor se ve lanzado por el aire por efecto de una explosinhn.
	 */
	public static final int STATE_HIT_ON_AIR = 214;
	
	/**
	 * Un actor sufre danho por algo que NO es una explosion y no sale 
	 * volando
	 */
	public static final int STATE_HIT_ON_GROUND = 204;
	
	

	/**
	 * Estado en el que se pone un player al colocarle en la pantalla. Es decir,
	 * al iniciar un stage.
	 */
	public static final int STATE_SET = 295;
	
	
	

	/**
	 * Estado actual
	 */
	protected int actualState;

	protected float power = 1.0f;

	
	/**
	 * Define la posicinhn y el tamanho del actor mediante un rectnhngulo
	 */
	protected Rectangle2D.Float bounds;

	/**
	 * Vector de velocidad del actor. El nhctor se movernh en lnhnea recta en la
	 * direccinhn del vector proporcionalmente al valor de sus componentes
	 */
	protected Point2D.Float velocity;

	/**
	 * Direccinhn a la que estnh orientado el actor
	 */
	protected Direction direction;

	/**
	 * nhngulo del actor en radianes
	 */
	protected double angle;

	protected double angleVelocity;
	
	private ByteBuffer lectura;

	/**
	 * Constructor
	 *  
	 */
	public LogicActor() {

		bounds = new Rectangle2D.Float();

		velocity = new Point2D.Float();

		direction = new Direction();
		
		lectura = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);

		initLogicActor();
	}

	/**
	 * Inicializa el actor.
	 *  
	 */
	public void initLogicActor() {


		bounds.x=0;
		bounds.y=0;
		bounds.width=0;
		bounds.height=0;

		angleVelocity = 0;

		velocity.setLocation(0, 0);

		direction.setDirection(Direction.RIGHT);

		actualState = STATE_SET;

	}//init()

	/**
	 * Devuelve la coordenada x de la posicinhn del actor
	 */
	public int getX() {
		return Math.round(bounds.x);
	}

	/**
	 * Devuelve la coordenada y de la posicinhn del actor
	 */
	public int getY() {
		return Math.round(bounds.y);
	}

	/**
	 * Devuelve el ancho del actor
	 */
	public int getHeight() {
		return Math.round(bounds.height);
	}

	/**
	 * Devuelve el alto del actor
	 */
	public int getWidth() {
		return Math.round(bounds.width);
	}

	/**
	 * Devuelve la velocidad por defecto del actor
	 */
	public float getPower() {
		return power;
	}

	/**
	 * Establece la velocidad por defecto del actor
	 */
	public void setPower(float s) {
		power = s;
	}

	/**
	 * @return Returns the velocity.
	 */
	public Point2D.Float getVelocity() {
		return velocity;
	}

	/**
	 * Devuelve la componente x de la velocidad del actor
	 */
	public float getXVelocity() {
		return velocity.x;
	}

	/**
	 * Devuelve la componente y de la velocidad del actor
	 */
	public float getYVelocity() {
		return velocity.y;
	}

	/**
	 * Establece la velocidad del actor
	 * 
	 * @param x
	 *            componente x de la velocidad
	 * @param y
	 *            componente y de la velocidad
	 */
	public void setVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}

	/**
	 * Establece la posicinhn del actor
	 * 
	 * @param x
	 *            componente x de la posicinhn
	 * @param y
	 *            componente y de la posicinhn
	 */
	public void setPosition(int x, int y) {
		bounds.x = x;
		bounds.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.actorInterface#setDimension(int, int)
	 */
	public void setSize(int w, int h) {
		bounds.width = w;
		bounds.height = h;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.ActorInterface#getXScreen(game.ScreenInterface)
	 */
	public int getXScreen(ScreenInterface s) {
		return Math.round(bounds.x) - s.getOffsetX();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.ActorInterface#getYScreen(game.ScreenInterface)
	 */
	public int getYScreen(ScreenInterface s) {
		return Math.round(bounds.y) - s.getOffsetY();
	}

	/**
	 * @return Devulelve un puntero al valor de la posicion interna del Actor.
	 */
	public Point2D getInternalPosition() {
		return new Point2D.Float(bounds.x,bounds.y);
	}

	/**
	 * @param internalPosition
	 *            The internalPosition to set.
	 */
	public void setInternalPosition(Point2D.Float position) {
		bounds.x=position.x;
	}

	/**
	 * @param internalPosition
	 *            The internalPosition to set.
	 */
	public void setInternalPosition(double x, double y) {
	    bounds.x=(float)x;
	    bounds.y=(float)y;
	}

	/**
	 * @return Devuelve la direccinhn a la que apunta el Actor.
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @return Returns the angle.
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param angle
	 *            The angle to set.
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void updateState() {

		switch (actualState) {
		case STATE_STILL:
			if (velocity.x != 0.0 || velocity.y != 0.0)
				actualState = STATE_MOVING;
			break;

		case STATE_MOVING:
			if (velocity.x == 0 && velocity.y == 0)
				actualState = STATE_STILL;
			break;

		default:
			break;
		}

		angle = angle + angleVelocity;

	}//updateState()

	public void setX(int x) {
		bounds.x = x;
	}

	public void setY(int y) {
		bounds.y = y;
	}

	/**
	 * @return Returns the actualState.
	 */
	public int getActualState() {
		return actualState;
	}

	/**
	 * @param actualState
	 *            The actualState to set.
	 */
	public void setActualState(int actualState) {
		this.actualState = actualState;
	}

	public boolean needsPhysics() {
		//Estos son los estados MOVING, JUMP, FALLING, FLYING y SET
		return (actualState > LogicActor.STATE_STILL);
	}

	public boolean getCollision(double x, double y) {
		return this.getCollision((int) StrictMath.round(x), (int) StrictMath
				.round(y));
	}

	public boolean getCollision(int x, int y) {
		return bounds.contains(x, y);
	}

	/**
	 * Alternativa para calcular una colisinhn con las bounding boxes.
	 */
	public boolean getCollision(LogicActorInterface logic) {
		return bounds.intersects(logic.getBounds());
	}

	/**
	 * @return Returns the angleVelocity.
	 */
	public double getAngleVelocity() {
		return angleVelocity;
	}

	/**
	 * @param angleVelocity
	 *            The angleVelocity to set.
	 */
	public void setAngleVelocity(double angleVelocity) {
		this.angleVelocity = angleVelocity;
	}

	/**
	 * @return Returns the bounds.
	 */
	public Rectangle2D.Float getBounds() {
		return bounds;
	}
	/**
	 * @param direction The direction to set.
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void setDirection(int direction) {
		this.direction.setDirection(direction);
	}
	
    
    public ByteBuffer desmontar(){
    	
    	lectura=ByteBuffer.allocate(80);
    	//Limpiamos el buffer y nos colocamos al principio de el
    	lectura.clear(); 
    	lectura.rewind();
        lectura.putInt(this.getActualState());
    	lectura.putDouble(this.getAngle());
    	lectura.putDouble(this.getAngleVelocity());
    	lectura.putDouble(this.getInternalPosition().getX());
    	lectura.putDouble(this.getInternalPosition().getY());
    	lectura.putInt(this.getX());
    	lectura.putInt(this.getY());
    	lectura.putFloat(this.getPower());
    	lectura.putInt(this.getWidth());
    	lectura.putInt(this.getHeight());
    	lectura.putFloat(this.getXVelocity());
    	lectura.putFloat(this.getYVelocity());
    	lectura.putInt(this.getDirection().getDirectionValue()); 
  //  	System.out.println("GETBYTES= "+System.getProperty("line.separator").getBytes().toString());
    	lectura.put(System.getProperty("line.separator").getBytes());
    	//int num =lectura.getInt();
    	//System.out.println("THE WINNER IS:"+num);
  //  	System.out.println(this.getActualState()+","+this.getHeight());    	
    	//String ls=lectura.toString();    	
    	return lectura;
    }
    
    public void montar(ByteBuffer buffer){
    	int a=buffer.getInt();
    	if((a<220)&(a>199)){
    		this.setActualState(a);
    		this.setAngle(buffer.getDouble());
    		this.setAngleVelocity(buffer.getDouble());
    		this.setInternalPosition(buffer.getDouble(),buffer.getDouble());
    		this.setPosition(buffer.getInt(),buffer.getInt());
    		this.setPower(buffer.getFloat());
    		this.setSize(buffer.getInt(),buffer.getInt());
    		this.setVelocity(buffer.getFloat(),buffer.getFloat());
    		this.direction.setDirection(buffer.getInt());  
    	}
    	else System.out.println("PAQUETE ERRONEO");    	
    	}
    
   public void fuckLogicActor (ByteBuffer buffer) {
   	buffer.rewind();
   	if (buffer.hasRemaining()){
   		char ch =buffer.getChar();
   		int a=buffer.getInt();
   		if((a<220)&(a>199)){
   			this.setActualState(a);
   			this.setAngle(buffer.getDouble());
   			this.setAngleVelocity(buffer.getDouble());
   			this.setInternalPosition(buffer.getDouble(),buffer.getDouble());
   			this.setPosition(buffer.getInt(),buffer.getInt());
   			this.setPower(buffer.getFloat());
   			this.setSize(buffer.getInt(),buffer.getInt());
   			this.setVelocity(buffer.getFloat(),buffer.getFloat());
   			this.direction.setDirection(buffer.getInt());
    		}
    	}
   }
   
    public ByteBuffer getByteBuffer(){return lectura;}
    public void setByteBuffer(ByteBuffer lec){lectura = lec;}
}//class LogicActor
