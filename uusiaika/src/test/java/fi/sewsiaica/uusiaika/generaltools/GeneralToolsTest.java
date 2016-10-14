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

import java.util.ArrayList;
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
public class GeneralToolsTest {

    public GeneralToolsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void generalToolsConstructorFunctionsAsExpected() {
        boolean resultBoolean = false;
        GeneralTools genTools = new GeneralTools();
        ObjectTypeConversionChecker otcc
                = genTools.getObjectTypeConversionChecker();
        StrIntMapAndStringListInterconversion simasli
                = genTools.getStrIntMapAndStringListInterconversion();
        StrStrMapAndStringListInterconversion ssmasli
                = genTools.getStrStrMapAndStringListInterconversion();
        StringAndStringListInterconversion sasli
                = genTools.getStringAndStringListInterconversion();
        StringToArraysConversion stac
                = genTools.getStringToArraysConversion();
        
        resultBoolean = otcc.intCanBeConvertedToBoolean(2);
        assertEquals(false, resultBoolean);
        resultBoolean = otcc.intCanBeConvertedToBoolean(1);
        assertEquals(true, resultBoolean);
        
        int resultInt = -1;
        resultInt = simasli.returnValueAsIntOrZeroIfInvalidIntGiven("huuhaa");
        assertEquals(0, resultInt);
        resultInt = simasli.returnValueAsIntOrZeroIfInvalidIntGiven("42");
        assertEquals(42, resultInt);
        
        List<String> sList = new ArrayList<>();
        sList.add("huuhaa: joo");
        sList.add("bla: bal");
        Map<String, String> map = ssmasli.convertStringListToStrStrMap(sList);
        assertEquals("joobal", map.get("huuhaa") + map.get("bla"));
        
        String stringToBeStringList = "jj,AA,RR";
        sList = sasli.convertStringToStringList(stringToBeStringList);
        assertEquals("jjRR", sList.get(0) + sList.get(2));
     
        String stringToBeBooleanArray = "0,1,0,0,0";
        boolean[] bArray = stac.stringToBooleanArray(stringToBeBooleanArray, 5);
        assertEquals(false, bArray[0]);
        assertEquals(true, bArray[1]);
        assertEquals(false, bArray[2]);
        assertEquals(false, bArray[3]);
        assertEquals(false, bArray[4]);
    }
}
