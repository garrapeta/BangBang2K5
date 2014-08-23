/*
 * Created on 12/03/2005
 *
 */
package network;

/**
 * @author Chiqui
 * 
 */
import game.GameInterface;
import game.NetworkGame;
import game.actors.ActorPlayer;
import game.logicEngine.LogicActor;
import game.logicEngine.LogicActorPlayer;
import game.stages.Stage;
import game.stages.StageEleuron;
import game.stages.StageNodrubaz;
import game.stages.StageOcb5;
import game.stages.StageSilicosis;
import game.stages.StageTest;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;

import util.Constants;
import util.Tools;

import GUI.CreateMatchGUI;
import GUI.RoomsGUI;
import GUI.SplashGUI;
import GUI.WaitingGUI;

public class Chat {

    private SocketChannel channel;
    
    private String serverIP;

    //private static final int PUERTO = 9000;
    private static final int TAMANYO_BUFFER = 100;

    private static final int TIEMPO_ENTRE_ESCRITURAS = 10; // 1 segundo

    private Selector selector;
    private Selector selector2;
    

    private ByteBuffer bufferWriterTCP;

    private ByteBuffer bufferReaderTCP;
    private ByteBuffer bufferReaderTCP2;
    
    private ByteBuffer bufferWriterUDP;

    private ByteBuffer bufferReaderUDP;

    private CharsetDecoder descodificador;

    public String mensaRecibido = "";

    public String mensaje = "";
    
    public DatagramChannel canalUDP;
    public DatagramSocket socketDatagrama;
    
    ClientPlayer cPlayer;
   // List jugadores = new ArrayList();
   // StringTokenizer separador;
    
    private RoomsGUI rooms;
    private CreateMatchGUI cmg;
    private WaitingGUI wg;
    private GameInterface currentGame;


