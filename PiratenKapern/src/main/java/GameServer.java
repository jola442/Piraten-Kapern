import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Server[] playerServer = new Server[3];
    Player[] players = new Player[3];

    int numPlayers;

    ServerSocket serverSocket;

    public static void main(String args[]) throws Exception {
        GameServer gameServer = new GameServer();
        gameServer.acceptConnections();
        System.out.println("Testing");
    }

    public GameServer() {
        numPlayers = 0;
        System.out.println("Starting game server");
        // initialize the players list with new players
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }

        try {
            serverSocket = new ServerSocket(Config.PORT);
        } catch (IOException ex) {
            System.out.println("Server Failed to open");
        }

    }

    /*
     * -----------Networking stuff ----------
     *
     */
    public void acceptConnections() throws ClassNotFoundException {
        try {
            System.out.println("Waiting for players...");
            while (numPlayers < 3) {
                Socket clientSocket = serverSocket.accept();
                numPlayers++;

                Server server = new Server(clientSocket, numPlayers);
                // send the player number
                server.outputToClient.writeInt(server.playerId);
                server.outputToClient.flush();

                String msg = server.inputFromClient.readUTF();
                System.out.println("Got this message from the client: " + msg);

                Player player = new Player();
                players[server.playerId - 1] = player;
                playerServer[numPlayers - 1] = server;


//                String message = "";
//
//                while(!"stop".equalsIgnoreCase(message)){
//                    message = server.inputFromClient.readUTF();
//                    System.out.println("The client sent this message: " + message);
//                    if(message == null){
//                        continue;
//                    }
//
//                    else{
//                        server.outputToClient.writeUTF("You sent this: "+ message);
//                        server.outputToClient.flush();
//                    }
//                }
            }
            System.out.println("Three players have joined the game");

            // start the server threads
            for (int i = 0; i < playerServer.length; i++) {
                Thread t = new Thread(playerServer[i]);
                t.start();
            }
            // start their threads
        } catch (IOException ex) {
            System.out.println("Could not connect 3 players");
        }
    }



    public class Server implements Runnable {
        private Socket socket;
        private ObjectInputStream inputFromClient;
        private ObjectOutputStream outputToClient;
        private int playerId;

        public Server(Socket s, int playerId) {
            socket = s;
            this.playerId = playerId;
            try {
                outputToClient = new ObjectOutputStream(socket.getOutputStream());
                inputFromClient = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                System.out.println("Server Connection failed");
            }
        }

        /*
         * run function for threads --> main body of the thread will start here
         */
        public void run() {
            try {
                while (true) {
                }

            } catch (Exception ex) {
                {
                    System.out.println("Run failed");
                    ex.printStackTrace();
                }
            }
        }

    }

}

