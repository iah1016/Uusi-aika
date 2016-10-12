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
package fi.sewsiaica.uusiaika.generaltools;

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
public class ObjectTypeConversionCheckerTest {

    private ObjectTypeConversionChecker otcc;
    
    public ObjectTypeConversionCheckerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        otcc = new ObjectTypeConversionChecker();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void stringCanBeConvertedToIntFunctionsProperly() {
        String string = "";
        boolean result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = " ";
        result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = "20'";
        result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = "2.0";
        result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = "0.2";
        result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = ".2";
        result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = "0";
        result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(true, result);
        string = "-2";
        result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(true, result);
        string = "+2";
        result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(true, result);
        string = "200000000";
        result = otcc.stringCanBeConvertedToInt(string);
        assertEquals(true, result);
    }
    
    @Test
    public void intCanBeConvertedToBooleanFunctionsProperly() {
        int one = 1;
        int zero = 0;
        int two = 2;
        int minusOne = -1;
        assertEquals(true, otcc.intCanBeConvertedToBoolean(one));
        assertEquals(true, otcc.intCanBeConvertedToBoolean(zero));
        assertEquals(false, otcc.intCanBeConvertedToBoolean(two));
        assertEquals(false, otcc.intCanBeConvertedToBoolean(minusOne));
    }
}
