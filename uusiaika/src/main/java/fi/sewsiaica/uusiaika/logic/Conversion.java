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

    // Move these to a yaml file
    private int maxNumberOfPersuasions;
    private int maxNumberOfSermons;
    private int maxNumberOfAccusations;
    private final int defaultConvPersPlayerBound = 20;
    private final int defaultConvPersVilBound = 20;
    private final int defaultConvSermPlayerBound = 20;
    private final int defaultConvSermVilBound = 20;
    private final int defaultConvAccuPlayerBound = 20;
    private final int defaultConvAccuVilBound = 20;
    private final int defaultConvPersPlayerCharIncr = 2;
    private final int defaultConvSermPlayerCharIncr = 2;
    private final int defaultConvAccuPlayerCharIncr = 2;
    private final int defaultConvPersVilSelfAwDecr = 5;
    private final int defaultConvPersVilSceptDecr = 5;
    private final int defaultConvSermVilSceptDecr = 5;
    private final int defaultConvAccuVilSelfEsDecr = 5;

    public Conversion(Random random, int[] maxNumbers) {
        this.random = random;
        this.maxNumberOfPersuasions = maxNumbers[0];
        this.maxNumberOfSermons = maxNumbers[1];
        this.maxNumberOfAccusations = maxNumbers[2];
    }

    public boolean isMaxedOut(int val, int max) {
        return val >= max || val < 0;
    }

    public boolean checkIfAllowedToProceed(char type, Villager villager) {
        switch (type) {
            case 'a':
                int persuasions = villager.getNumberOfPersuasions();
                return !isMaxedOut(persuasions, maxNumberOfPersuasions);
            case 'b':
                int sermons = villager.getNumberOfSermons();
                return !isMaxedOut(sermons, maxNumberOfSermons);
            case 'c':
                int accusations = villager.getNumberOfAccusations();
                return !isMaxedOut(accusations, maxNumberOfAccusations);
            default:
                return false;
        }
    }

    public void increaseAmountOfConv(char type, Villager villager) {
        switch (type) {
            case 'a':
                int persuasions = villager.getNumberOfPersuasions();
                villager.setNumberOfPersuations(persuasions + 1);
                break;
            case 'b':
                int sermons = villager.getNumberOfSermons();
                villager.setNumberOfSermons(sermons + 1);
                break;
            case 'c':
                int accusations = villager.getNumberOfAccusations();
                villager.setNumberOfAccusations(accusations + 1);
                break;
            default:
                break;
        }
    }

    public boolean convSucceeds(int plVal, int vilVal, int plIncr,
            int vilIncr) {
        if (plVal < 0 || vilVal < 0) {
            return false;
        }

        plVal += random.nextInt(plIncr);
        vilVal += random.nextInt(vilIncr);

        return plVal >= vilVal;
    }

    public boolean persuasion(Player player, Villager villager, Sect sect) {
        increaseAmountOfConv('a', villager);
        int playerCharisma = player.getCharisma();
        int vilSelfAw = villager.getSelfAwareness();
        int vilScept = villager.getScepticism();
        boolean successfulConv = convSucceeds(playerCharisma, vilSelfAw,
                defaultConvPersPlayerBound, defaultConvPersVilBound);

        if (successfulConv) {
            villager.setSelfAwareness(vilSelfAw - defaultConvPersVilSelfAwDecr);
            villager.setScepticism(vilScept - defaultConvPersVilSceptDecr);
            player.setCharisma(playerCharisma + defaultConvPersPlayerCharIncr);
            return true;
        }
        return false;
    }

    public boolean sermon(Player player, Villager villager, Sect sect) {
        increaseAmountOfConv('b', villager);
        int playerCharisma = player.getCharisma();
        int vilScept = villager.getScepticism();
        int playerValue = playerCharisma + player.getArgSkills();
        int vilValue = vilScept + villager.getArgSkills();
        boolean successfulConv = convSucceeds(playerValue, vilValue,
                defaultConvSermPlayerBound, defaultConvSermVilBound);

        if (successfulConv) {
            villager.setInSect(true);
            sect.getCongregation().add(villager);
            player.setCharisma(playerCharisma + defaultConvSermPlayerCharIncr);
            villager.setScepticism(vilScept - defaultConvSermVilSceptDecr);
            return true;
        }
        return false;
    }

    public boolean accusation(Player player, Villager villager, Sect sect) {
        increaseAmountOfConv('c', villager);
        int playerCharisma = player.getCharisma();
        int vilSelfEs = villager.getSelfEsteem();
        int playerValue = playerCharisma + player.getArgSkills();
        int vilValue = vilSelfEs + villager.getArgSkills();
        boolean successfulConv = convSucceeds(playerValue, vilValue,
                defaultConvAccuPlayerBound, defaultConvAccuVilBound);

        if (successfulConv) {
            villager.setInSect(true);
            sect.getCongregation().add(villager);
            player.setCharisma(playerCharisma + defaultConvAccuPlayerCharIncr);
            villager.setSelfEsteem(vilSelfEs - defaultConvAccuVilSelfEsDecr);
            return true;
        }
        return false;
    }
}
