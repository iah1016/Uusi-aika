/*
 * Copyright (C) 2016 Ilja Häkkinen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fi.sewsiaica.uusiaika.logic.activegame;

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
public class ScoreHandlerTest {
    
    private ScoreHandler scoreHandler;
    
    public ScoreHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        scoreHandler = new ScoreHandler();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initValueForScoreIsZero() {
        assertEquals(0, scoreHandler.getScore());
    }
    
    @Test
    public void endScoreIsNotZeroIfNormalEnding() {
        // [0] Ending condition, [1] player charisma, [2] sect's balance,
        // [3] congregation size, [4] turns played, [5] max turns, 
        // [6] charisma needed for the condition 2, 
        // [7] balance needed for the condition 3.
        int[] elements0 = {1, 0, 0, 1, 100, 100, 600, 10000};
        int score = scoreHandler.calculateScore(elements0);
        assertEquals(2, score);
        int[] elements1 = {1, 0, -1, 1, 1, 100, 600, 10000};
        score = scoreHandler.calculateScore(elements1);
        assertEquals(2, score);
        int[] elements2 = {1, 0, 2300, 1, 1, 100, 600, 10000};
        score = scoreHandler.calculateScore(elements2);
        assertEquals(23, score);
        int[] elements3 = {2, 600, 0, 0, 99, 100, 600, 10000};
        score = scoreHandler.calculateScore(elements3);
        assertEquals(100, score);
        int[] elements4 = {2, 880, 40000, 11, 65, 100, 600, 10000};
        score = scoreHandler.calculateScore(elements4);
        assertEquals(65730, score);
        int[] elements5 = {3, 0, 10000, 0, 99, 100, 600, 10000};
        score = scoreHandler.calculateScore(elements5);
        assertEquals(120, score);
        int[] elements6 = {3, 77, 89000, 4, 65, 100, 600, 10000};
        score = scoreHandler.calculateScore(elements6);
        assertEquals(43455, score);
    }
    
    @Test
    public void calculateScoreReturnsZeroScoreIfInvalidElementsArrayGiven() {
        int[] elements = {1, 100, 1000, 10, 100, 100, 600};
        int score = scoreHandler.calculateScore(elements);
        assertEquals(0, score);
        int[] elements2 = {1, 100, 1000, 10, 100, 100, 600, 10000, 9};
        score = scoreHandler.calculateScore(elements2);
        assertEquals(0, score);
        int[] elements3 = {1, 100, 1000, 10, 100, 100, 600, 10000};
        score = scoreHandler.calculateScore(elements3);
        assertNotEquals(0, score);
    }
    
    @Test
    public void calculateScoreReturnsZeroIfDefaultCondition() {
        int[] elements = {0, 100, 1000, 10, 100, 100, 600, 10000};
        int score = scoreHandler.calculateScore(elements);
        assertEquals(0, score);
        int[] elements2 = {4, 100, 1000, 10, 100, 100, 600, 10000};
        score = scoreHandler.calculateScore(elements2);
        assertEquals(0, score);
        int[] elements3 = {1, 100, 1000, 10, 100, 100, 600, 10000};
        score = scoreHandler.calculateScore(elements3);
        assertNotEquals(0, score);
    }
}
