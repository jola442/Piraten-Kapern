package com.piratenKapern;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import com.piratenKapern.Game.Dice;
import com.piratenKapern.Game.Card;

public class AcceptanceTestPart2 {
    Game game = new Game();

    @Test
    public void testRow77() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.SORCERESS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.SWORD, Dice.MONKEY, Dice.COIN, Dice.PARROT, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Dice.SKULL);
        game.getDice().set(6,Dice.MONKEY);
        game.getDice().set(7,Dice.MONKEY);

        diceToReroll.clear();
        diceToReroll.add(5);

        game.rerollDice();

        game.getDice().set(5, Dice.MONKEY);
        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow78() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.SORCERESS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(0));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(0,Dice.PARROT);

        diceToReroll.clear();
        diceToReroll.addAll(Arrays.asList(6,7));

        game.rerollDice();

        game.getDice().set(6, Dice.PARROT);
        game.getDice().set(7, Dice.PARROT);

        assertEquals(1000, game.calculateScore());
    }

    @Test
    public void testRow79() {
        game.drawFortuneCard();
        game.setFortuneCard(Card.SORCERESS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Dice.SKULL);
        game.getDice().set(6,Dice.PARROT);
        game.getDice().set(7,Dice.PARROT);

        diceToReroll.clear();
        diceToReroll.add(5);

        game.rerollDice();

        game.getDice().set(5, Dice.PARROT);

        assertEquals(2000, game.calculateScore());
    }

    @Test
    public void testRow82(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.MONKEY_AND_PARROT);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.SKULL, Dice.COIN));
        game.setDice(dice);

        assertEquals(1100, game.calculateScore());
    }

    @Test
    public void testRow83(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.MONKEY_AND_PARROT);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.PARROT, Dice.PARROT, Dice.COIN, Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(2,3));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(2,Dice.MONKEY);
        game.getDice().set(3,Dice.PARROT);

        assertEquals(1700, game.calculateScore());
    }

    @Test
    public void testRow84(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.MONKEY_AND_PARROT);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow90(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.CHEST);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.SWORD, Dice.SWORD, Dice.DIAMOND, Dice.DIAMOND, Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceInTreasureChest = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceInTreasureChest(diceInTreasureChest);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Dice.PARROT);
        game.getDice().set(4,Dice.PARROT);

        game.getDiceInTreasureChest().addAll(Arrays.asList(0,1,2,3,4));
        game.getDiceInTreasureChest().removeAll(Arrays.asList(5,6,7));

        diceToReroll.clear();;
        diceToReroll.addAll(Arrays.asList(5,6,7));

        game.rerollDice();
        game.getDice().set(5, Dice.SKULL);
        game.getDice().set(6, Dice.COIN);
        game.getDice().set(7, Dice.PARROT);

        assertEquals(1100, game.calculateScore());
    }

    @Test
    public void testRow94(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.CHEST);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.COIN, Dice.COIN, Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceInTreasureChest = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceInTreasureChest(diceInTreasureChest);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(2,3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(2,Dice.DIAMOND);
        game.getDice().set(3,Dice.DIAMOND);
        game.getDice().set(4,Dice.COIN);

        game.getDiceInTreasureChest().add(4);

        diceToReroll.clear();
        diceToReroll.addAll(Arrays.asList(2,3));

        game.rerollDice();

        game.getDice().set(2, Dice.SKULL);
        game.getDice().set(3, Dice.COIN);
//        game.reportDeath();

        assertEquals(600, game.calculateScore());
    }

    @Test
    public void testRow97(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.DIAMOND, Dice.PARROT));
        game.setDice(dice);

        assertEquals(400, game.calculateScore());
    }

    @Test
    public void testRow98(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.COIN, Dice.COIN));
        game.setDice(dice);

        assertEquals(1800, game.calculateScore());
    }

    @Test
    public void testRow99(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.DIAMOND));
        game.setDice(dice);

        assertEquals(1000, game.calculateScore());
    }

    @Test
    public void testRow102(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.TWO_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.PARROT, Dice.PARROT, Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Dice.COIN);
        game.getDice().set(6,Dice.SWORD);


        assertEquals(1200, game.calculateScore());
    }

    @Test
    public void testRow103(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.MONKEY_AND_PARROT);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.COIN, Dice.COIN, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND));
        game.setDice(dice);

        assertEquals(1200, game.calculateScore());
    }

    @Test
    public void testRow106(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.TWO_SKULLS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow107(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.ONE_SKULL);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow109(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.TWO_SKULLS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(2,3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(2, Dice.SKULL);
        game.getDice().set(3, Dice.SKULL);
        game.getDice().set(4, Dice.SWORD);

        diceToReroll.clear();
        diceToReroll.addAll(Arrays.asList(4,5,6,7));

        game.getDice().set(4, Dice.SKULL);
        game.getDice().set(5, Dice.SKULL);
        game.getDice().set(6, Dice.SKULL);
        game.getDice().set(7, Dice.SWORD);

        assertEquals(0, game.calculateScore());
        assertEquals(-900, game.getSkullIslandDeduction());
    }

    @Test
    public void testRow110(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5, Dice.SKULL);
        game.getDice().set(6, Dice.SKULL);
        game.getDice().set(7, Dice.COIN);

        assertEquals(0, game.calculateScore());
        assertEquals(-1400, game.getSkullIslandDeduction());
    }


    @Test
    public void testRow111(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.TWO_SKULLS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(3,4,5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3, Dice.COIN);
        game.getDice().set(4, Dice.COIN);
        game.getDice().set(5, Dice.COIN);
        game.getDice().set(6, Dice.COIN);
        game.getDice().set(7, Dice.COIN);

        assertEquals(0, game.calculateScore());
//        assertEquals(-500, game.getSkullIslandDeduction());
    }


    @Test
    public void testRow114(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.TWO_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.SWORD));
        game.setDice(dice);

