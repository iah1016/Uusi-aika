/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.logic;

/**
 *
 * @author iah1016
 */
public class Sect {
    private String name;
    private int expenses;
    private int memberFee;

    public Sect(String name, int expenses, int memberFee) {
        this.name = name;
        this.expenses = expenses;
        this.memberFee = memberFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
}
