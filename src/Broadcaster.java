import java.net.InetSocketAddress;
import java.util.Date;
import java.net.*;

import java.io.IOException;
public class Broadcaster extends Thread{
    private static final int PORT = 4711;
    private static final int BUFSIZE = 512;

    public static void main(String[] args) {

        try(DatagramSocket socket = new DatagramSocket(PORT)) {
            InetAddress inetAddress= InetAddress.getByName("255.255.255.255"); //changed to Broadcast Address
            DatagramPacket outPacket = new DatagramPacket(new byte[BUFSIZE], BUFSIZE, inetAddress, 7777);

            // socket.setBroadcast(true);
            System.out.println("Server gestartet");

            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println(new Date().toString());
                outPacket.setData(java.time.LocalDate.now().toString().getBytes());
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