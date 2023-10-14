import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    private int thisServerPort;

    /**
     * This constructor forces port to be passed in which is necessary for server startup.
     * @param iPort
     */
    public SocketServer(int iPort){

        thisServerPort = iPort;

    }

    /**
     * This thread listens for connecting clients, receives messages, and replies with sent message.
     */
    public void run(){
        try(ServerSocket oServerSocket = new ServerSocket(thisServerPort)){
            System.out.println("Server is listening on port " + thisServerPort);

            while(true){
                //Server will pause on this line waiting for client to connect to it.
                Socket oSocket = oServerSocket.accept();
                System.out.println("[server]: new client connected: " + oSocket.getRemoteSocketAddress());

                //Build a reader.
                InputStream input = oSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                //Build a writer.
                OutputStream output = oSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                String sReceivedMessage = reader.readLine();

                String[] array = sReceivedMessage.split(",");
                int a = Integer.parseInt(array[0]);
                int b = Integer.parseInt(array[1]);
                int c = Integer.parseInt(array[2]);

                int temp = a + b + c;

                System.out.println("[server]: Message received: " + temp);

                writer.println("Your message was received (" + temp + ")");
                writer.flush(); //Guarantees that message is sent immediately.






            }
        }
        catch(IOException ex){
            System.out.println("[server]: Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


}
