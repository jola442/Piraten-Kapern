import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class GameServer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Server[] playerServer = new Server[Config.NUM_OF_PLAYERS];
    Player[] players = new Player[Config.NUM_OF_PLAYERS];
    Game game;
    int numPlayers;

    ServerSocket serverSocket;
    private int roundNum;

    public static void main(String args[]) throws Exception {
        GameServer gameServer = new GameServer();
        gameServer.acceptConnections();
        gameServer.gameLoop();
    }

    public GameServer() {
        numPlayers = 0;
        roundNum = 0;
        game = new Game();
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

                String playerName = server.inputFromClient.readUTF();
                System.out.println(playerName+ " has joined!");


                Player player = new Player(playerName);
                players[server.playerId - 1] = player;
                playerServer[numPlayers - 1] = server;


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

    public void playRound(Server playerServer, Player player) {
        String msgToClient = "";
        String msgFromClient = "";

        playerServer.sendScoreBoard();
        game.drawFortuneCard();
        playerServer.sendString("Your fortune card for this turn is " + game.getFortuneCard());
        //Player's initial roll
        game.rollDice();


        while (true) {
            boolean isValidResponse = false;
            //Sending dice to player
            playerServer.sendDice();
            playerServer.sendString("Fortune Card: " + game.getFortuneCard());

            if (game.getNumSkullDice() == 3 ||
                (game.getFortuneCard() == Game.Card.ONE_SKULL && game.getNumSkullDice() == 2) ||
                    (game.getFortuneCard() == Game.Card.TWO_SKULLS && game.getNumSkullDice() == 1)) {
                playerServer.sendString("You are dead. Your score for this turn is 0 and your turn is over");
                break;

            } else if(game.getNumSkullDice() > 3 ||
                    (game.getFortuneCard() == Game.Card.ONE_SKULL && game.getNumSkullDice() > 2) ||
                    (game.getFortuneCard() == Game.Card.TWO_SKULLS && game.getNumSkullDice() > 1)) {
                playerServer.sendString("You are now on skull island, which hasn't been implemented yet.");
                break;
            }

            else{
                //Ask if the player would like to place dice in the treasure chest
                if (game.getFortuneCard() == Game.Card.CHEST) {
                    while (!isValidResponse) {
                        msgToClient = "Would you like to place dice in the treasure chest?";
                        playerServer.sendString(msgToClient);

                        msgFromClient = playerServer.receiveString();
                        if (msgFromClient.equalsIgnoreCase("Yes") || msgFromClient.equalsIgnoreCase("No")) {
                            isValidResponse = true;
                        }
                    }


                    if(msgFromClient.equalsIgnoreCase("Yes")){
                        isValidResponse = false;
                        while (!isValidResponse) {
                            playerServer.sendString("What dice would you like to place in the treasure chest? Separate your choices with commas e.g 1,2,3");
                            msgFromClient = playerServer.receiveString();
                            ArrayList<String> diceStringList = new ArrayList<>(Arrays.asList(msgFromClient.split(",")));
                            ArrayList<Integer> treasureChestDice;

                            if(game.getInTreasureChest().isEmpty()){
                                treasureChestDice = new ArrayList<>();
                            }

                            else{
                                treasureChestDice = game.getInTreasureChest();
                            }

//
                            try {
                                for (int i = 0; i < diceStringList.size(); i++) {
                                    int diceNum = Integer.parseInt(diceStringList.get(i));
                                    if (diceNum >= 1 && diceNum <= 8 && !game.getInTreasureChest().contains(diceNum-1)) {
                                        if (game.getDice().get(diceNum - 1) == Game.Dice.SKULL) {
                                            playerServer.sendString("You can't place a skull in your treasure chest");
                                            throw new Exception();
                                        } else {
                                            treasureChestDice.add(diceNum - 1);
                                        }
                                    }

                                }
                                isValidResponse = true;
                                System.out.println(treasureChestDice);
                                game.setInTreasureChest(treasureChestDice);
                            }

                            catch (Exception e) {
                                isValidResponse = false;
                            }
                        }
                    }
                    else{
                        if(!game.getInTreasureChest().isEmpty()){
                            isValidResponse = false;
                            while (!isValidResponse) {
                                msgToClient = "Would you like to remove from treasure chest?";
                                playerServer.sendString(msgToClient);

                                msgFromClient = playerServer.receiveString();
                                if (msgFromClient.equalsIgnoreCase("Yes") || msgFromClient.equalsIgnoreCase("No")) {
                                    isValidResponse = true;
                                }
                            }
                        }


                    }

                }

                isValidResponse = false;
                while(!isValidResponse){
                    msgToClient = "Would you like to re-roll? Yes or No";
                    playerServer.sendString(msgToClient);
                    //Receive user input
                    msgFromClient = playerServer.receiveString();
                    if (msgFromClient.equalsIgnoreCase("Yes") || msgFromClient.equalsIgnoreCase("No")) {
                        isValidResponse = true;
                    }
                }
                if (msgFromClient.equalsIgnoreCase("No")) {
                    int roundScore = game.calculateScore();
                    if(player.getScore() + roundScore < 0){
                        player.setScore(0);
                    }

                    else{
                        player.setScore(player.getScore() + roundScore);
                    }

                    msgToClient = "Your turn has ended.\n" +
                            "Your score for this round is " + roundScore
                            +"\nYour total score is " + player.getScore();
                    playerServer.sendString(msgToClient);
                    game.getInTreasureChest().clear();
                    break;
                }

                else {
                    isValidResponse = false;
                    while (!isValidResponse) {
                        playerServer.sendString("What dice would you like to reroll? Separate your choices with commas e.g 1,2,3");
                        msgFromClient = playerServer.receiveString();
                        ArrayList<String> diceStringList = new ArrayList<>(Arrays.asList(msgFromClient.split(",")));
                        ArrayList<Integer> diceToReroll = new ArrayList<>();

//
                        try {
                            isValidResponse = true;
                            for (int i = 0; i < diceStringList.size(); i++) {
                                int diceNum = Integer.parseInt(diceStringList.get(i));
                                if (diceNum >= 1 && diceNum <= 8) {
                                    if (game.getDice().get(diceNum - 1) == Game.Dice.SKULL) {
                                        if (game.getFortuneCard() == Game.Card.SORCERESS) {
                                            playerServer.sendString("This feature has not been implemented yet");
                                            //throw exception for now
                                            throw new Exception();
                                        } else {
                                            playerServer.sendString("You can't reroll a skull. You don't have a sorceress card");
                                            isValidResponse = false;
                                            throw new Exception();
                                        }
                                    }
                                    else {
                                        diceToReroll.add(diceNum - 1);
                                    }


                                }
                                else {
                                    playerServer.sendString("Dice numbers range from 1 to 8. Please supply valid dice numbers");
                                    isValidResponse = false;
                                    throw new Exception();
                                }

                            }

                            if (diceToReroll.size() < 2) {
                                playerServer.sendString("You must roll at least 2 valid dice");
                                isValidResponse = false;
                                throw new Exception();
                            }
                            game.setDiceToReroll(diceToReroll);
                            game.rerollDice();
                            game.countDice();
                        } catch (Exception e) {
                            //If a reason for the input failing hasn't been given
                            if(isValidResponse){
                                playerServer.sendString("Invalid dice supplied");
                            }
                            isValidResponse = false;

                        }
                    }

                }

            }
        }
    }

    public void gameLoop(){
        while (true) {
            roundNum++;
            playRound(playerServer[0], players[0]);
            playRound(playerServer[1], players[1]);
            playRound(playerServer[2], players[2]);
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

        public void sendString(String msg){
            try{
                outputToClient.writeUTF(msg);
                outputToClient.flush();
            }

            catch (IOException e){
                e.printStackTrace();
            }

        }

        public void sendDice(){
            String msgToClient = "Rolling dice....\n";
            for(int i = 0; i < Config.NUM_OF_DICE; i++){
                msgToClient += "Dice " + (i+1) + ": " + game.getDice().get(i) + "\n";
            }
            sendString(msgToClient);
        }

        public void sendScoreBoard(){
            String msgToClient = "--------------------------------------------\n"+
                                 "                ROUND "+ roundNum + "\n"+
                                 "--------------------------------------------\n";
            for(int i = 0; i < Config.NUM_OF_PLAYERS; ++i){
                msgToClient += players[i].getName() + "'s score: " + players[i].getScore() + "\n";
            }
            msgToClient       += "--------------------------------------------\n";

            sendString(msgToClient);
        }

        public String receiveString(){
            try{
                return inputFromClient.readUTF();
            }

            catch (IOException e){
                e.printStackTrace();
            }
            return "";
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

