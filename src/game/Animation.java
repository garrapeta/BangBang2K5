package game;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
 * 
 */

/**
 * Clase que implementa las animaciones
 *  
 */
public class Animation {

    /* ArrayList que contiene los frames de la animacinhn */
    private ArrayList frames;

    /* duracinhn total de la animacinhn en nnhmero de frames del juego */
    private int totalDuration = 0;

    /* velocidad por defecto de los frames de la animacinhn */
    private int defaultFrameDuration = 10;

    /* nnhmero de frame de tiempo actual de la animacinhn */
    private long actualTime = 0;

    /* frame actual de la animacinhn */
    private AnimationFrame currentFrame=null;
    private int currentFrameNumber = 0;

    /* indica si la animacinhn estnh parada */
    private boolean stopped = false;

    private Point offset;


    /**
     * Constructor
     *  
     */
    public Animation() {
        offset = new Point();
        init();
    }//Animation()


    public void init() {
        offset.setLocation(0, 0);
        frames = new ArrayList();
    }


    /**
     * Reinicia la animacinhn desde el principio
     *  
     */
    public void start() {
        actualTime = 0;
        currentFrameNumber = 0;
    }//start()


    /**
     * Establece si se para o reanuda la animacinhn
     * 
     * @param s si es true se para y si es false se continnha
     */
    public void setStopped(boolean s) {
        stopped = s;
    }


    /**
     * Anhade un frame al final de la animacinhn
     * 
     * @param i imagen del frame
     * @param duration duracinhn del frame en nnhmero de frames del juego
     */
    public void addFrame(BufferedImage i, int duration) {
        frames.add(new AnimationFrame(i, duration));
        totalDuration = totalDuration + duration;
    }//addFrame()


    /**
     * Anhade un frame al final de la animacinhn. Establece la duracinhn de cada
     * frame a la duracinhn por defecto
     * 
     * @param i imagen del frame
     */
    public void addFrame(BufferedImage i) {
        frames.add(new AnimationFrame(i, defaultFrameDuration));
        totalDuration = totalDuration + defaultFrameDuration;
    }
    
    
    public void addFrame(BufferedImage i,int duration,int offsetX,int offsetY){
        AnimationFrame frame=new AnimationFrame(i, duration);
    	frames.add(frame);
        totalDuration = totalDuration + duration;
        frame.offsetX=offsetX;
        frame.offsetY=offsetY;
    }


    /**
     * Indica si es el final de la animacinhn
     * 
     * @return
     */
    public boolean isLastTime() {
        return (actualTime + 1 == totalDuration);
    }
    
    /**
     * Indica si es el principio de la animacinhn
     * 
     * @return
     */
    public boolean isFirstTime() {
        return (actualTime == 1);
    }


    /**
     * Devuelve la imagen del frame actual
     * 
     * @return la imagen
     */
    public BufferedImage getCurrentFrame() {
        return ((AnimationFrame) frames.get(currentFrameNumber)).image;
    }//getCurrentFrame()


    /**
     * Actualiza el estado de la animacinhn
     *  
     */
    public void updateState() {
        if (!stopped) {
            actualTime++;

            if (actualTime >= totalDuration) {
                actualTime = actualTime % totalDuration;
                currentFrameNumber = 0;
            }

            while (actualTime > ((AnimationFrame) frames.get(currentFrameNumber)).endTime)
                currentFrameNumber++;
        }//if
        
        currentFrame=(AnimationFrame)frames.get(currentFrameNumber);
    }//updateState()


    /**
     * Clase privada que representa los objetos frame de la animacinhn
     * 
     * @author
     *  
     */
    private class AnimationFrame {

        public BufferedImage image;

        /** Duracinhn del frame en frames de juego */
        public int duration = 0;

        /** Finalizacinhn del frame en frames de juego */
        public long endTime = 0;
        
        public int offsetX=0;
        public int offsetY=0;


        /**
         * Constructor
         * 
         * @param i imagen del frame
         * @param duration duracinhn del frame en frames de juego
         */
        public AnimationFrame(BufferedImage i, int duration) {
            image = i;
            this.duration = duration;
            endTime = totalDuration + duration - 1;
        }//AnimationFrame
        
        public AnimationFrame(BufferedImage i, int duration, int offsetX, int offsetY){
        	this(i,duration);
        	this.offsetX=offsetX;
        	this.offsetY=offsetY;
        }

    }//class AnimationFrame


    /**
     * @return Returns the defaultFrameDuration.
     */
    public int getDefaultFrameDuration() {
        return defaultFrameDuration;
    }


    public void setDefaultFrameDuration(int defaultFrameDuration) {
        this.defaultFrameDuration = defaultFrameDuration;
    }


    /**
     * Establece la duracinhn de todos los frames de la animacinhn a la duracinhn
     * por defecto.
     */
    public void resetFramesDuration() {

        AnimationFrame frame;
        int size = frames.size();

        totalDuration = 0;
        for (int i = 0; i < size; i++) {
            frame = (AnimationFrame) frames.get(i);
            frame.duration = defaultFrameDuration;
            frame.endTime = totalDuration + defaultFrameDuration - 1;

            totalDuration = totalDuration + defaultFrameDuration;
        }
    }//resetFramesDuration()


    public int getOffsetX() {
        if (currentFrame!=null) return currentFrame.offsetX;
        else return offset.x;
    }
    
    
    public int getOffsetY() {
        if (currentFrame!=null) return currentFrame.offsetY;
        else return offset.y;
    }

    
    public void setOffset(int x, int y) {
    	offset.setLocation(x,y);
        int size=frames.size();
        AnimationFrame frame;
        for (int i=0;i<size;i++){
        	frame=(AnimationFrame)frames.get(i);
        	frame.offsetX=x;
        	frame.offsetY=y;
        }
    }
}//class Animation
