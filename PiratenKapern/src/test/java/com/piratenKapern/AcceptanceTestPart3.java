package com.piratenKapern;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AcceptanceTestPart3 {
    Game game = new Game();

    @Test
    public void testRow132(){
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
    public void testRow140(){
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
    public void testRow145(){
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
    public void testRow150(){
        GameTestServer testServer = new GameTestServer(4);
        testServer.acceptConnections();
        testServer.playRound(testServer.serverThreads[0], testServer.players[0]);
        testServer.playRound(testServer.serverThreads[1], testServer.players[1]);
        assertEquals(300,testServer.getPlayers()[0].getScore());
        assertEquals(0, testServer.getPlayers()[1].getScore());
        assertEquals(0, testServer.getPlayers()[2].getScore());
    }

}