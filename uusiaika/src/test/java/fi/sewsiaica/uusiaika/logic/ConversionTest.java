/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
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
public class ConversionTest {
    
    private Random random;
    private Conversion conversion;
    private Player player;
    private Villager villager;

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
        // Later from a file
        int[] maxNumbersForConversion = {3, 2, 2};
        
        random = new Random();
        conversion = new Conversion(random, maxNumbersForConversion);
        
        // String name, int charisma, int argSkills
        player = new Player("Pekka", 10, 10);
        // String name, boolean inSect, int scepticism, int selfEsteem, 
        // int selfAwareness, int argSkills, String profession
        villager = new Villager("Matti", false, 10, 10, 10, 10, "kirkkoherra");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void persuasionMaxTimes() {
        for (int i = 0; i < 11; i++) {
            conversion.persuasion(player, villager);
        }
        assertEquals(conversion.getMaxNumberOfPersuasions(), villager.getNumberOfPersuasions());
    }

    @Test
    public void sermonMaxTimes() {
        for (int i = 0; i < 11; i++) {
            conversion.sermon(player, villager);
        }
        assertEquals(conversion.getMaxNumberOfSermons(), villager.getNumberOfSermons());
    }

    @Test
    public void accusationMaxTimes() {
        for (int i = 0; i < 11; i++) {
            conversion.accusation(player, villager);
        }
        assertEquals(conversion.getMaxNumberOfAccusations(), villager.getNumberOfAccusations());
    }

    @Test
    public void persuasionSuccessful() {
        player.setCharisma(1000);
        conversion.persuasion(player, villager);
        assertEquals(1002, player.getCharisma());
    }

    @Test
    public void persuasionNotSuccessful() {
        player.setCharisma(7);
        villager.setSelfAwareness(1000);
        conversion.persuasion(player, villager);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void sermonSuccessful() {
        player.setCharisma(1000);
        conversion.sermon(player, villager);
        assertEquals(1002, player.getCharisma());
    }

    @Test
    public void sermonNotSuccessful() {
        player.setCharisma(7);
        villager.setScepticism(1000);
        conversion.sermon(player, villager);
        assertEquals(7, player.getCharisma());
    }

    @Test
    public void accusationSuccessful() {
        player.setCharisma(1000);
        conversion.accusation(player, villager);
        assertEquals(1002, player.getCharisma());
    }

    @Test
    public void accusationNotSuccessful() {
        player.setCharisma(7);
        villager.setSelfEsteem(1000);
        conversion.accusation(player, villager);
        assertEquals(7, player.getCharisma());
    }
}
