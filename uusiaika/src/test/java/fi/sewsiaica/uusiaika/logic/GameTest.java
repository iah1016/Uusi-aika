/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iah1016
 */
public class GameTest {

    private Game game;
    private Villager villager;

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String[] namesForVillagers = {"A", "B", "C", "D"};
        String[] professions = {"a", "b", "c", "d", "e"};
        game = new Game(namesForVillagers, professions);
        String[] names = {"AA", "AB"};
        game.initGame(names);
        villager = game.getVillagers().get(0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void villagersAreProperlyInitiated() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Villager> villagers = game.getVillagers();
        for (int i = 0; i < villagers.size(); i++) {
            sb.append(villagers.get(i).getName());
            sb.append(villagers.get(i).getProfession());
        }
        String expected = "AaBbCcDd";

        assertEquals(expected, sb.toString());
    }

    @Test
    public void initGameWorksAsExpected() {
        String[] namesForVillagers = {"A", "B", "C", "D"};
        String[] professions = {"a", "b", "c", "d", "e"};
        Game game2 = new Game(namesForVillagers, professions);
        String[] names = {"AA", "AB"};
        game2.initGame(names);
        String res = game2.getPlayer().getName() + game2.getSect().getName();
        String expected = "AAAB";
        assertEquals(expected, res);
    }

    @Test
    public void persuasionMaxTimes() {
        for (int i = 0; i < 11; i++) {
            game.persuasion(villager);
        }
        assertEquals(game.getMaxNoOfPs(), villager.getNoOfPersuations());
    }

    @Test
    public void sermonMaxTimes() {
        for (int i = 0; i < 11; i++) {
            game.sermon(villager);
        }
        assertEquals(game.getMaxNoOfSe(), villager.getNoOfSermons());
    }

    @Test
    public void accusationMaxTimes() {
        for (int i = 0; i < 11; i++) {
            game.accusation(villager);
        }
        assertEquals(game.getMaxNoOfAc(), villager.getNoOfAccusations());
    }

    @Test
    public void persuasionSuccessful() {
        game.getPlayer().setCharisma(1000);
        game.persuasion(villager);
        assertEquals(1002, game.getPlayer().getCharisma());
    }

    @Test
    public void persuasionNotSuccessful() {
        game.getPlayer().setCharisma(7);
        villager.setSelfAwareness(1000);
        game.persuasion(villager);
        assertEquals(7, game.getPlayer().getCharisma());
    }

    @Test
    public void sermonSuccessful() {
        game.getPlayer().setCharisma(1000);
        game.sermon(villager);
        assertEquals(1002, game.getPlayer().getCharisma());
    }

    @Test
    public void sermonNotSuccessful() {
        game.getPlayer().setCharisma(7);
        villager.setScepticism(1000);
        game.sermon(villager);
        assertEquals(7, game.getPlayer().getCharisma());
    }

    @Test
    public void accusationSuccessful() {
        game.getPlayer().setCharisma(1000);
        game.accusation(villager);
        assertEquals(1002, game.getPlayer().getCharisma());
    }

    @Test
    public void accusationNotSuccessful() {
        game.getPlayer().setCharisma(7);
        villager.setSelfEsteem(1000);
        game.accusation(villager);
        assertEquals(7, game.getPlayer().getCharisma());
    }
}
