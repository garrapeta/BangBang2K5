package game;

import game.actors.*;
import game.actors.ActorPlayer;
import game.stages.*;
import game.stages.StageInterface;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/*
 *
 */

/**
 * Componente donde se pinta el juego. Extiende la clase JFrame y pinta su
 * ContentPane. Implementa el interfaz ScreenInterface.
 * 
 * @author
 *  
 */
public class Screen extends JPanel implements ScreenInterface {

    /*
     * Constantes que definen la resolucinhn de la pantalla
     */
    private final int X_RESOLUTION = 640;

    private final int Y_RESOLUTION = 480;

    /*
     * Ancho del margen de la pantalla en la que al posicionar el cursos del
     * ratnhn, esta se desplaza por el escenario
     */
    private int moveMargin = 40;

    /*
     * Valor de la velocidad de desplazamiento de la pantalla por el escenario
     */
    private int moveSpeed = 10;

    
    private boolean movingToPosition=false;
    private float moveToPositionSpeed;
    private float distanceFromPosition;
    private Point destination; 
    
    /*
     * Vector de velocidad de la pantalla por el escenario
     */
    private Point2D.Float velocity;
    
    

    //Objeto graphics actual en el que pintar en la pantalla
    private Graphics graphics;

    /*
     * Variables que guardan el desplazamiento de la pantalla respecto del
     * escenario
     */
    private int offsetX = 0;

    private int offsetY = 0;
    
    /*private int iniX;
     						//seguro q hay una forma mejor de hacerlo
    private int iniY;*/
    
    private float internalOffsetX = 0;

    private float internalOffsetY = 0;

    //Buffer de dibujado
    protected Image actualBuffer;

    BufferedImage snapshot = new BufferedImage(X_RESOLUTION, Y_RESOLUTION,
            BufferedImage.TYPE_INT_ARGB);


    /**
     * Constructor. Inicializa la clase.
     *  
     */
    public Screen() {
        super();
        destination=new Point();
        init();

    }//Screen()


    /**
     * Inicializa la clase
     *  
     */
    public void init() {
    	destination.setLocation(0,0);
        velocity = new Point2D.Float();
        //this.setIgnoreRepaint(false);
        this.setFocusable(true);
        this.setLayout(null);

        initControls();
    }//init()


    /*
     * Perpara la pantalla para generar Graphics. Este mnhtodo debe invocarse una
     * vez se haya anhadido el objeto Screen a un contenedor y este se haya hecho
     * visible por lo menos una vez.
     */
    public void initScreenGraphics() {

        /*
         * this.setLocation(20, 20); this.setSize(X_RESOLUTION, Y_RESOLUTION);
         * 
         * BufferCapabilities bc = new BufferCapabilities(new ImageCapabilities(
         * true), new ImageCapabilities(true), null);
         * 
         * try { this.createBufferStrategy(2, bc); } catch (AWTException e) {
         * e.printStackTrace(); }
         * 
         * graphics = this.getBufferStrategy().getDrawGraphics();
         */

        //buffer1=new
        // BufferedImage(this.X_RESOLUTION,this.Y_RESOLUTION,BufferedImage.TYPE_INT_RGB);
        //buffer2=new
        // BufferedImage(this.X_RESOLUTION,this.Y_RESOLUTION,BufferedImage.TYPE_INT_RGB);
        actualBuffer=new BufferedImage(this.X_RESOLUTION, this.Y_RESOLUTION,BufferedImage.TYPE_INT_RGB);
        //actualBuffer = this.createImage(this.X_RESOLUTION, this.Y_RESOLUTION);
        graphics = actualBuffer.getGraphics();
    }//initScreenGraphics()


    /*
     * Inicializa los controles como los botones de seleccinhn de armas
     */
    private void initControls() {

    }//initControls()


    /**
     * Devuelve la resolucinhn horizontal del nhrea donde se pinta el juego
     */
    public int getXRes() {
        return X_RESOLUTION;
    }//getXRes()


    /**
     * Devuelve la resolucinhn vertical del nhrea donde se pinta el juego
     */
    public int getYRes() {
        return Y_RESOLUTION;
    }//getYRes()


    /**
     * Devuelve el desplazamiento horizontal del nhrea donde se pinta el juego
     * respecto del escenario
     */
    public int getOffsetX() {
        return offsetX;
    }//getOffsetX()


    /**
     * Devuelve el desplazamiento vertical del nhrea donde se pinta el juego
     * respecto del escenario
     */
    public int getOffsetY() {
        return offsetY;
    }//getOffsetY()


    /**
     * Establece el vector de la velocidad a la que se mueve la pantalla por el
     * escenario
     * 
     * @param x componente de velocidad x
     * @param y componente de velocidad y
     */
    public void setVelocity(float x, float y) {
        velocity.x = x;
        velocity.y = y;
    }//setVelocity()
    
    public void setVelocity(float x,float y ,float v){
    	
        float module=(float)Math.pow((Math.pow(x,2)+Math.pow(y,2)),0.5);
    	velocity.x=(x/module)*v;
    	velocity.y=(y/module)*v;
    }
    
    public void moveToPosition(int posX,int posY,float velocityModule){
    	moveToPositionSpeed=velocityModule;
        movingToPosition=true;
    	int vectorX=posX-offsetX;
    	int vectorY=posY-offsetY;
    	
    	distanceFromPosition=(float)Math.pow((Math.pow(vectorX,2)+Math.pow(vectorY,2)),0.5);
    	   	
    	destination.x=posX;
    	destination.y=posY;
    	
    	setVelocity(vectorX,vectorY,velocityModule);
    }


