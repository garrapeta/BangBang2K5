package game;

import java.awt.*;
import java.awt.event.*;

/*
 * Created on 08-dic-2004
 */

/**
 * Clase que implementa Listeners para los dispositivos de entrada del juego
 *  
 */
public class InputManager implements KeyListener, MouseMotionListener,
        MouseListener, FocusListener {

    // indica que esta habilitado
    protected boolean enabled = true;
    
    /*
     * Indican si se ha pulsado alguna tecla
     */
    protected boolean leftKey = false;

    protected boolean rightKey = false;

    protected boolean cannonUpKey = false;

    protected boolean cannonDownKey = false;

    protected boolean shotKey = false;

    protected boolean jumpKey = false;

    protected boolean focusLost = true;

    protected boolean shot = false;

    protected boolean chatKey = false;

    protected boolean chatMode = false;

    protected String chatMessage = "";

    protected boolean sendChatMessage = false;

    /*
     * Indica la posicinhn de la pantalla
     */
    protected Point screenDrag = new Point();

    protected int dragSensivity = 2;

    /*
     * Indica la posicinhn del cursor del ratnhn en la pantalla
     */
    protected Point mousePosition = new Point(320, 240);

    /*
     * Objetos que representan los cursores
     */
    protected Cursor moveCursor = new Cursor(Cursor.MOVE_CURSOR);

    private long initShotTime = 0;

    private long finalShotTime = 0;

    private long totalShotTime = 0;

    protected static final long MAX_SHOT_TIME = 2000;

    
    public InputManager() {
    	this.setInitShotTime(-1);
    }
    
    public void keyPressed(KeyEvent e) {

        if (!chatMode && enabled) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && !shotKey)
                leftKey = true;
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !shotKey)
                rightKey = true;
            else if (e.getKeyCode() == KeyEvent.VK_UP && !shotKey)
                cannonUpKey = true;
            else if (e.getKeyCode() == KeyEvent.VK_DOWN && !shotKey)
                cannonDownKey = true;
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (!shotKey) {
                    shotKey = true;
                    // registrar el instante en que se pulsa la tecla
                    initShotTime = e.getWhen();
                    finalShotTime = e.getWhen();
                    totalShotTime = 0;
                    // initShotTime = Sys.getTime();
                }
            } else if (e.getKeyCode() == KeyEvent.VK_CONTROL )
            	jumpKey = true;
        }

        //System.out.println("keyPressed");

    }//keyPressed()


    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            leftKey = false;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightKey = false;
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            cannonUpKey = false;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            cannonDownKey = false;
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // registrar el instante en que se suelta la tecla
            finalShotTime = e.getWhen();
            // finalShotTime = Sys.getTime();
            totalShotTime = calculateShotTime();
            // resetear initShotTime
            initShotTime = -1;
            // se activa al soltar
            if (!GameEngine.osName.equals("Linux")) {
                shotKey = false;
                shot = true;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_CONTROL)
            jumpKey = false;
        else if (e.getKeyCode() == KeyEvent.VK_T) chatKey = false;

        //System.out.println("keyreleased");
    }//keyReleased()


    public void keyTyped(KeyEvent e) {

    }//keyTyped()


    public boolean getLeftKey() {
        return leftKey;
    }


    public boolean getRightKey() {
        return rightKey;
    }


    public boolean getCannonUpKey() {
        return cannonUpKey;
    }


    public boolean getCannonDownKey() {
        return cannonDownKey;
    }


    public boolean getShotKey() {
        return shotKey;
    }//getShotKey()


    /**
     * @return Returns the jumpKey.
     */
    public boolean getJumpKey() {
        return jumpKey;
    }


    public int getMousePositionX() {
        return mousePosition.x;
    }


    public int getMousePositionY() {
        return mousePosition.y;
    }


    public Point getMousePosition() {
        return mousePosition;
    }


    public synchronized void mouseDragged(MouseEvent e) {
        int x = (e.getX() - mousePosition.x) * dragSensivity;
        int y = (e.getY() - mousePosition.y) * dragSensivity;

        screenDrag.x = x;
        screenDrag.y = y;

        mousePosition.x = e.getX();
        mousePosition.y = e.getY();

    }


    public synchronized void mouseMoved(MouseEvent e) {
        mousePosition.x = e.getX();
        mousePosition.y = e.getY();

    }


    public synchronized int getScreenDragX() {
        return screenDrag.x;
    }


    public synchronized int getScreenDragY() {
        return screenDrag.y;
    }


    public synchronized void setScreenDrag(int x, int y) {
        screenDrag.x = x;
        screenDrag.y = y;
    }


    public void mouseClicked(MouseEvent e) {
        ((Component) e.getSource()).requestFocus();
    }


    public void mouseExited(MouseEvent e) {
        mousePosition.x = 320;
        mousePosition.y = 240;
    }


    public void mouseEntered(MouseEvent e) {

    }


    public synchronized void mousePressed(MouseEvent e) {
        ((Component) e.getSource()).setCursor(moveCursor);
    }


    public synchronized void mouseReleased(MouseEvent e) {
        ((Component) e.getSource()).setCursor(null);
    }


    /**
     * @param shotKey The shotKey to set.
     */
    public void setShotKey(boolean shotKey) {
        this.shotKey = shotKey;
    }


    /**
     * @param jumpKey The jumpKey to set.
     */
    public void setJumpKey(boolean jumpKey) {
        this.jumpKey = jumpKey;
    }


    public void focusGained(FocusEvent e) {
        focusLost = false;
    }


    public void focusLost(FocusEvent e) {
        focusLost = true;
    }


    /**
     * @return Returns the focusLost.
     */
    public boolean isFocusLost() {
        return focusLost;
    }


    /**
     * @param focusLost The focusLost to set.
     */
    public void setFocusLost(boolean focusLost) {
        this.focusLost = focusLost;
    }


    /**
     * @return Returns the totalShotTime.
     */
    public long getTotalShotTime() {
        return totalShotTime;
    }


    private long calculateShotTime() {
        long shotTime = 0;
        shotTime = finalShotTime - initShotTime;
        if (shotTime > MAX_SHOT_TIME) {
            shotTime = MAX_SHOT_TIME;
        }
        return shotTime;
    }


    /**
     * @return Returns the mAX_SHOT_TIME.
     */
    public static long getMAX_SHOT_TIME() {
        return MAX_SHOT_TIME;
    }


    /**
     * @return Returns the shot.
     */
    public boolean getShot() {
        return shot;
    }


    /**
     * @param shot The shot to set.
     */
    public void setShot(boolean shot) {
        this.shot = shot;
    }


    /**
     * @return Returns the sendChatMessage.
     */
    public boolean isSendChatMessage() {
        return sendChatMessage;
    }


    /**
     * @return Returns the chatMessage.
     */
    public String getChatMessage() {
        return chatMessage;
    }


    /**
     * @return Returns the chatMode.
     */
    public boolean isChatMode() {
        return chatMode;
    }


    /**
     * @param sendChatMessage The sendChatMessage to set.
     */
    public void setSendChatMessage(boolean sendChatMessage) {
        this.sendChatMessage = sendChatMessage;
    }


    /**
     * @return Returns the finalShotTime.
     */
    public long getFinalShotTime() {
        return finalShotTime;
    }


    /**
     * @param finalShotTime The finalShotTime to set.
     */
    public void setFinalShotTime(long finalShotTime) {
        this.finalShotTime = finalShotTime;
    }


    /**
     * @return Returns the initShotTime.
     */
    public long getInitShotTime() {
        return initShotTime;
    }


    /**
     * @param initShotTime The initShotTime to set.
     */
    public void setInitShotTime(long initShotTime) {
        this.initShotTime = initShotTime;
    }


    /**
     * @param totalShotTime The totalShotTime to set.
     */
    public void setTotalShotTime(long totalShotTime) {
        this.totalShotTime = totalShotTime;
    }
  
    private void resetAllKeys(){
        leftKey = false;
        rightKey = false;
        cannonUpKey = false;
        cannonDownKey = false;
        shotKey = false;
        jumpKey = false;
        focusLost = true;
        shot = false;
        chatKey = false;
        chatMode = false;
        chatMessage = "";
        sendChatMessage = false;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled==false){
            resetAllKeys();
        }
    }
}//Input
