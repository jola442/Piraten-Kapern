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
    protected Server[] serverThreads = new Server[Config.NUM_OF_PLAYERS];
    protected Player[] players = new Player[Config.NUM_OF_PLAYERS];
    protected Game game;
    private int numPlayers;
    protected boolean usedSorceress;
    private ServerSocket serverSocket;
    protected int roundNum;

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

    public Player[] getPlayers() {
        return players;
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
        game.getDiceInTreasureChest().clear();

        game.drawFortuneCard();
        //Player's initial roll
        game.rollDice();
        serverThread.sendScoreBoard();
        serverThread.sendString("Your turn has started\n");


        if(game.getNumSkullDice() > 3 ||
        (game.getFortuneCard() == Game.Card.ONE_SKULL && game.getNumSkullDice() > 2) ||
        (game.getFortuneCard() == Game.Card.TWO_SKULLS && game.getNumSkullDice() > 1)) {
            serverThread.sendDice();
            serverThread.sendString("Fortune Card " + game.getFortuneCard());
            playSkullIslandRound(serverThread, player);
            msgToClient = "Your turn has ended.\n" +
                    "Your score for this round is 0"
                    +"\nYour total score is " + player.getScore() + "\n";
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
                        }

                        else{
                            serverThread.sendString("You are dead. Your score for this turn is 0 and your turn is over\n");
                            return;
                        }
                    }

                    else{
                        serverThread.sendString("You are dead. Your score for this turn is 0 and your turn is over\n");
                        return;
                    }
                }

                //If you have more than 3 skulls
                else{
                    serverThread.sendString("You are dead. Your score for this turn is 0 and your turn is over\n");
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

                            if(game.getDiceInTreasureChest().isEmpty()){
                                treasureChestDice = new ArrayList<>();
                            }

                            else{
                                treasureChestDice = game.getDiceInTreasureChest();
                            }

//
                            try {
                                for (int i = 0; i < diceStringList.size(); i++) {
                                    int diceNum = Integer.parseInt(diceStringList.get(i));
                                    if (diceNum >= 1 && diceNum <= 8 && !game.getDiceInTreasureChest().contains(diceNum-1)) {
                                        if (game.getDice().get(diceNum - 1) == Game.Dice.SKULL) {
                                            serverThread.sendString("You can't place a skull in your treasure chest");
                                            throw new Exception();
                                        } else {
                                            treasureChestDice.add(diceNum - 1);
                                        }
                                    }

                                }
                                isValidResponse = true;
                                game.setDiceInTreasureChest(treasureChestDice);
                            }

                            catch (Exception e) {
                                isValidResponse = false;
                            }
                        }
                    }
                    else{
                        if(!game.getDiceInTreasureChest().isEmpty()){
                            isValidResponse = false;
                            while(!isValidResponse){
                                msgFromClient = getValidYesNoAnswer(serverThread,"Would you like to remove from treasure chest?");
                                if(msgFromClient.equalsIgnoreCase("Yes")){
                                    msgToClient = "What dice would you like to remove from the treasure chest? Separate your choices with commas e.g 1,2,3\n Your choices are: \n";
                                    for(int i = 0; i < game.getDiceInTreasureChest().size(); i++){
                                        msgToClient += "Dice " + (game.getDiceInTreasureChest().get(i) + 1) + "\n";
                                    }
                                    serverThread.sendString(msgToClient);
                                    msgFromClient = serverThread.receiveString();
                                    try{
                                        ArrayList<String> diceStringList = new ArrayList<>(Arrays.asList(msgFromClient.split(",")));
                                        for(int i = 0; i < diceStringList.size(); i++){
                                            int diceNum = Integer.parseInt(diceStringList.get(i));
                                            //If the value cannot be removed from the arrayList
                                            if(!game.getDiceInTreasureChest().remove(Integer.valueOf(diceNum-1))){
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
                            +"\nYour total score is " + player.getScore() + "\n";
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
                int numSkullDiceToReroll = 0;
                for (int i = 0; i < diceStringList.size(); i++) {
                    int diceNum = Integer.parseInt(diceStringList.get(i));
                    if (diceNum >= 1 && diceNum <= 8) {
                        if (game.getDice().get(diceNum - 1) == Game.Dice.SKULL) {
                            numSkullDiceToReroll++;
                            if(numSkullDiceToReroll > 1){
                                playerServer.sendString("You can't reroll more than 1 skull even if you have a sorceress card");
                                usedSorceress = false;
                                isValidResponse = false;
                                throw new Exception();
                            }
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
                serverThread.sendDice();
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
                updateTotalScore(players[i],deduction);
                serverThreads[i].sendString(player.getName() + " entered skull island and reduced your score by " + (-1*deduction) + "\nYour score is now " + players[i].getScore() + "\n");

            }
        }

    }

    public void gameLoop() {
        Player player1 = players[0];
        Player player2 = players[1];
        Player player3 = players[2];
        Server player1Server = serverThreads[0];
        Server player2Server = serverThreads[1];
        Server player3Server = serverThreads[2];
        while (true) {

            while (game.getWinners(players) == null) {
                roundNum++;
                playRound(player1Server, player1);
                playRound(player2Server, player2);
                playRound(player3Server, player3);
            }

            ArrayList<String> winners = game.getWinners(players);
            //One player wins
            if (winners.size() == 1) {
                //If player 3 wins, player 1 and player 2 play one more time
                if (winners.get(0).equalsIgnoreCase(player3.getName())) {
                    playRound(player1Server, player1);
                    playRound(player2Server, player2);
                }

                //If player 2 wins, player 1 plays one more time (player 3 already played after player 2)
                else if (winners.get(0).equalsIgnoreCase(player2.getName())) {
                    playRound(player1Server, player1);
                }

                //If player 1 wins, everyone else has already played


            } else if (winners.size() == 2) {
                //if the second winner is player 3
                if(winners.get(1).equalsIgnoreCase(player3.getName())){

                    //If the first winner is player 2, player 1 plays one more time
                    if(winners.get(0).equalsIgnoreCase(player2.getName())){
                        playRound(player1Server, player1);
                    }

                    //If the first winner is player 1, player 2 plays one more time
                    if(winners.get(0).equalsIgnoreCase(player1.getName())){
                        playRound(player2Server, player2);
                    }
                }
            }

            winners = game.getWinners(players);
            if(winners != null){
                announceWinners(winners);
                return;
            }

        }
    }

    public void announceWinners(ArrayList<String> winners){
        if(winners.isEmpty()) {
            return;
        }

        Player player1 = players[0];
        Player player2 = players[1];
        Player player3 = players[2];
        Server player1Server = serverThreads[0];
        Server player2Server = serverThreads[1];
        Server player3Server = serverThreads[2];

        if(winners.size() == 1){
            if(winners.get(0).equalsIgnoreCase(player1.getName())){
                player1Server.sendString("You have won");
                player2Server.sendString(player1.getName() + " has won");
                player3Server.sendString(player1.getName() + " has won");
            }

            else if(winners.get(0).equalsIgnoreCase(player2.getName())){
                player2Server.sendString("You have won" );
                player1Server.sendString(player2.getName() + " has won");
                player3Server.sendString(player2.getName() + " has won");
            }

            else{
                player3Server.sendString("You have won");
                player2Server.sendString(player3.getName() + " has won" );
                player1Server.sendString(player3.getName() + " has won" );
            }
        }

        else if(winners.size() == 2){
            //If player 1 and player 2 win
            if(winners.get(0).equalsIgnoreCase(player1.getName()) && winners.get(1).equalsIgnoreCase(player2.getName())){
                player1Server.sendString("You tied for the win with " + player2.getName() );
                player2Server.sendString("You tied for the win with " + player1.getName() );
                player3Server.sendString(player1.getName() + " and " + player2.getName() + " tied for the win" );
            }

            //If player 1 and player 3 win
            else if(winners.get(0).equalsIgnoreCase(player1.getName()) && winners.get(1).equalsIgnoreCase(player3.getName())){
                player1Server.sendString( "You tied for the win with " + player3.getName() );
                player3Server.sendString( "You tied for the win with " + player1.getName() );
                player2Server.sendString( player1.getName() + " and " + player3.getName() + " tied for the win" );
            }

            //If player 2 and player 3 win
            else{
                player2Server.sendString( "You tied for the win with " + player3.getName() );
                player3Server.sendString( "You tied for the win with " + player2.getName() );
                player1Server.sendString( player2.getName() + " and " + player3.getName() + " tied for the win" );
            }
        }

        else{
            player1Server.sendString( "Three way tie for the win" );
            player2Server.sendString( "Three way tie for the win" );
            player3Server.sendString( "Three way tie for the win" );
        }

    }





    public class Server implements Runnable {
        private Socket socket;
        private ObjectInputStream inputFromClient;
        private ObjectOutputStream outputToClient;
        protected int playerId;

        public Server(){}

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

