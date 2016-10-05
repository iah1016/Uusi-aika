/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic.activegame.conversion;

import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.util.HashMap;
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
public class ConversionTest {

    private MockRandom random;
    private Map<String, Integer> intValues;
    private Conversion conv;

    public ConversionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        random = new MockRandom();
        intValues = new HashMap<>();
        intValues.put("convMaxNumberOfPersuasions", 15);
        intValues.put("convPersPlayerBound", 20);
        intValues.put("convPersVilBound", 20);
        intValues.put("convPersPlayerCharIncr", 2);
        intValues.put("convPersVilSelfAwDecr", 5);
        intValues.put("convPersVilSceptDecr", 5);
        conv = new Persuasion(random, intValues, 15, 20, 20);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isMaxedOutIsTrueIfMaxIsZero() {
        int value = 0;
        int max = 0;
        boolean result = conv.isMaxedOut(value, max);
        assertEquals(true, result);
    }

    @Test
    public void isMaxedOutIsTrueWithNegValues() {
        int value = -1;
        int max = 4;
        boolean result = conv.isMaxedOut(value, max);
        assertEquals(true, result);
    }

    @Test
    public void isMaxedOutIsFalseWithValuesLowerThanMax() {
        int value = 2;
        int max = 3;
        boolean result = conv.isMaxedOut(value, max);
        assertEquals(false, result);
    }

    @Test
    public void isMaxedOutIsTrueWithValuesEqualToMax() {
        int value = 3;
        int max = 3;
        boolean result = conv.isMaxedOut(value, max);
        assertEquals(true, result);
    }

    @Test
    public void isMaxedOutIsTrueWithValuesGreaterThanMax() {
        int value = 4;
        int max = 3;
        boolean result = conv.isMaxedOut(value, max);
        assertEquals(true, result);
    }
    
    @Test
    public void convSucceedsSucceedsWithEqualValues() {
        int playerBaseValue = 0;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 0;
        int villagerIncreaseLimit = 1;
        boolean result = conv.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(true, result);
    }

    @Test
    public void convSucceedsSucceedsWithPlayerHavingGreaterValue() {
        int playerBaseValue = 1;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 0;
        int villagerIncreaseLimit = 1;
        boolean result = conv.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(true, result);
    }

    @Test
    public void convSucceedsDoesNotSucceedWithPlayerHavingLowerValue() {
        int playerBaseValue = 0;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = 1;
        int villagerIncreaseLimit = 1;
        boolean result = conv.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(false, result);
    }

    @Test
    public void convSucceedsDoesNotSucceedWithNegBaseValue() {
        int playerBaseValue = 0;
        int playerIncreaseLimit = 1;
        int villagerBaseValue = -1;
        int villagerIncreaseLimit = 1;
        boolean result = conv.convSucceeds(playerBaseValue,
                villagerBaseValue, playerIncreaseLimit, villagerIncreaseLimit);
        assertEquals(false, result);
    }
}
