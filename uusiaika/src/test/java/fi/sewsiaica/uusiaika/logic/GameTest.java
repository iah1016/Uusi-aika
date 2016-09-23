/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
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
    public void givenStringArrayIsCorrectLengthReturnsFalseWithNull() {
        String[] names = null;
        boolean result = game.givenStringArrayIsCorrectLength(names, 0);
        
        assertEquals(false, result);
    }
    
    @Test
    public void givenStringArrayIsCorrectLengthReturnsFalseIfLowerLength() {
        String[] names = {"A", "B", "C"};  
        boolean result = game.givenStringArrayIsCorrectLength(names, 4);
        assertEquals(false, result);
    }

    @Test
    public void givenStringArrayIsCorrectLengthReturnsFalseIfLargerLength() {
        String[] names = {"A", "B", "C"};  
        boolean result = game.givenStringArrayIsCorrectLength(names, 2);
        assertEquals(false, result);
    }
    
    @Test
    public void givenStringArrayIsCorrectLengthReturnsTrueIfEqualLength() {
        String[] names = {"A", "B", "C"};  
        boolean result = game.givenStringArrayIsCorrectLength(names, 3);
        assertEquals(true, result);
    }
    
    @Test
    public void initGameCreatesPlayerAndSectCorrectly() {
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
        boolean result = game.conversion(villager, 'a');
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
        boolean result = game.conversion(villager, 'a');
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
        boolean result = game.conversion(villager, 'b');
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
        boolean result = game.conversion(villager, 'b');
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
        boolean result = game.conversion(villager, 'c');
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
        boolean result = game.conversion(villager, 'c');
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

        boolean result = game.conversion(villager, ' ');
        assertEquals(false, result);
    }
    
    @Test
    public void templeActionsReturnsTrueWithOptionAIfMemberListIsNotEmpty() {
        ArrayList<Villager> congregation = new ArrayList<Villager>();
        congregation.add(new Villager("A", true, 0, 0, 0, 0, "B"));
        game.getSect().setCongregation(congregation);
        boolean result = game.templeActions('a');
        assertEquals(true, result);
    }
    
    @Test
    public void templeActionsReturnsFalseWithOptionAIfMemberListIsEmpty() {
        ArrayList<Villager> congregation = new ArrayList<Villager>();
        game.getSect().setCongregation(congregation);
        boolean result = game.templeActions('a');
        assertEquals(false, result);
    }
    
    @Test
    public void templeActionsReturnsFalseWithOptionAIfMemberListIsNull() {
        game.getSect().setCongregation(null);
        boolean result = game.templeActions('a');
        assertEquals(false, result);
    }
    
    @Test
    public void templeActionsReturnsTrueWithOptionBAndHighCharisma() {
        Player player = game.getPlayer();
        player.setCharisma(1000);
        boolean result = game.templeActions('b');
        assertEquals(true, result);
    }
    
    @Test
    public void templeActionsReturnsFalseWithOptionBAndLowCharisma() {
        Player player = game.getPlayer();
        player.setCharisma(3);
        boolean result = game.templeActions('b');
        assertEquals(false, result);
    }
    
    @Test
    public void templeActionsReturnsTrueWithOptionCAndHighBalance() {
        Sect sect = game.getSect();
        sect.setBalance(10000000);
        boolean result = game.templeActions('c');
        assertEquals(true, result);
    }
    
    @Test
    public void templeActionsReturnsFalseWithOptionCAndLowBalance() {
        Sect sect = game.getSect();
        sect.setBalance(10);
        boolean result = game.templeActions('c');
        assertEquals(false, result);
    }
    
    @Test
    public void templeActionsReturnsFalseWithInvalidOption() {
        boolean result = game.templeActions(' ');
        assertEquals(false, result);
    }
    
    @Test
    public void trainingCentreActionsReturnsFalseWithInvalidOption() {
        boolean result = game.trainingCentreActions(' ');
        assertEquals(false, result);
    }
    
    @Test
    public void trainingCentreActionsReturnsTrueWithOptionA() {
        boolean result = game.trainingCentreActions('a');
        assertEquals(true, result);
    }
    
    @Test
    public void trainingCentreActionsReturnsTrueWithOptionB() {
        boolean result = game.trainingCentreActions('b');
        assertEquals(true, result);
    }
    
}
