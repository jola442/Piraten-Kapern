import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;


public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    int playerId = 0;

    static Client clientConnection;


    /*
     * ----------Network Stuff------------
     */
    public void sendStringToServer(String str) {
        clientConnection.sendString(str);
    }

    public void connectToClient() {
        clientConnection = new Client();
    }

    public void connectToClient(int port) {
        clientConnection = new Client(port);
    }

    public Player getPlayer() {
        return this;
    }


    private class Client {
        Socket socket;
        private ObjectInputStream inputFromServer;
        private ObjectOutputStream outputToServer;

        public Client() {
            try {
                socket = new Socket("localhost", Config.PORT);
                outputToServer = new ObjectOutputStream(socket.getOutputStream());
                inputFromServer = new ObjectInputStream(socket.getInputStream());

                playerId = inputFromServer.readInt();

                System.out.println("Connected as " + playerId);
//                Scanner in = new Scanner(System.in);
//                System.out.println("Send a message to the server");
//                String msgToServer = "";

//                while (!"stop".equalsIgnoreCase(msgToServer)) {
//                    msgToServer = in.nextLine();
//                    System.out.println("Sending this to the server: " + msgToServer);
//                    outputToServer.writeUTF(msgToServer);
//                    outputToServer.flush();
//                    String responseFromServer = inputFromServer.readUTF();
//                    System.out.println("Server's response: " + responseFromServer);
//                }

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }

        public Client(int portId) {
            try {
                socket = new Socket("localhost", portId);
                outputToServer = new ObjectOutputStream(socket.getOutputStream());
                inputFromServer = new ObjectInputStream(socket.getInputStream());

                playerId = inputFromServer.readInt();

                System.out.println("Connected as " + playerId);
//                Scanner in = new Scanner(System.in);
//                System.out.println("Send a message to the server");
//                String msgToServer = "";

//                while (!"stop".equalsIgnoreCase(msgToServer)) {
//                    msgToServer = in.nextLine();
//                    System.out.println("Sending this to the server: " + msgToServer);
//                    outputToServer.writeUTF(msgToServer);
//                    outputToServer.flush();
//                    String responseFromServer = inputFromServer.readUTF();
//                    System.out.println("Server's response: " + responseFromServer);
//                }

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }


        /*
         * function to send strings
         */
        public void sendString(String str) {
            try {
                outputToServer.writeUTF(str);
                outputToServer.flush();
            } catch (IOException ex) {
                System.out.println("String not sent");
                ex.printStackTrace();
            }
        }
    }


        /*
         * ---------Constructor and Main class-----------
         */
        public static void main(String args[]) {
            Scanner in = new Scanner(System.in);
            System.out.println("Type a message to the server");
            String msg = in.nextLine();
            Player p = new Player();
            p.connectToClient();
            clientConnection.sendString(msg);
        }

}
