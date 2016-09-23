/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.util.ArrayList;
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
        createVil = new CreateVillagers(random);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pickStringsAddsFewerStringsToArrayThanThereAreInSelectArray() {
        int quantity = 3;
        String[] selection = {"A", "B", "C", "D", "A", "B"};
        String[] result = createVil.pickStrings(quantity, selection);
        String[] expected = {"A", "B", "C"};

        assertArrayEquals(expected, result);
    }

    @Test
    public void pickStringsAddsMoreStringsToArrayThanThereAreInSelectArray() {
        int quantity = 6;
        String[] selection = {"A", "B", "C", "D"};
        String[] result = createVil.pickStrings(quantity, selection);
        String[] expected = {"A", "B", "C", "D", "A", "B"};

        assertArrayEquals(expected, result);
    }

    @Test
    public void pickStringsReturnsNullIfQuantityIsZeroOrLess() {
        int quantity = 0;
        String[] selection = {"A", "B", "C", "D", "A", "B"};
        String[] result = createVil.pickStrings(quantity, selection);
        String[] expected = null;

        assertArrayEquals(expected, result);
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
    public void pickRandomNumbersReturnsNullIfQuantityIsZeroOrLess() {
        int quantity = 0;
        int baseValue = 0;
        int bound = 2;
        int[] nums = createVil.pickRandomNumbers(quantity, baseValue, bound);

        assertEquals(null, nums);
    }

    @Test
    public void pickRandomNumbersReturnsNullIfBoundIsZeroOrLess() {
        int quantity = 100;
        int baseValue = 0;
        int bound = 0;
        int[] nums = createVil.pickRandomNumbers(quantity, baseValue, bound);

        assertEquals(null, nums);
    }

    @Test
    public void populateVillageWorksProperly() {
        int quantity = 7;
        String[] names = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] profs = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r"};

        ArrayList<Villager> res = createVil.populateVillage(quantity, names,
                profs);
        String expected = "AaBbCcDdEeFfGg";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i).getName());
            sb.append(res.get(i).getProfession());
        }
        assertEquals(expected, sb.toString());
    }

}
