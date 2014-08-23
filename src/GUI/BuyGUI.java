package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import util.ColoursDefinitions;
import util.Tools;

/**
 * <p>
 * Tnhtulo:
 * </p>
 * <p>
 * Descripcinhn:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Empresa: Bang-Bang_3005
 * </p>
 * 
 * @author Germnhn Martnhnez Sancho
 * @version 1.0
 */

public class BuyGUI extends JFrame {

    JList SelectWeaponjList = new JList();

    JList SelectSessionjList = new JList();

    JLabel BackgroundjLabel = new JLabel();
    
    JButton OkjButton = new JButton();

    JButton ReturnjButton = new JButton();

    JLabel ChoosejLabel = new JLabel();

    JLabel BuyjLabel = new JLabel();

    JButton PlayjButton = new JButton();

    JTextField MoneyjTextField = new JTextField();

    JLabel MoneyjLabel = new JLabel();

    JLabel ChronojLabel = new JLabel();


    public BuyGUI() throws HeadlessException {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void jbInit() throws Exception {
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(658, 427));
        this.getContentPane().setBackground(new Color(212, 224, 230));
        this.setLocation(70, 70);
        OkjButton.setBounds(new Rectangle(261, 152, 123, 25));
        OkjButton.setText("Ok  >>");
        OkjButton.setBorder(BorderFactory.createRaisedBevelBorder());
        OkjButton.setBackground(new Color(195, 206, 212));
        OkjButton.setMnemonic(KeyEvent.VK_O);
        ReturnjButton.setBounds(new Rectangle(261, 203, 123, 25));
        ReturnjButton.setPreferredSize(new Dimension(73, 25));
        ReturnjButton.setToolTipText("");
        ReturnjButton.setText("<<  Devolver");
        ReturnjButton.setBorder(BorderFactory.createRaisedBevelBorder());
        ReturnjButton.setBackground(new Color(195, 206, 212));
        ReturnjButton.setMnemonic(KeyEvent.VK_D);
        ChoosejLabel.setText("Lista de Armas que puedes elegir");
        ChoosejLabel.setFont(MoneyjTextField.getFont().deriveFont(Font.BOLD));
        ChoosejLabel.setForeground(Color.yellow);
        ChoosejLabel.setBounds(new Rectangle(16, 62, 200, 27));
        BuyjLabel.setVerifyInputWhenFocusTarget(true);
        BuyjLabel.setText("Lista de Armas que vas a comprar");
        BuyjLabel.setFont(MoneyjTextField.getFont().deriveFont(Font.BOLD));
        BuyjLabel.setForeground(Color.yellow);
        BuyjLabel.setBounds(new Rectangle(399, 62, 224, 27));
        PlayjButton.setBounds(new Rectangle(465, 355, 159, 25));
        PlayjButton.setText("Jugar");
        PlayjButton.setBorder(BorderFactory.createRaisedBevelBorder());
        PlayjButton.setBackground(new Color(195, 206, 212));
        PlayjButton.setMnemonic(KeyEvent.VK_J);
        PlayjButton
                .addActionListener(new BuyGUI_PlayjButton_actionAdapter(this));
        this.setResizable(false);
        this.setTitle("Comprar");
        
        MoneyjLabel.setMaximumSize(new Dimension(30, 15));
        MoneyjLabel.setText("Saldo:");
        MoneyjLabel.setFont(MoneyjLabel.getFont().deriveFont(Font.BOLD));
        
        MoneyjLabel.setForeground(Color.yellow);
        MoneyjLabel.setBounds(new Rectangle(14, 16, 45, 24));
       
        MoneyjTextField.setEditable(false);
        MoneyjTextField.setText("");
        MoneyjTextField.setScrollOffset(0);
        MoneyjTextField.setBounds(new Rectangle(55, 16, 137, 24));
        MoneyjTextField.setBorder(BorderFactory.createLineBorder(ColoursDefinitions.TRANSPARENT));
        MoneyjTextField.setDisabledTextColor(Color.YELLOW);
        MoneyjTextField.setFont(MoneyjTextField.getFont().deriveFont(Font.BOLD));
        MoneyjTextField.setBackground(ColoursDefinitions.TRANSPARENT);
        
        ChronojLabel.setText("Tiempo:");
        ChronojLabel.setFont(MoneyjTextField.getFont().deriveFont(Font.BOLD));
        ChronojLabel.setForeground(Color.yellow);
        ChronojLabel.setBounds(new Rectangle(514, 16, 61, 26));
        
        SelectSessionjList.setBounds(new Rectangle(399, 92, 225, 212));
        SelectSessionjList.setBackground(Color.white);
        SelectSessionjList.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        
        SelectWeaponjList.setBounds(new Rectangle(16, 91, 225, 212));
        SelectWeaponjList.setBackground(Color.white);
        SelectWeaponjList.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        
        BackgroundjLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"stars.png")));
        BackgroundjLabel.setText("");
        BackgroundjLabel.setBounds(new Rectangle(0, 0, 945, 565));
        
        this.getContentPane().add(MoneyjLabel, null);
        this.getContentPane().add(MoneyjTextField, null);
        this.getContentPane().add(ChronojLabel, null);
        this.getContentPane().add(ChoosejLabel, null);
        this.getContentPane().add(BuyjLabel, null);
        this.getContentPane().add(SelectSessionjList, null);
        this.getContentPane().add(SelectWeaponjList, null);
        this.getContentPane().add(PlayjButton, null);
        this.getContentPane().add(OkjButton, null);
        this.getContentPane().add(ReturnjButton, null);
        this.getContentPane().add(BackgroundjLabel);
    }


    void PlayjButton_actionPerformed(ActionEvent e) {
        this.dispose();
    }

}


class BuyGUI_PlayjButton_actionAdapter implements java.awt.event.ActionListener {

    BuyGUI adaptee;


    BuyGUI_PlayjButton_actionAdapter(BuyGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.PlayjButton_actionPerformed(e);
    }
}