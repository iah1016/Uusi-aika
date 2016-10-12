/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    private Config config;
    private GameLogic gameLogic;
    private String[] names;
    private ActiveGame activeGame;

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
        config = new Config();
        gameLogic = new GameLogic(random, config);
        names = new String[2];
        names[0] = "AA";
        names[1] = "AB";
        try {
            gameLogic.newGame(names);
        } catch (FileNotFoundException e) {

        }
        activeGame = gameLogic.getActiveGame();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newGameThrowsFileNotFoundExceptionIfConfigFileIsInvalid() {
        boolean epicFail = false;
        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[0] = new File("foo");

        try {
            gameLogic.newGame(names);
        } catch (FileNotFoundException e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void newGameThrowsFileNotFoundExceptionIfVilNamesFileIsInvalid() {
        boolean epicFail = false;
        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[1] = new File("foo");

        try {
            gameLogic.newGame(names);
        } catch (FileNotFoundException e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void newGameThrowsFileNotFoundExceptionIfProfsFileIsInvalid() {
        boolean epicFail = false;
        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[2] = new File("foo");

        try {
            gameLogic.newGame(names);
        } catch (FileNotFoundException e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void newGameReturnsTrueIfConfigFilesAreNull()
            throws FileNotFoundException {
        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[0] = null;
        configFiles[1] = null;
        configFiles[2] = null;

        boolean result = gameLogic.newGame(names);
        assertEquals(true, result);
    }

    @Test
    public void newGameReturnsTrueIfValidFilesAreUsed()
            throws FileNotFoundException {
        String confID = "src/test/filesfortests/test_values.txt";
        String vilID = "src/test/filesfortests/test_villagers.txt";
        String profsID = "src/test/filesfortests/test_professions.txt";

        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[0] = new File(confID);
        configFiles[1] = new File(vilID);
        configFiles[2] = new File(profsID);

        boolean result = gameLogic.newGame(names);
        assertEquals(true, result);
    }

    @Test
    public void newGameReturnsFalseIfInvalidIntValues()
            throws FileNotFoundException {
        String confID = "src/test/filesfortests/testfile.txt";
        gameLogic.getConfigFiles()[0] = new File(confID);
        boolean result = gameLogic.newGame(names);
        assertEquals(false, result);
    }

    @Test
    public void newGameCreatesActiveGameProperlyWithDefaults() {
        assertEquals("AAAB", activeGame.getPlayer().getName()
                + activeGame.getSect().getName());
        assertEquals("Teemu P, Opettaja",
                activeGame.getVillagers().get(3).getName() + ", "
                + activeGame.getVillagers().get(2).getProfession());

        // The default max conversion values are: 3, 2, 2.
        assertEquals(7, activeGame.getPersuasion().getMaxNumberOfConversions()
                + activeGame.getSermon().getMaxNumberOfConversions()
                + activeGame.getAccusation().getMaxNumberOfConversions());

        // The default training charisma increase is 10.
        int expected = activeGame.getPlayer().getCharisma() + 10;
        gameLogic.trainingCentreActions('a');
        assertEquals(expected, activeGame.getPlayer().getCharisma());

        // The default death cult charisma requirement is 255.
        activeGame.getPlayer().setCharisma(254);
        assertEquals(false, gameLogic.templeActions('b'));
        activeGame.getPlayer().setCharisma(255);
        assertEquals(true, gameLogic.templeActions('b'));

        boolean gameOver = false;
        activeGame.getSect().setBalance(1000000);
        // The default max number of turns is 100.
        for (int i = 1; i <= 99; i++) {
            gameOver = !gameLogic.endTurn();
        }
        assertEquals(false, gameOver);
        gameOver = !gameLogic.endTurn();
        assertEquals(true, gameOver);
    }

    @Test
    public void intValuesAreCorrectAfterCreatingNewGameWithValidFiles()
            throws FileNotFoundException {
        String confID = "src/test/filesfortests/test_values.txt";
        String vilID = "src/test/filesfortests/test_villagers.txt";
        String profsID
                = "src/test/filesfortests/test_professions.txt";

        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[0] = new File(confID);
        configFiles[1] = new File(vilID);
        configFiles[2] = new File(profsID);

        gameLogic.newGame(names);
        activeGame = gameLogic.getActiveGame();

        int expenses = activeGame.getSect().getExpenses();
        assertEquals(700, expenses);
        String villagerName = activeGame.getVillagers().get(0).getName();
        assertEquals("Heikki K", villagerName);
        activeGame.getPlayer().setCharisma(665);
        assertEquals(false, gameLogic.templeActions('b'));
        activeGame.getPlayer().setCharisma(666);
        assertEquals(true, gameLogic.templeActions('b'));
    }

    @Test
    public void loadGameReturnsFalseIfLoadingUnsuccessful() {
        ActiveGame oldAG = gameLogic.getActiveGame();
        assertEquals(false, gameLogic.loadGame(new File("foo")));
        assertEquals(oldAG, gameLogic.getActiveGame());
    }

    @Test
    public void loadGameNormalOperation() {
        ActiveGame oldAG = gameLogic.getActiveGame();
        gameLogic.endTurn();
        int oldNextTurn = oldAG.getNumberOfTurns() + 1;
        int oldBalance = oldAG.getSect().getBalance();
        
        File file = new File("src/test/filesfortests/test_savefile.txt");
        assertEquals(true, gameLogic.loadGame(file));
        assertNotEquals(oldBalance, gameLogic.getActiveGame()
                .getSect().getBalance());
        assertNotEquals(oldAG, gameLogic.getActiveGame());
        
        gameLogic.endTurn();
        assertNotEquals(28, oldNextTurn);
        assertEquals(28, gameLogic.getActiveGame().getNumberOfTurns());
    }

    @Test
    public void conversionDoesNotWorkWithOptionAIfAlreadyMaxedTries() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(17);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
        villager.setSelfAwareness(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'a');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionBIfAlreadyMaxedTries() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(11);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'b');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionCIfAlreadyMaxedTries() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(19);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'c');
        assertEquals(false, result);
    }

    @Test
    public void conversionWorksWithOptionA() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(100);
        villager.setSelfAwareness(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'a');
        assertEquals(true, result);
    }

    @Test
    public void conversionWorksWithOptionB() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(100);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'b');
        assertEquals(true, result);
    }

    @Test
    public void conversionWorksWithOptionC() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'c');
        assertEquals(true, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionAIfMaxIsZero() {
        Villager villager = activeGame.getVillagers().get(0);
        activeGame.getPersuasion().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(1);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(100);
        villager.setSelfAwareness(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'a');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionBIfMaxIsZero() {
        Villager villager = activeGame.getVillagers().get(0);
        activeGame.getSermon().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(100);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'b');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionCIfMaxIsZero() {
        Villager villager = activeGame.getVillagers().get(0);
        activeGame.getAccusation().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, 'c');
        assertEquals(false, result);
    }

    @Test
    public void conversionReturnsFalseIfOptionOtherThanABC() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = gameLogic.conversion(villager, ' ');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsTrueWithOptionAIfMemberListIsNotEmpty() {
        List<Villager> congregation = new ArrayList<>();
        congregation.add(new Villager("A", true, 0, 0, 0, 0, "B"));
        activeGame.getSect().setCongregation(congregation);
        boolean result = gameLogic.templeActions('a');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionAIfMemberListIsEmpty() {
        List<Villager> congregation = new ArrayList<>();
        activeGame.getSect().setCongregation(congregation);
        boolean result = gameLogic.templeActions('a');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionAIfMemberListIsNull() {
        activeGame.getSect().setCongregation(null);
        boolean result = gameLogic.templeActions('a');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsTrueWithOptionBAndHighCharisma() {
        Player player = activeGame.getPlayer();
        player.setCharisma(1000);
        boolean result = gameLogic.templeActions('b');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionBAndLowCharisma() {
        Player player = activeGame.getPlayer();
        player.setCharisma(3);
        boolean result = gameLogic.templeActions('b');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsTrueWithOptionCAndHighBalance() {
        Sect sect = activeGame.getSect();
        sect.setBalance(10000000);
        boolean result = gameLogic.templeActions('c');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionCAndLowBalance() {
        Sect sect = activeGame.getSect();
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
        int turnsBefore = activeGame.getNumberOfTurns();
        assertEquals(true, gameLogic.endTurn());
        int turnsAfter = activeGame.getNumberOfTurns();
        assertEquals(turnsAfter, turnsBefore + 1);
    }

    @Test
    public void getConfigFilesFunctionsAsExpected() {
        File[] result = gameLogic.getConfigFiles();
        assertNotNull(result);
        for (int i = 0; i < result.length; i++) {
            File resultFile = result[i];
            assertNull(resultFile);
        }

        for (int i = 0; i < result.length; i++) {
            result[i] = new File("src/test/filesfortests/testfile.txt");
            File resultFile = result[i];
            assertNotNull(resultFile);
        }
    }
}
