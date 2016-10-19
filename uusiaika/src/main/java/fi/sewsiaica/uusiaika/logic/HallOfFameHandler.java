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

import fi.sewsiaica.uusiaika.generaltools.GeneralTools;
import fi.sewsiaica.uusiaika.io.ReadFromInputStream;
import fi.sewsiaica.uusiaika.io.WriteFromOutputStream;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The instance of this class maintains the Hall of Fame.
 *
 * @author iah1016
 */
public class HallOfFameHandler {

    private TreeMap<Integer, List<String>> hallOfFameMap;
    private List<String> hallOfFameList;
    private final ReadFromInputStream readFromInputStream;
    private final WriteFromOutputStream writeFromOutputStream;
    private final GeneralTools generalTools;
    private final int maxNumberOfHallOfFamers;
    private int currentNumberOfHallOfFamers;
    private final String fileName;

    /**
     * The constructor creates new ReadFromInputStream, WriteFromOutputStream
     * and GeneralTools objects.
     */
    public HallOfFameHandler() {
        readFromInputStream = new ReadFromInputStream();
        writeFromOutputStream = new WriteFromOutputStream();
        generalTools = new GeneralTools();

        currentNumberOfHallOfFamers = 0;
        fileName = "/halloffame/hall_of_fame.txt";
        InputStream inputStream = getClass().getResourceAsStream(fileName);
        loadHallOfFameFromFile(inputStream);
        maxNumberOfHallOfFamers = Math.max(currentNumberOfHallOfFamers, 15);
    }

    /**
     * This method loads the Hall of Fame from the file and sets it to an
     * Integer,StringList-type TreeMap.
     *
     * @param inputStream The file where the Hall of Fame is located.
     */
    protected final void loadHallOfFameFromFile(InputStream inputStream) {
        List<String> lines;
        try {
            lines = readFromInputStream.yankTextFromFile(inputStream);
            currentNumberOfHallOfFamers = lines.size();
            hallOfFameMap = generalTools.convertStringListToIntStrListTreeMap(
                    lines);
        } catch (IOException | NullPointerException e) {
            hallOfFameMap = new TreeMap<>();
        }
    }

    public void updateHallOfFame(int score, ActiveGame activeGame) {
        updateHallOfFameMap(score, activeGame);
        updateHallOfFameList();
//        saveHallOfFameToFile(new File(fileName));
    }
    
    private void updateHallOfFameMap(int score, ActiveGame activeGame) {
        String activeGameInfo = activeGame.toString() + "  Ending condition: "
                + activeGame.getGameEndingCondition();
        List<String> valueList;
        if (hallOfFameMap.containsKey(score)) {
            valueList = hallOfFameMap.get(score);
            valueList.add(activeGameInfo);
        } else {
            valueList = new ArrayList<>();
            valueList.add(activeGameInfo);
            hallOfFameMap.put(score, valueList);
        }
    }

    private void updateHallOfFameList() {
        List<String> tempList = new ArrayList<>();
        Integer key = hallOfFameMap.firstKey();

        while (key != null) {
            List<String> valueList = hallOfFameMap.get(key);
            for (String value : valueList) {
                hallOfFameList.add(key + "  " + value);
            }
            key = hallOfFameMap.higherKey(key);
        }
        hallOfFameList = tempList.subList(0, maxNumberOfHallOfFamers);
    }
    
//    public boolean saveHallOfFameToFile(File file) {
//        String content = generalTools.convertStringListToString(hallOfFameList);
//        return writeFromOutputStream.dumpStringToTextFile(file, content);
//    }

    public List<String> getHallOfFameList() {
        return hallOfFameList;
    }

    protected TreeMap<Integer, List<String>> getHallOfFameMap() {
        return hallOfFameMap;
    }
}
