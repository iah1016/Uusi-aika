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

import fi.sewsiaica.uusiaika.logic.activegame.conversion.*;
import fi.sewsiaica.uusiaika.domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * ActiveGame is the game that is currently played.
 *
 * @author iah1016
 */
public class ActiveGame {

    private final Map<String, Integer> configIntValues;
    private final List<Villager> villagers;
    private final Player player;
    private final Sect sect;
    private final ScoreHandler scoreHandler;
    private Persuasion persuasion;
    private Sermon sermon;
    private Accusation accusation;
    private TurnLogic turnLogic;
    private Temple temple;
    private TrainingCentre trainingCentre;
    private List<Villager> targetVillagers;
    private int gameEndingCondition;

    /**
     * All the objects given as parameters will be permanently attached to this
     * ActiveGame object.
     *
     * @param random Random is created by the Main class.
     * @param configIntValues The variable values for all the logic modules
     * attached to this ActiveGame.
     * @param villagers The List of villagers.
     * @param player The player of the active game.
     * @param sect The player's sect.
     */
    public ActiveGame(Random random, Map<String, Integer> configIntValues,
            List<Villager> villagers, Player player, Sect sect) {
        this.configIntValues = configIntValues;
        this.villagers = villagers;
        this.player = player;
        this.sect = sect;
        this.scoreHandler = new ScoreHandler();
        this.targetVillagers = new ArrayList<>();
        this.gameEndingCondition = 0;
        createLogicModules(random);
        setMembersToCongregation();
    }

    private void createLogicModules(Random random) {
        this.turnLogic = new TurnLogic(configIntValues);
        this.persuasion = new Persuasion(random, configIntValues,
                configIntValues.get("convMaxNumberOfPersuasions"),
                configIntValues.get("convPersPlayerBound"),
                configIntValues.get("convPersVilBound"));
        this.sermon = new Sermon(random, configIntValues,
                configIntValues.get("convMaxNumberOfSermons"),
                configIntValues.get("convSermPlayerBound"),
                configIntValues.get("convSermVilBound"));
        this.accusation = new Accusation(random, configIntValues,
                configIntValues.get("convMaxNumberOfAccusations"),
                configIntValues.get("convAccuPlayerBound"),
                configIntValues.get("convAccuVilBound"));
        this.temple = new Temple(configIntValues);
        this.trainingCentre = new TrainingCentre(configIntValues);
    }

    private void setMembersToCongregation() {
        for (Villager villager : villagers) {
            if (villager.isInSect()) {
                sect.getCongregation().add(villager);
            }
        }
    }

    // Getters
    public int getNumberOfTurns() {
        return turnLogic.getNumberOfTurns();
    }

    public TurnLogic getTurnLogic() {
        return turnLogic;
    }

    public Conversion getPersuasion() {
        return persuasion;
    }

    public Conversion getSermon() {
        return sermon;
    }

    public Conversion getAccusation() {
        return accusation;
    }

    public Temple getTemple() {
        return temple;
    }

    public TrainingCentre getTrainingCentre() {
        return trainingCentre;
    }

    public Player getPlayer() {
        return player;
    }

    public Sect getSect() {
        return sect;
    }

    public List<Villager> getVillagers() {
        return villagers;
    }

    public List<Villager> getTargetVillagers() {
        return targetVillagers;
    }

    public void setTargetVillagers(List<Villager> targetVillagers) {
        this.targetVillagers = targetVillagers;
    }

    @Override
    public String toString() {
        return "Turn: " + getNumberOfTurns()
                + "  " + player.getName()
                + "  " + sect.getName() + "  Balance: " + sect.getBalance()
                + "  Members: " + sect.getCongregation().size();
    }

    /**
     * The conditions are: [0] the game has not ended, [1] the maximum amount of
     * turns has been reached, [2] the death cult ending, [3] the paradise
     * island ending.
     *
     * @return Returns the ending condition.
     */
    public int getGameEndingCondition() {
        return gameEndingCondition;
    }

    public int getFinalScore() {
        return scoreHandler.getScore();
    }

    /**
     * The active game ends with the given condition. The method calls the
     * private method calculateEndScore(), which updates the end score with the
     * aid of the ScoreHandler object.
     *
     * @param gameEndingCondition [1] the maximum amount of turns has been
     * reached, [2] the death cult ending, [3] the paradise island ending.
     * @return Returns the final score.
     */
    public int endThisActiveGame(int gameEndingCondition) {
        this.gameEndingCondition = gameEndingCondition;
        return calculateEndScore();
    }

    private int calculateEndScore() {
        int[] elements = new int[8];
        elements[0] = this.gameEndingCondition;
        elements[1] = player.getCharisma();
        elements[2] = sect.getBalance();
        elements[3] = sect.getCongregation().size();
        elements[4] = getNumberOfTurns();
        elements[5] = configIntValues.get("turnMaxNumberOfTurns");
        elements[6] = configIntValues.get("templeDeathCultCharismaReq");
        elements[7] = configIntValues.get("templeDivineRightMoneyReq");
        return scoreHandler.calculateScore(elements);
    }

    public Map<String, Integer> getConfigIntValues() {
        return configIntValues;
    }
}
