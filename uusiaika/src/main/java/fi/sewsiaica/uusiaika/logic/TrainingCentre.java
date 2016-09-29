/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Player;
import java.util.Map;

/**
 *
 * @author iah1016
 */
public class TrainingCentre {

    private Map<String, Integer> intValues;
    private int trainingCharismaIncr;
    private int trainingArgSkillsIncr;

    public TrainingCentre(Map<String, Integer> intValues) {
        this.intValues = intValues;
        this.trainingCharismaIncr = intValues.get("trainingCharismaIncr");
        this.trainingArgSkillsIncr = intValues.get("trainingArgSkillsIncr");
    }

    public boolean applyForCharismaCourse(Player player) {
        int playerCharisma = player.getCharisma();

        player.setCharisma(playerCharisma + trainingCharismaIncr);
        return true;
    }

    public boolean applyForDebateCourse(Player player) {
        int playerArgSkills = player.getArgSkills();

        player.setArgSkills(playerArgSkills + trainingArgSkillsIncr);
        return true;
    }
}
