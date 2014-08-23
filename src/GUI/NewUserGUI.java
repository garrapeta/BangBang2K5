package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import network.ClientServer;

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

public class NewUserGUI extends JFrame {

    JFrame frame;

    JButton OkjButton = new JButton();

    JButton CanceljButton = new JButton();

    JTextField UserjTextField = new JTextField();

    JLabel UserjLabel = new JLabel();

    JLabel PwdjLabel = new JLabel();

    JLabel RePwdjLabel = new JLabel();
    
    JLabel BackgroundjLabel = new JLabel();

    JPasswordField PwdjPasswordField = new JPasswordField();

    JPasswordField RePwdjPasswordField = new JPasswordField();
    
    String nombre;

    public NewUserGUI() throws HeadlessException {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * public static void main(String[] args) throws HeadlessException {
     * NewUserGUI registroGUI1 = new NewUserGUI(); }
     */

    private void jbInit() throws Exception {
    	Dimension screenRes=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(screenRes.width/2)-(535/2);
        int y=(screenRes.height/2)-(160/2);
        this.setLocation(x,y);
        this.setSize(535, 160);
        this.getContentPane().setBackground(new Color(212, 224, 230));
        this.setForeground(Color.black);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        this.setTitle("Registrar");
        OkjButton.setBounds(new Rectangle(400, 30, 120, 25));
        OkjButton.setText("Enviar");
        OkjButton.setBackground(new Color(195, 206, 212));
        OkjButton.setBorder(BorderFactory.createRaisedBevelBorder());
        //OkjButton.setBackground(Color.YELLOW);
        OkjButton.setMnemonic(KeyEvent.VK_E);
        OkjButton
                .addActionListener(new NewUserGUI_OkjButton_actionAdapter(this));
        CanceljButton.setBounds(new Rectangle(400, 75, 120, 25));
        CanceljButton.setText("Cancelar");
        CanceljButton.setBorder(BorderFactory.createRaisedBevelBorder());
        CanceljButton.setBackground(new Color(195, 206, 212));
        //CanceljButton.setBackground(Color.YELLOW);
        CanceljButton.setMnemonic(KeyEvent.VK_C);
        CanceljButton
                .addActionListener(new NewUserGUI_CanceljButton_actionAdapter(
                        this));
        UserjTextField.setText("");
        UserjTextField.setBounds(new Rectangle(140, 20, 249, 20));
        UserjTextField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        UserjLabel.setForeground(Color.YELLOW);
        UserjLabel.setText("Usuario:");
        UserjLabel.setFont(UserjLabel.getFont().deriveFont(Font.BOLD));
        UserjLabel.setBounds(new Rectangle(12, 19, 108, 25));
        //PwdjLabel.setForeground(Color.black);
        PwdjLabel.setForeground(Color.YELLOW);
        PwdjLabel.setText("Contrasenha:");
        PwdjLabel.setFont(UserjLabel.getFont().deriveFont(Font.BOLD));
        PwdjLabel.setBounds(new Rectangle(12, 54, 110, 25));
        RePwdjLabel.setText("Confirmar Contrasenha:");
        RePwdjLabel.setFont(UserjLabel.getFont().deriveFont(Font.BOLD));
        RePwdjLabel.setForeground(Color.YELLOW);
        RePwdjLabel.setBounds(new Rectangle(12, 91, 144, 25));
        /*
         * RePwdjPasswordField .addActionListener(new
         * NewUserGUI_RePwdjPasswordField_actionAdapter( this));
         */
        PwdjPasswordField.setText("");
        PwdjPasswordField.setBounds(new Rectangle(140, 55, 249, 20));
        PwdjPasswordField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        RePwdjPasswordField.setText("");
        RePwdjPasswordField.setBounds(new Rectangle(140, 92, 249, 20));
        RePwdjPasswordField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        BackgroundjLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"stars.png")));
        BackgroundjLabel.setText("");
        BackgroundjLabel.setBounds(new Rectangle(0, 0, 550, 265));
        
        this.getContentPane().add(UserjLabel, null);
        this.getContentPane().add(RePwdjLabel, null);
        this.getContentPane().add(PwdjLabel, null);
        this.getContentPane().add(RePwdjPasswordField, null);
        this.getContentPane().add(PwdjPasswordField, null);
        this.getContentPane().add(UserjTextField, null);
        this.getContentPane().add(OkjButton, null);
        this.getContentPane().add(CanceljButton, null);
        this.getContentPane().add(BackgroundjLabel);
    }


    void CanceljButton_actionPerformed(ActionEvent e) {
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
        this.dispose();
    }


    void OkjButton_actionPerformed(ActionEvent e) {
        String pa1 = new String(PwdjPasswordField.getPassword());
        String pa2 = new String(RePwdjPasswordField.getPassword());
        if (UserjTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(frame,
                    "Necesitas un nombre de usuario para poder registrarte",
                    "User Error", JOptionPane.WARNING_MESSAGE);
        } else if ((UserjTextField.getText().length() < 6)
                || (UserjTextField.getText().length() > 12)) {
            JOptionPane.showMessageDialog(frame,
                    "El nombre de usuario debe tener entre 6 y 12 caracteres",
                    "User Error", JOptionPane.WARNING_MESSAGE);
        } else if ((PwdjPasswordField.getText().length() < 4)
                || (PwdjPasswordField.getText().length() > 8)) {
            JOptionPane.showMessageDialog(frame,
                    "La contrasenha debe tener entre 4 y 8 caracteres",
                    "Password Error", JOptionPane.WARNING_MESSAGE);
        } else if (pa1.equals(pa2) && !pa1.equals("")) {
            //if(PwdjPasswordField.getPassword().equals(RePwdjPasswordField.getPassword())){
        		nombre = UserjTextField.getText();
    			ClientServer clientServer = new ClientServer(nombre);
            clientServer.arrancar();
            SelPersonageGUI selPersonageGUI = new SelPersonageGUI(clientServer);
            selPersonageGUI.getUserjTextField().setText(UserjTextField.getText());
            selPersonageGUI.setVisible(true);
            this.dispose();
        } else {
            JOptionPane
                    .showMessageDialog(
                            frame,
                            "Los campos de Password y Confirmar Password tienen que ser iguales y no vacnhos",
                            "Password Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /*
     * void RePwdjPasswordField_actionPerformed(ActionEvent e) {
     *  }
     */

}


class NewUserGUI_CanceljButton_actionAdapter implements
        java.awt.event.ActionListener {

    NewUserGUI adaptee;


    NewUserGUI_CanceljButton_actionAdapter(NewUserGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.CanceljButton_actionPerformed(e);
    }
}


class NewUserGUI_OkjButton_actionAdapter implements
        java.awt.event.ActionListener {

    NewUserGUI adaptee;


    NewUserGUI_OkjButton_actionAdapter(NewUserGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.OkjButton_actionPerformed(e);
    }
}

/*
 * class NewUserGUI_RePwdjPasswordField_actionAdapter implements
 * java.awt.event.ActionListener {
 * 
 * NewUserGUI adaptee;
 * 
 * 
 * NewUserGUI_RePwdjPasswordField_actionAdapter(NewUserGUI adaptee) {
 * this.adaptee = adaptee; }
 * 
 * 
 * public void actionPerformed(ActionEvent e) {
 * adaptee.RePwdjPasswordField_actionPerformed(e); } }
 */