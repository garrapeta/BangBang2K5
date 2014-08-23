package sound;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sound.audioManagers.AudioManagerInterface;
import util.ColoursDefinitions;

public class SoundControlGUI extends JDialog implements ActionListener {

	private AudioManagerInterface am;

	private JPanel panel;

	private JLabel musicLabel;

	private JLabel soundLabel;

	private JCheckBox musicCheckBox;

	private JCheckBox soundCheckBox;

	private JProgressBar soundBar;

	private JProgressBar musicBar;

	private JButton soundPositiveButton;

	private JButton soundNegativeButton;

	private JButton musicPositiveButton;

	private JButton musicNegativeButton;
	
	private final static float delta =(float)0.1;
	
	public SoundControlGUI(Frame owner, AudioManagerInterface am) {
		super(owner);
	    this.setTitle("Control de sonido");
		this.am = am;
		SoundControlGUIInit();
	}

	public void SoundControlGUIInit() {
	    this.setModal(true);
		this.setSize(new Dimension(245, 170));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(ColoursDefinitions.LIGHT_BLUE_1);		
		panel = new JPanel();
		panel.setBackground(ColoursDefinitions.LIGHT_BLUE_1);
		panel.setLayout(null);
		
		int x=this.getParent().getX()+this.getParent().getWidth()/2-this.getWidth()/2;
		int y=this.getParent().getY()+this.getParent().getHeight()/2-this.getHeight()/2;
		this.setLocation(x,y);
		
		//sonido
		soundLabel = new JLabel("Sonido ");
		soundLabel.setLocation(10, 10);
		soundLabel.setSize(soundLabel.getPreferredSize());

		soundCheckBox = new JCheckBox();
		soundCheckBox.setBackground(ColoursDefinitions.LIGHT_BLUE_1);
		soundCheckBox.setSelected(am.isSoundOn());
		soundCheckBox.setLocation(197, 10);
		soundCheckBox.setSize(soundCheckBox.getPreferredSize());
		soundCheckBox.addActionListener(this);

		soundPositiveButton = new JButton();
		soundPositiveButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
		soundPositiveButton.setLocation(200, 40);
		soundPositiveButton.setSize(new Dimension(15, 15));
		soundPositiveButton.addActionListener(this);
		soundPositiveButton.setEnabled(am.isSoundOn());

		soundNegativeButton = new JButton();
		soundNegativeButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
		soundNegativeButton.setLocation(40, 40);
		soundNegativeButton.setSize(new Dimension(15, 15));
		soundNegativeButton.addActionListener(this);
		soundNegativeButton.setEnabled(am.isSoundOn());

		soundBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 10);
		soundBar.setBackground(ColoursDefinitions.LIGHT_BLUE_1);
		soundBar.setSize(new Dimension(125, 15));
		soundBar.setLocation(65, 40);
		soundBar.setValue((int) (am.getSoundVolume() * 10));


		//musica
		musicLabel = new JLabel("Musica ");
		musicLabel.setLocation(10, 70);
		musicLabel.setSize(musicLabel.getPreferredSize());

		musicCheckBox = new JCheckBox();
		musicCheckBox.setBackground(ColoursDefinitions.LIGHT_BLUE_1);
		musicCheckBox.setSelected(am.isMusicOn());
		musicCheckBox.setLocation(197, 70);
		musicCheckBox.setSize(musicCheckBox.getPreferredSize());
		musicCheckBox.addActionListener(this);

		musicPositiveButton = new JButton();
		musicPositiveButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
		musicPositiveButton.setLocation(200, 100);
		musicPositiveButton.setSize(new Dimension(15, 15));
		musicPositiveButton.addActionListener(this);
		musicPositiveButton.setEnabled(am.isMusicOn());

		musicNegativeButton = new JButton();
		musicNegativeButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
		musicNegativeButton.setLocation(40, 100);
		musicNegativeButton.setSize(new Dimension(15, 15));
		musicNegativeButton.addActionListener(this);
		musicNegativeButton.setEnabled(am.isMusicOn());

		musicBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 10);
		musicBar.setBackground(ColoursDefinitions.LIGHT_BLUE_1);
		musicBar.setSize(new Dimension(125, 15));
		musicBar.setLocation(65, 100);
		musicBar.setValue((int) (am.getMusicVolume() * 10));

		//adds
		panel.add(soundLabel);
		panel.add(soundCheckBox);
		panel.add(musicLabel);
		panel.add(musicCheckBox);
		panel.add(soundPositiveButton);
		panel.add(soundNegativeButton);
		panel.add(musicNegativeButton);
		panel.add(musicPositiveButton);
		panel.add(soundBar);
		panel.add(musicBar);
		getContentPane().add(panel);

		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		//SONIDO
	    if (e.getSource() == soundCheckBox) {
			am.setSoundOn(soundCheckBox.isSelected());
			soundPositiveButton.setEnabled(soundCheckBox.isSelected());
			soundNegativeButton.setEnabled(soundCheckBox.isSelected());
		} else if (e.getSource() == soundPositiveButton) {
			if (am.getSoundVolume() < 1) {
			    
				am.setSoundVolume((float) (am.getSoundVolume() + delta));
				soundBar.setValue((int) (am.getSoundVolume() * 10));
			}
		} else if (e.getSource() == soundNegativeButton) {
			if (am.getSoundVolume() > 0) {
				am.setSoundVolume((float) (am.getSoundVolume() - delta));
				soundBar.setValue((int) (am.getSoundVolume() * 10));				
			}
			
		//MUSICA	
		} else if (e.getSource() == musicCheckBox) {
			am.setMusicOn(musicCheckBox.isSelected());
			musicPositiveButton.setEnabled(musicCheckBox.isSelected());
			musicNegativeButton.setEnabled(musicCheckBox.isSelected());

		} else if (e.getSource() == musicPositiveButton) {
			if (am.getMusicVolume() < 1) {
				am.setMusicVolume((float) (am.getMusicVolume() + delta));
				musicBar.setValue((int) (am.getMusicVolume() * 10));
			}

		} else if (e.getSource() == musicNegativeButton) {
			if (am.getMusicVolume() > 0) {
				am.setMusicVolume(am.getMusicVolume() - delta);
				musicBar.setValue((int) (am.getMusicVolume() * 10));
			}
		}
	}
}