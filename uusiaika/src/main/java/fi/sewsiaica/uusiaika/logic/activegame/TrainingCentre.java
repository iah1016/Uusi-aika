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
package fi.sewsiaica.uusiaika.logic.activegame;

import fi.sewsiaica.uusiaika.domain.Player;
import java.util.Map;

/**
 * TrainingCentre is the place to be when you want to increase your charisma or
 * argumentation skills.
 *
 * @author iah1016
 */
public class TrainingCentre {

    private final Map<String, Integer> intValues;
    private final int trainingCharismaIncr;
    private final int trainingArgSkillsIncr;

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
        int playerArgSkills = player.getArgumentationSkills();

        player.setArgumentationSkills(playerArgSkills + trainingArgSkillsIncr);
        return true;
    }
}
