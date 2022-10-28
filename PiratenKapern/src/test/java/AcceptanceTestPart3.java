import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AcceptanceTestPart3 {
    Game game = new Game();

    @Test
    public void testRow130To133(){
        GameTestServer testServer = new GameTestServer(1);
        testServer.acceptConnections();
        testServer.gameLoop();
        assertEquals(4000,testServer.getPlayers()[0].getScore());
        assertEquals(2000, testServer.getPlayers()[1].getScore());
        assertEquals(0, testServer.getPlayers()[2].getScore());
        ArrayList<String> winners = new ArrayList<String>(Arrays.asList(testServer.getPlayers()[0].getName()));
        assertEquals(winners, game.getWinners(testServer.getPlayers()));
    }

    @Test
    public void testRow134To140(){
        GameTestServer testServer = new GameTestServer(2);
        testServer.acceptConnections();
        testServer.gameLoop();
        assertEquals(3800,testServer.getPlayers()[0].getScore());
        assertEquals(0, testServer.getPlayers()[1].getScore());
        assertEquals(0, testServer.getPlayers()[2].getScore());
        ArrayList<String> winners = new ArrayList<String>(Arrays.asList(testServer.getPlayers()[0].getName()));
        assertEquals(winners, game.getWinners(testServer.getPlayers()));
    }

    @Test
    public void testRow142To145(){
        GameTestServer testServer = new GameTestServer(3);
        testServer.acceptConnections();
        testServer.gameLoop();
        assertEquals(9000,testServer.getPlayers()[0].getScore());
        assertEquals(4000, testServer.getPlayers()[1].getScore());
        assertEquals(0, testServer.getPlayers()[2].getScore());
        ArrayList<String> winners = new ArrayList<String>(Arrays.asList(testServer.getPlayers()[0].getName()));
        assertEquals(winners, game.getWinners(testServer.getPlayers()));
    }

    @Test
    public void testRow147To150(){
        GameTestServer testServer = new GameTestServer(4);
        testServer.acceptConnections();
        testServer.gameLoop();
        assertEquals(300,testServer.getPlayers()[0].getScore());
        assertEquals(0, testServer.getPlayers()[1].getScore());
        assertEquals(0, testServer.getPlayers()[2].getScore());
    }

}