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
public class KeysForLangMapsTest {

    public KeysForLangMapsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void keyNameInKeysForLangMapsFunctionsProperly() {
        assertEquals("language", KeysForLangMaps.LANGUAGENAME.keyName());
        assertEquals("applyForCharismaCourseButton", KeysForLangMaps.APPLYFORCHARISMACOURSEBUTTON.keyName());
        assertEquals("applyForDebateCourseButton", KeysForLangMaps.APPLYFORDEBATECOURSEBUTTON.keyName());
        assertEquals("buyTicketButton", KeysForLangMaps.BUYTICKETBUTTON.keyName());
        assertEquals("changeLanguageButton", KeysForLangMaps.CHANGELANGUAGEBUTTON.keyName());
        assertEquals("configValuesFileButton", KeysForLangMaps.CONFIGVALUESFILEBUTTON.keyName());
        assertEquals("createGameButton", KeysForLangMaps.CREATEGAMEBUTTON.keyName());
        assertEquals("doorToDoorConversionButton", KeysForLangMaps.DOORTODOORCONVERSIONBUTTON.keyName());
        assertEquals("endTurnButton", KeysForLangMaps.ENDTURNBUTTON.keyName());
        assertEquals("hallOfFameButton", KeysForLangMaps.HALLOFFAMEBUTTON.keyName());
        assertEquals("loadGameButton", KeysForLangMaps.LOADGAMEBUTTON.keyName());
        assertEquals("loadLangFromFileButton", KeysForLangMaps.LOADLANGFROMFILEBUTTON.keyName());
        assertEquals("newGameButton", KeysForLangMaps.NEWGAMEBUTTON.keyName());
        assertEquals("nextTargetButton", KeysForLangMaps.NEXTTARGETBUTTON.keyName());
        assertEquals("offerSodaButton", KeysForLangMaps.OFFERSODABUTTON.keyName());
        assertEquals("openingMenuViewButton", KeysForLangMaps.OPENINGMENUVIEWBUTTON.keyName());
        assertEquals("openingMenuViewButton2", KeysForLangMaps.OPENINGMENUVIEWBUTTON2.keyName());
        assertEquals("persuasionButton", KeysForLangMaps.PERSUASIONBUTTON.keyName());
        assertEquals("preachButton", KeysForLangMaps.PREACHBUTTON.keyName());
        assertEquals("quitButton", KeysForLangMaps.QUITBUTTON.keyName());
        assertEquals("returnToMapViewButton", KeysForLangMaps.RETURNTOMAPVIEWBUTTON.keyName());
        assertEquals("saveGameButton", KeysForLangMaps.SAVEGAMEBUTTON.keyName());
        assertEquals("sermonButton", KeysForLangMaps.SERMONBUTTON.keyName());
        assertEquals("settingsButton", KeysForLangMaps.SETTINGSBUTTON.keyName());
        assertEquals("showHallOfFameButton", KeysForLangMaps.SHOWHALLOFFAMEBUTTON.keyName());
        assertEquals("templeButton", KeysForLangMaps.TEMPLEBUTTON.keyName());
        assertEquals("trainingCentreButton", KeysForLangMaps.TRAININGCENTREBUTTON.keyName());
        assertEquals("villagerNamesFileButton", KeysForLangMaps.VILLAGERNAMESFILEBUTTON.keyName());
        assertEquals("villagerProfsFileButton", KeysForLangMaps.VILLAGERPROFSFILEBUTTON.keyName());
        assertEquals("finalScoreMessage", KeysForLangMaps.FINALSCOREMESSAGE.keyName());
        assertEquals("endMessage1", KeysForLangMaps.ENDMESSAGE1.keyName());
        assertEquals("endMessage2", KeysForLangMaps.ENDMESSAGE2.keyName());
        assertEquals("endMessage3", KeysForLangMaps.ENDMESSAGE3.keyName());
        assertEquals("endMessageDefault", KeysForLangMaps.ENDMESSAGEDEFAULT.keyName());
        assertEquals("templePreachActionOK", KeysForLangMaps.TEMPLEPREACHACTIONOK.keyName());
        assertEquals("templePreachActionFail", KeysForLangMaps.TEMPLEPREACHACTIONFAIL.keyName());
        assertEquals("templeOfferSodaFail", KeysForLangMaps.TEMPLEOFFERSODAFAIL.keyName());
        assertEquals("templeBuyTicketFail", KeysForLangMaps.TEMPLEBUYTICKETFAIL.keyName());
        assertEquals("charismaCourseOK", KeysForLangMaps.CHARISMACOURSEOK.keyName());
        assertEquals("charismaCourseFail", KeysForLangMaps.CHARISMACOURSEFAIL.keyName());
        assertEquals("debateCourseOK", KeysForLangMaps.DEBATECOURSEOK.keyName());
        assertEquals("debateCourseFail", KeysForLangMaps.DEBATECOURSEFAIL.keyName());
        assertEquals("noTargetsLeft", KeysForLangMaps.NOTARGETSLEFT.keyName());
        assertEquals("persuasionOK", KeysForLangMaps.PERSUASIONOK.keyName());
        assertEquals("persuasionFail", KeysForLangMaps.PERSUASIONFAIL.keyName());
        assertEquals("sermonOK", KeysForLangMaps.SERMONOK.keyName());
        assertEquals("sermonFail", KeysForLangMaps.SERMONFAIL.keyName());
        assertEquals("accusationOK", KeysForLangMaps.ACCUSATIONOK.keyName());
        assertEquals("accusationFail", KeysForLangMaps.ACCUSATIONFAIL.keyName());
        assertEquals("mapViewInfo1", KeysForLangMaps.MAPVIEWINFO1.keyName());
        assertEquals("mapViewInfo2", KeysForLangMaps.MAPVIEWINFO2.keyName());
        assertEquals("noTargetsSelected", KeysForLangMaps.NOTARGETSSELECTED.keyName());
        assertEquals("saveFileLocationTitle", KeysForLangMaps.SAVEFILELOCATIONTITLE.keyName());
        assertEquals("nameTooLong", KeysForLangMaps.NAMETOOLONG.keyName());
        assertEquals("fileChoosingCancelled", KeysForLangMaps.FILECHOOSINGCANCELLED.keyName());
        assertEquals("invalidConfigFile", KeysForLangMaps.INVALIDCONFIGFILE.keyName());
        assertEquals("filesCannotBeRead", KeysForLangMaps.FILESCANNOTBEREAD.keyName());
        assertEquals("chooseConfigFileTitle", KeysForLangMaps.CHOOSECONFIGFILETITLE.keyName());
        assertEquals("choosevilNamesTitle", KeysForLangMaps.CHOOSEVILNAMESTITLE.keyName());
        assertEquals("choosevilProfsTitle", KeysForLangMaps.CHOOSEVILPROFSTITLE.keyName());
        assertEquals("openSaveFileTitle", KeysForLangMaps.OPENSAVEFILETITLE.keyName());
        assertEquals("openLangFileTitle", KeysForLangMaps.OPENLANGFILETITLE.keyName());
        assertEquals("turnInfoPanel", KeysForLangMaps.TURNINFOPANEL.keyName());
        assertEquals("balanceInfoPanel", KeysForLangMaps.BALANCEINFOPANEL.keyName());
        assertEquals("membersInfoPanel", KeysForLangMaps.MEMBERSINFOPANEL.keyName());
        assertEquals("currTargetInfoPanel", KeysForLangMaps.CURRTARGETINFOPANEL.keyName());
        assertEquals("totalTargetsInfoPanel", KeysForLangMaps.TOTALTARGETSINFOPANEL.keyName());
    }
}
