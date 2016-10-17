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
public class LanguageHandlerTest {

    private LanguageHandler languageHandler;

    public LanguageHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        languageHandler = new LanguageHandler();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void activeLanguageIsEnglishAfterInit() {
        Map<String, String> lang = languageHandler.getActiveLanguage();
        assertEquals("English", lang.get("language"));
    }

    @Test
    public void getNamesOfLanguagesFunctionsAsExcpected() {
        List<String> names = languageHandler.getNamesOfLanguages();
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            sb.append(name);
        }
        assertEquals("Englishsuomi", sb.toString());
    }

    @Test
    public void setActiveLanguageReturnsFalseIfLanguageNotFound() {
        boolean result = languageHandler.setActiveLanguage("foolish");
        assertEquals(false, result);
    }

    @Test
    public void setActiveLanguageFunctionsProperly() {
        boolean result = languageHandler.setActiveLanguage("suomi");
        assertEquals(true, result);
        assertEquals("suomi", languageHandler.getActiveLanguage()
                .get("language"));
        result = languageHandler.setActiveLanguage("English");
        assertEquals(true, result);
        assertEquals("English", languageHandler.getActiveLanguage()
                .get("language"));
    }

    @Test
    public void changeCustomLanguageFunctionsProperly() {
        String name = "src/test/filesfortests/language_opr.txt";
        boolean result = languageHandler.changeCustomLanguage(new File(name));
        assertEquals(true, result);
        name = "src/test/filesfortests/language_vdl.txt";
        result = languageHandler.changeCustomLanguage(new File(name));
        assertEquals(true, result);
        name = "foobar";
        result = languageHandler.changeCustomLanguage(new File(name));
        assertEquals(false, result);
    }
}
