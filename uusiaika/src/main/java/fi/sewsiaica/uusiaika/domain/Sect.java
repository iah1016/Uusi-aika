/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.domain;

import java.util.ArrayList;

/**
 *
 * @author iah1016
 */
public class Sect {

    private String name;
    private int balance;
    private int expenses;
    private int memberFee;
    private ArrayList<Villager> congregation;

    public Sect(String name, int balance, int expenses, int memberFee) {
        this.name = name;
        this.balance = balance;
        this.expenses = expenses;
        this.memberFee = memberFee;
        this.congregation = new ArrayList<Villager>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getMemberFee() {
        return memberFee;
    }

    public void setMemberFee(int memberFee) {
        this.memberFee = memberFee;
    }

    public ArrayList<Villager> getCongregation() {
        return congregation;
    }

    public void setCongregation(ArrayList<Villager> congregation) {
        this.congregation = congregation;
    }

}
