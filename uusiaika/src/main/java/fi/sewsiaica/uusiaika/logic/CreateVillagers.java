/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Villager;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author iah1016
 */
public class CreateVillagers {

    private Random random;
    // Move these to a yaml file
    private final int defaultVilBaseScepticism = 10;
    private final int defaultVilBaseSelfEs = 10;
    private final int defaultVilBaseSelfAw = 10;
    private final int defaultVilBaseArgSkills = 10;
    private final int defaultVilBoundValue = 51;

    public CreateVillagers(Random random) {
        this.random = random;
    }

    public ArrayList<Villager> populateVillage(int quantity, String[] names,
            String[] profs) {
        // Villager(String name, boolean inSect, int scepticism, int selfEsteem,
        // int selfAwareness, int argSkills, String profession)
        ArrayList<Villager> vlist = new ArrayList<Villager>();

        String[] namesForVillagers = pickStrings(quantity, names);
        int[] sceptValues = pickRandomNumbers(quantity,
                defaultVilBaseScepticism, defaultVilBoundValue);
        int[] selfEsValues = pickRandomNumbers(quantity,
                defaultVilBaseSelfEs, defaultVilBoundValue);
        int[] selfAwValues = pickRandomNumbers(quantity,
                defaultVilBaseSelfAw, defaultVilBoundValue);
        int[] argSkillsValues = pickRandomNumbers(quantity,
                defaultVilBaseArgSkills, defaultVilBoundValue);
        String[] professions = pickStrings(quantity, profs);

        for (int i = 0; i < quantity; i++) {
            vlist.add(new Villager(namesForVillagers[i], false,
                    sceptValues[i], selfEsValues[i], selfAwValues[i],
                    argSkillsValues[i], professions[i]));
        }
        return vlist;
    }

    public String[] pickStrings(int quantity, String[] selection) {
        if (quantity <= 0) {
            return null;
        }

        String[] stringArray = new String[quantity];

        for (int i = 0; i < quantity; i++) {
            stringArray[i] = selection[i % selection.length];
        }

        return stringArray;
    }

    public int[] pickRandomNumbers(int quantity, int baseValue, int bound) {
        if (quantity <= 0 || bound <= 0) {
            return null;
        }

        int[] numbers = new int[quantity];

        for (int i = 0; i < quantity; i++) {
            numbers[i] = baseValue + random.nextInt(bound);
        }
        return numbers;
    }
}
