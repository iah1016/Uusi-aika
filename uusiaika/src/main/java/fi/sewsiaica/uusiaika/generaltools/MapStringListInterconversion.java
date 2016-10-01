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
import java.util.Map;
import java.util.Set;

/**
 * This abstract class contains the implementation for converting a
 * String,Object-type Map to a String-type List and most of the methods for the
 * reverse conversion. The conversions to a String,String-type and a
 * String,Integer-type Map are implemented in the subclasses.
 *
 * @author iah1016
 */
public abstract class MapStringListInterconversion {
    
    /**
     * An abstract method for converting a String-type List to String,?-type
     * Map, where ? is a wildcard.
     *
     * @param list A List of Strings.
     * @return Returns a Map of an appropriate type, selected by the subclass.
     */
    public abstract Map<String, ?> convertStringListToMap(List<String> list);

    /**
     * This method converts any String,?-type map to a String-type List, where ?
     * is a wildcard.
     *
     * @param map Map's keys are of String type and values can be any type.
     * @return A List of Strings is returned.
     */
    public List<String> convertMapToStringList(Map<String, ?> map) {
        List<String> stringList = new ArrayList<>();
        Set<String> keySet = map.keySet();

        for (String key : keySet) {
            stringList.add(key + ": " + map.get(key).toString());
        }

        return stringList;
    }

    /**
     * This method is used in convertStringListToMap. It processes one string
     * from the StringList.
     *
     * @param string A string is given from the StringList.
     * @return Returns a key and value pair for the Map.
     */
    public String[] processOneStringFromStringList(String string) {
        boolean colonFound = false;
        boolean postColonSpaceIgnored = false;
        StringBuilder keySB = new StringBuilder();
        StringBuilder valueSB = new StringBuilder();

        for (int charIdx = 0; charIdx < string.length(); charIdx++) {
            char currentChar = string.charAt(charIdx);

            if (!colonFound) {
                if (!currentCharIsColon(currentChar)) {
                    keySB.append(currentChar);
                } else {
                    colonFound = true;
                }
            } else if (!postColonSpaceIgnored) {
                postColonSpaceIgnored = true;
            } else {
                valueSB.append(currentChar);
            }
            if (keySB.toString().isEmpty()) {
                return null;
            }
        }
        String[] keyAndValue = {keySB.toString(),
            returnValueOrNullIfValueIsEmpty(valueSB.toString())};
        return keyAndValue;
    }

    /**
     * This method checks if the current char is a colon, which indicates that
     * the key has been fully read.
     *
     * @param currentChar The character that is being checked from the string.
     * @return True, if the character is a colon and false otherwise.
     */
    public boolean currentCharIsColon(char currentChar) {
        return currentChar == ':';
    }

    /**
     * If there are no characters in the string after the colon (whitespace
     * excluded), the value is null.
     *
     * @param value The value part of the string.
     * @return Returns null if value is empty and value otherwise.
     */
    public String returnValueOrNullIfValueIsEmpty(String value) {
        if (value.isEmpty()) {
            return null;
        }
        return value;
    }
}
