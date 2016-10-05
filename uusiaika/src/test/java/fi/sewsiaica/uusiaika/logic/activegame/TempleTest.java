/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic.activegame;

import fi.sewsiaica.uusiaika.domain.*;
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
public class TempleTest {

    private final int playerCharisma = 10;
    private final int playerArgSkills = 10;
    private final int sectBalance = 5000;
    private final int sectExpenses = 1000;
    private final int sectMemberFee = 100;
    private final int templeSceptDecr = 10;
    private final int deathCultCharismaReq = 255;
    private final int divineRightMoneyReq = 100000;
    private Map<String, Integer> intValues;
    private Temple temple;
    private Player player;
    private Sect sect;

    public TempleTest() {
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
        intValues.put("templeSceptDecr", templeSceptDecr);
        intValues.put("templeDeathCultCharismaReq", deathCultCharismaReq);
        intValues.put("templeDivineRightMoneyReq", divineRightMoneyReq);
        temple = new Temple(intValues);
        player = new Player("Hessu", playerCharisma,
                playerArgSkills);
        sect = new Sect("Tunneli", sectBalance, sectExpenses,
                sectMemberFee);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void preachLowersEveryonesScepticism() {
        boolean result = true;
        List<Villager> congregation = sect.getCongregation();
        int initScept = 97;

        for (int i = 0; i < 10; i++) {
            congregation.add(new Villager("anon", true,
                    initScept, 6, 6, 6, "janari"));
        }
        temple.preach(player, sect);

        for (Villager member : congregation) {
            if (member.getScepticism() != initScept - templeSceptDecr) {
                result = false;
            }
        }
        assertEquals(true, result);
    }

    @Test
    public void offerSodaToAllMembersReturnsTrueWithReqValue() {
        player.setCharisma(deathCultCharismaReq);
        boolean result = temple.offerSodaToAllMembers(player);
        assertEquals(true, result);
    }

    @Test
    public void offerSodaToAllMembersReturnsFalseWithLowCharisma() {
        player.setCharisma(deathCultCharismaReq - 1);
        boolean result = temple.offerSodaToAllMembers(player);
        assertEquals(false, result);
    }

    @Test
    public void buyTicketToParadiseIslandReturnsTrueWithReqValue() {
        sect.setBalance(divineRightMoneyReq);
        boolean result = temple.buyTicketToParadiseIsland(player, sect);
        assertEquals(true, result);
    }

    @Test
    public void buyTicketToParadiseIslandReturnsFalseWithLowBalance() {
        sect.setBalance(divineRightMoneyReq - 1);
        boolean result = temple.buyTicketToParadiseIsland(player, sect);
        assertEquals(false, result);
    }
}
