/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.toolsfortests.MockConfig;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class GameLogicTest {

    private MockRandom random;
    private MockConfig config;
    private GameLogic gameLogic;
    private String[] names;

    public GameLogicTest() {
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
        String[] namesForVillagers = {"A", "B", "C", "D"};
        String[] professions = {"a", "b", "c", "d"};
        config.setVilNames(Arrays.asList(namesForVillagers));
        config.setProfessions(Arrays.asList(professions));

        gameLogic = new GameLogic(random, config);
        names = new String[2];
        names[0] = "AA";
        names[1] = "AB";
        try {
            gameLogic.newGame(names, "", "", "");
        } catch (FileNotFoundException e) {

        }

        gameLogic.getPersuasion().setMaxNumberOfConversions(17);
        gameLogic.getSermon().setMaxNumberOfConversions(11);
        gameLogic.getAccusation().setMaxNumberOfConversions(19);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newGameThrowsFileNotFoundExceptionIfConfIDIsInvalid() {
        boolean epicFail = false;
        try {
            gameLogic.newGame(names, "foo", "", "");
        } catch (FileNotFoundException e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

// Kysy näistä...
//    @Test
//    public void newGameThrowsFileNotFoundExceptionIfVilIDIsInvalid() {
//        boolean epicFail = false;
//        try {
//            gameLogic.newGame(names, "", "foo", "");
//        } catch (FileNotFoundException e) {
//            epicFail = true;
//        }
//        assertEquals(true, epicFail);
//    }
//    
//    @Test
//    public void newGameThrowsFileNotFoundExceptionIfProfsIDIsInvalid() {
//        boolean epicFail = false;
//        try {
//            gameLogic.newGame(names, "", "", "foo");
//        } catch (FileNotFoundException e) {
//            epicFail = true;
//        }
//        assertEquals(true, epicFail);
//    }
    
    @Test
    public void newGameReturnsTrueIfIDsAreEmpty() throws FileNotFoundException {
        boolean result = gameLogic.newGame(names, "", "", "");
        assertEquals(true, result);
    }
    
    // ... ja tästä.
    @Test
    public void newGameReturnsTrueIfValidFilesAreUsed()
            throws FileNotFoundException {
        String confID = "src/main/resources/default_values.txt";
        String vilID = "src/main/resources/default_villagers.txt";
        String profsID = "src/main/resources/default_professions.txt";
        boolean result = gameLogic.newGame(names, confID, vilID, profsID);
        assertEquals(true, result);
    }
    
    @Test
    public void newGameReturnsFalseIfInvalidIntValues()
            throws FileNotFoundException {
        String confID = "src/main/resources/testfile.txt";
        boolean result = gameLogic.newGame(names, confID, "", "");
        assertEquals(false, result);
    }

    @Test
    public void newGameCreatesActiveGameProperlyWithDefaults() {
        assertEquals("AAAB", gameLogic.getPlayer().getName()
                + gameLogic.getSect().getName());
        assertEquals("Dc", gameLogic.getVillagers().get(3).getName()
                + gameLogic.getVillagers().get(2).getProfession());

        // The default max conversion values are: 3, 2, 2.
        assertEquals(7, gameLogic.getPersuasion().getMaxNumberOfConversions()
                + gameLogic.getSermon().getMaxNumberOfConversions()
                + gameLogic.getAccusation().getMaxNumberOfConversions());

        // The default training charisma increase is 10.
        int expected = gameLogic.getPlayer().getCharisma() + 10;
        gameLogic.trainingCentreActions('a');
        assertEquals(expected, gameLogic.getPlayer().getCharisma());

        // The default death cult charisma requirement is 255.
        gameLogic.getPlayer().setCharisma(254);
        assertEquals(false, gameLogic.templeActions('b'));
        gameLogic.getPlayer().setCharisma(255);
        assertEquals(true, gameLogic.templeActions('b'));

        boolean gameOver = false;
        gameLogic.getSect().setBalance(1000000);
        // The default max number of turns is 100.
        for (int i = 0; i < 99; i++) {
            gameOver = !gameLogic.endTurn();
        }
        assertEquals(false, gameOver);
        gameOver = !gameLogic.endTurn();
        assertEquals(true, gameOver);
    }

    // Load game is not yet implemented.
    @Test
    public void conversionDoesNotWorkWithOptionAIfAlreadyMaxedTries() {
        Villager villager = gameLogic.getVillagers().get(0);
        villager.setNumberOfPersuations(17);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
        villager.setSelfAwareness(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'a');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionBIfAlreadyMaxedTries() {
        Villager villager = gameLogic.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(11);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'b');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionCIfAlreadyMaxedTries() {
        Villager villager = gameLogic.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(19);
        villager.setScepticism(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'c');
        assertEquals(false, result);
    }

    @Test
    public void conversionWorksWithOptionA() {
        Villager villager = gameLogic.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(100);
        villager.setSelfAwareness(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'a');
        assertEquals(true, result);
    }

    @Test
    public void conversionWorksWithOptionB() {
        Villager villager = gameLogic.getVillagers().get(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(100);
        villager.setScepticism(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'b');
        assertEquals(true, result);
    }

    @Test
    public void conversionWorksWithOptionC() {
        Villager villager = gameLogic.getVillagers().get(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'c');
        assertEquals(true, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionAIfMaxIsZero() {
        Villager villager = gameLogic.getVillagers().get(0);
        gameLogic.getPersuasion().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(1);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(100);
        villager.setSelfAwareness(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'a');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionBIfMaxIsZero() {
        Villager villager = gameLogic.getVillagers().get(0);
        gameLogic.getSermon().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(100);
        villager.setScepticism(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'b');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionCIfMaxIsZero() {
        Villager villager = gameLogic.getVillagers().get(0);
        gameLogic.getAccusation().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'c');
        assertEquals(false, result);
    }

    @Test
    public void conversionReturnsFalseIfOptionOtherThanABC() {
        Villager villager = gameLogic.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        gameLogic.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, ' ');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsTrueWithOptionAIfMemberListIsNotEmpty() {
        List<Villager> congregation = new ArrayList<Villager>();
        congregation.add(new Villager("A", true, 0, 0, 0, 0, "B"));
        gameLogic.getSect().setCongregation(congregation);
        boolean result = gameLogic.templeActions('a');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionAIfMemberListIsEmpty() {
        List<Villager> congregation = new ArrayList<Villager>();
        gameLogic.getSect().setCongregation(congregation);
        boolean result = gameLogic.templeActions('a');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionAIfMemberListIsNull() {
        gameLogic.getSect().setCongregation(null);
        boolean result = gameLogic.templeActions('a');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsTrueWithOptionBAndHighCharisma() {
        Player player = gameLogic.getPlayer();
        player.setCharisma(1000);
        boolean result = gameLogic.templeActions('b');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionBAndLowCharisma() {
        Player player = gameLogic.getPlayer();
        player.setCharisma(3);
        boolean result = gameLogic.templeActions('b');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsTrueWithOptionCAndHighBalance() {
        Sect sect = gameLogic.getSect();
        sect.setBalance(10000000);
        boolean result = gameLogic.templeActions('c');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionCAndLowBalance() {
        Sect sect = gameLogic.getSect();
        sect.setBalance(10);
        boolean result = gameLogic.templeActions('c');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsFalseWithInvalidOption() {
        boolean result = gameLogic.templeActions(' ');
        assertEquals(false, result);
    }

    @Test
    public void trainingCentreActionsReturnsFalseWithInvalidOption() {
        boolean result = gameLogic.trainingCentreActions(' ');
        assertEquals(false, result);
    }

    @Test
    public void trainingCentreActionsReturnsTrueWithOptionA() {
        boolean result = gameLogic.trainingCentreActions('a');
        assertEquals(true, result);
    }

    @Test
    public void trainingCentreActionsReturnsTrueWithOptionB() {
        boolean result = gameLogic.trainingCentreActions('b');
        assertEquals(true, result);
    }

    @Test
    public void endTurnReturnsTrueWhenTurnHasChanged() {
        int turnsBefore = gameLogic.getNumberOfTurns();
        assertEquals(true, gameLogic.endTurn());
        int turnsAfter = gameLogic.getNumberOfTurns();
        assertEquals(turnsAfter, turnsBefore + 1);
    }
}
