package GUI;

import game.*;
import game.actors.ActorPlayer;
import game.stages.StageInterface;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.net.URL;

import javax.help.*;
import javax.help.HelpSet;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;

import util.ColoursDefinitions;
import util.Constants;
import util.Tools;

/*
 *
 */

/**
 * Componente donde se pinta el juego. Extiende la clase JFrame y pinta su
 * ContentPane. Implementa el interfaz ScreenInterface.
 * 
 * @author
 *  
 */
public class MainWindowGUI extends JFrame {

    /*
     * Constantes que definen el tamanho de la ventana
     */
    private final int X_WIN_SIZE = 800;

    private final int Y_WIN_SIZE = 615;

    private Screen screen;

    public GameInterface game;

    private Image buffer1;

    private Image buffer2;

    private Image actualBuffer;

    private Graphics graphics;

    private boolean freezeDrawing = false;
   
    private boolean newGame=false;
    
    private HelpBroker helpBroker;

    /*
     * CONSTANTES COMPONENTES
     *  
     */

    private final int gameWindowX = 10;

    private final int gameWindowY = 10;

    private final int buttonsAlign = 675;

    private final int buttonsWidth = 100;

    /*
     * COMPONENTES
     */
    protected JButton porVidajButton = new JButton();

    protected JButton porPuntosjButton = new JButton();

    protected JButton AyudajButton = new JButton();

    protected JButton AbandonarjButton = new JButton();

    protected CustomComboBoxDemo comboBoxImages0 = new CustomComboBoxDemo();

    /*
     * protected CustomComboBoxDemo comboBoxImages1 = new CustomComboBoxDemo();
     * protected CustomComboBoxDemo comboBoxImages2 = new CustomComboBoxDemo();
     * protected CustomComboBoxDemo comboBoxImages3 = new CustomComboBoxDemo();
     * protected CustomComboBoxDemo comboBoxImages4 = new CustomComboBoxDemo();
     */

    protected WeaponJButton weaponJButton1;

    protected WeaponJButton weaponJButton2;

    protected WeaponJButton weaponJButton3;

    protected WeaponJButton weaponJButton4;


    protected JLabel RectojLabel = new JLabel();

    protected JLabel ParabolajLabel = new JLabel();

    protected JLabel GranadajLabel = new JLabel();

    protected JLabel CuerpojLabel = new JLabel();

    protected JLabel EspecialjLabel = new JLabel();

    protected JButton MsGjButton = new JButton();

    protected JLabel StatisticsjLabel = new JLabel();

    protected JList StatisticsjList = new JList();

    private GridBagLayout gridBagLayout1 = new GridBagLayout();

    private GridBagLayout gridBagLayout2 = new GridBagLayout();

    private GridBagLayout gridBagLayout3 = new GridBagLayout();

    private GridBagLayout gridBagLayout4 = new GridBagLayout();

    private GridBagLayout gridBagLayout5 = new GridBagLayout();

    private ChatFrontend chat;
    
    /**
     * Constructor. Inicializa la clase.
     *  
     */
    public MainWindowGUI(Screen s, GameInterface game) {
        chat = new ChatFrontend(null);
        initMainWindowGUI(s, game);

    }


