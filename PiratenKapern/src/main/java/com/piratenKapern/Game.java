package com.piratenKapern;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;

public class Game{
    private int numCards;

    private ArrayList<Dice> dice;
    private int numMonkeyDice;
    private int numParrotDice;
    private int numSwordDice;
    private int numSkullDice;
    private int numCoinDice;
    private int numDiamondDice;

    private ArrayList<Integer> diceToReroll;
    private ArrayList<Integer> diceInTreasureChest;
    private EnumMap<Card, Integer> fortuneCards;

    private Card fortuneCard;


    public enum Card{
        CHEST, SORCERESS, CAPTAIN, MONKEY_AND_PARROT, DIAMOND, COIN, TWO_SKULLS, ONE_SKULL, TWO_SWORDS, THREE_SWORDS, FOUR_SWORDS;
    }

    public enum Dice{
        MONKEY, PARROT, SWORD, SKULL, COIN, DIAMOND
    }


    public Game(){
        fortuneCards = new EnumMap<Card, Integer>(Card.class);
        fortuneCard = null;
        diceToReroll = new ArrayList<>();
        diceInTreasureChest = new ArrayList<>();

        dice = new ArrayList<Dice>();
        for(int i = 0; i < Config.NUM_OF_DICE; i++){
            dice.add(null);
        }
        restockCards();
    }

    public ArrayList<Dice> getDice() {
        return dice;
    }

    public int getNumMonkeyDice() {
        return numMonkeyDice;
    }

    public int getNumParrotDice() {
        return numParrotDice;
    }

    public int getNumSwordDice() {
        return numSwordDice;
    }

    public int getNumSkullDice() {
        return numSkullDice;
    }

    public int getNumCoinDice() {
        return numCoinDice;
    }

    public int getNumDiamondDice() {
        return numDiamondDice;
    }
    public int getNumCards() {
        return numCards;
    }
    public ArrayList<Integer> getDiceInTreasureChest() {
        return diceInTreasureChest;
    }



    public EnumMap<Card, Integer> getFortuneCards() {
        return fortuneCards;
    }

    public Card getFortuneCard() {
        return fortuneCard;
    }

    public void setFortuneCard(Card fortuneCard) {
        this.fortuneCard = fortuneCard;
    }

    public void setDice(ArrayList<Dice> dice) {
        this.dice = dice;
    }

    public void setDiceToReroll(ArrayList<Integer> diceToReroll) {
        this.diceToReroll = diceToReroll;
    }


    public void setDiceInTreasureChest(ArrayList<Integer > diceInTreasureChest) {
        this.diceInTreasureChest = diceInTreasureChest;
    }

    public Card drawFortuneCard(){
        Random rand = new Random();

        //Creating an arraylist that will hold remaining fortune cards
        ArrayList<Card> cards = new ArrayList<>();
        for(Card card: fortuneCards.keySet()){
            for(int i = 0; i < fortuneCards.get(card); i++){
                cards.add(card);
            }
        }

        int max = cards.size()-1;
        int min = 0;

        //Generating a random card
        int index = rand.nextInt((max-min) + 1) + min;
        Card card = cards.get(index);

        fortuneCards.put(card, fortuneCards.get(card)-1);

        numCards -= 1;
        if(numCards == 0){
            restockCards();
        }

        fortuneCard = card;
        return card;
    }

    public void restockCards(){
        numCards = Config.NUM_OF_CARDS;
        fortuneCards.put(Card.CHEST, Config.NUM_OF_CHEST_CARDS);
        fortuneCards.put(Card.SORCERESS, Config.NUM_OF_SORCERESS_CARDS);
        fortuneCards.put(Card.CAPTAIN, Config.NUM_OF_CAPTAIN_CARDS);
        fortuneCards.put(Card.MONKEY_AND_PARROT, Config.NUM_OF_MONKEY_AND_PARROT_CARDS);
        fortuneCards.put(Card.DIAMOND, Config.NUM_OF_DIAMOND_CARDS);
        fortuneCards.put(Card.COIN, Config.NUM_OF_COIN_CARDS);
        fortuneCards.put(Card.TWO_SKULLS, Config.NUM_OF_TWO_SKULLS_CARDS);
        fortuneCards.put(Card.ONE_SKULL, Config.NUM_OF_ONE_SKULL_CARDS);
        fortuneCards.put(Card.TWO_SWORDS, Config.NUM_OF_TWO_SWORDS_CARDS);
        fortuneCards.put(Card.THREE_SWORDS, Config.NUM_OF_THREE_SWORDS_CARDS);
        fortuneCards.put(Card.FOUR_SWORDS, Config.NUM_OF_FOUR_SWORDS_CARDS);
    }

