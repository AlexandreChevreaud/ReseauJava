package Echo;

import java.net.*;
import java.util.Scanner;

public class ClientEcho
{
    final static int taille = 1024;
    static byte[] buffer;
    final static char CHAR_SEPARATEUR = 'µ';

    public static void main(String argv[]) throws Exception
    {
        InetAddress serveur = InetAddress.getByName("172.20.10.3");
        Scanner scanner = new Scanner( System.in );
        String message = scanner.nextLine();
        message += CHAR_SEPARATEUR;     // création du message et ajout du char separateur


        buffer = message.getBytes();
        DatagramPacket dataSent = new DatagramPacket(buffer,buffer.length,serveur,ServeurEcho.port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(dataSent);          // Envoie du message

        DatagramPacket dataRecieved = new DatagramPacket(new byte[taille],taille);
        socket.receive(dataRecieved);   // reception du message


        String msg = new String(dataRecieved.getData());
        String msgRecu = msg.split(""+CHAR_SEPARATEUR)[0];
        System.out.println("message : " + msgRecu);       // traitement et affichage du message
    }
}
