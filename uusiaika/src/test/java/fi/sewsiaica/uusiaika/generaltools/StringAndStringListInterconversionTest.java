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
public class StringAndStringListInterconversionTest {

    private StringAndStringListInterconversion sasli;

    public StringAndStringListInterconversionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        sasli = new StringAndStringListInterconversion();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void convertStringToStringListFunctionsAsExpected() {
        List<String> res = sasli.convertStringToStringList("Aa a,bBB,c Cc,d D");
        StringBuilder resultSB = new StringBuilder();
        for (String string : res) {
            resultSB.append(string);
        }
        String expected = "Aa abBBc Ccd D";

        assertEquals(expected, resultSB.toString());
    }
    
    @Test
    public void convertStringToStringListReturnsNullIfStringNull() {
        List<String> res = sasli.convertStringToStringList(null);
        assertNull(res);
    }
}
