/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author iah1016
 */
public class Config {

    private final String[] variableNames;
    private final int[] defaultValues;
    private Map<String, Integer> intValues;
    private List<String> vilNames;
    private List<String> professions;

    public Config() {
        variableNames = createArrayOfVariableNames();
        defaultValues = createArrayOfDefaultValues();
        intValues = new HashMap<>();
        vilNames = new ArrayList<>();
        professions = new ArrayList<>();
        loadIntValues();
        loadVilNames();
        loadProfessions();
    }

    public void loadIntValues() {
        Map<String, Integer> newValues = new HashMap<>();
//        check if a config file exists
//        if (loadFromFile.load("fileName")) {
//        } else {
        for (int i = 0; i < variableNames.length; i++) {
            newValues.put(variableNames[i], defaultValues[i]);
        }
        this.intValues = newValues;
//        }
    }

    public void loadVilNames() {
        List<String> namesForVillagers = new ArrayList<>();
        for (DefaultVilNames villager : DefaultVilNames.values()) {
            namesForVillagers.add(villager.vilName());
        }
        this.vilNames = namesForVillagers;
    }

    public void loadProfessions() {
        List<String> profs = new ArrayList<>();
        for (DefaultProfessions profession : DefaultProfessions.values()) {
            profs.add(profession.profName());
        }
        this.professions = profs;
    }

    private String[] createArrayOfVariableNames() {
        int i = 0;
        String[] array = new String[VariableNames.values().length];
        for (VariableNames variable : VariableNames.values()) {
            array[i] = variable.varName();
            i++;
        }
        return array;
    }

    private int[] createArrayOfDefaultValues() {
        int i = 0;
        int[] array = new int[DefaultValues.values().length];
        for (DefaultValues variable : DefaultValues.values()) {
            array[i] = variable.value();
            i++;
        }
        return array;
    }

    public Map<String, Integer> getIntValues() {
        return intValues;
    }

    public List<String> getVilNames() {
        return vilNames;
    }

    public List<String> getProfessions() {
        return professions;
    }
}
