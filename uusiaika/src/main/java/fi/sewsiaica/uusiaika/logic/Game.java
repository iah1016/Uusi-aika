package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.logic.conversion.*;
import fi.sewsiaica.uusiaika.domain.*;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The core logic of the game. It acquires all the game's variable values from
 * Config. All the other logic parts are permanently attached to this class.
 *
 * @author iah1016
 */
public class Game {

    private final Config config;
    private Map<String, Integer> configIntValues;
    private List<String> vilNamesForCreation;
    private List<String> professionsForCreation;
    private Player player;
    private Sect sect;
    private List<Villager> villagers;
    private CreateVillagers createVillagers;
    private Conversion persuasion;
    private Conversion sermon;
    private Conversion accusation;
    private TurnLogic turnLogic;
    private Temple temple;
    private TrainingCentre trainingCentre;

    /**
     * The constructor pulls all the values from Config and, with them, creates
     * the Logic objects.
     *
     * @param random Random is needed by the CreateVillagers and Conversion-type
     * classes.
     * @param config Config contains all the variable values.
     */
    public Game(Random random, Config config) {
        this.config = config;
        updateGamesConfigValues();
        createLogicModules(random);
        this.villagers = createVillagers.populateVillage();
    }

    private void updateGamesConfigValues() {
        this.configIntValues = config.getIntValues();
        this.vilNamesForCreation = config.getVilNames();
        this.professionsForCreation = config.getProfessions();
    }

    private void createLogicModules(Random random) {
        this.createVillagers = new CreateVillagers(random, configIntValues,
                vilNamesForCreation, professionsForCreation);
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

    /**
     * The method gets the player and sect names in a String array-type
     * parameter, creates new Player and Sect objects, and sets them to its
     * variables.
     *
     * @param playerAndSectNames Player and Sect names in a String array.
     * @return The return value is only true if the array size is exactly 2.
     */
    public boolean createPlayerAndSect(String[] playerAndSectNames) {
        if (playerAndSectNames.length == 2) {
            this.player = new Player(playerAndSectNames[0],
                    configIntValues.get("playerCharisma"),
                    configIntValues.get("playerArgSkills"));
            this.sect = new Sect(playerAndSectNames[1],
                    configIntValues.get("sectBalance"),
                    configIntValues.get("sectExpenses"),
                    configIntValues.get("sectMemberFee"));
            return true;
        }
        return false;
    }

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
     * The actions that are set in the sect's temple. (a) will decrease the
     * scepticism of the members. Options (b) and (c) will end the game (yet to
     * be implemented), if the conditions are met.
     *
     * @param option (a) Preach, (b) Offer soda to the congregation, (c) Buy a
     * one-way ticket to a paradise island Option.
     * @return The option a is currently always returns true. Option (b)
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
     * @return
     */
    public boolean endTurn() {
        return turnLogic.nextTurn(player, sect);
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
