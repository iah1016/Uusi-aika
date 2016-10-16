/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic.activegame;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.ArrayList;
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
public class TurnLogicTest {

    private TurnLogic turnLogic;
    private Player player;
    private List<Villager> congregation;
    private List<Villager> allVillagers;
    private Map<String, Integer> intValues;

    public TurnLogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        intValues = new HashMap<>();
        intValues.put("turnInitialNumberOfTurns", 1);
        intValues.put("turnMaxNumberOfTurns", 77);
        intValues.put("turnSceptIncrPerTurn", 10);
        intValues.put("turnThresholdForScepticism", 200);
        turnLogic = new TurnLogic(intValues);
        player = new Player("A", 100, 100);
        congregation = new ArrayList<>();
        allVillagers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Villager vil = new Villager("A", true, 0, 0, 0, 0, "member");
            congregation.add(vil);
            allVillagers.add(vil);
            allVillagers.add(new Villager("B", false, 0, 0, 0, 0, "nonmember"));
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getNumberOfTurnsWorksCorrectly() {
        intValues.put("turnInitialNumberOfTurns", 11);
        turnLogic = new TurnLogic(intValues);
        assertEquals(11, turnLogic.getNumberOfTurns());
    }

    @Test
    public void nextTurnIncreasesNumberOfTurns() {
        Sect sect = new Sect("A", 10000, 1000, 100);
        turnLogic.nextTurn(player, sect, allVillagers);
        assertEquals(2, turnLogic.getNumberOfTurns());
    }

    @Test
    public void nextTurnReturnsTrueIfGameHasNotReachedMaxTurns() {
        intValues.put("turnInitialNumberOfTurns", 76);
        turnLogic = new TurnLogic(intValues);
        Sect sect = new Sect("A", 10000, 1000, 100);
        assertEquals(true, turnLogic.nextTurn(player, sect, allVillagers));
    }

    @Test
    public void nextTurnReturnsFalseIfGameHasReachedMaxTurns() {
        intValues.put("turnInitialNumberOfTurns", 77);
        turnLogic = new TurnLogic(intValues);
        Sect sect = new Sect("A", 10000, 1000, 100);
        assertEquals(false, turnLogic.nextTurn(player, sect, allVillagers));
    }

    @Test
    public void nextTurnReturnsFalseIfBalanceIsNegBeforeTurnChange() {
        intValues.put("turnInitialNumberOfTurns", 77);
        turnLogic = new TurnLogic(intValues);
        Sect sect = new Sect("A", -1, 0, 10000);
        assertEquals(false, turnLogic.nextTurn(player, sect, allVillagers));
    }

    @Test
    public void nextTurnReturnsTrueIfBalanceIsPositiveAfterUpdate() {
        Sect sect = new Sect("A", 0, 200, 82);
        Villager vil1 = new Villager("B", true, 190, 0, 0, 0, "Doubting Tom");
        Villager vil2 = new Villager("A", true, 0, 0, 0, 0, "member");
        allVillagers.add(vil1);
        congregation.add(vil1);
        allVillagers.add(vil2);
        congregation.add(vil2);
        sect.setCongregation(congregation);

        assertEquals(true, turnLogic.nextTurn(player, sect, allVillagers));
    }

    @Test
    public void nextTurnReturnsTrueIfBalanceIsZeroAfterUpdate() {
        Sect sect = new Sect("A", 0, 902, 82);
        Villager vil1 = new Villager("B", true, 190, 0, 0, 0, "Doubting Tom");
        Villager vil2 = new Villager("A", true, 0, 0, 0, 0, "member");
        allVillagers.add(vil1);
        congregation.add(vil1);
        allVillagers.add(vil2);
        congregation.add(vil2);
        sect.setCongregation(congregation);

        assertEquals(true, turnLogic.nextTurn(player, sect, allVillagers));
    }

    @Test
    public void nextTurnReturnsFalseIfBalanceIsNegativeAfterUpdate() {
        Sect sect = new Sect("A", 0, 903, 82);
        Villager vil1 = new Villager("B", true, 190, 0, 0, 0, "Doubting Tom");
        Villager vil2 = new Villager("A", true, 0, 0, 0, 0, "member");
        allVillagers.add(vil1);
        congregation.add(vil1);
        allVillagers.add(vil2);
        congregation.add(vil2);
        sect.setCongregation(congregation);

        assertEquals(false, turnLogic.nextTurn(player, sect, allVillagers));
    }

    @Test
    public void nextTurnUpdatesCongregationCorrectly() {
        Sect sect = new Sect("A", 10000, 1000, 100);
        Villager vil1 = new Villager("B", true, 190, 0, 0, 0, "Doubting Tom");
        Villager vil2 = new Villager("A", true, 0, 0, 0, 0, "member");
        allVillagers.add(vil1);
        congregation.add(vil1);
        allVillagers.add(vil2);
        congregation.add(vil2);
        sect.setCongregation(congregation);

        int membersOutOfVillagers = 0;
        for (Villager villagers : allVillagers) {
            if (villagers.isInSect()) {
                membersOutOfVillagers++;
            }
        }
        assertEquals(12, membersOutOfVillagers);
        assertEquals(12, sect.getCongregation().size());

        turnLogic.nextTurn(player, sect, allVillagers);
        membersOutOfVillagers = 0;
        for (Villager villagers : allVillagers) {
            if (villagers.isInSect()) {
                membersOutOfVillagers++;
            }
        }
        assertEquals(11, membersOutOfVillagers);
        assertEquals(11, sect.getCongregation().size());
    }

    @Test
    public void nextTurnUpdatesBalanceCorrectly() {
        Sect sect = new Sect("A", 0, 1666, 82);
        Villager vil1 = new Villager("B", true, 190, 0, 0, 0, "Doubting Tom");
        Villager vil2 = new Villager("A", true, 0, 0, 0, 0, "member");
        allVillagers.add(vil1);
        congregation.add(vil1);
        allVillagers.add(vil2);
        congregation.add(vil2);
        sect.setCongregation(congregation);

        turnLogic.nextTurn(player, sect, allVillagers);
        assertEquals(-764, sect.getBalance());
    }

    @Test
    public void nextTurnResetsVillagersConversionNumbers() {
        for (Villager villager : allVillagers) {
            villager.setNumberOfPersuations(2);
            villager.setNumberOfSermons(2);
            villager.setNumberOfAccusations(2);
        }
        Sect sect = new Sect("A", 10000, 1000, 100);
        sect.setCongregation(congregation);
        turnLogic.nextTurn(player, sect, allVillagers);

        int totalConversions = 0;
        for (Villager villager : allVillagers) {
            totalConversions += villager.getNumberOfPersuasions();
            totalConversions += villager.getNumberOfSermons();
            totalConversions += villager.getNumberOfAccusations();
        }
        assertEquals(0, totalConversions);
    }

    @Test
    public void nextTurnIncreasesMembersScepticism() {
        int expected = 100;
        Sect sect = new Sect("A", 10000, 1000, 100);
        sect.setCongregation(congregation);
        turnLogic.nextTurn(player, sect, allVillagers);
        int result = 0;
        for (Villager member : congregation) {
            result += member.getScepticism();
        }
        assertEquals(expected, result);
    }
}
