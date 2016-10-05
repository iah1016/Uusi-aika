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
    private Persuasion persuasion;
    private Sermon sermon;
    private Accusation accusation;
    private TurnLogic turnLogic;
    private Temple temple;
    private TrainingCentre trainingCentre;

    /**
     *
     * @param random
     * @param configIntValues
     * @param villagers
     * @param player
     * @param sect
     */
    public ActiveGame(Random random, Map<String, Integer> configIntValues,
            List<Villager> villagers, Player player, Sect sect) {
        this.configIntValues = configIntValues;
        createLogicModules(random);
        this.villagers = villagers;
        this.player = player;
        this.sect = sect;
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

    // Getters
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

}
