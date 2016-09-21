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
    private TrainingCentre trainingCentre;

    // Move these to a yaml file
    private final int defaultPlayerCharisma = 10;
    private final int defaultPlayerArgSkills = 10;
    private final int defaultSectExpenses = 1000;
    private final int defaultSectMemberFee = 100;
    private final int defaultTrainingCharismaIncr = 10;
    private final int defaultTrainingArgSkillsIncr = 10;
    private int numberOfVillagers;

    public Game(Random random, String[] namesForVillagers,
            String[] professions, int[] maxNumbersForConversion) {
        this.conversion = new Conversion(random, maxNumbersForConversion);
        this.trainingCentre = new TrainingCentre(defaultTrainingCharismaIncr,
                defaultTrainingArgSkillsIncr);

        // Move this to a yaml file
        numberOfVillagers = namesForVillagers.length;

        // Change this so that the values come from a yaml file.
        CreateVillagers cv = new CreateVillagers(random);
        this.villagers = cv.populateVillage(numberOfVillagers,
                namesForVillagers, professions);
    }

    public void initGame(String[] names) {
        String[] playerAndSectNames = names;

        // This creates Player and Sect objects.
        // Player attributes: name, charisma, arg.skills
        this.player = new Player(playerAndSectNames[0], defaultPlayerCharisma,
                defaultPlayerArgSkills);
        // Sect attributes: name, expenses, member fee
        this.sect = new Sect(playerAndSectNames[1], defaultSectExpenses,
                defaultSectMemberFee);
    }

    public boolean conversion(Villager villager, String option) {
        // Player attempts to convert a villager. Future expansion allows
        // a Villager (Sect member) to convert other villagers.
        // (a) Persuasion:  charisma vs. selfAwareness
        // (b) Sermon:      charisma + argSkills vs. scepticism + argSkills
        // (c) Accusation:  charisma + argSkills vs. selfEsteem + argSkills
        if (option.equals("a")) {
            if (!conversion.checkIfAllowedToProceed('a', villager)) {
                return false;
            }
            return conversion.persuasion(player, villager);
        } else if (option.equals("b")) {
            if (!conversion.checkIfAllowedToProceed('b', villager)) {
                return false;
            }
            return conversion.sermon(player, villager);
        } else if (option.equals("c")) {
            if (!conversion.checkIfAllowedToProceed('c', villager)) {
                return false;
            }
            return conversion.accusation(player, villager);
        }
        return false;
    }

    public void holySiteActions(String option) {
        // These are the actions that are set in the sect's holy site.
        // (a) Preach
        // (b) Offer soda to the congregation 
        // (c) Buy a one-way ticket to a paradise island
        // Option (a) will decrease the scepticism of all the sect members.
        // Options (b) and (c) will end the game, if the conditions are met.
//        if (option.equals("a")) {
//
//        } else if (option.equals("b")) {
//
//        } else if (option.equals("c")) {
//
//        }
    }

    public void trainingCentreActions(String option) {
        // These are the actions that are set in the training centre.
        // (a) apply for a charisma course
        // (b) apply for a debate course
        if (option.equals("a")) {
            trainingCentre.applyForCharismaCourse(player);
        } else if (option.equals("b")) {
            trainingCentre.applyForDebateCourse(player);
        }
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
