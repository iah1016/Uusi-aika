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
package fi.sewsiaica.uusiaika.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * This is you precious gift to the World. Find id... like-minded friends in the
 * village and lead them to the Next Level.
 *
 * @author iah1016
 */
public class Sect {

    private String name;
    private int balance;
    private int expenses;
    private int memberFee;
    private List<Villager> congregation;

    /**
     * The constructor is given the name, and the values for Balance, Expenses
     * and MemberFee as parameters.
     *
     * @param name The name is given by the user via GUI.
     * @param balance Default value from Config.intValues.
     * @param expenses Default value from Config.intValues.
     * @param memberFee Default value from Config.intValues.
     */
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

    public List<Villager> getCongregation() {
        return congregation;
    }

    public void setCongregation(List<Villager> congregation) {
        this.congregation = congregation;
    }

}
