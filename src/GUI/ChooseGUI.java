package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import util.Tools;

/**
 * <p>Tnhtulo: </p>
 * <p>Descripcinhn: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class ChooseGUI extends JFrame {
  JLabel label = new JLabel();
  JButton OkLocaljButton = new JButton();
  JButton OkRedjButton = new JButton();
  JButton ExitjButton = new JButton();
  public ChooseGUI() throws HeadlessException {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    OkLocaljButton.setBounds(new Rectangle(50, 500, 315, 30));
    OkLocaljButton.setText("Juego Local");
    OkLocaljButton.setMnemonic(KeyEvent.VK_L);
    OkLocaljButton.addActionListener(new ChooseGUI_OkLocaljButton_actionAdapter(this));
    OkLocaljButton.setBackground(new Color(195, 206, 212));
    OkLocaljButton.setBorder(BorderFactory.createRaisedBevelBorder());
    
    OkRedjButton.setBounds(new Rectangle(580, 500, 315, 30));
    OkRedjButton.setText("Juego en Red");
    OkRedjButton.setMnemonic(KeyEvent.VK_R);
    OkRedjButton.addActionListener(new ChooseGUI_OkRedjButton_actionAdapter(this));
    OkRedjButton.setBackground(new Color(195, 206, 212));
    OkRedjButton.setBorder(BorderFactory.createRaisedBevelBorder());
    
    ExitjButton.setBounds(new Rectangle(315, 540, 315, 30));
    ExitjButton.setText("Salir del Juego");
    ExitjButton.setMnemonic(KeyEvent.VK_S);
    ExitjButton.addActionListener(new ChooseGUI_ExitjButton_actionAdapter(this));
    ExitjButton.setBackground(new Color(195, 206, 212));
    ExitjButton.setBorder(BorderFactory.createRaisedBevelBorder());
    
    label.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"fotochoose.png")));
    //label.setMaximumSize(new Dimension(100,100));
    label.setText("");
    label.setBounds(new Rectangle(0, 0, 960, 590));
    
    this.getContentPane().setLayout(null);
    Dimension screenRes=Toolkit.getDefaultToolkit().getScreenSize();
    int x=(screenRes.width/2)-(950/2);
    int y=(screenRes.height/2)-(610/2);
    this.setLocation(x,y);
    
    //this.setLocation(20, 20);
    this.setResizable(false);
    this.getContentPane().setBackground(new Color(212, 224, 230));
    this.setSize(new Dimension(950, 610));
    this.setTitle("Selector de juego");
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.getContentPane().add(OkLocaljButton, null);
    this.getContentPane().add(OkRedjButton, null);
    this.getContentPane().add(ExitjButton, null);
    this.getContentPane().add(label);

  }

  void OkLocaljButton_actionPerformed(ActionEvent e) {
   	SelPersonageGUI selPersonageGUI = new SelPersonageGUI(true);//1 por poner alguna sala
  	selPersonageGUI.setVisible(true);
    this.dispose();
  }
  void OkRedjButton_actionPerformed(ActionEvent e) {
    //String command = buttonGroup1.getSelection().getActionCommand();
    //if (command.equals("Red")){
      LoginGUI loginGUI = new LoginGUI();
      loginGUI.setVisible(true);
      this.dispose();
    //}
    /*else if(command.equals("Local")){
      CreateMatchGUI createMatchGUI = new CreateMatchGUI(1);//1 por poner alguna sala
      createMatchGUI.setVisible(true);
      this.dispose();
    }*/
  }
  void ExitjButton_actionPerformed(ActionEvent e) {
  	System.exit(0);
  }
}

class ChooseGUI_OkLocaljButton_actionAdapter implements java.awt.event.ActionListener {
  ChooseGUI adaptee;

  ChooseGUI_OkLocaljButton_actionAdapter(ChooseGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.OkLocaljButton_actionPerformed(e);
  }
}
class ChooseGUI_OkRedjButton_actionAdapter implements java.awt.event.ActionListener {
	  ChooseGUI adaptee;

	  ChooseGUI_OkRedjButton_actionAdapter(ChooseGUI adaptee) {
	    this.adaptee = adaptee;
	  }
	  public void actionPerformed(ActionEvent e) {
	    adaptee.OkRedjButton_actionPerformed(e);
	  }
	}
class ChooseGUI_ExitjButton_actionAdapter implements java.awt.event.ActionListener {
	  ChooseGUI adaptee;

	  ChooseGUI_ExitjButton_actionAdapter(ChooseGUI adaptee) {
	    this.adaptee = adaptee;
	  }
	  public void actionPerformed(ActionEvent e) {
	    adaptee.ExitjButton_actionPerformed(e);
	  }
	}
