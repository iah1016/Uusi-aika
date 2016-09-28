/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic.conversion;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.Random;

/**
 *
 * @author iah1016
 */
public abstract class Conversion {

    private Random random;
    private int maxNumberOfConversions;
    private int[] playerAndVilRandomBounds;

    public Conversion(Random random, int defaultConvMaxNumber,
            int[] defaultBounds, int[] defaultPlayerAttribIncr,
            int[] defaultVilAttribDecr) {
        this.random = random;
        this.maxNumberOfConversions = defaultConvMaxNumber;
        this.playerAndVilRandomBounds = defaultBounds;
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
                playerAndVilRandomBounds[0], playerAndVilRandomBounds[1]);

        if (successfulConv) {
            winningActions(player, villager, sect);
            return true;
        }
        return false;
    }

    public int getMaxNumberOfConversions() {
        return maxNumberOfConversions;
    }

    public void setMaxNumberOfConversions(int maxNumberOfConversions) {
        this.maxNumberOfConversions = maxNumberOfConversions;
    }

    public abstract boolean checkIfAllowedToProceed(Villager villager);

    public abstract void increaseAmountOfConv(Villager villager);

    public abstract int[] calculatePlayerAndVilValues(Player player,
            Villager villager);

    public abstract void winningActions(Player player, Villager villager,
            Sect sect);
}
