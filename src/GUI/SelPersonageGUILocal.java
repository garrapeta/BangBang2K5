/*
 * Created on 17-may-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package GUI;

import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.ColoursDefinitions;
import util.Tools;

/**
 * @author usuario_local
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SelPersonageGUILocal extends JFrame{
	private JButton ContinuejButton = new JButton();

	private JButton CreatejButton = new JButton();

	private JButton BuyjButton = new JButton();//Comprar no va a funcionar!

	//private JLabel UserjLabel = new JLabel();
    
	private JLabel BackgroundjLabel = new JLabel();

	private JTextField UserjTextField = new JTextField();

	private DefaultListModel RacesModel = new DefaultListModel();

	private JList RacesjList = new JList(RacesModel);

	private JList PointsjList = new JList();

	private JList WeaponsjList = new JList();

	private JTextField WeaponsjTextField = new JTextField();

	private JTextField PointsjTextField = new JTextField();

	private JTextField RacejTextField = new JTextField();
    
    private JButton ErasejButton = new JButton();

    public SelPersonageGUILocal() throws HeadlessException {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void jbInit() throws Exception {
        this.setLocation(30, 30);
        this.setSize(new Dimension(583, 300));
        this.getContentPane().setBackground(new Color(212, 224, 230));
        this.getContentPane().setLayout(null);
        UserjTextField.setEnabled(false);
        //UserjTextField.setBorder(BorderFactory.createLineBorder(Color.black));
        UserjTextField.setBorder(BorderFactory.createLineBorder(ColoursDefinitions.TRANSPARENT));
        UserjTextField.setDisabledTextColor(Color.YELLOW);
        UserjTextField.setFont(UserjTextField.getFont().deriveFont(Font.BOLD));
        UserjTextField.setBackground(ColoursDefinitions.TRANSPARENT);
        //UserjTextField.setMargin(new Insets(0,56,0,0));
        UserjTextField.setEditable(false);
        UserjTextField.setScrollOffset(0);
        UserjTextField.setBounds(new Rectangle(80, 8, 121, 24));
        ContinuejButton.setBounds(new Rectangle(320, 231, 110, 25));
        ContinuejButton.setText("Siguiente");
        ContinuejButton.setBorder(BorderFactory.createRaisedBevelBorder());
        ContinuejButton.setBackground(new Color(195, 206, 212));
        ContinuejButton.setMnemonic(KeyEvent.VK_S);
        ContinuejButton
                .addActionListener(new SelPersonageGUILocal_ContinuejButton_actionAdapter(
                        this));
        CreatejButton.setBounds(new Rectangle(27, 231, 170, 25));
        CreatejButton.setText("Crear Nuevo Personaje");
        CreatejButton.setBorder(BorderFactory.createRaisedBevelBorder());
        CreatejButton.setBackground(new Color(195, 206, 212));
        CreatejButton.setMnemonic(KeyEvent.VK_N);
        CreatejButton
                .addActionListener(new SelPersonageGUILocal_CreatejButton_actionAdapter(
                        this));
        /*BuyjButton.setBounds(new Rectangle(440, 231, 112, 25));
        BuyjButton.setText("Comprar");
        BuyjButton.setBorder(BorderFactory.createRaisedBevelBorder());
        BuyjButton.setBackground(new Color(195, 206, 212));
        BuyjButton.setMnemonic(KeyEvent.VK_C);
        BuyjButton
                .addActionListener(new SelPersonageGUI_BuyjButton_actionAdapter(
                        this));*/
        ErasejButton.setBounds(new Rectangle(207, 231, 103, 25));
        ErasejButton.setText("Borrar");
        ErasejButton.setBorder(BorderFactory.createRaisedBevelBorder());
        ErasejButton.setBackground(new Color(195, 206, 212));
        ErasejButton.setMnemonic(KeyEvent.VK_B);
        ErasejButton.setEnabled(false);
        ErasejButton
                .addActionListener(new SelPersonageGUILocal_ErasejButton_actionAdapter(
                        this));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Seleccionar Personaje");
        RacesjList.setBackground(Color.white);
        RacesjList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        RacesjList.setBorder(BorderFactory.createLineBorder(Color.black));
        RacesjList.setBounds(new Rectangle(27, 66, 130, 150));
        RacesjList.addListSelectionListener(new SelPersonageGUILocal_RacesjList_listSelectionListener(this));
        PointsjList.setBackground(Color.white);
        PointsjList.setBorder(BorderFactory.createLineBorder(Color.black));
        PointsjList.setBounds(new Rectangle(156, 66, 86, 150));
        WeaponsjList.setBackground(Color.white);
        WeaponsjList.setBorder(BorderFactory.createLineBorder(Color.black));
        WeaponsjList.setBounds(new Rectangle(241, 66, 312, 150));
        WeaponsjTextField.setBackground(Color.darkGray);
        WeaponsjTextField.setEnabled(false);
        WeaponsjTextField.setForeground(Color.white);
        WeaponsjTextField
                .setBorder(BorderFactory.createLineBorder(Color.black));
        WeaponsjTextField.setDisabledTextColor(Color.white);
        WeaponsjTextField.setEditable(false);
        WeaponsjTextField.setText("    Armas que posee");
        WeaponsjTextField.setBounds(new Rectangle(241, 42, 312, 25));
        PointsjTextField.setBackground(Color.darkGray);
        PointsjTextField.setEnabled(false);
        PointsjTextField.setForeground(Color.white);
        PointsjTextField.setBorder(BorderFactory.createLineBorder(Color.black));
        PointsjTextField.setDisabledTextColor(Color.white);
        PointsjTextField.setEditable(false);
        PointsjTextField.setText("    Puntos");
        PointsjTextField.setBounds(new Rectangle(156, 42, 86, 25));
        RacejTextField.setBackground(Color.darkGray);
        RacejTextField.setEnabled(false);
        RacejTextField.setFont(new java.awt.Font("Dialog", 0, 11));
        RacejTextField.setForeground(Color.white);
        RacejTextField.setBorder(BorderFactory.createLineBorder(Color.black));
        RacejTextField.setDisabledTextColor(Color.white);
        RacejTextField.setEditable(false);
        RacejTextField.setText("   Jugador");
        RacejTextField.setBounds(new Rectangle(27, 42, 130, 25));
        BackgroundjLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"stars.png")));
        BackgroundjLabel.setText("");
        BackgroundjLabel.setBounds(new Rectangle(0, 0, 945, 565));
        //this.getContentPane().add(UserjLabel, null);
        this.getContentPane().add(UserjTextField, null);
        this.getContentPane().add(CreatejButton, null);
        this.getContentPane().add(ContinuejButton, null);
        this.getContentPane().add(ErasejButton, null);
        this.getContentPane().add(BuyjButton, null);
        this.getContentPane().add(RacesjList, null);
        this.getContentPane().add(RacejTextField, null);
        this.getContentPane().add(PointsjList, null);
        this.getContentPane().add(PointsjTextField, null);
        this.getContentPane().add(WeaponsjTextField, null);
        this.getContentPane().add(WeaponsjList, null);
        this.getContentPane().add(BackgroundjLabel);
    }
        void CreatejButton_actionPerformed(ActionEvent e) {
            //CreatePersonageGUI createPersonageGUI = new CreatePersonageGUI(this);
            //createPersonageGUI.setVisible(true);
        }
        
        void ContinuejButton_actionPerformed(ActionEvent e) {
    		//CreateMatchGUI createMatchGUI = new CreateMatchGUI(this);
            //createMatchGUI.setVisible(true);
        }
        void ErasejButton_actionPerformed(ActionEvent e) {
        	RacesModel.remove(RacesjList.getSelectedIndex());
            this.ErasejButton.setEnabled(false);
        }
        void RacesjList_valueChanged(ListSelectionEvent e) {
            ErasejButton.setEnabled(true); 
        }
}

