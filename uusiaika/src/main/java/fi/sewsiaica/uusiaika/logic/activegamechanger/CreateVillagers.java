/*
 * Copyright (C) 2016 Ilja Häkkinen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fi.sewsiaica.uusiaika.logic.activegamechanger;

import fi.sewsiaica.uusiaika.domain.Villager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Your caravan has finally reached land, ahoy, and there seems to be a lovely
 * village nearby. And on the second day, someone created a set of villagers to
 * ruin it.
 *
 * @author iah1016
 */
public class CreateVillagers {

    private final Random random;
    private Map<String, Integer> intValues;
    private List<String> vilNames;
    private List<String> professions;
    private int numberOfVillagers;

    /**
     * The constructor gets the value for numberOfVillagers from intValues.
     *
     * @param random Random is needed to determine the villagers' attributes.
     */
    public CreateVillagers(Random random) {
        this.random = random;
    }

    /**
     * Doing the Lord's work. The shepherd needs the sheep. First the names are
     * picked from vilNames, second all the villagers' attributes are calculated
     * in makeAttribLists, and third professions from professions-list. The
     * ArrayList is formed in addVillagersToVList (private method).
     *
     * @param intValues Includes all the variable values of the game.
     * @param vilNames Includes a list of names.
     * @param professions Includes a list of professions.
     * @return Returns an ArrayList of Villagers.
     */
    public List<Villager> populateVillage(Map<String, Integer> intValues,
            List<String> vilNames, List<String> professions) {
        this.numberOfVillagers = intValues.get("vilPopulation");
        String[] namesArray = pickStrings(numberOfVillagers, vilNames);
        List<int[]> atlists = makeAttribLists(numberOfVillagers, intValues);
        String[] profsArray = pickStrings(numberOfVillagers, professions);

        return addVillagersToVList(numberOfVillagers, namesArray,
                atlists.get(0), atlists.get(1), atlists.get(2), atlists.get(3),
                profsArray);
    }

    /**
     * Prepare the salvaged villagers for the postdiluvian ventures.
     *
     * @param names The names list of the villagers.
     * @param professions The professions list of the villagers.
     * @param inSect The boolean array of the villagers' sect membership.
     * @param attributes The villagers' attributes in a list of arrays.
     * @return Returns an ArrayList of Villagers.
     */
    public List<Villager> populateVillageWithLoadedVillagers(
            List<String> names, List<String> professions, boolean[] inSect,
            List<int[]> attributes) {
        List<Villager> villagers = new ArrayList<>();
        this.numberOfVillagers = inSect.length;

        for (int i = 0; i < numberOfVillagers; i++) {
            villagers.add(new Villager(names.get(i), inSect[i],
                    attributes.get(0)[i], attributes.get(1)[i],
                    attributes.get(2)[i], attributes.get(3)[i],
                    professions.get(i)));
        }

        return villagers;
    }

    /**
     * Creates an ArrayList of integer arrays, each containing each villager's
     * value for each attribute.
     *
     * @param quantity The number of villagers.
     * @return returns an ArrayList of integer arrays to populateVillage.
     */
    private List<int[]> makeAttribLists(int quantity,
            Map<String, Integer> intValues) {
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

    private List<Villager> addVillagersToVList(int quantity, String[] names,
            int[] scept, int[] selfEs, int[] selfAwValues, int[] argSkills,
            String[] profs) {

        List<Villager> vlist = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            vlist.add(new Villager(names[i], false, scept[i], selfEs[i],
                    selfAwValues[i], argSkills[i], profs[i]));
        }
        return vlist;
    }

    /**
     * This method will pick the same amount of strings from a list as there are
     * villagers. If there are more villagers than there are names in the list,
     * the picking will continue from the start, once the pointer has reached
     * the end.
     *
     * @param quantity The number of villagers.
     * @param selection The list of String.
     * @return Returns a String array.
     */
    protected String[] pickStrings(int quantity, List<String> selection) {
        if (quantity < 1) {
            return new String[0];
        }
        String[] strings = new String[quantity];

        for (int i = 0; i < quantity; i++) {
            strings[i] = (selection.get(i % selection.size()));
        }

        return strings;
    }

    /**
     * An attribute value is calculated here.
     *
     * @param quantity The number of villagers.
     * @param baseValue The base value for an attribute, ie. the can't be lower.
     * @param bound The upper bound for Random (exclusive).
     * @return Returns the calculated value for the attribute.
     */
    protected int[] pickRandomNumbers(int quantity, int baseValue, int bound) {
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
