/*
 * Created on 04-abr-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game.stages;

import game.actors.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import util.Tools;
import weapons.PhysicEnvironment;

/**
 * @author usuario_local
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StageNodrubaz extends Stage{
	
	
	private final String TERRAIN_FILE="art" + File.separator + "nodrubaz"
	+ File.separator + "plataformaforeground.png";
	
	private final String MUSIC_FILE="musicFiles" + File.separator + "sintetico56-80.mp3";
	
	private final String RADAR_FILE="art" + File.separator
    + "nodrubaz"+File.separator+"plataformaforegroundMapa.png";

	public StageNodrubaz() {
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
        float gravity = (float) 9.8*(3f/5f);
        float timeStep = (float) 0.04;
        float wind = (float) 0;
        PhysicEnvironment environment = new PhysicEnvironment(gravity,
                timeStep, wind);
        this.setEnvironment(environment);
	}
	
	private void niapa(){/*
		ActorPlayer player1 = new ActorPlayerScrapper(this,"Luisete");
        player1.getLogic().setPosition(200, 1);
        player1.getLogic().setPower(3f);
        player1.getBehavior().initPhysicPlayer(player1.getLogic(), this);
        
        ActorPlayer player2 = new ActorPlayerScrapper(this, "el Palankas");
        player2.getLogic().setPosition(400, 1);
        player2.getLogic().setPower(3f);
        player2.getBehavior().initPhysicPlayer(player2.getLogic(), this);
        
        actorsArray.add(player1);
        actorsArray.add(player2);*/
	}
	
	private void setBackground(){
		background = new Background();
        background.addLayer(Tools.loadBufferedImage("art" + File.separator
               + "nodrubaz" + File.separator + "plataformabackground.png"));
        background.addLayer(Tools.loadBufferedImage("art" + File.separator
                + "nodrubaz" + File.separator + "plataformabackground1.png"));
        background.addLayer(Tools.loadBufferedImage("art" + File.separator
                + "nodrubaz" + File.separator + "plataformamiddleground.png"));
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
