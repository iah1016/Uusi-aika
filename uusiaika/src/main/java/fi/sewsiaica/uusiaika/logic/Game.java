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

    public Game() {
        this.random = new Random();
        
        //Later from a file
        String[] namesForVillagers = {"Jaakko P", "Harri H", "Mikko M", "Teemu P",
            "Ilona R", "Taina E", "Marika M", "Robert F", "Cecilia C", "Oleg M"};
        String[] professions = {"Kauppias", "Leipuri", "Opettaja", "Postinjakaja",
            "Lääkäri", "Radiojuontaja", "Poliisi", "Bussikuski", "Putkimies",
            "Poliitikko", "Tutkija", "Apteekkari", "AD", "Toimitusjohtaja"};
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
        //a  charisma vs. selfAwareness + scepticism
        //b  charisma + argSkills vs. argSkills vs. scepticism
        //c  charisma vs. selfEsteem + argSkills
        return false;
    }
    
    public boolean persuasion(Villager villager) {
        return false;
    }
    
    public boolean sermon(Villager villager) {
        return false;
    }
    
    public boolean accusation(Villager villager) {
        return false;
    }
    
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
