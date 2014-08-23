/*
 * Created on 13/03/2005
 *
 */
package network;

/**
 * @author Chiqui
 * 
 */

import java.io.IOException;
import java.net.*;
import java.nio.channels.*;

public class ClientServer {

    private SocketChannel canal;

    private static final int PUERTO = 9000;

    private Selector selector;
    
    private String nombre;
    
    private static final String SERVER_IP = /*"alumnos2.fdi.ucm.es";*/"192.168.0.2";

    // Constructor
    public ClientServer(String name) {
        /*
         * bufferEscritura = ByteBuffer.allocate(TAMANYO_BUFFER); bufferLectura =
         * ByteBuffer.allocate(TAMANYO_BUFFER); descodificador =
         * Charset.forName("ISO-8859-1").newDecoder();
         */
        //this.arrancar();
    		nombre = name;
    }

    public String dameNombre(){return nombre;}
    
    
    public void arrancar() {
        try {
            selector = Selector.open();
            canal = SocketChannel.open(new InetSocketAddress(/*InetAddress
                    .getLocalHost()*/SERVER_IP, PUERTO));
         //   canal = SocketChannel.open(new InetSocketAddress(InetAddress
         //           .getLocalHost(), PUERTO));
            canal.configureBlocking(false);
            canal.register(selector, SelectionKey.OP_READ);
        } catch (ConnectException e1) {
            System.out.println("Problemas para conectar");
            System.out
                    .println("Puede ser un problema de la configuracinhn de seguridad");
            System.exit(-1);
        } catch (UnknownHostException e2) {
            System.out.println("No se encuentra el servidor");
            System.exit(-1);
        } catch (IOException e3) {
            System.out.println("Error inesperado: " + e3.getStackTrace());
            System.exit(-1);
        }
    }


    public SocketChannel dameCanal() {
        return canal;
    }


    public Selector dameSelector() {
        return selector;
    }


    public int damePuerto() {
        return PUERTO;
    }

    /**
     * @return
     */
    public String getServerIP() {
        return this.SERVER_IP;
    }

}