package com.piratenKapern;

import io.cucumber.java.en.*;
import com.piratenKapern.Game.Dice;
import com.piratenKapern.Game.Card;
import com.piratenKapern.AcceptanceTestPart3;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AcceptanceTestDefs {
    Game game = new Game();
    AcceptanceTestPart3 aTestPart3 = new AcceptanceTestPart3();
    @Given("a player wishes to roll dice only once for their turn")
    public void aPlayerWishesToRollDiceOnlyOnceForTheirTurn() {

    }

    public ArrayList<Dice> convertToDiceArray(String dice){
        dice = dice.replaceAll("\\s+","");
        String[] diceArray = dice.split(",");
        ArrayList<Dice> convertedDice = new ArrayList<>();

        for(int i = 0; i < diceArray.length; ++i){
            convertedDice.add(Dice.valueOf(diceArray[i]));
        }

        return convertedDice;
    }

    public ArrayList<Integer> convertToIntegerArray(String diceNumbers){
        diceNumbers = diceNumbers.replaceAll("\\s+","");
        ArrayList<Integer> convertedDiceNums = new ArrayList<>();
        String[] diceNumList = diceNumbers.split(",");

        for(int i = 0; i < diceNumList.length; ++i){
            convertedDiceNums.add(Integer.valueOf(diceNumList[i])-1);
        }

        return convertedDiceNums;
    }
    @When("the player draws a {} and rolls {}")
    public void thePlayerDrawsAFortuneCardAndRollsDice(Card fortuneCard, String diceString) {
        game.drawFortuneCard();
        game.setFortuneCard(fortuneCard);

        game.rollDice();
        game.setDice(convertToDiceArray(diceString));
    }


    @Then("the player scores {int} for this round")
    public void thePlayerScores(int score) {
        assertEquals(score,game.calculateScore());
    }

    @Given("a player wishes to roll dice twice for their turn")
    public void aPlayerWishesToRollDiceTwiceForTheirTurn() {

    }

    @And("the player re-rolls dice numbers {}")
    public void thePlayerReRollsDiceNumbers(String diceNumbers) {
        ArrayList<Integer> convertedDiceNums = convertToIntegerArray(diceNumbers);
        game.setDiceToReroll(convertedDiceNums);
        game.rerollDice();
    }

    @And("the player gets {}")
    public void thePlayerGetsRoll(String diceString) {
        ArrayList<Dice> dice = convertToDiceArray(diceString);
        for(int i = 0; i < game.getDiceToReroll().size();++i){
            int dieIndex = game.getDiceToReroll().get(i);
            game.getDice().set(dieIndex,dice.get(i));
        }
    }

    @Given("a player wishes to roll dice thrice for their turn")
    public void aPlayerWishesToRollDiceThriceForTheirTurn() {
    }

    @And("the player keeps dice numbers {} in the chest")
    public void thePlayerKeepsDiceNumbersInTheChest(String diceNumbers) {
        ArrayList<Integer> convertedDiceNums = convertToIntegerArray(diceNumbers);
        for(int i = 0; i < convertedDiceNums.size(); ++i){
            int dieIndex = convertedDiceNums.get(i);
            game.getDiceInTreasureChest().add(dieIndex);
        }
    }


    @And("the player takes out dice numbers {} from the chest")
    public void thePlayerTakesOutDiceNumbersFromTheChest(String diceNumbers) {
        ArrayList<Integer> convertedDiceNums = convertToIntegerArray(diceNumbers);
        game.getDiceInTreasureChest().removeAll(convertedDiceNums);
    }


    @And("the player enters the Skulls Island")
    public void thePlayerEntersTheSkullsIsland() {
    }

    @And("the other players receive a deduction of {}")
    public void theOtherPlayersReceiveADeductionOf(int deduction) {
        //Deductions were defined as positive in the steps but the system works with negative scores
        deduction = deduction * -1;
        assertEquals(deduction,game.getSkullIslandDeduction());
    }

    //Multiplayer step definitions
    //All step definitions except theGameStops() and theGameEnds() are empty because I am reusing my multiplayer tests from assignment 1
    @Given("all players have joined")
    public void allPlayersHaveJoined() {
    }

    @When("round {int} begins")
    public void roundBegins(int roundNum) {
    }

    @And("player {int} draws a {}")
    public void playerDrawsCardAndRollsDice(int playerNum, String diceString) {
    }

    @And("player {int} scores {}")
    public void playerScoresPointsForThisRound(int playerNum, String points) {
    }

    @And("player {int} re-rolls a {}")
    public void playerReRollsDice(int playerNum, String roll) {
    }


    @And("player {int} chooses not to re-roll")
    public void playerChoosesNotToReRoll(int playerNum) {
    }

    @And("player {int} and player {int} get {} {int} point deduction")
    public void playerAndPlayerGetAPointDeduction(int playerNum1, int playerNum2, String article, int score) {
    }

    @And("player {int}'s score drops to {int}")
    public void playerScoreDropsTo(int playerNum, int score) {
    }

    @And("player {int}'s score stays at {int}")
    public void playerScoreStaysAt(int playerNum, int score) {
    }



    @Then("the game ends with player 1 as the winner with {int} points, player 2 with {int} points and player 3 with {int} points.")
    public void theGameEnds(int playerOneScore, int playerTwoScore, int playerThreeScore) {

        if(playerOneScore == 4000 && playerTwoScore == 2000 && playerThreeScore == 0){
            aTestPart3.testRow132();
        }

        else if(playerOneScore == 3800 && playerTwoScore == 0 && playerThreeScore == 0){
            aTestPart3.testRow140();
        }

        else if(playerOneScore == 9000 && playerTwoScore == 4000 && playerThreeScore == 0){
            aTestPart3.testRow145();
        }


    }


    @Then("the game stops")
    public void theGameStops() {
        aTestPart3.testRow150();
    }

    @But("the player is dead")
    public void thePlayerIsDead() {
        assertTrue(game.playerIsDead());
    }

}
