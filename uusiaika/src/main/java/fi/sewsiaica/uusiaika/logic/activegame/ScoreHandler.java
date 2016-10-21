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

/**
 * In this version the score is only updated when the active game ends.
 *
 * @author iah1016
 */
public class ScoreHandler {

    private int score;

    /**
     * The constructor sets the initial value of zero to the object variable
     * score.
     */
    public ScoreHandler() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    /**
     * The score is calculated at the end of the game.
     *
     * @param elements The integer array that contains the elements needed to
     * calculate the final score.
     * @return Returns the calculated score or 0 if the array is of wrong size
     * or the condition is other than a valid ending condition.
     */
    public int calculateScore(int[] elements) {
        if (elements.length != 8) {
            score = 0;
            return score;
        }
        int condition = elements[0];
        switch (condition) {
            case 1:
                return unsuccessCondition(elements);
            case 2:
                return deathCultCondition(elements);
            case 3:
                return paradiseIslandCondition(elements);
            default:
                score = 0;
                return score;
        }
    }

    private int unsuccessCondition(int[] elements) {
        int sectBalance = elements[2];
        int congregationSize = elements[3];

        if (sectBalance <= 0) {
            score = congregationSize + 1;
        } else {
            score = sectBalance / 1000 + congregationSize * 20 + 1;
        }
        return score;
    }

    private int basePointsForWinningCondition(int balance, int members) {
        return balance / 500 + members * 50;
    }

    private int turnsLeftBonus(int played, int max) {
        return (max - played) * 100;
    }

    private int deathCultCondition(int[] elements) {
        int congregationSize = elements[3];
        int playerCharisma = elements[1];
        int charismaThreshold = elements[6];
        
        int base = basePointsForWinningCondition(elements[2], elements[3]);
        int turnsLeftBonus = turnsLeftBonus(elements[4], elements[5]);
        int deathCultBonus = 20 * congregationSize
                * (playerCharisma - charismaThreshold);

        score = base + turnsLeftBonus + deathCultBonus;
        return score;
    }

    private int paradiseIslandCondition(int[] elements) {
        int playerCharisma = elements[1];
        int sectBalance = elements[2];
        int balanceThreshold = elements[7];
        
        int base = basePointsForWinningCondition(elements[2], elements[3]);
        int turnsLeftBonus = turnsLeftBonus(elements[4], elements[5]);
        int paradiseIslandBonus = (sectBalance - balanceThreshold) / 2;
        
        score = base + playerCharisma + turnsLeftBonus + paradiseIslandBonus;
        return score;
    }
}
