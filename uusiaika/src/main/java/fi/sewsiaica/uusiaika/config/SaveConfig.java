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
package fi.sewsiaica.uusiaika.config;

import fi.sewsiaica.uusiaika.generaltools.MapAndStringListInterconversion;
import fi.sewsiaica.uusiaika.generaltools.StrStrMapAndStringListInterconversion;

/**
 * The save feature is yet to be implemented.
 *
 * @author iah1016
 */
public class SaveConfig {

    private final MapAndStringListInterconversion mapToString;

    /**
     * The save feature is yet to be implemented.
     */
    public SaveConfig() {
        this.mapToString = new StrStrMapAndStringListInterconversion();
    }

}
