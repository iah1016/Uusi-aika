/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Villager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author iah1016
 */
public class CreateVillagers {

    private Random random;
    private Map<String, Integer> intValues;
    private List<String> vilNames;
    private List<String> professions;
    private int numberOfVillagers;

    public CreateVillagers(Random random, Map<String, Integer> intValues,
            List<String> vilNames, List<String> professions) {
        this.random = random;
        this.intValues = intValues;
        this.vilNames = vilNames;
        this.professions = professions;
        this.numberOfVillagers = intValues.get("vilPopulation");
    }

    public List<Villager> populateVillage() {
        String[] namesArray = pickStrings(numberOfVillagers, vilNames);
        List<int[]> atlists = makeAttribLists(numberOfVillagers);
        String[] profsArray = pickStrings(numberOfVillagers, professions);

        return addVillagersToVList(numberOfVillagers, namesArray,
                atlists.get(0), atlists.get(1), atlists.get(2), atlists.get(3),
                profsArray);
    }

    public List<int[]> makeAttribLists(int quantity) {
        List<int[]> attribLists = new ArrayList<>();
        int bound = intValues.get("vilBoundValue");

        attribLists.add(pickRandomNumbers(quantity,
                intValues.get("vilBaseScepticism"), bound));
        attribLists.add(pickRandomNumbers(quantity,
                intValues.get("vilBaseSelfEs"), bound));
        attribLists.add(pickRandomNumbers(quantity,
                intValues.get("vilBaseSelfAw"), bound));
        attribLists.add(pickRandomNumbers(quantity,
                intValues.get("vilBaseArgSkills"), bound));
        return attribLists;
    }

    public List<Villager> addVillagersToVList(int quantity, String[] names,
            int[] scept, int[] selfEs, int[] selfAwValues, int[] argSkills,
            String[] profs) {
        
        List<Villager> vlist = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            vlist.add(new Villager(names[i], false, scept[i], selfEs[i],
                    selfAwValues[i], argSkills[i], profs[i]));
        }
        return vlist;
    }

    public String[] pickStrings(int quantity, List<String> selection) {
        if (quantity < 1) {
            return new String[0];
        }
        String[] strings = new String[quantity];

        for (int i = 0; i < quantity; i++) {
            strings[i] = (selection.get(i % selection.size()));
        }

        return strings;
    }

    public int[] pickRandomNumbers(int quantity, int baseValue, int bound) {
        if (quantity < 1 || bound < 1) {
            return new int[0];
        }
        
        int[] numbers = new int[quantity];

        for (int i = 0; i < quantity; i++) {
            numbers[i] = baseValue + random.nextInt(bound);
        }
        return numbers;
    }
}
