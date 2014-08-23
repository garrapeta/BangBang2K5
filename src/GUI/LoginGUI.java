package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import network.ClientServer;

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

public class LoginGUI extends JFrame {

    JFrame frame;
    
    JTextField UserjTextField = new JTextField();
    
    JPasswordField PwdjPasswordField = new JPasswordField();
    
    JLabel BackgroundjLabel = new JLabel();

    JLabel UserjLabel = new JLabel();

    JLabel PwdjLabel = new JLabel();

    JButton LoginjButton = new JButton();

    JButton ErasejButton = new JButton();

    JButton NewUserjButton = new JButton();

    
    
    String nombre;


    public LoginGUI() throws HeadlessException {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void jbInit() throws Exception {
    	Dimension screenRes=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(screenRes.width/2)-(500/2);
        int y=(screenRes.height/2)-(145/2);
        this.setLocation(x,y);
        this.setSize(500, 145);
        
        //this.getContentPane().setBackground(new Color(212, 224, 230));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        UserjTextField.setText("");
        //UserjTextField.setBackground(ColoursDefinitions.TRANSPARENT);
        //UserjTextField.setForeground(Color.yellow);
        UserjTextField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        UserjTextField.setBounds(new Rectangle(90, 22, 249, 20));
        
        PwdjPasswordField.setText("");
        PwdjPasswordField.setBounds(new Rectangle(90, 67, 249, 20));
        //PwdjPasswordField.setBackground(ColoursDefinitions.TRANSPARENT);
        //PwdjPasswordField.setForeground(Color.yellow);
        PwdjPasswordField.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        
        NewUserjButton.setBounds(new Rectangle(360, 75, 120, 25));
        NewUserjButton.setText("Registrar");
        NewUserjButton.setBackground(new Color(195, 206, 212));
        //NewUserjButton.setBackground(Color.YELLOW);
        NewUserjButton.setBorder(BorderFactory.createRaisedBevelBorder());
        NewUserjButton.setMnemonic(KeyEvent.VK_R);
        NewUserjButton
                .addActionListener(new LoginGUI_NewUserjButton_actionAdapter(
                        this));
        this.getContentPane().setLayout(null);
        UserjLabel.setForeground(Color.YELLOW);
        UserjLabel.setFont(UserjLabel.getFont().deriveFont(Font.BOLD));
        UserjLabel.setText("Usuario:");
        UserjLabel.setBounds(new Rectangle(20, 21, 53, 25));
        PwdjLabel.setForeground(Color.YELLOW);
        PwdjLabel.setFont(PwdjLabel.getFont().deriveFont(Font.BOLD));
        PwdjLabel.setText("Contrasenha:");
        PwdjLabel.setBounds(new Rectangle(20, 66, 69, 25));
        LoginjButton.setBounds(new Rectangle(360, 14, 120, 25));
        LoginjButton.setText("Enviar");
        LoginjButton.setBorder(BorderFactory.createRaisedBevelBorder());
        LoginjButton.setBackground(new Color(195, 206, 212));
        //LoginjButton.setBackground(Color.YELLOW);
        LoginjButton.setMnemonic(KeyEvent.VK_E);
        LoginjButton.addActionListener(new LoginGUI_LoginjButton_actionAdapter(
                this));
        ErasejButton.setBounds(new Rectangle(360, 45, 120, 25));
        ErasejButton.setText("Borrar");
        ErasejButton.setBorder(BorderFactory.createRaisedBevelBorder());
        ErasejButton.setBackground(new Color(195, 206, 212));
        //ErasejButton.setBackground(Color.YELLOW);
        ErasejButton.setMnemonic(KeyEvent.VK_B);
        ErasejButton.addActionListener(new LoginGUI_ErasejButton_actionAdapter(
                this));
        this.setResizable(false);
        this.setTitle("Login");
        
        
        BackgroundjLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"stars.png")));
        BackgroundjLabel.setText("");
        BackgroundjLabel.setBounds(new Rectangle(0, 0, 550, 265));
                
        this.getContentPane().add(UserjTextField, null, 0);
        this.getContentPane().add(PwdjPasswordField, null, 1);
        this.getContentPane().add(LoginjButton, null, 2);
        this.getContentPane().add(ErasejButton, null, 3);
        this.getContentPane().add(NewUserjButton, null, 4);
        this.getContentPane().add(UserjLabel, null);
        this.getContentPane().add(PwdjLabel, null);        
        //this.getContentPane().add(PwdjPasswordField, null);
        this.getContentPane().add(BackgroundjLabel, null);
        this.getContentPane().validate();
        this.setFocusTraversalPolicy(new LoginGUIContainer());
    }


    void NewUserjButton_actionPerformed(ActionEvent e) {
        NewUserGUI newUserGUI = new NewUserGUI();
        newUserGUI.setVisible(true);
        this.dispose();
    }


    void LoginjButton_actionPerformed(ActionEvent e) {
        if (UserjTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(frame,
                    "Necesitas un nombre de usuario para poder registrarte",
                    "User Error", JOptionPane.WARNING_MESSAGE);
        } else if (PwdjPasswordField.getText().equals("")) {
            JOptionPane.showMessageDialog(frame,
                    "Necesitas una contrasenha para poder registrarte",
                    "Password Error", JOptionPane.WARNING_MESSAGE);
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
        } else {
            nombre = UserjTextField.getText();
            ClientServer clientServer = new ClientServer(nombre);
            clientServer.arrancar();
            SelPersonageGUI selPersonageGUI = new SelPersonageGUI(clientServer);
            selPersonageGUI.getUserjTextField().setText(UserjTextField.getText());
            selPersonageGUI.setVisible(true);
            this.dispose();
        }
    }


    void ErasejButton_actionPerformed(ActionEvent e) {
        UserjTextField.setText("");
        PwdjPasswordField.setText("");
    }

}


class LoginGUI_ErasejButton_actionAdapter implements
        java.awt.event.ActionListener {

    LoginGUI adaptee;


    LoginGUI_ErasejButton_actionAdapter(LoginGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.ErasejButton_actionPerformed(e);
    }
}


class LoginGUI_NewUserjButton_actionAdapter implements
        java.awt.event.ActionListener {

    LoginGUI adaptee;


    LoginGUI_NewUserjButton_actionAdapter(LoginGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.NewUserjButton_actionPerformed(e);
    }
}


class LoginGUI_LoginjButton_actionAdapter implements
        java.awt.event.ActionListener {

    LoginGUI adaptee;


    LoginGUI_LoginjButton_actionAdapter(LoginGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.LoginjButton_actionPerformed(e);
    }
    
}