
/*
 * HelpTest.java
 */
package GUI;
import java.net.*;
import java.net.URL;

import javax.help.*;
import javax.swing.*;
/**
 *
 */
public class HelpTest  {
    

    public static void main(String[] args) {
       
//      // Find the HelpSet file and create the HelpSet object:
        String helpHS = "HelpSet.hs";
        ClassLoader cl = HelpTest.class.getClassLoader();
        HelpSet hs;
        try {
           URL hsURL = HelpSet.findHelpSet(cl, helpHS);
           hs = new HelpSet(null, hsURL);
        } catch (Exception ee) {
           // Say what the exception really is
           System.out.println( "HelpSet " + ee.getMessage());
           System.out.println("HelpSet "+ helpHS +" not found");
           return;
        }
//      Create a HelpBroker object:
        HelpBroker hb = hs.createHelpBroker();
//      Create a "Help" menu item to trigger the help viewer:
        
        JFrame frame=new JFrame();
        frame.setSize(200,200);
        JButton button=new JButton("help!");
        button.addActionListener(new CSH.DisplayHelpFromSource( hb ));
        frame.getContentPane().add(button);
        frame.setVisible(true);

    }
}
