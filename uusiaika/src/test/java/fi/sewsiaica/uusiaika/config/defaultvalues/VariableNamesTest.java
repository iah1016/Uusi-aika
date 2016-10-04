/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.config.defaultvalues;

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
public class VariableNamesTest {

    public VariableNamesTest() {
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
    public void variableNamesAreAllCorrect() {
        StringBuilder sb = new StringBuilder();
        for (VariableNames value : VariableNames.values()) {
            String variable = value.varName();
            sb.append(variable);
        }
        String keys = "playerCharismaplayerArgSkillssectBalancesectExpenses"
                + "sectMemberFeevilPopulationvilBaseScepticismvilBaseSelfEs"
                + "vilBaseSelfAwvilBaseArgSkillsvilBoundValue"
                + "convMaxNumberOfPersuasionsconvPersPlayerBound"
                + "convPersVilBoundconvPersPlayerCharIncrconvPersVilSelfAwDecr"
                + "convPersVilSceptDecrconvMaxNumberOfSermons"
                + "convSermPlayerBoundconvSermVilBoundconvSermPlayerCharIncr"
                + "convSermVilSceptDecrconvMaxNumberOfAccusations"
                + "convAccuPlayerBoundconvAccuVilBoundconvAccuPlayerCharIncr"
                + "convAccuVilSelfEsDecrturnInitialNumberOfTurns"
                + "turnMaxNumberOfTurnsturnSceptIncrPerTurn"
                + "turnThresholdForScepticismtempleSceptDecr"
                + "templeDeathCultCharismaReqtempleDivineRightMoneyReq"
                + "trainingCharismaIncrtrainingArgSkillsIncr";
        assertEquals(keys, sb.toString());
    }

    @Test
    public void varNameFromVariableNamesFunctionsProperly() {
        assertEquals("templeSceptDecr",
                VariableNames.TEMPLESCEPTDECR.varName());
    }
}
