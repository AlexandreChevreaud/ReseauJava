package Concurent;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClientConcurent {

    final static int taille = 1024;
    static byte[] buffer;
    final static char CHAR_SEPARATEUR = 'µ';
    final static String MESSAGE_FIN_CLIENT = "Terminer";
    static int port;

    public static void main(String[] args) throws IOException {
        InetAddress serveur = InetAddress.getByName("127.0.0.1");
        DatagramSocket socket = new DatagramSocket();
        port = ServeurConcurent.PORT;
        boolean comEnCours = true;
        while (comEnCours) {

            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            message += CHAR_SEPARATEUR;     // création du message et ajout du char separateur


            buffer = message.getBytes();
            DatagramPacket dataSent = new DatagramPacket(buffer, buffer.length, serveur, port);
            socket.send(dataSent);          // Envoie du message

            DatagramPacket dataRecieved = new DatagramPacket(new byte[taille],taille);
            socket.receive(dataRecieved);   // reception du message


            String msg = new String(dataRecieved.getData());
            String msgRecu = msg.split(""+CHAR_SEPARATEUR)[0];
            System.out.println("message : " + msgRecu);       // traitement et affichage du message
            port = dataRecieved.getPort();
            if(ServeurConcurent.MESSAGE_FIN_SERVEUR.equals(msgRecu)){
                comEnCours = false;
            }

        }
    }
}
