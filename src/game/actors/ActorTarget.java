/*
 * ActorTarget.java
 */

package game.actors;

import game.ScreenInterface;
import game.graphicsEngine.*;
import game.logicEngine.*;
import game.stages.StageInterface;

import java.awt.Image;



/**
 * Clase que implementa el punto de mira
 */
public class ActorTarget extends Actor{
    
    protected GraphicsEngineInterface graphicsEngine;
    protected ActorPlayer owner;
    
    public ActorTarget(Image image,ActorPlayer owner){
        super();
        logic = new LogicActorTarget();
        graphicsEngine=new GraphicsEngineTarget(logic,image);
        initActorTarget(image,owner);
    }
    
    public void initActorTarget(Image image,ActorPlayer owner){
        this.owner=owner;
        logic.setSize(image.getWidth(null),image.getHeight(null));
        logic.setPosition(owner.getLogic().getX(),owner.getLogic().getY());
    }
    
    public void updateState(StageInterface stage){
        LogicActorInterface ownerLogic=owner.getLogic();
        logic.setPosition(ownerLogic.getX()+ownerLogic.getWidth()/2,
                ownerLogic.getY()+ownerLogic.getHeight()/2);
        logic.updateState();
    }
    
    public void updatePosition(ActorPlayer actor){
//        if (actor.getLogic().getDirection().getDirectionValue()==Direction.LEFT){
//            logic.getDirection().setDirection(Direction.LEFT);
//            logic.setPosition(actor.getLogic().getX(),actor.getLogic());
//        }
//        else {
//            logic.getDirection().setDirection(Direction.RIGHT);
//            logic.setPosition(actor.getLogic().getX()+actor.getGraphicsEngine().getImage().getWidth(null)/2,actor.getLogic().getY()+30);
//        }
    }
    
    public void draw(ScreenInterface screen){
        graphicsEngine.draw(screen);
    }
}
