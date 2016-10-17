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

import fi.sewsiaica.uusiaika.generaltools.GeneralTools;
import fi.sewsiaica.uusiaika.io.ReadFromInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains all the dialogue text (ie all the text, including the
 * button names) in the game. Each language has its own hash map of the
 * dialogue. These hash maps, in turn, are included in the languages hash map.
 * The language settings are handled by the LanguageHandler class in the logic
 * package, as is the addition of new languages.
 *
 * @author iah1016
 */
public class Dialogue {

    private final GeneralTools generalTools;
    private final ReadFromInputStream readFromInputStream;
    private final Map<String, Map<String, String>> languages;
    private final List<String> namesOfLanguages;
    private final String[] keysForLangMaps;

    /**
     * The constructor creates immutable objects of the following types:
     * GeneralTools, ReadFromInputStream, HashMap (the map of all the
     * languages), String[] (the keys for language maps that are used for the
     * validity checks).
     */
    public Dialogue() {
        this.generalTools = new GeneralTools();
        this.readFromInputStream = new ReadFromInputStream();
        this.languages = new HashMap<>();
        this.namesOfLanguages = new ArrayList<>();
        this.keysForLangMaps = createArrayOfKeysForLangMaps();
        loadDefaultLanguages();
    }

    /**
     * This method will return the language map corresponding to the language
     * name given as a parameter or null if the languages map does not contain
     * the requested language.
     *
     * @param languageName The language name given as a string.
     * @return Returns the language map if found or null if not found.
     */
    public Map<String, String> getLangMap(String languageName) {
        if (languages.containsKey(languageName)) {
            return languages.get(languageName);
        }
        return null;
    }

    /**
     * The names of each language given as a string. The language name is needed
     * as a key to retrieve the language map from the larger map that contains
     * all the languages.
     *
     * @return Returns the language names in a String-type List.
     */
    public List<String> getNamesOfLanguages() {
        return namesOfLanguages;
    }

    /**
     * This method will add a (valid) language to the hash map of languages.
     *
     * @param language The name of the language file as a string.
     * @return Returns true if the file is valid.
     */
    public boolean loadNewLanguage(String language) {
        try {
            InputStream inputStream = new FileInputStream(language);
            return languageLoader(inputStream);
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    /**
     * This protected method uses the ReadFromInputStream object to get the
     * String lines from the language file. Not private due to testing.
     *
     * @param inputStream The InputStream given.
     * @return Returns true if loading succeeds, false if there is either an
     * IOException or a NullPointerException.
     */
    protected boolean languageLoader(InputStream inputStream) {
        try {
            List<String> lines = readFromInputStream
                    .yankTextFromFile(inputStream);
            return createLanguageMap(lines);
        } catch (IOException | NullPointerException e) {
            return false;
        }
    }

    private void loadDefaultLanguages() {
        String language = "/dialoguefiles/language_eng.txt";
        languageLoader(getLanguageResourceAsStream(language));
        language = "/dialoguefiles/language_fin.txt";
        languageLoader(getLanguageResourceAsStream(language));
    }

    private InputStream getLanguageResourceAsStream(String language) {
        return getClass().getResourceAsStream(language);
    }

    private boolean createLanguageMap(List<String> lines) {
        Map<String, String> langMap = generalTools
                .getStrStrMapAndStringListInterconversion()
                .convertStringListToStrStrMap(lines);
        if (!isValidLanguageMap(langMap)) {
            return false;
        }
        String langName = langMap.get("language");
        languages.put(langName, langMap);
        namesOfLanguages.add(langName);
        return true;
    }

    private boolean isValidLanguageMap(Map<String, String> map) {
        for (String keysForLangMap : keysForLangMaps) {
            if (!map.containsKey(keysForLangMap)) {
                return false;
            }
        }
        return true;
    }

    private String[] createArrayOfKeysForLangMaps() {
        int i = 0;
        String[] array = new String[KeysForLangMaps.values().length];
        for (KeysForLangMaps key : KeysForLangMaps.values()) {
            array[i] = key.keyName();
            i++;
        }
        return array;
    }

}
