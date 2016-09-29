/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic.conversion;

import fi.sewsiaica.uusiaika.domain.Player;
import fi.sewsiaica.uusiaika.domain.Sect;
import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.util.HashMap;
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
public class PersuasionTest {

    private MockRandom random;
    private Map<String, Integer> intValues;
    private Conversion persuasion;
    private Player player;
    private Villager villager;
    private Sect sect;

    public PersuasionTest() {
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
        intValues = new HashMap<>();
        intValues.put("convMaxNumberOfPersuasions", 15);
        intValues.put("convPersPlayerBound", 20);
        intValues.put("convPersVilBound", 20);
        intValues.put("convPersPlayerCharIncr", 2);
        intValues.put("convPersVilSelfAwDecr", 5);
        intValues.put("convPersVilSceptDecr", 5);
        persuasion = new Persuasion(random, intValues, 15, 20, 20);
        // String name, int charisma, int argSkills
        player = new Player("Pekka", 10, 10);
        // String name, boolean inSect, int scepticism, int selfEsteem, 
        // int selfAwareness, int argSkills, String profession
        villager = new Villager("Matti", false, 10, 10, 10, 10, "kirkkoherra");
        sect = new Sect("Pekan lahko", 10000, 100, 10);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkIfAllowedToProceedReturnsTrueCorrectlyWithPersuasion() {
        villager.setNumberOfPersuations(0);

        boolean result = persuasion.checkIfAllowedToProceed(villager);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseCorrectlyWithPersuasion() {
        villager.setNumberOfPersuations(15);

        boolean result = persuasion.checkIfAllowedToProceed(villager);
        assertEquals(false, result);
    }

    @Test
    public void increaseAmountOfConvSucceedsWithPersuasion() {
        int expected = villager.getNumberOfPersuasions() + 1;
        persuasion.increaseAmountOfConv(villager);
        int result = villager.getNumberOfPersuasions();
        assertEquals(expected, result);
    }

    @Test
    public void convertIncreasesNumberOfPersuasions() {
        int expected = 1 + villager.getNumberOfPersuasions();
        persuasion.convert(player, villager, sect);
        int result = villager.getNumberOfPersuasions();
        assertEquals(expected, result);
    }

    @Test
    public void convertSucceedsAndCharismaIncreases() {
        player.setCharisma(100);
        villager.setSelfAwareness(100);
        persuasion.convert(player, villager, sect);
        int expected = 100 + intValues.get("convPersPlayerCharIncr");
        assertEquals(expected, player.getCharisma());
    }

    @Test
    public void convertSucceedsAndSelfAwDecreases() {
        player.setCharisma(100);
        villager.setSelfAwareness(100);
        persuasion.convert(player, villager, sect);
        int expected = 100 - intValues.get("convPersVilSelfAwDecr");
        assertEquals(expected, villager.getSelfAwareness());
    }

    @Test
    public void convertSucceedsAndSceptDecreases() {
        player.setCharisma(300);
        villager.setSelfAwareness(100);
        villager.setScepticism(100);
        persuasion.convert(player, villager, sect);
        int expected = 100 - intValues.get("convPersVilSceptDecr");
        assertEquals(expected, villager.getScepticism());
    }

    @Test
    public void convertNotSuccessfulCharismaDoesNotChange() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        boolean success = persuasion.convert(player, villager, sect);
        assertEquals(7, player.getCharisma());
        assertEquals(false, success);
    }

    @Test
    public void convertNotSuccessfulSelfAwDoesNotChange() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        boolean success = persuasion.convert(player, villager, sect);
        assertEquals(100, villager.getSelfAwareness());
        assertEquals(false, success);
    }

    @Test
    public void convertNotSuccessfulSceptDoesNotChange() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        villager.setScepticism(100);
        boolean success = persuasion.convert(player, villager, sect);
        assertEquals(100, villager.getScepticism());
        assertEquals(false, success);
    }

    @Test
    public void getMaxNumberOfConversionsWorksWithPersuasion() {
        assertEquals(15, persuasion.getMaxNumberOfConversions());
    }
}
