/*
 * LogicActorTarget.java
 */

package game.logicEngine;


/**
 *
 */
public class LogicActorTarget extends LogicActor{
    
    public final float SPEED=0.05f;
    
    public void updateState(){
        double angleAux;
        angleAux=angle+angleVelocity;
        if (angleAux>-1.57 && angleAux<1.57) angle=angleAux;
    }
}
