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
import java.util.Map;
import java.util.TreeMap;

/**
 * The core class of the general tools.
 *
 * @author iah1016
 */
public class GeneralTools {

    private final ObjectTypeConversionChecker objectTypeConversionChecker;
    private final StrIntMapAndStringListInterconversion strIntMapAndStrListConv;
    private final StrStrMapAndStringListInterconversion strStrMapAndStrListConv;
    private final StringAndStringListInterconversion stringAndStrListInterconv;
    private final StringToArraysConversion stringToArraysConversion;
    private final StringListToIntStrListTreeMapConversion listToTreeMap;

    /**
     * The constructor creates new general tools objects.
     */
    public GeneralTools() {
        objectTypeConversionChecker = new ObjectTypeConversionChecker();
        strIntMapAndStrListConv
                = new StrIntMapAndStringListInterconversion();
        strStrMapAndStrListConv
                = new StrStrMapAndStringListInterconversion();
        stringAndStrListInterconv
                = new StringAndStringListInterconversion();
        stringToArraysConversion = new StringToArraysConversion();
        listToTreeMap = new StringListToIntStrListTreeMapConversion();
    }

    /**
     * This method converts any String,(wildcard)-type map to a String-type List
     * where the elements are in the given order.
     *
     * @param map Map's keys are of String type and values can be any type.
     * @param keys The String array of the map keys.
     * @return A List of Strings is returned.
     */
    public List<String> convertMapToOrderedStringList(Map<String, ?> map,
            String[] keys) {
        return strStrMapAndStrListConv.convertMapToOrderedStringList(map, keys);
    }

    /**
     * This method converts any String,(wildcard)-type map to a String-type
     * List.
     *
     * @param map Map's keys are of String type and values can be any type.
     * @return A List of Strings is returned.
     */
    public List<String> convertMapToStringList(Map<String, ?> map) {
        return strStrMapAndStrListConv.convertMapToStringList(map);
    }

    /**
     * This method checks if a string can be converted to an integer.
     *
     * @param string String.
     * @return Returns true if the string can be converted, false otherwise.
     */
    public boolean stringCanBeConvertedToInt(String string) {
        return objectTypeConversionChecker.stringCanBeConvertedToInt(string);
    }

    /**
     * This method checks if an integer can be converted to a boolean, ie its
     * value is either 0 or 1.
     *
     * @param i Integer.
     * @return Returns true if the integer can be converted, false otherwise.
     */
    public boolean intCanBeConvertedToBoolean(int i) {
        return objectTypeConversionChecker.intCanBeConvertedToBoolean(i);
    }

    /**
     * This method converts a StringList to a String,Integer-type hash map.
     *
     * @param list A List of strings.
     * @return Returns a String,Integer-type Map.
     */
    public Map<String, Integer> convertStringListToStrIntMap(
            List<String> list) {
        return strIntMapAndStrListConv.convertStringListToStrIntMap(list);
    }

    /**
     * This method converts a StringList to a String,String-type hash map.
     *
     * @param list A List of strings.
     * @return Returns a String,String-type Map.
     */
    public Map<String, String> convertStringListToStrStrMap(
            List<String> list) {
        return strStrMapAndStrListConv.convertStringListToStrStrMap(list);
    }

    /**
     * This method converts a String to a StringList.
     *
     * @param string A String.
     * @return Returns a List of Strings.
     */
    public List<String> convertStringToStringList(String string) {
        return stringAndStrListInterconv.convertStringToStringList(string);
    }

    /**
     * This method converts a StringList to a String.
     *
     * @param list A List of Strings.
     * @return Returns a String.
     */
    public String convertStringListToString(List<String> list) {
        return stringAndStrListInterconv.convertStringListToString(list);
    }

    /**
     * This method converts a string, where integers are separated by commas, to
     * an integer array.
     *
     * @param string String.
     * @param size The size of the array.
     * @return Returns an array of integers or null if invalid format.
     */
    public int[] stringToIntArray(String string, int size) {
        return stringToArraysConversion.stringToIntArray(string, size);
    }

    /**
     * This method converts a string, where there are ones and twos separated by
     * commas, to an Boolean Array.
     *
     * @param string String.
     * @param size The size of the Array.
     * @return Returns an Array of Booleans or null if invalid format.
     */
    public boolean[] stringToBooleanArray(String string, int size) {
        return stringToArraysConversion.stringToBooleanArray(string, size);
    }

    /**
     * This method converts a list of strings to an Integer,StringList-type
     * TreeMap.
     *
     * @param list A list of strings.
     * @return Returns an Integer,StringList-type TreeMap.
     */
    public TreeMap<Integer, List<String>> convertStringListToIntStrListTreeMap(
            List<String> list) {
        return listToTreeMap.convertStringListToIntStrListTreeMap(list);
    }
}
