/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.Player;

/**
 *
 * @author iah1016
 */
public class TrainingCentre {

    private int defaultTrainingCharismaIncr;
    private int defaultTrainingArgSkillsIncr;

    public TrainingCentre(int defaultTrainingCharismaIncr,
            int defaultTrainingArgSkillsIncr) {
        this.defaultTrainingCharismaIncr = defaultTrainingCharismaIncr;
        this.defaultTrainingArgSkillsIncr = defaultTrainingArgSkillsIncr;
    }

    public void applyForCharismaCourse(Player player) {
        int playerCharisma = player.getCharisma();

        player.setCharisma(playerCharisma + defaultTrainingCharismaIncr);
    }

    public void applyForDebateCourse(Player player) {
        int playerArgSkills = player.getArgSkills();

        player.setArgSkills(playerArgSkills + defaultTrainingArgSkillsIncr);
    }
}
