import java.util.Scanner;

public class SocketManager {
    public static void main(String[] args) {
        System.out.print("Enter port for this server to listen on: ");
        int iThisServerPort = new Scanner(System.in).nextInt();

        System.out.print("Enter IP address of server to connect to: ");
        String sOtherServerIP = new Scanner(System.in).nextLine();
        System.out.print("Enter port of server to connect to: ");
        int iOtherServerPort = new Scanner(System.in).nextInt();

        SocketServer oServer = new SocketServer(iThisServerPort);
        Thread oServerThread = new Thread(oServer);
        oServerThread.start();

        try{Thread.sleep(1000);}catch(Exception ex){}

        while(true){
            System.out.print("Enter a message that contains three numbers, separated by commas only: ");
            String sMessage = new Scanner(System.in).nextLine();
            SocketClient oClient = new SocketClient();
            String sReply = oClient.connectForOneMessage(sOtherServerIP, iOtherServerPort, sMessage);
            System.out.println("[client]: reply from server: " + sReply);
        }
    }
}
