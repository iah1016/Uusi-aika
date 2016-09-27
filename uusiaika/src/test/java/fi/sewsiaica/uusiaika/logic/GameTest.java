/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
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

    private MockRandom random;
    private Game game;
    private Conversion conv;

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
        String[] professions = {"a", "b", "c", "d"};

        random = new MockRandom();
        game = new Game(random, namesForVillagers,
                professions);
        String[] names = {"AA", "AB"};
        game.initGame(names);
        conv = game.getConversion();
        conv.setConvMaxNumberOfPersuasions(17);
        conv.setConvMaxNumberOfSermons(11);
        conv.setConvMaxNumberOfAccusations(19);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void villagersAreProperlyInitiated() {
        ArrayList<Villager> villagers = game.getVillagers();
        int quantity = villagers.size();

        StringBuilder resultSB = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            resultSB.append(villagers.get(i).getName());
            resultSB.append(villagers.get(i).getProfession());
        }
        StringBuilder expectedSB = new StringBuilder();
        int numberOfAppends = quantity / 4 + 1;
        for (int i = 0; i < numberOfAppends; i++) {
            expectedSB.append("AaBbCcDd");
        }
        int ceiling = villagers.size() * 2;
        String expected = expectedSB.toString().substring(0, ceiling);

        assertEquals(expected, resultSB.toString());
    }

    @Test
    public void initGameCreatesPlayerAndSectCorrectly() {
        String[] namesForVillagers = {"A", "B", "C", "D"};
        String[] professions = {"a", "b", "c", "d", "e"};

        Game game2 = new Game(random, namesForVillagers,
                professions);
        String[] names = {"AR", "BX"};
        game2.initGame(names);
        String res = game2.getPlayer().getName() + game2.getSect().getName();
        assertEquals("ARBX", res);
    }
    
    @Test
    public void initGameReturnsTrueIfArrayLengthIsTwo() {
        String[] fooArray = {"one", "two"};
        assertEquals(true, game.initGame(fooArray));
    }
    
    @Test
    public void initGameReturnsFalseIfArrayLengthIsNotTwo() {
        String[] fooArray = {"one"};
        assertEquals(false, game.initGame(fooArray));
    }

    @Test
    public void conversionDoesNotWorkWithOptionAIfAlreadyMaxedTries() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(17);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
        villager.setSelfAwareness(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, 'a');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionBIfAlreadyMaxedTries() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(11);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, 'b');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionCIfAlreadyMaxedTries() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(19);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, 'c');
        assertEquals(false, result);
    }

    @Test
    public void conversionWorksWithOptionA() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(100);
        villager.setSelfAwareness(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, 'a');
        assertEquals(true, result);
    }

    @Test
    public void conversionWorksWithOptionB() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(100);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, 'b');
        assertEquals(true, result);
    }

    @Test
    public void conversionWorksWithOptionC() {
        Villager villager = game.getVillagers().get(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, 'c');
        assertEquals(true, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionAIfMaxIsZero() {
        Villager villager = game.getVillagers().get(0);
        conv.setConvMaxNumberOfPersuasions(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(100);
        villager.setSelfAwareness(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, 'a');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionBIfMaxIsZero() {
        Villager villager = game.getVillagers().get(0);
        conv.setConvMaxNumberOfSermons(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(100);
        villager.setScepticism(7);
        game.getPlayer().setCharisma(100);
        boolean result = game.conversion(villager, 'b');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionCIfMaxIsZero() {
        Villager villager = game.getVillagers().get(0);
        conv.setConvMaxNumberOfAccusations(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(0);
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
