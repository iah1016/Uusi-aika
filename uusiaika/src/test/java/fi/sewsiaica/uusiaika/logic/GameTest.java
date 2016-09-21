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
        assertEquals("AAAB", res);
    }
    
    @Test
    public void conversionWorksWithOptionA() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(20);
        villager.setNumberOfAccusations(20);
        villager.setSelfAwareness(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, "a");
        assertEquals(true, result);
    }
    
    @Test
    public void conversionDoesNotWorkWithOptionAIfAlreadyMaxedTries() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(3);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
        villager.setSelfAwareness(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, "a");
        assertEquals(false, result);
    }
    
    @Test
    public void conversionWorksWithOptionB() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(20);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(20);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, "b");
        assertEquals(true, result);
    }
    
    @Test
    public void conversionDoesNotWorkWithOptionBIfAlreadyMaxedTries() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(2);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, "b");
        assertEquals(false, result);
    }
    
    @Test
    public void conversionWorksWithOptionC() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(20);
        villager.setNumberOfSermons(20);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, "c");
        assertEquals(true, result);
    }
    
    @Test
    public void conversionDoesNotWorkWithOptionCIfAlreadyMaxedTries() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(2);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, "c");
        assertEquals(false, result);
    }
    
    @Test
    public void conversionReturnsFalseIfOptionOtherThanABC() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        String option = "";
        boolean result = game.conversion(villager, option);
        assertEquals(false, result);
    }
    
}
