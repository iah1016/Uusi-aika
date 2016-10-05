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
package fi.sewsiaica.uusiaika.config;

import fi.sewsiaica.uusiaika.config.defaultvalues.DefaultVilNames;
import fi.sewsiaica.uusiaika.config.defaultvalues.DefaultVariableValues;
import fi.sewsiaica.uusiaika.config.defaultvalues.DefaultProfessions;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles the configuration of the game. It will first try to load
 * the values from configuration files through a file handler class. If a file
 * is not present, the values will be loaded from the enum classes.
 *
 * @author iah1016
 */
public class Config {

    private final LoadConfig loadConfig;
    private final String[] variableNames;
    private final int[] defaultValues;
    private final Map<String, Integer> defaultIntValuesMap;
    private final List<String> defaultVilNames;
    private final List<String> defaultProfessions;

    /**
     * This class provides the logic parts with the variable values, and also
     * the values for villager names and professions.
     */
    public Config() {
        variableNames = createArrayOfVariableNames();
        defaultValues = createArrayOfDefaultValues();
        defaultIntValuesMap = createDefaultIntValuesMap();
        defaultVilNames = createDefaultVilNames();
        defaultProfessions = createDefaultProfessions();
        loadConfig = new LoadConfig(variableNames);
    }

    /**
     * This method loads the Config values for the active game.
     *
     * @param configID The Config filename given as String.
     * @return Returns the values from the enum class if the ID is empty and
     * from a file otherwise.
     * @throws FileNotFoundException Throws FileNotFoundException.
     */
    public Map<String, Integer> loadIntValues(String configID) throws
            FileNotFoundException {
        if (configID.isEmpty()) {
            return defaultIntValuesMap;
        } else {
            return loadConfig.loadIntValuesFromAFile(configID);
        }
    }

    /**
     * This method loads villager names for the populateVillage-method in
     * createVillagers.
     *
     * @param villagersID The Villager names filename given as String.
     * @return Returns vilNames from the enum class if the ID is empty and from
     * a file otherwise.
     * @throws FileNotFoundException Throws FileNotFoundException.
     */
    public List<String> loadVilNames(String villagersID) throws
            FileNotFoundException {
        if (villagersID.isEmpty()) {
            return defaultVilNames;
        }
        return loadConfig.loadListFromAFile(villagersID);
    }

    /**
     * This method loads professions for the populateVillage-method in
     * createVillagers.
     *
     * @param professionsID The professions filename given as String.
     * @return Returns professions from the enum class if the ID is empty and
     * from a file otherwise.
     * @throws FileNotFoundException Throws FileNotFoundException.
     */
    public List<String> loadProfessions(String professionsID) throws
            FileNotFoundException {
        if (professionsID.isEmpty()) {
            return defaultProfessions;
        }
        return loadConfig.loadListFromAFile(professionsID);
    }

    public String[] getVariableNames() {
        return variableNames;
    }

    private String[] createArrayOfVariableNames() {
        int i = 0;
        String[] array = new String[VariableNames.values().length];
        for (VariableNames variable : VariableNames.values()) {
            array[i] = variable.varName();
            i++;
        }
        return array;
    }

    private int[] createArrayOfDefaultValues() {
        int i = 0;
        int[] array = new int[DefaultVariableValues.values().length];
        for (DefaultVariableValues variable : DefaultVariableValues.values()) {
            array[i] = variable.value();
            i++;
        }
        return array;
    }

    private Map<String, Integer> createDefaultIntValuesMap() {
        Map<String, Integer> valueMap = new HashMap<>();
        for (int i = 0; i < variableNames.length; i++) {
            valueMap.put(variableNames[i], defaultValues[i]);
        }
        return valueMap;
    }

    private List<String> createDefaultVilNames() {
        List<String> namesForVillagers = new ArrayList<>();
        for (DefaultVilNames villager : DefaultVilNames.values()) {
            namesForVillagers.add(villager.vilName());
        }
        return namesForVillagers;
    }

    private List<String> createDefaultProfessions() {
        List<String> professions = new ArrayList<>();
        for (DefaultProfessions profession : DefaultProfessions.values()) {
            professions.add(profession.profName());
        }
        return professions;
    }
}
