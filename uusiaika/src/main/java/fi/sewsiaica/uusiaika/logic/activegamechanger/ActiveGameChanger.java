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

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import fi.sewsiaica.uusiaika.logic.activegamechanger.saveloadgamehandlers.*;
import java.io.File;
import java.io.FileNotFoundException;
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
    private final CreateVillagers createVillagers;
    private final PlayerAndSectHandler playerAndSectHandler;
    private final SaveGameHandler saveGameHandler;
    private final LoadGameHandler loadGameHandler;
    private Map<String, Integer> configIntValues;
    private List<String> vilNamesForCreation;
    private List<String> professionsForCreation;

    /**
     * Random and Config objects are given to the constructor; CreateVillagers,
     * PlayerAndSecHandler, Save- and LoadGameHandler objects are created by the
     * constructor. All of them are immutable object variables.
     *
     * @param random Random is needed by CreateVillagers and the Conversion
     * classes.
     * @param config The object is immutable but its values are updated on every
     * active game change.
     */
    public ActiveGameChanger(Random random, Config config) {
        this.random = random;
        this.config = config;
        createVillagers = new CreateVillagers(random);
        playerAndSectHandler = new PlayerAndSectHandler();
        saveGameHandler = new SaveGameHandler();
        loadGameHandler = new LoadGameHandler(createVillagers);
    }

    /**
     * This method creates a new active game.
     *
     * @param playerAndSectNames Player and Sect names from the user's input.
     * @return Returns a new active game.
     */
    public ActiveGame createNewActiveGame(String[] playerAndSectNames) {
        playerAndSectHandler.createPlayerAndSect(playerAndSectNames,
                configIntValues);

        return new ActiveGame(random, configIntValues,
                createVillagers.populateVillage(configIntValues,
                        vilNamesForCreation, professionsForCreation),
                playerAndSectHandler.getPlayer(),
                playerAndSectHandler.getSect());
    }

    /**
     * This method loads a game from a file.
     *
     * @param saveFile The save file.
     * @return Returns the ActiveGame or null if loading is unsuccessful.
     */
    public ActiveGame loadActiveGame(File saveFile) {
        if (loadGameHandler.loadingFromSaveFileSuccessful(saveFile)) {
            
            setConfigIntValues(loadGameHandler.getConfigVariableMap());
            playerAndSectHandler.createPlayerAndSect(
                    loadGameHandler.getPlayerAndSectNamesArray(),
                    configIntValues);

            return new ActiveGame(random, configIntValues,
                    loadGameHandler.getVillagers(),
                    playerAndSectHandler.getPlayer(),
                    playerAndSectHandler.getSect());
        }
        return null;
    }

    /**
     * Config variable values, villager names and professions are updated before
     * creating a new active game.
     *
     * @param configFile The configuration variable values file.
     * @param villagerNamesFile The villager names file.
     * @param professionsFile The professions file.
     * @return Returns false if at least one of the files is invalid.
     * @throws FileNotFoundException Throws the FileNotFoundException.
     */
    public boolean updateConfigValues(File configFile,
            File villagerNamesFile, File professionsFile)
            throws FileNotFoundException {
        Map<String, Integer> tempIntValues = config.loadIntValues(configFile);
        List<String> tempVilNames = config.loadVilNames(villagerNamesFile);
        List<String> tempProfs = config.loadProfessions(professionsFile);

        if (tempIntValues == null || tempVilNames == null
                || tempProfs == null) {
            return false;
        }
        configIntValues = tempIntValues;
        vilNamesForCreation = tempVilNames;
        professionsForCreation = tempProfs;
        return true;
    }

    /**
     * This method is used for updating Config variables when loaded from a save
     * file.
     *
     * @param configIntValues loaded successfully from a save file.
     */
    public void setConfigIntValues(Map<String, Integer> configIntValues) {
        this.configIntValues = configIntValues;
    }
}
