import java.net.InetSocketAddress;
import java.util.Date;
import java.net.*;

import java.io.IOException;
public class Broadcaster extends Thread{
    private static final int PORT = 7777;
    private static final int BUFSIZE = 512;

    public static void main(String[] args) {

        try(DatagramSocket socket = new DatagramSocket()) {
            InetAddress inetAddress= InetAddress.getByName("192.168.0.255"); //eigene Broadcast Addresse zur generischen geaendert
            DatagramPacket outPacket = new DatagramPacket(new byte[BUFSIZE], BUFSIZE, inetAddress, PORT);

            System.out.println("Server gestartet");

            while (true) {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                String date = new Date().toString();
                System.out.println(date);
                outPacket.setData(date.getBytes());
                socket.send(outPacket);
                System.out.println(
                        "Gesendet: " + outPacket.getLength() + " bytes: " + new String(outPacket.getData()) + " an: "
                                + outPacket.getSocketAddress().toString());

            }
        }catch (IOException e) {
            System.err.println(e);
        }

        }
    }
