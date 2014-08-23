/*
 * ChatFrontend.java
 */

package game;

import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import network.Chat;
import network.Package;
import util.ColoursDefinitions;

/**
 *  
 */
public class ChatFrontend extends JPanel {

    private Chat chat;

    private String history;

    private String message;

    private boolean chatMode;

    private boolean sendChatMessage;

    private JTextArea text1;

    private JTextField text2;
    
    private JLabel label;


    public ChatFrontend(Chat chat) {

        init(chat);
    }
    
    


    public void init(Chat chat) {
        this.chat = chat;
        chatMode = false;

        history = "YeyYe: como molan los meteoritos\n"
                + "Chunguito: snh, molan\n" + "YeyYe: dan vueltas\n";
        this.setLayout(null);
        //this.setLocation(10, 10);
        this.setSize(125, 235);
        this.setBackground(ColoursDefinitions.TRANSPARENT);
        /*this.setBorder(BorderFactory.createLineBorder(ColoursDefinitions.HUD,
                2));*/
        //this.setOpaque(false);
        //this.setIgnoreRepaint(true);
        
        label=new JLabel("Chat");
        label.setBounds(0,0,100,10);
        this.add(label);
        
        text1 = new JTextArea();
        text1.setFocusable(false);
        text1.setSize(125, 195);
        text1.setLocation(0, 15);
        text1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //text1.setDisabledTextColor(ColoursDefinitions.HUD);
        text1.setBackground(ColoursDefinitions.LIGHT_YELLOW_1);
        //text1.setForeground(ColoursDefinitions.HUD);
        text1.setLineWrap(true);
        text1.setWrapStyleWord(true);
        //text1.setText(history);
        //text1.setIgnoreRepaint(true);
        this.add(text1);

        text2 = new JTextField();
        text2.setSize(125, 20);
        text2.setLocation(0, 215);
        text2
                .setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
        //text2.setForeground(ColoursDefinitions.HUD);
        //text2.setOpaque(false);
        text2.addActionListener(new sendMessageListener());
        //text2.setIgnoreRepaint(true);
        this.add(text2);
        
        this.setVisible(true);
    }


    public void updateState() {
        if (!chat.mensaRecibido.equals("")) {
            history = history + chat.mensaRecibido;
            chat.mensaRecibido="";
            text1.setText(history);
        }
    }


    

    public Chat getChat(){return chat;} 

    private class sendMessageListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	
        	//Creamos el paquete de chat y lo enviamos.
        	char ident = '#';
        	message = text2.getText();
        	Package pak = new Package (ident,message);
         
         	try { 
         		
         		chat.sendMessageChatTCP(pak);
         		text2.setText("");
         		} catch (IOException io){System.out.println("Excepcion al enviar paquete chat");}

        }
    }
    /**
     * @param chat The chat to set.
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }
}