//        game.reportDeduction();
        assertEquals(-300, game.calculateScore());
    }


    @Test
    public void testRow115(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.THREE_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(4,5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(4, Dice.SKULL);
        game.getDice().set(5, Dice.SKULL);
        game.getDice().set(6, Dice.SKULL);
        game.getDice().set(7, Dice.SKULL);

//        game.reportDeduction();
        assertEquals(-500, game.calculateScore());
    }


    @Test
    public void testRow116(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.FOUR_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);

//        game.reportDeduction();
        assertEquals(-1000, game.calculateScore());
    }

    @Test
    public void testRow117(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.TWO_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.COIN, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);

        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow119(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.TWO_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SKULL, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6, Dice.SWORD);
        game.getDice().set(7, Dice.SKULL);

        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow120(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.THREE_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SKULL));
        game.setDice(dice);

        assertEquals(800, game.calculateScore());
    }

    @Test
    public void testRow122(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.THREE_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.SKULL, Dice.SKULL));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(0,1,2,3));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(0, Dice.SKULL);
        game.getDice().set(1, Dice.SKULL);
        game.getDice().set(2, Dice.SWORD);
        game.getDice().set(3, Dice.SWORD);

//        game.reportDeduction();
        assertEquals(-500, game.calculateScore());
    }

    @Test
    public void testRow123(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.FOUR_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SKULL));
        game.setDice(dice);

        assertEquals(1300, game.calculateScore());
    }

    @Test
    public void testRow126(){
        game.drawFortuneCard();
        game.setFortuneCard(Card.FOUR_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SKULL, Dice.DIAMOND, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6, Dice.SWORD);
        game.getDice().set(7, Dice.SWORD);

        diceToReroll.clear();
        diceToReroll.addAll(Arrays.asList(0,1,2));
        game.setDiceToReroll(diceToReroll);

        game.rerollDice();

        game.getDice().set(0, Dice.SWORD);
        game.getDice().set(1, Dice.PARROT);
        game.getDice().set(2, Dice.PARROT);


        assertEquals(1300, game.calculateScore());
    }











}