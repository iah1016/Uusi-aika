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
public class StringListToStringIntMapConversion extends
        StringListToStringMapConversion {

    @Override
    public Map<String, ?> readStringListAndReturnKeysAndValues(
            List<String> list) {
        Map<String, Integer> map = new HashMap<>();

        for (int strIdx = 0; strIdx < list.size(); strIdx++) {
            String string = list.get(strIdx);
            String[] keyAndValue = processOneStringFromStringList(string);
            if (keyAndValue != null) {
                map.put(keyAndValue[0],
                        returnValueAsIntOrZeroIfInvalidIntGiven(
                                keyAndValue[1]));
            }
        }

        return map;
    }

    public int returnValueAsIntOrZeroIfInvalidIntGiven(String valueStr) {
        int value;
        try {
            value = Integer.parseInt(valueStr);
        } catch (Exception e) {
            return 0;
        }

        return value;
    }
}
