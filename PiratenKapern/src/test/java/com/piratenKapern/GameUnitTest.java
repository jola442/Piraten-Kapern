package com.piratenKapern;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import com.piratenKapern.Game.Dice;
import com.piratenKapern.Game.Card;


public class GameUnitTest {
    Game game = new Game();

    @Test
    void testDrawFortuneCard() {
        Game.Card fortuneCard = game.drawFortuneCard();

        if (fortuneCard == Card.CHEST) {
            assertEquals(Config.NUM_OF_CHEST_CARDS - 1, game.getFortuneCards().get(Card.CHEST));

        } else if (fortuneCard == Card.SORCERESS) {
            assertEquals(Config.NUM_OF_SORCERESS_CARDS - 1, game.getFortuneCards().get(Card.SORCERESS));

        } else if (fortuneCard == Card.CAPTAIN) {
            assertEquals(Config.NUM_OF_CAPTAIN_CARDS - 1, game.getFortuneCards().get(Card.CAPTAIN));

        } else if (fortuneCard == Card.MONKEY_AND_PARROT) {
            assertEquals(Config.NUM_OF_MONKEY_AND_PARROT_CARDS - 1, game.getFortuneCards().get(Card.MONKEY_AND_PARROT));

        } else if (fortuneCard == Card.DIAMOND) {
            assertEquals(Config.NUM_OF_DIAMOND_CARDS - 1, game.getFortuneCards().get(Card.DIAMOND));

        } else if (fortuneCard == Card.COIN) {
            assertEquals(Config.NUM_OF_COIN_CARDS - 1, game.getFortuneCards().get(Card.COIN));

        } else if (fortuneCard == Card.TWO_SKULLS) {
            assertEquals(Config.NUM_OF_TWO_SKULLS_CARDS - 1, game.getFortuneCards().get(Card.TWO_SKULLS));

        } else if (fortuneCard == Card.ONE_SKULL) {
            assertEquals(Config.NUM_OF_ONE_SKULL_CARDS - 1, game.getFortuneCards().get(Card.ONE_SKULL));

        } else if (fortuneCard == Card.TWO_SWORDS) {
            assertEquals(Config.NUM_OF_TWO_SWORDS_CARDS - 1, game.getFortuneCards().get(Card.TWO_SWORDS));

        } else if (fortuneCard == Card.THREE_SWORDS) {
            assertEquals(Config.NUM_OF_THREE_SWORDS_CARDS - 1, game.getFortuneCards().get(Card.THREE_SWORDS));

        } else if (fortuneCard == Card.FOUR_SWORDS) {
            assertEquals(Config.NUM_OF_FOUR_SWORDS_CARDS - 1, game.getFortuneCards().get(Card.FOUR_SWORDS));
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
        ArrayList<Game.Dice> dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.PARROT, Dice.SWORD, Dice.SKULL, Dice.COIN,
                Dice.DIAMOND, Dice.MONKEY, Dice.PARROT));
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
        game.setFortuneCard(Card.ONE_SKULL);
        ArrayList<Game.Dice> dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.DIAMOND, Dice.COIN,
                Dice.MONKEY, Dice.MONKEY, Dice.PARROT));
        game.setDice(dice);
        assertEquals(0, game.calculateScore());

        //1 SKULLS DIE + 1 TWO_SKULLS CARD
        game.setFortuneCard(Card.TWO_SKULLS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.DIAMOND, Dice.COIN,
                Dice.MONKEY, Dice.MONKEY, Dice.PARROT));
        game.setDice(dice);
        assertEquals(0, game.calculateScore());

        //2 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Card.COIN);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.SWORD, Dice.SKULL, Dice.SKULL, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*3) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //3 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.SKULL, Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*3) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //2 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.SWORD, Dice.SKULL, Dice.SKULL, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*3) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //3 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.SKULL, Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*3) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 2 MONKEY DICE + MONKEY_AND_PARROT CARD + 1 COIN DIE
        game.setFortuneCard(Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.COIN));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS) + (Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //3 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //3 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SWORD, Dice.SWORD, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.THREE_OF_A_KIND_SCORE), game.calculateScore());
    }

    @Test
    void testFourOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //3 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.SKULL, Dice.SKULL, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*4) + (Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //4 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*4) + (Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //3 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.SKULL, Dice.SKULL, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*4) + (Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //4 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*4) + (Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 3 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);
        assertEquals(Config.FOUR_OF_A_KIND_SCORE, game.calculateScore());

        //2 PARROT DIE + 2 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.PARROT, Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);
        assertEquals((Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //4 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //4 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());
    }

    @Test
    void testFiveOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //4 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.SKULL, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*5) + (Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //5 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*5) + (Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //4 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.SKULL, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*5) + (Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //5 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*5) + (Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 4 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);
        assertEquals(Config.FIVE_OF_A_KIND_SCORE, game.calculateScore());

        //2 PARROT DIE + 3 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.PARROT, Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);
        assertEquals((Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //5 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());

        //5 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());
    }

    @Test
    void testSixOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //5 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*6) + (Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //6 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*6) + (Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //5 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*6) + (Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //6 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*6) + (Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 5 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);
        assertEquals(Config.SIX_OF_A_KIND_SCORE, game.calculateScore());

        //2 PARROT DIE + 4 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.PARROT, Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD, Dice.SWORD));
        game.setDice(dice);
        assertEquals((Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //6 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.SIX_OF_A_KIND_SCORE), game.calculateScore());

        //6 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.SIX_OF_A_KIND_SCORE), game.calculateScore());
    }


    @Test
    void testSevenOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //6 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*7) + (Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //7 COIN DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*7) + (Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //6 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*7) + (Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //7 DIAMOND DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*7) + (Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //1 PARROT DIE + 6 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.SWORD));
        game.setDice(dice);
        assertEquals(Config.SEVEN_OF_A_KIND_SCORE, game.calculateScore());

        //7 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());

        //7 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * Config.SEVEN_OF_A_KIND_SCORE), game.calculateScore());
    }


    @Test
    void testEightOfAKind(){
        game.drawFortuneCard();
        game.rollDice();


        //7 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Card.COIN);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*8) + (Config.EIGHT_OF_A_KIND_SCORE), game.calculateScore());

        //8 COIN DICE + 1 COIN CARD
        game.setFortuneCard(Card.COIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN, Dice.COIN));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*9) + (Config.EIGHT_OF_A_KIND_SCORE) + (Config.FULL_CHEST_BONUS), game.calculateScore());

        //7 DIAMOND DICE + 1 DIAMOND CARD
        game.setFortuneCard(Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.DIAMOND_BONUS*8) + (Config.EIGHT_OF_A_KIND_SCORE), game.calculateScore());

        //8 DIAMOND DICE + DIAMOND CARD
        game.setFortuneCard(Card.DIAMOND);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND, Dice.DIAMOND));
        game.setDice(dice);
        assertEquals((Config.COIN_BONUS*9) + (Config.EIGHT_OF_A_KIND_SCORE) + (Config.FULL_CHEST_BONUS), game.calculateScore());

        //1 PARROT DIE + 7 MONKEY DICE + MONKEY_AND_PARROT CARD
        game.setFortuneCard(Card.MONKEY_AND_PARROT);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.PARROT, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY));
        game.setDice(dice);
        assertEquals((Config.EIGHT_OF_A_KIND_SCORE) + (Config.FULL_CHEST_BONUS), game.calculateScore());

        //8 MONKEY DICE + 1 ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY));
        game.setDice(dice);
        assertEquals((Config.EIGHT_OF_A_KIND_SCORE) + (Config.FULL_CHEST_BONUS), game.calculateScore());

        //8 MONKEY DICE + CAPTAIN_CARD
        game.setFortuneCard(Card.CAPTAIN);
        dice = new ArrayList<Game.Dice>(Arrays.asList(Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY, Dice.MONKEY));
        game.setDice(dice);
        assertEquals((Config.CAPTAIN_MULTIPLIER * (Config.EIGHT_OF_A_KIND_SCORE + Config.FULL_CHEST_BONUS)) , game.calculateScore());
    }

    @Test
    void testSeaBattles(){
        game.drawFortuneCard();
        game.rollDice();


        //3 SWORDS DICE + TWO_SWORDS CARD
        game.setFortuneCard(Card.TWO_SWORDS);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.TWO_SWORDS_BONUS + Config.THREE_OF_A_KIND_SCORE), game.calculateScore());


        //2 SWORDS DICE + TWO_SWORDS CARD
        game.setFortuneCard(Card.TWO_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.MONKEY, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals(Config.TWO_SWORDS_BONUS, game.calculateScore());

        //1 SWORDS DIE + 1 COIN DIE + 1 DIAMOND DIE + TWO_SWORDS CARD
        game.setFortuneCard(Card.TWO_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SWORD, Dice.SKULL, Dice.MONKEY, Dice.MONKEY, Dice.COIN, Dice.DIAMOND, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals(-Config.TWO_SWORDS_BONUS, game.calculateScore());

        //4 SWORDS DICE + THREE_SWORDS CARD
        game.setFortuneCard(Card.THREE_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.THREE_SWORDS_BONUS + Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());


        //3 SWORDS DICE + THREE_SWORDS CARD
        game.setFortuneCard(Card.THREE_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.MONKEY, Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.THREE_SWORDS_BONUS + Config.THREE_OF_A_KIND_SCORE), game.calculateScore());

        //2 SWORDS DICE + 1 COIN DIE + 1 DIAMOND DIE + THREE_SWORDS CARD
        game.setFortuneCard(Card.THREE_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.MONKEY, Dice.MONKEY, Dice.COIN, Dice.DIAMOND, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals(- Config.THREE_SWORDS_BONUS, game.calculateScore());

        //5 SWORDS DICE + FOUR_SWORDS CARD
        game.setFortuneCard(Card.FOUR_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SKULL, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.FOUR_SWORDS_BONUS + Config.FIVE_OF_A_KIND_SCORE), game.calculateScore());


        //4 SWORDS DICE + FOUR_SWORDS CARD
        game.setFortuneCard(Card.FOUR_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals((Config.FOUR_SWORDS_BONUS + Config.FOUR_OF_A_KIND_SCORE), game.calculateScore());

        //3 SWORDS DICE + 1 COIN DIE + 1 DIAMOND DIE + FOUR_SWORDS CARD
        game.setFortuneCard(Card.FOUR_SWORDS);
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.SWORD, Dice.SWORD, Dice.SWORD, Dice.MONKEY, Dice.COIN, Dice.DIAMOND, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals(- Config.FOUR_SWORDS_BONUS, game.calculateScore());
    }

    @Test
    void testTreasureChest(){
        game.drawFortuneCard();
        game.rollDice();
        game.setFortuneCard(Card.CHEST);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.DIAMOND, Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);


        ArrayList <Integer> diceToReroll = new ArrayList<>(Arrays.asList(4,5,6,7));
        game.setDiceToReroll(diceToReroll);;


        ArrayList <Integer> diceInTreasureChest = new ArrayList<>(Arrays.asList(0,1,2,3));
        game.setDiceInTreasureChest(diceInTreasureChest);

        game.rerollDice();

        //3 SKULLS
        dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.DIAMOND, Dice.SKULL, Dice.SKULL, Dice.SKULL, Dice.PARROT));
        game.setDice(dice);

        assertEquals((Config.COIN_BONUS*3) + Config.DIAMOND_BONUS + (Config.THREE_OF_A_KIND_SCORE),game.calculateScore());
    }

    @Test
    void testSkullIslandDeductions(){
        game.drawFortuneCard();
        game.rollDice();

        //2 SKULLS DICE + TWO_SKULLS CARD
        game.setFortuneCard(Card.TWO_SKULLS);
        ArrayList<Game.Dice> dice = new ArrayList <Game.Dice>(Arrays.asList(Dice.COIN, Dice.COIN, Dice.COIN, Dice.DIAMOND, Dice.SKULL, Dice.SKULL, Dice.PARROT, Dice.PARROT));
        game.setDice(dice);
        assertEquals(-(Config.SKULL_DEDUCTION*4), game.getSkullIslandDeduction());

        //2 SKULLS DICE + ONE_SKULL CARD
        game.setFortuneCard(Card.ONE_SKULL);
        assertEquals(-(Config.SKULL_DEDUCTION*3), game.getSkullIslandDeduction());

        //2 SKULLS DICE + COIN CARD
        game.setFortuneCard(Card.COIN);
        assertEquals(-(Config.SKULL_DEDUCTION*2), game.getSkullIslandDeduction());

        //2 SKULLS DICE + CAPTAIN CARD
        game.setFortuneCard(Card.CAPTAIN);
        assertEquals(-(Config.SKULL_DEDUCTION*2)*Config.CAPTAIN_MULTIPLIER, game.getSkullIslandDeduction());
    }

    @Test
    void testWinners(){
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        Player[] players = {player1, player2, player3};
        ArrayList <String> winners = new ArrayList<>();

        //Only player 1 wins
        player1.setScore(Config.WIN_THRESHOLD+2);
        player2.setScore(Config.WIN_THRESHOLD+1);
        player3.setScore(Config.WIN_THRESHOLD);
        winners.add(player1.getName());
        assertEquals(winners, game.getWinners(players));

        //Only player 2 wins
        winners.clear();
        player1.setScore(Config.WIN_THRESHOLD);
        player2.setScore(Config.WIN_THRESHOLD+2);
        player3.setScore(Config.WIN_THRESHOLD+1);
        winners.add(player2.getName());
        assertEquals(winners, game.getWinners(players));

        //Only player 3 wins
        winners.clear();
        player1.setScore(Config.WIN_THRESHOLD);
        player2.setScore(Config.WIN_THRESHOLD+1);
        player3.setScore(Config.WIN_THRESHOLD+2);
        winners.add(player3.getName());
        assertEquals(winners, game.getWinners(players));

        //Player 1 and Player 2 win
        winners.clear();
        player1.setScore(Config.WIN_THRESHOLD+1);
        player2.setScore(Config.WIN_THRESHOLD+1);
        player3.setScore(Config.WIN_THRESHOLD);
        winners.addAll(Arrays.asList(player1.getName(), player2.getName()));
        assertEquals(winners, game.getWinners(players));

        //Player 1 and Player 3 win
        winners.clear();
        player1.setScore(Config.WIN_THRESHOLD+1);
        player2.setScore(Config.WIN_THRESHOLD);
        player3.setScore(Config.WIN_THRESHOLD+1);
        winners.addAll(Arrays.asList(player1.getName(), player3.getName()));
        assertEquals(winners, game.getWinners(players));

        //Player 2 and Player 3 win
        winners.clear();
        player1.setScore(Config.WIN_THRESHOLD);
        player2.setScore(Config.WIN_THRESHOLD+1);
        player3.setScore(Config.WIN_THRESHOLD+1);
        winners.addAll(Arrays.asList(player2.getName(), player3.getName()));
        assertEquals(winners, game.getWinners(players));

        //Player 1, Player 2 and Player 3 win
        winners.clear();
        player1.setScore(Config.WIN_THRESHOLD);
        player2.setScore(Config.WIN_THRESHOLD);
        player3.setScore(Config.WIN_THRESHOLD);
        winners.addAll(Arrays.asList(player1.getName(), player2.getName(), player3.getName()));
        assertEquals(winners, game.getWinners(players));
    }

}
