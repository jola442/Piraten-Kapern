import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceTestPart2 {
    Game game = new Game();

    @Test
    public void testRow77() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.SORCERESS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.SWORD, Game.Dice.MONKEY, Game.Dice.COIN, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Game.Dice.SKULL);
        game.getDice().set(6,Game.Dice.MONKEY);
        game.getDice().set(7,Game.Dice.MONKEY);

        diceToReroll.clear();
        diceToReroll.add(5);

        game.rerollDice();

        game.getDice().set(5, Game.Dice.MONKEY);
        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow78() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.SORCERESS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(0));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(0,Game.Dice.PARROT);

        diceToReroll.clear();
        diceToReroll.addAll(Arrays.asList(6,7));

        game.rerollDice();

        game.getDice().set(6, Game.Dice.PARROT);
        game.getDice().set(7, Game.Dice.PARROT);

        assertEquals(1000, game.calculateScore());
    }

    @Test
    public void testRow79() {
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.SORCERESS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Game.Dice.SKULL);
        game.getDice().set(6,Game.Dice.PARROT);
        game.getDice().set(7,Game.Dice.PARROT);

        diceToReroll.clear();
        diceToReroll.add(5);

        game.rerollDice();

        game.getDice().set(5, Game.Dice.PARROT);

        assertEquals(2000, game.calculateScore());
    }

    @Test
    public void testRow82(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SKULL, Game.Dice.COIN));
        game.setDice(dice);

        assertEquals(1100, game.calculateScore());
    }

    @Test
    public void testRow83(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.COIN, Game.Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(2,3));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(2,Game.Dice.MONKEY);
        game.getDice().set(3,Game.Dice.PARROT);

        assertEquals(1700, game.calculateScore());
    }

    @Test
    public void testRow84(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow87To90(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.CHEST);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceInTreasureChest = new ArrayList<>(Arrays.asList(5,6,7));
        game.setInTreasureChest(diceInTreasureChest);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3,Game.Dice.PARROT);
        game.getDice().set(4,Game.Dice.PARROT);

        game.getInTreasureChest().addAll(Arrays.asList(0,1,2,3,4));
        game.getInTreasureChest().removeAll(Arrays.asList(5,6,7));

        diceToReroll.clear();;
        diceToReroll.addAll(Arrays.asList(5,6,7));

        game.rerollDice();
        game.getDice().set(5, Game.Dice.SKULL);
        game.getDice().set(6, Game.Dice.COIN);
        game.getDice().set(7, Game.Dice.PARROT);

        assertEquals(1100, game.calculateScore());
    }

    @Test
    public void testRow92To94(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.CHEST);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceInTreasureChest = new ArrayList<>(Arrays.asList(5,6,7));
        game.setInTreasureChest(diceInTreasureChest);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(2,3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(2,Game.Dice.DIAMOND);
        game.getDice().set(3,Game.Dice.DIAMOND);
        game.getDice().set(4,Game.Dice.COIN);

        game.getInTreasureChest().add(4);

        diceToReroll.clear();
        diceToReroll.addAll(Arrays.asList(2,3));

        game.rerollDice();

        game.getDice().set(2, Game.Dice.SKULL);
        game.getDice().set(3, Game.Dice.COIN);
//        game.reportDeath();

        assertEquals(600, game.calculateScore());
    }

    @Test
    public void testRow97(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.DIAMOND, Game.Dice.PARROT));
        game.setDice(dice);

        assertEquals(400, game.calculateScore());
    }

    @Test
    public void testRow98(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.COIN, Game.Dice.COIN));
        game.setDice(dice);

        assertEquals(1800, game.calculateScore());
    }

    @Test
    public void testRow99(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.COIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.DIAMOND));
        game.setDice(dice);

        assertEquals(1000, game.calculateScore());
    }

    @Test
    public void testRow100To102(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.TWO_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.COIN));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5,Game.Dice.COIN);
        game.getDice().set(6,Game.Dice.SWORD);


        assertEquals(1200, game.calculateScore());
    }

    @Test
    public void testRow103(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND));
        game.setDice(dice);

        assertEquals(1200, game.calculateScore());
    }

    @Test
    public void testRow106(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.TWO_SKULLS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow107(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.ONE_SKULL);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        assertEquals(0, game.calculateScore());
    }

    @Test
    public void testRow108To109(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.TWO_SKULLS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(2,3,4));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(2, Game.Dice.SKULL);
        game.getDice().set(3, Game.Dice.SKULL);
        game.getDice().set(4, Game.Dice.SWORD);

        diceToReroll.clear();
        diceToReroll.addAll(Arrays.asList(4,5,6,7));

        game.getDice().set(4, Game.Dice.SKULL);
        game.getDice().set(5, Game.Dice.SKULL);
        game.getDice().set(6, Game.Dice.SKULL);
        game.getDice().set(7, Game.Dice.SWORD);

        assertEquals(0, game.calculateScore());
        assertEquals(-900, game.getSkullIslandDeduction());
    }

    @Test
    public void testRow110(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.CAPTAIN);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(5, Game.Dice.SKULL);
        game.getDice().set(6, Game.Dice.SKULL);
        game.getDice().set(7, Game.Dice.COIN);

        assertEquals(0, game.calculateScore());
        assertEquals(-1400, game.getSkullIslandDeduction());
    }


    @Test
    public void testRow111(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.TWO_SKULLS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(3,4,5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(3, Game.Dice.COIN);
        game.getDice().set(4, Game.Dice.COIN);
        game.getDice().set(5, Game.Dice.COIN);
        game.getDice().set(6, Game.Dice.COIN);
        game.getDice().set(7, Game.Dice.COIN);

        assertEquals(0, game.calculateScore());
//        assertEquals(-500, game.getSkullIslandDeduction());
    }


    @Test
    public void testRow114(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.TWO_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD));
        game.setDice(dice);