    /**
     * Actualiza el estado lnhgico
     * 
     * @param stage
     */
    public void updateState(StageInterface stage) {
        
        float auxInternalOffsetX;
        float auxInternalOffsetY;
        auxInternalOffsetX = internalOffsetX + velocity.x;
        auxInternalOffsetY = internalOffsetY + velocity.y;
        
        if (auxInternalOffsetX<0){
            velocity.x=0;
            auxInternalOffsetX=0;
        }
        else if(auxInternalOffsetX>stage.getSizeX()-1-this.getWidth()){
            velocity.x=0;
            auxInternalOffsetX=stage.getSizeX()-1-this.getWidth();
        }
        
        if (auxInternalOffsetY<0){
            velocity.y=0;
            auxInternalOffsetY=0;
        }
        else if(auxInternalOffsetY>stage.getSizeY()-1-this.getHeight()){
            velocity.y=0;
            auxInternalOffsetY=stage.getSizeY()-1-this.getHeight();
        }
        
        internalOffsetX=auxInternalOffsetX;
        internalOffsetY=auxInternalOffsetY;
        
        offsetX=Math.round(internalOffsetX);
        offsetY=Math.round(internalOffsetY);

        if (movingToPosition){
            distanceFromPosition=distanceFromPosition-moveToPositionSpeed;
            
            if (distanceFromPosition<=0){
	        	movingToPosition=false;
	        	offsetX=destination.x;
	        	offsetY=destination.y;
	        	setVelocity(0,0);
	        }
        }
        	
    }//updateState()


    /**
     * Establece el desplazamiento horizontal del nhrea donde se pinta el juego
     * respecto del escenario
     */
    public void setOffsetX(int x) {
        this.offsetX = x;
        this.internalOffsetX = x;
    }//setOffsetX()


    /**
     * Establece el desplazamiento vertical del nhrea donde se pinta el juego
     * respecto del escenario
     */
    public void setOffsetY(int y) {
        this.offsetY = y;
        this.internalOffsetY = y;
    }//setOffsetY()


    /**
     * Devuelve el objeto Graphics del nhrea donde se pinta el juego
     */
    public Graphics getScreenGraphics() {
        return graphics;
        //return this.getBufferStrategy().getDrawGraphics();
    }//getScreenGraphics()


    /**
     * Vuelca el contenido del bnhffer de pantalla sobre el nhrea en el que se
     * pinta el juego
     */
    public void showScreen() {
        /*
         * BufferStrategy be = this.getBufferStrategy();
         * 
         * be.show(); graphics.dispose(); graphics = be.getDrawGraphics();
         */
        this.paintComponents(graphics);
        this.getGraphics().drawImage(actualBuffer, 0, 0, null);
        graphics.dispose();

        /*
         * if (actualBuffer==buffer1) actualBuffer=buffer2; else if
         * (actualBuffer==buffer2) actualBuffer=buffer1;
         */

        graphics = actualBuffer.getGraphics();

    }//ShowScreen()


    /*
     * public void paint(Graphics g) { BufferStrategy be =
     * this.getBufferStrategy();
     * 
     * be.show(); graphics.dispose(); graphics = be.getDrawGraphics();
     * g.drawImage(actualBuffer,0,0,null); graphics.dispose();
     * 
     * if (actualBuffer==buffer1) actualBuffer=buffer2; else if
     * (actualBuffer==buffer2) actualBuffer=buffer1;
     * 
     * graphics=actualBuffer.getGraphics();
     * 
     * }//ShowScreen()
     * 
     * public void update(Graphics g){ paint(g); }
     */

    /**
     * Mnhtodo de pintado utilizado por SWING
     */
    public void paint(Graphics g) {
        if (actualBuffer != null) {

            Graphics2D g2 = (Graphics2D) snapshot.getGraphics();

            g2.drawImage(actualBuffer, 0, 0, null);
            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRect(0, 0, X_RESOLUTION, Y_RESOLUTION);

            g.drawImage(snapshot, 0, 0, null);
        }

    }//paint()
    


    public void update(Graphics g) {

    }


    public void repaint() {

    }


    /**
     * Devuelve el ancho del umbral sobre el que posicionar el cursor para poder
     * deplazar la pantalla por el escenario
     * 
     * @return el margen
     */
    public int getMoveMargin() {
        return moveMargin;
    }//getMoveMargin()


    public int getMoveSpeed() {
        return moveSpeed;
    }//getMoveSpeed()

	/**
	 * @return Returns the movingToPosition.
	 */
	public boolean isMovingToPosition() {
		return movingToPosition;
	}
	/**
	 * @param movingToPosition The movingToPosition to set.
	 */
	public void setMovingToPosition(boolean movingToPosition) {
		this.movingToPosition = movingToPosition;
	}
	
	
	public void setCentered(StageInterface stage,Actor actor){
	    	    
	    	    
        int x=actor.getLogic().getX()-this.getSize().width/2+actor.getLogic().getWidth()/2;
        int y=actor.getLogic().getY()-this.getSize().height/2+actor.getLogic().getHeight()/2;
        
        if(x<0){
            x=0;
        }
        else if (x>stage.getSizeX()-X_RESOLUTION-1){
            x=stage.getSizeX()-X_RESOLUTION-1;
        }   
        this.setOffsetX(x);
        if(y<0){
            y=0;
        }
        else if (y>stage.getSizeY()-Y_RESOLUTION-1){
            y=stage.getSizeY()-Y_RESOLUTION-1;
        } 
        this.setOffsetY(y); 
	}
}//class Screen
