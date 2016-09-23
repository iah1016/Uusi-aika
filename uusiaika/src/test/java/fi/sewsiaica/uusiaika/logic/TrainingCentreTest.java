/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Player;
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
public class TrainingCentreTest {
    
    private Player player;
    private TrainingCentre tc;
    
    public TrainingCentreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        int defaultTrainingCharismaIncr = 11;
        int defaultTrainingArgSkillsIncr = 12;
        tc = new TrainingCentre(defaultTrainingCharismaIncr,
                defaultTrainingArgSkillsIncr);
        // String name, int charisma, int argSkills
        player = new Player("Pekka", 17, 13);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void applyForCharismaIncreasesCharismaCorrectly() {
        tc.applyForCharismaCourse(player);
        assertEquals(28, player.getCharisma());
    }
    
    @Test
    public void applyForDebateCourseIncreasesArgSkillsCorrectly() {
        tc.applyForDebateCourse(player);
        assertEquals(25, player.getArgSkills());
    }
}
