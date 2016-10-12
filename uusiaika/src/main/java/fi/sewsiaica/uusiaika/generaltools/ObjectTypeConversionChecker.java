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

/**
 * This class contains simple object type conversion checkers.
 *
 * @author iah1016
 */
public class ObjectTypeConversionChecker {

    /**
     * This method checks if a string can be converted to an integer.
     *
     * @param string String.
     * @return Returns true if the string can be converted, false otherwise.
     */
    public boolean stringCanBeConvertedToInt(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * This method checks if an integer can be converted to a boolean, ie its
     * value is either 0 or 1.
     *
     * @param i Integer.
     * @return Returns true if the integer can be converted, false otherwise.
     */
    public boolean intCanBeConvertedToBoolean(int i) {
        return i == 0 || i == 1;
    }

}
