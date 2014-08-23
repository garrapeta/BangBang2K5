/*
 * Created on 16-may-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import game.Animation;
import java.io.File;

import network.ClientPlayer;

import util.Constants;
import util.IdAndRace;
import util.Tools;

/**
 * @author usuario_local
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class AnimSet {

	private TreeMap anims;

	private int frameDuration;

	public AnimSet() {
		anims = new TreeMap();
		frameDuration = 10;
	}

	public void init(List arrListOfIdsAndRaces) {
		System.out.println("Cargando animaciones de ActorPlayers:");

		for (int i = 0; i < arrListOfIdsAndRaces.size(); i++) {
			Iterator it = arrListOfIdsAndRaces.listIterator();
			ClientPlayer aux;
			while (it.hasNext()) {
				aux = (ClientPlayer) (it.next());
				switch (aux.getRace()) {
				case Constants.RACE_SCRAPER:
					//"Scraper"
					if (!anims.containsKey("scraper"))
						loadScraperAnims();
					break;
				case Constants.RACE_BIOMORPH:
					//"Biomorph"
					if (!anims.containsKey("biomorph"))
						loadBiomorphAnims();
					break;
				case Constants.RACE_GMARINE:
					//"GMarine"
					if (!anims.containsKey("gmarine"))
						loadGMarineAnims();
					break;
				case Constants.RACE_NINFA:
					//"Ninfa"
					if (!anims.containsKey("ninfa"))
						loadNinfaAnims();
					break;
				case Constants.RACE_ELEO:
					//"Eleo"
					if (!anims.containsKey("eleo"))
						loadEleoAnims();
					break;
				default:
				    System.out.println("initGameEngine - Raza desconocida");
	    			break;
				}
			}
		}
	}

	public TreeMap getAnims() {
		return anims;
	}

	//NO TOCAR YA estan bien los offsets
	
	private void loadBiomorphAnims() {
		TreeMap raceAnims = new TreeMap();
		System.out.print("\tanimaciones bioMorphicas ");

		//reposo
		Animation reposoAnim = new Animation();
		for (int i = 1; i < 10; i++)
			reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "biomorph_reposo000" + i + ".png"), frameDuration );
		    reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones" + File.separator+ "reposo" + File.separator + "biomorph_reposo0010.png"),frameDuration );
		
		    reposoAnim.setOffset(-5, -00);

		raceAnims.put("reposo", reposoAnim);
		System.out.print(".");

		//andando

		Animation andarAnim = new Animation();
		for (int i = 1; i < 10; i++)
			andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones"+ File.separator + "andar"  + File.separator + "BioMorphAndar000"+ i + ".png"), frameDuration / 5);
		for (int i = 10; i < 12; i++)
			andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones"+ File.separator + "andar" + File.separator + "BioMorphAndar00"+ i + ".png"), frameDuration / 5);

		andarAnim.setOffset(-40, -40);

		raceAnims.put("andar", andarAnim);
		System.out.print(".");

		//presalto

		Animation preSaltoAnim = new Animation();
		for (int i = 1; i < 10; i++)
			preSaltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator+ "animaciones" + File.separator + "salto" + File.separator+ "salto000" + i + ".png"), frameDuration / 5);
		for (int i = 10; i < 17; i++)
			preSaltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "salto" + File.separator+ "salto00" + i + ".png"), frameDuration / 10);
		preSaltoAnim.setOffset(-40, -40);

		raceAnims.put("preSalto", preSaltoAnim);
		System.out.print(".");

		//salto

		Animation saltoAnim = new Animation();
		for (int i = 16; i < 17; i++)
			saltoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones"+ File.separator + "salto" + File.separator + "salto00" + i+ ".png"), frameDuration / 5);

		saltoAnim.setOffset(-40, -40);

		raceAnims.put("salto", saltoAnim);
		System.out.print(".");

		//danho

		Animation danhoAnim = new Animation();
		String fileName;
		for (int i = 1; i < 18; i++) {

			if (i < 10)
				fileName = "BioMorphHerido000" + i + ".png";
			else
				fileName = "BioMorphHerido00" + i + ".png";

			danhoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones"+ File.separator + "herido" + File.separator + fileName), frameDuration / 3);
		}
		danhoAnim.setOffset(-40, -40);

		raceAnims.put("danho", danhoAnim);
		System.out.print(".");

		//muerte

		Animation muerteAnim = new Animation();
		for (int i = 1; i < 23; i++) {

			if (i < 10)
				fileName = "BioMorphMuere000" + i + ".png";
			else
				fileName = "BioMorphMuere00" + i + ".png";

			muerteAnim
					.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones"+ File.separator + "muerto" + File.separator+ fileName), frameDuration / 3);

		}
		muerteAnim.setOffset(-40, -40);

		raceAnims.put("muerte", muerteAnim);
		System.out.print(".");

		//melee1

		Animation melee1Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee1Anim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones"+ File.separator +"disparos"+ File.separator + "cuerpoaCuerpo" + File.separator+ "Nivel1" + File.separator + "cc1000" + i + ".png"),					frameDuration / 5);
		for (int i = 10; i < 30; i++)
			melee1Anim.addFrame(Tools.loadBufferedImage("art" + File.separator
					+ "biomorph" + File.separator + "animaciones"+ File.separator +	"disparos"+ File.separator + "cuerpoaCuerpo" + File.separator+ "Nivel1" + File.separator + "cc100" + i + ".png"),frameDuration / 5);

		melee1Anim.setOffset(-40, -40);

		raceAnims.put("melee1", melee1Anim);
		System.out.print(".");

		//melee2
		
		Animation melee2Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee2Anim.addFrame(Tools.loadBufferedImage("art" + File.separator
					+ "biomorph" + File.separator + "animaciones"+ File.separator +	"disparos"+ File.separator + "cuerpoaCuerpo" + File.separator+ "Nivel2" + File.separator + "cc2000" + i + ".png"),	frameDuration / 5);
		for (int i = 10; i < 20; i++)
			melee2Anim.addFrame(Tools.loadBufferedImage("art" + File.separator
					+ "biomorph" + File.separator + "animaciones"+ File.separator +	"disparos"	+ File.separator + "cuerpoaCuerpo" + File.separator	+ "Nivel2" + File.separator + "cc200" + i + ".png"),frameDuration / 5);

		melee2Anim.setOffset(-40, -40);

		raceAnims.put("melee2", melee2Anim);
		System.out.print(".");

		//melee3
		
		Animation melee3Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee3Anim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones"+ File.separator +"disparos"+ File.separator + "cuerpoaCuerpo" + File.separator+ "Nivel3" + File.separator + "cc3000" + i + ".png"),
					frameDuration / 5);
		for (int i = 10; i < 20; i++)
			melee3Anim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "biomorph" + File.separator + "animaciones"+ File.separator +"disparos"+ File.separator + "cuerpoaCuerpo" + File.separator+ "Nivel3" + File.separator + "cc300" + i + ".png"),
					frameDuration / 5);

		melee3Anim.setOffset(-40, -40);

		raceAnims.put("melee3", melee3Anim);
		System.out.print(".");

		
		//parabolic1

		Animation parabolic1Anim = new Animation();
		for (int i = 1; i < 10; i++)
			parabolic1Anim.addFrame(Tools.loadBufferedImage("art"+ File.separator + "biomorph" + File.separator	+ "animaciones" + File.separator + "disparos"+ File.separator + "parabolico"+ File.separator + "biomorph_escupe000" + i + ".png"),
					frameDuration / 7);
		for (int i = 10; i < 16; i++)
			parabolic1Anim.addFrame(Tools.loadBufferedImage("art"+ File.separator + "biomorph" + File.separator	+ "animaciones" + File.separator + "disparos"+ File.separator + "parabolico"+ File.separator + "biomorph_escupe00" + i + ".png"),
					frameDuration / 7);

		parabolic1Anim.setOffset(-40, -40);

		raceAnims.put("parabolic1", parabolic1Anim);
		System.out.print(".");

		//parabolic2
		//parabolic3
		
		//grenade1
		
		Animation grenade1Anim = new Animation();
		for (int i = 1; i < 10; i++)
			grenade1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "rebote"+ File.separator+ "nivel1"+ File.separator + "BioMorphRebote1_000" + i + ".png"),
					frameDuration / 5);
		for (int i = 10; i < 20; i++)
			grenade1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "rebote"+ File.separator+ "nivel1"+ File.separator + "BioMorphRebote1_00" + i + ".png"),
					frameDuration / 5);

		grenade1Anim.setOffset(-40, -40);

		raceAnims.put("grenade1", grenade1Anim);
		System.out.print(".");

		//grenade2
		
		Animation grenade2Anim = new Animation();
		for (int i = 1; i < 10; i++)
			grenade2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "rebote"+ File.separator+ "nivel2"+ File.separator + "BioMorphRebote2_000" + i + ".png"),
					frameDuration / 5);
		for (int i = 10; i < 20; i++)
			grenade2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "rebote"+ File.separator+ "nivel2"+ File.separator + "BioMorphRebote2_00" + i + ".png"),
					frameDuration / 5);

		grenade2Anim.setOffset(-40, -40);
		
		raceAnims.put("grenade2", grenade2Anim);
		System.out.print(".");


		//grenade3
		
		Animation grenade3Anim = new Animation();
		for (int i = 1; i < 10; i++)
			grenade3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "rebote"+ File.separator+ "nivel3"+ File.separator + "BioMorphRebote3_000" + i + ".png"),
					frameDuration / 5);
		for (int i = 10; i < 20; i++)
			grenade3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "rebote"+ File.separator+ "nivel3"+ File.separator + "BioMorphRebote3_00" + i + ".png"),
					frameDuration / 5);

		grenade3Anim.setOffset(-40, -40);
		
		raceAnims.put("grenade3", grenade3Anim);
		System.out.print(".");

		
		//rectilinear1
		
		Animation rectilinear1Anim = new Animation();
		for (int i = 1; i < 10; i++)
			rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "tiroRecto"+ File.separator + "biomorph_recto000" + i + ".png"),
					frameDuration / 5);
		for (int i = 1; i < 4; i++)
			rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "tiroRecto"+ File.separator + "biomorph_recto0014.png"),
					frameDuration / 5);
		
		rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "tiroRecto"+ File.separator + "biomorph_recto0027.png"),frameDuration / 5);
		rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "tiroRecto"+ File.separator + "biomorph_recto0027.png"),frameDuration / 5);
		rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "biomorph" + File.separator + "animaciones" + File.separator + "disparos"+ File.separator + "tiroRecto"+ File.separator + "biomorph_recto0027.png"),frameDuration / 5);
		



		rectilinear1Anim.setOffset(-40, -40);
		

		raceAnims.put("rectilinear1", rectilinear1Anim);
		System.out.print(".");
		//rectilinear2
		//rectilinear3

		anims.put("biomorph", raceAnims);
		System.out.println(" ok");
	}

    //NO TOCAR YA estan bien los offsets
	
	private void loadScraperAnims() {
		TreeMap raceAnims = new TreeMap();
		System.out.print("\tanimaciones scrapperianas ");
		
		

		//reposo2

		Animation reposoAnim2 = new Animation();
		for (int i = 5; i < 17; i++) 
			reposoAnim2.addFrame(Tools.loadBufferedImage("art" + File.separator	+ "scrapers" + File.separator + "animaciones"+ File.separator + "reposo" + File.separator + "reposo2"+ File.separator + "reposo2_" + i + ".png"), frameDuration/1);

		for (int i = 16; i > 2; i--) 
			reposoAnim2.addFrame(Tools.loadBufferedImage("art" + File.separator	+ "scrapers" + File.separator + "animaciones"+ File.separator + "reposo" + File.separator + "reposo2"+ File.separator + "reposo2_" + i + ".png"), frameDuration/1);

			

		
		reposoAnim2.setOffset(-12, 1);

		raceAnims.put("reposo2", reposoAnim2);
		System.out.print(".");

		//reposo
		Animation reposoAnim1 = new Animation();
		for (int i = 1; i < 29; i++) {
			reposoAnim1.addFrame(Tools.loadBufferedImage("art" + File.separator	+ "scrapers" + File.separator + "animaciones"+ File.separator + "reposo" + File.separator + "reposo1"+ File.separator + "reposo" + i + ".png"),
					frameDuration / 10);

		}
		reposoAnim1.setOffset(-12, 1);

		raceAnims.put("reposo1", reposoAnim1);
		System.out.print(".");



		//reposo3

		Animation reposoAnim3 = new Animation();
		for (int i = 1; i < 29; i++) {
			reposoAnim3.addFrame(Tools.loadBufferedImage("art" + File.separator	+ "scrapers" + File.separator + "animaciones"+ File.separator + "reposo" + File.separator + "reposo3"+ File.separator + "reposo" + i + ".png"),
					frameDuration / 10);

		}
		reposoAnim3.setOffset(-12, 1);

		raceAnims.put("reposo3", reposoAnim3);
		System.out.print(".");


		//andando

		Animation andarAnim = new Animation();
		andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones" + File.separator+ "andar" + File.separator + "scraper_anda" + File.separator	+ "scraper_anda0001.png"), frameDuration / 2);
		andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones" + File.separator	+ "andar" + File.separator + "scraper_anda" + File.separator+ "scraper_anda0002.png"), frameDuration / 2);
		andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones" + File.separator+ "andar" + File.separator + "scraper_anda" + File.separator	+ "scraper_anda0003.png"), frameDuration / 2);
		andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones" + File.separator+ "andar" + File.separator + "scraper_anda" + File.separator+ "scraper_anda0004.png"), frameDuration / 2);
		andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones" + File.separator	+ "andar" + File.separator + "scraper_anda" + File.separator+ "scraper_anda0005.png"), frameDuration / 2);
		andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones" + File.separator+ "andar" + File.separator + "scraper_anda" + File.separator	+ "scraper_anda0006.png"), frameDuration / 2);
		andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones" + File.separator	+ "andar" + File.separator + "scraper_anda" + File.separator+ "scraper_anda0007.png"), frameDuration / 2);
		andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones" + File.separator+ "andar" + File.separator + "scraper_anda" + File.separator	+ "scraper_anda0008.png"), frameDuration / 2);
		andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones" + File.separator+ "andar" + File.separator + "scraper_anda" + File.separator	+ "scraper_anda0009.png"), frameDuration / 2);
		andarAnim.setOffset(-12, 1);

		raceAnims.put("andar", andarAnim);
		System.out.print(".");

		//presalto

		Animation preSaltoAnim = new Animation();
		for (int i = 10; i < 15; i++) {
			preSaltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "scrapers" + File.separator	+ "animaciones" + File.separator + "salto" + File.separator	+ "salto" + i + ".png"), frameDuration / 3);

		}

		preSaltoAnim.setOffset(-12, 1);

		raceAnims.put("preSalto", preSaltoAnim);
		System.out.print(".");

		//salto

		Animation saltoAnim = new Animation();

		for (int i = 20; i < 21; i++) {
			saltoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones"	+ File.separator + "salto" + File.separator + "salto" + i+ ".png"), frameDuration / 10);
		}

		saltoAnim.setOffset(-12, 1);

		raceAnims.put("salto", saltoAnim);
		System.out.print(".");

		//danho

		Animation danhoAnim = new Animation();
		String fileName;
		for (int i = 1; i < 10; i++) 
			danhoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones"+ File.separator + "danio" + File.separator + "danio000"+i+".png"), 2);
		for (int i = 10; i < 19; i++) 
			danhoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones"+ File.separator + "danio" + File.separator + "danio00"+i+".png"), 2);
	
		danhoAnim.setOffset(-12, 1);

		raceAnims.put("danho", danhoAnim);
		System.out.print(".");

		//muerte

		Animation muerteAnim = new Animation();

		for (int i = 1; i < 52; i++) {

			if (i < 10)
				fileName = "muerte000" + i + ".png";
			else
				fileName = "muerte00" + i + ".png";

			if (i < 46) {
				muerteAnim.addFrame(Tools.loadBufferedImage("art"+ File.separator + "scrapers" + File.separator	+ "animaciones" + File.separator + "muerte"+ File.separator + fileName), 2);

			} else {
				muerteAnim.addFrame(Tools.loadBufferedImage("art" +File.separator + "scrapers" + File.separator	+ "animaciones" + File.separator + "muerte"+ File.separator + fileName), 2, -46, -33);
			}

		}
		muerteAnim.setOffset(-12, 1);
		raceAnims.put("muerte", muerteAnim);
		System.out.print(".");

		//melee1

		Animation melee1Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee1Anim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones"	+ File.separator + "disparos"+ File.separator + "cc" + File.separator+ "cc1" + File.separator + "enchufe000" + i + ".png"),
					frameDuration / 3);
		for (int i = 10; i < 28; i++)
		    melee1Anim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones"	+ File.separator + "disparos"+ File.separator + "cc" + File.separator+ "cc1" + File.separator + "enchufe00" + i + ".png"),
					frameDuration / 3);

		melee1Anim.setOffset(-5, 1);

		raceAnims.put("melee1", melee1Anim);
		System.out.print(".");

		//melee2
		
		Animation melee2Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee2Anim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones"	+ File.separator + "disparos"+ File.separator + "cc" + File.separator+ "cc2" + File.separator + "cuerpo2000" + i + ".png"),
					frameDuration / 3);
		for (int i = 10; i < 38; i++)
		    melee2Anim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones"	+ File.separator + "disparos"+ File.separator + "cc" + File.separator+ "cc2" + File.separator + "cuerpo200" + i + ".png"),
					frameDuration / 3);
		
		melee2Anim.setOffset(-5, 1);

		raceAnims.put("melee2", melee2Anim);
		System.out.print(".");

		//melee3
		
		Animation melee3Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee3Anim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones"	+ File.separator + "disparos"+ File.separator + "cc" + File.separator+ "cc3" + File.separator + "cuerpo3000" + i + ".png"),
					frameDuration / 3);
		for (int i = 10; i < 23; i++)
		    melee2Anim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "scrapers" + File.separator + "animaciones"	+ File.separator + "disparos"+ File.separator + "cc" + File.separator+ "cc3" + File.separator + "cuerpo300" + i + ".png"),
					frameDuration / 3);
		
		melee3Anim.setOffset(-5, 1);

		raceAnims.put("melee3", melee3Anim);
		System.out.print(".");

		//parabolic1

		Animation parabolic1Anim = new Animation();
		for (int i = 1; i < 15; i++) {
			parabolic1Anim.addFrame(Tools.loadBufferedImage("art"+ File.separator + "scrapers" + File.separator	+ "animaciones" + File.separator + "disparos"+ File.separator + "parabolico" + File.separator + "nivel1"+ File.separator + "disparo" + i + ".png"),
					frameDuration / 5);

		}
		parabolic1Anim.setOffset(-12, 1);

		raceAnims.put("parabolic1", parabolic1Anim);
		System.out.print(".");

		//parabolic2
		//parabolic3
		
		//grenade1
		
		Animation grenade1Anim = new Animation();
		for (int i = 1; i < 13; i++) {
		    grenade1Anim.addFrame(Tools.loadBufferedImage("art"+ File.separator + "scrapers" + File.separator	+ "animaciones" + File.separator + "disparos"+ File.separator + "rebote" + File.separator + "nivel1"+ File.separator + "Rebote1-" + i + ".png"),
					frameDuration / 3);

		}
		grenade1Anim.setOffset(-12, 1);

		raceAnims.put("grenade1", grenade1Anim);
		System.out.print(".");

		//grenade2		
		
		Animation grenade2Anim = new Animation();
		for (int i = 1; i < 17; i++) {
		    grenade2Anim.addFrame(Tools.loadBufferedImage("art"+ File.separator + "scrapers" + File.separator	+ "animaciones" + File.separator + "disparos"+ File.separator + "rebote" + File.separator + "nivel2"+ File.separator + "Rebote2-" + i + ".png"),
					frameDuration / 5);

		}
		grenade1Anim.setOffset(-12, 1);

		raceAnims.put("grenade2", grenade2Anim);
		System.out.print(".");


		//grenade3
		
		Animation grenade3Anim = new Animation();
		for (int i = 1; i < 16; i++) {
		    grenade3Anim.addFrame(Tools.loadBufferedImage("art"+ File.separator + "scrapers" + File.separator	+ "animaciones" + File.separator + "disparos"+ File.separator + "rebote" + File.separator + "nivel3"+ File.separator + "Rebote3-" + i + ".png"),
					frameDuration / 3);

		}
		grenade3Anim.setOffset(-12, 1);

		raceAnims.put("grenade3", grenade3Anim);
		System.out.print(".");

		//rectilinear1
		
		Animation rectilinear1Anim = new Animation();
		for (int i = 1; i < 16; i++) {
		    rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"+ File.separator + "scrapers" + File.separator	+ "animaciones" + File.separator + "disparos"+ File.separator + "rectilineo" + File.separator + i + ".png"),
					frameDuration / 5);

		}
		rectilinear1Anim.setOffset(-12, 1);

		raceAnims.put("rectilinear1", rectilinear1Anim);
		System.out.print(".");

		//rectilinear2
		//rectilinear3

		anims.put("scraper", raceAnims);
		System.out.println(" ok");
	}

	
	private void loadNinfaAnims() {
	    
		TreeMap raceAnims = new TreeMap();
		System.out.print("\tanimaciones ninfaticas ");

		//reposo
		Animation reposoAnim = new Animation();
		for (int i = 1; i < 40; i++)
			reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "pibitas" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "fuma_peta0001.png"), frameDuration / 2);

		for (int i = 1; i < 10; i++)
			reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "pibitas" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "fuma_peta000" + i + ".png"), frameDuration / 2);
		for (int i = 10; i < 26; i++)
			reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "pibitas" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "fuma_peta00" + i + ".png"), frameDuration / 2);		    
		
		reposoAnim.setOffset(-35, -20);

		raceAnims.put("reposo", reposoAnim);
		System.out.print(".");

		//andando

		Animation andarAnim = new Animation();
		for (int i = 3; i < 10; i++)
			andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "pibitas" + File.separator + "animaciones"+ File.separator + "andar"  + File.separator + "andar000"+ i + ".png"), frameDuration / 5);
		for (int i = 10; i < 13; i++)
			andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "pibitas" + File.separator + "animaciones"+ File.separator + "andar" + File.separator + "andar00"+ i + ".png"), frameDuration / 5);

		andarAnim.setOffset(-45, -20);

		raceAnims.put("andar", andarAnim);
		System.out.print(".");

		//presalto

		Animation preSaltoAnim = new Animation();
		for (int i = 1; i < 10; i++)
			preSaltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator+ "animaciones" + File.separator + "salto" + File.separator+ "ninfa_salto000" + i + ".png"), frameDuration / 5);
		for (int i = 10; i < 13; i++)
			preSaltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "salto" + File.separator+ "ninfa_salto00" + i + ".png"), frameDuration / 10);
		
		preSaltoAnim.setOffset(-45, -20);

		raceAnims.put("preSalto", preSaltoAnim);
		System.out.print(".");

		//salto

		Animation saltoAnim = new Animation();
		for (int i = 12; i < 13; i++)
			saltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "salto" + File.separator+ "ninfa_salto00" + i + ".png"), frameDuration / 5);

		saltoAnim.setOffset(-45, -20);

		raceAnims.put("salto", saltoAnim);
		System.out.print(".");

		//danho

		Animation danhoAnim = new Animation();

		for (int i = 3; i < 10; i++)
			danhoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "danio" + File.separator+ "danio000" + i + ".png"), frameDuration / 3);
		for (int i = 1; i < 5; i++)
			danhoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "danio" + File.separator+ "danio0009.png"), frameDuration / 3);
		for (int i = 10; i < 13; i++)
			danhoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "danio" + File.separator+ "danio00" + i + ".png"), frameDuration / 3);




		
		danhoAnim.setOffset(-45, -20);

		raceAnims.put("danho", danhoAnim);
		System.out.print(".");

		//muerte

		Animation muerteAnim = new Animation();
		for (int i = 1; i < 10; i++)
			muerteAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "muerte" + File.separator+ "muerte000" + i + ".png"), frameDuration / 2);
		for (int i = 10; i < 31; i++)
			muerteAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "muerte" + File.separator+ "muerte00" + i + ".png"), frameDuration / 2);
		
		muerteAnim.setOffset(-45, -20);

		raceAnims.put("muerte", muerteAnim);
		System.out.print(".");

		//melee1

		Animation melee1Anim = new Animation();
		
		for (int i = 1; i < 10; i++)
			melee1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+File.separator+ "ninfaCuerpoCuerpo1000" + i + ".png"), frameDuration / 4);
		for (int i = 10; i < 16; i++)
			melee1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+ File.separator+ "ninfaCuerpoCuerpo100" + i + ".png"), frameDuration / 4);

		melee1Anim.setOffset(-45, -20);

		raceAnims.put("melee1", melee1Anim);
		System.out.print(".");

		//melee2
		
		Animation melee2Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+File.separator+ "ninfaCuerpoCuerpo2000" + i + ".png"), frameDuration / 4);
		for (int i = 10; i < 18; i++)
			melee2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+ File.separator+ "ninfaCuerpoCuerpo200" + i + ".png"), frameDuration / 4);

		melee2Anim.setOffset(-45, -20);

		raceAnims.put("melee2", melee2Anim);
		System.out.print(".");

		//melee3
		
		Animation melee3Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+File.separator+ "ninfaCuerpoCuerpo3000" + i + ".png"), frameDuration / 4);
		for (int i = 10; i < 18; i++)
			melee3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+ File.separator+ "ninfaCuerpoCuerpo300" + i + ".png"), frameDuration / 4);

		melee3Anim.setOffset(-45, -20);

		raceAnims.put("melee3", melee3Anim);
		System.out.print(".");

		
		//parabolic1

		Animation parabolic1Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    parabolic1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+ "NinfaParabolico1000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 13; i++)
			parabolic1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+ File.separator+ "NinfaParabolico100" + i + ".png"), frameDuration / 3);

		parabolic1Anim.setOffset(-45, -20);

		raceAnims.put("parabolic1", parabolic1Anim);
		System.out.print(".");

		//parabolic2
		
		Animation parabolic2Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    parabolic2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+ "NinfasParabolico2000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 13; i++)
			parabolic2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+ File.separator+ "NinfasParabolico200" + i + ".png"), frameDuration / 3);

		parabolic2Anim.setOffset(-45, -20);

		raceAnims.put("parabolic2", parabolic2Anim);
		System.out.print(".");

		//parabolic3
		
		Animation parabolic3Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    parabolic3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+ "NinfaParabolico3000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 13; i++)
			parabolic3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+ File.separator+ "NinfaParabolico300" + i + ".png"), frameDuration / 3);

		parabolic3Anim.setOffset(-45, -20);

		raceAnims.put("parabolic3", parabolic3Anim);
		System.out.print(".");

		
		//grenade1
		
		Animation grenade1Anim = new Animation();
		for (int i = 1; i < 9; i++)
		    grenade1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+ "ninfa_nivel1_rebote000" + i + ".png"), frameDuration / 5);

		grenade1Anim.setOffset(-45, -20);

		raceAnims.put("grenade1", grenade1Anim);
		System.out.print(".");

		//grenade2
		
		Animation grenade2Anim = new Animation();
		for (int i = 1; i < 9; i++)
		    grenade2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+ "ninfa_nivel2_rebote000" + i + ".png"), frameDuration / 5);

		grenade2Anim.setOffset(-45, -20);
		
		raceAnims.put("grenade2", grenade2Anim);
		System.out.print(".");


		//grenade3
		
		Animation grenade3Anim = new Animation();
		for (int i = 1; i < 9; i++)
		    grenade3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+ "ninfa_nivel3_rebote000" + i + ".png"), frameDuration / 5);

		grenade3Anim.setOffset(-45, -20);
		
		raceAnims.put("grenade3", grenade3Anim);
		System.out.print(".");

		
		//rectilinear1
		
		Animation rectilinear1Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "recto"+File.separator+ "recto1000" + i + ".png"), frameDuration / 4);
		for (int i = 10; i < 36; i++)
		    rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "recto"+ File.separator+ "recto100" + i + ".png"), frameDuration / 4);

		rectilinear1Anim.setOffset(-45, -20);
		

		raceAnims.put("rectilinear1", rectilinear1Anim);
		System.out.print(".");

		//rectilinear2
		
		Animation rectilinear2Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    rectilinear2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "recto"+File.separator+ "recto2000" + i + ".png"), frameDuration / 4);
		for (int i = 10; i < 36; i++)
		    rectilinear2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "recto"+ File.separator+ "recto200" + i + ".png"), frameDuration / 4);

		rectilinear2Anim.setOffset(-45, -20);
		

		raceAnims.put("rectilinear2", rectilinear2Anim);
		System.out.print(".");

		//rectilinear3
		
		Animation rectilinear3Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    rectilinear3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "recto"+File.separator+ "recto3000" + i + ".png"), frameDuration / 4);
		for (int i = 10; i < 36; i++)
		    rectilinear3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "pibitas" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "recto"+ File.separator+ "recto300" + i + ".png"), frameDuration / 4);

		rectilinear3Anim.setOffset(-45, -20);
		

		raceAnims.put("rectilinear3", rectilinear3Anim);
		System.out.print(".");

		
		

		anims.put("ninfa", raceAnims);
		System.out.println(" ok");


	}
	
	


	//GMARINES YA ESTAN BIEN EXCEPTO EL OFFSET DE SALTO
	
	private void loadGMarineAnims() {
	    
		TreeMap raceAnims = new TreeMap();
		System.out.print("\tanimaciones gmarineras ");

		//reposo
		Animation reposoAnim = new Animation();
		for (int i = 1; i < 10; i++)
			reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "gmarine" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "GMarineReposo000" + i + ".png"), frameDuration*2);

		reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "gmarine" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "GMarineReposo0010.png"), frameDuration*2);

		for (int i = 9; i > 0; i--)
			reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "gmarine" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "GMarineReposo000" + i + ".png"), frameDuration*2);


    
				    
	    reposoAnim.setOffset(-45, -15);

		raceAnims.put("reposo", reposoAnim);
		System.out.print(".");

		//andando

		Animation andarAnim = new Animation();
		for (int i = 1; i < 10; i++)
			andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "gmarine" + File.separator + "animaciones"+ File.separator + "andar"  + File.separator + "andar000"+ i + ".png"), frameDuration / 5);
		for (int i = 10; i < 14; i++)
			andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "gmarine" + File.separator + "animaciones"+ File.separator + "andar" + File.separator + "andar00"+ i + ".png"), frameDuration / 5);

		andarAnim.setOffset(-45, -15);

		raceAnims.put("andar", andarAnim);
		System.out.print(".");

		//presalto

		Animation preSaltoAnim = new Animation();
		for (int i = 1; i < 10; i++)
			preSaltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator+ "animaciones" + File.separator + "salto" +  File.separator+"marine_despega000" + i + ".png"), frameDuration / 5);

		
		preSaltoAnim.setOffset(-40, -45);

		raceAnims.put("preSalto", preSaltoAnim);
		System.out.print(".");

		//salto

		Animation saltoAnim = new Animation();
		saltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator+ "animaciones" + File.separator + "salto" +  File.separator+ "marine_despega0009.png"), frameDuration / 5);
		saltoAnim.setOffset(-40, -45);

		raceAnims.put("salto", saltoAnim);
		System.out.print(".");

		//danho

		Animation danhoAnim = new Animation();

		for (int i = 1; i < 4; i++)
			danhoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "danio" + File.separator+ "danio000" + i + ".png"), frameDuration / 3);
		for (int i = 1; i < 3; i++)
			danhoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "danio" + File.separator+ "danio0004.png"), frameDuration / 3);
		for (int i = 4; i < 10; i++)
			danhoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "danio" + File.separator+ "danio0004.png"), frameDuration / 3);


		
		danhoAnim.setOffset(-45, -15);

		raceAnims.put("danho", danhoAnim);
		System.out.print(".");

		//muerte

		Animation muerteAnim = new Animation();
		for (int i = 1; i < 10; i++)
			muerteAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "muerte" + File.separator+ "marine_muerte000" + i + ".png"), frameDuration/2);
		for (int i = 10; i < 19; i++)
			muerteAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "muerte" + File.separator+ "marine_muerte00" + i + ".png"), frameDuration/2);
		
		muerteAnim.setOffset(-45, -15);

		raceAnims.put("muerte", muerteAnim);
		System.out.print(".");

		//melee1

		Animation melee1Anim = new Animation();
		
		for (int i = 1; i < 10; i++)
			melee1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+File.separator+"nivel1"+File.separator+ "marine_cc1_000" + i + ".png"),2);
		for (int i = 10; i < 20; i++)
			melee1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+ File.separator+"nivel1"+File.separator+ "marine_cc1_00" + i + ".png"), 2);

		melee1Anim.setOffset(-65, -35);

		raceAnims.put("melee1", melee1Anim);
		System.out.print(".");

		//melee2
		
		Animation melee2Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+File.separator+"nivel2"+File.separator+ "marine_cc2_000" + i + ".png"),2);
		for (int i = 10; i < 20; i++)
			melee2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+ File.separator+"nivel2"+File.separator+ "marine_cc2_00" + i + ".png"),2);

		melee2Anim.setOffset(-65, -35);

		raceAnims.put("melee2", melee2Anim);
		System.out.print(".");

		//melee3
		
		Animation melee3Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+File.separator+"nivel3"+File.separator+ "marine_cc3_000" + i + ".png"),2);
		for (int i = 10; i < 20; i++)
			melee3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+ File.separator+"nivel3"+File.separator+ "marine_cc3_00" + i + ".png"), 2);

		melee3Anim.setOffset(-65, -35);

		raceAnims.put("melee3", melee3Anim);
		System.out.print(".");

		
		//parabolic1

		Animation parabolic1Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    parabolic1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+"nivel1"+File.separator+ "gmarineParabolico1000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 12; i++)
			parabolic1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+"nivel1"+File.separator+ "gmarineParabolico100" + i + ".png"), frameDuration / 3);

		parabolic1Anim.setOffset(-45, -25);

		raceAnims.put("parabolic1", parabolic1Anim);
		System.out.print(".");

		//parabolic2
		
		Animation parabolic2Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    parabolic2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+"nivel2"+File.separator+ "gmarineParabolico2000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 12; i++)
			parabolic2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+"nivel2"+File.separator+ "gmarineParabolico200" + i + ".png"), frameDuration / 3);

		parabolic2Anim.setOffset(-45, -25);

		raceAnims.put("parabolic2", parabolic2Anim);
		System.out.print(".");

		//parabolic3
		
		Animation parabolic3Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    parabolic3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+"nivel3"+File.separator+ "gmarineParabolico3000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 13; i++)
			parabolic3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+"nivel3"+File.separator+ "gmarineParabolico300" + i + ".png"), frameDuration / 3);

		parabolic3Anim.setOffset(-45, -25);

		raceAnims.put("parabolic3", parabolic3Anim);
		System.out.print(".");

		
		//grenade1
		
		Animation grenade1Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    grenade1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"nivel1"+File.separator+ "GMarineRebote1000" + i + ".png"), frameDuration / 5);
		for (int i = 10; i < 16; i++)
			grenade1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"nivel1"+File.separator+ "GMarineRebote100" + i + ".png"), frameDuration / 5);

		grenade1Anim.setOffset(-45, -15);

		raceAnims.put("grenade1", grenade1Anim);
		System.out.print(".");

		//grenade2
		
		Animation grenade2Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    grenade2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"nivel2"+File.separator+ "GMarineRebote2000" + i + ".png"), frameDuration / 5);
		for (int i = 10; i < 16; i++)
			grenade2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"nivel2"+File.separator+ "GMarineRebote200" + i + ".png"), frameDuration / 5);

		grenade2Anim.setOffset(-45, -15);
		
		raceAnims.put("grenade2", grenade2Anim);
		System.out.print(".");


		//grenade3
		
		Animation grenade3Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    grenade3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"nivel3"+File.separator+ "GMarineRebote3000" + i + ".png"), frameDuration / 5);
		for (int i = 10; i < 16; i++)
		    grenade3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+ File.separator+"nivel3" + File.separator+ "GMarineRebote300" + i + ".png"), frameDuration / 5);

		grenade3Anim.setOffset(-45, -15);
		
		raceAnims.put("grenade3", grenade3Anim);
		System.out.print(".");

		
		//rectilinear1
		
		Animation rectilinear1Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiro_recto"+File.separator+"nivel1"+File.separator+ "marine_recto1_000" + i + ".png"), frameDuration / 5);
		for (int i = 10; i < 21; i++)
			rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiro_recto"+File.separator+"nivel1"+File.separator+ "marine_recto1_00" + i + ".png"), frameDuration / 5);

		rectilinear1Anim.setOffset(-40, -40);
		

		raceAnims.put("rectilinear1", rectilinear1Anim);
		System.out.print(".");

		//rectilinear2
		
		Animation rectilinear2Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    rectilinear2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiro_recto"+File.separator+"nivel2"+File.separator+ "marine_recto2_000" + i + ".png"), frameDuration / 5);
		for (int i = 10; i < 21; i++)
			rectilinear2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiro_recto"+File.separator+"nivel2"+File.separator+ "marine_recto2_00" + i + ".png"), frameDuration / 5);

		rectilinear2Anim.setOffset(-40, -40);
		

		raceAnims.put("rectilinear2", rectilinear2Anim);
		System.out.print(".");

		//rectilinear3
		
		Animation rectilinear3Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    rectilinear3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiro_recto"+File.separator+"nivel3"+File.separator+ "marine_recto3_000" + i + ".png"), frameDuration / 5);
		for (int i = 10; i < 21; i++)
			rectilinear3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "gmarine" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiro_recto"+File.separator+"nivel3"+File.separator+ "marine_recto3_00" + i + ".png"), frameDuration / 5);		

		rectilinear3Anim.setOffset(-40, -40);
		
		
		raceAnims.put("rectilinear3", rectilinear3Anim);
		System.out.print(".");

		
		

		anims.put("gmarine", raceAnims);
		System.out.println(" ok");

  
	}

	private void loadEleoAnims() {
		
		TreeMap raceAnims = new TreeMap();
		System.out.print("\tanimaciones elicas ");

		//reposo
		Animation reposoAnim = new Animation();
		for (int i = 1; i < 10; i++)
			reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "eleos" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "reposo000" + i + ".png"), frameDuration / 1);		    

		for (int i = 10; i < 17; i++)
			reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "eleos" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "reposo00" + i + ".png"), frameDuration / 1);
		
		for (int i = 1; i < 4; i++)
			reposoAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "eleos" + File.separator + "animaciones"	+ File.separator + "reposo" + File.separator+ "reposo0016.png"), frameDuration / 1);

	
	    reposoAnim.setOffset(-30, -15);

		raceAnims.put("reposo", reposoAnim);
		System.out.print(".");

		//andando

		Animation andarAnim = new Animation();
		for (int i = 1; i < 10; i++)
			andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "eleos" + File.separator + "animaciones"+ File.separator + "andar"  + File.separator + "EleoAndando000"+ i + ".png"), frameDuration / 5);
		for (int i = 10; i < 11; i++)
			andarAnim.addFrame(Tools.loadBufferedImage("art" + File.separator+ "eleos" + File.separator + "animaciones"+ File.separator + "andar" + File.separator + "EleoAndando00"+ i + ".png"), frameDuration / 5);

		andarAnim.setOffset(-33, -15);

		raceAnims.put("andar", andarAnim);
		System.out.print(".");

		//presalto

		Animation preSaltoAnim = new Animation();
		for (int i = 1; i < 8; i++)
			preSaltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator+ "animaciones" + File.separator + "salto" + File.separator+ "eleo_despega"+ File.separator+"eleo_despega000" + i + ".png"), frameDuration / 5);

		preSaltoAnim.setOffset(-30, -15);

		raceAnims.put("preSalto", preSaltoAnim);
		System.out.print(".");

		//salto

		Animation saltoAnim = new Animation();
		for (int i = 7; i < 8; i++)
			saltoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator+ "animaciones" + File.separator + "salto" + File.separator+ "eleo_despega"+ File.separator+"eleo_despega000" + i + ".png"), frameDuration / 5);

		saltoAnim.setOffset(-30, -15);

		raceAnims.put("salto", saltoAnim);
		System.out.print(".");

		//danho

		Animation danhoAnim = new Animation();

		for (int i = 1; i < 10; i++)
			danhoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "danio" + File.separator+ "danio000" + i + ".png"), frameDuration / 4);
		for (int i = 1; i < 5; i++)
			danhoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "danio" + File.separator+ "danio0009.png"), frameDuration / 4);

		for (int i = 10; i < 13; i++)
			danhoAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "danio" + File.separator+ "danio00" + i + ".png"), frameDuration / 4);

		
		danhoAnim.setOffset(-30, -15);

		raceAnims.put("danho", danhoAnim);
		System.out.print(".");

		//muerte

		Animation muerteAnim = new Animation();
		for (int i = 1; i < 10; i++)
			muerteAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "muerte" + File.separator+ "muerte000" + i + ".png"), frameDuration / 4);
		for (int i = 10; i < 29; i++)
			muerteAnim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "muerte" + File.separator+ "muerte00" + i + ".png"), frameDuration / 4);
		
		muerteAnim.setOffset(-30, -15);

		raceAnims.put("muerte", muerteAnim);
		System.out.print(".");

		//melee1

		Animation melee1Anim = new Animation();
		
		for (int i = 1; i < 10; i++)
			melee1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+File.separator+ "nivel1"+File.separator+"eleo_nivel1_cuerpo000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 19; i++)
			melee1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+ File.separator+ "nivel1"+File.separator+"eleo_nivel1_cuerpo00" + i + ".png"), frameDuration / 3);

		melee1Anim.setOffset(-25, -20);

		raceAnims.put("melee1", melee1Anim);
		System.out.print(".");

		//melee2
		
		Animation melee2Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+File.separator+ "nivel2"+File.separator+"eleo_nivel2_cuerpo000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 19; i++)
			melee2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+ File.separator+ "nivel2"+File.separator+"eleo_nivel2_cuerpo00" + i + ".png"), frameDuration / 3);

		melee2Anim.setOffset(-25, -20);

		raceAnims.put("melee2", melee2Anim);
		System.out.print(".");

		//melee3
		
		Animation melee3Anim = new Animation();
		for (int i = 1; i < 10; i++)
			melee3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+File.separator+ "nivel3"+File.separator+"eleo_nivel3_cuerpo000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 19; i++)
			melee3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "cc"+ File.separator+ "nivel3"+File.separator+"eleo_nivel3_cuerpo00" + i + ".png"), frameDuration / 3);

		melee3Anim.setOffset(-25, -20);

		raceAnims.put("melee3", melee3Anim);
		System.out.print(".");

		
		//parabolic1

		Animation parabolic1Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    parabolic1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+"nivel1"+File.separator+ "eleo_nivel1_impactante000" + i + ".png"), frameDuration / 3);

		parabolic1Anim.setOffset(-25, -20);

		raceAnims.put("parabolic1", parabolic1Anim);
		System.out.print(".");

		//parabolic2
		
		Animation parabolic2Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    parabolic2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+"nivel2"+File.separator+ "eleo_nivel2_impactante000" + i + ".png"), frameDuration / 3);

		parabolic2Anim.setOffset(-25, -20);

		raceAnims.put("parabolic2", parabolic2Anim);
		System.out.print(".");

		//parabolic3
		
		Animation parabolic3Anim = new Animation();
		for (int i = 1; i < 10; i++)
		    parabolic3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "parabolico"+File.separator+"nivel3"+File.separator+ "eleo_nivel3_impactante000" + i + ".png"), frameDuration / 3);

		parabolic3Anim.setOffset(-25, -20);

		raceAnims.put("parabolic3", parabolic3Anim);
		System.out.print(".");

		
		//grenade1
		
		Animation grenade1Anim = new Animation();
		for (int i = 15; i < 27; i++)
		    grenade1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"rebote1"+File.separator+ "rebote00" + i + ".png"), frameDuration / 4);
		for (int i = 1; i < 3; i++)
		    grenade1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"rebote1"+File.separator+ "rebote0027.png"), frameDuration / 4);


		grenade1Anim.setOffset(-25, -15);

		raceAnims.put("grenade1", grenade1Anim);
		System.out.print(".");

		//grenade2
		
		Animation grenade2Anim = new Animation();
		for (int i = 15; i < 27; i++)
		    grenade2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"rebote2"+File.separator+ "rebote200" + i + ".png"), frameDuration / 4);
		for (int i = 1; i < 3; i++)
		    grenade2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"rebote2"+File.separator+ "rebote20027.png"), frameDuration / 4);


		grenade2Anim.setOffset(-25, -15);
		
		raceAnims.put("grenade2", grenade2Anim);
		System.out.print(".");


		//grenade3
		
		Animation grenade3Anim = new Animation();
		for (int i = 15; i < 27; i++)
		    grenade3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"rebote3"+File.separator+ "rebote300" + i + ".png"), frameDuration / 4);
		for (int i = 1; i < 3; i++)
		    grenade3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "rebote"+File.separator+"rebote3"+File.separator+ "rebote30027.png"), frameDuration / 4);


		grenade3Anim.setOffset(-25, -15);
		
		raceAnims.put("grenade3", grenade3Anim);
		System.out.print(".");

		
		//rectilinear1
		
		Animation rectilinear1Anim = new Animation();
		for (int i = 1; i < 10; i++)
			rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiroRecto"+File.separator+"nivel1"+File.separator+ "eleoRecto1000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 12; i++)
			rectilinear1Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiroRecto"+File.separator+"nivel1"+File.separator+ "eleoRecto100" + i + ".png"), frameDuration / 3);


		rectilinear1Anim.setOffset(-30, -15);
		

		raceAnims.put("rectilinear1", rectilinear1Anim);
		System.out.print(".");

		//rectilinear2
		
		Animation rectilinear2Anim = new Animation();

		for (int i = 1; i < 10; i++)
			rectilinear2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiroRecto"+File.separator+"nivel2"+File.separator+ "eleoRecto2000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 12; i++)
			rectilinear2Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiroRecto"+File.separator+"nivel2"+File.separator+ "eleoRecto200" + i + ".png"), frameDuration / 3);

		rectilinear2Anim.setOffset(-30, -15);
		

		raceAnims.put("rectilinear2", rectilinear2Anim);
		System.out.print(".");

		//rectilinear3
		
		Animation rectilinear3Anim = new Animation();
		for (int i = 1; i < 10; i++)
			rectilinear3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiroRecto"+File.separator+"nivel3"+File.separator+ "eleoRecto3000" + i + ".png"), frameDuration / 3);
		for (int i = 10; i < 12; i++)
			rectilinear3Anim.addFrame(Tools.loadBufferedImage("art"	+ File.separator + "eleos" + File.separator + "animaciones" + File.separator + "disparos" + File.separator + "tiroRecto"+File.separator+"nivel3"+File.separator+ "eleoRecto300" + i + ".png"), frameDuration / 3);

		rectilinear3Anim.setOffset(-30, -15);
		

		raceAnims.put("rectilinear3", rectilinear3Anim);
		System.out.print(".");

			

		anims.put("eleo", raceAnims);
		System.out.println(" ok");

	}
}