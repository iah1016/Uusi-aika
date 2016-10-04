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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This class handles the configuration of the game. It will first try to load
 * the values from configuration files through a file handler class. If a file
 * is not present, the values will be loaded from the enum classes.
 *
 * @author iah1016
 */
public class Config {

    private final String[] variableNames;
    private final int[] defaultValues;
    private String configID;

    public Config() {
        variableNames = createArrayOfVariableNames();
        defaultValues = createArrayOfDefaultValues();
        configID = "";
    }

    public Map<String, Integer> loadIntValues() {
        Map<String, Integer> newValues = new HashMap<>();
        if (configID.isEmpty()) {
            for (int i = 0; i < variableNames.length; i++) {
                newValues.put(variableNames[i], defaultValues[i]);
            }
        }
        return newValues;
    }

    public List<String> loadVilNames() {
        List<String> namesForVillagers = new ArrayList<>();
        if (configID.isEmpty()) {
            for (DefaultVilNames villager : DefaultVilNames.values()) {
                namesForVillagers.add(villager.vilName());
            }
        }
        return namesForVillagers;
    }

    public List<String> loadProfessions() {
        List<String> professions = new ArrayList<>();
        if (configID.isEmpty()) {
            for (DefaultProfessions profession : DefaultProfessions.values()) {
                professions.add(profession.profName());
            }
        }
        return professions;
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

    public void setConfigID(String configID) {
        this.configID = configID;
    }
}
