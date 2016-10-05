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
package fi.sewsiaica.uusiaika.logic.activegamechanger;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.HashMap;
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
public class PlayerAndSectHandlerTest {

    private Map<String, Integer> intValues;
    private PlayerAndSectHandler playerAndSectHandler;

    public PlayerAndSectHandlerTest() {
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
        intValues.put("playerCharisma", 9);
        intValues.put("playerArgSkills", 7);
        intValues.put("sectBalance", 1004);
        intValues.put("sectExpenses", 12);
        intValues.put("sectMemberFee", 11);
        playerAndSectHandler = new PlayerAndSectHandler();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createPlayerAndSectFunctionsCorrectly() {
        String[] playerAndSectNames = {"Dolando", "Murica"};
        playerAndSectHandler.createPlayerAndSect(playerAndSectNames, intValues);
        Player player = playerAndSectHandler.getPlayer();
        Sect sect = playerAndSectHandler.getSect();
        
        String expectedNames = player.getName() + sect.getName();
        assertEquals("DolandoMurica", expectedNames);
        int expectedInt = player.getCharisma() - player.getArgSkills();
        assertEquals(2, expectedInt);
        expectedInt = sect.getBalance() + sect.getExpenses()
                - sect.getMemberFee();
        assertEquals(1005, expectedInt);
    }
    
    // Replace this with a proper test, once the method is implemented.
    @Test
    public void loadPlayerAndSectFromFileNotImplementedAndReturnsDummies() {
        playerAndSectHandler.loadPlayerAndSectFromFile("fooFile");
        Player player = playerAndSectHandler.getPlayer();
        Sect sect = playerAndSectHandler.getSect();
        String expectedNames = player.getName() + sect.getName();
        assertEquals("foobar", expectedNames);
    }
}
