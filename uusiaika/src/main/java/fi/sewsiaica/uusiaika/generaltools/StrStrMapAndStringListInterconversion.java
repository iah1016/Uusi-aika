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
 * String,Integer-type Map.
 *
 * @author iah1016
 */
public class StrStrMapAndStringListInterconversion extends
        MapAndStringListInterconversion {

    /**
     * The implementation of convertStringListToMap.
     *
     * @param list A List of strings.
     * @return Returns a String,?-type Map, where ? is a wildcard.
     */
    @Override
    public Map<String, ?> convertStringListToMap(List<String> list) {

        Map<String, String> map = new HashMap<>();

        for (int strIdx = 0; strIdx < list.size(); strIdx++) {
            String string = list.get(strIdx);
            String[] keyAndValue = processOneStringFromStringList(string);
            if (keyAndValue != null) {
                map.put(keyAndValue[0], keyAndValue[1]);
            }
        }

        return map;
    }

    /**
     * This method is the one that is called from the outside. It casts the
     * String,?-type Map to a String,String-type Map.
     *
     * @param list A List of strings.
     * @return Returns a String,String-type Map.
     */
    public Map<String, String> convertStringListToStrStrMap(
            List<String> list) {
        Map<String, String> newMap
                = (Map<String, String>) convertStringListToMap(list);
        return newMap;
    }
}
