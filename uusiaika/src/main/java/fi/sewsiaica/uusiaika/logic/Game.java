package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.logic.conversion.*;
import fi.sewsiaica.uusiaika.domain.*;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author iah1016
 */
public class Game {

    // Config
    private final Config config;
    private Map<String, Integer> configIntValues;
    private List<String> vilNamesForCreation;
    private List<String> professionsForCreation;
    // Domain
    private Player player;
    private Sect sect;
    private List<Villager> villagers;
    // Logic modules
    private CreateVillagers createVillagers;
    private Conversion persuasion;
    private Conversion sermon;
    private Conversion accusation;
    private TurnLogic turnLogic;
    private Temple temple;
    private TrainingCentre trainingCentre;

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
