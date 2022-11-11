package com.piratenKapern;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.piratenKapern.Game.Dice;
import com.piratenKapern.Game.Card;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AcceptanceTestPart1Defs {
    Game game = new Game();
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

    @And("the player wishes to re-roll dice numbers {}")
    public void thePlayerWishesToReRollDiceNumbers(String diceNumbers) {
        ArrayList<Integer> convertedDiceNums = new ArrayList<>();
        String[] diceNumList =diceNumbers.split(",");
        for(int i = 0; i < diceNumList.length; ++i){
            convertedDiceNums.add(Integer.valueOf(diceNumList[i])-1);
        }
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
}
