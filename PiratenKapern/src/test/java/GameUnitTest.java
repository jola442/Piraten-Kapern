import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class GameUnitTest {
    Game game = new Game();

    @Test
    void testDrawFortuneCard() {
        Game.Card fortuneCard = game.drawFortuneCard();

        if (fortuneCard == Game.Card.CHEST) {
            assertEquals(Config.NUM_OF_CHEST_CARDS - 1, game.getFortuneCards().get(Game.Card.CHEST));

        } else if (fortuneCard == Game.Card.SORCERESS) {
            assertEquals(Config.NUM_OF_SORCERESS_CARDS - 1, game.getFortuneCards().get(Game.Card.SORCERESS));

        } else if (fortuneCard == Game.Card.CAPTAIN) {
            assertEquals(Config.NUM_OF_CAPTAIN_CARDS - 1, game.getFortuneCards().get(Game.Card.CAPTAIN));

        } else if (fortuneCard == Game.Card.MONKEY_AND_PARROT) {
            assertEquals(Config.NUM_OF_MONKEY_AND_PARROT_CARDS - 1, game.getFortuneCards().get(Game.Card.MONKEY_AND_PARROT));

        } else if (fortuneCard == Game.Card.DIAMOND) {
            assertEquals(Config.NUM_OF_DIAMOND_CARDS - 1, game.getFortuneCards().get(Game.Card.DIAMOND));

        } else if (fortuneCard == Game.Card.COIN) {
            assertEquals(Config.NUM_OF_COIN_CARDS - 1, game.getFortuneCards().get(Game.Card.COIN));

        } else if (fortuneCard == Game.Card.TWO_SKULLS) {
            assertEquals(Config.NUM_OF_TWO_SKULLS_CARDS - 1, game.getFortuneCards().get(Game.Card.TWO_SKULLS));

        } else if (fortuneCard == Game.Card.ONE_SKULL) {
            assertEquals(Config.NUM_OF_ONE_SKULL_CARDS - 1, game.getFortuneCards().get(Game.Card.ONE_SKULL));

        } else if (fortuneCard == Game.Card.TWO_SWORDS) {
            assertEquals(Config.NUM_OF_TWO_SWORDS_CARDS - 1, game.getFortuneCards().get(Game.Card.TWO_SWORDS));

        } else if (fortuneCard == Game.Card.THREE_SWORDS) {
            assertEquals(Config.NUM_OF_THREE_SWORDS_CARDS - 1, game.getFortuneCards().get(Game.Card.THREE_SWORDS));

        } else if (fortuneCard == Game.Card.FOUR_SWORDS) {
            assertEquals(Config.NUM_OF_FOUR_SWORDS_CARDS - 1, game.getFortuneCards().get(Game.Card.FOUR_SWORDS));
        }

        assertEquals(Config.NUM_OF_CARDS - 1, game.getNumCards());
    }


    @Test
    void testDrawAllFortuneCards(){
        for(int i = 0; i < Config.NUM_OF_CARDS; i++){
            game.drawFortuneCard();
        }

        assertEquals(35,game.getNumCards());
    }

    @Test
    void testCountDice(){
        game.rollDice();
        ArrayList<Game.Dice> dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.COIN,
                Game.Dice.DIAMOND, Game.Dice.MONKEY, Game.Dice.PARROT));
        game.setDice(dice);
        game.countDice();
        assertEquals(2, game.getNumMonkeyDice());
        assertEquals(2, game.getNumParrotDice());
        assertEquals(1, game.getNumSwordDice());
        assertEquals(1, game.getNumSkullDice());
        assertEquals(1, game.getNumCoinDice());
        assertEquals(1, game.getNumDiamondDice());
    }

    @Test
    void testThreeOfAKind(){
        game.drawFortuneCard();
        game.rollDice();

        //2 SKULLS DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        ArrayList<Game.Dice> dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.DIAMOND, Game.Dice.COIN,
                Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals(0, game.calculateScore());

        //1 SKULLS DIE + 1 TWO_SKULLS CARD
        game.setFortuneCard(Game.Card.TWO_SKULLS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.DIAMOND, Game.Dice.COIN,
                Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals(0, game.calculateScore());

        //2 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Game.Card.COIN);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*3) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //3 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*3) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //2 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Game.Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*3) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //3 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*3) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 2 MONKEY DICE + MONKEY_AND_PARROT CARD + 1 COIN DIE
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.COIN));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //3 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //3 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Game.Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.THREE_OF_A_KIND_SCORE), game.calculateScore());
    }

}