class SelPersonageGUILocal_CreatejButton_actionAdapter implements
		java.awt.event.ActionListener {

	SelPersonageGUILocal adaptee;

	SelPersonageGUILocal_CreatejButton_actionAdapter(SelPersonageGUILocal adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.CreatejButton_actionPerformed(e);
	}
}
class SelPersonageGUILocal_ContinuejButton_actionAdapter implements
		java.awt.event.ActionListener {

	private SelPersonageGUILocal adaptee;

	SelPersonageGUILocal_ContinuejButton_actionAdapter(SelPersonageGUILocal adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.ContinuejButton_actionPerformed(e);
	}
}

class SelPersonageGUILocal_ErasejButton_actionAdapter implements
		java.awt.event.ActionListener {

	private SelPersonageGUILocal adaptee;

	SelPersonageGUILocal_ErasejButton_actionAdapter(SelPersonageGUILocal adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.ErasejButton_actionPerformed(e);
	}
}

class SelPersonageGUILocal_RacesjList_listSelectionListener implements
		ListSelectionListener {

	private SelPersonageGUILocal adaptee;

	SelPersonageGUILocal_RacesjList_listSelectionListener(SelPersonageGUILocal adaptee) {
		this.adaptee = adaptee;
	}

	public void valueChanged(ListSelectionEvent e) {
		adaptee.RacesjList_valueChanged(e);
	}
}