//        game.reportDeduction();
        assertEquals(-300, game.calculateScore());
    }


    @Test
    public void testRow115(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.THREE_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(4,5,6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(4, Game.Dice.SKULL);
        game.getDice().set(5, Game.Dice.SKULL);
        game.getDice().set(6, Game.Dice.SKULL);
        game.getDice().set(7, Game.Dice.SKULL);

//        game.reportDeduction();
        assertEquals(-500, game.calculateScore());
    }


    @Test
    public void testRow116(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.FOUR_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);

//        game.reportDeduction();
        assertEquals(-1000, game.calculateScore());
    }

    @Test
    public void testRow117(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.TWO_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.COIN, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);

        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow118(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.TWO_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6, Game.Dice.SWORD);
        game.getDice().set(7, Game.Dice.SKULL);

        assertEquals(500, game.calculateScore());
    }

    @Test
    public void testRow120(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.THREE_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL));
        game.setDice(dice);

        assertEquals(800, game.calculateScore());
    }

    @Test
    public void testRow121And122(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.THREE_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.SKULL));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(0,1,2,3));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(0, Game.Dice.SKULL);
        game.getDice().set(1, Game.Dice.SKULL);
        game.getDice().set(2, Game.Dice.SWORD);
        game.getDice().set(3, Game.Dice.SWORD);

//        game.reportDeduction();
        assertEquals(-500, game.calculateScore());
    }

    @Test
    public void testRow123(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.FOUR_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL));
        game.setDice(dice);

        assertEquals(1300, game.calculateScore());
    }

    @Test
    public void testRow124To126(){
        game.drawFortuneCard();
        game.setFortuneCard(Game.Card.FOUR_SWORDS);

        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.DIAMOND, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);

        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(6,7));
        game.setDiceToReroll(diceToReroll);
        game.rerollDice();

        game.getDice().set(6, Game.Dice.SWORD);
        game.getDice().set(7, Game.Dice.SWORD);

        diceToReroll.clear();
        diceToReroll.addAll(Arrays.asList(0,1,2));
        game.setDiceToReroll(diceToReroll);

        game.rerollDice();

        game.getDice().set(0, Game.Dice.SWORD);
        game.getDice().set(1, Game.Dice.PARROT);
        game.getDice().set(2, Game.Dice.PARROT);


        assertEquals(1300, game.calculateScore());
    }











}