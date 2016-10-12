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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a subclass of the abstract class
 * MapAndStringListInterconversion that converts a StringList to a a
 * String,String-type Map.
 *
 * @author iah1016
 */
public class StrIntMapAndStringListInterconversion extends
        MapAndStringListInterconversion {

    private final ObjectTypeConversionChecker typeChecker;

    /**
     * The constructor creates a new ObjectTypeConversionChecker object.
     */
    public StrIntMapAndStringListInterconversion() {
        this.typeChecker = new ObjectTypeConversionChecker();
    }

    /**
     * The implementation of convertStringListToMap.
     *
     * @param list A List of strings.
     * @return Returns a String,?-type Map, where ? is a wildcard.
     */
    @Override
    protected Map<String, ?> convertStringListToMap(
            List<String> list) {
        Map<String, Integer> map = new HashMap<>();

        for (int strIdx = 0; strIdx < list.size(); strIdx++) {
            String string = list.get(strIdx);
            String[] keyAndValue = processOneStringFromStringList(string);
            if (keyAndValue != null) {
                map.put(keyAndValue[0], returnValueAsIntOrZeroIfInvalidIntGiven(
                        keyAndValue[1]));
            }
        }
        return map;
    }

    /**
     * This method is the one that is called from the outside. It casts the
     * String,?-type Map to a String,Integer-type Map.
     *
     * @param list A List of strings.
     * @return Returns a String,Integer-type Map.
     */
    public Map<String, Integer> convertStringListToStrIntMap(
            List<String> list) {
        Map<String, Integer> newMap
                = (Map<String, Integer>) convertStringListToMap(list);
        return newMap;
    }

    /**
     * This method attempts to convert a string value to an integer. If the
     * original value is not an integer, zero is returned.
     *
     * @param valueStr The value part of the string from the StringList.
     * @return Returns the value as an integer or zero if the conversion is
     * unsuccessful.
     */
    protected int returnValueAsIntOrZeroIfInvalidIntGiven(String valueStr) {
        if (typeChecker.stringCanBeConvertedToInt(valueStr)) {
            return Integer.parseInt(valueStr);
        }
        return 0;
    }
}
