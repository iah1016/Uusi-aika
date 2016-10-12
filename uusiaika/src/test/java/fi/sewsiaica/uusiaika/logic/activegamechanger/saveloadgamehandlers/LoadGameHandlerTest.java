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

import fi.sewsiaica.uusiaika.logic.activegamechanger.CreateVillagers;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.io.File;
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
public class LoadGameHandlerTest {

    private LoadGameHandler lgh;

    public LoadGameHandlerTest() {
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
        CreateVillagers createVillagers = new CreateVillagers(random);
        lgh = new LoadGameHandler(createVillagers);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void loadingFromSaveFileSuccessfulNormalOperation() {
        File file = new File("src/test/filesfortests/test_savefile.txt");
        boolean result = lgh.loadingFromSaveFileSuccessful(file);
        assertEquals(true, result);
    }
    
    @Test
    public void loadingFromSaveFileSuccessfulFails() {
        // File not found.
        String fileName = "src/test/filesfortests/test_foo.txt";
        File file = new File(fileName);
        boolean result = lgh.loadingFromSaveFileSuccessful(file);
        assertEquals(false, result);
        
        // File has too many lines.
        fileName = "src/test/filesfortests/test_savefile_too_long.txt";
        file = new File(fileName);
        result = lgh.loadingFromSaveFileSuccessful(file);
        assertEquals(false, result);
        
        // File has too few lines.
        fileName = "src/test/filesfortests/test_savefile_too_short.txt";
        file = new File(fileName);
        result = lgh.loadingFromSaveFileSuccessful(file);
        assertEquals(false, result);
                
        // File has an invalid variable name.
        fileName = "src/test/filesfortests/"
                + "test_savefile_invalid_variable_name.txt";
        file = new File(fileName);
        result = lgh.loadingFromSaveFileSuccessful(file);
        assertEquals(false, result);
        
        // File has an invalid boolean value. (Booleans displayed as Integers).
        fileName = "src/test/filesfortests/"
                + "test_savefile_invalid_boolean.txt";
        file = new File(fileName);
        result = lgh.loadingFromSaveFileSuccessful(file);
        assertEquals(false, result);
        
        // File has an invalid villager attribute.
        fileName = "src/test/filesfortests/"
                + "test_savefile_invalid_vil_attrib.txt";
        file = new File(fileName);
        result = lgh.loadingFromSaveFileSuccessful(file);
        assertEquals(false, result);
    }
    
    @Test
    public void getLinesFunctionsAsExpected() {
        File file = new File("src/test/filesfortests/test_savefile.txt");
        lgh.loadingFromSaveFileSuccessful(file);
        // There are precisely 45 lines in the allTheLinesList.
        List<String> resultList = lgh.getLines(44, 45);
        assertNotNull(resultList);
        resultList = lgh.getLines(44, 46);
        assertNull(resultList);
    }
}
