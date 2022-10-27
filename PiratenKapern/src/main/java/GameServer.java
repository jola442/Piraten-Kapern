import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class GameServer{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Server[] serverThreads = new Server[Config.NUM_OF_PLAYERS];
    private Player[] players = new Player[Config.NUM_OF_PLAYERS];
    private Game game;
    private int numPlayers;
    private boolean usedSorceress;
    private ServerSocket serverSocket;
    private int roundNum;

    public static void main(String args[]) throws Exception {
        GameServer gameServer = new GameServer();
        gameServer.acceptConnections();
        gameServer.gameLoop();
    }

    public GameServer() {
        numPlayers = 0;
        roundNum = 0;
        usedSorceress = false;
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

                String playerName = server.receiveString();
                System.out.println(playerName+ " has joined!");


                Player player = new Player(playerName);
                players[server.playerId - 1] = player;
                serverThreads[numPlayers - 1] = server;


            }


            System.out.println("Three players have joined the game");

            // start the server threads
            for (int i = 0; i < serverThreads.length; i++) {
                Thread t = new Thread(serverThreads[i]);
                t.start();
            }
            // start their threads
        } catch (IOException ex) {
            System.out.println("Could not connect 3 players");
        }
    }

    public void playRound(Server serverThread, Player player) {
        String msgToClient = "";
        String msgFromClient = "";
        usedSorceress = false;
        game.getInTreasureChest().clear();

        serverThread.sendScoreBoard();
        game.drawFortuneCard();
        //Player's initial roll
        game.rollDice();


        if(game.getNumSkullDice() > 3 ||
        (game.getFortuneCard() == Game.Card.ONE_SKULL && game.getNumSkullDice() > 2) ||
        (game.getFortuneCard() == Game.Card.TWO_SKULLS && game.getNumSkullDice() > 1)) {
            serverThread.sendDice();
            serverThread.sendString("Fortune Card " + game.getFortuneCard());
            playSkullIslandRound(serverThread, player);
            msgToClient = "Your turn has ended.\n" +
                    "Your score for this round is 0"
                    +"\nYour total score is " + player.getScore();
            serverThread.sendString(msgToClient);
            return;
        }


        while (true) {
            boolean isValidResponse = false;
            //Sending dice to player
            serverThread.sendDice();
            serverThread.sendString("Fortune Card: " + game.getFortuneCard());

            //If you have more 3 or more skulls
            if (game.getNumSkullDice() >= 3 || game.getFortuneCard() == Game.Card.ONE_SKULL && game.getNumSkullDice() >= 2 || game.getFortuneCard() == Game.Card.TWO_SKULLS && game.getNumSkullDice() >= 1) {

                //If you have exactly 3 skulls
                if(game.getNumSkullDice() == 3 || game.getFortuneCard() == Game.Card.ONE_SKULL && game.getNumSkullDice() == 2 || game.getFortuneCard() == Game.Card.TWO_SKULLS && game.getNumSkullDice() == 1){

                    //If you have an unused sorceress card
                    if(game.getFortuneCard() == Game.Card.SORCERESS && !usedSorceress){
                        msgFromClient = getValidYesNoAnswer(serverThread, "Would you like to reroll a skull?");

                        //If you wish to use your sorceress card
                        if(msgFromClient.equalsIgnoreCase("Yes")){
                            usedSorceress = true;
                            int skullIndex = game.getDice().indexOf(Game.Dice.SKULL);
                            ArrayList<Integer> diceToReroll = new ArrayList<>(Arrays.asList(skullIndex));
                            game.setDiceToReroll(diceToReroll);
                            game.rerollDice();
                            game.countDice();
                        }

                        else{
                            serverThread.sendString("You are dead. Your score for this turn is 0 and your turn is over");
                            return;
                        }
                    }

                    else{
                        serverThread.sendString("You are dead. Your score for this turn is 0 and your turn is over");
                        return;
                    }
                }

                //If you have more than 3 skulls
                else{
                    serverThread.sendString("You are dead. Your score for this turn is 0 and your turn is over");
                    return;
                }


            }

            else{
                //Ask if the player would like to place dice in the treasure chest
                if (game.getFortuneCard() == Game.Card.CHEST) {
                    msgFromClient = getValidYesNoAnswer(serverThread, "Would you like to place dice in the treasure chest?");
                    if(msgFromClient.equalsIgnoreCase("Yes")){
                        isValidResponse = false;
                        while (!isValidResponse) {
                            serverThread.sendString("What dice would you like to place in the treasure chest? Separate your choices with commas e.g 1,2,3");
                            msgFromClient = serverThread.receiveString();
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
                                            serverThread.sendString("You can't place a skull in your treasure chest");
                                            throw new Exception();
                                        } else {
                                            treasureChestDice.add(diceNum - 1);
                                        }
                                    }

                                }
                                isValidResponse = true;
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
                            while(!isValidResponse){
                                msgFromClient = getValidYesNoAnswer(serverThread,"Would you like to remove from treasure chest?");
                                if(msgFromClient.equalsIgnoreCase("Yes")){
                                    msgToClient = "What dice would you like to remove from the treasure chest? Separate your choices with commas e.g 1,2,3\n Your choices are: \n";
                                    for(int i = 0; i < game.getInTreasureChest().size(); i++){
                                        msgToClient += "Dice " + (game.getInTreasureChest().get(i) + 1) + "\n";
                                    }
                                    serverThread.sendString(msgToClient);
                                    msgFromClient = serverThread.receiveString();
                                    try{
                                        ArrayList<String> diceStringList = new ArrayList<>(Arrays.asList(msgFromClient.split(",")));
                                        for(int i = 0; i < diceStringList.size(); i++){
                                            int diceNum = Integer.parseInt(diceStringList.get(i));
                                            //If the value cannot be removed from the arrayList
                                            if(!game.getInTreasureChest().remove(Integer.valueOf(diceNum-1))){
                                                throw new Exception();
                                            }
                                        }
                                        isValidResponse = true;
                                    }

                                    catch (Exception e){
                                        isValidResponse = false;
                                    }

                                }
                            }

                        }

                    }

                }

                msgFromClient = getValidYesNoAnswer(serverThread, "Would you like to re-roll? Yes or No");
                if (msgFromClient.equalsIgnoreCase("No")) {
                    int roundScore = game.calculateScore();
                    updateTotalScore(player, roundScore);

                    msgToClient = "Your turn has ended.\n" +
                            "Your score for this round is " + roundScore
                            +"\nYour total score is " + player.getScore();
                    serverThread.sendString(msgToClient);
                    break;
                }

                else {
                    getValidDiceReroll(serverThread);
                }

            }
        }
    }

    public String getValidYesNoAnswer(Server playerServer, String question){
        boolean isValidResponse = false;
        String msgFromClient = "";
        while(!isValidResponse){
            playerServer.sendString(question);
            //Receive user input
            msgFromClient = playerServer.receiveString();
            if (msgFromClient.equalsIgnoreCase("Yes") || msgFromClient.equalsIgnoreCase("No")) {
                isValidResponse = true;
            }
        }
        return msgFromClient;
    }

    public ArrayList<Integer> getValidDiceReroll(Server playerServer){
        boolean isValidResponse = false;
        String msgFromClient = "";
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
                            if (game.getFortuneCard() == Game.Card.SORCERESS && !usedSorceress) {
                                usedSorceress = true;
                                diceToReroll.add(diceNum-1);
                            } else {
                                playerServer.sendString("You can't reroll a skull right now");
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
                    isValidResponse = false;
                    Game.Dice die = null;
                    //Get the die in the arrayList
                    if(!diceToReroll.isEmpty()) {
                        die = game.getDice().get(diceToReroll.get(0));
                        System.out.println(die);
                        System.out.println(die == Game.Dice.SKULL);
                        //Checking if the die is a skull and the fortune card is sorceress
                        //The sorceress card was used when adding the skull to the arrayList
                        if (die == Game.Dice.SKULL && game.getFortuneCard() == Game.Card.SORCERESS) {
                            isValidResponse = true;
                            System.out.println("set isvalid response to true");
                        }
                    }
                }

                if (!isValidResponse) {
                    System.out.println("wrong! isvalid response was not set to true");
                    playerServer.sendString("You must roll at least 2 valid dice");
                    throw new Exception();
                }

                game.setDiceToReroll(diceToReroll);
                game.rerollDice();
                game.countDice();
                return diceToReroll;
            } catch (Exception e) {
                //If a reason for the input failing hasn't been given
                if(isValidResponse){
                    playerServer.sendString("Invalid dice supplied");
                }
                isValidResponse = false;
            }
        }
        return null;
    }

    public void updateTotalScore(Player player, int roundScore){
        if(player.getScore() + roundScore < 0){
            player.setScore(0);
        }

        else{
            player.setScore(player.getScore() + roundScore);
        }
    }

    public void playSkullIslandRound(Server serverThread, Player player){
        String msgToClient = "-------------------------------------\n"
        +                    "You are now on the Island of Skulls\n"
        +                    "-------------------------------------";
        serverThread.sendString(msgToClient);

        String msgFromClient = "";
        boolean canReroll = true;
        while(true){
            if(game.getNumSkullDice() >= 7 && (game.getFortuneCard() != Game.Card.SORCERESS|| usedSorceress)){
                break;
            }

            if(!canReroll){
                break;
            }

            msgFromClient = getValidYesNoAnswer(serverThread, "Would you like to reroll? Yes or No");
            if(msgFromClient.equalsIgnoreCase("Yes")){
                ArrayList<Integer> rerolledDice = getValidDiceReroll(serverThread);
                canReroll = false;
                //If the dice can be rerolled under normal circumstances, check if the roll contains at least one skull
                if(rerolledDice != null){
                    for(int i = 0; i < rerolledDice.size(); i++){
                        if(game.getDice().get(i) == Game.Dice.SKULL){
                            canReroll = true;
                            serverThread.sendDice();
                        }
                    }

                }

            }

            else{
                break;
            }
        }

        int deduction = game.getSkullIslandDeduction();

        for(int i = 0; i < players.length; i++){
            if(players[i] == player){
                serverThreads[i].sendString("You have inflicted a deduction of " + -1*deduction + " on all other players");
            }

            else{
                serverThreads[i].sendString(player.getName() + " entered skull island and reduced your score by " + deduction);
                updateTotalScore(players[i],deduction);
                serverThreads[i].sendString("Your score is now " + players[i].getScore());
            }
        }

    }

    public void gameLoop(){
        while (true) {
            roundNum++;
            playRound(serverThreads[0], players[0]);
            playRound(serverThreads[1], players[1]);
            playRound(serverThreads[2], players[2]);
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

