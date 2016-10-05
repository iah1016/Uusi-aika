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
package fi.sewsiaica.uusiaika.logic.activegame.conversion;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.Map;
import java.util.Random;

/**
 * This is a subclass of the abstract class Conversion. A successful conversion
 * adds the villager to the sect.
 *
 * @author iah1016
 */
public class Accusation extends Conversion {

    private int convMaxNumberOfAccusations;
    private int convAccuPlayerCharIncr;
    private int convAccuVilSelfEsDecr;

    /**
     * The constructor is given random, and Config values as parameters, which
     * it sets as its object variables. It uses them for calling the superclass
     * constructor.
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
    public Accusation(Random random, Map<String, Integer> intValues,
            int maxNumberOfConversions, int playerRandomBound,
            int vilRandomBound) {
        super(random, intValues, maxNumberOfConversions, playerRandomBound,
                vilRandomBound);
        this.convMaxNumberOfAccusations = maxNumberOfConversions;
        this.convAccuPlayerCharIncr = intValues.get("convAccuPlayerCharIncr");
        this.convAccuVilSelfEsDecr = intValues.get("convAccuVilSelfEsDecr");

    }

    /**
     * The villager's current value of the number of accusations is checked.
     *
     * @param villager The target villager.
     * @return If the current value is not max or more, return true.
     */
    @Override
    public boolean checkIfAllowedToProceed(Villager villager) {
        int accusations = villager.getNumberOfAccusations();
        return !super.isMaxedOut(accusations, convMaxNumberOfAccusations);
    }

    /**
     * Increases the villager's amount of accusations by one.
     *
     * @param villager The target villager.
     */
    @Override
    public void increaseAmountOfConv(Villager villager) {
        int accusations = villager.getNumberOfAccusations();
        villager.setNumberOfAccusations(accusations + 1);
    }

    /**
     * The player's value consists of Charisma and ArgSkills, and the villager's
     * of SelfEsteem and ArgSkills.
     *
     * @param player The Enlightened One.
     * @param villager Pogo stick.
     * @return Returns an integer array of the values.
     */
    @Override
    public int[] calculatePlayerAndVilValues(Player player, Villager villager) {
        int[] values = new int[2];

        int playerValue = player.getCharisma() + player.getArgSkills();
        values[0] = playerValue;
        int vilValue = villager.getSelfEsteem() + villager.getArgSkills();
        values[1] = vilValue;

        return values;
    }

    /**
     * The villager's self-esteem decreases, and the players charisma increases.
     *
     * @param player The Enlightened One.
     * @param villager Pogo stick.
     * @param sect The Guardians of the Truth
     */
    @Override
    public void winningActions(Player player, Villager villager, Sect sect) {
        villager.setInSect(true);
        sect.getCongregation().add(villager);
        player.setCharisma(player.getCharisma()
                + convAccuPlayerCharIncr);
        villager.setSelfEsteem(villager.getSelfEsteem()
                - convAccuVilSelfEsDecr);
    }

    @Override
    public void setMaxNumberOfConversions(int maxNumberOfConversions) {
        this.convMaxNumberOfAccusations = maxNumberOfConversions;
    }
}
