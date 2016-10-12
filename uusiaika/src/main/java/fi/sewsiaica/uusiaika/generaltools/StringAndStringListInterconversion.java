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

/**
 * This class converts Strings to StringList and vice versa.
 *
 * @author iah1016
 */
public class StringAndStringListInterconversion {

    /**
     * This method converts a String to a StringList.
     *
     * @param string A String.
     * @return Returns a List of Strings.
     */
    public List<String> convertStringToStringList(String string) {
        if (string == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (character == ',') {
                list.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(string.charAt(i));
            }
        }
        list.add(stringBuilder.toString());
        return list;
    }

}
