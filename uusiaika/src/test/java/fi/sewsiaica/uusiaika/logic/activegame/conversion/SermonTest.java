/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic.activegame.conversion;

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
public class SermonTest {

    private MockRandom random;
    private Map<String, Integer> intValues;
    private Conversion sermon;
    private Player player;
    private Villager villager;
    private Sect sect;

    public SermonTest() {
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
        intValues.put("convMaxNumberOfSermons", 13);
        intValues.put("convSermPlayerBound", 20);
        intValues.put("convSermVilBound", 20);
        intValues.put("convSermPlayerCharIncr", 2);
        intValues.put("convSermVilSceptDecr", 5);
        sermon = new Sermon(random, intValues, 13, 20, 20);
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
    public void checkIfAllowedToProceedReturnsTrueCorrectlyWithSermon() {
        villager.setNumberOfSermons(0);

        boolean result = sermon.checkIfAllowedToProceed(villager);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseCorrectlyWithSermon() {
        villager.setNumberOfSermons(13);

        boolean result = sermon.checkIfAllowedToProceed(villager);
        assertEquals(false, result);
    }

    @Test
    public void increaseAmountOfConvSucceedsWithSermon() {
        int expected = villager.getNumberOfSermons() + 1;
        sermon.increaseAmountOfConv(villager);
        int result = villager.getNumberOfSermons();
        assertEquals(expected, result);
    }

    @Test
    public void convertIncreasesNumberOfSermons() {
        int expected = 1 + villager.getNumberOfSermons();
        sermon.convert(player, villager, sect);
        int result = villager.getNumberOfSermons();
        assertEquals(expected, result);
    }

    @Test
    public void convertSucceedsAndVillagerNowInSect() {
        player.setCharisma(10);
        player.setArgumentationSkills(100);
        villager.setScepticism(10);
        villager.setArgSkills(100);
        villager.setInSect(false);
        sermon.convert(player, villager, sect);
        assertEquals(true, villager.isInSect());
    }

    @Test
    public void convertSucceedsAndPlaCharismaIncr() {
        player.setCharisma(100);
        player.setArgumentationSkills(100);
        villager.setScepticism(100);
        villager.setArgSkills(100);
        sermon.convert(player, villager, sect);
        int expected = 100 + intValues.get("convSermPlayerCharIncr");
        assertEquals(expected, player.getCharisma());
    }

    @Test
    public void convertSucceedsAndVilSceptDecr() {
        player.setCharisma(100);
        player.setArgumentationSkills(100);
        villager.setScepticism(100);
        villager.setArgSkills(100);
        sermon.convert(player, villager, sect);
        int expected = 100 - intValues.get("convSermVilSceptDecr");
        assertEquals(expected, villager.getScepticism());
    }

    @Test
    public void convertNotSuccessfulAndVillagerNotInSect() {
        player.setCharisma(7);
        villager.setScepticism(100);
        villager.setInSect(false);
        sermon.convert(player, villager, sect);
        assertEquals(false, villager.isInSect());
    }

    @Test
    public void convertNotSuccessfulAndPlaCharismaSame() {
        player.setCharisma(7);
        villager.setScepticism(100);
        sermon.convert(player, villager, sect);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void convertNotSuccessfulAndVilSceptSame() {
        player.setCharisma(7);
        villager.setScepticism(100);
        sermon.convert(player, villager, sect);
        assertEquals(100, villager.getScepticism());
    }

    @Test
    public void getMaxNumberOfConversionsWorksWithSermon() {
        assertEquals(13, sermon.getMaxNumberOfConversions());
    }
}
