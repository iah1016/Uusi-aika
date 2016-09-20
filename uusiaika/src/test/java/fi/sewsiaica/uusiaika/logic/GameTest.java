/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Villager;
import java.util.ArrayList;
import java.util.Random;
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

    private Random random;
    private Game game;

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
        // Later from a file
        String[] namesForVillagers = {"A", "B", "C", "D"};
        String[] professions = {"a", "b", "c", "d", "e"};
        int[] maxNumbersForConversion = {3, 2, 2};
        
        random = new Random();
        game = new Game(random, namesForVillagers,
                professions, maxNumbersForConversion);
        String[] names = {"AA", "AB"};
        game.initGame(names);
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
        int[] maxNumbersForConversion = {3, 2, 2};
        
        Game game2 = new Game(random, namesForVillagers,
                professions, maxNumbersForConversion);
        String[] names = {"AA", "AB"};
        game2.initGame(names);
        String res = game2.getPlayer().getName() + game2.getSect().getName();
        String expected = "AAAB";
        assertEquals(expected, res);
    }
}
