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
package fi.sewsiaica.uusiaika.config.generaltools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author iah1016
 */
public class StringListToStringMapConversion {

    public Map<String, ?> readStringListAndReturnKeysAndValues(
            List<String> list) {

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

    public String[] processOneStringFromStringList(String string) {
        boolean colonFound = false;
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
            } else {
                if (keySB.toString().isEmpty()) {
                    return null;
                }
                valueSB.append(currentChar);
            }
        }
        String[] keyAndValue = {keySB.toString(),
            returnValueOrNullIfValueIsEmpty(valueSB.toString())};
        return keyAndValue;
    }

    public boolean currentCharIsColon(char currentChar) {
        return currentChar == ':';
    }

    public String returnValueOrNullIfValueIsEmpty(String value) {
        if (value.isEmpty()) {
            return null;
        }
        return value;
    }
}
