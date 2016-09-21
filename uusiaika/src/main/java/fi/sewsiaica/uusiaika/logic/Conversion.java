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
    private int[] maxNumbers;

    public Conversion(Random random, int[] maxNumbers) {
        this.random = random;
        this.maxNumbers = maxNumbers;
        this.maxNumberOfPersuasions = maxNumbers[0];
        this.maxNumberOfSermons = maxNumbers[1];
        this.maxNumberOfAccusations = maxNumbers[2];
    }

    public boolean isMaxedOut(int val, int max) {
        if (val >= max || val < 0) {
            return true;
        }

        return false;
    }

    public boolean checkIfAllowedToProceed(char type, Villager villager) {
        if (type == 'a') {
            int persuasions = villager.getNumberOfPersuasions();
            return !isMaxedOut(persuasions, maxNumbers[0]);
        } else if (type == 'b') {
            int sermons = villager.getNumberOfSermons();
            return !isMaxedOut(sermons, maxNumbers[1]);
        } else if (type == 'c') {
            int accusations = villager.getNumberOfAccusations();
            return !isMaxedOut(accusations, maxNumbers[2]);
        }
        return false;
    }

    public void increaseAmountOfConv(char type, Villager villager) {
        if (type == 'a') {
            int persuasions = villager.getNumberOfPersuasions();
            villager.setNumberOfPersuations(persuasions + 1);
        } else if (type == 'b') {
            int sermons = villager.getNumberOfSermons();
            villager.setNumberOfSermons(sermons + 1);
        } else if (type == 'c') {
            int accusations = villager.getNumberOfAccusations();
            villager.setNumberOfAccusations(accusations + 1);
        }
    }

    public boolean convSucceeds(int plVal, int vilVal, int plIncr,
            int vilIncr) {

        plVal += random.nextInt(plIncr);
        vilVal += random.nextInt(vilIncr);

        if (plVal >= vilVal) {
            return true;
        }
        return false;
    }

    public boolean persuasion(Player player, Villager villager) {
        increaseAmountOfConv('a', villager);
        int playerCharisma = player.getCharisma();
        int vilSelfAw = villager.getSelfAwareness();
        int vilScept = villager.getScepticism();
        boolean successfulConv = convSucceeds(playerCharisma,
                vilSelfAw, 20, 20);

        if (successfulConv) {
            villager.setSelfAwareness(vilSelfAw - 5);
            villager.setScepticism(vilScept - 5);
            player.setCharisma(playerCharisma + 2);
            return true;
        }
        return false;
    }

    public boolean sermon(Player player, Villager villager) {
        increaseAmountOfConv('b', villager);
        int playerCharisma = player.getCharisma();
        int vilScept = villager.getScepticism();
        int playerValue = playerCharisma + player.getArgSkills();
        int vilValue = vilScept + villager.getArgSkills();
        boolean successfulConv = convSucceeds(playerValue,
                vilValue, 20, 20);

        if (successfulConv) {
            villager.setInSect(true);
            player.setCharisma(playerCharisma + 2);
            villager.setScepticism(vilScept - 5);
            return true;
        }
        return false;
    }

    public boolean accusation(Player player, Villager villager) {
        increaseAmountOfConv('c', villager);
        int playerCharisma = player.getCharisma();
        int vilSelfEs = villager.getSelfEsteem();
        int playerValue = playerCharisma + player.getArgSkills();
        int vilValue = vilSelfEs + villager.getArgSkills();
        boolean successfulConv = convSucceeds(playerValue, vilValue, 20, 20);

        if (successfulConv) {
            villager.setInSect(true);
            player.setCharisma(playerCharisma + 2);
            villager.setSelfEsteem(vilSelfEs - 5);
            return true;
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
