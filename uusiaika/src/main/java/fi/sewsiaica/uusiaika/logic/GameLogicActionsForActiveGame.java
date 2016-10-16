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

import fi.sewsiaica.uusiaika.domain.Player;
import fi.sewsiaica.uusiaika.domain.Sect;
import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import fi.sewsiaica.uusiaika.logic.activegame.Temple;
import fi.sewsiaica.uusiaika.logic.activegame.TrainingCentre;
import fi.sewsiaica.uusiaika.logic.activegame.conversion.Conversion;
import java.util.List;

/**
 * GameLogic's actions for an ActiveGame object are: conversion, templeActions,
 * trainingCentreActions, and endTurn.
 *
 * @author iah1016
 */
public class GameLogicActionsForActiveGame {

    private ActiveGame activeGame;
    private Player player;
    private Sect sect;
    private List<Villager> villagers;
    private Conversion persuasion;
    private Conversion sermon;
    private Conversion accusation;
    private Temple temple;
    private TrainingCentre trainingCentre;

    /**
     * This method updated the ActiveGame object.
     *
     * @param activeGame The game that is being played.
     */
    protected void updateActiveGame(ActiveGame activeGame) {
        this.activeGame = activeGame;
        getModulesFromActiveGame();
    }

    /**
     * Player attempts to convert a villager.
     *
     * @param villager The Player has chosen the target villager beforehand.
     * @param option (a) Persuasion, (b) Sermon, (c) Accusation.
     * @return If the conversion is allowed (ie. the maximum amount of attempts
     * has not been reached) and it is successful, then the method returns true.
     */
    protected boolean conversion(Villager villager, char option) {
        if (activeGame == null) {
            return false;
        }
        return conversionNullChecked(villager, option);
    }

    private boolean conversionNullChecked(Villager villager, char option) {
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
    protected boolean templeActions(char option) {
        if (activeGame == null) {
            return false;
        }
        return templeActionsNullChecked(option);
    }

    private boolean templeActionsNullChecked(char option) {
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
    protected boolean trainingCentreActions(char option) {
        if (activeGame == null) {
            return false;
        }
        return trainingCentreActionsNullChecked(option);
    }

    private boolean trainingCentreActionsNullChecked(char option) {
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
    protected boolean endTurn() {
        if (activeGame == null) {
            return false;
        }
        return activeGame.getTurnLogic().nextTurn(player, sect, villagers);
    }

    private void getModulesFromActiveGame() {
        player = activeGame.getPlayer();
        sect = activeGame.getSect();
        villagers = activeGame.getVillagers();
        persuasion = activeGame.getPersuasion();
        sermon = activeGame.getSermon();
        accusation = activeGame.getAccusation();
        temple = activeGame.getTemple();
        trainingCentre = activeGame.getTrainingCentre();
    }
}
