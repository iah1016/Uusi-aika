/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.toolsfortests;

import fi.sewsiaica.uusiaika.config.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author iah1016
 */
public class MockConfig extends Config {

    private Map<String, Integer> intValues;
    private List<String> vilNames;
    private List<String> professions;
    
    public MockConfig() {
        super();
    }
    
    @Override
    public List<String> getVilNames() {
        return this.vilNames;
    }
    
    @Override
    public List<String> getProfessions() {
        return this.professions;
    }
    
    public void setIntValues(Map<String, Integer> intValues) {
        this.intValues = intValues;
    }

    public void setVilNames(List<String> vilNames) {
        this.vilNames = vilNames;
    }

    public void setProfessions(List<String> professions) {
        this.professions = professions;
    }
}
