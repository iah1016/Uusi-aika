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
public class Accusation extends Conversion {

    private int convMaxNumberOfAccusations;
    private int defaultConvAccuPlayerCharIncr;
    private int defaultConvAccuVilSelfEsDecr;

    public Accusation(Random random, int defaultConvMaxNumber,
            int[] defaultBounds, int[] defaultPlayerAttribIncr,
            int[] defaultVilAttribDecr) {
        super(random, defaultConvMaxNumber, defaultBounds,
                defaultPlayerAttribIncr, defaultVilAttribDecr);
        this.convMaxNumberOfAccusations = defaultConvMaxNumber;
        this.defaultConvAccuPlayerCharIncr = defaultPlayerAttribIncr[0];
        this.defaultConvAccuVilSelfEsDecr = defaultVilAttribDecr[0];

    }

    @Override
    public boolean checkIfAllowedToProceed(Villager villager) {
        int accusations = villager.getNumberOfAccusations();
        return !super.isMaxedOut(accusations, convMaxNumberOfAccusations);
    }

    @Override
    public void increaseAmountOfConv(Villager villager) {
        int accusations = villager.getNumberOfAccusations();
        villager.setNumberOfAccusations(accusations + 1);
    }

    @Override
    public int[] calculatePlayerAndVilValues(Player player, Villager villager) {
        int[] values = new int[2];

        int playerValue = player.getCharisma() + player.getArgSkills();
        values[0] = playerValue;
        int vilValue = villager.getSelfEsteem() + villager.getArgSkills();
        values[1] = vilValue;

        return values;
    }

    @Override
    public void winningActions(Player player, Villager villager, Sect sect) {
        villager.setInSect(true);
        sect.getCongregation().add(villager);
        player.setCharisma(player.getCharisma()
                + defaultConvAccuPlayerCharIncr);
        villager.setSelfEsteem(villager.getSelfEsteem()
                - defaultConvAccuVilSelfEsDecr);
    }
}
