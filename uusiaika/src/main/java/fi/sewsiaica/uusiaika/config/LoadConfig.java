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
import fi.sewsiaica.uusiaika.io.ReadFromInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * This class loads the variable values, villager names, and professions from
 * files.
 *
 * @author iah1016
 */
public class LoadConfig {

    private final String[] variableNames;
    private final GeneralTools generalTools;
    private final ReadFromInputStream readFromInputStream;

    /**
     * The String-array of the variable names is given to the constructor as a
     * parameter. The array is needed for the validity check.
     *
     * @param variableNames The correct keys for the intValues-map.
     */
    public LoadConfig(String[] variableNames) {
        this.variableNames = variableNames;
        this.generalTools = new GeneralTools();
        this.readFromInputStream = new ReadFromInputStream();
    }

    /**
     * This method loads the variable values from a file.
     *
     * @param file The Map of variable values is loaded from this file.
     * @return Returns the String,Integer-type map.
     * @throws Exception Throws the generic exception.
     */
    public Map<String, Integer> loadIntValuesFromAFile(File file) throws
            Exception {
        List<String> lines = getLinesFromInputStream(file);
        Map<String, Integer> intValues
                = generalTools.convertStringListToStrIntMap(lines);
        if (areValidIntValues(intValues)) {
            return intValues;
        } else {
            return null;
        }
    }

    private List<String> getLinesFromInputStream(File file)
            throws Exception {
        List<String> lines;
        InputStream inputStream = new FileInputStream(file);
        lines = readFromInputStream.yankTextFromFile(inputStream);

        return lines;
    }

    /**
     * This method loads a list from a file.
     *
     * @param file The list is loaded from this file.
     * @return Returns the String-type list.
     * @throws Exception Throws the generic exception.
     */
    public List<String> loadListFromAFile(File file)
            throws Exception {
        return getLinesFromInputStream(file);
    }

    /**
     * This method checks that the Map is a valid Config variable value map.
     *
     * @param intValuesMap The Map that is being checked for validity.
     * @return Returns true if valid and false if invalid.
     */
    protected boolean areValidIntValues(Map<String, Integer> intValuesMap) {
        if (intValuesMap == null) {
            return false;
        }

        for (String variable : variableNames) {
            if (!intValuesMap.containsKey(variable)) {
                return false;
            }
        }
        return true;
    }

}
