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

import fi.sewsiaica.uusiaika.generaltools.*;
import fi.sewsiaica.uusiaika.io.ReadFromTextFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author iah1016
 */
public class LoadConfig {

    private final String[] variableNames;
    private final StrIntMapStringListInterconversion listStrIntMapInterConv;
    private final ReadFromTextFile readFromFile;

    public LoadConfig(String[] variableNames) {
        this.variableNames = variableNames;
        this.listStrIntMapInterConv = new StrIntMapStringListInterconversion();
        readFromFile = new ReadFromTextFile();
    }
    
    /**
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public Map<String, Integer> loadIntValuesFromAFile(String fileName) throws
            FileNotFoundException {
        List<String> lines = readFromFile.yankTextFromFile(new File(fileName));
        Map<String, Integer> intValues
                = listStrIntMapInterConv.convertStringListToStrIntMap(lines);
        if (areValidIntValues(intValues)) {
            return intValues;
        } else {
            return null;
        }
    }
    
    /**
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public List<String> loadListFromAFile(String fileName)
            throws FileNotFoundException {
        return readFromFile.yankTextFromFile(new File(fileName));
    }

    private boolean areValidIntValues(Map<String, Integer> intValues) {
        for (String variable : variableNames) {
            if (!intValues.containsKey(variable)) {
                return false;
            }
        }
        return true;
    }

}
