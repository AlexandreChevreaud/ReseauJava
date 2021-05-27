package Concurent;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Communication extends Thread implements Runnable {

    private final DatagramPacket data;
    final static char CHAR_SEPARATEUR = 'µ';
    static byte[] buffer = new byte[ServeurConcurent.taille];
    private final DatagramSocket socket;

    public Communication(DatagramPacket data) throws SocketException {
        this.data = data;
        socket = new DatagramSocket();
    }

    @Override
    public void run() {
        boolean comEnCours = true;
        while (comEnCours){
            String msg = new String(data.getData());
            String msgRecu = msg.split(""+CHAR_SEPARATEUR)[0];
            System.out.println("message :" + msgRecu);

            if(ClientConcurent.MESSAGE_FIN_CLIENT.equals(msgRecu)){
                buffer = (ServeurConcurent.MESSAGE_FIN_SERVEUR + CHAR_SEPARATEUR).getBytes();
                data.setData(buffer);
                comEnCours = false;
                try {
                    socket.send(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                byte[] bufferMsg = (ServeurConcurent.MESSAGE_SERVEUR + CHAR_SEPARATEUR).getBytes();
                data.setData(bufferMsg);
                try {
                    socket.send(data);
                    data.setData(buffer);
                    socket.receive(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
