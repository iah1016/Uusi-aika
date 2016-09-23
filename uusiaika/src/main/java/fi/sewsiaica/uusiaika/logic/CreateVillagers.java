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
        ArrayList<Villager> vlist = new ArrayList<>();

        String[] namesForVillagers = pickStrings(quantity, names);
        ArrayList<int[]> atlists = makeAttribLists(quantity);
        String[] professions = pickStrings(quantity, profs);

        return addVillagersToList(vlist, quantity, namesForVillagers,
                atlists.get(0), atlists.get(1), atlists.get(2), atlists.get(3),
                professions);
    }

    public ArrayList<int[]> makeAttribLists(int quantity) {
        ArrayList<int[]> attribLists = new ArrayList<>();

        attribLists.add(pickRandomNumbers(quantity, defaultVilBaseScepticism,
                defaultVilBoundValue));
        attribLists.add(pickRandomNumbers(quantity, defaultVilBaseSelfEs,
                defaultVilBoundValue));
        attribLists.add(pickRandomNumbers(quantity, defaultVilBaseSelfAw,
                defaultVilBoundValue));
        attribLists.add(pickRandomNumbers(quantity, defaultVilBaseArgSkills,
                defaultVilBoundValue));
        return attribLists;
    }

    public ArrayList<Villager> addVillagersToList(ArrayList<Villager> vlist,
            int quantity, String[] names, int[] scept, int[] selfEs,
            int[] selfAwValues, int[] argSkills, String[] profs) {

        for (int i = 0; i < quantity; i++) {
            vlist.add(new Villager(names[i], false, scept[i], selfEs[i],
                    selfAwValues[i], argSkills[i], profs[i]));
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
