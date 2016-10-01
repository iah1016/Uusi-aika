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
public class MapStringListInterconversionTest {

    private MapStringListInterconversion conversion;
    private Map<String, String> testMap1;
    private Map<String, Integer> testMap2;
    List<String> list1;
    List<String> list2;

    public MapStringListInterconversionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        conversion = new StrStrMapStringListInterconversion();
        testMap1 = new HashMap<>();
        testMap1.put("key1", "value1");
        testMap1.put("key2", "value2");
        testMap1.put("key3", "value3");
        testMap2 = new HashMap<>();
        testMap2.put("key1", 1);
        testMap2.put("key2", 2);
        testMap2.put("key3", 3);
        testMap2.put("key4", 4);
        list1 = new ArrayList<>();
        list1.add("key1: value1");
        list1.add("key2: value2");
        list1.add("key3: value3");
        list2 = new ArrayList<>();
        list2.add("key1: 1");
        list2.add("key2: 2");
        list2.add("key3: 3");
        list2.add("key4: 4");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void convertMapToStringListFunctionsWithStrStrMap() {
        boolean allStringsMatch = false;
        List<String> resList = conversion.convertMapToStringList(testMap1);
        assertEquals(3, resList.size());
        assertEquals(true, list1.containsAll(resList));
    }

    @Test
    public void convertMapToStringListFunctionsWithStrIntMap() {
        boolean allStringsMatch = false;
        List<String> resList = conversion.convertMapToStringList(testMap2);
        if (list2.containsAll(resList)) {
            allStringsMatch = true;
        }
        assertEquals(4, resList.size());
        assertEquals(true, allStringsMatch);
    }

    @Test
    public void processOneStringFromStringListReturnsKeyValuePairCorrectly() {
        String testString = "defaultValue: 666";
        String[] res = conversion.processOneStringFromStringList(testString);
        assertEquals("defaultValue", res[0]);
        assertEquals("666", res[1]);
    }

    @Test
    public void processOneStringFromStringListReturnsNullValueIfNoValuePart() {
        String testString = "defaultValue: ";
        String[] res = conversion.processOneStringFromStringList(testString);
        assertEquals(null, res[1]);
        String testString2 = "defaultValue:";
        String[] res2 = conversion.processOneStringFromStringList(testString2);
        assertEquals(null, res2[1]);
        String testString3 = "defaultValue";
        String[] res3 = conversion.processOneStringFromStringList(testString3);
        assertEquals(null, res3[1]);
    }

    @Test
    public void processOneStringFromStringListReturnsNullIfNoKeyPart() {
        String testString = ": niceValue";
        String[] res = conversion.processOneStringFromStringList(testString);
        assertArrayEquals(null, res);
        String testString2 = ":";
        String[] res2 = conversion.processOneStringFromStringList(testString2);
        assertArrayEquals(null, res2);
    }
    
    @Test
    public void currentCharIsColonFunctionsCorrectly() {
        assertEquals(false, conversion.currentCharIsColon(' '));
        assertEquals(true, conversion.currentCharIsColon(':'));
    }
    
    @Test
    public void returnValueOrNullIfValueIsEmptyFunctionsCorrectly() {
        assertEquals("val", conversion.returnValueOrNullIfValueIsEmpty("val"));
        assertEquals(null, conversion.returnValueOrNullIfValueIsEmpty(""));
    }
}
