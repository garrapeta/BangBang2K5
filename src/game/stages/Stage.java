package game.stages;

import game.*;
import game.actors.*;
import game.actors.ammo.ActorAmmo;
import game.logicEngine.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;

import util.Direction;
import util.Tools;
import weapons.*;

/*
 * 
 */

/**
 * @author
 */
public abstract class Stage implements StageInterface {

    //Capacidad inicial del vector de actores
    protected final int ACTORS_ARRAY_INITIAL_CAPACITY = 40;

    protected BufferedImage terrain; //imagen del terreno

    protected BufferedImage arrowRight; //Imagen de la flecha de desplazamiento

    protected BufferedImage arrowLeft; //Imagen de la flecha de desplazamiento

    protected BufferedImage arrowUp; //Imagen de la flecha de desplazamiento

    protected BufferedImage arrowDown; //Imagen de la flecha de desplazamiento

    //Raster con la informacinhn de la imagen del terreno
    protected Raster terrainRaster;

    //Modelo de color de la imagen del terreno
    protected ColorModel terrainColorModel;

    protected Dimension dimension; //tamanho en pixeles

    protected ArrayList actorsArray; //vector de sprites

    //Referencia al objeto del jugador
    //protected ActorPlayer player;

    //Fondos del escenario
    protected Background background;

    protected PhysicEnvironment environment;

    //Mapa del rnhdar del escenario
    protected Radar radar;

    // referencia al proyectil "activo"
    protected ActorAmmo activeProjectile;

    protected ActorPlayer activePlayer;
    
    /*
     * AudioManager utilizado por los actores de este stage para producir
     * eventos de sonido
     */
    protected sound.audioManagers.AudioManagerInterface am;

    /**
     * Linea que describe la inclinacinhn de punto del terreno
     */
    protected Point2D boundaryLine;

    /**
     * Constructor
     */

    public Stage() {
        boundaryLine = new Point2D.Float();
        arrowRight = Tools.loadBufferedImage("art" + File.separator
                + "arrowRight.png");
        arrowLeft = Tools.loadBufferedImage("art" + File.separator
                + "arrowLeft.png");
        arrowUp = Tools.loadBufferedImage("art" + File.separator
                + "arrowUp.png");
        arrowDown = Tools.loadBufferedImage("art" + File.separator
                + "arrowDown.png");
        initStage();

    }

    public void initStage() {
        actorsArray = new ArrayList(ACTORS_ARRAY_INITIAL_CAPACITY);

        dimension = new Dimension();

    }
    
    protected void setTerrain(String tFile){
    	terrain = Tools.loadBufferedImage(tFile);
        terrainRaster = terrain.getData();
        terrainColorModel = terrain.getColorModel();
    }

    protected void setMusic(String mFile){
        ArrayList playlist = new ArrayList();
        playlist.add(new File(mFile));        
        this.am = new sound.audioManagers.AudioManagerStandard(playlist);
    }
    
    protected void setRadar(String rFile,int x,int y){
    	radar = new Radar(terrain,Tools.loadBufferedImage(rFile));
    	radar.setLocation(x,y);
    	radar.setActorsArray(actorsArray);
    }
   
    protected void setDimension(){
    	dimension.width = terrain.getWidth();
        dimension.height = terrain.getHeight();
    }
    
    public void updateState(GameInterface game) {

        ActorInterface sprite;
        int size = actorsArray.size();
        int i = 0;

        //Eliminamos los cadnhveres y los casquillos
        while (i < size) {
            sprite = (ActorInterface) actorsArray.get(i);

            sprite.updateState(this);


            if (sprite.getLogic().getActualState() == LogicActor.STATE_REMOVING) {
                this.removeActor(sprite);

                size = actorsArray.size();
            } else
                i++;

        }

        activePlayer = game.getActivePlayer();

        background.updateState();

    }

    /**
     *  
     */
    public void calculateDamage(ActorPlayer actor) {
        double distance = activeProjectile.getLogic().getInternalPosition()
                .distance(actor.getLogic().getInternalPosition());
        LogicActorAmmo logicActorProjectile = (LogicActorAmmo) activeProjectile
                .getLogic();
        LogicActorPlayer logicActorPlayer = (LogicActorPlayer) actor.getLogic();

        //se le quita vida
        drainLife(distance, logicActorPlayer, logicActorProjectile);
        //se calcula si salta por los aires 
        calcShockwave(actor, logicActorProjectile);

    }//calculateDamage()

