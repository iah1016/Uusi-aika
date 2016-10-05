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
package fi.sewsiaica.uusiaika.config.defaultvalues;

/**
 * This enum class contains the default list of names for villagers. This list
 * will soon be much greater.
 *
 * @author iah1016
 */
public enum DefaultVilNames {
    VILLAGER01("Jaakko P"),
    VILLAGER02("Harri H"),
    VILLAGER03("Mikko M"),
    VILLAGER04("Teemu P"),
    VILLAGER05("Ilona R"),
    VILLAGER06("Taina E"),
    VILLAGER07("Marika M"),
    VILLAGER08("Robert F"),
    VILLAGER09("Cecilia C"),
    VILLAGER10("Oleg M");
    private final String name;

    private DefaultVilNames(String name) {
        this.name = name;
    }

    /**
     * The getter for the default villager names.
     * @return Returns a villager name.
     */
    public String vilName() {
        return name;
    }
}
