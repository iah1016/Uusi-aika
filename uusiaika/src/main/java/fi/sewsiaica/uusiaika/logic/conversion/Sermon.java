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
public class Sermon extends Conversion {

    private int convMaxNumberOfSermons;
    private int convSermPlayerCharIncr;
    private int convSermVilSceptDecr;

    public Sermon(Random random, Map<String, Integer> intValues,
            int maxNumberOfConversions, int playerRandomBound,
            int vilRandomBound) {
        super(random, intValues, maxNumberOfConversions, playerRandomBound,
                vilRandomBound);
        this.convMaxNumberOfSermons = maxNumberOfConversions;
        this.convSermPlayerCharIncr = intValues.get("convSermPlayerCharIncr");
        this.convSermVilSceptDecr = intValues.get("convSermVilSceptDecr");
    }

    @Override
    public boolean checkIfAllowedToProceed(Villager villager) {
        int sermons = villager.getNumberOfSermons();
        return !super.isMaxedOut(sermons, convMaxNumberOfSermons);
    }

    @Override
    public void increaseAmountOfConv(Villager villager) {
        int sermons = villager.getNumberOfSermons();
        villager.setNumberOfSermons(sermons + 1);
    }

    @Override
    public int[] calculatePlayerAndVilValues(Player player, Villager villager) {
        int[] values = new int[2];

        int playerValue = player.getCharisma() + player.getArgSkills();
        values[0] = playerValue;
        int vilValue = villager.getScepticism() + villager.getArgSkills();
        values[1] = vilValue;

        return values;
    }

    @Override
    public void winningActions(Player player, Villager villager, Sect sect) {
        villager.setInSect(true);
        sect.getCongregation().add(villager);
        player.setCharisma(player.getCharisma()
                + convSermPlayerCharIncr);
        villager.setScepticism(villager.getScepticism()
                - convSermVilSceptDecr);
    }
    
    @Override
    public void setMaxNumberOfConversions(int maxNumberOfConversions) {
        this.convMaxNumberOfSermons = maxNumberOfConversions;
    }
}
