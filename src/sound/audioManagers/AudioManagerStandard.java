/*
 * Created on 05-mar-2005
 *
 */
package sound.audioManagers;

import java.io.File;
import java.util.ArrayList;

/**
 * @author GaRRaPeTa
 *  
 */
public class AudioManagerStandard extends AudioManager {

    public AudioManagerStandard(ArrayList playlist) {
        super(playlist);
        init();
    }

    public void init() {
        System.out.println("Cargando el AudioManager (dios del ruido):");
        initActorProperties();
        initNonActorSamples();
        this.setSoundOn(true);
        this.setMusicOn(true);
        this.setSoundVolume((float) 0.8);
        this.setMusicVolume((float) 0.3);

        //printInfo();
    }

    private void initActorProperties() {
        //PLAYERS
        
        loadProperties("sound.audioProperties.AudioPropertiesScraper");
        loadProperties("sound.audioProperties.AudioPropertiesBiomorph");
        loadProperties("sound.audioProperties.AudioPropertiesGMarine");
        loadProperties("sound.audioProperties.AudioPropertiesEleo");
        loadProperties("sound.audioProperties.AudioPropertiesNinfa");
        
        //GENERICOS
        
        loadProperties("sound.audioProperties.AudioPropertiesParabolicBiomorph");
        loadProperties("sound.audioProperties.AudioPropertiesGenericGrenade");
        loadProperties("sound.audioProperties.AudioPropertiesGenericRectilinear");
        loadProperties("sound.audioProperties.AudioPropertiesGenericParabolic");

        //AMMO SCRAPER
        
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearScrapper1");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearScrapper2");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearScrapper3");
        
        loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicScrapper1");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicScrapper2");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicScrapper3");
        
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeScrapper1");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeScrapper2");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeScrapper3");
        

        //AMMO BIOMORPH
        
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearBiomorph1");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearBiomorph2");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearBiomorph3");
        
        loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeBiomorph1");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicBiomorph2");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicBiomorph3");
        
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeBiomorph1");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeBiomorph2");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeSBiomorph3");
        

        // AMMO MARINE
        
        loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesRectilinearGMarine");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearG_Marine2");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearG_Marine3");
        
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicG_Marine1");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicG_Marine2");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicG_Marine3");
        
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeG_Marine1");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeG_Marine2");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeG_Marine3");
        

        //AMMO ELEO
        
        loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesRectilinearEleo");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearEleo2");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearEleo3");
        
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicEleo1");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicEleo2");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicEleo3");
        
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeEleo1");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeEleo2");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeEleo3");
        

        //AMMO NINFA
        
        loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesRectilinearNinfa");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearNinfa2");
        //loadProperties("sound.audioProperties.projRectilinear.AudioPropertiesProjRectilinearNinfa3");
        
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicNinfa1");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicNinfa2");
        //loadProperties("sound.audioProperties.projParabolic.AudioPropertiesProjParabolicNinfa3");
        
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeNinfa1");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeNinfa2");
        //loadProperties("sound.audioProperties.projGrenade.AudioPropertiesProjGrenadeNinfa3");
                
    }

    private void initNonActorSamples() {
        System.out.print("\tsamples estaticos ");
        

        this.loadNonActorSample(new File("soundFiles" + File.separator
                + "timeBeep.wav"), "timeBeep");

        this.loadNonActorSample(new File("soundFiles" + File.separator
                + "quack.wav"), "quack");

        this.loadNonActorSample(new File("soundFiles" + File.separator
                + "whistleup.wav"), "whistleUp");

        this.loadNonActorSample(new File("soundFiles" + File.separator
                + "whistledown.wav"), "whistleDown");
        
        System.out.println(" ok");
    }
}