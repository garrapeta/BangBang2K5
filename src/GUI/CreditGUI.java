package GUI;

import javax.swing.*;

import util.Tools;

import java.awt.*;
import java.io.File;

public class CreditGUI extends JDialog {

    private JLabel FotojLabel = new JLabel();

    public CreditGUI(Frame owner) throws HeadlessException {
        super(owner);
        try {
            jbInit(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit(Frame owner) throws Exception {        
        this.setResizable(false);
        this.setTitle("Crnhditos");
        this.getContentPane().setBackground(new Color(212, 224, 230));
        this.setSize(new Dimension(355, 430));
        this.setLocation(owner.getX()+owner.getWidth()/2-this.getWidth()/2,
                owner.getY()+owner.getHeight()/2-this.getHeight()/2);
        FotojLabel.setIcon(new ImageIcon(Tools.loadBufferedImage("src"+File.separator+"GUI"+File.separator+"Images"+File.separator+"credit.png")));
       // FotojLabel.setMaximumSize(new Dimension(530, 500));
        FotojLabel.setText("");
        FotojLabel.setBounds(new Rectangle(0, 0, 550, 400));
       
        this.getContentPane().setLayout(null);
        this.getContentPane().add(FotojLabel);
    }
}
