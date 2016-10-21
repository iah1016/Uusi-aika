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

/**
 * The keys for the language maps will be used for checking if a
 * string,string-type map is a valid language map, ie it includes all the
 * required fields.
 *
 * @author iah1016
 */
public enum KeysForLangMaps {
    LANGUAGENAME("language"),
    APPLYFORCHARISMACOURSEBUTTON("applyForCharismaCourseButton"),
    APPLYFORDEBATECOURSEBUTTON("applyForDebateCourseButton"),
    BUYTICKETBUTTON("buyTicketButton"),
    CHANGELANGUAGEBUTTON("changeLanguageButton"),
    CONFIGVALUESFILEBUTTON("configValuesFileButton"),
    CREATEGAMEBUTTON("createGameButton"),
    DOORTODOORCONVERSIONBUTTON("doorToDoorConversionButton"),
    ENDTURNBUTTON("endTurnButton"),
    HALLOFFAMEBUTTON("hallOfFameButton"),
    LOADGAMEBUTTON("loadGameButton"),
    LOADLANGFROMFILEBUTTON("loadLangFromFileButton"),
    NEWGAMEBUTTON("newGameButton"),
    NEXTTARGETBUTTON("nextTargetButton"),
    OFFERSODABUTTON("offerSodaButton"),
    OPENINGMENUVIEWBUTTON("openingMenuViewButton"),
    OPENINGMENUVIEWBUTTON2("openingMenuViewButton2"),
    PERSUASIONBUTTON("persuasionButton"),
    PREACHBUTTON("preachButton"),
    QUITBUTTON("quitButton"),
    RETURNTOMAPVIEWBUTTON("returnToMapViewButton"),
    SAVEGAMEBUTTON("saveGameButton"),
    SERMONBUTTON("sermonButton"),
    SETTINGSBUTTON("settingsButton"),
    SHOWHALLOFFAMEBUTTON("showHallOfFameButton"),
    TEMPLEBUTTON("templeButton"),
    TRAININGCENTREBUTTON("trainingCentreButton"),
    VILLAGERNAMESFILEBUTTON("villagerNamesFileButton"),
    VILLAGERPROFSFILEBUTTON("villagerProfsFileButton"),
    FINALSCOREMESSAGE("finalScoreMessage"),
    ENDMESSAGE1("endMessage1"),
    ENDMESSAGE2("endMessage2"),
    ENDMESSAGE3("endMessage3"),
    ENDMESSAGEDEFAULT("endMessageDefault"),
    TEMPLEPREACHACTIONOK("templePreachActionOK"),
    TEMPLEPREACHACTIONFAIL("templePreachActionFail"),
    TEMPLEOFFERSODAFAIL("templeOfferSodaFail"),
    TEMPLEBUYTICKETFAIL("templeBuyTicketFail"),
    CHARISMACOURSEOK("charismaCourseOK"),
    CHARISMACOURSEFAIL("charismaCourseFail"),
    DEBATECOURSEOK("debateCourseOK"),
    DEBATECOURSEFAIL("debateCourseFail"),
    NOTARGETSLEFT("noTargetsLeft"),
    PERSUASIONOK("persuasionOK"),
    PERSUASIONFAIL("persuasionFail"),
    SERMONOK("sermonOK"),
    SERMONFAIL("sermonFail"),
    ACCUSATIONOK("accusationOK"),
    ACCUSATIONFAIL("accusationFail"),
    MAPVIEWINFO1("mapViewInfo1"),
    MAPVIEWINFO2("mapViewInfo2"),
    NOTARGETSSELECTED("noTargetsSelected"),
    SAVEFILELOCATIONTITLE("saveFileLocationTitle"),
    NAMETOOLONG("nameTooLong"),
    FILECHOOSINGCANCELLED("fileChoosingCancelled"),
    INVALIDCONFIGFILE("invalidConfigFile"),
    FILESCANNOTBEREAD("filesCannotBeRead"),
    CHOOSECONFIGFILETITLE("chooseConfigFileTitle"),
    CHOOSEVILNAMESTITLE("choosevilNamesTitle"),
    CHOOSEVILPROFSTITLE("choosevilProfsTitle"),
    OPENSAVEFILETITLE("openSaveFileTitle"),
    OPENLANGFILETITLE("openLangFileTitle"),
    TURNINFOPANEL("turnInfoPanel"),
    BALANCEINFOPANEL("balanceInfoPanel"),
    MEMBERSINFOPANEL("membersInfoPanel"),
    CURRTARGETINFOPANEL("currTargetInfoPanel"),
    TOTALTARGETSINFOPANEL("totalTargetsInfoPanel");
    private final String name;

    private KeysForLangMaps(String name) {
        this.name = name;
    }

    /**
     * The getter method for the keys of the language maps. These are used for
     * the validity checks.
     *
     * @return Returns a key string.
     */
    public String keyName() {
        return name;
    }
}
