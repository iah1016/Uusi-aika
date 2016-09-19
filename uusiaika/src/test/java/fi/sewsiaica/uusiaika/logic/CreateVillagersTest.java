/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Villager;
import java.util.ArrayList;
import java.util.Random;
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

    private Random random;
    private CreateVillagers testCreate;

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
        random = new Random();
        testCreate = new CreateVillagers(random);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pickStringsAddsFewerStringsToArrayThanThereAreInSelectArray() {
        int quantity = 3;
        String[] selection = {"A", "B", "C", "D", "A", "B"};
        String[] result = testCreate.pickStrings(quantity, selection);
        String[] expected = {"A", "B", "C"};
        
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void pickStringsAddsMoreStringsToArrayThanThereAreInSelectArray() {
        int quantity = 6;
        String[] selection = {"A", "B", "C", "D"};
        String[] result = testCreate.pickStrings(quantity, selection);
        String[] expected = {"A", "B", "C", "D", "A", "B"};
        
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void pickStringsReturnsNullIfQuantityIsZeroOrLess() {
        int quantity = -1;
        String[] selection = {"A", "B", "C", "D", "A", "B"};
        String[] result = testCreate.pickStrings(quantity, selection);
        String[] expected = null;
        
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void pickRandomNumbersUnlikelyToPickSameNumberIn100KTries() {
        int quantity = 100000;
        int baseValue = 0;
        int bound = 2;
        int[] nums = testCreate.pickRandomNumbers(quantity, baseValue, bound);
        
        int zeroes = 0;
        int ones = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroes++;
            }
            if (nums[i] == 1) {
                ones++;
            }
        }
        
        assertEquals(false, Math.min(zeroes, ones) == 0);
    }
    
    @Test
    public void pickRandomNumbersReturnsNullIfQuantityIsZeroOrLess() {
        int quantity = 0;
        int baseValue = 0;
        int bound = 2;
        int[] nums = testCreate.pickRandomNumbers(quantity, baseValue, bound);
        
        assertEquals(null, nums);
    }
    
    @Test
    public void pickRandomNumbersReturnsNullIfBoundIsZeroOrLess() {
        int quantity = 100;
        int baseValue = 0;
        int bound = -1;
        int[] nums = testCreate.pickRandomNumbers(quantity, baseValue, bound);
        
        assertEquals(null, nums);
    }
    
    @Test
    public void populateVillageWorksProperly() {
        int quantity = 7;
        String[] names = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] profs = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r"};
        
        ArrayList<Villager> res = testCreate.populateVillage(quantity, names,
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