    public ArrayList<Dice> rollDice(){
        Random rand =  new Random();
        for(int i = 0; i < Config.NUM_OF_DICE; i++){
            int index = rand.nextInt(Dice.values().length);
            dice.set(i,Dice.values()[index]);
        }
        countDice();
        return dice;
    }

    public void countDice(){
        numMonkeyDice = 0;
        numParrotDice = 0;
        numSwordDice = 0;
        numSkullDice = 0;
        numCoinDice = 0;
        numDiamondDice = 0;
        for(int i = 0; i < dice.size(); i++){
            if(dice.get(i) == Dice.MONKEY){
                numMonkeyDice++;
            }

            else if(dice.get(i) == Dice.PARROT){
                numParrotDice++;
            }

            else if(dice.get(i) == Dice.SWORD){
                numSwordDice++;
            }


            else if(dice.get(i) == Dice.SKULL){
                numSkullDice++;
            }

            else if(dice.get(i) == Dice.COIN){
                numCoinDice++;
            }

            else{
                numDiamondDice++;
            }
        }
    }

    public int calculateScore(){
        int score = 0;
        int numScoringDice = 0;
        countDice();

        if(numSkullDice >= 3){
            //If the fortune card isn't a chest or an X_SWORDS card, 3 skulls means 0 points
            if(fortuneCard != Card.CHEST && fortuneCard != Card.TWO_SWORDS && fortuneCard != Card.THREE_SWORDS && fortuneCard != Card.FOUR_SWORDS){
                return 0;
            }

            else {
                if (fortuneCard == Card.CHEST) {
                    int numChestCoinDice = 0;
                    int numChestDiamondDice = 0;
                    int numChestSwordDice = 0;
                    int numChestMonkeyDice = 0;
                    int numChestParrotDice = 0;

                    for (int j = 0; j < diceInTreasureChest.size(); j++) {
                        int chestDieIndex = diceInTreasureChest.get(j);
                        if (dice.get(chestDieIndex) == Dice.COIN)
                            numChestCoinDice++;
                        else if (dice.get(chestDieIndex) == Dice.DIAMOND)
                            numChestDiamondDice++;
                        else if (dice.get(chestDieIndex) == Dice.SWORD)
                            numChestSwordDice++;
                        else if (dice.get(chestDieIndex) == Dice.MONKEY)
                            numChestMonkeyDice++;
                        else if (dice.get(chestDieIndex) == Dice.PARROT)
                            numChestParrotDice++;
                    }


                    score += Config.COIN_BONUS * numChestCoinDice;
                    score += Config.COIN_BONUS * numChestDiamondDice;

                    if (numChestCoinDice == 5 || numChestDiamondDice == 5 || numChestMonkeyDice == 5 || numChestParrotDice == 5 || numChestSwordDice == 5) {
                        score += Config.FIVE_OF_A_KIND_SCORE;
                    }

                    if (numChestCoinDice == 4 || numChestDiamondDice == 4 || numChestMonkeyDice == 4 || numChestParrotDice == 4 || numChestSwordDice == 4) {
                        score += Config.FOUR_OF_A_KIND_SCORE;
                    }

                    if (numChestCoinDice == 3 || numChestDiamondDice == 3 || numChestMonkeyDice == 3 || numChestParrotDice == 3 || numChestSwordDice == 3) {
                        score += Config.THREE_OF_A_KIND_SCORE;
                    }

                    return score;
                }

                else if(fortuneCard == Card.TWO_SWORDS){
                    score -= Config.TWO_SWORDS_BONUS;
                }

                else if(fortuneCard == Card.THREE_SWORDS){
                    score -= Config.THREE_SWORDS_BONUS;
                }

                else if(fortuneCard == Card.FOUR_SWORDS){
                    score -= Config.FOUR_SWORDS_BONUS;
                }
                return score;
            }

        }

        //0 points for 3 skulls including card
        if(numSkullDice == 1 & fortuneCard == Card.TWO_SKULLS || numSkullDice == 2 & fortuneCard == Card.ONE_SKULL){
            return 0;
        }

        //Add coin bonus for coin fortune card
        if(fortuneCard == Card.COIN){
            score += Config.COIN_BONUS;
        }

        //Add face value bonus for diamond card
        else if(fortuneCard == Card.DIAMOND){
            score += Config.DIAMOND_BONUS;
        }


        else if(fortuneCard == Card.TWO_SWORDS){
            if(numSwordDice >= 2){
                score += Config.TWO_SWORDS_BONUS;
            }

            else{
                score -= Config.TWO_SWORDS_BONUS;
                return score;
            }
        }

        else if(fortuneCard == Card.THREE_SWORDS){
            if(numSwordDice >= 3){
                score += Config.THREE_SWORDS_BONUS;
            }

            else{
                score -= Config.THREE_SWORDS_BONUS;
                return score;
            }
        }

        else if(fortuneCard == Card.FOUR_SWORDS){
            if(numSwordDice >= 4){
                score += Config.FOUR_SWORDS_BONUS;
            }

            else{
                score -= Config.FOUR_SWORDS_BONUS;
                return score;
            }
        }

        //Add face value bonus for coin dice
        if(numCoinDice >= 1){
            score += Config.COIN_BONUS * numCoinDice;
            numScoringDice += numCoinDice;
        }

        //Add face value bonus for diamond dice
        if(numDiamondDice >= 1){
            score += Config.DIAMOND_BONUS * numDiamondDice;
            numScoringDice += numDiamondDice;
        }

        if(numSwordDice >= 3){
            numScoringDice += numSwordDice;
        }


        //Eight of a kind excluding monkey and parrot dice
        if(numSwordDice == 8){
            score += Config.EIGHT_OF_A_KIND_SCORE;
        }

        //Checking for X of a Kind with sword dice

        else if(numSwordDice == 7){
            score += Config.SEVEN_OF_A_KIND_SCORE;
        }

        else if(numSwordDice == 6){
            score += Config.SIX_OF_A_KIND_SCORE;
        }

        else if(numSwordDice == 5){
            score += Config.FIVE_OF_A_KIND_SCORE;
        }

        else if(numSwordDice == 4){
            score += Config.FOUR_OF_A_KIND_SCORE;
        }

        else if(numSwordDice == 3){
            score += Config.THREE_OF_A_KIND_SCORE;
        }

        //Checking for X of a Kind with diamond dice and diamond card

        if(numDiamondDice == 8){
            score+= Config.EIGHT_OF_A_KIND_SCORE;
        }

        else if(numDiamondDice == 7) {
            if (fortuneCard == Card.DIAMOND) {
                score += Config.EIGHT_OF_A_KIND_SCORE;
            } else {
                score += Config.SEVEN_OF_A_KIND_SCORE;
            }
        }

        else if(numDiamondDice == 6){
            if(fortuneCard == Card.DIAMOND){
                score+= Config.SEVEN_OF_A_KIND_SCORE;
            }

            else{
                score += Config.SIX_OF_A_KIND_SCORE;
            }
        }

        else if(numDiamondDice == 5){
            if(fortuneCard == Card.DIAMOND){
                score+= Config.SIX_OF_A_KIND_SCORE;
            }

            else{
                score += Config.FIVE_OF_A_KIND_SCORE;
            }
        }

        else if(numDiamondDice == 4){
            if(fortuneCard == Card.DIAMOND){
                score+= Config.FIVE_OF_A_KIND_SCORE;
            }

            else{
                score += Config.FOUR_OF_A_KIND_SCORE;
            }
        }

        else if(numDiamondDice == 3){
            if(fortuneCard == Card.DIAMOND){
                score+= Config.FOUR_OF_A_KIND_SCORE;
            }

            else{
                score += Config.THREE_OF_A_KIND_SCORE;
            }
        }

        else if(numDiamondDice == 2 && fortuneCard == Card.DIAMOND){score+= Config.THREE_OF_A_KIND_SCORE;}

        //Checking for X of a Kind with coin dice and coin card
        if(numCoinDice == 8){
            score += Config.EIGHT_OF_A_KIND_SCORE;
        }

        else if(numCoinDice == 7) {
            if (fortuneCard == Card.COIN) {
                score += Config.EIGHT_OF_A_KIND_SCORE;
            } else {
                score += Config.SEVEN_OF_A_KIND_SCORE;
            }
        }

        else if(numCoinDice == 6){
            if(fortuneCard == Card.COIN){
                score+= Config.SEVEN_OF_A_KIND_SCORE;
            }

            else{
                score += Config.SIX_OF_A_KIND_SCORE;
            }
        }

        else if(numCoinDice == 5){
            if(fortuneCard == Card.COIN){
                score+= Config.SIX_OF_A_KIND_SCORE;
            }

            else{
                score += Config.FIVE_OF_A_KIND_SCORE;
            }
        }

        else if(numCoinDice == 4){
            if(fortuneCard == Card.COIN){
                score+= Config.FIVE_OF_A_KIND_SCORE;
            }

            else{
                score += Config.FOUR_OF_A_KIND_SCORE;
            }
        }

        else if(numCoinDice == 3){
            if(fortuneCard == Card.COIN){
                score+= Config.FOUR_OF_A_KIND_SCORE;
            }

            else{
                score += Config.THREE_OF_A_KIND_SCORE;
            }
        }

        else if(numCoinDice == 2 && fortuneCard == Card.COIN){score+= Config.THREE_OF_A_KIND_SCORE;}


        //Checking for X of a kind for monkey and parrot dice with the monkey and parrot card
        if(fortuneCard == Card.MONKEY_AND_PARROT){
            if(numMonkeyDice + numParrotDice >= 3){
                numScoringDice += (numMonkeyDice + numParrotDice);
            }
            if(numMonkeyDice + numParrotDice == 3){
                score += Config.THREE_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice + numParrotDice == 4){
                score += Config.FOUR_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice + numParrotDice == 5){
                score += Config.FIVE_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice + numParrotDice == 6){
                score += Config.SIX_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice + numParrotDice == 7){
                score += Config.SEVEN_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice + numParrotDice == 8){
                score += Config.EIGHT_OF_A_KIND_SCORE;
            }
        }

        else{
            if(numMonkeyDice >= 3){
                numScoringDice += numMonkeyDice;
            }

            if(numParrotDice >= 3){
                numScoringDice += numParrotDice;
            }

            //Checking for X of a kind with monkey dice
            if(numMonkeyDice == 3){
                score += Config.THREE_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice == 4){
                score += Config.FOUR_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice == 5){
                score += Config.FIVE_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice == 6){
                score += Config.SIX_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice == 7){
                score += Config.SEVEN_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice == 8){
                score += Config.EIGHT_OF_A_KIND_SCORE;
            }

            //Checking for X of a kind with parrot dice
            if(numParrotDice == 3){
                score += Config.THREE_OF_A_KIND_SCORE;
            }

            else if(numParrotDice == 4){
                score += Config.FOUR_OF_A_KIND_SCORE;
            }

            else if(numParrotDice == 5){
                score += Config.FIVE_OF_A_KIND_SCORE;
            }

            else if(numParrotDice == 6){
                score += Config.SIX_OF_A_KIND_SCORE;
            }

            else if(numParrotDice == 7){
                score += Config.SEVEN_OF_A_KIND_SCORE;
            }

            else if(numParrotDice == 8){
                score += Config.EIGHT_OF_A_KIND_SCORE;
            }
        }

        //Edge case where having only two swords contributes to scoring dice
        if(numScoringDice < 8){
            if(fortuneCard == Card.TWO_SWORDS){
                numScoringDice += 2;
            }
        }
        if(numScoringDice == 8){
            score += Config.FULL_CHEST_BONUS;
        }

        if(fortuneCard == Card.CAPTAIN){
            score *= Config.CAPTAIN_MULTIPLIER;
        }


        return score;

    }

