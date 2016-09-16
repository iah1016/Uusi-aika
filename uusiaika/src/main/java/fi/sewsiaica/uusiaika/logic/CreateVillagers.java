/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author iah1016
 */
public class CreateVillagers {
    
    private Random random;

    public CreateVillagers(Random random) {
        this.random = random;
    }

    public ArrayList<Villager> populateVillage(int quantity, String[] names, String[] profs) {
        //Villager(String name, boolean inSect, int scepticism, int selfEsteem, int selfAwareness, int argSkills, String profession)
        ArrayList<Villager> vlist = new ArrayList<Villager>();
        
        String[] namesForVillagers = pickStrings(quantity, names);
        int[] sceptValues = pickRandomNumbers(quantity, 10, 50);
        int[] selfEsValues = pickRandomNumbers(quantity, 10, 50);
        int[] selfAwValues = pickRandomNumbers(quantity, 10, 50);
        int[] argSkillsValues = pickRandomNumbers(quantity, 10, 50);
        String[] professions = pickStrings(quantity, profs);

        for (int i = 0; i < quantity; i++) {
            vlist.add(new Villager(namesForVillagers[i], false,
                    sceptValues[i], selfEsValues[i], selfAwValues[i],
                    argSkillsValues[i], professions[i]));
        }
        
        return vlist;
    }

    public String[] pickStrings(int quantity, String[] selection) {
        String[] stringArray = new String[quantity];

        for (int i = 0; i < quantity; i++) {
            stringArray[i] = selection[i % selection.length];
        }

        return stringArray;
    }

    public int[] pickRandomNumbers(int quantity, int baseValue, int bound) {
        
        int[] numbers = new int[quantity];

        for (int i = 0; i < quantity; i++) {
            numbers[i] = baseValue + random.nextInt(bound);
        }

        return numbers;
    }
}
