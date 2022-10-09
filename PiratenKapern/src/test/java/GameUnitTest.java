import org.junit.jupiter.api.Test;

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
}

