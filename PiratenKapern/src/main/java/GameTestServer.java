import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameTestServer extends GameServer{
    private int testNumber;
    private FileWriter fileWriter;

    public GameTestServer(int testNum){
        testNumber = testNum;
        try{
            String filePath = "AcceptanceTest3Part" + testNumber + ".txt";
            //overwrite the file if it exists
            fileWriter = new FileWriter(filePath, false);

            //set the file to append mode
            fileWriter = new FileWriter(filePath, true);
        }

        catch (IOException e){
            System.err.println("IOException: " + e.getMessage());
        }
    }

    @Override
    public void acceptConnections(){
        try{
            for(int i = 0; i < players.length; i++){
                int id = i+1;
                Player player = new Player("Player " + id);
                TestServer testServer = new TestServer(id);
                players[i] = player;
                serverThreads[i] = testServer;
                fileWriter.write(player.getName() + " has joined!\n");
                fileWriter.flush();
            }
        }

        catch (IOException e){
            System.err.println("IOException: " + e.getMessage());
        }

    }

    @Override
    public void playRound(Server serverThread, Player player) {
        if(testNumber == 4 && serverThread.playerId == 3){
            return;
        }
        super.playRound(serverThread, player);
    }

    @Override
    public String getValidYesNoAnswer(Server playerServer, String question) {
        playerServer.sendString(question);
        String riggedYesNoAnswer = "No";
        //Test number 4 is the only one where the user opts to reroll
        if(testNumber == 4){
            if(playerServer.playerId == 2){
                riggedYesNoAnswer = "Yes";
            }

        }


        playerServer.sendString(riggedYesNoAnswer+"\n");
        return riggedYesNoAnswer;

    }

    @Override
    public ArrayList<Integer> getValidDiceReroll(Server playerServer) {
        ArrayList<Integer> returnVal = super.getValidDiceReroll(playerServer);
        if(playerServer.playerId == 2){
            if(returnVal.containsAll(Arrays.asList(6,7))){
                game.getDice().set(6, Game.Dice.SKULL);
                game.getDice().set(7, Game.Dice.SKULL);
            }

            else{
                game.getDice().set(6, Game.Dice.PARROT);
            }
            game.countDice();
        }
        return returnVal;
    }

    public class TestServer extends Server{
        public TestServer(int playerId){
            this.playerId = playerId;
        }

        @Override
        public void sendScoreBoard() {
            ArrayList<Game.Dice> dice = new ArrayList<Game.Dice>();
            //player1 has a playerId of 1
            //player2 has a playerId of 2
            //player3 has a playerId of 3
            if(testNumber == 1){
                if(playerId == 1){
                    dice = new ArrayList<>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL));
                    game.setFortuneCard(Game.Card.CAPTAIN);
                }

                else if(playerId == 2){
                    dice = new ArrayList<>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL));
                    game.setFortuneCard(Game.Card.ONE_SKULL);
                }

                else{
                    dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
                    game.setFortuneCard(Game.Card.COIN);
                }
            }

            else if(testNumber == 2){
                if(playerId == 1){
                    if(roundNum == 1){
                        dice = new ArrayList<>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL));
                        game.setFortuneCard(Game.Card.CAPTAIN);
                    }

                    else{
                        dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT));
                        game.setFortuneCard(Game.Card.COIN);
                    }

                }

                else if(playerId == 2){
                    if(roundNum == 1){
                        dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
                        game.setFortuneCard(Game.Card.COIN);
                    }

                    else{
                        dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
                        game.setFortuneCard(Game.Card.CAPTAIN);
                    }

                }

                else{
                    if(roundNum == 1){
                        dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));

                        game.setFortuneCard(Game.Card.CAPTAIN);
                    }

                    else{
                        dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
                        game.setFortuneCard(Game.Card.ONE_SKULL);
                    }

                }
            }

            else if(testNumber == 3){
                if(playerId == 1){
                    if(roundNum == 1){
                        //If player 3 has played
                        if(players[1].getScore() == 4000){
                            dice = new ArrayList<>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
                        }
                        else{
                            dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
                        }

                    }

                    game.setFortuneCard(Game.Card.CAPTAIN);
                }

                else if(playerId == 2){
                    dice = new ArrayList<>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL));
                    game.setFortuneCard(Game.Card.CAPTAIN);
                }

                else{
                    dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
                    game.setFortuneCard(Game.Card.TWO_SKULLS);
                }
            }

            else{
                if(playerId == 1){
                    dice = new ArrayList<>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.SKULL));
                    game.setFortuneCard(Game.Card.COIN);
                }

                else if(playerId == 2){
                    dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.COIN));
                    game.setFortuneCard(Game.Card.SORCERESS);
                }

            }

            game.setDice(dice);
            game.countDice();


            String msgToClient = "--------------------------------------------\n"
                                +"      Round " + roundNum + ": Player " + playerId + "'s Turn\n"
                                +"--------------------------------------------\n";

            for(int i = 0; i < Config.NUM_OF_PLAYERS; ++i){
                msgToClient += players[i].getName() + "'s score: " + players[i].getScore() + "\n";
            }
            msgToClient       += "--------------------------------------------\n\n";

            sendString(msgToClient);

        }


        @Override
        public void sendDice() {
            if(testNumber == 4 && playerId == 3){
                System.out.println("Dice:" + game.getDice());
            }
            String toWrite = "Rolling dice....\n";
            for(int i = 0; i < Config.NUM_OF_DICE; i++){
                toWrite += "Dice " + (i+1) + ": " + game.getDice().get(i) + "\n";
            }
            sendString(toWrite);
        }

        @Override
        public String receiveString() {
            String riggedDiceReroll = "";

            if(testNumber == 4){
                if(playerId == 2){
                    if(!usedSorceress){
                        riggedDiceReroll = "7";
                    }

                    else{
                        riggedDiceReroll = "7,8";
                    }
                    game.countDice();
                    sendString(riggedDiceReroll + "\n");
                }


            }
            return riggedDiceReroll;
        }

        @Override
        public void sendString(String msg) {
            try{
                if(msg.contains("reduced") || msg.contains("deduction")){
                    String newMsg = "What player " + playerId + " sees:\n" + msg;
                    msg = newMsg;
                }

                else if(msg.contains("won")){
                    String newMsg = "What player " + playerId + " sees: \n" + msg;
                    msg = newMsg;
                }
                fileWriter.write(msg+"\n");
                fileWriter.flush();
            }

            catch (IOException e){
                System.err.println("IOException: " + e.getMessage());
            };
        }


    }
}
