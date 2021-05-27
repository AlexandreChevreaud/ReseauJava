package Echo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurEcho {
    final static int port = 8532;
    final static int taille = 1024;
    static byte[] buffer = new byte[taille];
    final static char CHAR_SEPARATEUR = 'µ';
    public static void main(String[] argv) throws Exception
    {
        DatagramSocket socket = new DatagramSocket(port);
        while(true)
        {
            DatagramPacket data = new DatagramPacket(buffer,buffer.length);
            socket.receive(data);

            String msg = new String(data.getData());
            String msgToSend = msg.split(""+CHAR_SEPARATEUR)[0];
            System.out.println("message :" + msgToSend);    // traitement + affichage

            msgToSend = msgToSend + CHAR_SEPARATEUR;        // rajout du char séparateur
            buffer = msgToSend.getBytes();
            data.setData(buffer);
            socket.send(data);
        }
    }
}