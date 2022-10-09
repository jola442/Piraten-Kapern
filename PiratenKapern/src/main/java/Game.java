import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;

public class Game {
    private int numCards;



    private EnumMap<Card, Integer> fortuneCards;

    public enum Card {
        CHEST, SORCERESS, CAPTAIN, MONKEY_AND_PARROT, DIAMOND, COIN, TWO_SKULLS, ONE_SKULL, TWO_SWORDS, THREE_SWORDS, FOUR_SWORDS;
    }

    public Game(){
        fortuneCards = new EnumMap<Card, Integer>(Card.class);
        restockCards();
    }

    public int getNumCards() {
        return numCards;
    }

    public EnumMap<Card, Integer> getFortuneCards() {
        return fortuneCards;
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

}