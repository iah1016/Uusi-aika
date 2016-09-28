package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.logic.conversion.*;
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
    private Conversion persuasion;
    private Conversion sermon;
    private Conversion accusation;
    private TurnLogic turnLogic;
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
    // Conversion types are Persuasion, Sermon, Accusation
    // Persuasion
    private final int defaultConvMaxNumberOfPersuasions = 3;
    private final int defaultConvPersPlayerBound = 20;
    private final int defaultConvPersVilBound = 20;
    private final int defaultConvPersPlayerCharIncr = 2;
    private final int defaultConvPersVilSelfAwDecr = 5;
    private final int defaultConvPersVilSceptDecr = 5;
    // Sermon
    private final int defaultConvMaxNumberOfSermons = 2;
    private final int defaultConvSermPlayerBound = 20;
    private final int defaultConvSermVilBound = 20;
    private final int defaultConvSermPlayerCharIncr = 2;
    private final int defaultConvSermVilSceptDecr = 5;
    // Accusation
    private final int defaultConvMaxNumberOfAccusations = 2;
    private final int defaultConvAccuPlayerBound = 20;
    private final int defaultConvAccuVilBound = 20;
    private final int defaultConvAccuPlayerCharIncr = 2;
    private final int defaultConvAccuVilSelfEsDecr = 5;
    // temporary arrays for Conversion types
    private final int[] convPersRandomBounds = {20, 20};
    private final int[] convSermRandomBounds = {20, 20};
    private final int[] convAccuRandomBounds = {20, 20};
    private final int[] convPersPlayerAttribIncr = {2};
    private final int[] convSermPlayerAttribIncr = {2};
    private final int[] convAccuPlayerAttribIncr = {2};
    private final int[] convPersVilAttribDecr = {5, 5};
    private final int[] convSermVilAttribDecr = {5};
    private final int[] convAccuVilAttribDecr = {5};
    //        
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
        // 
        this.persuasion = new Persuasion(random,
                defaultConvMaxNumberOfPersuasions, convPersRandomBounds,
                convPersPlayerAttribIncr, convPersVilAttribDecr);
        this.sermon = new Sermon(random,
                defaultConvMaxNumberOfSermons, convSermRandomBounds,
                convSermPlayerAttribIncr, convSermVilAttribDecr);
        this.accusation = new Accusation(random,
                defaultConvMaxNumberOfAccusations, convAccuRandomBounds,
                convAccuPlayerAttribIncr, convAccuVilAttribDecr);
        //
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
        if (option == 'a' && !persuasion.checkIfAllowedToProceed(villager)) {
            return persuasion.convert(player, villager, sect);
        }
        if (option == 'b' && !sermon.checkIfAllowedToProceed(villager)) {
            return sermon.convert(player, villager, sect);
        }
        if (option == 'c' && !accusation.checkIfAllowedToProceed(villager)) {
            return accusation.convert(player, villager, sect);
        }
        return false;
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

    public Conversion getPersuasion() {
        return persuasion;
    }

    public Conversion getSermon() {
        return sermon;
    }

    public Conversion getAccusation() {
        return accusation;
    }
}
