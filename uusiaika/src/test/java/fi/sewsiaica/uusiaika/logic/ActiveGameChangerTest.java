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
import fi.sewsiaica.uusiaika.domain.Player;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.io.FileNotFoundException;
import java.util.Arrays;
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
            throws FileNotFoundException {
        String[] names = {"AA", "AB"};
        activeGameChanger.updateConfigValues("", "", "");
        ActiveGame game = activeGameChanger.createNewActiveGame(names);

        assertEquals("Teemu P, Opettaja", game.getVillagers().get(3).getName()
                + ", " + game.getVillagers().get(2).getProfession());
    }

    @Test
    public void loadActiveGameIsNotImplementedAndReturnsNull() {
        assertEquals(null, activeGameChanger.loadActiveGame(""));
    }
}
