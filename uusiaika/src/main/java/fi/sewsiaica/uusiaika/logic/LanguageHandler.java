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

import fi.sewsiaica.uusiaika.dialogue.Dialogue;
import java.util.List;
import java.util.Map;

/**
 * The dialogue (ie all the text) of the game can be viewed in different
 * languages; this class will handle what language is being displayed. The
 * default language is English. The language is changed in the settings menu,
 * accessed from the opening/main menu.
 *
 * @author iah1016
 */
public class LanguageHandler {

    private final Dialogue dialogue;
    private Map<String, String> activeLanguage;

    /**
     * The constructor creates an immutable Dialogue object and sets it as an
     * object variable. It also sets English as the default language.
     */
    protected LanguageHandler() {
        this.dialogue = new Dialogue();
        this.activeLanguage = dialogue.getLangMap("English");
    }

    /**
     * This method changes the active language by getting the language map that
     * corresponds to the language name given as a parameter from the Dialogue
     * object. Used by the GameLogic object.
     *
     * @param languageName The language name given as a string.
     * @return Returns true if the language change is successful.
     */
    protected boolean setActiveLanguage(String languageName) {
        Map<String, String> tempLang = dialogue.getLangMap(languageName);
        if (tempLang == null) {
            return false;
        }
        activeLanguage = tempLang;
        return true;
    }

    /**
     * This method returns the active language map to the GameLogic object.
     *
     * @return Returns the active language map.
     */
    protected Map<String, String> getActiveLanguage() {
        return activeLanguage;
    }
    
    /**
     * This method returns the language names to the GameLogic object.
     * 
     * @return Returns the list of language names.
     */
    protected List<String> getNamesOfLanguages() {
        return dialogue.getNamesOfLanguages();
    }
}
