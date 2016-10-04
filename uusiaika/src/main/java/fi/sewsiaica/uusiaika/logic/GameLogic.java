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

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.logic.conversion.*;
import fi.sewsiaica.uusiaika.domain.*;
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
    }

    /**
     * This method creates a new game.
     *
     * @param playerAndSectNames The names are given by the user.
     * @param configID The name of the configuration file.
     */
    public void newGame(String[] playerAndSectNames, String configID) {
        activeGame = activeGameChanger.createNewActiveGame(playerAndSectNames,
                configID);
        getModulesFromActiveGame();
    }

    /**
     * The LoadGame feature is not yet implemented.
     *
     * @param saveName The values will be read from a text file.
     * @return Returns false if the file is not found.
     */
//    public boolean loadGame(String saveName) {
//        try {
//            activeGame = activeGameChanger.loadActiveGame(saveName);
//            getModulesFromActiveGame();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

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
     * end the game (yet to be implemented), if the conditions are met.
     *
     * @param option (a) Preach, (b) Offer soda (c) Buy a one-way ticket.
     * @return The option a is currently always returns true. The Option (b)
     * requires a high playerCharisma and option (c) a high balance.
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

    public Player getPlayer() {
        return player;
    }

    public Sect getSect() {
        return sect;
    }

    public List<Villager> getVillagers() {
        return villagers;
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

    public int getNumberOfTurns() {
        return turnLogic.getNumberOfTurns();
    }
}
