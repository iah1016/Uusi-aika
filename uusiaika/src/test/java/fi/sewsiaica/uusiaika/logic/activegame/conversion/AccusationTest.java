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
public class AccusationTest {
    
    private MockRandom random;
    private Map<String, Integer> intValues;
    private Conversion accusation;
    private Player player;
    private Villager villager;
    private Sect sect;
    
    public AccusationTest() {
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
        intValues.put("convMaxNumberOfAccusations", 12);
        intValues.put("convAccuPlayerBound", 20);
        intValues.put("convAccuVilBound", 20);
        intValues.put("convAccuPlayerCharIncr", 2);
        intValues.put("convAccuVilSelfEsDecr", 5);
        accusation = new Accusation(random, intValues, 12, 20, 20);
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
    public void checkIfAllowedToProceedReturnsTrueCorrectlyWithAccusation() {
        villager.setNumberOfAccusations(0);

        boolean result = accusation.checkIfAllowedToProceed(villager);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseCorrectlyWithAccusation() {
        villager.setNumberOfAccusations(12);

        boolean result = accusation.checkIfAllowedToProceed(villager);
        assertEquals(false, result);
    }


    @Test
    public void increaseAmountOfConvSucceedsWithAccusation() {
        int expected = villager.getNumberOfAccusations() + 1;
        accusation.increaseAmountOfConv(villager);
        int result = villager.getNumberOfAccusations();
        assertEquals(expected, result);
    }

    @Test
    public void convertIncreasesNumberOfAccusations() {
        int expected = 1 + villager.getNumberOfAccusations();
        accusation.convert(player, villager, sect);
        int result = villager.getNumberOfAccusations();
        assertEquals(expected, result);
    }

    @Test
    public void convertSucceedsAndVillagerNowInSect() {
        player.setCharisma(10);
        player.setArgSkills(100);
        villager.setSelfEsteem(10);
        villager.setArgSkills(100);
        villager.setInSect(false);
        accusation.convert(player, villager, sect);
        assertEquals(true, villager.isInSect());
    }

    @Test
    public void convertSucceedsAndPlaCharismaIncr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setSelfEsteem(100);
        villager.setArgSkills(100);
        accusation.convert(player, villager, sect);
        int expected = 100 + intValues.get("convAccuPlayerCharIncr");
        assertEquals(expected, player.getCharisma());
    }

    @Test
    public void convertSucceedsAndVilSelfEsteemDecr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setSelfEsteem(100);
        villager.setArgSkills(100);
        accusation.convert(player, villager, sect);
        int expected = 100 - intValues.get("convAccuVilSelfEsDecr");
        assertEquals(expected, villager.getSelfEsteem());
    }

    @Test
    public void convertNotSuccessfulAndVillagerNotInSect() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        villager.setInSect(false);
        accusation.convert(player, villager, sect);
        assertEquals(false, villager.isInSect());
    }

    @Test
    public void convertNotSuccessfulAndPlaCharismaSame() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        accusation.convert(player, villager, sect);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void convertNotSuccessfulAndVilSelfEsteemSame() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        accusation.convert(player, villager, sect);
        assertEquals(100, villager.getSelfEsteem());
    }

    @Test
    public void getMaxNumberOfConversionsWorksWithAccusation() {
        assertEquals(12, accusation.getMaxNumberOfConversions());
    }
}
