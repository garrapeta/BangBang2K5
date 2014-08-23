package game.stages;

import game.Animation;
import game.actors.*;
import game.logicEngine.LogicActorPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import util.Tools;
import weapons.PhysicEnvironment;

/*
 * 
 */

/**
 * @author
 *  
 */
public class StageTest extends Stage {

	private final String TERRAIN_FILE="art" + File.separator + "terrain.png";
	
    private final String MUSIC_FILE="musicFiles" + File.separator + "bangbang56-80.mp3";
   
    private final String RADAR_FILE="art" + File.separator
                + "terrainmap.png";
   
    public StageTest() {
        super();
        //String TERRAIN=this.getTerrain();
        //this.setTerrain(TERRAIN);
        this.setTerrain(TERRAIN_FILE);
        this.setMusic(MUSIC_FILE);
        this.setRadar(RADAR_FILE,20,20);
        this.addEnvironment();
        this.setDimension();
        this.setBackground();
    }
  
    private void addEnvironment(){
    	//Estos params. se sacan de fichero o son estnhticos en
        //cada clase.
        float gravity = (float) 9.8*(2f/5f);
        float timeStep = (float) 0.04;
        float wind = (float) 0;
        PhysicEnvironment environment = new PhysicEnvironment(gravity,
                timeStep, wind);
        this.setEnvironment(environment);
    }

    private void niapa(){
        //ActorPlayer player0 = new ActorPlayerScrapper(this,"el Tuerkas");
        //player0.getLogic().setPosition(500, 1);
        //player0.getLogic().setPower(3f);
        //player0.getBehavior().initPhysicPlayer(player0.getLogic(), this);

        //ActorPlayer player1 = new ActorPlayerScrapper(this,"Bender");
        //player1.getLogic().setPosition(200, 1);
        //player1.getLogic().setPower(3f);
        //player1.getBehavior().initPhysicPlayer(player1.getLogic(), this);
        
        //ActorPlayer player2 = new ActorPlayerBiomorph(this, "Er bisho de Chiclana");
        //player2.getLogic().setPosition(400, 1);
        //player2.getLogic().setPower(3f);
        //player2.getBehavior().initPhysicPlayer(player2.getLogic(), this);
        
        //actorsArray.add(player1);
        //actorsArray.add(player2);
        //actorsArray.add(player0);
    }
    
    private void setBackground(){
    	 background = new Background();
         background.addLayer(Tools.loadBufferedImage("art" + File.separator
                 + "sky_1.png"));
         background.addLayer(Tools.loadBufferedImage("art" + File.separator
                 + "sky_3.png"));
         background.addLayer(createSky2());
    }
    
    private Animation createSky2() {
        int frameLength = 2;
        Animation anim = new Animation();
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0000.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0001.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0002.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0003.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0004.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0005.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0006.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0007.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0008.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0009.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0010.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0011.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0012.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0013.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0014.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0015.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0016.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0017.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0018.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0019.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0020.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0021.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0022.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0023.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0024.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0025.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0026.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0027.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0028.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0029.png"),
                frameLength);
        anim.addFrame(Tools.loadBufferedImage("art" + File.separator
                + "meteorBack" + File.separator + "meteoBack0030.png"),
                frameLength);

        return anim;
    }
    
	private String getTerrain(){
		List l=this.addForegrounds();
		int numberOfStrings=l.size();
		return (String)(l.get((int)(Math.random()*numberOfStrings)));
	}
	
	private List addForegrounds(){
		List l=new ArrayList();
		l.add("art" + File.separator + "ocb-5"
				+ File.separator + "ocb5foreground.png");
		l.add("art" + File.separator + "silicosis"
				+ File.separator + "silicosisforeground.png");
		l.add("art" + File.separator + "terrain.png");
		l.add("art" + File.separator + "nodrubaz"
				+ File.separator + "plataformaforeground.png");
		l.add("art" + File.separator + "eleuron"
				+ File.separator + "eleuronforeground.png");
		return l;
	}

}