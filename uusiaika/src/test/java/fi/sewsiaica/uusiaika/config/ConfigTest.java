/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.config;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iah1016
 */
public class ConfigTest {

    private Config config;
    private final String[] defaultNames = {"Jaakko P", "Harri H", "Mikko M",
            "Teemu P", "Ilona R", "Taina E", "Marika M", "Robert F",
            "Cecilia C", "Oleg M"};
    private final String[] defaultProfs = {"Kauppias", "Leipuri", "Opettaja",
            "Postinjakaja", "Lääkäri", "Radiojuontaja", "Poliisi",
            "Bussikuski", "Putkimies", "Poliitikko", "Tutkija",
            "Apteekkari", "AD", "Toimitusjohtaja"};

    public ConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        config = new Config();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void loadIntValuesCurrentlyReturnsEmptyMapIfNonemptyConfigID() {
        config.setConfigID("foo");
        Map<String, Integer> result = config.loadIntValues();
        assertEquals(true, result.isEmpty());
    }
    
    @Test
    public void loadVilNamesCurrentlyReturnsEmptyListIfNonemptyConfigID() {
        config.setConfigID("foo");
        List<String> result = config.loadVilNames();
        assertEquals(true, result.isEmpty());
    }
    
    @Test
    public void loadProfessionsCurrentlyReturnsEmptyListIfNonemptyConfigID() {
        config.setConfigID("foo");
        List<String> result = config.loadProfessions();
        assertEquals(true, result.isEmpty());
    }
    
    @Test
    public void defaultIntValuesHasCorrectNumberOfValuesAfterCreation() {
        assertEquals(36, config.loadIntValues().size());
    }

    @Test
    public void defaultTempleDeathCultCharismaReqIs255() {
        int result = config.loadIntValues().get("templeDeathCultCharismaReq");
        assertEquals(255, result);
    }

    @Test
    public void defaultVilNamesAreCorrect() {
        boolean result = true;
        List<String> vilNames = config.loadVilNames();
        for (int i = 0; i < defaultNames.length; i++) {
            if (vilNames.get(i) != defaultNames[i]) {
                result = false;
            }
        }
        assertEquals(true, result);
    }
    
    @Test
    public void defaultProfessionsAreCorrect() {
        boolean result = true;
        List<String> profs = config.loadProfessions();
        for (int i = 0; i < defaultProfs.length; i++) {
            if (profs.get(i) != defaultProfs[i]) {
                result = false;
            }
        }
        assertEquals(true, result);
    }
}
