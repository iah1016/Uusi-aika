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
public class StrStrMapStringListInterconversionTest {

    private StrStrMapStringListInterconversion conversion;
    private Map<String, String> testMap;
    List<String> list;

    public StrStrMapStringListInterconversionTest() {
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
        testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        list = new ArrayList<>();
        list.add("key1: value1");
        list.add("key2: value2");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void convertStringListToStrStrMapReturnsMapCorrectly() {
        boolean allLinesMatch = false;
        Map<String, String> resMap
                = conversion.convertStringListToStrStrMap(list);

        List<String> resList = new ArrayList<>();
        for (String resMapKey : resMap.keySet()) {
            resList.add(resMapKey + ": " + resMap.get(resMapKey));
        }
        assertEquals(2, resList.size());
        assertEquals(true, list.containsAll(resList));
    }

}
