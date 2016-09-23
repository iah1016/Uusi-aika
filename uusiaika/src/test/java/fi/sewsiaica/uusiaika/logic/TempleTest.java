/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.ArrayList;
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

    private final int defaultPlayerCharisma = 10;
    private final int defaultPlayerArgSkills = 10;
    private final int defaultSectBalance = 5000;
    private final int defaultSectExpenses = 1000;
    private final int defaultSectMemberFee = 100;
    private final int defaultTempleSceptDecr = 10;
    private final int defaultDeathCultCharismaReq = 255;
    private final int defaultDivineRightMoneyReq = 100000;
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
        temple = new Temple(defaultTempleSceptDecr,
                defaultDeathCultCharismaReq,
                defaultDivineRightMoneyReq);
        player = new Player("Hessu", defaultPlayerCharisma,
                defaultPlayerArgSkills);
        sect = new Sect("Tunneli", defaultSectBalance, defaultSectExpenses,
                defaultSectMemberFee);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void preachLowersEveryonesScepticism() {
        boolean result = true;
        ArrayList<Villager> congregation = new ArrayList<Villager>();
        int initScept = 97;

        for (int i = 0; i < 10; i++) {
            congregation.add(new Villager("anon", true,
                    initScept, 6, 6, 6, "janari"));
        }
        temple.preach(player, congregation);

        for (Villager member : congregation) {
            if (member.getScepticism() != initScept - defaultTempleSceptDecr) {
                result = false;
            }
        }
        assertEquals(true, result);
    }

    @Test
    public void offerSodaToAllMembersReturnsTrueWithReqValue() {
        player.setCharisma(defaultDeathCultCharismaReq);
        boolean result = temple.offerSodaToAllMembers(player);
        assertEquals(true, result);
    }

    @Test
    public void offerSodaToAllMembersReturnsFalseWithLowCharisma() {
        player.setCharisma(defaultDeathCultCharismaReq - 1);
        boolean result = temple.offerSodaToAllMembers(player);
        assertEquals(false, result);
    }

    @Test
    public void buyTicketToParadiseIslandReturnsTrueWithReqValue() {
        sect.setBalance(defaultDivineRightMoneyReq);
        boolean result = temple.buyTicketToParadiseIsland(player, sect);
        assertEquals(true, result);
    }

    @Test
    public void buyTicketToParadiseIslandReturnsFalseWithLowBalance() {
        sect.setBalance(defaultDivineRightMoneyReq - 1);
        boolean result = temple.buyTicketToParadiseIsland(player, sect);
        assertEquals(false, result);
    }
}
