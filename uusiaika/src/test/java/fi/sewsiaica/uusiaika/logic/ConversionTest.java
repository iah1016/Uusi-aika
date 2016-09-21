/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
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
public class ConversionTest {

    private Random random;
    private Conversion conversion;
    private Player player;
    private Villager villager;
    private int[] maxNumbers;

    public ConversionTest() {
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
        maxNumbers = new int[3];
        maxNumbers[0] = 15; // Persuasion
        maxNumbers[1] = 13; // Sermon
        maxNumbers[2] = 12; // Accusation

        random = new Random();
        conversion = new Conversion(random, maxNumbers);

        // String name, int charisma, int argSkills
        player = new Player("Pekka", 10, 10);
        // String name, boolean inSect, int scepticism, int selfEsteem, 
        // int selfAwareness, int argSkills, String profession
        villager = new Villager("Matti", false, 10, 10, 10, 10, "kirkkoherra");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isMaxedOutIsFalseWithValuesLowerThanMax() {
        int value = 2;
        int max = 3;
        boolean result = conversion.isMaxedOut(value, max);
        assertEquals(false, result);
    }

    @Test
    public void isMaxedOutIsTrueWithValuesEqualToMax() {
        int value = 3;
        int max = 3;
        boolean result = conversion.isMaxedOut(value, max);
        assertEquals(true, result);
    }

    @Test
    public void isMaxedOutIsTrueWithValuesGreaterThanMax() {
        int value = 4;
        int max = 3;
        boolean result = conversion.isMaxedOut(value, max);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseIfTypeIsOtherThanABC() {
        char type = 'd';
        boolean result = conversion.checkIfAllowedToProceed(type, villager);
        assertEquals(false, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsTrueCorrectlyWithPersuasion() {
        char type = 'a';
        villager.setNumberOfPersuations(0);

        boolean result = conversion.checkIfAllowedToProceed(type, villager);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseCorrectlyWithPersuasion() {
        char type = 'a';
        villager.setNumberOfPersuations(maxNumbers[0]);

        boolean result = conversion.checkIfAllowedToProceed(type, villager);
        assertEquals(false, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsTrueCorrectlyWithSermon() {
        char type = 'b';
        villager.setNumberOfSermons(0);

        boolean result = conversion.checkIfAllowedToProceed(type, villager);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseCorrectlyWithSermon() {
        char type = 'b';
        villager.setNumberOfSermons(maxNumbers[1]);

        boolean result = conversion.checkIfAllowedToProceed(type, villager);
        assertEquals(false, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsTrueCorrectlyWithAccusation() {
        char type = 'c';
        villager.setNumberOfAccusations(0);

        boolean result = conversion.checkIfAllowedToProceed(type, villager);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseCorrectlyWithAccusation() {
        char type = 'c';
        villager.setNumberOfAccusations(maxNumbers[2]);

        boolean result = conversion.checkIfAllowedToProceed(type, villager);
        assertEquals(false, result);
    }

    @Test
    public void increaseAmountOfConvSucceedsWithPersuasion() {
        char type = 'a';
        int expected = villager.getNumberOfPersuasions() + 1;
        conversion.increaseAmountOfConv(type, villager);
        int result = villager.getNumberOfPersuasions();
        assertEquals(expected, result);
    }

    @Test
    public void increaseAmountOfConvSucceedsWithSermon() {
        char type = 'b';
        int expected = villager.getNumberOfSermons() + 1;
        conversion.increaseAmountOfConv(type, villager);
        int result = villager.getNumberOfSermons();
        assertEquals(expected, result);
    }

    @Test
    public void increaseAmountOfConvSucceedsWithAccusation() {
        char type = 'c';
        int expected = villager.getNumberOfAccusations() + 1;
        conversion.increaseAmountOfConv(type, villager);
        int result = villager.getNumberOfAccusations();
        assertEquals(expected, result);
    }

    @Test
    public void increaseAmountOfConvDoesNotIncreaseOtherConvTypes() {
        char type = 'a';
        int expected = villager.getNumberOfAccusations()
                + villager.getNumberOfSermons();
        conversion.increaseAmountOfConv(type, villager);
        int result = villager.getNumberOfAccusations()
                + villager.getNumberOfSermons();
        assertEquals(expected, result);
    }
    
    @Test
    public void convSucceedsSucceedsWithEqualValues() {
        int playerBaseValue = 100;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 100;
        int villagerIncreaseLimit = 1;
        boolean result = conversion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(true, result);
    }
    
    @Test
    public void convSucceedsSucceedsWithPlayerHavingGreaterValue() {
        int playerBaseValue = 101;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 100;
        int villagerIncreaseLimit = 1;
        boolean result = conversion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(true, result);
    }
    
    @Test
    public void convSucceedsDoesNotSucceedWithPlayerHavingLowerValue() {
        int playerBaseValue = 99;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 100;
        int villagerIncreaseLimit = 1;
        boolean result = conversion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(false, result);
    }

    @Test
    public void persuasionSuccessful() {
        player.setCharisma(100);
        villager.setSelfAwareness(7);
        conversion.persuasion(player, villager);
        assertEquals(102, player.getCharisma());
    }

    @Test
    public void persuasionNotSuccessful() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        conversion.persuasion(player, villager);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void sermonSuccessful() {
        player.setCharisma(100);
        villager.setScepticism(7);
        villager.setInSect(false);
        conversion.sermon(player, villager);
        assertEquals(true, villager.isInSect());
    }

    @Test
    public void sermonNotSuccessful() {
        player.setCharisma(7);
        villager.setScepticism(100);
        villager.setInSect(false);
        conversion.sermon(player, villager);
        assertEquals(false, villager.isInSect());
    }

    @Test
    public void accusationSuccessful() {
        player.setCharisma(100);
        villager.setSelfEsteem(7);
        villager.setInSect(false);
        conversion.accusation(player, villager);
        assertEquals(true, villager.isInSect());
    }

    @Test
    public void accusationNotSuccessful() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        villager.setInSect(false);
        conversion.accusation(player, villager);
        assertEquals(false, villager.isInSect());
    }
}
