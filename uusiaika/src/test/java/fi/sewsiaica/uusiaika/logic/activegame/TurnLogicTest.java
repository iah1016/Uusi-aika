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
    private Map<String, Integer> intValues;
    private final int defaultMaxNumberOfTurns = 100;
    private final int defaultSceptIncrPerTurn = 10;
    private final int defaultThresholdForScepticism = 200;

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
        intValues.put("turnMaxNumberOfTurns", 100);
        intValues.put("turnSceptIncrPerTurn", 10);
        intValues.put("turnThresholdForScepticism", 200);
        turnLogic = new TurnLogic(intValues);
        player = new Player("A", 100, 100);
        congregation = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            congregation.add(new Villager("A", true, 0, 0, 0, 0, "polis"));
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void nextTurnIncreasesNumberOfTurns() {
        Sect sect = new Sect("A", 10000, 1000, 100);
        turnLogic.nextTurn(player, sect);
        assertEquals(2, turnLogic.getNumberOfTurns());
    }

    @Test
    public void nextTurnReturnsTrueIfGameHasNotReachedMaxTurns() {
        Sect sect = new Sect("A", 10000, 1000, 100);
        assertEquals(true, turnLogic.nextTurn(player, sect));
    }

    @Test
    public void nextTurnReturnsFalseIfGameHasReachedMaxTurns() {
        intValues.put("turnInitialNumberOfTurns", 100);
        turnLogic = new TurnLogic(intValues);
        Sect sect = new Sect("A", 10000, 1000, 100);
        assertEquals(false, turnLogic.nextTurn(player, sect));
    }

    @Test
    public void nextTurnUpdatesCongregationCorrectly() {
        Sect sect = new Sect("A", 10000, 1000, 100);
        congregation.add(new Villager("B", true, 200, 0, 0, 0, "polis"));
        congregation.add(new Villager("A", true, 0, 0, 0, 0, "potatisgris"));
        sect.setCongregation(congregation);
        turnLogic.nextTurn(player, sect);
        assertEquals(11, sect.getCongregation().size());
    }

    @Test
    public void nextTurnUpdatesBalanceCorrectly() {
        Sect sect = new Sect("A", 0, 1666, 82);
        congregation.add(new Villager("B", true, 200, 0, 0, 0, "polis"));
        congregation.add(new Villager("A", true, 0, 0, 0, 0, "polis"));
        sect.setCongregation(congregation);
        turnLogic.nextTurn(player, sect);
        assertEquals(-764, sect.getBalance());
    }

    @Test
    public void nextTurnReturnsFalseIfBalanceIsNegAfterUpdate() {
        Sect sect = new Sect("A", 0, 1666, 82);
        congregation.add(new Villager("B", true, 200, 0, 0, 0, "potatisgris"));
        congregation.add(new Villager("A", true, 0, 0, 0, 0, "snut"));
        sect.setCongregation(congregation);
        assertEquals(false, turnLogic.nextTurn(player, sect));
    }

    @Test
    public void nextTurnReturnsTrueIfBalanceIsZeroAfterUpdate() {
        Sect sect = new Sect("A", 0, 1000, 100);
        sect.setCongregation(congregation);
        assertEquals(true, turnLogic.nextTurn(player, sect));
    }

    @Test
    public void membersInNextTurnReturnsVillagersThatRemainInSect() {
        for (int i = 0; i < 3; i++) {
            congregation.add(new Villager("Y", true, 300, 0, 0, 0, "exmember"));
            congregation.add(new Villager("T", true, 190, 0, 0, 0, "exmember"));
            congregation.add(new Villager("B", true, 10, 0, 0, 0, "sbrirro"));
            congregation.add(new Villager("C", true, 189, 0, 0, 0, "foo"));
        }
        List<Villager> newList = turnLogic.membersInNextTurn(congregation);
        StringBuilder resultSB = new StringBuilder();
        for (int i = 0; i < newList.size(); i++) {
            resultSB.append(newList.get(i).getName());
        }
        assertEquals("AAAAAAAAAABCBCBC", resultSB.toString());
    }

    @Test
    public void membersInNextTurnChangesLeavingMembersInSectStatus() {
        Villager tom = new Villager("Y", true, 190, 0, 0, 0, "exmember");
        congregation.add(tom);
        turnLogic.membersInNextTurn(congregation);

        assertEquals(false, tom.isInSect());
    }

    @Test
    public void membersInNextTurnReturnsEmptyListIfOldListIsNull() {
        List<Villager> oldList = null;
        List<Villager> newList = turnLogic.membersInNextTurn(oldList);
        assertEquals(0, newList.size());
    }

    @Test
    public void increaseScepticismWorksCorrectly() {
        Villager villager = new Villager("R", true, 7, 0, 0, 0,
                "special agent");
        turnLogic.increaseScepticism(villager);
        assertEquals(17, villager.getScepticism());
    }

    @Test
    public void membersPayFeeWorksCorrectly() {
        Sect sect = new Sect("A", 71502, 1002, 101);
        sect.setCongregation(congregation);
        turnLogic.membersPayFee(sect);
        assertEquals(72512, sect.getBalance());
    }

    @Test
    public void sectPaysExpensesWorksCorrectly() {
        Sect sect = new Sect("A", 71502, 1002, 100);
        turnLogic.sectPaysExpenses(sect);
        assertEquals(70500, sect.getBalance());
    }

    @Test
    public void balanceIsNotNegativeReturnsTrueIfBalanceEqualToZero() {
        Sect sect = new Sect("A", 0, 1000, 100);
        assertEquals(true, turnLogic.balanceIsNotNegative(sect));
    }

    @Test
    public void balanceIsNotNegativeReturnsTrueIfBalanceMoreThanZero() {
        Sect sect = new Sect("A", 1, 1000, 100);
        assertEquals(true, turnLogic.balanceIsNotNegative(sect));
    }

    @Test
    public void balanceIsNotNegativeReturnsFalseIfBalanceLessThanZero() {
        Sect sect = new Sect("A", -1, 1000, 100);
        assertEquals(false, turnLogic.balanceIsNotNegative(sect));
    }

    @Test
    public void sceptLessThanThresholdReturnsTrueIfValueLessThanThreshold() {
        assertEquals(true, turnLogic.sceptLessThanThreshold(199));
    }

    @Test
    public void sceptLessThanThresholdReturnsFalseIfValueEqualToThreshold() {
        assertEquals(false, turnLogic.sceptLessThanThreshold(200));
    }

    @Test
    public void sceptLessThanThresholdReturnsFalseIfValueMoreThanThreshold() {
        assertEquals(false, turnLogic.sceptLessThanThreshold(201));
    }

    @Test
    public void gameHasReachedMaxTurnReturnsFalseIfTurnsLessThanMax() {
        intValues.put("turnInitialNumberOfTurns", 99);
        turnLogic = new TurnLogic(intValues);
        assertEquals(false, turnLogic.gameHasReachedMaxTurns());
    }

    @Test
    public void gameHasReachedMaxTurnReturnsTrueIfTurnsEqualToMax() {
        intValues.put("turnInitialNumberOfTurns", 100);
        turnLogic = new TurnLogic(intValues);
        assertEquals(true, turnLogic.gameHasReachedMaxTurns());
    }

    @Test
    public void gameHasReachedMaxTurnReturnsTrueIfTurnsMoreThanMax() {
        intValues.put("turnInitialNumberOfTurns", 101);
        turnLogic = new TurnLogic(intValues);
        assertEquals(true, turnLogic.gameHasReachedMaxTurns());
    }

    @Test
    public void getNumberOfTurnsWorksCorrectly() {
        intValues.put("turnInitialNumberOfTurns", 11);
        turnLogic = new TurnLogic(intValues);
        assertEquals(11, turnLogic.getNumberOfTurns());
    }
}
