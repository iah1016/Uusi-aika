/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.config;

import java.io.FileNotFoundException;
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
    public void loadIntValuesThrowsFileNotFoundExceptionIfFileNotFound() {
        boolean epicFail = false;
        try {
            config.loadIntValues("foo");
        } catch (FileNotFoundException e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }
    
    @Test
    public void loadVilNamesThrowsFileNotFoundExceptionIfFileNotFound() {
        boolean epicFail = false;
        try {
            config.loadVilNames("foo");
        } catch (FileNotFoundException e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }
    
    @Test
    public void loadProfessionsThrowsFileNotFoundExceptionIfFileNotFound() {
        boolean epicFail = false;
        try {
            config.loadProfessions("foo");
        } catch (FileNotFoundException e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }
    
    @Test
    public void loadIntValuesReturnsDefaultIntValuesMapIfIDEmpty()
            throws FileNotFoundException {
        String id = "";
        Map<String, Integer> result = config.loadIntValues(id);
        int value = result.get("templeDeathCultCharismaReq");
        assertEquals(255, value);
    }
    
    @Test
    public void loadIntValuesReturnsValuesFromFileCorrectly()
            throws FileNotFoundException {
        String id = "src/main/resources/default_values.txt";
        Map<String, Integer> result = config.loadIntValues(id);
        int value = result.get("templeDeathCultCharismaReq");
        assertEquals(666, value);
    }
    
    @Test
    public void loadVilNamesReturnsDefaultNamesIfIDEmpty()
            throws FileNotFoundException {
        String id = "";
        List<String> result = config.loadVilNames(id);
        assertEquals("Jaakko P", result.get(0));
    }
    
    @Test
    public void loadVilNamesReturnsNamesFromFileCorrectly()
            throws FileNotFoundException {
        String id = "src/main/resources/default_villagers.txt";
        List<String> result = config.loadVilNames(id);
        assertEquals("Heikki K", result.get(0));
    }
    
    @Test
    public void loadProfessionsReturnsDefaultProfsIfIDEmpty()
            throws FileNotFoundException {
        String id = "";
        List<String> result = config.loadProfessions(id);
        assertEquals("Leipuri", result.get(1));
    }
    
    @Test
    public void loadProfessionsReturnsProfsFromFileCorrectly()
            throws FileNotFoundException {
        String id = "src/main/resources/default_professions.txt";
        List<String> result = config.loadProfessions(id);
        assertEquals("Maanviljelijä", result.get(1));
    }
    
    @Test
    public void defaultIntValuesHasCorrectNumberOfValuesAfterCreation()
            throws FileNotFoundException {
        assertEquals(36, config.loadIntValues("").size());
    }

    @Test
    public void defaultTempleDeathCultCharismaReqIs255()
            throws FileNotFoundException {
        int result = config.loadIntValues("").get("templeDeathCultCharismaReq");
        assertEquals(255, result);
    }

    @Test
    public void defaultVilNamesAreCorrect() throws FileNotFoundException {
        boolean result = true;
        List<String> vilNames = config.loadVilNames("");
        for (int i = 0; i < defaultNames.length; i++) {
            if (vilNames.get(i) != defaultNames[i]) {
                result = false;
            }
        }
        assertEquals(true, result);
    }

    @Test
    public void defaultProfessionsAreCorrect() throws FileNotFoundException {
        boolean result = true;
        List<String> profs = config.loadProfessions("");
        for (int i = 0; i < defaultProfs.length; i++) {
            if (profs.get(i) != defaultProfs[i]) {
                result = false;
            }
        }
        assertEquals(true, result);
    }
}
