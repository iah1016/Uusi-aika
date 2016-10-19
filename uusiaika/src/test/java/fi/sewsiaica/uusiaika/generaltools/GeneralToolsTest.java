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
import java.util.HashMap;
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

    private GeneralTools genTools;

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
        genTools = new GeneralTools();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void convertMapToOrderedStringListFunctionsAsExpected() {
        String[] keys = {"one", "two", "three", "four", "five"};
        Map<String, String> testMap1 = new HashMap<>();
        testMap1.put("four", "v4");
        testMap1.put("three", "v4");
        testMap1.put("three", "v3");
        testMap1.put("five", "v5");
        testMap1.put("one", "v1");
        testMap1.put("two", "v2");
        List<String> orderedList = genTools
                .convertMapToOrderedStringList(testMap1, keys);
        assertEquals(5, orderedList.size());
        assertEquals("one: v1", orderedList.get(0));
        assertEquals("two: v2", orderedList.get(1));
        assertEquals("three: v3", orderedList.get(2));
        assertEquals("four: v4", orderedList.get(3));
        assertEquals("five: v5", orderedList.get(4));
    }

    @Test
    public void convertMapToStringListFunctionsAsExpected() {
        String[] keys = {"one", "two", "three", "four", "five"};
        Map<String, String> testMap1 = new HashMap<>();
        testMap1.put("four", "v-1");
        testMap1.put("three", "v3");
        testMap1.put("four", "v4");
        testMap1.put("five", "v5");
        testMap1.put("one", "v1");
        testMap1.put("two", "v2");
        List<String> list = genTools.convertMapToStringList(testMap1);
        assertEquals(5, list.size());
        assertEquals(true, list.contains("one: v1"));
        assertEquals(true, list.contains("two: v2"));
        assertEquals(true, list.contains("three: v3"));
        assertEquals(true, list.contains("four: v4"));
        assertEquals(true, list.contains("five: v5"));
    }

    @Test
    public void stringCanBeConvertedToIntFunctionsAsExpected() {
        String string = "";
        boolean result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = " ";
        result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = "20'";
        result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = "2.0";
        result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = "0.2";
        result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = ".2";
        result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(false, result);
        string = "0";
        result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(true, result);
        string = "-2";
        result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(true, result);
        string = "+2";
        result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(true, result);
        string = "200000000";
        result = genTools.stringCanBeConvertedToInt(string);
        assertEquals(true, result);
    }

    @Test
    public void intCanBeConvertedToBooleanFunctionsAsExpected() {
        boolean resultBoolean = genTools.intCanBeConvertedToBoolean(2);
        assertEquals(false, resultBoolean);
        resultBoolean = genTools.intCanBeConvertedToBoolean(1);
        assertEquals(true, resultBoolean);
    }

    @Test
    public void convertStringListToStrIntMapFunctionsAsExpected() {
        List<String> sList = new ArrayList<>();
        sList.add("huuhaa: -77");
        sList.add("bla: 42");
        Map<String, Integer> map = genTools.convertStringListToStrIntMap(sList);
        int result = map.get("huuhaa");
        assertEquals(-77, result);
        result = map.get("bla");
        assertEquals(42, result);
    }

    @Test
    public void convertStringListToStrStrMapFunctionsAsExpected() {
        List<String> sList = new ArrayList<>();
        sList.add("huuhaa: joo");
        sList.add("bla: bal");
        Map<String, String> map = genTools.convertStringListToStrStrMap(sList);
        assertEquals("joobal", map.get("huuhaa") + map.get("bla"));
    }

    @Test
    public void convertStringToStringListFunctionsAsExpected() {
        String stringToBeStringList = "jj,AA,RR";
        List<String> sList = genTools
                .convertStringToStringList(stringToBeStringList);
        assertEquals("jjRR", sList.get(0) + sList.get(2));
    }

    @Test
    public void convertStringListToStringFunctionsAsExpected() {
        List<String> list = new ArrayList<>();
        list.add("aaa aa");
        list.add("bb bbb");
        list.add("c");
        String result = genTools.convertStringListToString(list);
        String expected = "aaa aa\nbb bbb\nc";
        assertEquals(expected, result);
    }
    
    @Test
    public void stringToIntArrayFunctionsAsExpected() {
        String string = "5,4,3223,+11,-1";
        int[] result = genTools.stringToIntArray(string, 5);
        int[] expected = {5, 4, 3223, 11, -1};
        assertArrayEquals(expected, result);
    }

    @Test
    public void stringToBooleanArrayFunctionsAsExpected() {
        String stringToBeBooleanArray = "0,1,0,0,0";
        boolean[] bArray = genTools
                .stringToBooleanArray(stringToBeBooleanArray, 5);
        assertEquals(false, bArray[0]);
        assertEquals(true, bArray[1]);
        assertEquals(false, bArray[2]);
        assertEquals(false, bArray[3]);
        assertEquals(false, bArray[4]);
    }
}
