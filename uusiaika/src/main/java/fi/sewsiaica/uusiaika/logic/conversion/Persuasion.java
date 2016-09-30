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
 * This is a subclass of the abstract class Conversion. This is supposed to be
 * the easiest of the conversion types to succeed (though is not, as yet).
 * However, the success only changes the player and villager attributes and does
 * not fully convert the villager.
 *
 * @author iah1016
 */
public class Persuasion extends Conversion {

    private int convMaxNumberOfPersuasions;
    private int convPersPlayerCharIncr;
    private int convPersVilSelfAwDecr;
    private int convPersVilSceptDecr;

    public Persuasion(Random random, Map<String, Integer> intValues,
            int maxNumberOfConversions, int playerRandomBound,
            int vilRandomBound) {
        super(random, intValues, maxNumberOfConversions, playerRandomBound,
                vilRandomBound);
        this.convMaxNumberOfPersuasions = maxNumberOfConversions;
        this.convPersPlayerCharIncr = intValues.get("convPersPlayerCharIncr");
        this.convPersVilSelfAwDecr = intValues.get("convPersVilSelfAwDecr");
        this.convPersVilSceptDecr = intValues.get("convPersVilSceptDecr");
    }

    /**
     * The villager's current value of the number of persuasions is checked.
     *
     * @param villager The target villager.
     * @return If the current value is not max or more, return true.
     */
    @Override
    public boolean checkIfAllowedToProceed(Villager villager) {
        int persuasions = villager.getNumberOfPersuasions();
        return !super.isMaxedOut(persuasions, convMaxNumberOfPersuasions);
    }

    /**
     * Increases the villager's amount of persuasions by one.
     *
     * @param villager The target villager.
     */
    @Override
    public void increaseAmountOfConv(Villager villager) {
        int persuasions = villager.getNumberOfPersuasions();
        villager.setNumberOfPersuations(persuasions + 1);
    }

    /**
     * The player's value consists solely of Charisma, the villager's of
     * SelfAwareness.
     *
     * @param player The Enlightened One.
     * @param villager Pogo stick.
     * @return Returns an integer array of the values.
     */
    @Override
    public int[] calculatePlayerAndVilValues(Player player, Villager villager) {
        int[] values = new int[2];

        int playerValue = player.getCharisma();
        values[0] = playerValue;
        int vilValue = villager.getSelfAwareness();
        values[1] = vilValue;

        return values;
    }

    /**
     * The villager's self-awareness and scepticism decreases, and the players
     * charisma increases.
     *
     * @param player The Enlightened One.
     * @param villager Pogo stick.
     * @param sect The Guardians of the Truth
     */
    @Override
    public void winningActions(Player player, Villager villager, Sect sect) {
        villager.setSelfAwareness(villager.getSelfAwareness()
                - convPersVilSelfAwDecr);
        villager.setScepticism(villager.getScepticism()
                - convPersVilSceptDecr);
        player.setCharisma(player.getCharisma()
                + convPersPlayerCharIncr);
    }

    /**
     * Currently needed for the GameTest tests. The tests will be modified to
     * get the values from Config.intValues.
     *
     * @param maxNumberOfConversions The maximum number of Persuasions.
     */
    @Override
    public void setMaxNumberOfConversions(int maxNumberOfConversions) {
        this.convMaxNumberOfPersuasions = maxNumberOfConversions;
    }
}
