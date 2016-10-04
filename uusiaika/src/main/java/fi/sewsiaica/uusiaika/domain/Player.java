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
    private int argSkills;

    /**
     *
     * @param name The name is given by the user via GUI.
     * @param charisma Default value from Config.intValues.
     * @param argSkills Default value from Config.intValues.
     */
    public Player(String name, int charisma, int argSkills) {
        this.name = name;
        this.charisma = charisma;
        this.argSkills = argSkills;
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

    public void setCharisma(int value) {
        if (value >= 0) {
            this.charisma = value;
        }
    }

    public int getArgSkills() {
        return argSkills;
    }

    public void setArgSkills(int value) {
        if (value >= 0) {
            this.argSkills = value;
        }
    }

}
