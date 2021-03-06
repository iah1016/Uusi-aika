/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.config;

import java.io.File;
import java.util.List;
import java.util.Map;
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
    public void loadIntValuesThrowsExceptionIfFileNotFound() {
        boolean epicFail = false;
        try {
            config.loadIntValues(new File("foo"));
        } catch (Exception e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void loadVilNamesThrowsExceptionIfFileNotFound() {
        boolean epicFail = false;
        try {
            config.loadVilNames(new File("foo"));
        } catch (Exception e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void loadProfessionsThrowsExceptionIfFileNotFound() {
        boolean epicFail = false;
        try {
            config.loadProfessions(new File("foo"));
        } catch (Exception e) {
            epicFail = true;
        }
        assertEquals(true, epicFail);
    }

    @Test
    public void loadIntValuesReturnsDefaultIntValuesMapIfNullFile()
            throws Exception {
        File file = null;
        Map<String, Integer> result = config.loadIntValues(file);
        int value = result.get("templeDeathCultCharismaReq");
        assertEquals(255, value);
    }

    @Test
    public void loadIntValuesReturnsValuesFromFileCorrectly()
            throws Exception {
        String id = "src/test/filesfortests/test_values.txt";
        Map<String, Integer> result = config.loadIntValues(new File(id));
        int value = result.get("templeDeathCultCharismaReq");
        assertEquals(666, value);
    }

    @Test
    public void loadVilNamesReturnsDefaultNamesIfNullFile()
            throws Exception {
        List<String> result = config.loadVilNames(null);
        assertEquals("Jaakko P", result.get(0));
    }

    @Test
    public void loadVilNamesReturnsNamesFromFileCorrectly()
            throws Exception {
        String id = "src/test/filesfortests/test_villagers.txt";
        List<String> result = config.loadVilNames(new File(id));
        assertEquals("Heikki K", result.get(0));
    }

    @Test
    public void loadProfessionsReturnsDefaultProfsIfNullFile()
            throws Exception {
        List<String> result = config.loadProfessions(null);
        assertEquals("Leipuri", result.get(1));
    }

    @Test
    public void loadProfessionsReturnsProfsFromFileCorrectly()
            throws Exception {
        String id = "src/test/filesfortests/test_professions.txt";
        List<String> result = config.loadProfessions(new File(id));
        assertEquals("Maanviljelijä", result.get(1));
    }

    @Test
    public void defaultIntValuesHasCorrectNumberOfValuesAfterCreation()
            throws Exception {
        assertEquals(36, config.loadIntValues(null).size());
    }

    @Test
    public void defaultTempleDeathCultCharismaReqIs255()
            throws Exception {
        int result = config.loadIntValues(null)
                .get("templeDeathCultCharismaReq");
        assertEquals(255, result);
    }

    @Test
    public void defaultVilNamesAreCorrect() throws Exception {
        boolean result = true;
        List<String> vilNames = config.loadVilNames(null);
        for (int i = 0; i < defaultNames.length; i++) {
            if (vilNames.get(i) != defaultNames[i]) {
                result = false;
            }
        }
        assertEquals(true, result);
    }

    @Test
    public void defaultProfessionsAreCorrect() throws Exception {
        boolean result = true;
        List<String> profs = config.loadProfessions(null);
        for (int i = 0; i < defaultProfs.length; i++) {
            if (profs.get(i) != defaultProfs[i]) {
                result = false;
            }
        }
        assertEquals(true, result);
    }

    @Test
    public void checkValidityOfConfigVariableMapFunctionsAsExpected()
            throws Exception {
        boolean result = config.checkValidityOfConfigVariableMap(null);
        assertEquals(false, result);

        Map<String, Integer> map = config.loadIntValues(null);
        result = config.checkValidityOfConfigVariableMap(map);
        assertEquals(true, result);

        map.remove("convMaxNumberOfSermons");
        result = config.checkValidityOfConfigVariableMap(map);
        assertEquals(false, result);
    }
}
