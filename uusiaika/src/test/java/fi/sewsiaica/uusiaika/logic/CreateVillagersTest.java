/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

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
        //Later from a file
        String[] namesForVillagers = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
        String[] professions = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z"};
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pickStringsAddsMoreStringsToArrayThanThereAreInSelectArray() {
        int quantity = 6;
        String[] selection = {"A", "B", "C", "D"};
        String[] result = testCreate.pickStrings(quantity, selection);
        String[] expected = {"A", "B", "C", "D", "A", "B"};
        
        assertArrayEquals(expected, result);
    }
    
}
