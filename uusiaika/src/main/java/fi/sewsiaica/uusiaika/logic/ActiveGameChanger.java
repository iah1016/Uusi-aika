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
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The instance of this class creates the game, either a new one or from a file.
 *
 * @author iah1016
 */
public class ActiveGameChanger {

    private final Random random;
    private final Config config;
    private Map<String, Integer> configIntValues;
    private List<String> vilNamesForCreation;
    private List<String> professionsForCreation;

    /**
     * Both object variables are immutable and given to the constructor.
     *
     * @param random Random is needed by CreateVillagers and the Conversion
     * classes.
     * @param config The object is immutable but its values are updated on every
     * active game change.
     */
    public ActiveGameChanger(Random random, Config config) {
        this.random = random;
        this.config = config;
    }

    /**
     * This method creates a new active game from the selected Config files.
     *
     * @param playerAndSectNames Player and Sect names from the user's input.
     * @param configID If empty, the default values are used, as given in the
     * appropriate enum classes.
     * @return Returns a new active game.
     */
    public ActiveGame createNewActiveGame(String[] playerAndSectNames,
            String configID) {
        config.setConfigID(configID);
        updateConfigValues();

        CreateVillagers createVillagers = new CreateVillagers(random,
                configIntValues, vilNamesForCreation, professionsForCreation);
        PlayerAndSectHandler playerAndSectHandler = new PlayerAndSectHandler();
        playerAndSectHandler.createPlayerAndSect(playerAndSectNames,
                configIntValues);

        return new ActiveGame(random, configIntValues,
                createVillagers.populateVillage(),
                playerAndSectHandler.getPlayer(),
                playerAndSectHandler.getSect());
    }

    /**
     * The LoadGame feature is not yet implemented.
     *
     * @param saveName
     * @return
     */
    public ActiveGame loadActiveGame(String saveName) {
        // Do something.
        return null;
    }

    private void updateConfigValues() {
        configIntValues = config.loadIntValues();
        vilNamesForCreation = config.loadVilNames();
        professionsForCreation = config.loadProfessions();
    }

}
