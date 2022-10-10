import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Random;

public class Game {
    private int numCards;

    private ArrayList<Dice> dice;
    private int numMonkeyDice;
    private int numParrotDice;
    private int numSwordDice;
    private int numSkullDice;
    private int numCoinDice;
    private int numDiamondDice;

    private EnumMap<Card, Integer> fortuneCards;

    private Card fortuneCard;
    public enum Card {
        CHEST, SORCERESS, CAPTAIN, MONKEY_AND_PARROT, DIAMOND, COIN, TWO_SKULLS, ONE_SKULL, TWO_SWORDS, THREE_SWORDS, FOUR_SWORDS;
    }

    public enum Dice {
        MONKEY, PARROT, SWORD, SKULL, COIN, DIAMOND
    }


    public Game(){
        fortuneCards = new EnumMap<Card, Integer>(Card.class);
        fortuneCard = null;
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
        countDice();

        //0 points for 3 skulls
        if(numSkullDice >= 3){
            return 0;
        }

        //0 points for 3 skulls including card
        if(numSkullDice == 1 & fortuneCard == Card.TWO_SKULLS || numSkullDice == 2 & fortuneCard == Card.ONE_SKULL){
            return 0;
        }

        //Add face value bonus for coins
        if(numCoinDice >= 1){
            if(fortuneCard == Card.COIN){
                score += Config.COIN_BONUS * (numCoinDice+1);
            }

            else{
                score += Config.COIN_BONUS * numCoinDice;
            }

        }

        //Add face value bonus for diamonds
        if(numDiamondDice >= 1){
            if(fortuneCard == Card.DIAMOND){
                score += Config.DIAMOND_BONUS * (numDiamondDice+1);
            }

            else{
                score += Config.DIAMOND_BONUS * numDiamondDice;
            }

        }

        //Five of a kind path excluding monkey and parrot dice
        if(numDiamondDice == 5 || (numDiamondDice == 4 && fortuneCard == Card.DIAMOND)||
                numCoinDice == 5 || (numCoinDice == 4 && fortuneCard == Card.COIN)||
                numSwordDice == 5){
            score += Config.FIVE_OF_A_KIND_SCORE;
        }

        //Four of a kind path excluding monkey and parrot dice
        else if(numDiamondDice == 4 || (numDiamondDice == 3 && fortuneCard == Card.DIAMOND)||
                numCoinDice == 4 || (numCoinDice == 3 && fortuneCard == Card.COIN)||
                numSwordDice == 4){
            score += Config.FOUR_OF_A_KIND_SCORE;
        }

        //Three of a kind path excluding monkey and parrot dice
        else if(numDiamondDice == 3 || (numDiamondDice == 2 && fortuneCard == Card.DIAMOND)||
                numCoinDice == 3 || (numCoinDice == 2 && fortuneCard == Card.COIN)||
                numSwordDice == 3){
            score += Config.THREE_OF_A_KIND_SCORE;
        }



        //Monkey and Parrot card path
        if(fortuneCard == Card.MONKEY_AND_PARROT){
            if(numMonkeyDice + numParrotDice == 3){
                score += Config.THREE_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice + numParrotDice == 4){
                score += Config.FOUR_OF_A_KIND_SCORE;
            }

            else if(numMonkeyDice + numParrotDice == 5){
                score += Config.FIVE_OF_A_KIND_SCORE;
            }
        }

        //Monkey and parrot dice path
        else if(numMonkeyDice == 3 || numParrotDice == 3){
            score += Config.THREE_OF_A_KIND_SCORE;
        }

        else if(numMonkeyDice == 4 || numParrotDice == 4){
            score += Config.FOUR_OF_A_KIND_SCORE;
        }

        else if(numMonkeyDice == 5 || numParrotDice == 5){
            score += Config.FIVE_OF_A_KIND_SCORE;
        }


        if(fortuneCard == Card.CAPTAIN){
            score *= Config.CAPTAIN_MULTIPLIER;
        }


        return score;

    }




}