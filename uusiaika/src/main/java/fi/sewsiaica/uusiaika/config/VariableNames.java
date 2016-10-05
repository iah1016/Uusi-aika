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
package fi.sewsiaica.uusiaika.config;

/**
 * This enum class contains all the variable names. These should not be changed
 * under any circumstances.
 *
 * @author iah1016
 */
public enum VariableNames {
    PLAYERCHARISMA("playerCharisma"),
    PLAYERARGSKILLS("playerArgSkills"),
    SECTBALANCE("sectBalance"),
    SECTEXPENSES("sectExpenses"),
    SECTMEMBERFEE("sectMemberFee"),
    VILPOPULATION("vilPopulation"),
    VILBASESCEPTICISM("vilBaseScepticism"),
    VILBASESELFES("vilBaseSelfEs"),
    VILBASESELFAW("vilBaseSelfAw"),
    VILBASEARGSKILLS("vilBaseArgSkills"),
    VILBOUNDVALUE("vilBoundValue"),
    CONVMAXNUMBEROFPERSUASIONS("convMaxNumberOfPersuasions"),
    CONVPERSPLAYERBOUND("convPersPlayerBound"),
    CONVPERSVILBOUND("convPersVilBound"),
    CONVPERSPLAYERCHARINCR("convPersPlayerCharIncr"),
    CONVPERSVILSELFAWDECR("convPersVilSelfAwDecr"),
    CONVPERSVILSCEPTDECR("convPersVilSceptDecr"),
    CONVMAXNUMBEROFSERMONS("convMaxNumberOfSermons"),
    CONVSERMPLAYERBOUND("convSermPlayerBound"),
    CONVSERMVILBOUND("convSermVilBound"),
    CONVSERMPLAYERCHARINCR("convSermPlayerCharIncr"),
    CONVSERMVILSCEPTDECR("convSermVilSceptDecr"),
    CONVMAXNUMBEROFACCUSATIONS("convMaxNumberOfAccusations"),
    CONVACCUPLAYERBOUND("convAccuPlayerBound"),
    CONVACCUVILBOUND("convAccuVilBound"),
    CONVACCUPLAYERCHARINCR("convAccuPlayerCharIncr"),
    CONVACCUVILSELFESDECR("convAccuVilSelfEsDecr"),
    TURNINITIALNUMBEROFTURNS("turnInitialNumberOfTurns"),
    TURNMAXNUMBEROFTURNS("turnMaxNumberOfTurns"),
    TURNSCEPTINCRPERTURN("turnSceptIncrPerTurn"),
    TURNTHRESHOLDFORSCEPTICISM("turnThresholdForScepticism"),
    TEMPLESCEPTDECR("templeSceptDecr"),
    TEMPLEDEATHCULTCHARISMAREQ("templeDeathCultCharismaReq"),
    TEMPLEDIVINERIGHTMONEYREQ("templeDivineRightMoneyReq"),
    TRAININGCHARISMAINCR("trainingCharismaIncr"),
    TRAININGARGSKILLSINCR("trainingArgSkillsIncr");
    private final String name;

    private VariableNames(String name) {
        this.name = name;
    }

    /**
     * The getter method for the variable names.
     *
     * @return Returns a variable name.
     */
    public String varName() {
        return name;
    }
}
