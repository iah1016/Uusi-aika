package fi.sewsiaica.uusiaika.logic;

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

    public Game(String[] namesForVillagers, String[] professions) {
        this.random = new Random();
        CreateVillagers cv = new CreateVillagers(random);
        int quantity = namesForVillagers.length;
        this.villagers = cv.populateVillage(quantity, namesForVillagers, professions);
    }

    public void initGame(String[] names) {
        String[] playerAndSectNames = names;
        // attributes: name, charisma, arg.skills
        this.player = new Player(playerAndSectNames[0], 10, 10);
        // attributes: name, expenses, member fee
        this.sect = new Sect(playerAndSectNames[1], 1000, 10);
    }

    public boolean conversion(Villager villager, String option) {
        //a  charisma vs. selfAwareness
        //b  charisma + argSkills vs. argSkills vs. scepticism
        //c  charisma + argSkills vs. selfEsteem + argSkills
        if (option.equals("a")) {
            return persuasion(villager);
        } else if (option.equals("b")) {
            return sermon(villager);
        } else if (option.equals("c")) {
            return accusation(villager);
        }

        return false;
    }

    public boolean persuasion(Villager villager) {
        if (villager.getNoOfPersuations() < 3) {
            if (player.getCharisma() + random.nextInt(20)
                    >= villager.getSelfAwareness() + random.nextInt(20)) {

                villager.setSelfAwareness(villager.getSelfAwareness() - 5);
                villager.setScepticism(villager.getScepticism() - 5);
                player.setCharisma(player.getCharisma() + 2);
            }
            villager.setNoOfPersuations(villager.getNoOfPersuations() + 1);
            return true;
        }
        return false;
    }

    public boolean sermon(Villager villager) {
        if (villager.getNoOfSermons() < 2) {
            if (player.getCharisma() + player.getArgSkills()
                    + random.nextInt(20) >= villager.getArgSkills()
                    + villager.getScepticism() + random.nextInt(20)) {

                villager.setInSect(true);
                player.setCharisma(player.getCharisma() + 2);
            }
            villager.setNoOfSermons(villager.getNoOfSermons() + 1);
        }
        return false;
    }

    public boolean accusation(Villager villager) {
        if (villager.getNoOfAccusations() < 2) {
            if (player.getCharisma() + player.getArgSkills()
                    + random.nextInt(20) >= villager.getSelfEsteem()
                    + villager.getArgSkills() + random.nextInt(20)) {

                villager.setInSect(true);
                player.setCharisma(player.getCharisma() + 2);
            }
            villager.setNoOfAccusations(villager.getNoOfAccusations() + 1);
        }
        return false;
    }

    //setters and getters
    //
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Sect getSect() {
        return sect;
    }

    public void setSect(Sect sect) {
        this.sect = sect;
    }

    public void setVillagers(ArrayList<Villager> villagers) {
        this.villagers = villagers;
    }

    public ArrayList<Villager> getVillagers() {
        return villagers;
    }

}
