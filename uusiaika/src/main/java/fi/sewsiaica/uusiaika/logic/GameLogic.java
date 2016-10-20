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
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The core logic of the game. It controls the other logic modules through an
 * ActiveGame object created by the ActiveGameChanger object. The actions for an
 * active game have now been moved to the GameLogicActionsForActiveGame object.
 *
 * @author iah1016
 */
public class GameLogic {

    private final ActiveGameChanger activeGameChanger;
    private final GameLogicActionsForActiveGame gameLogicActionsForActiveGame;
    private final LanguageHandler languageHandler;
    private final HallOfFameHandler hallOfFameHandler;
    private final File[] configFiles;
    private ActiveGame activeGame;

    /**
     * The ActiveGameChanger, GameLogicActionsForActiveGame, and LanguageHandler
     * objects are created by the constructor. The file array of the configFiles
     * is also created.
     *
     * @param random Random is created by the Main class.
     * @param config Config is created by the Main class.
     */
    public GameLogic(Random random, Config config) {
        activeGameChanger = new ActiveGameChanger(random, config);
        gameLogicActionsForActiveGame = new GameLogicActionsForActiveGame();
        languageHandler = new LanguageHandler();
        hallOfFameHandler = new HallOfFameHandler(new File("hall_of_fame.txt"));
        configFiles = new File[3];
    }

    /**
     * An active game is created and set to GameLogicActionsForActiveGame. The
     * used Config files 0-2: [0] the Config variable values, [1] the villager
     * names, [2] the professions.
     *
     * @param playerAndSectNames The names are given by the user.
     * @return Returns false if at least one of the files is invalid.
     * @throws Exception Throws the generic exception.
     */
    public boolean newGame(String[] playerAndSectNames) throws Exception {
        if (!activeGameChanger.updateConfigValues(configFiles[0],
                configFiles[1], configFiles[2])) {
            return false;
        }
        activeGame = activeGameChanger.createNewActiveGame(playerAndSectNames);
        gameLogicActionsForActiveGame.updateActiveGame(activeGame);
        return true;
    }

    /**
     * An active game is loaded and set to GameLogicActionsForActiveGame.
     *
     * @param saveFile The values will be read from a text file.
     * @return Returns false if the save file is invalid.
     */
    public boolean loadGame(File saveFile) {
        ActiveGame tempGame = activeGameChanger.loadActiveGame(saveFile);
        if (tempGame == null) {
            return false;
        }
        activeGame = tempGame;
        gameLogicActionsForActiveGame.updateActiveGame(activeGame);
        return true;
    }

    /**
     * Saves the active game by using ActiveGameChanger's method.
     *
     * @param saveFile The name of the save file.
     * @return Returns true if the saving succeeds.
     */
    public boolean saveGame(File saveFile) {
        return activeGameChanger.saveActiveGame(saveFile, activeGame);
    }

    /**
     * This method will change the active language if the given name is found.
     *
     * @param languageName The language name given as a string.
     * @return Returns true if the language change is successful.
     */
    public boolean setActiveLanguage(String languageName) {
        return languageHandler.setActiveLanguage(languageName);
    }

    /**
     * A valid language is added to the languages map and its name to the custom
     * slot of the language list.
     *
     * @param languageFile The file that contains the dialogue in this language.
     * @return Returns true if the file is valid.
     */
    public boolean changeCustomLanguage(File languageFile) {
        return languageHandler.changeCustomLanguage(languageFile);
    }

    public Map<String, String> getActiveLanguage() {
        return languageHandler.getActiveLanguage();
    }

    public List<String> getNamesOfLanguages() {
        return languageHandler.getNamesOfLanguages();
    }

    /**
     * The Conversion actions executed by GameLogicActionsForActiveGame.
     *
     * @param villager The Player has chosen the target villager beforehand.
     * @param option (a) Persuasion, (b) Sermon, (c) Accusation.
     * @return If the conversion is allowed (ie. the maximum amount of attempts
     * has not been reached) and it is successful, then the method returns true.
     */
    public boolean conversion(Villager villager, char option) {
        return gameLogicActionsForActiveGame.conversion(villager, option);
    }

    /**
     * The Temple actions executed by GameLogicActionsForActiveGame.
     *
     * @param option (a) Preach, (b) Offer soda (c) Buy a one-way ticket.
     * @return Case (a) is returns true, unless the congregation size is zero,
     * (b) requires a high playerCharisma and (c) a high balance.
     */
    public boolean templeActions(char option) {
        return gameLogicActionsForActiveGame.templeActions(option);
    }

    /**
     * The TrainingCentre actions executed by GameLogicActionsForActiveGame.
     *
     * @param option (a) apply for a charisma course (increases playerCharisma),
     * (b) apply for a debate course (increases playerArgSkills).
     * @return Both options (a) and (b) always return true.
     */
    public boolean trainingCentreActions(char option) {
        return gameLogicActionsForActiveGame.trainingCentreActions(option);
    }

    /**
     * TurnLogic's nextTurn-method executed by GameLogicActionsForActiveGame.
     *
     * @return False equals that the game is finished.
     */
    public boolean endTurn() {
        return gameLogicActionsForActiveGame.endTurn();
    }

    /**
     * Ends the active game with the given condition. High Score also checked.
     *
     * @param endingCondition The given ending condition.
     */
    public void endGame(int endingCondition) {
        int score = activeGame.endThisActiveGame(endingCondition);
        hallOfFameHandler.updateHallOfFame(score, activeGame);
    }

    public List<String> getHallOfFameList() {
        return hallOfFameHandler.getHallOfFameList();
    }

    public File[] getConfigFiles() {
        return configFiles;
    }

    public ActiveGame getActiveGame() {
        return activeGame;
    }
}
