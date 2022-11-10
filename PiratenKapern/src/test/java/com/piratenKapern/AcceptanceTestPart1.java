package com.piratenKapern;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import com.piratenKapern.Game.Dice;
import com.piratenKapern.Game.Card;

import static org.junit.jupiter.api.Assertions.*;

public class AcceptanceTestPart1 {
    Game game = new Game();

    @Test
    public void testRow45() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow46() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Dice.SKULL);
        game.getDice().set(6,Dice.SKULL);
        game.getDice().set(7,Dice.SWORD);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow47() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6,Dice.SKULL);
        game.getDice().set(7,Dice.SWORD);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow49() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Dice.SKULL);
        game.getDice().set(6,Dice.MONKEY);
        game.getDice().set(7,Dice.MONKEY);

        diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6, Dice.SKULL);
        game.getDice().set(7, Dice.MONKEY);

        assertEquals(0, game.calculateScore());
    }


    @Test
    public void testRow51() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.PARROT, Dice.PARROT,Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.COIN, Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(1,2));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(1,Dice.COIN);
        game.getDice().set(2,Dice.COIN);

        diceToReroll = new ArrayList<>(Arrays.asList(3,4,5));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Dice.COIN);
        game.getDice().set(4,Dice.COIN);
        game.getDice().set(5,Dice.COIN);

        assertEquals(4800, game.calculateScore());
    }


    @Test
    public void testRow52() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.PARROT,Dice.PARROT, Dice.DIAMOND, Dice.DIAMOND, Dice.COIN, Dice.COIN));
        game.setDice(dice);

        assertEquals(800, game.calculateScore());
    }

    @Test
    public void testRow53(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.SKULL,Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6,Dice.SWORD);
        game.getDice().set(7,Dice.MONKEY);

        assertEquals(300, game.calculateScore());
    }

    @Test
    public void testRow54(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SKULL, Dice.SKULL));
        game.setDice(dice);

        assertEquals(300, game.calculateScore());
    }

    @Test
    public void testRow55(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.SKULL, Dice.SKULL, Dice.MONKEY, Dice.SWORD, Dice.PARROT));
        game.setDice(dice);

        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow56(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.DIAMOND);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        assertEquals(700, game.calculateScore());
    }

    @Test
    public void testRow57(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.SKULL));
        game.setDice(dice);

        assertEquals(400, game.calculateScore());
    }

    @Test
    public void testRow58() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.COIN, Dice.COIN, Dice.PARROT, Dice.PARROT, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Dice.COIN);
        game.getDice().set(4,Dice.SWORD);
        assertEquals(800, game.calculateScore());
    }

    @Test
    public void testRow59() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.COIN, Dice.COIN, Dice.PARROT, Dice.PARROT, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Dice.COIN);
        game.getDice().set(4,Dice.SWORD);
        assertEquals(1200, game.calculateScore());
    }

    @Test
    public void testRow61() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.PARROT, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(1,2));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(1,Dice.SKULL);
        game.getDice().set(2,Dice.SWORD);

        diceToReroll = new ArrayList<>(Arrays.asList(3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Dice.SWORD);
        game.getDice().set(4,Dice.MONKEY);



        assertEquals(600, game.calculateScore());
    }



    @Test
    public void testRow62(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SKULL));
        game.setDice(dice);

        assertEquals(1100, game.calculateScore());
    }

    @Test
    public void testRow63(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.SKULL));
        game.setDice(dice);

        assertEquals(2100, game.calculateScore());
    }

    @Test
    public void testRow64(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN));
        game.setDice(dice);

        assertEquals(5400, game.calculateScore());
    }

    @Test
    public void testRow65(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.DIAMOND);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN));
        game.setDice(dice);

        assertEquals(5400, game.calculateScore());
    }

    @Test
    public void testRow66(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        assertEquals(9000, game.calculateScore());
    }

    @Test
    public void testRow67() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6,Dice.MONKEY);
        game.getDice().set(7,Dice.MONKEY);
        assertEquals(4600, game.calculateScore());
    }

    @Test
    public void testRow68() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.DIAMOND);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6,Dice.DIAMOND);
        game.getDice().set(7,Dice.DIAMOND);
        assertEquals(400, game.calculateScore());
    }

    @Test
    public void testRow69() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.DIAMOND, Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(0,1));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(0,Dice.DIAMOND);
        game.getDice().set(1,Dice.DIAMOND);
        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow70() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.COIN, Dice.COIN, Dice.MONKEY, Dice.PARROT, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Dice.COIN);
        game.getDice().set(6,Dice.MONKEY);
        game.getDice().set(7,Dice.PARROT);
        assertEquals(600, game.calculateScore());
    }

    @Test
    public void testRow71() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.DIAMOND);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.COIN, Dice.COIN, Dice.MONKEY, Dice.PARROT, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Dice.COIN);
        game.getDice().set(6,Dice.MONKEY);
        game.getDice().set(7,Dice.PARROT);
        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow72(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.COIN, Dice.COIN, Dice.SKULL, Dice.SKULL));
        game.setDice(dice);

        assertEquals(600, game.calculateScore());
    }



}
