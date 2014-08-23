package game.actors.ammo.projParabolic;

import game.actors.ActorPlayer;
import game.actors.ammo.projGrenade.ActorAmmoProjGrenade;
import game.stages.Stage;

import java.awt.Point;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import util.Constants;
import weapons.Explossion;

/*
 * Created on 25-abr-2005
 */

/**
 * @author Carlos Segovia
 */

/**
 * Clase que extiende al proyectil parabnhlico gennhrico y sirve 
 * para dar funcionalidades concretas al proyectil parabnhlico
 * de las ninfas 
 */
public class ActorAmmoProjParabolicNinfa extends ActorAmmoProjParabolic {

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
    public ActorAmmoProjParabolicNinfa(Stage stage, ActorPlayer owner,
            int power) {
        super(stage, owner);
        int damage=0;	
        int radius=0;

        //en funcion del power se configura de diferente manera
        switch (power) {

        //POWER 1
        case Constants.POW_1:
        	route = "art" + File.separator + "pibitas" + File.separator
            + "proyectiles" + File.separator + "parabolico1.png";

            this.audioPlayer = new sound.AudioPlayer(stage.getAudioManager(),
                    this.getLogic(),
                    "sound.audioProperties.AudioPropertiesGenericParabolic");


            damage=200;
            radius=100;
            
            break;

        //POWER 2
        case Constants.POW_2:
        	route = "art" + File.separator + "pibitas" + File.separator
            + "proyectiles" + File.separator + "parabolico2.png";

            this.audioPlayer = new sound.AudioPlayer(stage.getAudioManager(),
                    this.getLogic(),
                    "sound.audioProperties.AudioPropertiesGenericParabolic");

            damage=250;
            radius=125;
            
            break;

        //POWER 3
        case Constants.POW_3:
        	route = "art" + File.separator + "pibitas" + File.separator
            + "proyectiles" + File.separator + "parabolico3.png";

            this.audioPlayer = new sound.AudioPlayer(stage.getAudioManager(),
                    this.getLogic(),
                    "sound.audioProperties.AudioPropertiesGenericParabolic");


            damage=350;
            radius=175;
            
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