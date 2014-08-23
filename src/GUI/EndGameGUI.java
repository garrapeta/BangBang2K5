/*
 * Created on 18-may-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package GUI;

import game.GameEngine;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import util.ColoursDefinitions;


/**
 * @author ibon
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EndGameGUI extends JDialog{
    
    protected final int WIDTH=290;
    protected final int HEIGHT=140;
    
    protected JLabel label1;
    protected JLabel label2;
        
    protected JButton nextButton;
    protected JButton exitButton;
    
    protected List idsAndRaces;
    
    
    public static void main(String args[])
    {
        EndGameGUI eg=new EndGameGUI();
        eg.setVisible(true);
    }
    
    public EndGameGUI(){
        
        this.initEndGameGUI(idsAndRaces);
    }
    
    public EndGameGUI(Frame owner,List l){        
        super(owner);      
        
        this.setLocation(owner.getX()+owner.getWidth()/2-WIDTH/2,
                owner.getY()+owner.getHeight()/2-HEIGHT/2);
        this.initEndGameGUI(l);
    }
    
    
    
    public void initEndGameGUI(List l){
        
//        this.setVisible(true);
//        this.setVisible(false);
        this.setTitle("Fin de juego");
        this.idsAndRaces=l;
        
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setModal(true);
        this.setResizable(false);
        
        this.setSize(WIDTH,HEIGHT);
        
        
        
        this.getContentPane().setBackground(ColoursDefinitions.LIGHT_BLUE_1);
        
        this.getContentPane().setLayout(null);
        
        
        this.getContentPane().add(initLabel1());
        this.getContentPane().add(initLabel2());
        
       
        this.getContentPane().add(Box.createGlue());
        this.getContentPane().add(initNextButton());
        this.getContentPane().add(initExitButton());
        this.getContentPane().add(Box.createGlue());
        
    }
    
    
    private JLabel initLabel1(){
        label1=new JLabel("Fin de partida",JLabel.CENTER);
        label1.setSize(this.WIDTH,20);
        label1.setLocation(0,20);
                
        return label1;
    }
    
    private JLabel initLabel2(){
        label2=new JLabel("nhQunh desea hacer?",JLabel.CENTER);
        label2.setSize(this.WIDTH,20);
        label2.setLocation(0,40);
                
        return label2;
    }
    
    private JButton initNextButton(){
        nextButton=new JButton("Nuevo escenario");
        nextButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        nextButton.setMargin(new Insets(0,0,0,0));
        nextButton.setSize(120,25);
        
        //Insets insets=this.getInsets();
        nextButton.setLocation((WIDTH-4)/2-nextButton.getWidth()-10,75);
        
        nextButton.addActionListener(new NextButtonListener());

        return nextButton;
    }
    
    private JButton initExitButton(){        
        exitButton=new JButton("Salir");
        exitButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        exitButton.setMargin(new Insets(0,0,0,0));
        exitButton.setSize(120,25);
        
        //Insets insets=this.getInsets();
        exitButton.setLocation((WIDTH-4)/2+10,75);
        
        exitButton.addActionListener(new ExitButtonListener());
        
        return exitButton;
    }
    
    
    private class NextButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            EndGameGUI.this.getOwner().dispose();
            EndGameGUI.this.dispose();
            CreateMatchGUI createMatch=new CreateMatchGUI(EndGameGUI.this.idsAndRaces,false);
            createMatch.setVisible(true);        
        }
    }
    
    
    private class ExitButtonListener implements ActionListener{
                
        public void actionPerformed(ActionEvent e){
            EndGameGUI.this.getOwner().dispose();
            EndGameGUI.this.dispose();
            
            ChooseGUI chooseGUI1 = new ChooseGUI();
			chooseGUI1.setVisible(true);
        }
    }
    
    

}//class EndGameGUI