	/* (non-Javadoc)
	 * @see game.stages.StageInterface#calculateFallDamage(game.actors.ActorPlayer)
	 */
	public void calculateFallDamage(ActorPlayer player) {
		
		LogicActorPlayer logic = (LogicActorPlayer) player.getLogic();
		
		logic.setLife(logic.getLife()-100);
	}

    /**
     * Quita vida en funcinhn de la distancia y del proyectil.
     * 
     * @param logicActorProjectile
     *            La lnhgica del proyectil
     * @param distance
     *            La distancia desde el proyectil al jugador
     * @param logicActorPlayer
     *            El jugador afectado
     *  
     */
    private void drainLife(double distance, LogicActorPlayer logicActorPlayer,
            LogicActorAmmo logicActorProjectile) {
        
        int life = logicActorPlayer.getLife();
        int damage = logicActorProjectile.getDamage();
        double distanceFactor = 100 / (100 + distance);
        int newLife = (int) (life - StrictMath.round(damage * distanceFactor));

        logicActorPlayer.setLife(newLife);
    }

    //para melee
    public void drainLife(double distance, LogicActorPlayer logicActorPlayer,
            int damage) {
        
        int life = logicActorPlayer.getLife();        
        double distanceFactor = 100 / (100 + distance);
        int newLife = (int) (life - StrictMath.round(damage * distanceFactor));
        logicActorPlayer.setLife(newLife);
    }
    /**
     * Calcula la fuerza con la que debe ser lanzado un personaje al ser
     * alcanzado por un proyectil.
     * 
     * @param actor
     *            El actor al que se le aplica.
     * @param logicActorProjectile
     * @param distance
     */
    private void calcShockwave(ActorPlayer actor,
            LogicActorAmmo logicActorProjectile) {
        double MAX_POWER = 5;

        actor.getLogic().setAngle(StrictMath.toRadians(15));
        actor.getLogic().setPower((float) (MAX_POWER));
        actor.getLogic().setActualState(LogicActor.STATE_HIT_ON_AIR);
        
        //Si el proyectil estnh a la izq. del personaje
        if(logicActorProjectile.getInternalPosition().getX() < actor.getLogic().getInternalPosition().getX()){
            actor.getLogic().setDirection(Direction.RIGHT);
        }
        //Si estnh a la derecha
        else{
            actor.getLogic().setDirection(Direction.LEFT); 
        }
        
        actor.getBehavior().initPhysicPlayer(actor.getLogic(), this);
    }

    /*
     * Pinta el terreno
     */
    private void drawTerrain(ScreenInterface s) {

        int offsetX = s.getOffsetX();
        int offsetY = s.getOffsetY();
        Graphics2D g2 = (Graphics2D) s.getScreenGraphics();

        g2.drawImage(terrain, -offsetX, -offsetY, null);

    }

    /*
     * Pinta los sprites
     */
    private void drawSprites(ScreenInterface s) {

        int numSprites = actorsArray.size();
        ActorInterface spriteAux;

        for (int i = 0; i < numSprites; i++) {

            spriteAux = (ActorInterface) actorsArray.get(i);
            spriteAux.draw(s);
        }
    }

    private void drawArrows(ScreenInterface s) {

        Graphics2D g2 = (Graphics2D) s.getScreenGraphics();

        if (s.getOffsetX() < (this.getSizeX() - s.getXRes() - 1)) {
            g2.drawImage(arrowRight, s.getXRes() - 40, s.getYRes() / 2 - 12,
                    null);
        }

        if (s.getOffsetX() > 0) {
            g2.drawImage(arrowLeft, 10, s.getYRes() / 2 - 12, null);
        }

        if (s.getOffsetY() < (this.getSizeY() - s.getYRes() - 1)) {
            g2.drawImage(arrowDown, s.getXRes() / 2 - 12, s.getYRes() - 40,
                    null);
        }

        if (s.getOffsetY() > 0) {
            g2.drawImage(arrowUp, s.getXRes() / 2 - 12, 10, null);
        }
    }

    public void draw(ScreenInterface s) {

        if (background != null)
            background.draw(s, this);

        drawTerrain(s);
        drawSprites(s);
        drawRadar(s);
        drawArrows(s);
    }//draw()

