import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameUnitTest {
    Game game = new Game();
    @Test
    void testDrawFortuneCard() {
        Game.Card fortuneCard = game.drawFortuneCard();

        if(fortuneCard == Game.Card.CHEST){
            assertEquals(game.getFortuneCards().get(Game.Card.CHEST),Config.NUM_OF_CHEST_CARDS-1);
        }

        else if(fortuneCard == Game.Card.SORCERESS){
            assertEquals(game.getFortuneCards().get(Game.Card.SORCERESS), Config.NUM_OF_SORCERESS_CARDS-1);
        }

        else if(fortuneCard == Game.Card.CAPTAIN){
            assertEquals(game.getFortuneCards().get(Game.Card.CAPTAIN), Config.NUM_OF_CAPTAIN_CARDS-1);
        }

        else if(fortuneCard == Game.Card.MONKEY_AND_PARROT){
            assertEquals(game.getFortuneCards().get(Game.Card.MONKEY_AND_PARROT), Config.NUM_OF_MONKEY_AND_PARROT_CARDS-1);
        }

        else if(fortuneCard == Game.Card.DIAMOND){
            assertEquals(game.getFortuneCards().get(Game.Card.DIAMOND), Config.NUM_OF_DIAMOND_CARDS-1);
        }

        else if(fortuneCard == Game.Card.COIN){
            assertEquals(game.getFortuneCards().get(Game.Card.COIN), Config.NUM_OF_COIN_CARDS-1);
        }

        else if(fortuneCard == Game.Card.TWO_SKULLS){
            assertEquals(game.getFortuneCards().get(Game.Card.TWO_SKULLS), Config.NUM_OF_TWO_SKULLS_CARDS-1);
        }

        else if(fortuneCard == Game.Card.ONE_SKULL){
            assertEquals(game.getFortuneCards().get(Game.Card.ONE_SKULL), Config.NUM_OF_ONE_SKULL_CARDS-1);
        }

        else if(fortuneCard == Game.Card.TWO_SWORDS){
            assertEquals(game.getFortuneCards().get(Game.Card.TWO_SWORDS), Config.NUM_OF_TWO_SWORDS_CARDS-1);
        }

        else if(fortuneCard == Game.Card.THREE_SWORDS){
            assertEquals(game.getFortuneCards().get(Game.Card.THREE_SWORDS), Config.NUM_OF_THREE_SWORDS_CARDS-1);
        }

        else if(fortuneCard == Game.Card.FOUR_SWORDS){
            assertEquals(game.getFortuneCards().get(Game.Card.FOUR_SWORDS), Config.NUM_OF_FOUR_SWORDS_CARDS-1);
        }

        assertEquals(game.getNumCards(), Config.NUM_OF_CARDS-1);


    }
}