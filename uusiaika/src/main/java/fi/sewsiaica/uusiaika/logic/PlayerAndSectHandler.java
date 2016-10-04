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

import fi.sewsiaica.uusiaika.domain.*;
import java.util.Map;

/**
 *
 * @author iah1016
 */
public class PlayerAndSectHandler {

    private Player player;
    private Sect sect;

    /**
     * The method gets the player and sect names in a String array-type
     * parameter, creates new Player and Sect objects.
     *
     * @param playerAndSectNames Player and Sect names in a String array.
     * @param configIntValues Attribute values from the configuration file.
     */
    public void createPlayerAndSect(String[] playerAndSectNames,
            Map<String, Integer> configIntValues) {
        this.player = new Player(playerAndSectNames[0],
                configIntValues.get("playerCharisma"),
                configIntValues.get("playerArgSkills"));
        this.sect = new Sect(playerAndSectNames[1],
                configIntValues.get("sectBalance"),
                configIntValues.get("sectExpenses"),
                configIntValues.get("sectMemberFee"));
    }
    
    /**
     * The LoadGame feature is not yet implemented.
     * @param fileName The attributes will be read from a text file.
     */
    public void loadPlayerAndSectFromFile(String fileName) {
        this.player = new Player("foo", 0, 0);
        this.sect = new Sect("bar", 0, 0, 0);
    }

    public Player getPlayer() {
        return player;
    }

    public Sect getSect() {
        return sect;
    }
}
