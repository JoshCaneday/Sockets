import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    /**
     * Allows a one time socket call to a server, sends message, and returns reply.
     * @param sIP
     * @param iPort
     * @param sMessage
     * @return
     */
    public String connectForOneMessage(String sIP, int iPort, String sMessage){

        try(Socket oSocket = new Socket()){

            //Attempt to connect to server at given IP address and port.
            oSocket.connect(new InetSocketAddress(sIP, iPort), 5000);

            //Build a writer.
            OutputStream output = oSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            //Send message to server.
            writer.println(sMessage);
            writer.flush(); // Guarantee that message is sent immediately.

            //Build a reader.
            InputStream input = oSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String sReply = reader.readLine();

            return sReply;
        }
        catch(Exception ex){
            System.out.println("[client]: client exception: " + ex.getMessage());
            // ex.printStackTrace();
            return null;
        }
    }
}