    /**
     * Inicializa la clase
     *  
     */
    public void initMainWindowGUI(Screen s, GameInterface game) {

        initJavaHelp();
        //NullRepaintManager.install();

        this.screen = s;
        this.game = game;
        this.getContentPane().add(screen);

        initWin(game);

        /*
         * buffer1=this.getContentPane().createImage(800,600);
         * buffer2=this.getContentPane().createImage(800,600);
         * actualBuffer=buffer1; graphics=actualBuffer.getGraphics();
         */

        BufferCapabilities bc = new BufferCapabilities(new ImageCapabilities(
                true), new ImageCapabilities(true), null);

        try {
            this.createBufferStrategy(2, bc);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        graphics = this.getBufferStrategy().getDrawGraphics();

        initScreen();
        initControls();

    }


    /*
     * Inicializa el JFrame
     */
    private void initWin(GameInterface game) {
        /*
         * this.setVisible(true); this.setVisible(false);
         * this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         * this.setTitle("BangBang"); this.setLocation(30, 30);
         * 
         * this.setResizable(false);
         * 
         * //Insets insets = this.getInsets();
         * 
         * this.setSize(X_WIN_SIZE, Y_WIN_SIZE);
         * this.getContentPane().setLayout(null);
         */

        this.setJMenuBar(createMenus());

        ParabolajLabel.setText("Tiro tipo parnhbola");
        ParabolajLabel.setBounds(new Rectangle(226, 673, 118, 27));
        this.setVisible(true);
        this.setVisible(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setLocation(30, 30);
        this.getContentPane().setLayout(null);
        this.setSize(X_WIN_SIZE, Y_WIN_SIZE);
        Dimension screenRes=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(screenRes.width/2)-(X_WIN_SIZE/2);
        int y=(screenRes.height/2)-(Y_WIN_SIZE/2);
        this.setLocation(x,y);

        this.getContentPane().setForeground(ColoursDefinitions.LIGHT_BLUE_1);
        this.getContentPane().setBackground(ColoursDefinitions.LIGHT_BLUE_1);
              
        chat.setLocation(660, 255);
        this.getContentPane().add(chat);

        /** Seleccinhn de armas */

        weaponJButton1 = new WeaponJButton(Constants.WEAP_MELEE,(game.getMyPlayer()));

        weaponJButton2 = new WeaponJButton(Constants.WEAP_PARABOLIC,(game.getMyPlayer()));

        weaponJButton3 = new WeaponJButton(Constants.WEAP_GRENADE,(game.getMyPlayer()));

        weaponJButton4 = new WeaponJButton(Constants.WEAP_RECTILINEAR,(game.getMyPlayer()));


         
        weaponJButton1.setLocation(60, 500);
        this.getContentPane().add(weaponJButton1);
        weaponJButton1.setToolTipText("Arma cuerpo a cuerpo");

        weaponJButton2.setLocation(220, 500);
        this.getContentPane().add(weaponJButton2);
        weaponJButton2.setToolTipText("Arma de tiro Parabnhlico");

        weaponJButton3.setLocation(380, 500);
        this.getContentPane().add(weaponJButton3);
        weaponJButton3.setToolTipText("Arma tipo granada");

        weaponJButton4.setLocation(540, 500);
        this.getContentPane().add(weaponJButton4);
        weaponJButton4.setToolTipText("Arma de disparo rectilnhneo");

        
        porVidajButton.setBounds(new Rectangle(660, 200, 60, 20));
        porVidajButton.setMargin(new Insets(0, 0, 0, 0));
        porVidajButton.setFont(porVidajButton.getFont().deriveFont(10f));
        porVidajButton.setText("#vida");
        porVidajButton.setMnemonic(KeyEvent.VK_V);
        porVidajButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        porVidajButton.setFocusable(false);

        porPuntosjButton.setBounds(new Rectangle(660 + 66, 200, 60, 20));
        porPuntosjButton.setMargin(new Insets(0, 0, 0, 0));
        porPuntosjButton.setFont(porPuntosjButton.getFont().deriveFont(10f));
        porPuntosjButton.setText("#puntos");
        porPuntosjButton.setMnemonic(KeyEvent.VK_P);
        porPuntosjButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        porPuntosjButton.setFocusable(false);

        /*
         * MsGjButton.setBounds(new Rectangle(685, 440, 80, 25));
         * MsGjButton.setMargin(new Insets(0, 0, 0, 0));
         * MsGjButton.setText("Mensaje"); MsGjButton.setMnemonic(KeyEvent.VK_M);
         * MsGjButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
         * MsGjButton.setFocusable(false);
         */

        AyudajButton.setBounds(new Rectangle(685, 500, 80, 25));
        AyudajButton.setText("Ayuda");
        AyudajButton.setMnemonic(KeyEvent.VK_A);
        AyudajButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        AyudajButton.setFocusable(false);
        AyudajButton.addActionListener(
                new CSH.DisplayHelpFromSource( helpBroker ));

        AbandonarjButton.setBounds(new Rectangle(685, 530, 80, 25));
        AbandonarjButton.setText("Salir");
        AbandonarjButton.setMnemonic(KeyEvent.VK_S);
        AbandonarjButton.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        AbandonarjButton.setFocusable(false);
        AbandonarjButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.setResizable(false);
        this.setTitle("Bang Bang 3005");
        RectojLabel.setText("Tiro Recto");
        RectojLabel.setBounds(new Rectangle(64, 673, 74, 27));
        GranadajLabel.setText("Granada");
        GranadajLabel.setBounds(new Rectangle(391, 673, 66, 27));
        CuerpojLabel.setText("Cuerpo a Cuerpo");
        CuerpojLabel.setBounds(new Rectangle(519, 673, 106, 27));
        EspecialjLabel.setText("Especial");
        EspecialjLabel.setBounds(new Rectangle(691, 673, 59, 27));

        StatisticsjLabel.setText("Estadnhsticas");
        StatisticsjLabel.setBounds(new Rectangle(660, 10, 102, 10));

        StatisticsjList.setBounds(new Rectangle(660, 25, 125, 170));
        StatisticsjList.setBackground(ColoursDefinitions.LIGHT_YELLOW_1);
        StatisticsjList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        StatisticsjList.setFocusable(false);

        /*
         * comboBoxImages4.setLayout(gridBagLayout5);
         * comboBoxImages3.setLayout(gridBagLayout4);
         * comboBoxImages2.setLayout(gridBagLayout3);
         * comboBoxImages1.setLayout(gridBagLayout2);
         * comboBoxImages0.setLayout(gridBagLayout1);
         * 
         * comboBoxImages0.setBounds(new Rectangle(19, 520, 120, 156));
         * comboBoxImages1.setBounds(new Rectangle(179, 520, 120, 156));
         * comboBoxImages2.setBounds(new Rectangle(335, 520, 120, 156));
         * comboBoxImages3.setBounds(new Rectangle(490, 520, 120, 156));
         * comboBoxImages4.setBounds(new Rectangle(670, 520, 120, 156));
         */

        this.getContentPane().add(porVidajButton);
        this.getContentPane().add(AbandonarjButton);
        this.getContentPane().add(MsGjButton);
        this.getContentPane().add(porPuntosjButton);
        this.getContentPane().add(StatisticsjLabel);
        this.getContentPane().add(StatisticsjList);

        /*
         * this.getLayeredPane().add(comboBoxImages4);
         * this.getLayeredPane().add(comboBoxImages3);
         * this.getLayeredPane().add(comboBoxImages0);
         * this.getLayeredPane().add(comboBoxImages2);
         * this.getLayeredPane().add(comboBoxImages1);
         */

        this.getContentPane().add(RectojLabel, null);
        this.getContentPane().add(ParabolajLabel, null);
        this.getContentPane().add(GranadajLabel, null);
        this.getContentPane().add(CuerpojLabel, null);
        this.getContentPane().add(EspecialjLabel, null);

        this.getContentPane().add(AyudajButton, null);
        
        this.changePlayerGUI(game.getMyPlayer());
    }


    private JMenuBar createMenus() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(ColoursDefinitions.LIGHT_BLUE_2);

        JMenu menuArchivo = new JMenu("Juego");
        menuArchivo.addMenuListener(new menuListener());
        menuArchivo.setBackground(ColoursDefinitions.LIGHT_BLUE_2);

        JMenuItem menuItemNuevo = new JMenuItem("Nuevo");
        menuItemNuevo.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        menuItemNuevo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                newGame=true;
            }
        });
        
        
        JMenuItem menuItemSoundControl = new JMenuItem("Control de sonido");
        menuItemSoundControl.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        menuItemSoundControl.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sound.SoundControlGUI gui = new sound.SoundControlGUI(
                        MainWindowGUI.this, game.getStage().getAudioManager());
            }
        });

        JMenuItem menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        menuItemSalir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuArchivo.add(menuItemNuevo);
        menuArchivo.add(menuItemSoundControl);
        menuArchivo.add(menuItemSalir);

        JMenu menuAyuda = new JMenu("Ayuda");
        menuAyuda.addMenuListener(new menuListener());
        menuAyuda.setBackground(ColoursDefinitions.LIGHT_BLUE_2);

        JMenuItem menuItemContenidos = new JMenuItem("Contenidos");
        menuItemContenidos.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        menuItemContenidos.addActionListener(
            new CSH.DisplayHelpFromSource( helpBroker ));
        JMenuItem menuItemAcerca = new JMenuItem("Acerca de...");
        menuItemAcerca.setBackground(ColoursDefinitions.LIGHT_BLUE_2);
        menuItemAcerca.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                CreditGUI creditGUI = new CreditGUI(MainWindowGUI.this);
                creditGUI.setVisible(true);
            }
        });

        menuAyuda.add(menuItemContenidos);
        menuAyuda.addSeparator();
        menuAyuda.add(menuItemAcerca);

        menuBar.add(menuArchivo);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menuAyuda);

        return menuBar;

    }
    
    private void initJavaHelp(){
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
        helpBroker = hs.createHelpBroker();
        helpBroker.setSize(new Dimension(800,500));
//      Create a "Help" menu item to trigger the help viewer:
  
        
    }


    private JDialog createAboutDialog() {
        JDialog dialog = new JDialog(this, "Acerca de Bang Bang 3005");
        dialog.setSize(350, 250);
        dialog.setLocation(this.getLocation().x + this.getSize().width / 2
                - dialog.getSize().width / 2, this.getLocation().y
                + this.getSize().height / 2 - dialog.getSize().height / 2);
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.getContentPane().setLayout(
                new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Bang Bang 3005. 2004-2005." /*
                                                                 * + "Ibnhn,
                                                                 * Bnhlder,
                                                                 * Carlos(el
                                                                 * Ciego),
                                                                 * Jacobo,\n\n" +
                                                                 * "Carlos(Pekenhnhn),
                                                                 * Germnhn,
                                                                 * Txema, Javi,
                                                                 * Sergio,
                                                                 * David,\n\n" +
                                                                 * "Rubnhn,Jonnhs
                                                                 * y nhngel"
                                                                 */);
        label1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        //label1.setLocation(20,20);
        label1.setSize(200, 80);

        dialog.getContentPane().add(Box.createGlue());
        dialog.getContentPane().add(label1);
        dialog.getContentPane().add(Box.createGlue());
        //CreditGUI creditGUI=new CreditGUI();
        return dialog;

    }


    private class menuListener implements MenuListener {

        public void menuSelected(MenuEvent e) {
            freezeDrawing = true;
            Component comp = ((Component) e.getSource());

        }


        public void menuDeselected(MenuEvent e) {
            freezeDrawing = false;
            Component comp = ((Component) e.getSource());

        }


        public void menuCanceled(MenuEvent e) {
            freezeDrawing = false;
            Component comp = ((Component) e.getSource());

        }
    }


    private void initScreen() {
        screen.setSize(screen.getXRes(), screen.getYRes());
        screen.setLocation(gameWindowX, gameWindowY);
        screen.initScreenGraphics();

        JPanel gameWindowFrame = new JPanel();
        gameWindowFrame.setSize(screen.getXRes() + 5, screen.getYRes() + 5);
        gameWindowFrame.setLocation(gameWindowX - 2, gameWindowY - 2);
        gameWindowFrame.setBorder(BorderFactory.createEtchedBorder());
        this.getContentPane().add(gameWindowFrame);
        System.out.println("Dibujando ventana");

    }


    /*
     * Inicializa los controles como los botones de seleccinhn de armas
     */
    private void initControls() {

    }


    /**
     * Devuelve el nhrea sobre el que se pinta el juego
     */
    public ScreenInterface getScreen() {
        return screen;
    }


    public Graphics getBufferGraphics() {
        graphics.translate(this.getInsets().left, this.getInsets().top);
        return graphics;
    }


    public void showWindow() {
        /*
         * this.getLayeredPane().getGraphics().drawImage(actualBuffer,0,0,null);
         * graphics.dispose();
         * 
         * if (actualBuffer==buffer1) actualBuffer=buffer2; else if
         * (actualBuffer==buffer2) actualBuffer=buffer1;
         * 
         * graphics=actualBuffer.getGraphics();
         */

        BufferStrategy be = this.getBufferStrategy();

        be.show();
        graphics.dispose();
        graphics = be.getDrawGraphics();

    }
    
 

    private class MessageActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

        }
    }

    
    public void changePlayerGUI(ActorPlayer player){
        weaponJButton1.setPlayer(player);
        weaponJButton2.setPlayer(player);
        weaponJButton3.setPlayer(player);
        weaponJButton4.setPlayer(player);

    	    	
    	if (player.getRaceCode()==Constants.RACE_SCRAPER){ 
    	    this.getContentPane().setBackground(ColoursDefinitions.SCRAPER_GUI_COLOR);
    	    this.getContentPane().setForeground(ColoursDefinitions.SCRAPER_GUI_COLOR);
    	}
    	else if (player.getRaceCode()==Constants.RACE_BIOMORPH){
    	    this.getContentPane().setBackground(ColoursDefinitions.BIOMORPH_GUI_COLOR);
    	    this.getContentPane().setForeground(ColoursDefinitions.BIOMORPH_GUI_COLOR);
    	}
    	else if (player.getRaceCode()==Constants.RACE_ELEO){
    	    this.getContentPane().setBackground(ColoursDefinitions.ELEO_GUI_COLOR);
    	    this.getContentPane().setForeground(ColoursDefinitions.ELEO_GUI_COLOR);
    	}
    	else if (player.getRaceCode()==Constants.RACE_GMARINE){
    	    this.getContentPane().setBackground(ColoursDefinitions.GMARINE_GUI_COLOR);
    	    this.getContentPane().setForeground(ColoursDefinitions.GMARINE_GUI_COLOR);
    	}
    	else if (player.getRaceCode()==Constants.RACE_NINFA){
    	    this.getContentPane().setBackground(ColoursDefinitions.NINFA_GUI_COLOR);
    	    this.getContentPane().setForeground(ColoursDefinitions.NINFA_GUI_COLOR);
    	}
    	
    	int weapon=player.getCurrentWeapon()/10;
    	int weaponLevel=player.getCurrentWeapon()%10;
    	
    	if (weapon==Constants.WEAP_MELEE){
    	    WeaponJButton.setButtonActivated(weaponJButton1);
    	    weaponJButton1.setCurrentLevel(weaponLevel);
    	}
    	else if (weapon==Constants.WEAP_PARABOLIC){
    	    WeaponJButton.setButtonActivated(weaponJButton2);
    	    weaponJButton2.setCurrentLevel(weaponLevel);
    	}
    	else if (weapon==Constants.WEAP_GRENADE){
    	    WeaponJButton.setButtonActivated(weaponJButton3);
    	    weaponJButton3.setCurrentLevel(weaponLevel);
    	}
    	else if (weapon==Constants.WEAP_RECTILINEAR){
    	    WeaponJButton.setButtonActivated(weaponJButton4);
    	    weaponJButton4.setCurrentLevel(weaponLevel);
    	}
    }
    


    /**
     * @return Returns the freezeDrawing.
     */
    public boolean isFreezeDrawing() {
        return freezeDrawing;
    }


    /**
     * @return Returns the chat.
     */
    public ChatFrontend getChat() {
        return chat;
    }


    void AyudajButton_actionPerformed(ActionEvent e) {
        /*HelpGUI helpGUI = new HelpGUI();
        helpGUI.setVisible(true);*/
    }
    /**
     * @return Returns the newGame.
     */
    public boolean isNewGame() {
        return newGame;
    }
}




class MainWindowGUI_AyudajButton_actionAdapter implements
        java.awt.event.ActionListener {

    MainWindowGUI adaptee;


    MainWindowGUI_AyudajButton_actionAdapter(MainWindowGUI adaptee) {
        this.adaptee = adaptee;
    }


    public void actionPerformed(ActionEvent e) {
        adaptee.AyudajButton_actionPerformed(e);
    }
}