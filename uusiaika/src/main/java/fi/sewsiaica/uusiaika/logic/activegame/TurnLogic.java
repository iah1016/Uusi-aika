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

    private int numberOfTurns;
    private final int maxNumberOfTurns;
    private final int sceptIncrPerTurn;
    private final int thresholdForScept;
    private List<Villager> congregationInNextTurn;

    /**
     * The constructor set the values of numberOfTurns, maxNumberOfTurns,
     * sceptIncrPerTurn, and thresholdForScept to its object variables.
     *
     * @param intValues Includes all the variable values of the game.
     */
    public TurnLogic(Map<String, Integer> intValues) {
        this.numberOfTurns = intValues.get("turnInitialNumberOfTurns");
        this.maxNumberOfTurns = intValues.get("turnMaxNumberOfTurns");
        this.sceptIncrPerTurn = intValues.get("turnSceptIncrPerTurn");
        this.thresholdForScept = intValues.get("turnThresholdForScepticism");
    }

    protected int getNumberOfTurns() {
        return numberOfTurns;
    }
    
    /**
     * The end is near. numberOfTurns increases by one. If it equals to max
     * turns, it's game over. Members may leave if their scepticism is high
     * enough. The remaining members pay the fee. Then the sect pays the
     * expenses. If the balance is less than zero after that, it's game over.
     *
     * @param player You probably should have picked the Marathon game speed.
     * @param sect Doubting Thomases.
     * @param allVillagers All the villagers.
     * @return Returns false, if the game is over and true otherwise.
     */
    public boolean nextTurn(Player player, Sect sect,
            List<Villager> allVillagers) {
        if (!balanceIsNotNegative(sect) || gameHasReachedMaxTurns()) {
            return false;
        }
        numberOfTurns++;
        congregationInNextTurn = new ArrayList<>();
        updateVillagersAndCongregation(allVillagers);
        sect.setCongregation(congregationInNextTurn);
        membersPayFee(sect);
        sectPaysExpenses(sect);

        return balanceIsNotNegative(sect);
    }

    private void updateVillagersAndCongregation(List<Villager> allVillagers) {
        for (Villager villager : allVillagers) {
            resetVillagersNumberOfConversions(villager);
            if (villager.isInSect()) {
                keepMemberIfSceptLessThanThreshold(villager);
            }
        }
    }

    private void resetVillagersNumberOfConversions(Villager villager) {
        villager.setNumberOfPersuations(0);
        villager.setNumberOfSermons(0);
        villager.setNumberOfAccusations(0);
    }
    
    private void keepMemberIfSceptLessThanThreshold(Villager member) {
        increaseScepticism(member);
        if (sceptLessThanThreshold(member.getScepticism())) {
            congregationInNextTurn.add(member);
        } else {
            member.setInSect(false);
        }
    }

    private void increaseScepticism(Villager member) {
        int newScept = member.getScepticism() + sceptIncrPerTurn;
        member.setScepticism(newScept);
    }

    private void membersPayFee(Sect sect) {
        int balance = sect.getBalance();
        int fee = sect.getMemberFee();

        for (int i = 0; i < sect.getCongregation().size(); i++) {
            balance += fee;
        }
        sect.setBalance(balance);
    }

    private void sectPaysExpenses(Sect sect) {
        int balance = sect.getBalance();
        int expenses = sect.getExpenses();
        sect.setBalance(balance - expenses);
    }

    private boolean balanceIsNotNegative(Sect sect) {
        return sect.getBalance() >= 0;
    }

    private boolean sceptLessThanThreshold(int value) {
        return value < thresholdForScept;
    }

    private boolean gameHasReachedMaxTurns() {
        return numberOfTurns >= maxNumberOfTurns;
    }
    
}
