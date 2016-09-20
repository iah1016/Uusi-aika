/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.ui;

import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.logic.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
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
public class TextbasedUITest {

    private Scanner reader;
    private Random random;
    private Game game;
    private TextbasedUI tui;
    private PrintStream ps;

    public TextbasedUITest() {
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
        String[] namesForVillagers = {"A", "B"};
        String[] professions = {"a", "b"};
        int[] maxNumbersForConversion = {3, 2, 2};
        
        random = new Random();
        game = new Game(random, namesForVillagers,
                professions, maxNumbersForConversion);
        reader = new Scanner(System.in, "ISO-8859-1");
        tui = new TextbasedUI(reader);
        tui.setGame(game);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createAllowedCharsCreatesHashSetNormally() {
        HashSet<Character> result = tui.createAllowedChars("abcd");

        HashSet<Character> expected = new HashSet<Character>();
        expected.add('a');
        expected.add('b');
        expected.add('c');
        expected.add('d');
        assertTrue(result.equals(expected));
    }

    @Test
    public void checkIfAllowedCharactersWorksNormally() {
        String input = "abad :aa d:d";
        HashSet<Character> allowed = tui.createAllowedChars("abcd :");

        assertEquals(true, tui.checkIfAllowedCharacters(input, allowed));
    }

    @Test
    public void checkIfAllowedCharactersFailsWithInvalidChars() {
        String input = "abad :aa d:dx";
        HashSet<Character> allowed = tui.createAllowedChars("abcd :");

        assertEquals(false, tui.checkIfAllowedCharacters(input, allowed));
    }

    @Test
    public void readInputAllowsOnlyAllowedChars() {
        String data = ""
                + "\ndiipa daapa"
                + "\ndiipa daapa"
                + "\ndab"
                + "\nöööö";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner mockReader = new Scanner(System.in, "ISO-8859-1");
        tui = new TextbasedUI(mockReader);
        tui.setGame(game);

        String result = tui.readInput(tui.createAllowedChars("abcd"));
        String expected = "dab";

        assertEquals(expected, result);
    }

    @Test
    public void chooseVillagerMenuChoosesCorrectVillager() {
        String[] names = {"A", "B", "C", "D"};
        String[] profs = {"A", "B", "C", "D"};
        int[] maxNumbersForConversion = {3, 2, 2};
        
        Game game2 = new Game(random, names, profs, maxNumbersForConversion);
        String data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner mockReader = new Scanner(System.in, "ISO-8859-1");
        tui = new TextbasedUI(mockReader);
        tui.setGame(game2);
        
        Villager villager = tui.chooseVillagerMenu();
        assertEquals("C", villager.getName());
    }
    
    @Test
    public void openingViewInitsPlayerAndSectProperly() {
        String data = "Jesus Garcia"
                + "\nThe Worshippers of Ancient Execution Methods";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner mockReader = new Scanner(System.in, "ISO-8859-1");
        tui = new TextbasedUI(mockReader);
        tui.setGame(game);
        
        tui.openingView();
        String result = game.getPlayer().getName() + " " + game.getSect().getName();
        String expected = "Jesus Garcia The Worshippers of Ancient Execution Methods";
        assertEquals(expected, result);
                
    }
}
