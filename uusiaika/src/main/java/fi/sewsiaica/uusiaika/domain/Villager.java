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
 * The village is a home to a miscellaneous group of people that are all
 * rational individuals with vibrant and joyful lives and not just a set of
 * random numbers.
 *
 * @author iah1016
 */
public class Villager {

    private String name;
    private boolean inSect;
    private int scepticism;
    private int selfEsteem;
    private int selfAwareness;
    private int argSkills;
    private String profession;
    private int numberOfPersuations;
    private int numberOfSermons;
    private int numberOfAccusations;

    /**
     * The constructor is given the name, the profession and the values for the
     * attributes as parameters.
     *
     * @param name CreateVillagers picks a name from the list created by Config.
     * @param inSect Initial value is false, ie. the villager is not a member.
     * @param scepticism CreateVillagers picks a number that is base+random.
     * @param selfEsteem CreateVillagers picks a number that is base+random.
     * @param selfAwareness CreateVillagers picks a number that is base+random.
     * @param argSkills CreateVillagers picks a number that is base+random.
     * @param profession CreateVillagers picks a name from the list created by
     * Config.
     */
    public Villager(String name, boolean inSect, int scepticism, int selfEsteem,
            int selfAwareness, int argSkills, String profession) {
        this.inSect = inSect;
        this.name = name;
        this.scepticism = scepticism;
        this.selfEsteem = selfEsteem;
        this.selfAwareness = selfAwareness;
        this.argSkills = argSkills;
        this.profession = profession;
        this.numberOfPersuations = 0;
        this.numberOfSermons = 0;
        this.numberOfAccusations = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isInSect() {
        return inSect;
    }

    public int getScepticism() {
        return scepticism;
    }

    public int getSelfEsteem() {
        return selfEsteem;
    }

    public int getSelfAwareness() {
        return selfAwareness;
    }

    public int getArgSkills() {
        return argSkills;
    }

    public String getProfession() {
        return profession;
    }

    public void setInSect(boolean inSect) {
        this.inSect = inSect;
    }

    /**
     * Sets a new value for Scepticism, unless the given value is less than
     * zero.
     *
     * @param value The given value.
     */
    public void setScepticism(int value) {
        if (value >= 0) {
            this.scepticism = value;
        }
    }

    /**
     * Sets a new value for SelfEsteem, unless the given value is less than
     * zero.
     *
     * @param value The given value.
     */
    public void setSelfEsteem(int value) {
        if (value >= 0) {
            this.selfEsteem = value;
        }
    }

    /**
     * Sets a new value for SelfAwareness, unless the given value is less than
     * zero.
     *
     * @param value The given value.
     */
    public void setSelfAwareness(int value) {
        if (value >= 0) {
            this.selfAwareness = value;
        }
    }

    /**
     * Sets a new value for ArgSkills, unless the given value is less than
     * zero.
     *
     * @param value The given value.
     */
    public void setArgSkills(int value) {
        if (value >= 0) {
            this.argSkills = value;
        }
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getNumberOfPersuasions() {
        return numberOfPersuations;
    }

    public void setNumberOfPersuations(int numberOfPersuations) {
        this.numberOfPersuations = numberOfPersuations;
    }

    public int getNumberOfSermons() {
        return numberOfSermons;
    }

    public void setNumberOfSermons(int numberOfSermons) {
        this.numberOfSermons = numberOfSermons;
    }

    public int getNumberOfAccusations() {
        return numberOfAccusations;
    }

    public void setNumberOfAccusations(int numberOfAccusations) {
        this.numberOfAccusations = numberOfAccusations;
    }

    @Override
    public String toString() {
        return name + ", " + profession;
    }

}
