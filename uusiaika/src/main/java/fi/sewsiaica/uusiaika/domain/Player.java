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
package fi.sewsiaica.uusiaika.domain;

/**
 * You, the Holy Controller of this Universe. You will have the name, the
 * charisma and the argumentation skills to conquer the world. Well, you have a
 * name, at least.
 *
 * @author iah1016
 */
public class Player {

    private String name;
    private int charisma;
    private int argumentationSkills;

    /**
     * The constructor is given the name, and the charisma and argumentation
     * skills values as parameters.
     *
     * @param name The name is given by the user via GUI.
     * @param charisma Default value from Config.intValues.
     * @param argumentationSkills Default value from Config.intValues.
     */
    public Player(String name, int charisma, int argumentationSkills) {
        this.name = name;
        this.charisma = charisma;
        this.argumentationSkills = argumentationSkills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCharisma() {
        return charisma;
    }

    /**
     * Sets a new value for Charisma, unless the given value is less than zero.
     *
     * @param value The given value.
     */
    public void setCharisma(int value) {
        if (value >= 0) {
            this.charisma = value;
        }
    }

    public int getArgumentationSkills() {
        return argumentationSkills;
    }

    /**
     * Sets a new value for ArgumentationSkills, unless the given value is less
     * than zero.
     *
     * @param value The given value.
     */
    public void setArgumentationSkills(int value) {
        if (value >= 0) {
            this.argumentationSkills = value;
        }
    }

}
