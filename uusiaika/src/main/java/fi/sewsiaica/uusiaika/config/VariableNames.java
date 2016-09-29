/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.config;

/**
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

    public String varName() {
        return name;
    }
}
