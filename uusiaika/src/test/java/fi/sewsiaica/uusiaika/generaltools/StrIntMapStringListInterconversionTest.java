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
public class StrIntMapStringListInterconversionTest {

    private StrIntMapStringListInterconversion conversion;
    private Map<String, Integer> testMap;
    List<String> list;

    public StrIntMapStringListInterconversionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        conversion = new StrIntMapStringListInterconversion();
        list = new ArrayList<>();
        list.add("key1: 570");
        list.add("key2: 2");
        list.add("key3: 9");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void convertStringListToStrStrMapReturnsValidMapCorrectly() {
        Map<String, Integer> resMap
                = conversion.convertStringListToStrIntMap(list);
        int result = resMap.get("key1") + resMap.get("key2")
                + resMap.get("key3");

        assertEquals(581, result);
        assertEquals(3, resMap.size());
    }

    @Test
    public void convertStringListToStrStrMapReturnsInvalidMapCorrectly() {
        list.add(":");
        list.add(": ");
        list.add("uusi1: ");
        list.add("uusi2");
        list.add("uusi3: ");
        list.add("uusi3: 27");
        list.add("uusi3: 85");
        list.add("uusi4: foo");
        int result = 0;

        Map<String, Integer> resMap
                = conversion.convertStringListToStrIntMap(list);
        for (String key : resMap.keySet()) {
            result += resMap.get(key);
        }

        assertEquals(666, result);
        assertEquals(7, resMap.size());
    }
    
    @Test
    public void returnValueAsIntOrZeroIfInvalidIntGivenFunctionsCorrectly() {
        int res = conversion.returnValueAsIntOrZeroIfInvalidIntGiven("fgdfds");
        assertEquals(0, res);
        res = conversion.returnValueAsIntOrZeroIfInvalidIntGiven(null);
        assertEquals(0, res);
        res = conversion.returnValueAsIntOrZeroIfInvalidIntGiven("");
        assertEquals(0, res);
        res = conversion.returnValueAsIntOrZeroIfInvalidIntGiven("1566");
        assertEquals(1566, res);
    }
}
