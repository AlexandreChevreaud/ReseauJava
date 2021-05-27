import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.HashMap;

public class Tool{


    public static boolean testPort(int port){

        try {
            DatagramSocket socket = new DatagramSocket(port);
            socket.connect(new InetSocketAddress("127.0.0.1",port));
            socket.close();
        } catch (SocketException e) {
            return false;
        }
        return true;
    }

    public static HashMap<Integer,Boolean> testPort(int portD, int portF) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i = portD; i <= portF; i++) {
            map.put(i, testPort(i));
        }
        return map;
    }
}
