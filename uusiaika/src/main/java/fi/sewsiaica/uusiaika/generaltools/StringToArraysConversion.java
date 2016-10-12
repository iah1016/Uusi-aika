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

import java.util.List;

/**
 * This class converts a string to an Array of one of the following types:
 * Integer, Boolean, String.
 *
 * @author iah1016
 */
public class StringToArraysConversion {

    private final ObjectTypeConversionChecker typeChecker;
    private final StringAndStringListInterconversion stringToStringList;

    /**
     * The constructor creates StringAndStringListInterconversion and
     * ObjectTypeConversionChecker objects.
     */
    public StringToArraysConversion() {
        this.typeChecker = new ObjectTypeConversionChecker();
        this.stringToStringList = new StringAndStringListInterconversion();
    }

    /**
     * This method converts a string to an Integer Array.
     *
     * @param string String.
     * @param size The size of the Array.
     * @return Returns an Array of Integers or null if invalid format.
     */
    public int[] stringToIntArray(String string, int size) {
        List<String> stringList
                = stringToStringList.convertStringToStringList(string);
        int[] intArray = new int[size];

        for (int i = 0; i < size; i++) {
            String arrayString = stringList.get(i);

            if (!typeChecker.stringCanBeConvertedToInt(arrayString)) {
                return null;
            }
            intArray[i] = Integer.parseInt(arrayString);
        }

        return intArray;
    }

    /**
     * This method converts a string to an Boolean Array.
     *
     * @param string String.
     * @param size The size of the Array.
     * @return Returns an Array of Booleans or null if invalid format.
     */
    public boolean[] stringToBooleanArray(String string, int size) {
        List<String> stringList
                = stringToStringList.convertStringToStringList(string);
        boolean[] booleanArray = new boolean[size];

        for (int i = 0; i < size; i++) {
            String currentString = stringList.get(i);
            if (!typeChecker.stringCanBeConvertedToInt(currentString)) {
                return null;
            }

            int integer = Integer.parseInt(currentString);

            if (!typeChecker.intCanBeConvertedToBoolean(integer)) {
                return null;
            }
            booleanArray[i] = convertIntToBoolean(integer);
        }

        return booleanArray;
    }

    private boolean convertIntToBoolean(int integer) {
        if (integer == 0) {
            return false;
        }
        return true;
    }
}
