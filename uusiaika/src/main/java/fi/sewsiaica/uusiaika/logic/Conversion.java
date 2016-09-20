/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.Random;

/**
 *
 * @author iah1016
 */
public class Conversion {

    private Random random;
    private final int maxNumberOfPersuasions;
    private final int maxNumberOfSermons;
    private final int maxNumberOfAccusations;

    public Conversion(Random random, int[] maxNumbers) {
        this.random = random;
        this.maxNumberOfPersuasions = maxNumbers[0];
        this.maxNumberOfSermons = maxNumbers[1];
        this.maxNumberOfAccusations = maxNumbers[2];
    }

    public boolean convSucceeds(int plVal, int vilVal, int plIncr,
            int vilIncr, boolean maxedOut) {

        plVal += random.nextInt(plIncr);
        vilVal += random.nextInt(vilIncr);

        if (plVal >= vilVal && !maxedOut) {
            return true;
        }
        return false;
    }

    public boolean isMaxedOut(int val, int max) {
        if (val >= max || val < 0) {
            return true;
        }

        return false;
    }

    public boolean persuasion(Player player, Villager villager) {
        int persuasions = villager.getNumberOfPersuasions();
        int playerCharisma = player.getCharisma();
        int vilSelfAw = villager.getSelfAwareness();
        int vilScept = villager.getScepticism();

        boolean maxedOut = isMaxedOut(persuasions, maxNumberOfPersuasions);
        boolean maxedOut2 = isMaxedOut(persuasions, maxNumberOfPersuasions - 1);
        boolean successfulConversion = convSucceeds(playerCharisma,
                vilSelfAw, 20, 20, maxedOut2);

        if (!maxedOut) {
            if (successfulConversion) {
                villager.setSelfAwareness(vilSelfAw - 5);
                villager.setScepticism(vilScept - 5);
                player.setCharisma(playerCharisma + 2);
            }
            villager.setNumberOfPersuations(persuasions + 1);
            return true;
        }
        return false;
    }

    public boolean sermon(Player player, Villager villager) {
        int sermons = villager.getNumberOfSermons();
        int playerCharisma = player.getCharisma();
        int vilScept = villager.getScepticism();
        int playerValue = playerCharisma + player.getArgSkills();
        int vilValue = vilScept + villager.getArgSkills();

        boolean maxedOut = isMaxedOut(sermons, maxNumberOfSermons);
        boolean maxedOut2 = isMaxedOut(sermons, maxNumberOfSermons - 1);
        boolean successfulConversion = convSucceeds(playerValue,
                vilValue, 20, 20, maxedOut2);
        
        if (!maxedOut) {
            if (successfulConversion) {
                villager.setInSect(true);
                player.setCharisma(playerCharisma + 2);
                villager.setScepticism(vilScept - 5);
            }
            villager.setNumberOfSermons(sermons + 1);
        }
        return false;
    }

    public boolean accusation(Player player, Villager villager) {
        int accusations = villager.getNumberOfAccusations();
        int playerCharisma = player.getCharisma();
        int vilSelfEs = villager.getSelfEsteem();
        int playerValue = playerCharisma + player.getArgSkills();
        int vilValue = vilSelfEs + villager.getArgSkills();

        boolean maxedOut = isMaxedOut(accusations, maxNumberOfAccusations);
        boolean maxedOut2 = isMaxedOut(accusations, maxNumberOfAccusations - 1);
        boolean successfulConversion = convSucceeds(playerValue,
                vilValue, 20, 20, maxedOut2);

        if (!maxedOut) {
            if (successfulConversion) {
                villager.setInSect(true);
                player.setCharisma(playerCharisma + 2);
                villager.setSelfEsteem(vilSelfEs - 5);
            }
            villager.setNumberOfAccusations(accusations + 1);
        }
        return false;
    }

    public int getMaxNumberOfPersuasions() {
        return maxNumberOfPersuasions;
    }

    public int getMaxNumberOfSermons() {
        return maxNumberOfSermons;
    }

    public int getMaxNumberOfAccusations() {
        return maxNumberOfAccusations;
    }
}
