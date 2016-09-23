/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
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

    private MockRandom random;
    private Conversion conversion;
    private Player player;
    private Villager villager;
    private Sect sect;
    private int[] maxNumbers;
    private int defaultConvPersPlayerCharIncr;
    private int defaultConvSermPlayerCharIncr;
    private int defaultConvAccuPlayerCharIncr;
    private int defaultConvPersVilSelfAwDecr;
    private int defaultConvPersVilSceptDecr;
    private int defaultConvSermVilSceptDecr;
    private int defaultConvAccuVilSelfEsDecr;

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
        defaultConvPersPlayerCharIncr = 2;
        defaultConvSermPlayerCharIncr = 2;
        defaultConvAccuPlayerCharIncr = 2;
        defaultConvPersVilSelfAwDecr = 5;
        defaultConvPersVilSceptDecr = 5;
        defaultConvSermVilSceptDecr = 5;
        defaultConvAccuVilSelfEsDecr = 5;

        random = new MockRandom();
        conversion = new Conversion(random, maxNumbers);

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
        int playerBaseValue = 0;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 0;
        int villagerIncreaseLimit = 1;
        boolean result = conversion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(true, result);
    }

    @Test
    public void convSucceedsSucceedsWithPlayerHavingGreaterValue() {
        int playerBaseValue = 1;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 0;
        int villagerIncreaseLimit = 1;
        boolean result = conversion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(true, result);
    }

    @Test
    public void convSucceedsDoesNotSucceedWithPlayerHavingLowerValue() {
        int playerBaseValue = 0;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 1;
        int villagerIncreaseLimit = 1;
        boolean result = conversion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(false, result);
    }

    @Test
    public void convSucceedsDoesNotSucceedWithNegBaseValue() {
        int playerBaseValue = 0;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = -1;
        int villagerIncreaseLimit = 1;
        boolean result = conversion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(false, result);
    }

    @Test
    public void persuasionIncreasesNumberOfPersuasions() {
        int expected = 1 + villager.getNumberOfPersuasions();
        conversion.persuasion(player, villager, sect);
        int result = villager.getNumberOfPersuasions();
        assertEquals(expected, result);
    }

    @Test
    public void sermonIncreasesNumberOfSermons() {
        int expected = 1 + villager.getNumberOfSermons();
        conversion.sermon(player, villager, sect);
        int result = villager.getNumberOfSermons();
        assertEquals(expected, result);
    }

    @Test
    public void accusationIncreasesNumberOfAccusations() {
        int expected = 1 + villager.getNumberOfAccusations();
        conversion.accusation(player, villager, sect);
        int result = villager.getNumberOfAccusations();
        assertEquals(expected, result);
    }

    @Test
    public void persuasionSucceedsAndCharismaIncreases() {
        player.setCharisma(100);
        villager.setSelfAwareness(100);
        conversion.persuasion(player, villager, sect);
        int expected = 100 + defaultConvPersPlayerCharIncr;
        assertEquals(expected, player.getCharisma());
    }

    @Test
    public void persuasionSucceedsAndSelfAwDecreases() {
        player.setCharisma(100);
        villager.setSelfAwareness(100);
        conversion.persuasion(player, villager, sect);
        int expected = 100 - defaultConvPersVilSelfAwDecr;
        assertEquals(expected, villager.getSelfAwareness());
    }

    @Test
    public void persuasionSucceedsAndSceptDecreases() {
        player.setCharisma(300);
        villager.setSelfAwareness(100);
        villager.setScepticism(100);
        conversion.persuasion(player, villager, sect);
        int expected = 100 - defaultConvPersVilSceptDecr;
        assertEquals(expected, villager.getScepticism());
    }

    @Test
    public void persuasionNotSuccessfulCharismaDoesNotChange() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        conversion.persuasion(player, villager, sect);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void persuasionNotSuccessfulSelfAwDoesNotChange() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        conversion.persuasion(player, villager, sect);
        assertEquals(100, villager.getSelfAwareness());
    }

    @Test
    public void persuasionNotSuccessfulSceptDoesNotChange() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        villager.setScepticism(100);
        conversion.persuasion(player, villager, sect);
        assertEquals(100, villager.getScepticism());
    }

    @Test
    public void sermonSucceedsAndVillagerNowInSect() {
        player.setCharisma(10);
        player.setArgSkills(100);
        villager.setScepticism(10);
        villager.setArgSkills(100);
        villager.setInSect(false);
        conversion.sermon(player, villager, sect);
        assertEquals(true, villager.isInSect());
    }

    @Test
    public void sermonSucceedsAndPlaCharismaIncr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setScepticism(100);
        villager.setArgSkills(100);
        conversion.sermon(player, villager, sect);
        int expected = 100 + defaultConvSermPlayerCharIncr;
        assertEquals(expected, player.getCharisma());
    }

    @Test
    public void sermonSucceedsAndVilSceptDecr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setScepticism(100);
        villager.setArgSkills(100);
        conversion.sermon(player, villager, sect);
        int expected = 100 - defaultConvSermVilSceptDecr;
        assertEquals(expected, villager.getScepticism());
    }

    @Test
    public void sermonNotSuccessfulAndVillagerNotInSect() {
        player.setCharisma(7);
        villager.setScepticism(100);
        villager.setInSect(false);
        conversion.sermon(player, villager, sect);
        assertEquals(false, villager.isInSect());
    }

    @Test
    public void sermonNotSuccessfulAndPlaCharismaSame() {
        player.setCharisma(7);
        villager.setScepticism(100);
        conversion.sermon(player, villager, sect);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void sermonNotSuccessfulAndVilSceptSame() {
        player.setCharisma(7);
        villager.setScepticism(100);
        conversion.sermon(player, villager, sect);
        assertEquals(100, villager.getScepticism());
    }

    @Test
    public void accusationSucceedsAndVillagerNowInSect() {
        player.setCharisma(10);
        player.setArgSkills(100);
        villager.setSelfEsteem(10);
        villager.setArgSkills(100);
        villager.setInSect(false);
        conversion.accusation(player, villager, sect);
        assertEquals(true, villager.isInSect());
    }

    @Test
    public void accusationSucceedsAndPlaCharismaIncr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setSelfEsteem(100);
        villager.setArgSkills(100);
        conversion.accusation(player, villager, sect);
        int expected = 100 + defaultConvAccuPlayerCharIncr;
        assertEquals(expected, player.getCharisma());
    }

    @Test
    public void accusationSucceedsAndVilSelfEsteemDecr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setSelfEsteem(100);
        villager.setArgSkills(100);
        conversion.accusation(player, villager, sect);
        int expected = 100 - defaultConvAccuVilSelfEsDecr;
        assertEquals(expected, villager.getSelfEsteem());
    }

    @Test
    public void accusationNotSuccessfulAndVillagerNotInSect() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        villager.setInSect(false);
        conversion.accusation(player, villager, sect);
        assertEquals(false, villager.isInSect());
    }

    @Test
    public void accusationNotSuccessfulAndPlaCharismaSame() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        conversion.accusation(player, villager, sect);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void accusationNotSuccessfulAndVilSelfEsteemSame() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        conversion.accusation(player, villager, sect);
        assertEquals(100, villager.getSelfEsteem());
    }

}
