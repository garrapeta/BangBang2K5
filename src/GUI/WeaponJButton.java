/*
 * Created on 12-may-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package GUI;
import game.actors.ActorPlayer;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import util.ColoursDefinitions;
import util.Constants;
import util.Tools;

/**
 * @author Moe
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WeaponJButton extends JButton {

	private static Border normalBorder;

	private static Border selectedBorder = BorderFactory.createLineBorder(
			Color.YELLOW, 2);

	private static WeaponJButton buttonActivated;

	private static int numberOfLevels = 3;

	//scrappers
	
	private static ArrayList imagesScraperMelee = new ArrayList(numberOfLevels);//tiro

	// de
	// cerca

	private static ArrayList imagesScraperParabolic = new ArrayList(
			numberOfLevels);//Parabolico

	private static ArrayList imagesScraperGrenade = new ArrayList(
			numberOfLevels);//el de rebote

	private static ArrayList imagesScraperRectilinear = new ArrayList(
			numberOfLevels);//Tiro recto

	private static ImageIcon imageScraperSpecial;

	//gmarines
	
	private static ArrayList imagesGmarineMelee = new ArrayList(
			numberOfLevels);//De cerca
	
	private static ArrayList imagesGmarineParabolic = new ArrayList(
			numberOfLevels);//Parabolico

	private static ArrayList imagesGmarineGrenade = new ArrayList(
			numberOfLevels);//el de rebote

	private static ArrayList imagesGmarineRectilinear = new ArrayList(
			numberOfLevels);//Tiro recto

	private static ImageIcon imageGmarineSpecial;
	
    //eleos
	
	private static ArrayList imagesEleoMelee = new ArrayList(
			numberOfLevels);//De cerca
	
	private static ArrayList imagesEleoParabolic = new ArrayList(
			numberOfLevels);//Parabolico

	private static ArrayList imagesEleoGrenade = new ArrayList(
			numberOfLevels);//el de rebote

	private static ArrayList imagesEleoRectilinear = new ArrayList(
			numberOfLevels);//Tiro recto

	private static ImageIcon imagesEleoSpecial;
	
	
	//ninfas
	
	private static ArrayList imagesNinfasMelee = new ArrayList(
			numberOfLevels);//De cerca
	
	private static ArrayList imagesNinfasParabolic = new ArrayList(
			numberOfLevels);//Parabolico

	private static ArrayList imagesNinfasGrenade = new ArrayList(
			numberOfLevels);//el de rebote

	private static ArrayList imagesNinfasRectilinear = new ArrayList(
			numberOfLevels);//Tiro recto

	private static ImageIcon imagesNinfasSpecial;


	//biomorphs
	
	private static ArrayList imagesBiomorphMelee = new ArrayList(numberOfLevels);//tiro

	// de
	// cerca

	private static ArrayList imagesBiomorphParabolic = new ArrayList(
			numberOfLevels);//Parabolico

	private static ArrayList imagesBiomorphGrenade = new ArrayList(
			numberOfLevels);//el de rebote

	private static ArrayList imagesBiomorphRectilinear = new ArrayList(
			numberOfLevels);//Tiro recto

	private static ImageIcon imageBiomorphSpecial;

	static {
		initImages();
	}

	private static ActorPlayer player;

	private int projectileType;

	private static int race;

	// Nivel del arma. Empieza desde 1.
	private int currentLevel;

	public WeaponJButton(int pType, ActorPlayer p) {

		initWeaponJButton(pType, p);
	}

	public void initWeaponJButton(int pType, ActorPlayer p) {

		this.setPlayer(p);
		projectileType = pType;
		//images=imagesScraper;

		this.setSize(50, 50);
		this.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
		this.setFocusable(false);

		this.addActionListener(new WeaponJButtonListener());
		this.setCurrentLevel(1);
	}

	private static void initImages() {

		//Botones del scraper

		imagesScraperMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "scrapers" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonScrapersCerca1.png")));
		imagesScraperMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "scrapers" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonScrapersCerca2.png")));
		imagesScraperMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "scrapers" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonScrapersCerca3.png")));

		imagesScraperParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "scrapers" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonScrapersParabolico1.png")));
		imagesScraperParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "scrapers" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonScrapersParabolico2.png")));
		imagesScraperParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "scrapers" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonScrapersParabolico3.png")));

		imagesScraperGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "scrapers"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonScrapersRebote1.png")));
		imagesScraperGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "scrapers"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonScrapersRebote2.png")));
		imagesScraperGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "scrapers"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonScrapersRebote3.png")));

		imagesScraperRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "scrapers"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonScrapersRecto1.png")));
		imagesScraperRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "scrapers"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonScrapersRecto2.png")));
		imagesScraperRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "scrapers"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonScrapersRecto3.png")));

		imageScraperSpecial = new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "scrapers" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonScrapersEspecial.png"));

		//Botones del Gmarine
		
		imagesGmarineMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "gmarine" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonGMarineCerca1.png")));
		imagesGmarineMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "gmarine" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonGMarineCerca2.png")));
		imagesGmarineMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "gmarine" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonGMarineCerca3.png")));

		imagesGmarineParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "gmarine" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonGMarineParabolico1.png")));
		imagesGmarineParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "gmarine" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonGMarineParabolico2.png")));
		imagesGmarineParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "gmarine" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonGMarineParabolico3.png")));

		imagesGmarineGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "gmarine"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonGMarineRebote1.png")));
		imagesGmarineGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "gmarine"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonGMarineRebote2.png")));
		imagesGmarineGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "gmarine"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonGMarineRebote3.png")));

		imagesGmarineRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "gmarine"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonGMarineRecto1.png")));
		imagesGmarineRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "gmarine"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonGMarineRecto2.png")));
		imagesGmarineRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "gmarine"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonGMarineRecto3.png")));

		imageGmarineSpecial = new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "gmarine" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonGMarineRecto1.png"));
		
        //Botones de las ninfas
		
		imagesNinfasMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "pibitas" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonNinfaCerca1.png")));
		imagesNinfasMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "pibitas" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonNinfaCerca2.png")));
		imagesNinfasMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "pibitas" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonNinfaCerca3.png")));

		imagesNinfasParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "pibitas" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonNinfaParabolico1.png")));
		imagesNinfasParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "pibitas" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonNinfaParabolico2.png")));
		imagesNinfasParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "pibitas" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonNinfaParabolico3.png")));

		imagesNinfasGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "pibitas"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonNinfaRebote1.png")));
		imagesNinfasGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "pibitas"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonNinfaRebote2.png")));
		imagesNinfasGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "pibitas"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonNinfaRebote3.png")));

		imagesNinfasRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "pibitas"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonNinfaRecto1.png")));
		imagesNinfasRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "pibitas"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonNinfaRecto2.png")));
		imagesNinfasRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "pibitas"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonNinfaRecto3.png")));

		imagesNinfasSpecial = new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "pibitas" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonNinfaRecto1.png"));

        //Botones de los eleos
		
		imagesEleoMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "eleos" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonEleoCerca1.png")));
		imagesEleoMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "eleos" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonEleoCerca2.png")));
		imagesEleoMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "eleos" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonEleoCerca3.png")));

		imagesEleoParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "eleos" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonEleoParabolico1.png")));
		imagesEleoParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "eleos" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonEleoParabolico2.png")));
		imagesEleoParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "eleos" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonEleoParabolico3.png")));

		imagesEleoGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "eleos"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonEleoRebote1.png")));
		imagesEleoGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "eleos"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonEleoRebote2.png")));
		imagesEleoGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "eleos"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonEleoRebote3.png")));

		imagesEleoRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "eleos"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonEleoRecto1.png")));
		imagesEleoRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "eleos"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonEleoRecto2.png")));
		imagesEleoRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "eleos"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonEleoRecto3.png")));

		imagesEleoSpecial = new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "eleos" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonEleoRecto1.png"));

		
		//Botones del biomorph

		imagesBiomorphMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "biomorph" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonBiomorphCerca1.png")));
		imagesBiomorphMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "biomorph" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonBiomorphCerca2.png")));
		imagesBiomorphMelee
				.add(new ImageIcon(Tools.loadBufferedImage("art"
						+ File.separator + "biomorph" + File.separator
						+ "botones_armas" + File.separator
						+ "BotonBiomorphCerca3.png")));

		imagesBiomorphParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "biomorph" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonBiomorphParabolico1.png")));
		imagesBiomorphParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "biomorph" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonBiomorphParabolico2.png")));
		imagesBiomorphParabolic.add(new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "biomorph" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonBiomorphParabolico3.png")));

		imagesBiomorphGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "biomorph"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonBiomorphRebote1.png")));
		imagesBiomorphGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "biomorph"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonBiomorphRebote2.png")));
		imagesBiomorphGrenade.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "biomorph"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonBiomorphRebote3.png")));

		imagesBiomorphRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "biomorph"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonBiomorphRecto1.png")));
		imagesBiomorphRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "biomorph"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonBiomorphRecto2.png")));
		imagesBiomorphRectilinear.add(new ImageIcon(Tools
				.loadBufferedImage("art" + File.separator + "biomorph"
						+ File.separator + "botones_armas" + File.separator
						+ "BotonBiomorphRecto3.png")));

		imageBiomorphSpecial = new ImageIcon(Tools.loadBufferedImage("art"
				+ File.separator + "biomorph" + File.separator
				+ "botones_armas" + File.separator
				+ "BotonBiomorphEspecial.png"));
		//		Botones del GMarine
		//		Botones del ninfa
		//		Botones del eleo

	}

	/**
	 * @return Returns the currentLevel.
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * @return Returns the numberOfLevels.
	 */
	public int getNumberOfLevels() {
		return numberOfLevels;
	}

	public static WeaponJButton getCurrentButton() {
		return WeaponJButtonListener.getButtonActivated();
	}

	/**
	 * @param currentLevel
	 *            The currentLevel to set.
	 */
	public void setCurrentLevel(int x) {
		if (x < 1)
			this.currentLevel = 1;
		else {
			this.currentLevel = ((x - 1) % numberOfLevels) + 1;
			updateImages();
		}
	}

	public void updateImages() {
		if (race == Constants.RACE_SCRAPER) {
			switch (projectileType) {
			case Constants.WEAP_MELEE:
				this.setIcon((ImageIcon) imagesScraperMelee
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_PARABOLIC:
				this.setIcon((ImageIcon) imagesScraperParabolic
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_GRENADE:
				this.setIcon((ImageIcon) imagesScraperGrenade
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_RECTILINEAR:
				this.setIcon((ImageIcon) imagesScraperRectilinear
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_SPECIAL:
				this.setIcon(imageScraperSpecial);
				break;
			default:
				break;
			}
		} else if (race == Constants.RACE_BIOMORPH) {
			switch (projectileType) {
			case Constants.WEAP_MELEE:
				this.setIcon((ImageIcon) imagesBiomorphMelee
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_PARABOLIC:
				this.setIcon((ImageIcon) imagesBiomorphParabolic
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_GRENADE:
				this.setIcon((ImageIcon) imagesBiomorphGrenade
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_RECTILINEAR:
				this.setIcon((ImageIcon) imagesBiomorphRectilinear
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_SPECIAL:
				this.setIcon(imageBiomorphSpecial);
				break;
			default:
				break;
			}

		}
		else if (race == Constants.RACE_GMARINE) {
			switch (projectileType) {
			case Constants.WEAP_MELEE:
				this.setIcon((ImageIcon) imagesGmarineMelee
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_PARABOLIC:
				this.setIcon((ImageIcon) imagesGmarineParabolic
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_GRENADE:
				this.setIcon((ImageIcon) imagesGmarineGrenade
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_RECTILINEAR:
				this.setIcon((ImageIcon) imagesGmarineRectilinear
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_SPECIAL:
				this.setIcon(imageGmarineSpecial);
				break;
			default:
				break;
			}

		}
		else if (race == Constants.RACE_NINFA) {
			switch (projectileType) {
			case Constants.WEAP_MELEE:
				this.setIcon((ImageIcon) imagesNinfasMelee
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_PARABOLIC:
				this.setIcon((ImageIcon) imagesNinfasParabolic
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_GRENADE:
				this.setIcon((ImageIcon) imagesNinfasGrenade
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_RECTILINEAR:
				this.setIcon((ImageIcon) imagesNinfasRectilinear
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_SPECIAL:
				this.setIcon(imagesNinfasSpecial);
				break;
			default:
				break;
			}

		}
		else if (race == Constants.RACE_ELEO) {
			switch (projectileType) {
			case Constants.WEAP_MELEE:
				this.setIcon((ImageIcon) imagesEleoMelee
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_PARABOLIC:
				this.setIcon((ImageIcon) imagesEleoParabolic
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_GRENADE:
				this.setIcon((ImageIcon) imagesEleoGrenade
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_RECTILINEAR:
				this.setIcon((ImageIcon) imagesEleoRectilinear
						.get(currentLevel - 1));
				break;
			case Constants.WEAP_SPECIAL:
				this.setIcon(imagesEleoSpecial);
				break;
			default:
				break;
			}

		}
	}

	public void increaseCurrentLevel() {
		setCurrentLevel(currentLevel + 1);
	}

	public void setPlayer(ActorPlayer p) {
		WeaponJButton.player = p;
		setRace(p.getRaceCode());
	}

	public static void setButtonActivated(WeaponJButton b) {
		if (buttonActivated == null) {
			buttonActivated = b;
			normalBorder = buttonActivated.getBorder();
			buttonActivated.setBorder(selectedBorder);
		}
		if (b != buttonActivated) {
			buttonActivated.setBorder(normalBorder);
			buttonActivated = b;
			normalBorder = buttonActivated.getBorder();
			buttonActivated.setBorder(selectedBorder);
		} else if (b == buttonActivated) {
			buttonActivated.increaseCurrentLevel();
		}
	}

	/**
	 * @return Returns the race.
	 */
	public int getRace() {
		return race;
	}

	/**
	 * @param race
	 *            The race to set.
	 */
	public void setRace(int r) {
		race = r;
		updateImages();
	}

	private static class WeaponJButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			WeaponJButton buttonClicked = (WeaponJButton) e.getSource();

			WeaponJButton.setButtonActivated(buttonClicked);

			int weapon = buttonActivated.getProjectileType() * 10
					+ buttonActivated.getCurrentLevel();
			player.setCurrentWeapon(weapon);
			
		}

		/**
		 * @return Returns the buttonActivated.
		 */
		public static WeaponJButton getButtonActivated() {
			return buttonActivated;
		}
	}

	/**
	 * @return Returns the projectileType.
	 */
	public int getProjectileType() {
		return projectileType;
	}
}//class WeaponJButton
