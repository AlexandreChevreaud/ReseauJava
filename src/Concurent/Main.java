package Concurent;

import java.net.SocketException;

public class Main {

    public static void main(String[] args) throws SocketException {
        ServeurConcurent s = new ServeurConcurent();
        s.start();
    }
}
