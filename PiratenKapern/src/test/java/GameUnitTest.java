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

    @Test
    void testFourOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //3 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Game.Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*4) + (Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //4 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*4) + (Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //3 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Game.Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*4) + (Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //4 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*4) + (Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 3 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);
        assertEquals(Config.FOUR_OF_A_KIND_SCORE, game.calculateScore());

        //2 PARROT DIE + 2 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);
        assertEquals((Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //4 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //4 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Game.Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());
    }

    @Test
    void testFiveOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //4 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Game.Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*5) + (Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //5 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*5) + (Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //4 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Game.Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*5) + (Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //5 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*5) + (Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 4 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);
        assertEquals(Config.FIVE_OF_A_KIND_SCORE, game.calculateScore());

        //2 PARROT DIE + 3 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);
        assertEquals((Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //5 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //5 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Game.Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());
    }

    @Test
    void testSixOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //5 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Game.Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*6) + (Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //6 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*6) + (Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //5 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Game.Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*6) + (Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //6 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*6) + (Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 5 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);
        assertEquals(Config.SIX_OF_A_KIND_SCORE, game.calculateScore());

        //2 PARROT DIE + 4 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.PARROT, Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD, Game.Dice.SWORD));
        game.setDice(dice);
        assertEquals((Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //6 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //6 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Game.Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.SIX_OF_A_KIND_SCORE), game.calculateScore());
    }


    @Test
    void testSevenOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //6 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Game.Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*7) + (Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //7 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*7) + (Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //6 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Game.Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*7) + (Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //7 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*7) + (Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 6 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SWORD));
        game.setDice(dice);
        assertEquals(Config.SEVEN_OF_A_KIND_SCORE, game.calculateScore());

        //7 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //7 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Game.Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());
    }


    @Test
    void testEightOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //7 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Game.Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*8) + (Config.EIGHT_OF_A_KIND_SCORE), game.calculateScore());

        //8 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Game.Card.COIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*9) + (Config.EIGHT_OF_A_KIND_SCORE) + (Config.FULL_CHEST_BONUS), game.calculateScore());

        //7 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Game.Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*8) + (Config.EIGHT_OF_A_KIND_SCORE), game.calculateScore());

        //8 DIAMOND DICE + DIAMOND CARD
        game.setFortuneCard(Game.Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND, Game.Dice.DIAMOND));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*9) + (Config.EIGHT_OF_A_KIND_SCORE) + (Config.FULL_CHEST_BONUS), game.calculateScore());

        //1 PARROT DIE + 7 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Game.Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.PARROT, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
        game.setDice(dice);
        assertEquals((Config.EIGHT_OF_A_KIND_SCORE) + (Config.FULL_CHEST_BONUS), game.calculateScore());

        //8 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
        game.setDice(dice);
        assertEquals((Config.EIGHT_OF_A_KIND_SCORE) + (Config.FULL_CHEST_BONUS), game.calculateScore());

        //8 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Game.Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.MONKEY));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * (Config.EIGHT_OF_A_KIND_SCORE + Config.FULL_CHEST_BONUS)) , game.calculateScore());
    }

    @Test
    void testSeaBattles(){
        game.drawFortuneCard();
        game.rollDice();


        //3 SWORDS DICE + TWO_SWORDS CARD
        game.setFortuneCard(Game.Card.TWO_SWORDS);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.TWO_SWORDS_BONUS + Config.THREE_OF_A_KIND_SCORE), game.calculateScore());


        //2 SWORDS DICE + TWO_SWORDS CARD
        game.setFortuneCard(Game.Card.TWO_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals(Config.TWO_SWORDS_BONUS, game.calculateScore());

        //1 SWORDS DIE + 1 COIN DIE + 1 DIAMOND DIE + TWO_SWORDS CARD
        game.setFortuneCard(Game.Card.TWO_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.COIN, Game.Dice.DIAMOND, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals(-Config.TWO_SWORDS_BONUS, game.calculateScore());

        //4 SWORDS DICE + THREE_SWORDS CARD
        game.setFortuneCard(Game.Card.THREE_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.THREE_SWORDS_BONUS + Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());


        //3 SWORDS DICE + THREE_SWORDS CARD
        game.setFortuneCard(Game.Card.THREE_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.MONKEY, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.THREE_SWORDS_BONUS + Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //2 SWORDS DICE + 1 COIN DIE + 1 DIAMOND DIE + THREE_SWORDS CARD
        game.setFortuneCard(Game.Card.THREE_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.MONKEY, Game.Dice.MONKEY, Game.Dice.COIN, Game.Dice.DIAMOND, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals(- Config.THREE_SWORDS_BONUS, game.calculateScore());

        //5 SWORDS DICE + FOUR_SWORDS CARD
        game.setFortuneCard(Game.Card.FOUR_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.FOUR_SWORDS_BONUS + Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());


        //4 SWORDS DICE + FOUR_SWORDS CARD
        game.setFortuneCard(Game.Card.FOUR_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.FOUR_SWORDS_BONUS + Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //3 SWORDS DICE + 1 COIN DIE + 1 DIAMOND DIE + FOUR_SWORDS CARD
        game.setFortuneCard(Game.Card.FOUR_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.SWORD, Game.Dice.MONKEY, Game.Dice.COIN, Game.Dice.DIAMOND, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals(- Config.FOUR_SWORDS_BONUS, game.calculateScore());
    }

    @Test
    void testTreasureChest(){
        game.drawFortuneCard();
        game.rollDice();
        game.setFortuneCard(Game.Card.CHEST);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.DIAMOND, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);


        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(4,5,6,7));
        game.setDiceToReroll(diceToReroll);;


        ArrayList <Integer> diceInTreasureChest = new ArrayList<>(Arrays.asList(0,1,2,3));
        game.setDiceInTreasureChest(diceInTreasureChest);

        game.rerollDice();

        //3 SKULLS
        dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.DIAMOND, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT));
        game.setDice(dice);

        assertEquals((Config.COIN_BONUS*3) + Config.DIAMOND_BONUS + (Config.THREE_OF_A_KIND_SCORE),game.calculateScore());
    }

    @Test
    void testSkullIslandDeductions(){
        game.drawFortuneCard();
        game.rollDice();

        //2 SKULLS DICE + TWO_SKULLS CARD
        game.setFortuneCard(Game.Card.TWO_SKULLS);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Game.Dice.COIN, Game.Dice.COIN, Game.Dice.COIN, Game.Dice.DIAMOND, Game.Dice.SKULL, Game.Dice.SKULL, Game.Dice.PARROT, Game.Dice.PARROT));
        game.setDice(dice);
        assertEquals(-(Config.SKULL_DEDUCTION*4), game.getSkullIslandDeduction());

        //2 SKULLS DICE + ONE_SKULL CARD
        game.setFortuneCard(Game.Card.ONE_SKULL);
        assertEquals(-(Config.SKULL_DEDUCTION*3), game.getSkullIslandDeduction());

        //2 SKULLS DICE + COIN CARD
        game.setFortuneCard(Game.Card.COIN);
        assertEquals(-(Config.SKULL_DEDUCTION*2), game.getSkullIslandDeduction());

        //2 SKULLS DICE + CAPTAIN CARD
        game.setFortuneCard(Game.Card.CAPTAIN);
        assertEquals(-(Config.SKULL_DEDUCTION*2)*Config.CAPTAIN_MULTIPLIER, game.getSkullIslandDeduction());
    }

}