    public ArrayList<Dice> rerollDice(){
        Random rand =  new Random();
        for(int i = 0; i < diceToReroll.size(); i++){
            int index = rand.nextInt(Dice.values().length);
            dice.set(diceToReroll.get(i),Dice.values()[index]);
        }
        countDice();
        return dice;
    }

    public int getSkullIslandDeduction(){
        countDice();
        int deduction = 0;

        deduction -= numSkullDice * Config.SKULL_DEDUCTION;

        if(fortuneCard == Card.ONE_SKULL){
            deduction -= Config.SKULL_DEDUCTION;
        }

        else if(fortuneCard == Card.TWO_SKULLS){
            deduction -= Config.SKULL_DEDUCTION*2;
        }

        else if(fortuneCard == Card.CAPTAIN){
            deduction *= Config.CAPTAIN_MULTIPLIER;
        }

        return deduction;
    }

    public ArrayList<String> getWinners(Player[] players) {
        int max = 0;
        for(int i = 0; i < players.length; i++){
            if(players[i].getScore() > max){
                max = players[i].getScore();
            }
        }

        if(max < Config.WIN_THRESHOLD){
            return null;
        }

        ArrayList <String> winners = new ArrayList<>();
        for(int i = 0; i < players.length; i++){
            if(players[i].getScore() == max){
                winners.add(players[i].getName());
            }
        }
        return winners;
    }


}
