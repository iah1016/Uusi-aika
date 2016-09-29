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
                - convPersVilSelfAwDecr);
        villager.setScepticism(villager.getScepticism()
                - convPersVilSceptDecr);
        player.setCharisma(player.getCharisma()
                + convPersPlayerCharIncr);
    }
    
    @Override
    public void setMaxNumberOfConversions(int maxNumberOfConversions) {
        this.convMaxNumberOfPersuasions = maxNumberOfConversions;
    }
}
