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
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.domain.Player;
import fi.sewsiaica.uusiaika.domain.Sect;
import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import fi.sewsiaica.uusiaika.toolsfortests.MockRandom;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
public class GameLogicActionsForActiveGameTest {
    
    private MockRandom random;
    private Config config;
    private GameLogic gameLogic;
    private String[] names;
    private ActiveGame activeGame;
    private GameLogicActionsForActiveGame actions;
    
    public GameLogicActionsForActiveGameTest() {
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
        config = new Config();
        gameLogic = new GameLogic(random, config);
        names = new String[2];
        names[0] = "AA";
        names[1] = "AB";
        try {
            gameLogic.newGame(names);
        } catch (FileNotFoundException e) {

        }
        activeGame = gameLogic.getActiveGame();
        actions = gameLogic.getActionsForActiveGame();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void actionsReturnFalseIfActiveGameIsNull() {
        gameLogic = new GameLogic(random, config);
        actions = gameLogic.getActionsForActiveGame();
        
        Villager dummy = new Villager("foo", true, 0, 0, 0, 0, "foo");
        
        assertEquals(false, actions.conversion(dummy, 'b'));
        assertEquals(false, actions.templeActions('a'));
        assertEquals(false, actions.trainingCentreActions('a'));
        assertEquals(false, actions.endTurn());
    }
    
    
    @Test
    public void conversionDoesNotWorkWithOptionAIfAlreadyMaxedTries() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(17);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
        villager.setSelfAwareness(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, 'a');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionBIfAlreadyMaxedTries() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(11);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, 'b');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionCIfAlreadyMaxedTries() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(19);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, 'c');
        assertEquals(false, result);
    }

    @Test
    public void conversionWorksWithOptionA() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(100);
        villager.setSelfAwareness(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, 'a');
        assertEquals(true, result);
    }

    @Test
    public void conversionWorksWithOptionB() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(100);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, 'b');
        assertEquals(true, result);
    }

    @Test
    public void conversionWorksWithOptionC() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, 'c');
        assertEquals(true, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionAIfMaxIsZero() {
        Villager villager = activeGame.getVillagers().get(0);
        activeGame.getPersuasion().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(1);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(100);
        villager.setSelfAwareness(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, 'a');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionBIfMaxIsZero() {
        Villager villager = activeGame.getVillagers().get(0);
        activeGame.getSermon().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(100);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, 'b');
        assertEquals(false, result);
    }

    @Test
    public void conversionDoesNotWorkWithOptionCIfMaxIsZero() {
        Villager villager = activeGame.getVillagers().get(0);
        activeGame.getAccusation().setMaxNumberOfConversions(0);
        villager.setNumberOfPersuations(100);
        villager.setNumberOfSermons(100);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, 'c');
        assertEquals(false, result);
    }

    @Test
    public void conversionReturnsFalseIfOptionOtherThanABC() {
        Villager villager = activeGame.getVillagers().get(0);
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
        villager.setScepticism(7);
        activeGame.getPlayer().setCharisma(100);
        boolean result = actions.conversion(villager, ' ');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsTrueWithOptionAIfMemberListIsNotEmpty() {
        List<Villager> congregation = new ArrayList<>();
        congregation.add(new Villager("A", true, 0, 0, 0, 0, "B"));
        activeGame.getSect().setCongregation(congregation);
        boolean result = actions.templeActions('a');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionAIfMemberListIsEmpty() {
        List<Villager> congregation = new ArrayList<>();
        activeGame.getSect().setCongregation(congregation);
        boolean result = actions.templeActions('a');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionAIfMemberListIsNull() {
        activeGame.getSect().setCongregation(null);
        boolean result = actions.templeActions('a');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsTrueWithOptionBAndHighCharisma() {
        Player player = activeGame.getPlayer();
        player.setCharisma(1000);
        boolean result = actions.templeActions('b');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionBAndLowCharisma() {
        Player player = activeGame.getPlayer();
        player.setCharisma(3);
        boolean result = actions.templeActions('b');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsTrueWithOptionCAndHighBalance() {
        Sect sect = activeGame.getSect();
        sect.setBalance(10000000);
        boolean result = actions.templeActions('c');
        assertEquals(true, result);
    }

    @Test
    public void templeActionsReturnsFalseWithOptionCAndLowBalance() {
        Sect sect = activeGame.getSect();
        sect.setBalance(10);
        boolean result = actions.templeActions('c');
        assertEquals(false, result);
    }

    @Test
    public void templeActionsReturnsFalseWithInvalidOption() {
        boolean result = actions.templeActions(' ');
        assertEquals(false, result);
    }

    @Test
    public void trainingCentreActionsReturnsFalseWithInvalidOption() {
        boolean result = actions.trainingCentreActions(' ');
        assertEquals(false, result);
    }

    @Test
    public void trainingCentreActionsReturnsTrueWithOptionA() {
        boolean result = actions.trainingCentreActions('a');
        assertEquals(true, result);
    }

    @Test
    public void trainingCentreActionsReturnsTrueWithOptionB() {
        boolean result = actions.trainingCentreActions('b');
        assertEquals(true, result);
    }

    @Test
    public void endTurnReturnsTrueWhenTurnHasChanged() {
        int turnsBefore = activeGame.getNumberOfTurns();
        assertEquals(true, actions.endTurn());
        int turnsAfter = activeGame.getNumberOfTurns();
        assertEquals(turnsAfter, turnsBefore + 1);
    }
}