    public void drawRadar(ScreenInterface s) {
        if (radar != null)
            radar.draw(s);
    }

    /**
     * Devuelve el tamanho del escenario
     */
    public Dimension getSize() {
        return dimension;
    }

    public int getSizeX() {
        return dimension.width;
    }

    public int getSizeY() {
        return dimension.height;
    }

	public Radar getStageMap() {
        return radar;
    }

    /**
     * Chequea la colisinhn del punto dado con el escenario o los personajes.
     * 
     * @param x
     *            Coordenada x del punto
     * @param y
     *            Coordenada y del punto
     * @return Devuelve, si hay colisinhn, <em>false</em>. Si no hay colisinhn
     *         devuelve <em>false</em>.
     */
    public boolean getCollision(double x, double y) {

        return getCollision((int) Math.round(x), (int) Math.round(y));

    }

    public boolean getCollision(int x, int y) {
        //Compruebo que estoy dentro de los lnhmites, por si acaso
        if (x < 0 || x >= this.getSizeX() || y < 0 || y >= this.getSizeY()) {
            return true;
        }

        Object samples;
        samples = terrainRaster.getDataElements(x, y, null);

        if (terrainColorModel.getAlpha(samples) == 0)
            return false;
        else
            return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see game.stages.StageInterface#getPlayersCollision(double, double)
     */
    public boolean getPlayersCollision(LogicActorInterface logic) {
        int size = actorsArray.size();
        boolean collide = false;
        ActorPlayer actorPlayer = null;
        int i = 0;

        while (i < size && !collide) {
            //si el actor es un jugador ActorPlayer
            if (actorsArray.get(i) instanceof ActorPlayer) {
                actorPlayer = (ActorPlayer) actorsArray.get(i);

                //TODO nhapa pnh que no choque consigo mismo
                //La bala no choca con su duenho nunca
                if (!actorPlayer.isShooting()) {
                    collide = actorPlayer.getCollision(logic);
                }
            }
            i++;
        }

        return collide;
    }

    /**
     * @return Returns the environment.
     */
    public PhysicEnvironment getEnvironment() {
        return environment;
    }

    /**
     * @param environment
     *            The environment to set.
     */
    public void setEnvironment(PhysicEnvironment environment) {
        this.environment = environment;
    }

    public void playSounds() {
        int numSprites = actorsArray.size();
        ActorInterface spriteAux;

        for (int i = 0; i < numSprites; i++) {
            spriteAux = (ActorInterface) actorsArray.get(i);
            spriteAux.playSounds();
        }
    }

    public sound.audioManagers.AudioManagerInterface getAudioManager() {
        return this.am;
    }


    /**
     * Calcula el nhngulo del terreno en un punto.
     * 
     * @return Returns the boundaryLine.
     */
    public Point2D calcBoundaryLine(double x, double y) {
        return calcBoundaryLine((int) StrictMath.round(x), (int) StrictMath
                .round(y));
    }

    //TODO Solo funciona rebotes de arriba a abajo
    public Point2D calcBoundaryLine(int x, int y) {
        if (y == this.getSizeY()) {
            x = 1;
            y = 0;
        } else if (x == 0 || x == this.getSizeX()) {
            x = 0;
            y = 1;
        } else {
            int preX = x - 1;
            int preY = y - 1;
            int postX = x + 1;
            int postY = y - 1;

            //Primero probamos la parte izq del punto
            for (int i = 0; i < 3; i++) {
                if (getCollision(preX, preY + i)) {
                    preY = preY + i;
                    continue;
                }
            }
            //Luego probamos la parte derecha del punto
            for (int i = 0; i < 3; i++) {
                if (getCollision(postX, postY + i)) {
                    postY = postY + i;
                    continue;
                }
            }
            x = postX - preX;
            y = postY - preY;
        }
        boundaryLine.setLocation(x, y);
        return boundaryLine;
    }

    public void commitExplossion(int x, int y, Explossion e) {
        Graphics2D g2 = (Graphics2D) terrain.getGraphics();
        //g2.setColor(ColoursDefinitions.TRANSPARENT);
        g2.setComposite(AlphaComposite.Clear);
        //g2.drawPolygon((Polygon)e.getShape());
        g2.fillPolygon((Polygon) e.getShape());
        g2.dispose();
        updateRaster();

    }

    public void addActor(ActorInterface actor) {
        this.actorsArray.add(actor);
    }

    public void removeActor(ActorInterface actor) {
        this.actorsArray.remove(actor);
    }

    protected void updateRaster() {
        terrainRaster = terrain.getData();
        terrainColorModel = terrain.getColorModel();
    }

    /**
     * @return Returns the actorsArray.
     */
    public ArrayList getActorsArray() {
        return actorsArray;
    }

    /**
     * @param actorsArray
     *            The actorsArray to set.
     */
    public void setActorsArray(ArrayList actorsArray) {
        this.actorsArray = actorsArray;
    }

    /**
     * Recalcula el estado de los jugadores, para que la destruccinhn del terreno
     * afecte a su posicinhn.
     */
    public void updatePlayersState() {
        for (int i = 0; i < actorsArray.size(); i++) {
            Actor auxActor = (Actor) actorsArray.get(i);
            if (auxActor instanceof ActorPlayer) {
                auxActor.getLogic().setActualState(LogicActor.STATE_FALLING);
            }
        }
    }

    /**
     * @return Returns the activeProjectile.
     */
    public ActorAmmo getActiveProjectile() {
        return activeProjectile;
    }

    /**
     * @param activeProjectile
     *            The activeProjectile to set.
     */
    public void setActiveProjectile(ActorAmmo activeProjectile) {
        this.activeProjectile = activeProjectile;
    }

    protected class Background {

        private ArrayList arrayLayers;

        public Background() {
            arrayLayers = new ArrayList();
        }

        public void addLayer(BufferedImage i) {
            Animation layer = new Animation();
            layer.addFrame(i, 1);
            arrayLayers.add(layer);
        }

        public void addLayer(Animation a) {
            arrayLayers.add(a);
        }

        public void draw(ScreenInterface screen, Stage stage) {
            int backGroundX;
            int backGroundY;
            int size = arrayLayers.size();
            BufferedImage image;
            Graphics2D g2 = (Graphics2D) screen.getScreenGraphics();

            for (int i = 0; i < size; i++) {
                image = ((Animation) arrayLayers.get(i)).getCurrentFrame();
                //Comentada nhapa parallax
                //nhAPA
                //                double n = i * 0.2 + 1.1;

                backGroundX = (int) (screen.getOffsetX() * ((float) (image
                        .getWidth() - screen.getXRes()) / (float) (stage
                        .getSizeX() - screen.getXRes())));

                backGroundY = (int) (screen.getOffsetY() * ((float) (image
                        .getHeight() - screen.getYRes()) / (float) (stage
                        .getSizeY() - screen.getYRes())));

                //nhAPA
                //                backGroundX = (int) StrictMath.round(n * backGroundX);
                g2.drawImage(image, -backGroundX, -backGroundY, null);
            }//for
        }//draw()

        public void updateState() {
            int size = arrayLayers.size();
            Animation anim;

            for (int i = 0; i < size; i++) {
                anim = (Animation) arrayLayers.get(i);
                anim.updateState();
            }
        }//updateState()

    }//class Background

    /**
     * @return Returns the activePlayer.
     */
    public ActorPlayer getActivePlayer() {
        return activePlayer;
    }

    /**
     * @param activePlayer
     *            The activePlayer to set.
     */
    public void setActivePlayer(ActorPlayer activePlayer) {
        this.activePlayer = activePlayer;
    }
    
    
    /* (non-Javadoc)
     * @see game.stages.StageInterface#searchActor(java.lang.String)
     */
    public ActorPlayer searchActor(String actorID) {
        ActorInterface actor = null;
        int i = 0;
        boolean found = false;
        
        while(!found && i < getActorsArray().size()){
            actor = (ActorInterface)getActorsArray().get(i);
            
            if(actor instanceof ActorPlayer){
//                int compare = actorID.compareTo(((LogicActorPlayer) actor.getLogic()).getUserID());
//                if(compare == 0){
//                    found = true;
//                }
            if (actorID.startsWith(((LogicActorPlayer) actor.getLogic()).getUserID()))
            		found=true;
            }
            i++;
        }
        System.out.println("userID= "+((LogicActorPlayer) actor.getLogic()).getUserID());
        return (ActorPlayer)actor;
    }
}