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
    }

    public ObjectTypeConversionChecker getObjectTypeConversionChecker() {
        return objectTypeConversionChecker;
    }

    public StrIntMapAndStringListInterconversion
            getStrIntMapAndStringListInterconversion() {
        return strIntMapAndStrListConv;
    }

    public StrStrMapAndStringListInterconversion
            getStrStrMapAndStringListInterconversion() {
        return strStrMapAndStrListConv;
    }

    public StringAndStringListInterconversion
            getStringAndStringListInterconversion() {
        return stringAndStrListInterconv;
    }

    public StringToArraysConversion getStringToArraysConversion() {
        return stringToArraysConversion;
    }

}
