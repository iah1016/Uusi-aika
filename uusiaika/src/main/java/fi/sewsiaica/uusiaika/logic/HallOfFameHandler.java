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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * The instance of this class maintains the Hall of Fame.
 *
 * @author iah1016
 */
public class HallOfFameHandler {

    private final ReadFromInputStream readFromInputStream;
    private final WriteFromOutputStream writeFromOutputStream;
    private final GeneralTools generalTools;

    private TreeMap<Integer, List<String>> hallOfFameMap;
    private List<String> hallOfFameList;
    private final File hallOfFameFile;

    private final int maxNumberOfHallOfFamers;
    private int currentNumberOfHallOfFamers;

    /**
     * The constructor creates new ReadFromInputStream, WriteFromOutputStream
     * and GeneralTools objects. It call the private method
     * loadOrCreateHallOfFame which either creates new hallOfFameMap and
     * hallOfFameList objects or loads them from the given file.
     */
    public HallOfFameHandler(File hofFile) {
        readFromInputStream = new ReadFromInputStream();
        writeFromOutputStream = new WriteFromOutputStream();
        generalTools = new GeneralTools();
        hallOfFameFile = hofFile;
        currentNumberOfHallOfFamers = 0;
        maxNumberOfHallOfFamers = 15;

        loadOrCreateHallOfFame();
    }

    private void loadOrCreateHallOfFame() {
        try {
            InputStream inputStream = new FileInputStream(hallOfFameFile);
            loadHallOfFameMapFromFile(inputStream);
        } catch (FileNotFoundException e) {
            hallOfFameMap = new TreeMap<>();
            hallOfFameList = new ArrayList<>();
        }
    }

    /**
     * This method loads the Hall of Fame from the file and sets it to an
     * Integer,StringList-type TreeMap. It calls the updateHallOfFameList()
     * method. If either IOException or NullPointerException occurs, new
     * hallOfFameMap and hallOfFameList objects are created.
     *
     * @param inputStream The file where the Hall of Fame is located.
     */
    protected final void loadHallOfFameMapFromFile(InputStream inputStream) {
        List<String> lines;
        try {
            lines = readFromInputStream.yankTextFromFile(inputStream);
            hallOfFameMap = generalTools.convertStringListToIntStrListTreeMap(
                    lines);
            updateHallOfFameList();
        } catch (IOException | NullPointerException e) {
            hallOfFameMap = new TreeMap<>();
            hallOfFameList = new ArrayList<>();
        }
    }

    /**
     * Updates the Hall of Fame with the given score; the data will be added to
     * hallOfFameMap regardless of the score but is visible in the Hall of Fame
     * list only if it is in the Top 15.
     *
     * @param score
     * @param activeGame
     */
    public void updateHallOfFame(int score, ActiveGame activeGame) {
        updateHallOfFameMap(score, activeGame);
        updateHallOfFameList();
        saveHallOfFameToFile(hallOfFameFile);
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

    /**
     * Updates the updateHallOfFameList object.
     *
     * @return Returns false if hallOfFameMap's lastKey call throws a
     * NoSuchElementException.
     */
    protected boolean updateHallOfFameList() {
        boolean keysAreNotNull = true;
        List<String> tempList = new ArrayList<>();
        Integer key;
        try {
            key = hallOfFameMap.lastKey();
            goThroughKeysFromMap(key, tempList);
        } catch (NoSuchElementException e) {
            keysAreNotNull = false;
        }
        updateCurrentNumberOfHallOfFamersInt(tempList.size());
        hallOfFameList = tempList.subList(0, currentNumberOfHallOfFamers);
        return keysAreNotNull;
    }

    private void updateCurrentNumberOfHallOfFamersInt(int listSize) {
        currentNumberOfHallOfFamers
                = Math.min(maxNumberOfHallOfFamers, listSize);
    }

    private void goThroughKeysFromMap(Integer key, List<String> tempList) {
        while (key != null) {
            List<String> valueList = hallOfFameMap.get(key);
            for (int i = 0; i < valueList.size(); i++) {
                String hofLine = key + ": " + valueList.get(i);
                tempList.add(hofLine);
            }
            key = hallOfFameMap.lowerKey(key);
        }
    }

    /**
     * Saves the Hall of Fame data (Top 15) to the given file.
     * 
     * @param file The Hall of Fame file.
     * @return Returns true if the writing has been successful.
     */
    protected boolean saveHallOfFameToFile(File file) {
        String content = generalTools.convertStringListToString(hallOfFameList);
        return writeFromOutputStream.dumpStringToTextFile(file, content);
    }

    public List<String> getHallOfFameList() {
        return hallOfFameList;
    }

    protected TreeMap<Integer, List<String>> getHallOfFameMap() {
        return hallOfFameMap;
    }
}
