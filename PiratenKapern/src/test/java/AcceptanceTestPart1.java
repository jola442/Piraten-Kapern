


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceTestPart1 {
    Game game = new Game();

    @Test
    public void testRow45() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow46() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Game.Dice.SKULL);
        game.getDice().set(6,Game.Dice.SKULL);
        game.getDice().set(7,Game.Dice.SWORD);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow47() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6,Game.Dice.SKULL);
        game.getDice().set(7,Game.Dice.SWORD);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow48And49() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Game.Dice.SKULL);
        game.getDice().set(6,Game.Dice.MONKEY);
        game.getDice().set(7,Game.Dice.MONKEY);

        diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6, Game.Dice.SKULL);
        game.getDice().set(7, Game.Dice.MONKEY);

        assertEquals(0, game.calculateScore());
    }


    @Test
    public void testRow50And51() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT,Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.COIN, Game.Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(1,2));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(1,Game.Dice.COIN);
        game.getDice().set(2,Game.Dice.COIN);

        diceToReroll = new ArrayList<>(Arrays.asList(3,4,5));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Game.Dice.COIN);
        game.getDice().set(4,Game.Dice.COIN);
        game.getDice().set(5,Game.Dice.COIN);

        assertEquals(4800, game.calculateScore());
    }


    @Test
    public void testRow52() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT,Game.Dice.PARROT, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.COIN, Game.Dice.COIN));
        game.setDice(dice);

        assertEquals(800, game.calculateScore());
    }

    @Test
    public void testRow53(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL,Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6,Game.Dice.SWORD);
        game.getDice().set(7,Game.Dice.MONKEY);

        assertEquals(300, game.calculateScore());
    }

    @Test
    public void testRow54(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.SKULL));
        game.setDice(dice);

        assertEquals(300, game.calculateScore());
    }

    @Test
    public void testRow55(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.PARROT));
        game.setDice(dice);

        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow56(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.DIAMOND);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        assertEquals(700, game.calculateScore());
    }

    @Test
    public void testRow57(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SKULL));
        game.setDice(dice);

        assertEquals(400, game.calculateScore());
    }

    @Test
    public void testRow58() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Game.Dice.COIN);
        game.getDice().set(4,Game.Dice.SWORD);
        assertEquals(800, game.calculateScore());
    }

    @Test
    public void testRow59() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Game.Dice.COIN);
        game.getDice().set(4,Game.Dice.SWORD);
        assertEquals(1200, game.calculateScore());
    }

    @Test
    public void testRow60And61() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(1,2));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(1,Game.Dice.SKULL);
        game.getDice().set(2,Game.Dice.SWORD);

        diceToReroll = new ArrayList<>(Arrays.asList(3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Game.Dice.SWORD);
        game.getDice().set(4,Game.Dice.MONKEY);



        assertEquals(600, game.calculateScore());
    }



    @Test
    public void testRow62(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL));
        game.setDice(dice);

        assertEquals(1100, game.calculateScore());
    }

    @Test
    public void testRow63(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SKULL));
        game.setDice(dice);

        assertEquals(2100, game.calculateScore());
    }

    @Test
    public void testRow64(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN));
        game.setDice(dice);

        assertEquals(5400, game.calculateScore());
    }

    @Test
    public void testRow65(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.DIAMOND);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN));
        game.setDice(dice);

        assertEquals(5400, game.calculateScore());
    }

    @Test
    public void testRow66(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        assertEquals(9000, game.calculateScore());
    }

    @Test
    public void testRow67() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6,Game.Dice.MONKEY);
        game.getDice().set(7,Game.Dice.MONKEY);
        assertEquals(4600, game.calculateScore());
    }

    @Test
    public void testRow68() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.DIAMOND);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6,Game.Dice.DIAMOND);
        game.getDice().set(7,Game.Dice.DIAMOND);
        assertEquals(400, game.calculateScore());
    }

    @Test
    public void testRow69() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.DIAMOND, Game.Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(0,1));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(0,Game.Dice.DIAMOND);
        game.getDice().set(1,Game.Dice.DIAMOND);
        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow70() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Game.Dice.COIN);
        game.getDice().set(6,Game.Dice.MONKEY);
        game.getDice().set(7,Game.Dice.PARROT);
        assertEquals(600, game.calculateScore());
    }

    @Test
    public void testRow71() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.DIAMOND);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Game.Dice.COIN);
        game.getDice().set(6,Game.Dice.MONKEY);
        game.getDice().set(7,Game.Dice.PARROT);
        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow72(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.SKULL, Game.Dice.SKULL));
        game.setDice(dice);

        assertEquals(600, game.calculateScore());
    }



}
