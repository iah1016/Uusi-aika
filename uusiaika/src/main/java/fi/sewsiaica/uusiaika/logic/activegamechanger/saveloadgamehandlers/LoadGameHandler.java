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
import fi.sewsiaica.uusiaika.generaltools.GeneralTools;
import fi.sewsiaica.uusiaika.io.ReadFromInputStream;
import fi.sewsiaica.uusiaika.logic.activegamechanger.CreateVillagers;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class handles the loading of a game from a file.
 *
 * @author iah1016
 */
public class LoadGameHandler {

    private final ReadFromInputStream readFromInputStream;
    private final GeneralTools generalTools;
    private final CreateVillagers createVillagers;
    private File saveFile;
    private List<String> allTheLinesList;
    private Map<String, Integer> configVariableMap;
    private final String[] playerAndSectNamesArray;
    private List<Villager> villagers;

    /**
     * The constructor gets a CreateVillagers object as a parameter and creates
     * new instances for ReadFromTextFile and GeneralTools.
     *
     * @param createVillagers The CreateVillagers object given by the parent.
     */
    public LoadGameHandler(CreateVillagers createVillagers) {
        this.createVillagers = createVillagers;
        this.readFromInputStream = new ReadFromInputStream();
        this.generalTools = new GeneralTools();
        this.playerAndSectNamesArray = new String[2];
    }

    /**
     * This method checks the validity of the save file through various private
     * methods and loads the elements for an ActiveGame one by one.
     *
     * @param saveFile The save file that will be read by ReadFromTextFile.
     * @return Returns true if every element is successfully loaded.
     */
    public boolean loadingFromSaveFileSuccessful(File saveFile) {
        this.saveFile = saveFile;
        if (!loadListFromFileSucceeds()) {
            // Also returns false if the number of lines is not 45.
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

    /**
     * Get a subList from all the (non-comment or whitespace) lines.
     *
     * @param init The inclusive lower limit.
     * @param limitExclusive The exclusive upper limit.
     * @return Returns a String list, or null if the limits are out of bounds.
     */
    protected List<String> getLines(int init, int limitExclusive) {
        try {
            return allTheLinesList.subList(init, limitExclusive);
        } catch (Exception e) {
            return null;
        }
    }

    private boolean loadListFromFileSucceeds() {
        try {
            InputStream inputStream = new FileInputStream(saveFile);
            allTheLinesList = readFromInputStream.yankTextFromFile(inputStream);
        } catch (Exception e) {
            return false;
        }
        return allTheLinesList.size() == 45;
    }

    private boolean processConfigVariableMapSucceeds(List<String> varList) {
        configVariableMap
                = generalTools.convertStringListToStrIntMap(varList);
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

        List<String> names = generalTools.convertStringToStringList(nameLine);
        List<String> profs = generalTools.convertStringToStringList(profsLine);
        boolean[] inSect = generalTools.stringToBooleanArray(
                boolLine, names.size());
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
        villagers = createVillagers.populateVillageWithLoadedVillagers(names,
                profs, inSect, attributes);
        return true;
    }

    private List<int[]> createListOfVillagerAttributeArrays(int size) {
        List<int[]> attributes = new ArrayList<>();
        List<String> lines = getLines(41, 45);
        for (int i = 0; i < lines.size(); i++) {
            int[] intArray = generalTools.stringToIntArray(lines.get(i), size);
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
