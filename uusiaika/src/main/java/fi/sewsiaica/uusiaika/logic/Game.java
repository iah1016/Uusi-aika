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
    private Conversion conversion;
    private Temple temple;
    private TrainingCentre trainingCentre;

    // Move these to a yaml file
    private final int defaultPlayerCharisma = 10;
    private final int defaultPlayerArgSkills = 10;
    private final int defaultSectBalance = 5000;
    private final int defaultSectExpenses = 1000;
    private final int defaultSectMemberFee = 100;
    private final int defaultTempleSceptDecr = 10;
    private final int defaultDeathCultCharismaReq = 255;
    private final int defaultDivineRightMoneyReq = 100000;
    private final int defaultTrainingCharismaIncr = 10;
    private final int defaultTrainingArgSkillsIncr = 10;
    private int numberOfVillagers;

    public Game(Random random, String[] namesForVillagers,
            String[] professions, int[] maxNumbersForConversion) {
        // New Conversion, Temple, TrainingCentre
        this.conversion = new Conversion(random, maxNumbersForConversion);
        this.temple = new Temple(defaultTempleSceptDecr,
                defaultDeathCultCharismaReq, defaultDivineRightMoneyReq);
        this.trainingCentre = new TrainingCentre(defaultTrainingCharismaIncr,
                defaultTrainingArgSkillsIncr);

        // Move this to a yaml file
        numberOfVillagers = namesForVillagers.length;

        // Change this so that the values come from a yaml file.
        CreateVillagers cv = new CreateVillagers(random);
        this.villagers = cv.populateVillage(numberOfVillagers,
                namesForVillagers, professions);
    }

    public boolean initGame(String[] playerAndSectNames) {
        // This method creates Player and Sect objects.
        if (givenStringArrayIsCorrectLength(playerAndSectNames, 2)) {
            // Player attributes: name, charisma, arg.skills
            this.player = new Player(playerAndSectNames[0],
                    defaultPlayerCharisma, defaultPlayerArgSkills);
            // Sect attributes: name, balance, expenses, member fee
            this.sect = new Sect(playerAndSectNames[1], defaultSectBalance,
                    defaultSectExpenses, defaultSectMemberFee);
            return true;
        }
        return false;
    }

    public boolean givenStringArrayIsCorrectLength(String[] array, int length) {
        if (array == null) {
            return false;
        }
        if (array.length == length) {
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
        if (conversion.checkIfAllowedToProceed(option, villager)) {
            if (option == 'a') {
                return conversion.persuasion(player, villager);
            }
            if (option == 'b') {
                return conversion.sermon(player, villager);
            }
            if (option == 'c') {
                return conversion.accusation(player, villager);
            }
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
        if (option == 'a') {
            return temple.preach(player, sect.getCongregation());
        }
        if (option == 'b') {
            return temple.offerSodaToAllMembers(player);
        }
        if (option == 'c') {
            return temple.buyTicketToParadiseIsland(player, sect);
        }
        return false;
    }

    public boolean trainingCentreActions(char option) {
        // These are the actions that are set in the training centre.
        // (a) apply for a charisma course
        // (b) apply for a debate course
        if (option == 'a') {
            return trainingCentre.applyForCharismaCourse(player);
        }
        if (option == 'b') {
            return trainingCentre.applyForDebateCourse(player);
        }
        return false;
    }

    // Setters
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSect(Sect sect) {
        this.sect = sect;
    }

    public void setVillagers(ArrayList<Villager> villagers) {
        this.villagers = villagers;
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
}
