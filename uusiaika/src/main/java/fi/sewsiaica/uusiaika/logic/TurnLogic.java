/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author iah1016
 */
public class TurnLogic {

    private Map<String, Integer> intValues;
    private int numberOfTurns;
    private int maxNumberOfTurns;
    private int sceptIncrPerTurn;
    private int thresholdForScept;

    public TurnLogic(Map<String, Integer> intValues) {
        this.intValues = intValues;
        this.numberOfTurns = intValues.get("turnInitialNumberOfTurns");
        this.maxNumberOfTurns = intValues.get("turnMaxNumberOfTurns");
        this.sceptIncrPerTurn = intValues.get("turnSceptIncrPerTurn");
        this.thresholdForScept = intValues.get("turnThresholdForScepticism");
    }

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

    public void increaseScepticism(Villager member) {
        int newScept = member.getScepticism() + sceptIncrPerTurn;
        member.setScepticism(newScept);
    }

    public void membersPayFee(Sect sect) {
        int balance = sect.getBalance();
        int fee = sect.getMemberFee();

        for (int i = 0; i < sect.getCongregation().size(); i++) {
            balance += fee;
        }
        sect.setBalance(balance);
    }

    public void sectPaysExpenses(Sect sect) {
        int balance = sect.getBalance();
        int expenses = sect.getExpenses();
        sect.setBalance(balance - expenses);
    }

    public boolean balanceIsNotNegative(Sect sect) {
        return sect.getBalance() >= 0;
    }

    public boolean sceptLessThanThreshold(int value) {
        return value < thresholdForScept;
    }

    public boolean gameHasReachedMaxTurns() {
        return numberOfTurns >= maxNumberOfTurns;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }
}
