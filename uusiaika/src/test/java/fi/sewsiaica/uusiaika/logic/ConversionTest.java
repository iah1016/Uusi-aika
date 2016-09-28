/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.logic.conversion.*;
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
    private Conversion persuasion;
    private Conversion sermon;
    private Conversion accusation;
    private Player player;
    private Villager villager;
    private Sect sect;
    private int maxPersuasions;
    private int maxSermons;
    private int maxAccusations;
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
        maxPersuasions = 15;
        maxSermons = 13;
        maxAccusations = 12;
        defaultConvPersPlayerCharIncr = 2;
        defaultConvPersVilSelfAwDecr = 5;
        defaultConvPersVilSceptDecr = 5;
        defaultConvSermPlayerCharIncr = 2;
        defaultConvSermVilSceptDecr = 5;
        defaultConvAccuPlayerCharIncr = 2;
        defaultConvAccuVilSelfEsDecr = 5;
        
        int[] defaultBounds = {20, 20};
        int[] pAttribIncrPers = {defaultConvPersPlayerCharIncr};
        int[] vAttribDecrPers = {defaultConvPersVilSelfAwDecr,
            defaultConvPersVilSceptDecr};
        int[] pAttribIncrSerm = {defaultConvSermPlayerCharIncr};
        int[] vAttribDecrSerm = {defaultConvSermVilSceptDecr};
        int[] pAttribIncrAccu = {defaultConvAccuPlayerCharIncr};
        int[] vAttribDecrAccu = {defaultConvAccuVilSelfEsDecr};

        random = new MockRandom();
        persuasion = new Persuasion(random, maxPersuasions, defaultBounds,
        pAttribIncrPers, vAttribDecrPers);
        sermon = new Sermon(random, maxSermons, defaultBounds,
        pAttribIncrSerm, vAttribDecrSerm);
        accusation = new Accusation(random, maxAccusations, defaultBounds,
        pAttribIncrAccu, vAttribDecrAccu);

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
    public void isMaxedOutIsTrueIfMaxIsZero() {
        int value = 0;
        int max = 0;
        boolean result = persuasion.isMaxedOut(value, max);
        assertEquals(true, result);
    }

    @Test
    public void isMaxedOutIsTrueWithNegValues() {
        int value = -1;
        int max = 4;
        boolean result = persuasion.isMaxedOut(value, max);
        assertEquals(true, result);
    }

    @Test
    public void isMaxedOutIsFalseWithValuesLowerThanMax() {
        int value = 2;
        int max = 3;
        boolean result = persuasion.isMaxedOut(value, max);
        assertEquals(false, result);
    }

    @Test
    public void isMaxedOutIsTrueWithValuesEqualToMax() {
        int value = 3;
        int max = 3;
        boolean result = persuasion.isMaxedOut(value, max);
        assertEquals(true, result);
    }

    @Test
    public void isMaxedOutIsTrueWithValuesGreaterThanMax() {
        int value = 4;
        int max = 3;
        boolean result = persuasion.isMaxedOut(value, max);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsTrueCorrectlyWithPersuasion() {
        villager.setNumberOfPersuations(0);

        boolean result = persuasion.checkIfAllowedToProceed(villager);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseCorrectlyWithPersuasion() {
        villager.setNumberOfPersuations(maxPersuasions);

        boolean result = persuasion.checkIfAllowedToProceed(villager);
        assertEquals(false, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsTrueCorrectlyWithSermon() {
        villager.setNumberOfSermons(0);

        boolean result = sermon.checkIfAllowedToProceed(villager);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseCorrectlyWithSermon() {
        villager.setNumberOfSermons(maxSermons);

        boolean result = sermon.checkIfAllowedToProceed(villager);
        assertEquals(false, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsTrueCorrectlyWithAccusation() {
        villager.setNumberOfAccusations(0);

        boolean result = accusation.checkIfAllowedToProceed(villager);
        assertEquals(true, result);
    }

    @Test
    public void checkIfAllowedToProceedReturnsFalseCorrectlyWithAccusation() {
        villager.setNumberOfAccusations(maxAccusations);

        boolean result = accusation.checkIfAllowedToProceed(villager);
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
    public void increaseAmountOfConvSucceedsWithSermon() {
        int expected = villager.getNumberOfSermons() + 1;
        sermon.increaseAmountOfConv(villager);
        int result = villager.getNumberOfSermons();
        assertEquals(expected, result);
    }

    @Test
    public void increaseAmountOfConvSucceedsWithAccusation() {
        int expected = villager.getNumberOfAccusations() + 1;
        accusation.increaseAmountOfConv(villager);
        int result = villager.getNumberOfAccusations();
        assertEquals(expected, result);
    }

    @Test
    public void convSucceedsSucceedsWithEqualValues() {
        int playerBaseValue = 0;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 0;
        int villagerIncreaseLimit = 1;
        boolean result = persuasion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(true, result);
    }

    @Test
    public void convSucceedsSucceedsWithPlayerHavingGreaterValue() {
        int playerBaseValue = 1;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 0;
        int villagerIncreaseLimit = 1;
        boolean result = persuasion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(true, result);
    }

    @Test
    public void convSucceedsDoesNotSucceedWithPlayerHavingLowerValue() {
        int playerBaseValue = 0;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 1;
        int villagerIncreaseLimit = 1;
        boolean result = persuasion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(false, result);
    }

    @Test
    public void convSucceedsDoesNotSucceedWithNegBaseValue() {
        int playerBaseValue = 0;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = -1;
        int villagerIncreaseLimit = 1;
        boolean result = persuasion.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(false, result);
    }

    @Test
    public void persuasionIncreasesNumberOfPersuasions() {
        int expected = 1 + villager.getNumberOfPersuasions();
        persuasion.convert(player, villager, sect);
        int result = villager.getNumberOfPersuasions();
        assertEquals(expected, result);
    }

    @Test
    public void sermonIncreasesNumberOfSermons() {
        int expected = 1 + villager.getNumberOfSermons();
        sermon.convert(player, villager, sect);
        int result = villager.getNumberOfSermons();
        assertEquals(expected, result);
    }

    @Test
    public void accusationIncreasesNumberOfAccusations() {
        int expected = 1 + villager.getNumberOfAccusations();
        accusation.convert(player, villager, sect);
        int result = villager.getNumberOfAccusations();
        assertEquals(expected, result);
    }

    @Test
    public void persuasionSucceedsAndCharismaIncreases() {
        player.setCharisma(100);
        villager.setSelfAwareness(100);
        persuasion.convert(player, villager, sect);
        int expected = 100 + defaultConvPersPlayerCharIncr;
        assertEquals(expected, player.getCharisma());
    }

    @Test
    public void persuasionSucceedsAndSelfAwDecreases() {
        player.setCharisma(100);
        villager.setSelfAwareness(100);
        persuasion.convert(player, villager, sect);
        int expected = 100 - defaultConvPersVilSelfAwDecr;
        assertEquals(expected, villager.getSelfAwareness());
    }

    @Test
    public void persuasionSucceedsAndSceptDecreases() {
        player.setCharisma(300);
        villager.setSelfAwareness(100);
        villager.setScepticism(100);
        persuasion.convert(player, villager, sect);
        int expected = 100 - defaultConvPersVilSceptDecr;
        assertEquals(expected, villager.getScepticism());
    }

    @Test
    public void persuasionNotSuccessfulCharismaDoesNotChange() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        persuasion.convert(player, villager, sect);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void persuasionNotSuccessfulSelfAwDoesNotChange() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        persuasion.convert(player, villager, sect);
        assertEquals(100, villager.getSelfAwareness());
    }

    @Test
    public void persuasionNotSuccessfulSceptDoesNotChange() {
        player.setCharisma(7);
        villager.setSelfAwareness(100);
        villager.setScepticism(100);
        persuasion.convert(player, villager, sect);
        assertEquals(100, villager.getScepticism());
    }

    @Test
    public void sermonSucceedsAndVillagerNowInSect() {
        player.setCharisma(10);
        player.setArgSkills(100);
        villager.setScepticism(10);
        villager.setArgSkills(100);
        villager.setInSect(false);
        sermon.convert(player, villager, sect);
        assertEquals(true, villager.isInSect());
    }

    @Test
    public void sermonSucceedsAndPlaCharismaIncr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setScepticism(100);
        villager.setArgSkills(100);
        sermon.convert(player, villager, sect);
        int expected = 100 + defaultConvSermPlayerCharIncr;
        assertEquals(expected, player.getCharisma());
    }

    @Test
    public void sermonSucceedsAndVilSceptDecr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setScepticism(100);
        villager.setArgSkills(100);
        sermon.convert(player, villager, sect);
        int expected = 100 - defaultConvSermVilSceptDecr;
        assertEquals(expected, villager.getScepticism());
    }

    @Test
    public void sermonNotSuccessfulAndVillagerNotInSect() {
        player.setCharisma(7);
        villager.setScepticism(100);
        villager.setInSect(false);
        sermon.convert(player, villager, sect);
        assertEquals(false, villager.isInSect());
    }

    @Test
    public void sermonNotSuccessfulAndPlaCharismaSame() {
        player.setCharisma(7);
        villager.setScepticism(100);
        sermon.convert(player, villager, sect);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void sermonNotSuccessfulAndVilSceptSame() {
        player.setCharisma(7);
        villager.setScepticism(100);
        sermon.convert(player, villager, sect);
        assertEquals(100, villager.getScepticism());
    }

    @Test
    public void accusationSucceedsAndVillagerNowInSect() {
        player.setCharisma(10);
        player.setArgSkills(100);
        villager.setSelfEsteem(10);
        villager.setArgSkills(100);
        villager.setInSect(false);
        accusation.convert(player, villager, sect);
        assertEquals(true, villager.isInSect());
    }

    @Test
    public void accusationSucceedsAndPlaCharismaIncr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setSelfEsteem(100);
        villager.setArgSkills(100);
        accusation.convert(player, villager, sect);
        int expected = 100 + defaultConvAccuPlayerCharIncr;
        assertEquals(expected, player.getCharisma());
    }

    @Test
    public void accusationSucceedsAndVilSelfEsteemDecr() {
        player.setCharisma(100);
        player.setArgSkills(100);
        villager.setSelfEsteem(100);
        villager.setArgSkills(100);
        accusation.convert(player, villager, sect);
        int expected = 100 - defaultConvAccuVilSelfEsDecr;
        assertEquals(expected, villager.getSelfEsteem());
    }

    @Test
    public void accusationNotSuccessfulAndVillagerNotInSect() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        villager.setInSect(false);
        accusation.convert(player, villager, sect);
        assertEquals(false, villager.isInSect());
    }

    @Test
    public void accusationNotSuccessfulAndPlaCharismaSame() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        accusation.convert(player, villager, sect);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void accusationNotSuccessfulAndVilSelfEsteemSame() {
        player.setCharisma(7);
        villager.setSelfEsteem(100);
        accusation.convert(player, villager, sect);
        assertEquals(100, villager.getSelfEsteem());
    }

    @Test
    public void getConvMaxNumberOfPersuasionsWorks() {
        assertEquals(15, persuasion.getMaxNumberOfConversions());
    }

    @Test
    public void getConvMaxNumberOfSermonsWorks() {
        assertEquals(13, sermon.getMaxNumberOfConversions());
    }

    @Test
    public void getConvMaxNumberOfAccusationsWorks() {
        assertEquals(12, accusation.getMaxNumberOfConversions());
    }
}
