/*
 * Created on 14-may-2005
 *
 */
package test;

import java.nio.ByteBuffer;

import network.Chat;
import network.ClientServer;

/**
 * Esta clase prueba la funcionalidad de los clientes, arrancando el juego con
 * inicializaciones. En cada cliente debe haber una clase de este tipo, pero
 * cambiando la X al construir el ClientServer.
 * 
 * @author CarlosG
 *  
 */
public class TestClientX {
    ClientServer css;

    Chat chat;

    /**
     * Construye la clase de prueba
     * 
     * @param css
     * @param chat
     */
    public TestClientX(ClientServer css, Chat chat) {

        //Cambiar la X por un numero distinto en cada cliente.
        this.css = new ClientServer("Jugador X");
        this.chat = new Chat(css);

        initTestClientX();
    }

    public void initTestClientX() {
    }

    public void run() {
        //Arranca el ClientServer
        css.arrancar();

        //Creamos una pseudo-partida
        ByteBuffer recibo = chat.makeCrearPartida(1, 1, 2);
        chat.sendCrearPartida(recibo);

        //Escuchamos si podemos empezar
        listen();

        //Iniciar la partida, que consiste en crear el stage y luego crear el
        //GameEngine, que crearnh (o debernha) un NetworkGame, metiendo a los
        // players

    }

    public void listen() {
        Thread t = new Thread("ListenTest") {
            public void run() {
                while (true) {
                    chat.showMessageCreaPartidaTCP();
                }
            }
        };
    }
}