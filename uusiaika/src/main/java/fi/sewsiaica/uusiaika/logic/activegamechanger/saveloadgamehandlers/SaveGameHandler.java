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

import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.generaltools.GeneralTools;
import fi.sewsiaica.uusiaika.io.WriteFromOutputStream;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * This class handles the saving of the active game to a file.
 *
 * @author iah1016
 */
public class SaveGameHandler {

    private final GeneralTools generalTools;
    private final WriteFromOutputStream writeToFile;
    private final String[] variableNames;
    private ActiveGame activeGame;
    private StringBuilder contentStringBuilder;

    /**
     * The constructor is given a string array of the variable names that is
     * used for creating a StringList from the configuration values hash map.
     *
     * @param variableNames The keys of the ConfigIntValues hash map.
     */
    public SaveGameHandler(String[] variableNames) {
        this.generalTools = new GeneralTools();
        this.writeToFile = new WriteFromOutputStream();
        this.variableNames = variableNames;
    }

    /**
     * This method saves the current game to a text file.
     *
     * @param saveFile The save file.
     * @param activeGame The current game.
     * @return Returns true if the saving process succeeds.
     */
    public boolean saveActiveGame(File saveFile, ActiveGame activeGame) {
        this.contentStringBuilder = new StringBuilder();
        this.activeGame = activeGame;

        Map<String, Integer> configIntValuesMap = getConfigIntValuesMap();
        List<String> lines = generalTools.convertMapToOrderedStringList(
                configIntValuesMap, variableNames);
        contentStringBuilder.append(
                generalTools.convertStringListToString(lines));
        contentStringBuilder.append(addPlayerAndSectNamesToContent());
        contentStringBuilder.append(addVillagerInfoToContent());

        String content = contentStringBuilder.toString();
        return writeToFile.dumpStringToTextFile(saveFile, content);
    }

    private String addPlayerAndSectNamesToContent() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(activeGame.getPlayer().getName());
        sb.append("\n").append(activeGame.getSect().getName());
        return sb.toString();
    }

    private String addVillagerInfoToContent() {
        StringBuilder[] fields = new StringBuilder[7];
        fields = initStringBuilderArray(fields);
        List<Villager> villagers = activeGame.getVillagers();

        for (int i = 0; i < villagers.size(); i++) {
            fields = appendVillagerInfoToSBArray(fields, villagers.get(i));

            if (i < villagers.size() - 1) {
                fields = appendCommasToSBArray(fields);
            }
        }
        return appendAllStringsFromSBArray(fields);
    }

    private StringBuilder[] initStringBuilderArray(StringBuilder[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = new StringBuilder();
        }
        return array;
    }

    private StringBuilder[] appendVillagerInfoToSBArray(StringBuilder[] fields,
            Villager villager) {
        fields[0].append(villager.getName());
        fields[1].append(villager.getProfession());
        if (villager.isInSect()) {
            fields[2].append("1");
        } else {
            fields[2].append("0");
        }
        fields[3].append(villager.getScepticism());
        fields[4].append(villager.getSelfEsteem());
        fields[5].append(villager.getSelfAwareness());
        fields[6].append(villager.getArgSkills());

        return fields;
    }

    private StringBuilder[] appendCommasToSBArray(StringBuilder[] fields) {
        for (int i = 0; i < 7; i++) {
            fields[i].append(",");
        }
        return fields;
    }

    private String appendAllStringsFromSBArray(StringBuilder[] fields) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append("\n").append(fields[i].toString());
        }
        return sb.toString();
    }

    private Map<String, Integer> getConfigIntValuesMap() {
        Map<String, Integer> configIntValues = activeGame.getConfigIntValues();
        updateConfigIntValues(configIntValues);
        return configIntValues;
    }

    private void updateConfigIntValues(Map<String, Integer> configIntValues) {
        Player player = activeGame.getPlayer();
        Sect sect = activeGame.getSect();
        configIntValues.put("playerCharisma", player.getCharisma());
        configIntValues.put("playerArgSkills", player.getArgumentationSkills());
        configIntValues.put("sectBalance", sect.getBalance());
        configIntValues.put("sectExpenses", sect.getExpenses());
        configIntValues.put("sectMemberFee", sect.getMemberFee());
        configIntValues.put("vilPopulation", activeGame.getVillagers().size());
        configIntValues.put("turnInitialNumberOfTurns",
                activeGame.getNumberOfTurns());
    }

}
