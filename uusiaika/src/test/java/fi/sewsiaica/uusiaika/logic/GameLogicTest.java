/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.generaltools.GeneralTools;
import fi.sewsiaica.uusiaika.io.*;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.io.File;
import java.io.FileInputStream;
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
        } catch (Exception e) {

        }
        activeGame = gameLogic.getActiveGame();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newGameThrowsExceptionIfConfigFileIsInvalid() {
        boolean epicFail = false;
        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[0] = new File("foo");

        try {
            gameLogic.newGame(names);
        } catch (Exception e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void newGameThrowsExceptionIfVilNamesFileIsInvalid() {
        boolean epicFail = false;
        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[1] = new File("foo");

        try {
            gameLogic.newGame(names);
        } catch (Exception e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void newGameThrowsExceptionIfProfsFileIsInvalid() {
        boolean epicFail = false;
        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[2] = new File("foo");

        try {
            gameLogic.newGame(names);
        } catch (Exception e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void newGameReturnsTrueIfConfigFilesAreNull()
            throws Exception {
        File[] configFiles = gameLogic.getConfigFiles();
        configFiles[0] = null;
        configFiles[1] = null;
        configFiles[2] = null;

        boolean result = gameLogic.newGame(names);
        assertEquals(true, result);
    }

    @Test
    public void newGameReturnsTrueIfValidFilesAreUsed()
            throws Exception {
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
            throws Exception {
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
            throws Exception {
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

    @Test
    public void GameLogicActionsFunctionProperlyWhenActiveGameNotNull()
            throws Exception {
        gameLogic = new GameLogic(random, config);
        Villager dummy = new Villager("foo", true, 0, 0, 0, 0, "foo");

        assertEquals(false, gameLogic.conversion(dummy, 'b'));
        assertEquals(false, gameLogic.trainingCentreActions('a'));
        assertEquals(false, gameLogic.templeActions('a'));
        assertEquals(false, gameLogic.endTurn());

        gameLogic.newGame(names);
        assertEquals(true, gameLogic.conversion(dummy, 'b'));
        assertEquals(true, gameLogic.trainingCentreActions('a'));
        assertEquals(true, gameLogic.templeActions('a'));
        assertEquals(true, gameLogic.endTurn());
    }

    @Test
    public void changeLanguageFunctionsProperly() {
        boolean result = gameLogic.setActiveLanguage("suomi");
        assertEquals(true, result);
        assertEquals("suomi", gameLogic.getActiveLanguage().get("language"));
        result = gameLogic.setActiveLanguage("English");
        assertEquals(true, result);
        assertEquals("English", gameLogic.getActiveLanguage().get("language"));
    }

    @Test
    public void getNamesOfLanguagesFunctionsProperly() {
        List<String> names = gameLogic.getNamesOfLanguages();
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            sb.append(name);
        }
        assertEquals("Englishsuomi", sb.toString());
    }

    @Test
    public void changeCustomLanguageFunctionsProperly() {
        String name = "src/test/filesfortests/language_opr.txt";
        boolean result = gameLogic.changeCustomLanguage(new File(name));
        assertEquals(true, result);
        name = "src/test/filesfortests/language_vdl.txt";
        result = gameLogic.changeCustomLanguage(new File(name));
        assertEquals(true, result);
        name = "foobar";
        result = gameLogic.changeCustomLanguage(new File(name));
        assertEquals(false, result);
    }

    @Test
    public void saveActiveGameFunctionsAsExpected() throws Exception {
        String fileName = "src/test/filesfortests/test_save_active_game.txt";
        File saveFile = new File(fileName);
        boolean result = gameLogic.saveGame(saveFile);
        assertEquals(true, result);
        result = gameLogic.saveGame(null);
        assertEquals(false, result);
    }

    @Test
    public void endGameFunctionsAsExpected() throws Exception {
        ReadFromInputStream rfis = new ReadFromInputStream();
        WriteFromOutputStream wfos = new WriteFromOutputStream();
        File hofFile = new File("hall_of_fame.txt");
        hofFile.createNewFile();

        // Backup the data on the file.
        GeneralTools genTools = new GeneralTools();
        List<String> dataBackupList = rfis.yankTextFromFile(
                new FileInputStream(hofFile));
        // Rewrite the file.
        String tempContent = "74445: second place\n"
                + "444: test444-1\n"
                + "444444: highscore\n"
                + "444: test444-2\n"
                + "13213: third\n"
                + "999: huu\n"
                + "999: huu2\n"
                + "999: huu3\n"
                + "999: huu4\n"
                + "999: huu5\n"
                + "999: huu6\n"
                + "999: huu7\n"
                + "999: huu8\n"
                + "9: noob";
        wfos.dumpStringToTextFile(hofFile, tempContent);
        gameLogic = new GameLogic(random, config);
        gameLogic.newGame(names);
        activeGame = gameLogic.getActiveGame();
        
        // The test.
        List<String> hallOfFameListBefore = gameLogic.getHallOfFameList();
        int index = hallOfFameListBefore.size() - 1;
        String lastLine = hallOfFameListBefore.get(index);
        assertEquals("9: noob", lastLine);
        
        gameLogic.endTurn();
        gameLogic.endGame(4);
        List<String> hallOfFameList = gameLogic.getHallOfFameList();
        index = hallOfFameList.size() - 1;
        lastLine = hallOfFameList.get(index);
        assertEquals("0: Turn: 2  AA  AB  Balance: 4000  "
                + "Members: 0  Ending condition: 4", lastLine);
        assertEquals(4, activeGame.getGameEndingCondition());
        
        // Restore the backup data to the file.
        String original = genTools.convertStringListToString(dataBackupList);
        wfos.dumpStringToTextFile(hofFile, original);
    }
}
