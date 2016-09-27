package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.ui.TextbasedUI;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author iah1016
 */
public class Game {

    private TextbasedUI tui;
    private Player player;
    private Sect sect;
    private ArrayList<Villager> villagers;
    private TurnLogic turnLogic;
    private Conversion conversion;
    private Temple temple;
    private TrainingCentre trainingCentre;

    // Move these to a yaml file
    // Player
    private final int defaultPlayerCharisma = 10;
    private final int defaultPlayerArgSkills = 10;
    // Sect
    private final int defaultSectBalance = 5000;
    private final int defaultSectExpenses = 1000;
    private final int defaultSectMemberFee = 100;
    // CreateVillagers
    private final int numberOfVillagers = 10; // can be changed now
    // Conversion
    private final int defaultConvMaxNumberOfPersuasions = 3; // can be changed now
    private final int defaultConvMaxNumberOfSermons = 2; // can be changed now
    private final int defaultConvMaxNumberOfAccusations = 2; // can be changed now
    // TurnLogic
    private final int initialNumberOfTurns = 0;
    private final int defaultMaxNumberOfTurns = 100;
    private final int defaultSceptIncrPerTurn = 10;
    private final int defaultThresholdForScepticism = 200;
    // Temple
    private final int defaultTempleSceptDecr = 10;
    private final int defaultDeathCultCharismaReq = 255;
    private final int defaultDivineRightMoneyReq = 100000;
    // TrainingCentre
    private final int defaultTrainingCharismaIncr = 10;
    private final int defaultTrainingArgSkillsIncr = 10;
    //

    public Game(Random random, String[] namesForVillagers,
            String[] professions) {
        // Change this so that the values come from a yaml file.
        CreateVillagers cv = new CreateVillagers(random);
        this.villagers = cv.populateVillage(numberOfVillagers,
                namesForVillagers, professions);
        // New TurnLogic, Conversion, Temple, TrainingCentre
        this.turnLogic = new TurnLogic(initialNumberOfTurns,
                defaultMaxNumberOfTurns, defaultSceptIncrPerTurn,
                defaultThresholdForScepticism);
        this.conversion = new Conversion(random,
                defaultConvMaxNumberOfPersuasions,
                defaultConvMaxNumberOfSermons,
                defaultConvMaxNumberOfAccusations);
        this.temple = new Temple(defaultTempleSceptDecr,
                defaultDeathCultCharismaReq, defaultDivineRightMoneyReq);
        this.trainingCentre = new TrainingCentre(defaultTrainingCharismaIncr,
                defaultTrainingArgSkillsIncr);
    }

    public boolean initGame(String[] playerAndSectNames) {
        // This method creates Player and Sect objects.
        if (playerAndSectNames.length == 2) {
            this.player = new Player(playerAndSectNames[0],
                    defaultPlayerCharisma, defaultPlayerArgSkills);
            this.sect = new Sect(playerAndSectNames[1], defaultSectBalance,
                    defaultSectExpenses, defaultSectMemberFee);
            return true;
        }
        return false;
    }

    public boolean conversion(Villager villager, char option) {
        // Player attempts to convert a villager. Future expansion allows
        // a Villager (Sect member) to convert other villagers.
        // (a) Persuasion:  charisma vs. selfAwareness
        // (b) Sermon:      charisma + argSkills vs. scepticism + argSkills
        // (c) Accusation:  charisma + argSkills vs. selfEsteem + argSkills
        if (!conversion.checkIfAllowedToProceed(option, villager)) {
            return false;
        }
        switch (option) {
            case 'a':
                return conversion.persuasion(player, villager, sect);
            case 'b':
                return conversion.sermon(player, villager, sect);
            case 'c':
                return conversion.accusation(player, villager, sect);
            default:
                return false;
        }
    }

    public boolean templeActions(char option) {
        // These are the actions that are set in the sect's temple.
        // (a) Preach
        // (b) Offer soda to the congregation 
        // (c) Buy a one-way ticket to a paradise island
        // Option (a) will decrease the scepticism of all the sect members.
        // Options (b) and (c) will end the game, if the conditions are met.
        switch (option) {
            case 'a':
                return temple.preach(player, sect);
            case 'b':
                return temple.offerSodaToAllMembers(player);
            case 'c':
                return temple.buyTicketToParadiseIsland(player, sect);
            default:
                return false;
        }
    }

    public boolean trainingCentreActions(char option) {
        // These are the actions that are set in the training centre.
        // (a) apply for a charisma course
        // (b) apply for a debate course
        switch (option) {
            case 'a':
                return trainingCentre.applyForCharismaCourse(player);
            case 'b':
                return trainingCentre.applyForDebateCourse(player);
            default:
                return false;
        }
    }

    // Getters
    public Player getPlayer() {
        return player;
    }

    public Sect getSect() {
        return sect;
    }

    public ArrayList<Villager> getVillagers() {
        return villagers;
    }

    public Conversion getConversion() {
        return conversion;
    }
}
