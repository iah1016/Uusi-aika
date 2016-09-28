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
public class Persuasion extends Conversion {

    private int convMaxNumberOfPersuasions;
    private int defaultConvPersPlayerCharIncr;
    private int defaultConvPersVilSelfAwDecr;
    private int defaultConvPersVilSceptDecr;

    public Persuasion(Random random, int defaultConvMaxNumber,
            int[] defaultBounds, int[] defaultPlayerAttribIncr,
            int[] defaultVilAttribDecr) {
        super(random, defaultConvMaxNumber, defaultBounds,
                defaultPlayerAttribIncr, defaultVilAttribDecr);
        this.convMaxNumberOfPersuasions = defaultConvMaxNumber;
        this.defaultConvPersPlayerCharIncr = defaultPlayerAttribIncr[0];
        this.defaultConvPersVilSelfAwDecr = defaultVilAttribDecr[0];
        this.defaultConvPersVilSceptDecr = defaultVilAttribDecr[1];
    }

    @Override
    public boolean checkIfAllowedToProceed(Villager villager) {
        int persuasions = villager.getNumberOfPersuasions();
        return !super.isMaxedOut(persuasions, convMaxNumberOfPersuasions);
    }

    @Override
    public void increaseAmountOfConv(Villager villager) {
        int persuasions = villager.getNumberOfPersuasions();
        villager.setNumberOfPersuations(persuasions + 1);
    }

    @Override
    public int[] calculatePlayerAndVilValues(Player player, Villager villager) {
        int[] values = new int[2];

        int playerValue = player.getCharisma();
        values[0] = playerValue;
        int vilValue = villager.getSelfAwareness();
        values[1] = vilValue;

        return values;
    }

    @Override
    public void winningActions(Player player, Villager villager, Sect sect) {
        villager.setSelfAwareness(villager.getSelfAwareness()
                - defaultConvPersVilSelfAwDecr);
        villager.setScepticism(villager.getScepticism()
                - defaultConvPersVilSceptDecr);
        player.setCharisma(player.getCharisma()
                + defaultConvPersPlayerCharIncr);
    }
}
