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
package fi.sewsiaica.uusiaika.config;

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
public class LoadConfigTest {

    private LoadConfig loadConfig;
    private String[] variableNames;

    public LoadConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Config config = new Config();
        variableNames = config.getVariableNames();
        loadConfig = new LoadConfig(variableNames);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void loadIntValuesFromAFileThrowsExceptionIfFileNotFound() {
        boolean epicFail = false;
        try {
            loadConfig.loadIntValuesFromAFile(new File("foo"));
        } catch (Exception e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void loadIntValuesFromAFileReturnsNullIfNotValidFile()
            throws Exception {
        String name = "src/test/filesfortests/testfile.txt";
        
        Map<String, Integer> result = loadConfig.loadIntValuesFromAFile(
                new File(name));
        assertEquals(null, result);
    }
    
    @Test
    public void loadIntValuesFromAFileReturnsIntValuesMapCorrectly()
            throws Exception {
        String name = "src/test/filesfortests/test_values.txt";
        
        Map<String, Integer> result = loadConfig.loadIntValuesFromAFile(
                new File(name));
        assertNotEquals(null, result);
        int value = result.get("templeDivineRightMoneyReq");
        assertEquals(100000, value);
    }
    
    @Test
    public void loadListFromAFileThrowsExceptionIfFileNotFound() {
        boolean epicFail = false;
        try {
            loadConfig.loadListFromAFile(new File("foo"));
        } catch (Exception e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }
    
    @Test
    public void loadListFromAFileFunctionsCorrectly()
            throws Exception {
        String name = "src/test/filesfortests/testfile.txt";
        
        List<String> result = loadConfig.loadListFromAFile(new File(name));
        String expected = "Caxikymen: 20";
        assertEquals(expected, result.get(19));
    }
}
