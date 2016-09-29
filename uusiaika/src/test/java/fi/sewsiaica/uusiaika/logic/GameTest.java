/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.logic.conversion.Conversion;
import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.toolsfortests.MockConfig;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private MockConfig config;
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
        random = new MockRandom();
        config = new MockConfig();
        Map<String, Integer> intValues = new HashMap<>();
        String[] namesForVillagers = {"A", "B", "C", "D"};
        String[] professions = {"a", "b", "c", "d"};
        config.setIntValues(intValues);
        config.setVilNames(Arrays.asList(namesForVillagers));
        config.setProfessions(Arrays.asList(professions));
        
        game = new Game(random, config);
        String[] names = {"AA", "AB"};
        game.createPlayerAndSect(names);
        game.getPersuasion().setMaxNumberOfConversions(17);
        game.getSermon().setMaxNumberOfConversions(11);
        game.getAccusation().setMaxNumberOfConversions(19);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void villagersAreProperlyInitiated() {
        List<Villager> villagers = game.getVillagers();
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
    public void createPlayerAndSectCreatesPlayerAndSectCorrectly() {
        String[] names = {"AR", "BX"};
        game.createPlayerAndSect(names);
        String res = game.getPlayer().getName() + game.getSect().getName();
        assertEquals("ARBX", res);
    }

    @Test
    public void createPlayerAndSectReturnsTrueIfArrayLengthIsTwo() {
        String[] fooArray = {"one", "two"};
        assertEquals(true, game.createPlayerAndSect(fooArray));
    }
    
    @Test
    public void createPlayerAndSectReturnsFalseIfArrayLengthIsNotTwo() {
        String[] fooArray = {"one"};
        assertEquals(false, game.createPlayerAndSect(fooArray));
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
        game.getPersuasion().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(1);
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
        game.getSermon().setMaxNumberOfConversions(0);
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
        game.getAccusation().setMaxNumberOfConversions(0);
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
        List<Villager> congregation = new ArrayList<Villager>();
        congregation.add(new Villager("A", true, 0, 0, 0, 0, "B"));
        game.getSect().setCongregation(congregation);
        boolean result = game.templeActions('a');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionAIfMemberListIsEmpty() {
        List<Villager> congregation = new ArrayList<Villager>();
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
