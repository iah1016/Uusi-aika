/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

import fi.sewsiaica.uusiaika.domain.*;
import java.util.ArrayList;

/**
 *
 * @author iah1016
 */
public class TurnLogic {

    private int numberOfTurns;
    private int defaultMaxNumberOfTurns;
    private int defaultSceptIncrPerTurn;
    private int defaultThresholdForScepticism;

    public TurnLogic(int defaultMaxNumberOfTurns, int defaultSceptIncrPerTurn,
            int defaultThresholdForScepticism) {
        this(0, defaultMaxNumberOfTurns, defaultSceptIncrPerTurn,
                defaultThresholdForScepticism);
    }

    public TurnLogic(int numberOfTurns, int defaultMaxNumberOfTurns,
            int defaultSceptIncrPerTurn, int defaultThresholdForScepticism) {
        this.numberOfTurns = numberOfTurns;
        this.defaultMaxNumberOfTurns = defaultMaxNumberOfTurns;
        this.defaultSceptIncrPerTurn = defaultSceptIncrPerTurn;
        this.defaultThresholdForScepticism = defaultThresholdForScepticism;
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

    public ArrayList<Villager> membersInNextTurn(ArrayList<Villager> oldList) {
        // Update this so that the members leaving the sect do not "disappear".
        ArrayList<Villager> congregationInNextTurn = new ArrayList<>();
        if (oldList != null) {
            for (Villager member : oldList) {
                increaseScepticism(member);
                if (sceptLessThanThreshold(member.getScepticism())) {
                    congregationInNextTurn.add(member);
                }
            }
        }
        return congregationInNextTurn;
    }

    public void increaseScepticism(Villager member) {
        int newScept = member.getScepticism() + defaultSceptIncrPerTurn;
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
        return value < defaultThresholdForScepticism;
    }

    public boolean gameHasReachedMaxTurns() {
        return numberOfTurns >= defaultMaxNumberOfTurns;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }
}
