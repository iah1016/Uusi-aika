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
package fi.sewsiaica.uusiaika.logic.activegamechanger.saveloadgamehandlers;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.generaltools.StrIntMapAndStringListInterconversion;
import fi.sewsiaica.uusiaika.generaltools.StringAndStringListInterconversion;
import fi.sewsiaica.uusiaika.generaltools.StringToArraysConversion;
import fi.sewsiaica.uusiaika.io.ReadFromTextFile;
import fi.sewsiaica.uusiaika.logic.activegamechanger.CreateVillagers;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class handles the loading of a game from a file.
 *
 * @author iah1016
 */
public class LoadGameHandler {

    private final ReadFromTextFile readFromFile;
    private final StrIntMapAndStringListInterconversion listToStringIntMap;
    private final StringAndStringListInterconversion stringToList;
    private final StringToArraysConversion stringToArray;
    private final CreateVillagers createVillagers;
    private File saveFile;
    private List<String> allTheLinesList;
    private Map<String, Integer> configVariableMap;
    private final String[] playerAndSectNamesArray;
    private List<Villager> villagers;

    public LoadGameHandler(CreateVillagers createVillagers) {
        this.createVillagers = createVillagers;
        this.readFromFile = new ReadFromTextFile();
        this.listToStringIntMap = new StrIntMapAndStringListInterconversion();
        this.stringToList = new StringAndStringListInterconversion();
        this.stringToArray = new StringToArraysConversion();
        this.playerAndSectNamesArray = new String[2];
    }

    public boolean loadGame(File saveFile) {
        this.saveFile = saveFile;
        if (!loadListFromFileSucceeds()) {
            return false;
        }
        if (!processConfigVariableMapSucceeds(getLines(0, 36))) {
            return false;
        }
        if (!processVillagersSucceeds()) {
            return false;
        }
        loadPlayerAndSectNames(getLines(36, 38));
        return true;
    }

    private List<String> getLines(int init, int limitExclusive) {
        List<String> newStringList
                = allTheLinesList.subList(init, limitExclusive);

        return newStringList;
    }

    private boolean loadListFromFileSucceeds() {
        try {
            allTheLinesList = readFromFile.yankTextFromFile(saveFile);
        } catch (FileNotFoundException e) {
            return false;
        }
        return allTheLinesList.size() == 45;
    }

    private boolean processConfigVariableMapSucceeds(
            List<String> variableList) {
        configVariableMap
                = listToStringIntMap.convertStringListToStrIntMap(variableList);
        Config config = new Config();
        return config.checkValidityOfConfigVariableMap(configVariableMap);
    }

    private void loadPlayerAndSectNames(List<String> namesList) {
        playerAndSectNamesArray[0] = namesList.get(0);
        playerAndSectNamesArray[1] = namesList.get(1);
    }

    private boolean processVillagersSucceeds() {
        String nameLine = allTheLinesList.get(38);
        String profsLine = allTheLinesList.get(39);
        String boolLine = allTheLinesList.get(40);

        List<String> names = stringToList.convertStringToStringList(nameLine);
        List<String> profs = stringToList.convertStringToStringList(profsLine);
        boolean[] inSect = stringToArray.stringToBooleanArray(boolLine,
                names.size());
        List<int[]> attributes = createListOfVillagerAttributeArrays(
                names.size());
        return noElementInVillageCreationIsNullOrEmpty(names, profs, inSect,
                attributes);
    }

    private boolean noElementInVillageCreationIsNullOrEmpty(List<String> names,
            List<String> profs, boolean[] inSect, List<int[]> attributes) {
        if (names == null || profs == null || inSect == null
                || attributes == null) {
            return false;
        }
        if (names.isEmpty() || profs.isEmpty() || attributes.isEmpty()) {
            return false;
        }
        villagers = createVillagers.populateVillageWithLoadedVillagers(names,
                profs, inSect, attributes);
        return true;
    }

    private List<int[]> createListOfVillagerAttributeArrays(int size) {
        List<int[]> attributes = new ArrayList<>();
        List<String> lines = getLines(41, 45);
        for (int i = 0; i < lines.size(); i++) {
            int[] intArray = stringToArray.stringToIntArray(lines.get(i), size);
            if (intArray == null) {
                return null;
            }
            attributes.add(intArray);
        }
        return attributes;
    }

    public Map<String, Integer> getConfigVariableMap() {
        return configVariableMap;
    }

    public String[] getPlayerAndSectNamesArray() {
        return playerAndSectNamesArray;
    }

    public List<Villager> getVillagers() {
        return villagers;
    }
}
