import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Recevier {

    private static final int PORT = 7777;
    private static final int BUFSIZE = 512;

    public static void main(String[] args) {

        try(DatagramSocket socket = new DatagramSocket(PORT)){
            DatagramPacket inPacket = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
            System.out.println("Server gestartet");

            while(true){
                socket.receive(inPacket);
                System.out.println("Packet empfangen...");
                System.out.println(
                        "Received: " + inPacket.getLength() + " bytes: " + new String(inPacket.getData(), 0, inPacket.getLength()));

            }
        } catch (IOException e) {
            System.err.println(e);
        }
        //empty DataGram
        //send off timestamp
    }
}
