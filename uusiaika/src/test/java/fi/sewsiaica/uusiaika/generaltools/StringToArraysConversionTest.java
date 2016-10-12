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
public class StringToArraysConversionTest {

    private StringToArraysConversion stac;

    public StringToArraysConversionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stac = new StringToArraysConversion();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void stringToIntArrayNormalOperation() {
        String string = "5,4,3223,+11,-1";
        int[] result = stac.stringToIntArray(string, 5);
        int[] expected = {5, 4, 3223, 11, -1};
        assertArrayEquals(expected, result);
    }

    @Test
    public void stringToIntArrayReturnsNullWithInvalidString() {
        String string = "5,4,3223,*11,-1";
        int[] result = stac.stringToIntArray(string, 5);
        assertNull(result);
    }

    @Test
    public void stringToBooleanArrayNormalOperation() {
        String string = "0,1,1,0,1";
        boolean[] result = stac.stringToBooleanArray(string, 5);
        boolean[] expected = {false, true, true, false, true};
        assertArrayEquals(expected, result);
    }

    @Test
    public void stringToBooleanArrayReturnsNullIfInvalidStringOrNull() {
        String string = "0,1,1,0,1.";
        boolean[] result = stac.stringToBooleanArray(string, 5);
        assertNull(result);
        string = "0,1,1,0,2";
        result = stac.stringToBooleanArray(string, 5);
        assertNull(result);
        
        string = null;
        result = stac.stringToBooleanArray(string, 5);
        assertNull(result);
    }
}
