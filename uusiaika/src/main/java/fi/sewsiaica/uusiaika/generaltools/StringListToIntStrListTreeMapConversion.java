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
package fi.sewsiaica.uusiaika.generaltools;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * This class uses the abstract class MapAndStringListInterconversion's methods
 * to produce a Integer,StringList-type TreeMap. ObjectTypeConversionChecker is
 * needed for checking that the keys can be converted to integers.
 *
 * @author iah1016
 */
public class StringListToIntStrListTreeMapConversion {

    private final ObjectTypeConversionChecker typeChecker;
    private final MapAndStringListInterconversion mapAndListInterconversion;

    /**
     * The constructor creates new ObjectTypeConversionChecker and
     * StrStrMapAndStringListInterconversion type objects.
     */
    public StringListToIntStrListTreeMapConversion() {
        this.typeChecker = new ObjectTypeConversionChecker();
        this.mapAndListInterconversion
                = new StrStrMapAndStringListInterconversion();
    }

    /**
     * This method converts a list of strings to an Integer,StringList-type
     * TreeMap.
     *
     * @param list A list of string.
     * @return Returns an Integer,StringList-type TreeMap.
     */
    public TreeMap<Integer, List<String>> convertStringListToIntStrListTreeMap(
            List<String> list) {
        TreeMap<Integer, List<String>> newMap = new TreeMap<>();

        for (int strIdx = 0; strIdx < list.size(); strIdx++) {
            String string = list.get(strIdx);

            putKeyValuePairToMap(string, newMap);
        }
        return newMap;
    }

    private void putKeyValuePairToMap(String string,
            TreeMap<Integer, List<String>> map) {
        String[] keyAndValue = mapAndListInterconversion
                .processOneStringFromStringList(string);
        if (keyAndValue != null) {
            int key;
            if (typeChecker.stringCanBeConvertedToInt(keyAndValue[0])) {
                key = Integer.parseInt(keyAndValue[0]);
                addValueToValueList(key, keyAndValue[1], map);
            }
        }
    }

    private void addValueToValueList(int key, String value,
            TreeMap<Integer, List<String>> map) {
        List<String> valueList;
        if (!map.containsKey(key)) {
            valueList = new ArrayList<>();
            valueList.add(value);
            map.put(key, valueList);
        } else {
            valueList = map.get(key);
            valueList.add(value);
        }
    }
}
