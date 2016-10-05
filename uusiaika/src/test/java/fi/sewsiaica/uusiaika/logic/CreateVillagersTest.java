/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.util.ArrayList;
import java.util.Arrays;
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
public class CreateVillagersTest {

    private MockRandom random;
    private Map<String, Integer> intValues;
    private List<String> names;
    private List<String> profs;
    private CreateVillagers createVil;

    public CreateVillagersTest() {
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
        intValues.put("vilPopulation", 10);
        intValues.put("vilBaseScepticism", 10);
        intValues.put("vilBaseSelfEs", 10);
        intValues.put("vilBaseSelfAw", 10);
        intValues.put("vilBaseArgSkills", 10);
        intValues.put("vilBoundValue", 51);
        names = new ArrayList<>();
        profs = new ArrayList<>();
        names.addAll(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));
        profs.addAll(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r"));
        createVil = new CreateVillagers(random);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pickStringsAddsFewerStringsToArrayThanThereAreInSelectArray() {
        int quantity = 3;
        String[] sArray = {"A", "B", "C", "D", "A", "B"};
        List<String> selection = new ArrayList<>();
        selection.addAll(Arrays.asList(sArray));
        String[] result = createVil.pickStrings(quantity, selection);
        String[] expected = {"A", "B", "C"};

        assertArrayEquals(expected, result);
    }

    @Test
    public void pickStringsAddsMoreStringsToArrayThanThereAreInSelectArray() {
        int quantity = 6;
        String[] sArray = {"A", "B", "C", "D"};
        List<String> selection = new ArrayList<>();
        selection.addAll(Arrays.asList(sArray));
        String[] result = createVil.pickStrings(quantity, selection);
        String[] expected = {"A", "B", "C", "D", "A", "B"};

        assertArrayEquals(expected, result);
    }

    @Test
    public void pickStringsReturnsZeroSizedArrayIfQuantityIsZero() {
        int quantity = 0;
        String[] sArray = {"A", "B", "C", "D", "A", "B"};
        List<String> selection = new ArrayList<>();
        selection.addAll(Arrays.asList(sArray));
        String[] result = createVil.pickStrings(quantity, selection);

        assertArrayEquals(new String[0], result);
    }
    
    @Test
    public void pickStringsReturnsZeroSizedArrayIfQuantityIsLessThanZero() {
        int quantity = -1;
        String[] sArray = {"A", "B", "C", "D", "A", "B"};
        List<String> selection = new ArrayList<>();
        selection.addAll(Arrays.asList(sArray));
        String[] result = createVil.pickStrings(quantity, selection);

        assertArrayEquals(new String[0], result);
    }
    
    @Test
    public void pickStringsReturnsNonEmptyArrayIfQuantityIsMoreThanZero() {
        int quantity = 1;
        String[] sArray = {"A", "B", "C", "D", "A", "B"};
        List<String> selection = new ArrayList<>();
        selection.addAll(Arrays.asList(sArray));
        String[] result = createVil.pickStrings(quantity, selection);

        assertNotEquals(0, result.length);
    }

    @Test
    public void pickRandomNumbersReturnsIntArray() {
        int quantity = 100;
        int baseValue = 55;
        int bound = 20;
        int[] result = createVil.pickRandomNumbers(quantity, baseValue, bound);
        boolean equal = true;

        for (int i = 0; i < quantity; i++) {
            if (result[i] != 57) {
                equal = false;
            }
        }
        assertEquals(true, equal);
    }

    @Test
    public void pickRandomNumbersReturnsEmptyArrayIfQuantityIsZero() {
        int quantity = 0;
        int baseValue = 0;
        int bound = 2;
        int[] result = createVil.pickRandomNumbers(quantity, baseValue, bound);

        assertArrayEquals(new int[0], result);
    }
    
    @Test
    public void pickRandomNumbersReturnsEmptyArrayIfQuantityIsNegInt() {
        int quantity = -1;
        int baseValue = 0;
        int bound = 2;
        int[] result = createVil.pickRandomNumbers(quantity, baseValue, bound);

        assertArrayEquals(new int[0], result);
    }
    
    @Test
    public void pickRandomNumbersReturnsNonEmptyArrayIfQuantityIsPosInt() {
        int quantity = 1;
        int baseValue = 0;
        int bound = 2;
        int[] result = createVil.pickRandomNumbers(quantity, baseValue, bound);

        assertNotEquals(0, result.length);
    }

    @Test
    public void pickRandomNumbersReturnsEmptyArrayIfBoundIsZero() {
        int quantity = 100;
        int baseValue = 0;
        int bound = 0;
        int[] result = createVil.pickRandomNumbers(quantity, baseValue, bound);

        assertArrayEquals(new int[0], result);
    }
    
    @Test
    public void pickRandomNumbersReturnsEmptyArrayIfBoundIsNegInt() {
        int quantity = 100;
        int baseValue = 0;
        int bound = -1;
        int[] result = createVil.pickRandomNumbers(quantity, baseValue, bound);

        assertArrayEquals(new int[0], result);
    }
    
    @Test
    public void pickRandomNumbersReturnsNonEmptyArrayIfBoundIsPosInt() {
        int quantity = 100;
        int baseValue = 0;
        int bound = 1;
        int[] result = createVil.pickRandomNumbers(quantity, baseValue, bound);

        assertNotEquals(0, result.length);
    }
    
    @Test
    public void populateVillageWorksProperly() {
        intValues.put("vilPopulation", 7);
        createVil = new CreateVillagers(random);
        List<Villager> res = createVil.populateVillage(intValues, names, profs);
        String expected = "AaBbCcDdEeFfGg";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i).getName());
            sb.append(res.get(i).getProfession());
        }
        assertEquals(expected, sb.toString());
    }

}
