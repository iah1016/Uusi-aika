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
package fi.sewsiaica.uusiaika.dialogue;

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
public class DialogueTest {

    private Dialogue dialogue;

    public DialogueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dialogue = new Dialogue();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void defaultLanguagesAreLoadedCorrectlyAfterInit() {
        List<String> names = dialogue.getNamesOfLanguages();
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            sb.append(name);
        }
        assertEquals("Englishsuomi", sb.toString());
    }

    @Test
    public void defaultLanguagesCanBeLoadedWithGetLangMap() {
        Map<String, String> language = dialogue.getLangMap("English");
        assertEquals("English", language.get("language"));
        language = dialogue.getLangMap("suomi");
        assertEquals("suomi", language.get("language"));
    }

    @Test
    public void loadNewLanguageLoadsNewLanguage() {
        String fileName = "src/test/filesfortests/language_vdl.txt";
        boolean loadingOK = dialogue.loadNewLanguage(new File(fileName));
        assertEquals(true, loadingOK);
        assertEquals("Vandalic", dialogue.getNamesOfLanguages().get(2));
        Map<String, String> language = dialogue.getLangMap("Vandalic");
        assertEquals("Scapia matzia ia drincan!", language.get("openingMenu"));
    }

    @Test
    public void languageNotAddedIfLanguageFileNotFound() {
        String fileName = "/foofiles/language_foolish.txt";
        boolean loadingOK = dialogue.loadNewLanguage(new File(fileName));
        assertEquals(false, loadingOK);
        assertEquals(2, dialogue.getNamesOfLanguages().size());
    }

    @Test
    public void languageNotAddedIfLanguageFileIsInvalid() {
        String fileName = "src/test/filesfortests/test_professions.txt";
        boolean loadingOK = dialogue.loadNewLanguage(new File(fileName));
        assertEquals(false, loadingOK);
        assertEquals(2, dialogue.getNamesOfLanguages().size());
    }

    @Test
    public void getLangMapReturnsNullIfLanguageNotFound() {
        Map<String, String> language = dialogue.getLangMap("huuhaa");
        assertNull(language);
    }

    @Test
    public void languageLoaderReturnsFalseWithNullPointerException() {
        boolean result = dialogue.languageLoader(null);
        assertEquals(false, result);
    }

    @Test
    public void addingNewCustomLanguageReplacesOldOne() {
        int amountOfLanguages = dialogue.getNamesOfLanguages().size();
        
        String fileName = "src/test/filesfortests/language_vdl.txt";
        dialogue.loadNewLanguage(new File(fileName));
        amountOfLanguages++;
        
        List<String> languages = dialogue.getNamesOfLanguages();
        StringBuilder sb = new StringBuilder();
        for (String language : languages) {
            sb.append(language);
        }
        
        assertEquals(amountOfLanguages, languages.size());
        assertEquals("EnglishsuomiVandalic", sb.toString());
        
        fileName = "src/test/filesfortests/language_opr.txt";
        dialogue.loadNewLanguage(new File(fileName));
        
        languages = dialogue.getNamesOfLanguages();
        sb = new StringBuilder();
        for (String language : languages) {
            sb.append(language);
        }
        
        assertEquals(amountOfLanguages, languages.size());
        assertEquals("EnglishsuomiOld Prussian", sb.toString());
    }
}
