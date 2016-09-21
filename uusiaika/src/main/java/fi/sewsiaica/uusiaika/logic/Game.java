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
    private Random random;
    private Player player;
    private Sect sect;
    private ArrayList<Villager> villagers;
    private Conversion conversion;
    
    // Move these to a yaml file
    private final int defaultPlayerCharisma = 10;
    private final int defaultPlayerArgSkills = 10;
    private final int defaultSectExpenses = 1000;
    private final int defaultSectMemberFee = 10;
    private int numberOfVillagers;

    public Game(Random random, String[] namesForVillagers,
            String[] professions, int[] maxNumbersForConversion) {
        this.random = random;
        
        // Move this to a yaml file
        numberOfVillagers = namesForVillagers.length;

        CreateVillagers cv = new CreateVillagers(random);
        this.villagers = cv.populateVillage(numberOfVillagers,
                namesForVillagers, professions);

        this.conversion = new Conversion(random, maxNumbersForConversion);
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
