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
package fi.sewsiaica.uusiaika.logic.activegame;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mickey Mouse has waved his hands furiously in your watch and as a result, you
 * are one step closer to the end. The members are beginning to doubt whether
 * you really are the chosen one. Those who still remain loyal to you will pay
 * the fee. Watch out – if the expenses sink your sect's balance to subzero, you
 * are done.
 *
 * @author iah1016
 */
public class TurnLogic {

    private final Map<String, Integer> intValues;
    private int numberOfTurns;
    private final int maxNumberOfTurns;
    private final int sceptIncrPerTurn;
    private final int thresholdForScept;

    /**
     * The constructor set the values of numberOfTurns, maxNumberOfTurns,
     * sceptIncrPerTurn, and thresholdForScept to its object variable.
     *
     * @param intValues Includes all the variable values of the game.
     */
    public TurnLogic(Map<String, Integer> intValues) {
        this.intValues = intValues;
        this.numberOfTurns = intValues.get("turnInitialNumberOfTurns");
        this.maxNumberOfTurns = intValues.get("turnMaxNumberOfTurns");
        this.sceptIncrPerTurn = intValues.get("turnSceptIncrPerTurn");
        this.thresholdForScept = intValues.get("turnThresholdForScepticism");
    }

    /**
     * The end is near. numberOfTurns increases by one. If it equals to max
     * turns, it's game over. Members may leave if their scepticism is high
     * enough. The remaining members pay the fee. Then the sect pays the
     * expenses. If the balance is less than zero after that, it's game over.
     *
     * @param player You probably should have picked the Marathon game speed.
     * @param sect Doubting Thomases.
     * @return Returns false, if the game is over and true otherwise.
     */
    public boolean nextTurn(Player player, Sect sect) {
        numberOfTurns++;
        if (!gameHasReachedMaxTurns()) {
            sect.setCongregation(membersInNextTurn(sect.getCongregation()));
            membersPayFee(sect);
            sectPaysExpenses(sect);

            return balanceIsNotNegative(sect);
        }
        return false;
    }

    /**
     * This method will remove the members who have decided to leave.
     *
     * @param oldList The member list before the turn change.
     * @return The member list after the turn change.
     */
    public List<Villager> membersInNextTurn(List<Villager> oldList) {
        List<Villager> congregationInNextTurn = new ArrayList<>();
        if (oldList != null) {
            for (Villager member : oldList) {
                increaseScepticism(member);
                if (sceptLessThanThreshold(member.getScepticism())) {
                    congregationInNextTurn.add(member);
                } else {
                    member.setInSect(false);
                }
            }
        }
        return congregationInNextTurn;
    }

    /**
     * The member's scepticism increases by the value of sceptIncrPerTurn.
     *
     * @param member Doubting Thomas or Thomalina.
     */
    public void increaseScepticism(Villager member) {
        int newScept = member.getScepticism() + sceptIncrPerTurn;
        member.setScepticism(newScept);
    }

    /**
     * Pay up for your sins, brothers and sisters.
     *
     * @param sect a source of pesetas.
     */
    public void membersPayFee(Sect sect) {
        int balance = sect.getBalance();
        int fee = sect.getMemberFee();

        for (int i = 0; i < sect.getCongregation().size(); i++) {
            balance += fee;
        }
        sect.setBalance(balance);
    }

    /**
     * The rent is too damn high.
     *
     * @param sect The piggy bank won't survive.
     */
    public void sectPaysExpenses(Sect sect) {
        int balance = sect.getBalance();
        int expenses = sect.getExpenses();
        sect.setBalance(balance - expenses);
    }

    /**
     * You go below, you are not coming back.
     *
     * @param sect Balancing on a thread.
     * @return True is boom, game over.
     */
    public boolean balanceIsNotNegative(Sect sect) {
        return sect.getBalance() >= 0;
    }

    /**
     * Checking that the member's scepticism is below the threshold.
     *
     * @param value Member's scepticism.
     * @return True, the member remains. False, (s)he has found a spoon and dug
     * him-/herself out.
     */
    public boolean sceptLessThanThreshold(int value) {
        return value < thresholdForScept;
    }

    /**
     * Checking that there are still turns left
     * @return True, and the game is over.
     */
    public boolean gameHasReachedMaxTurns() {
        return numberOfTurns >= maxNumberOfTurns;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }
}
