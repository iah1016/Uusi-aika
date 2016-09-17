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
        String[] names = {"AA", "AB"};
        game.initGame(names);
        String res = game.getPlayer().getName() + game.getSect().getName();
        String expected = "AAAB";
        assertEquals(expected, res);
    }
}
