/*
 * Copyright (C) 2016 Ilja Häkkinen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.io.ReadFromInputStream;
import fi.sewsiaica.uusiaika.io.WriteFromOutputStream;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import fi.sewsiaica.uusiaika.logic.activegamechanger.ActiveGameChanger;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
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
public class HallOfFameHandlerTest {

    private HallOfFameHandler hofHandler;
    private ActiveGameChanger agc;

    public HallOfFameHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        MockRandom random = new MockRandom();
        Config config = new Config();
        agc = new ActiveGameChanger(random, config);
        agc.updateConfigValues(null, null, null);

        File hofFile = new File("src/test/filesfortests/hof_file.txt");
        hofHandler = new HallOfFameHandler(hofFile);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorInitiatesHOFListAndTreeMapCorrectly() {
        TreeMap<Integer, List<String>> hofMap = hofHandler.getHallOfFameMap();
        List<String> hofList = hofHandler.getHallOfFameList();
        List<String> resList = new ArrayList<>();

        Integer key = hofMap.lastKey();
        while (key != null) {
            List<String> valueList = hofMap.get(key);
            for (int i = 0; i < valueList.size(); i++) {
                String hofLine = key + ": " + valueList.get(i);
                resList.add(hofLine);
            }
            key = hofMap.lowerKey(key);
        }
        assertEquals(5, resList.size());
        assertEquals(5, hofList.size());
        assertEquals("444444: highscore", resList.get(0));
        assertEquals("444444: highscore", hofList.get(0));
        assertEquals("74445: second place", resList.get(1));
        assertEquals("74445: second place", hofList.get(1));
        assertEquals("13213: third", resList.get(2));
        assertEquals("13213: third", hofList.get(2));
        assertEquals("444: test444-1", resList.get(3));
        assertEquals("444: test444-1", hofList.get(3));
        assertEquals("444: test444-2", resList.get(4));
        assertEquals("444: test444-2", hofList.get(4));
    }

    @Test
    public void constructorCreatesNewHOFListAndTreeMapIfFileNotFound() {
        File hoffFile = new File("src/test/filesfortests/the_hoff_file.txt");
        if (hoffFile.exists()) {
            hoffFile.delete();
        }
        hofHandler = new HallOfFameHandler(hoffFile);
        TreeMap<Integer, List<String>> hofMap = hofHandler.getHallOfFameMap();
        List<String> hofList = hofHandler.getHallOfFameList();
        assertEquals(true, hofMap.isEmpty());
        assertEquals(true, hofList.isEmpty());
    }

    @Test
    public void loadHallOfFameMapFromFileCreatesNewListAndMapIfInvalidIStream()
            throws Exception {
        InputStream fooStream = null;
        hofHandler.loadHallOfFameMapFromFile(fooStream);
        TreeMap<Integer, List<String>> hofMap = hofHandler.getHallOfFameMap();
        List<String> hofList = hofHandler.getHallOfFameList();
        assertEquals(true, hofMap.isEmpty());
        assertEquals(true, hofList.isEmpty());
    }

    @Test
    public void updateHallOfFameFunctionsCorrectly() {
        File hoffFile = new File("src/test/filesfortests/the_hoff_file.txt");
        if (hoffFile.exists()) {
            hoffFile.delete();
        }
        hofHandler = new HallOfFameHandler(hoffFile);
        String[] names = {"pla", "sec"};
        ActiveGame activeGame = agc.createNewActiveGame(names);
        activeGame.endThisActiveGame(3);
        hofHandler.updateHallOfFame(322, activeGame);

        String[] names2 = {"a", "B"};
        activeGame = agc.createNewActiveGame(names2);
        activeGame.endThisActiveGame(2);
        hofHandler.updateHallOfFame(623, activeGame);

        String[] names3 = {"c", "D"};
        activeGame = agc.createNewActiveGame(names3);
        activeGame.endThisActiveGame(1);
        hofHandler.updateHallOfFame(441, activeGame);

        String[] names4 = {"e", "F"};
        activeGame = agc.createNewActiveGame(names4);
        activeGame.endThisActiveGame(2);
        hofHandler.updateHallOfFame(441, activeGame);

        int lastKey = hofHandler.getHallOfFameMap().lastKey();
        int firstKey = hofHandler.getHallOfFameMap().firstKey();
        assertEquals(623, lastKey);
        assertEquals(322, firstKey);
        List<String> hofList = hofHandler.getHallOfFameList();
        assertEquals(4, hofList.size());
        assertEquals("623: Turn: 1  a  B  Balance: 5000  Members: 0  "
                + "Ending condition: 2", hofList.get(0));
        assertEquals("441: Turn: 1  c  D  Balance: 5000  Members: 0  "
                + "Ending condition: 1", hofList.get(1));
        assertEquals("441: Turn: 1  e  F  Balance: 5000  Members: 0  "
                + "Ending condition: 2", hofList.get(2));
        assertEquals("322: Turn: 1  pla  sec  Balance: 5000  Members: 0  "
                + "Ending condition: 3", hofList.get(3));
        assertEquals(true, hoffFile.exists());
        hoffFile.delete();
    }

    @Test
    public void saveHallOfFameToFileFunctionsAsExpected() throws Exception {
        ReadFromInputStream rfis = new ReadFromInputStream();

        File hofDumpTest = new File("src/test/filesfortests/hofDumpTest.txt");
        if (hofDumpTest.exists()) {
            hofDumpTest.delete();
        }
        List<String> hofList = hofHandler.getHallOfFameList();
        boolean result = hofHandler.saveHallOfFameToFile(hofDumpTest);
        assertEquals(true, result);

        List<String> resList
                = rfis.yankTextFromFile(new FileInputStream(hofDumpTest));
        assertEquals(hofList.size(), resList.size());
        for (int i = 0; i < resList.size(); i++) {
            assertEquals(hofList.get(i), resList.get(i));
        }
        hofDumpTest.delete();
    }

    @Test
    public void updateHallOfFameListFunctionsAsExpected() {
        boolean result = hofHandler.updateHallOfFameList();
        assertEquals(true, result);
        TreeMap<Integer, List<String>> hofMap = hofHandler.getHallOfFameMap();
        hofMap.clear();
        result = hofHandler.updateHallOfFameList();
        assertEquals(false, result);
    }

    @Test
    public void ifMaxNumberOfHallOfFamersReachedHOFListUpdatedCorrectly() {
        File hofMaxLines = new File("src/test/filesfortests/hofMaxLines.txt");
        WriteFromOutputStream wfos = new WriteFromOutputStream();
        String content = "74445: second place\n"
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
                + "9: noob\n"
                + "7: noob";
        wfos.dumpStringToTextFile(hofMaxLines, content);

        hofHandler = new HallOfFameHandler(hofMaxLines);
        assertEquals(15, hofHandler.getHallOfFameList().size());

        String[] names = {"pla", "sec"};
        ActiveGame activeGame = agc.createNewActiveGame(names);
        activeGame.endThisActiveGame(3);
        hofHandler.updateHallOfFame(999998, activeGame);
        hofHandler.updateHallOfFame(999999, activeGame);

        assertEquals(15, hofHandler.getHallOfFameList().size());
        assertEquals(false, hofHandler.getHallOfFameList().contains("7: noob"));
        assertEquals(false, hofHandler.getHallOfFameList().contains("9: noob"));
        String highScore = "999999: Turn: 1  pla  sec  Balance: 5000  "
                + "Members: 0  Ending condition: 3";
        assertEquals(true, hofHandler.getHallOfFameList().contains(highScore));
        highScore = "999998: Turn: 1  pla  sec  Balance: 5000  "
                + "Members: 0  Ending condition: 3";
        assertEquals(true, hofHandler.getHallOfFameList().contains(highScore));
    }
}
