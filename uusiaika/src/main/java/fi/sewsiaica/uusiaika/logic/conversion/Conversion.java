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
package fi.sewsiaica.uusiaika.logic.conversion;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.Map;
import java.util.Random;

/**
 * This is an abstract class of the Conversion process, and thus no instances of
 * this type can be made. Currently, its subclasses are Persuasion, Sermon, and
 * Accusation.
 *
 * @author iah1016
 */
public abstract class Conversion {

    private Random random;
    Map<String, Integer> intValues;
    private int maxNumberOfConversions;
    private int playerRandomBound;
    private int vilRandomBound;

    /**
     *
     * @param random Random is needed for the convSucceeds method.
     * @param intValues Includes all the variable values of the game.
     * @param maxNumberOfConversions This value needs to be given separately so
     * that a subclass' setMaxNumberOfConversions functions.
     * @param playerRandomBound Given separately so that Conversion knows which
     * Random bounds to use.
     * @param vilRandomBound Given separately so that Conversion knows which
     * Random bounds to use.
     */
    public Conversion(Random random, Map<String, Integer> intValues,
            int maxNumberOfConversions, int playerRandomBound,
            int vilRandomBound) {
        this.random = random;
        this.intValues = intValues;
        this.maxNumberOfConversions = maxNumberOfConversions;
        this.playerRandomBound = playerRandomBound;
        this.vilRandomBound = vilRandomBound;
    }

    /**
     * Checking if the value is higher than the maximum.
     *
     * @param val The number of times that Villager has gone through this
     * Conversion method. It is currently undecided when this value is reset to
     * its initial value.
     * @param max The maximum amount of conversions of this type.
     * @return
     */
    public boolean isMaxedOut(int val, int max) {
        return val >= max || val < 0;
    }

    /**
     * The core element of the conversion "battle". The total Player value +
     * random factor versus the total Villager value + random factor.
     *
     * @param pVal This is calculated in the subclass.
     * @param vVal This is calculated in the subclass.
     * @param plIncr This is given as a parameter playerRandomBound.
     * @param vilIncr This is given as a parameter vilRandomBound.
     * @return Return value true equals a successful conversion.
     */
    public boolean convSucceeds(int pVal, int vVal, int plIncr, int vilIncr) {
        if (pVal < 0 || vVal < 0) {
            return false;
        }
        pVal += random.nextInt(plIncr);
        vVal += random.nextInt(vilIncr);

        return pVal >= vVal;
    }

    /**
     * Increases the amount of conversions made to this Villager, calculates
     * "battle values" for Player and Villager, checks who won. If the player is
     * successful, winning actions are made according to the conversion type and
     * then method returns true.
     *
     * @param player Head honcho.
     * @param villager Your opposition.
     * @param sect This is needed for the winningActions method.
     * @return
     */
    public boolean convert(Player player, Villager villager, Sect sect) {
        increaseAmountOfConv(villager);
        int[] battleValues = calculatePlayerAndVilValues(player, villager);
        boolean successfulConv = convSucceeds(battleValues[0], battleValues[1],
                playerRandomBound, vilRandomBound);

        if (successfulConv) {
            winningActions(player, villager, sect);
            return true;
        }
        return false;
    }

    /**
     * Returns the maxNumberOfConversions.
     *
     * @return The returned value is that of the subclass.
     */
    public int getMaxNumberOfConversions() {
        return maxNumberOfConversions;
    }

    public abstract void setMaxNumberOfConversions(int maxNumberOfConversions);

    public abstract boolean checkIfAllowedToProceed(Villager villager);

    public abstract void increaseAmountOfConv(Villager villager);

    public abstract int[] calculatePlayerAndVilValues(Player player,
            Villager villager);

    public abstract void winningActions(Player player, Villager villager,
            Sect sect);
}
