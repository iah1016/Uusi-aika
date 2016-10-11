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
package fi.sewsiaica.uusiaika.config.defaultvalues;

/**
 * This enum class contains all the default values of the game's variables.
 *
 * @author iah1016
 */
public enum DefaultVariableValues {
    DEFAULTPLAYERCHARISMA(10),
    DEFAULTPLAYERARGSKILLS(10),
    DEFAULTSECTBALANCE(5000),
    DEFAULTSECTEXPENSES(1000),
    DEFAULTSECTMEMBERFEE(100),
    DEFAULTVILPOPULATION(10),
    DEFAULTVILBASESCEPTICISM(10),
    DEFAULTVILBASESELFES(10),
    DEFAULTVILBASESELFAW(10),
    DEFAULTVILBASEARGSKILLS(10),
    DEFAULTVILBOUNDVALUE(51),
    DEFAULTCONVMAXNUMBEROFPERSUASIONS(3),
    DEFAULTCONVPERSPLAYERBOUND(20),
    DEFAULTCONVPERSVILBOUND(20),
    DEFAULTCONVPERSPLAYERCHARINCR(2),
    DEFAULTCONVPERSVILSELFAWDECR(5),
    DEFAULTCONVPERSVILSCEPTDECR(5),
    DEFAULTCONVMAXNUMBEROFSERMONS(2),
    DEFAULTCONVSERMPLAYERBOUND(20),
    DEFAULTCONVSERMVILBOUND(20),
    DEFAULTCONVSERMPLAYERCHARINCR(2),
    DEFAULTCONVSERMVILSCEPTDECR(5),
    DEFAULTCONVMAXNUMBEROFACCUSATIONS(2),
    DEFAULTCONVACCUPLAYERBOUND(20),
    DEFAULTCONVACCUVILBOUND(20),
    DEFAULTCONVACCUPLAYERCHARINCR(2),
    DEFAULTCONVACCUVILSELFESDECR(5),
    DEFAULTTURNINITIALNUMBEROFTURNS(1),
    DEFAULTTURNMAXNUMBEROFTURNS(100),
    DEFAULTTURNSCEPTINCRPERTURN(10),
    DEFAULTTURNTHRESHOLDFORSCEPTICISM(200),
    DEFAULTTEMPLESCEPTDECR(10),
    DEFAULTTEMPLEDEATHCULTCHARISMAREQ(255),
    DEFAULTTEMPLEDIVINERIGHTMONEYREQ(100000),
    DEFAULTTRAININGCHARISMAINCR(10),
    DEFAULTTRAININGARGSKILLSINCR(10);
    private final int value;

    private DefaultVariableValues(int value) {
        this.value = value;
    }

    /**
     * The getter for the default variable values.
     *
     * @return Returns a variable value.
     */
    public int value() {
        return value;
    }
}
