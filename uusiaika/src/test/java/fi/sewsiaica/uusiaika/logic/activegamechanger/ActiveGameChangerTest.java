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
package fi.sewsiaica.uusiaika.logic.activegamechanger;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.io.File;
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
public class ActiveGameChangerTest {

    private ActiveGameChanger activeGameChanger;

    public ActiveGameChangerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        MockRandom random = new MockRandom();
        Config config = new Config();

        activeGameChanger = new ActiveGameChanger(random, config);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createNewActiveGameWorksAsExpectedWithEmptyConfigID()
            throws Exception {
        String[] names = {"AA", "AB"};
        activeGameChanger.updateConfigValues(null, null, null);
        ActiveGame game = activeGameChanger.createNewActiveGame(names);

        assertEquals("Teemu P, Opettaja", game.getVillagers().get(3).getName()
                + ", " + game.getVillagers().get(2).getProfession());
    }

    @Test
    public void loadActiveGameReturnsNullIfFileNotFoundOrInvalid() {
        File file = new File("foo");
        ActiveGame result = activeGameChanger.loadActiveGame(file);
        assertNull(result);
        file = new File("src/test/filesfortests/testfile.txt");
        result = activeGameChanger.loadActiveGame(file);
        assertNull(result);
    }

    @Test
    public void loadActiveGameReturnsValidActiveFileIfValidSaveFile() {
        File file = new File("src/test/filesfortests/test_savefile.txt");
        ActiveGame result = activeGameChanger.loadActiveGame(file);
        assertNotNull(result);
        String resString = result.getVillagers().get(1).getName() + " + "
                + result.getSect().getName();
        assertEquals("Urpo T + TestSect", resString);
    }

    @Test
    public void updateConfigValuesReturnsTrueWithValidFiles() throws Exception {
        File one = null;
        File two = null;
        File three = null;
        boolean result = activeGameChanger.updateConfigValues(one, two, three);
        assertEquals(true, result);
        one = new File("src/test/filesfortests/test_values.txt");
        two = new File("src/test/filesfortests/test_villagers.txt");
        three = new File("src/test/filesfortests/test_professions.txt");
        result = activeGameChanger.updateConfigValues(one, two, three);
        assertEquals(true, result);
    }

    @Test
    public void updateConfigValuesReturnsFalseWithInvalidConfigVariables()
            throws Exception {
        File one = new File("src/test/filesfortests/testfile.txt");
        File two = null;
        File three = null;
        boolean result = activeGameChanger.updateConfigValues(one, two, three);
        assertEquals(false, result);
    }

    @Test
    public void updateConfigValuesThrowsExceptionIfFileNotFound() {
        boolean fail = false;
        try {
            activeGameChanger.updateConfigValues(new File("foo"), null, null);
        } catch (Exception e) {
            fail = true;
        }
        assertEquals(true, fail);
        
        fail = false;
        try {
            activeGameChanger.updateConfigValues(null, new File("foo"), null);
        } catch (Exception e) {
            fail = true;
        }
        assertEquals(true, fail);
        
        fail = false;
        try {
            activeGameChanger.updateConfigValues(null, null, new File("foo"));
        } catch (Exception e) {
            fail = true;
        }
        assertEquals(true, fail);
    }
    
    @Test
    public void saveActiveGameFunctionsAsExpected() throws Exception {
        String[] names = {"AA", "AB"};
        activeGameChanger.updateConfigValues(null, null, null);
        ActiveGame activeGame = activeGameChanger.createNewActiveGame(names);
        activeGame.getSect().setBalance(1000000);
        String fileName = "src/test/filesfortests/test_save_active_game.txt";
        File saveFile = new File(fileName);
        boolean result = activeGameChanger.saveActiveGame(saveFile, activeGame);
        assertEquals(true, result);
        result = activeGameChanger.saveActiveGame(null, activeGame);
        assertEquals(false, result);
    }
}
