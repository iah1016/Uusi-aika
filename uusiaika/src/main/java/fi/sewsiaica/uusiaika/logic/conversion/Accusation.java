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
 * This is a subclass of the abstract class Conversion. A successful conversion
 * adds the villager to the sect.
 *
 * @author iah1016
 */
public class Accusation extends Conversion {

    private int convMaxNumberOfAccusations;
    private int convAccuPlayerCharIncr;
    private int convAccuVilSelfEsDecr;

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

    /**
     * Currently needed for the GameTest tests. The tests will be modified to
     * get the values from Config.intValues.
     *
     * @param maxNumberOfConversions The maximum number of Accusations.
     */
    @Override
    public void setMaxNumberOfConversions(int maxNumberOfConversions) {
        this.convMaxNumberOfAccusations = maxNumberOfConversions;
    }
}
