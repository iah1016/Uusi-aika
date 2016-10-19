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
package fi.sewsiaica.uusiaika.logic.activegamechanger.saveloadgamehandlers;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import fi.sewsiaica.uusiaika.logic.activegamechanger.ActiveGameChanger;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.io.File;
import java.util.List;
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
public class SaveGameHandlerTest {

    private ActiveGame activeGame;
    private String[] variableNames;
    private ActiveGameChanger agc;
    private SaveGameHandler saveGameHandler;

    public SaveGameHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        Config config = new Config();
        variableNames = config.getVariableNames();

        MockRandom random = new MockRandom();
        agc = new ActiveGameChanger(random, config);
        agc.updateConfigValues(null, null, null);
        String[] names = {"Player A", "Sect A"};
        activeGame = agc.createNewActiveGame(names);

        saveGameHandler = new SaveGameHandler(variableNames);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void saveActiveGameReturnsTrueOnNormalOperation() {
        File saveFile = new File("src/test/filesfortests/emptydir/save.txt");
        boolean result = saveGameHandler.saveActiveGame(saveFile, activeGame);
        assertEquals(true, result);
    }

    @Test
    public void saveActiveGameReturnsFalseIfNullFileGiven() {
        boolean result = saveGameHandler.saveActiveGame(null, activeGame);
        assertEquals(false, result);
    }

    @Test
    public void updateConfigIntValuesUpdatesConfigValues() {
        Map<String, Integer> configIntValues = activeGame.getConfigIntValues();

        String[] attributes = {"playerCharisma", "playerArgSkills",
            "sectBalance", "sectExpenses", "sectMemberFee",
            "vilPopulation", "turnInitialNumberOfTurns"};
        int[] values = new int[7];
        for (int i = 0; i < 7; i++) {
            values[i] = configIntValues.get(attributes[i]);
        }

        activeGame.getPlayer().setCharisma(78);
        activeGame.getPlayer().setArgumentationSkills(95);
        activeGame.getSect().setBalance(12007);
        activeGame.getSect().setExpenses(29);
        activeGame.getSect().setMemberFee(99);
        activeGame.getVillagers().remove(0);
        activeGame.getTurnLogic()
                .nextTurn(activeGame.getPlayer(), activeGame.getSect(),
                        activeGame.getVillagers());

        File saveFile = new File("src/test/filesfortests/emptydir/save.txt");
        saveGameHandler.saveActiveGame(saveFile, activeGame);
        for (int i = 0; i < 7; i++) {
            int result = configIntValues.get(attributes[i]);
            assertNotEquals(values[i], result);
        }
        int result = configIntValues.get(attributes[0]);
        assertEquals(78, result);
        result = configIntValues.get(attributes[1]);
        assertEquals(95, result);
        result = configIntValues.get(attributes[2]);
        assertEquals(11978, result);
        result = configIntValues.get(attributes[3]);
        assertEquals(29, result);
        result = configIntValues.get(attributes[4]);
        assertEquals(99, result);
        result = configIntValues.get(attributes[5]);
        assertEquals(9, result);
        result = configIntValues.get(attributes[6]);
        assertEquals(2, result);
        
        saveFile.delete();
    }

    @Test
    public void saveFileIsInCorrectFormat() {
        File saveFile = new File("src/test/filesfortests/emptydir/save.txt");
        saveGameHandler.saveActiveGame(saveFile, activeGame);
        ActiveGame loadedActiveGame = agc.loadActiveGame(saveFile);
        assertNotNull(loadedActiveGame);
        
        saveFile.delete();
    }
    
    @Test
    public void villagerInSectDataIsSavedCorrectly() {
        activeGame.getVillagers().get(2).setInSect(true);
        activeGame.getVillagers().get(5).setInSect(true);
        activeGame.getVillagers().get(7).setInSect(true);
        
        File saveFile = new File("src/test/filesfortests/emptydir/save.txt");
        saveGameHandler.saveActiveGame(saveFile, activeGame);
        ActiveGame loadedActiveGame = agc.loadActiveGame(saveFile);
        List<Villager> loadedVillagers = loadedActiveGame.getVillagers();
        assertEquals(10, loadedVillagers.size());
        
        boolean result = loadedVillagers.get(0).isInSect();
        assertEquals(false, result);
        result = loadedVillagers.get(1).isInSect();
        assertEquals(false, result);
        result = loadedVillagers.get(2).isInSect();
        assertEquals(true, result);
        result = loadedVillagers.get(3).isInSect();
        assertEquals(false, result);
        result = loadedVillagers.get(4).isInSect();
        assertEquals(false, result);
        result = loadedVillagers.get(5).isInSect();
        assertEquals(true, result);
        result = loadedVillagers.get(6).isInSect();
        assertEquals(false, result);
        result = loadedVillagers.get(7).isInSect();
        assertEquals(true, result);
        result = loadedVillagers.get(8).isInSect();
        assertEquals(false, result);
        result = loadedVillagers.get(9).isInSect();
        assertEquals(false, result);
        
        saveFile.delete();
    }
}
