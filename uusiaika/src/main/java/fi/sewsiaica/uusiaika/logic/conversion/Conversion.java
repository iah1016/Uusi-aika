/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic.conversion;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author iah1016
 */
public abstract class Conversion {

    private Random random;
    Map<String, Integer> intValues;
    private int maxNumberOfConversions;
    private int playerRandomBound;
    private int vilRandomBound;

    public Conversion(Random random, Map<String, Integer> intValues,
            int maxNumberOfConversions, int playerRandomBound,
            int vilRandomBound) {
        this.random = random;
        this.intValues = intValues;
        this.maxNumberOfConversions = maxNumberOfConversions;
        this.playerRandomBound = playerRandomBound;
        this.vilRandomBound = vilRandomBound;
    }

    public boolean isMaxedOut(int val, int max) {
        return val >= max || val < 0;
    }

    public boolean convSucceeds(int pVal, int vVal, int plIncr, int vilIncr) {
        if (pVal < 0 || vVal < 0) {
            return false;
        }
        pVal += random.nextInt(plIncr);
        vVal += random.nextInt(vilIncr);

        return pVal >= vVal;
    }

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
