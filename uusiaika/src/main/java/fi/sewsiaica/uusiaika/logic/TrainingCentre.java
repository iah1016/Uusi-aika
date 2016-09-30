/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Player;
import java.util.Map;

/**
 * TrainingCentre is the place to be when you want to increase your charisma or
 * argumentation skills.
 *
 * @author iah1016
 */
public class TrainingCentre {

    private Map<String, Integer> intValues;
    private int trainingCharismaIncr;
    private int trainingArgSkillsIncr;

    /**
     * The constructor set the values of trainingCharismaIncr and
     * trainingArgSkillsIncr to its object variable.
     *
     * @param intValues Includes all the variable values of the game.
     */
    public TrainingCentre(Map<String, Integer> intValues) {
        this.intValues = intValues;
        this.trainingCharismaIncr = intValues.get("trainingCharismaIncr");
        this.trainingArgSkillsIncr = intValues.get("trainingArgSkillsIncr");
    }

    /**
     * Player's charisma is increased.
     *
     * @param player The player whose attribute is increased.
     * @return The method always returns true.
     */
    public boolean applyForCharismaCourse(Player player) {
        int playerCharisma = player.getCharisma();

        player.setCharisma(playerCharisma + trainingCharismaIncr);
        return true;
    }

    /**
     * Player's argumentation skills are increased.
     *
     * @param player The player whose attribute is increased.
     * @return The method always returns true.
     */
    public boolean applyForDebateCourse(Player player) {
        int playerArgSkills = player.getArgSkills();

        player.setArgSkills(playerArgSkills + trainingArgSkillsIncr);
        return true;
    }
}
