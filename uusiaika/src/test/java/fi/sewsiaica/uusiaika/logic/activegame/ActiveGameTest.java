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
package fi.sewsiaica.uusiaika.logic.activegame;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.logic.activegamechanger.ActiveGameChanger;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
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
public class ActiveGameTest {

    private ActiveGame activeGame;

    public ActiveGameTest() {
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
        ActiveGameChanger agc = new ActiveGameChanger(random, config);
        try {
            agc.updateConfigValues("", "", "");
        } catch (FileNotFoundException e) {
        }
        String[] playerAndSectNames = {"AA", "BB"};
        activeGame = agc.createNewActiveGame(playerAndSectNames);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAndSetTargetVillagersFunctionAsExpected() {
        List<Villager> result = activeGame.getTargetVillagers();
        assertNotNull(result);
        
        activeGame.setTargetVillagers(null);
        result = activeGame.getTargetVillagers();
        assertNull(result);
        
        List<Villager> newTargets = new ArrayList<>();
        newTargets.add(activeGame.getVillagers().get(1));
        newTargets.add(activeGame.getVillagers().get(3));
        activeGame.setTargetVillagers(newTargets);
        result = activeGame.getTargetVillagers();
        assertEquals(2, result.size());
    }
    
    @Test
    public void toStringFunctionsAsExpected() {
        String result = activeGame.toString();
        String expected = "Turn: 1  AA  BB  Balance: 5000  Members: 0";
        assertEquals(expected, result);
    }
}
