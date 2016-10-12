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
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.logic.activegame.*;
import fi.sewsiaica.uusiaika.logic.activegamechanger.ActiveGameChanger;
import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.domain.*;
import fi.sewsiaica.uusiaika.logic.activegame.conversion.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

/**
 * The core logic of the game. It controls the other logic modules through an
 * ActiveGame object created by the ActiveGameChanger object.
 *
 * @author iah1016
 */
public class GameLogic {

    private final ActiveGameChanger activeGameChanger;
    private final File[] configFiles;
    private ActiveGame activeGame;
    private Player player;
    private Sect sect;
    private List<Villager> villagers;
    private Conversion persuasion;
    private Conversion sermon;
    private Conversion accusation;
    private TurnLogic turnLogic;
    private Temple temple;
    private TrainingCentre trainingCentre;

    /**
     * The ActiveGameChanger object is created by the constructor.
     *
     * @param random Random is created by the Main class.
     * @param config Config is created by the Main class.
     */
    public GameLogic(Random random, Config config) {
        activeGameChanger = new ActiveGameChanger(random, config);
        configFiles = new File[3];
    }

    /**
     * This method creates a new game. It uses the Config files 0-2: [0] the
     * file containing Config variable values, [1] The file containing villager
     * names, [2] The file containing professions.
     *
     * @param playerAndSectNames The names are given by the user.
     * @return Returns false if at least one of the files is invalid.
     * @throws java.io.FileNotFoundException Throws the FileNotFoundException.
     */
    public boolean newGame(String[] playerAndSectNames)
            throws FileNotFoundException {
        if (!activeGameChanger.updateConfigValues(configFiles[0],
                configFiles[1], configFiles[2])) {
            return false;
        }
        activeGame = activeGameChanger.createNewActiveGame(playerAndSectNames);
        getModulesFromActiveGame();
        return true;
    }

    /**
     * The LoadGame feature is not yet implemented.
     *
     * @param saveName The values will be read from a text file.
     * @return Returns false if the file is not found.
     */
//    public boolean loadGame(String saveName) throws FileNotFoundException {
//            activeGame = activeGameChanger.loadActiveGame(saveName);
//            getModulesFromActiveGame();
//            return true;
//    }
//
    /**
     * Player attempts to convert a villager.
     *
     * @param villager The Player has chosen the target villager beforehand.
     * @param option (a) Persuasion, (b) Sermon, (c) Accusation.
     * @return If the conversion is allowed (ie. the maximum amount of attempts
     * has not been reached) and it is successful, then the method returns true.
     */
    public boolean conversion(Villager villager, char option) {
        if (option == 'a' && persuasion.checkIfAllowedToProceed(villager)) {
            return persuasion.convert(player, villager, sect);
        }
        if (option == 'b' && sermon.checkIfAllowedToProceed(villager)) {
            return sermon.convert(player, villager, sect);
        }
        if (option == 'c' && accusation.checkIfAllowedToProceed(villager)) {
            return accusation.convert(player, villager, sect);
        }
        return false;
    }

    /**
     * The actions that are set in the sect's temple. Options (b) and (c) will
     * end the game, if the conditions are met.
     *
     * @param option (a) Preach, (b) Offer soda (c) Buy a one-way ticket.
     * @return Case (a) is returns true, unless the congregation size is zero,
     * (b) requires a high playerCharisma and (c) a high balance.
     */
    public boolean templeActions(char option) {
        switch (option) {
            case 'a':
                return temple.preach(player, sect);
            case 'b':
                return temple.offerSodaToAllMembers(player);
            case 'c':
                return temple.buyTicketToParadiseIsland(player, sect);
            default:
                return false;
        }
    }

    /**
     * These are the actions that are set in the training centre.
     *
     * @param option (a) apply for a charisma course (increases playerCharisma),
     * (b) apply for a debate course (increases playerArgSkills).
     * @return Both options (a) and (b) always return true.
     */
    public boolean trainingCentreActions(char option) {
        switch (option) {
            case 'a':
                return trainingCentre.applyForCharismaCourse(player);
            case 'b':
                return trainingCentre.applyForDebateCourse(player);
            default:
                return false;
        }
    }

    /**
     * This method executes the TurnLogic's nextTurn-method.
     *
     * @return False equals that the game is finished.
     */
    public boolean endTurn() {
        return turnLogic.nextTurn(player, sect);
    }

    private void getModulesFromActiveGame() {
        player = activeGame.getPlayer();
        sect = activeGame.getSect();
        villagers = activeGame.getVillagers();
        persuasion = activeGame.getPersuasion();
        sermon = activeGame.getSermon();
        accusation = activeGame.getAccusation();
        turnLogic = activeGame.getTurnLogic();
        temple = activeGame.getTemple();
        trainingCentre = activeGame.getTrainingCentre();
    }

    public ActiveGame getActiveGame() {
        return activeGame;
    }

    /**
     * The configuration files are: [0] the file containing Config variable
     * values, [1] The file containing villager names, [2] The file containing
     * professions, [3] .
     *
     * @return Returns the array of the configuration files.
     */
    public File[] getConfigFiles() {
        return configFiles;
    }
}
