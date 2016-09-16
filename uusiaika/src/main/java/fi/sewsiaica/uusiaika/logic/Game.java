package fi.sewsiaica.uusiaika.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author iah1016
 */
public class Game {

    private Random random;
    private Player player;
    private ArrayList<Villager> villagers;

    public Game() {
        //Later from a file
        String[] namesForVillagers = {"Jaakko P", "Harri H", "Mikko M", "Teemu P",
            "Ilona R", "Taina E", "Marika M", "Robert F", "Cecilia C", "Oleg M"};
        String[] professions = {"Kauppias", "Leipuri", "Opettaja", "Postinjakaja",
            "Lääkäri", "Radiojuontaja", "Poliisi", "Bussikuski", "Putkimies",
            "Poliitikko", "Tutkija", "Apteekkari", "AD", "Toimitusjohtaja",};

        this.random = new Random();
        // attributes: charisma, arg.skills
        this.player = new Player(10, 10);
        this.villagers = new ArrayList<Villager>();
        populateVillage(namesForVillagers.length, namesForVillagers, professions);
    }

    public void populateVillage(int quantity, String[] names, String[] profs) {
        //Villager(String name, boolean inSect, int scepticism, int selfEsteem, int selfAwareness, int argSkills, String profession)

        String[] namesForVillagers = pickStrings(quantity, names);
        int[] sceptValues = pickRandomNumbers(quantity, 10);
        int[] selfEsValues = pickRandomNumbers(quantity, 10);
        int[] selfAwValues = pickRandomNumbers(quantity, 10);
        int[] argSkillsValues = pickRandomNumbers(quantity, 10);
        String[] professions = pickStrings(quantity, profs);

        for (int i = 0; i < quantity; i++) {
            villagers.add(new Villager(namesForVillagers[i], false,
                    sceptValues[i], selfEsValues[i], selfAwValues[i],
                    argSkillsValues[i], professions[i]));
        }
    }

    private String[] pickStrings(int quantity, String[] selection) {
        String[] stringArray = new String[quantity];

        for (int i = 0; i < quantity; i++) {
            stringArray[i] = selection[i % quantity];
        }

        return stringArray;
    }

    private int[] pickRandomNumbers(int quantity, int baseValue) {
        int[] numbers = new int[quantity];

        for (int i = 0; i < quantity; i++) {
            numbers[i] = baseValue + random.nextInt(50);
        }

        return numbers;
    }

    public ArrayList<Villager> getVillagers() {
        return villagers;
    }

}