    public Chat(ClientServer css) {
        channel = css.dameCanal();
        this.serverIP = css.getServerIP();
        cPlayer = new ClientPlayer(css.dameNombre());
       // cPlayer.setName(css.dameNombre());        
        selector = css.dameSelector();
        try{
      
        
//      Se crea un canal DatagramChannel
		canalUDP = DatagramChannel.open();
		// Se configura como canal sin bloqueo
		canalUDP.configureBlocking(false);
		
		//InetSocketAddress isa = new InetSocketAddress("localhost",9000);
		//canalUDP.connect(isa);
		
		//////////////////////////////////////////////
		//Creo que hay que descomentar estas dos lineas
		
		socketDatagrama = canalUDP.socket();
		
		socketDatagrama.bind(new InetSocketAddress(9002));
		
		
		/////////////////////////////////////////////////////
		
		//System.out.println(socketDatagrama.getPort());
		selector2=Selector.open();
		canalUDP.register(selector2,SelectionKey.OP_READ);
        }catch (IOException ioe){System.out.println("Excepcion al crear el canalUDP");}
        bufferWriterTCP = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
        bufferReaderTCP = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
        bufferReaderTCP2 = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE_LARGE);
        bufferWriterUDP = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
        bufferReaderUDP = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
        descodificador = Charset.forName("ISO-8859-1").newDecoder();
    }

    public void setRooms(RoomsGUI rg){rooms=rg;};
    public RoomsGUI getRoomsGUI(){return rooms;};
    public void setCreateMatchGUI(CreateMatchGUI cm){cmg=cm;};
    public void setWaitingGUI(WaitingGUI wm){wg=wm;};  
    
    public void showMessageTCP(LogicActor logic) {
        try {
            // selectNow() obliga a que select() vuelva, aunque no haya ningnhn
            // clave.
            // Es decir, aunque no haya ningnhn suceso de E/S.
            selector.selectNow();

            // Se obtienen las claves (pudiera ser que el Set estuviera vacnho)
            Set readyKeys = selector.selectedKeys();

            // Se recorren las claves y se van procesando.
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();

                // Se elimina la clave para que no haya sucesos "fantasmas".
                it.remove();

                SocketChannel channel = (SocketChannel) key.channel();

                // Se deja el buffer listo para leer.
                bufferReaderTCP.clear();

                // Se leen datos del canal y se almacenan en el buffer.
                long numBytes = channel.read(bufferReaderTCP);

                // Si el cnhdigo que devuelve read() es -1, indica que se ha
                // recibido el nhltimo byte del mensaje y que el cliente ha
                // perdido
                // la conexinhn con el servidor.
                if (numBytes == -1) {
                    channel.close();
                } else {                	
                    bufferReaderTCP.flip();
                    fuckPackage(bufferReaderTCP,logic);
                    }
            } //fin while
        } //fin try
        catch (Exception e) {
            System.out.println("Excepcinhn al leer del canal en los TCP");
        }
    }//fin de mostrarMensaje
    
    
    public void showMessageSalasTCP(/*RoomsGUI room*/) {
        try {
            // selectNow() obliga a que select() vuelva, aunque no haya ningnhn
            // clave.
            // Es decir, aunque no haya ningnhn suceso de E/S.
            selector.selectNow();

            // Se obtienen las claves (pudiera ser que el Set estuviera vacnho)
            Set readyKeys = selector.selectedKeys();

            // Se recorren las claves y se van procesando.
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();

                // Se elimina la clave para que no haya sucesos "fantasmas".
                it.remove();

                SocketChannel channel = (SocketChannel) key.channel();

                // Se deja el buffer listo para leer.
                bufferReaderTCP.clear();

                // Se leen datos del canal y se almacenan en el buffer.
                long numBytes = channel.read(bufferReaderTCP);

                // Si el cnhdigo que devuelve read() es -1, indica que se ha
                // recibido el nhltimo byte del mensaje y que el cliente ha
                // perdido
                // la conexinhn con el servidor.
                if (numBytes == -1) {
                    channel.close();
                //    bufferReaderTCP.clear();
                //    return bufferReaderTCP;
                } else {                	
                    bufferReaderTCP.flip();
                  // System.out.println("antes process");
                    rooms.processPackageSala(bufferReaderTCP);
                    //  processPackageSala(bufferReaderTCP,room);
              //      return bufferReaderTCP;
                //    fuckPackage(bufferReaderTCP,logic);
                    
                    }
            } //fin while
        } //fin try
        catch (Exception e) {
            System.out.println("Excepcinhn al leer del canal en las Salas TCP");
        }
        
    }//fin de mostrarMensaje
    
    public void showMessageCreaPartidaTCP(/*CreateMatchGUI create*/) {
        try {
            // selectNow() obliga a que select() vuelva, aunque no haya ningnhn
            // clave.
            // Es decir, aunque no haya ningnhn suceso de E/S.
            selector.selectNow();

            // Se obtienen las claves (pudiera ser que el Set estuviera vacnho)
            Set readyKeys = selector.selectedKeys();

            // Se recorren las claves y se van procesando.
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();

                // Se elimina la clave para que no haya sucesos "fantasmas".
                it.remove();

                SocketChannel channel = (SocketChannel) key.channel();

                // Se deja el buffer listo para leer.
                bufferReaderTCP.clear();

                // Se leen datos del canal y se almacenan en el buffer.
                long numBytes = channel.read(bufferReaderTCP);

                // Si el cnhdigo que devuelve read() es -1, indica que se ha
                // recibido el nhltimo byte del mensaje y que el cliente ha
                // perdido
                // la conexinhn con el servidor.
                if (numBytes == -1) {
                    channel.close();
                } else {                	
                    bufferReaderTCP.flip();
//                	System.out.println("buffer: "+bufferReaderTCP.getChar());                	bufferReaderTCP.rewind();
//                    ByteBuffer be=ByteBuffer.allocate(100);
//                    be=bufferReaderTCP;                    
                    /********************************************/
                    // ESTO ES LO QUE HABIA ANTES
                    cmg.processPackageCrearPartida(bufferReaderTCP);
                    //
                    /********************************************/
                    
                    //processPackageCrearPartida(bufferReaderTCP,create);
                    
                //    fuckPackage(bufferReaderTCP,logic);
                    }
            } //fin while
        } //fin try
        catch (Exception e) {
            System.out.println("Excepcinhn al leer del canal en el CrearPartida");
            e.printStackTrace();
        }
    }//fin de mostrarMensaje
    
    
    public void showMessageUnirmeAPartidaTCP(/*CreateMatchGUI create*/) {
        try {
            // selectNow() obliga a que select() vuelva, aunque no haya ningnhn
            // clave.
            // Es decir, aunque no haya ningnhn suceso de E/S.
            selector.selectNow();

            // Se obtienen las claves (pudiera ser que el Set estuviera vacnho)
            Set readyKeys = selector.selectedKeys();

            // Se recorren las claves y se van procesando.
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();

                // Se elimina la clave para que no haya sucesos "fantasmas".
                it.remove();

                SocketChannel channel = (SocketChannel) key.channel();

                // Se deja el buffer listo para leer.
                bufferReaderTCP2.clear();

                // Se leen datos del canal y se almacenan en el buffer.
                long numBytes = channel.read(bufferReaderTCP2);

                // Si el cnhdigo que devuelve read() es -1, indica que se ha
                // recibido el nhltimo byte del mensaje y que el cliente ha
                // perdido
                // la conexinhn con el servidor.
                if (numBytes == -1) {
                    channel.close();
                } else {                	
                    bufferReaderTCP2.flip();
                    rooms.processPackageUniroEmpezar(bufferReaderTCP2);
                   // rooms.processPackageUnirmeAPartida(bufferReaderTCP2);
                    //processPackageCrearPartida(bufferReaderTCP,create);
                    
                //    fuckPackage(bufferReaderTCP,logic);
                    }
            } //fin while
        } //fin try
        catch (Exception e) {
            System.out.println("Excepcinhn al leer del canal al UniraPartida");
        }
    }//fin de mostrarMensaje
    
    public void showMessageSalaEsperaTCP() {
        try {
            // selectNow() obliga a que select() vuelva, aunque no haya ningnhn
            // clave.
            // Es decir, aunque no haya ningnhn suceso de E/S.
            selector.selectNow();

            // Se obtienen las claves (pudiera ser que el Set estuviera vacnho)
            Set readyKeys = selector.selectedKeys();

            // Se recorren las claves y se van procesando.
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();

                // Se elimina la clave para que no haya sucesos "fantasmas".
                it.remove();

                SocketChannel channel = (SocketChannel) key.channel();

                // Se deja el buffer listo para leer.
                bufferReaderTCP2.clear();

                // Se leen datos del canal y se almacenan en el buffer.
                long numBytes = channel.read(bufferReaderTCP2);
                
                // Si el cnhdigo que devuelve read() es -1, indica que se ha
                // recibido el nhltimo byte del mensaje y que el cliente ha
                // perdido
                // la conexinhn con el servidor.
                if (numBytes == -1) {
                    channel.close();
                } else {                	
                    bufferReaderTCP2.flip();
                   // System.out.println("buffer: "+bufferReaderTCP2.getChar());
                    //System.out.println("Antes del pmsala espera");
                    wg.processPackageSalaEspera(bufferReaderTCP2);
                    
                //    fuckPackage(bufferReaderTCP,logic);
                    }
            } //fin while
        } //fin try
        catch (Exception e) {
            System.out.println("Excepcinhn al leer del canal en SalaEspera");
            e.printStackTrace();
        }
        
    }//fin de mostrarMensaje
    
    public void showMessageNGTCP() {
        try {
            // selectNow() obliga a que select() vuelva, aunque no haya ningnhn
            // clave.
            // Es decir, aunque no haya ningnhn suceso de E/S.
            selector.selectNow();

            // Se obtienen las claves (pudiera ser que el Set estuviera vacnho)
            Set readyKeys = selector.selectedKeys();

            // Se recorren las claves y se van procesando.
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();

                // Se elimina la clave para que no haya sucesos "fantasmas".
                it.remove();

                SocketChannel channel = (SocketChannel) key.channel();

                // Se deja el buffer listo para leer.
                bufferReaderTCP.clear();

                // Se leen datos del canal y se almacenan en el buffer.
                long numBytes = channel.read(bufferReaderTCP);

                // Si el cnhdigo que devuelve read() es -1, indica que se ha
                // recibido el nhltimo byte del mensaje y que el cliente ha
                // perdido
                // la conexinhn con el servidor.
                if (numBytes == -1) {
                    channel.close();
                } else {                	
                    bufferReaderTCP.flip();
                    ((NetworkGame) currentGame).processBufferTCP(bufferReaderTCP);                   
                    }
            } //fin while
        } //fin try
        catch (Exception e) {
            System.out.println("Excepcinhn al leer del canal en los TCP");
        }
    }//fin de mostrarMensaje
    
    
    public ByteBuffer makePeticionSala(){
    	ByteBuffer b = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
    	b.clear();
    	b.rewind();
    	b.putChar('%');
    	return b;
    }
    public ByteBuffer makeCrearPartida(int sala,int scene,int limit){
    	ByteBuffer b = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
    	b.clear();
    	b.rewind();
    	b.putChar('/');
    	b.putInt(sala);
    	b.putInt(scene);
    	b.putInt(limit);
    	String cadena = cPlayer.getName() + " " + cPlayer.getPlayerType();
    	b.put(cadena.getBytes());
    	return b;
    }
    
    public ByteBuffer makeUnirmeAPartida(int sala){
    	ByteBuffer b = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
    	b.clear();
    	b.rewind();
    	b.putChar('(');
    	b.putInt(sala);
    	String cadena = cPlayer.getName() + " " + cPlayer.getPlayerType();
    	b.put(cadena.getBytes());
    	return b;
    }
    
    public void sendPeticionSalas(ByteBuffer b){
    		b.rewind();
    		bufferWriterTCP.put(b);
    		bufferWriterTCP.flip();
    		WriteChannel(channel,bufferWriterTCP);
    		//System.out.println("Envio peticion salas");
    }
    
    public void sendCrearPartida(ByteBuffer b){
    		b.rewind();
    		//bufferWriterTCP=b;
    		bufferWriterTCP.put(b);
    		bufferWriterTCP.flip();
    		WriteChannel(channel,bufferWriterTCP);
    }
    
    public void sendDead(int room, ActorPlayer player) {
    	/*ByteBuffer bb = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
		bb.putChar(Constants.PACK_REMOVE_PLAYER);
		bb.putInt(room);
		bb.put(((LogicActorPlayer) player.getLogic()).getUserID().getBytes());
		WriteChannel(channel,bb);*/
    	bufferWriterTCP.clear();
    	bufferWriterTCP.putChar(Constants.PACK_REMOVE_PLAYER);
    	bufferWriterTCP.putInt(room);
    	bufferWriterTCP.put(((LogicActorPlayer) player.getLogic()).getUserID().getBytes());
    	bufferWriterTCP.flip();
    	WriteChannel(channel,bufferWriterTCP);
    }
    
    public void sendShot(double angle,long fuerza,int roomGame) {
    	/*ByteBuffer bb = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
		bb.putChar(Constants.PACK_REMOVE_PLAYER);
		bb.putInt(room);
		bb.put(((LogicActorPlayer) player.getLogic()).getUserID().getBytes());
		WriteChannel(channel,bb);*/
    	bufferWriterTCP.clear();
    	bufferWriterTCP.putChar(Constants.PACK_SHOOT);
    	bufferWriterTCP.putInt(roomGame);
    	bufferWriterTCP.putDouble(angle);
    	bufferWriterTCP.putLong(fuerza);
    	bufferWriterTCP.flip();
    	WriteChannel(channel,bufferWriterTCP);
    }
    
    public void sendConfirmInterlude(int room) {
    	/*ByteBuffer bb = ByteBuffer.allocate(Constants.BYTE_BUFFER_SIZE);
		bb.putChar(Constants.PACK_CONFIRM_INTERLUDE);
		bb.putInt(room);
		WriteChannel(channel,bb);*/
    	bufferWriterTCP.clear();
    	bufferWriterTCP.putChar(Constants.PACK_CONFIRM_INTERLUDE);
    	bufferWriterTCP.putInt(room);
    	bufferWriterTCP.flip();
    	WriteChannel(channel,bufferWriterTCP);
    }
    
    public void sendUnirmeAPartida(ByteBuffer b){
		b.rewind();
		bufferWriterTCP.put(b);
		bufferWriterTCP.flip();
		WriteChannel(channel,bufferWriterTCP);
    }
    
    
    public void showMessageUDP() {
        try {
            // selectNow() obliga a que select() vuelva, aunque no haya ningnhn
            // clave.
            // Es decir, aunque no haya ningnhn suceso de E/S.
            selector2.selectNow();

            // Se obtienen las claves (pudiera ser que el Set estuviera vacnho)
            Set readyKeys = selector2.selectedKeys();

            // Se recorren las claves y se van procesando.
            Iterator it2 = readyKeys.iterator();
            while (it2.hasNext()) {
                SelectionKey key2 = (SelectionKey) it2.next();

                // Se elimina la clave para que no haya sucesos "fantasmas".
                it2.remove();

                DatagramChannel channel = (DatagramChannel) key2.channel();

                // Se deja el buffer listo para leer.
                bufferReaderUDP.clear();

                // Se leen datos del canal y se almacenan en el buffer.
                channel.receive(bufferReaderUDP);

                // Si el cnhdigo que devuelve read() es -1, indica que se ha
                // recibido el nhltimo byte del mensaje y que el cliente ha
                // perdido
                // la conexinhn con el servidor.                	
                    bufferReaderUDP.flip();
                    ((NetworkGame) currentGame).processBufferUDP(bufferReaderUDP);
                    
            } //fin while
        } //fin try
        catch (Exception e) {
            System.out.println("Excepcinhn al leer del canalUDP.");
        }
    }//fin de mostrarMensaje
    

    /*public void sendMessageByteBuffer(ByteBuffer bs) {
        bufferWriter.clear();       
        bufferWriter.put(bs); 
        bs.clear();      
        bufferWriter.put(System.getProperty("line.separator").getBytes());
        bufferWriter.flip();
        WriteChannel(channel, bufferWriter);
       	}*/
    
    /*public void sendMessageString(String mensaje) {
        bufferWriter.clear();              
        bufferWriter.put(mensaje.getBytes());       
        bufferWriter.put(System.getProperty("line.separator").getBytes());
        bufferWriter.flip();
        WriteChannel(channel, bufferWriter);
    }//fin de enviarMensaje*/
    
    
    public void sendMessageStateUDP(Package mensaje) throws IOException{
		bufferWriterUDP.clear();       
		ByteBuffer b = (ByteBuffer)mensaje.dameEstado().desmontar();
		b.rewind();
		bufferWriterUDP.putChar(mensaje.getIdentifier());
		bufferWriterUDP.put(b);
    		bufferWriterUDP.put(System.getProperty("line.separator").getBytes());
    		bufferWriterUDP.flip();
    		//La direccion es la del servidor
    		InetSocketAddress ina=new InetSocketAddress(/*InetAddress.getLocalHost()*/this.serverIP,9001);
    		// InetSocketAddress ina=new InetSocketAddress(this.channel.socket().getInetAddress(),this.channel.socket().getPort());
    		canalUDP.send(bufferWriterUDP,ina);
    } 
   
    
    public void sendMessageStateTCP(Package mensaje) throws IOException{
    			bufferWriterTCP.clear();       
    			ByteBuffer b = (ByteBuffer)mensaje.dameEstado().desmontar();
    			b.rewind();
    			bufferWriterTCP.putChar(mensaje.getIdentifier());
    			bufferWriterTCP.put(b);
    			bufferWriterTCP.put(System.getProperty("line.separator").getBytes());
            	bufferWriterTCP.flip();
            	WriteChannel(channel,bufferWriterTCP);
            }
    
    public void sendMessageChatTCP(Package mensaje) throws IOException{
    		bufferWriterTCP.clear();
    		bufferWriterTCP.putChar(mensaje.getIdentifier());
    		bufferWriterTCP.put(mensaje.dameCadena().getBytes());
    		bufferWriterTCP.put(System.getProperty("line.separator").getBytes());
    		bufferWriterTCP.flip();
		WriteChannel(channel,bufferWriterTCP);
    		}
    

    public void WriteChannel(SocketChannel channel2, ByteBuffer bufferWriter2) {
        try {
            while (bufferWriter2.remaining() > 0) {
                channel2.write(bufferWriterTCP);
            }
            try {
                Thread.sleep(TIEMPO_ENTRE_ESCRITURAS);
            } catch (InterruptedException e1) {
            }
        } catch (IOException e2) {
        }

        // Se deja preparado el buffer para escribir.
        bufferWriter2.rewind();
     
    }//fin escribirCanal
        
    public SocketChannel getSocket(){
    	return channel;    
    }
    
    public ByteBuffer getBufferReaderTCP(){
    	return bufferReaderTCP;}
    
    public ByteBuffer getBufferReaderUDP(){
    	return bufferReaderUDP;}
    
    public CharsetDecoder getDescodificador(){return descodificador;}
    
    public ClientPlayer getClientPlayer(){return cPlayer;}
    public void setClientPlayer(ClientPlayer c){cPlayer =c;}
    
    public void fuckChat(){
    	bufferReaderTCP.rewind();
    	char ch =bufferReaderTCP.getChar();
    	try{
    		//AQUI PODRIAMOS ACTUALIZAR MENSA O MENSARECIBIDO
    		mensaRecibido =getDescodificador().decode(bufferReaderTCP).toString();
    		
    		}catch (CharacterCodingException cce){System.out.println("Error en descodificador");}
    }
    
    
    
    public void fuckPackage(ByteBuffer b,LogicActor l){
		char tipo = b.getChar();
		b.rewind();
		if (tipo=='#'){
			fuckChat();
		}  		
		else {
				if (tipo =='*'){			
				l.fuckLogicActor(getBufferReaderTCP()); 
				}
				
		}
}
    
    public void processPackageSala(ByteBuffer b,RoomsGUI room){
		b.rewind();
		char ident = b.getChar();
		//System.out.println(ident);
		if (ident=='%'){
			
			int sala = b.getInt();
			int estate = b.getInt();
		//	System.out.println(estate);
			if (estate == 0){
             //	System.out.println("una");
				room.drawEmpty(sala);
				}
			else	 {
				int numPlayers = b.getInt(); 
				
				try {
				String jugadores = descodificador.decode(b).toString();
				room.drawRoom(sala,estate,numPlayers,jugadores);
				}catch (CharacterCodingException cce){System.out.println("Error en descodificador");}	
			}
			}
}
    
    public void sendPackageUDP (ByteBuffer bb){
    	InetSocketAddress ina=new InetSocketAddress(this.serverIP,9001);
    	try {
			canalUDP.send(bb,ina);
		} catch (IOException e) {
			System.out.println("IOException al enviar Paquete UDP");
			e.printStackTrace();
		}
    }
    
    public void sendPackageTCP (ByteBuffer bb){
    	WriteChannel(channel,bb);
    }

	/**
	 * @param roomGame
	 */
	public void sendConfirm(int roomGame) {
		bufferWriterTCP.clear();
		bufferWriterTCP.putChar(Constants.PACK_CONFIRM_PLAYER_START);		
		bufferWriterTCP.putInt(roomGame);
		bufferWriterTCP.flip();
		WriteChannel(channel,bufferWriterTCP);		
	}
    
  /*  public void processPackageCrearPartida(ByteBuffer b,CreateMatchGUI c){
    		char ident = b.getChar();
		if (ident=='/'){
			int sala = b.getInt();
			char confirm = b.getChar();
			if (confirm == 'o'){
					int selectedScene = b.getInt();
					int limite = b.getInt();
					c.dispose();
	              WaitingGUI waitingGUI = new WaitingGUI(sala, selectedScene,limite,this,descodificador);
	              waitingGUI.setVisible(true);
	              waitingGUI.listen();
			}
	             
			else	 {
				 if (confirm == 'c'){
				 	//Ventana de partida ya creada
				 	RoomsGUI roomsGUI = new RoomsGUI(this);
			        roomsGUI.setVisible(true);
			        c.dispose();
			        roomsGUI.listen();	
				 
				 }
					
			}
			}

}*/
    
 /*   public void processPackageSalaEspera(ByteBuffer b,WaitingGUI w){
		char ident = b.getChar();
		if (ident=='('){
			int sala = b.getInt();
			char estate = b.getChar();
			if (estate == 'o'){
				//Recibo un TCP con nombre y tipo de jugador de cada cliente
				int numPlayer = b.getInt();
				//Almaceno el nombre de todos los jugadores y su tipo en una lista
				jugadores.clear();
				try{
				String cadena = descodificador.decode(b).toString();
				
				separador = new StringTokenizer(cadena);
				for (int i=0;i<numPlayer;i++){
					
					//PlayerClient p= new PlayerClient(separador.nextToken(),separador.nextToken());
					//jugadores.add(p);
					
				}
				//Ahora los pinto en la GUI
				//escribirJugadores(jugadores);
				}catch (CharacterCodingException cce) {
					System.out.println("Error en descodificador");}
				
				//aqunh tiene que ir una ventana intermedia de espera
	        	
			}
	             
			else	 {
				 if (estate == 'c'){
				 	RoomsGUI roomsGUI = new RoomsGUI(this);
			        roomsGUI.setVisible(true);
			        roomsGUI.listen();	
				 
				 }
					
			}
			}
		
		//Recibo paquete de empezar partida
		else{ if (ident==')'){
			int sala = b.getInt();
			//Igual no hace falta.Sera 'o' de la confirmacion
			//char estate = b.getChar();
			//El estado de la partida:Sera siempre ocupada
			int estado =b.getInt();
			//El numero de jugadores
			int numPlayer = b.getInt();
			
			//Almaceno el nombre de todos los jugadores y su tipo en una lista
			jugadores.clear();
			try{
			String cadena = descodificador.decode(b).toString();			
			separador = new StringTokenizer(cadena);
			for (int i=0;i<numPlayer;i++){
				
				//PlayerClient p= new PlayerClient(separador.nextToken(),separador.nextToken());
				//jugadores.add(p);
				
			}
			//Ahora los pinto en la GUI
			//escribirJugadores(jugadores);
			}catch (CharacterCodingException cce) {
				System.out.println("Error en descodificador");}
			
			
			
			SplashGUI loading=new SplashGUI(w,Tools.loadBufferedImage("art"+File.separator+"loading.png"));
            //this.setVisible(false);
            loading.setVisible(true);
            
            Thread t1 = new Thread("main") {

                public void run() {
                    
               //     if (SelectedScene.equals("Eleuron")) {
                	if (escenario == 1){
                        Stage stage = new StageEleuron();
                       w.dispose();
                        beginning(stage);
                 //   } else if (SelectedScene.equals("Silicosis")) {
                    } else if (escenario == 2){
                        Stage stage = new StageSilicosis();
                        WaitingGUI.this.dispose();
                        beginning(stage);
                   // } else if (SelectedScene.equals("Nodrubaz")) {
                    } else if (escenario == 3){
                        Stage stage = new StageNodrubaz();
                        WaitingGUI.this.dispose();
                        beginning(stage);
                 //   } else if (SelectedScene.equals("OCB-5")) {
                    } else if (escenario == 4){
                        Stage stage = new StageOcb5();
                        WaitingGUI.this.dispose();
                        beginning(stage);
                  //  } else if (SelectedScene.equals("CampoAsteroides")) {
                    } else if (escenario == 5){
                        Stage stage = new StageTest();
                       // stage.setActorsArray(jugadores);
                        WaitingGUI.this.dispose();
                        beginning(stage);
                    }
                    
                    
                }
                
            };
            t.start();		
		} 
			
		
		}

}*/

	/**
	 * @return Returns the currentGame.
	 */
	public GameInterface getCurrentGame() {
		return currentGame;
	}
	/**
	 * @param currentGame The currentGame to set.
	 */
	public void setCurrentGame(GameInterface currentGame) {
		this.currentGame = currentGame;
	}
	/**
	 * @param bufferReaderUDP The bufferReaderUDP to set.
	 */
}//fin de clase
