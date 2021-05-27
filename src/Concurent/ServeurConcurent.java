package Concurent;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServeurConcurent extends Thread{

    static int PORT = 8533;
    final static String MESSAGE_FIN_SERVEUR = "OK terminer";
    final static String MESSAGE_SERVEUR = "OK";
    final static String MESSAGE_CONNEXION = "Connexion";
    final static String MESSAGE_CONNEXION_REFUSE = "Connexion refusé";
    private DatagramSocket socket;
    final static int taille = 1024;
    static byte[] buffer = new byte[taille];
    final static char CHAR_SEPARATEUR = 'µ';

    public ServeurConcurent() throws SocketException {
        socket = new DatagramSocket(PORT);
    }

    @Override
    public void run() {
        while (true){
            DatagramPacket data = new DatagramPacket(buffer,buffer.length);
            try {
                socket.receive(data);
                String msg = new String(data.getData());
                String msgRecu = msg.split(""+CHAR_SEPARATEUR)[0];
                System.out.println("message :" + msgRecu);

                if(MESSAGE_CONNEXION.equals(msgRecu)){
                    Communication com = new Communication(data);
                    com.start();
                }
                else {
                    buffer = (MESSAGE_CONNEXION_REFUSE + CHAR_SEPARATEUR).getBytes();
                    data.setData(buffer);
                    socket.send(data);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
