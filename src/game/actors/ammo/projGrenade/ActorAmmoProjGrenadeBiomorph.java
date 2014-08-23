package game.actors.ammo.projGrenade;

import game.actors.ActorPlayer;
import game.actors.ammo.projParabolic.ActorAmmoProjParabolic;
import game.stages.Stage;

import java.awt.Point;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import util.Constants;
import weapons.Explossion;

/*
 * Created on 26-abr-2005
 */

/**
 * @author Carlos Segovia
 */

/**
 * Clase que extiende al proyectil parabnhlico gennhrico y sirve para dar
 * funcionalidades concretas al proyectil granada de los biomorphs
 */
public class ActorAmmoProjGrenadeBiomorph extends ActorAmmoProjGrenade {

    private String route;

    private ArrayList bangAnim;

    /**
     * Constructor de la clase
     * 
     * @param stage
     *            Pantalla
     * @param owner
     *            Actor-personaje que dispara
     */
    public ActorAmmoProjGrenadeBiomorph(Stage stage, ActorPlayer owner,
            int power) {
        super(stage, owner);
        int damage=0;	
        int radius=0;
        
        //en funcion del power se configura de diferente manera
        switch (power) {

        //POWER 1
        case Constants.POW_1:
        	route = "art" + File.separator + "biomorph" + File.separator
            + "proyectiles" + File.separator + "rebote1.png";

            this.audioPlayer = new sound.AudioPlayer(stage.getAudioManager(),
                    this.getLogic(),
                    "sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeBiomorph1");
            
            damage=300;
            radius=100;
            
            break;

        //POWER 2
        case Constants.POW_2:
        	route = "art" + File.separator + "biomorph" + File.separator
            + "proyectiles" + File.separator + "rebote2.png";

            this.audioPlayer = new sound.AudioPlayer(stage.getAudioManager(),
                    this.getLogic(),
                    "sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeBiomorph1");
            
            damage=350;
            radius=125;
            
            break;

        //POWER 3
        case Constants.POW_3:
        	route = "art" + File.separator + "biomorph" + File.separator
            + "proyectiles" + File.separator + "rebote3.png";

            this.audioPlayer = new sound.AudioPlayer(stage.getAudioManager(),
                    this.getLogic(),
                    "sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeBiomorph1");

            damage=450;
            radius=125;
            
            break;

        //ARMAS SIN POWER
        default:
            break;
        }

        this.newExplosion(new Point(), Explossion.FRACTAL_POLYGON7, 10, 50 + (radius/20));

        bangAnim = new ArrayList(5);
        bangAnim.add("art" + File.separator + "bang0001.png");
        bangAnim.add("art" + File.separator + "bang0002.png");
        bangAnim.add("art" + File.separator + "bang0003.png");
        bangAnim.add("art" + File.separator + "bang0004.png");
        bangAnim.add("art" + File.separator + "bang0005.png");

        this.initLogic(40, 26, damage, radius);
        this.newFlyingAnim(route);
        this.newBangAnim(bangAnim);
    }
}