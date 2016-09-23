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
public class Temple {

    private int defaultTempleSceptDecr;
    private int defaultDeathCultCharismaReq;
    private int defaultDivineRightMoneyReq;

    public Temple(int defaultTempleCharismaDecr,
            int defaultDeathCultCharismaReq, int defaultDivineRightMoneyReq) {
        this.defaultTempleSceptDecr = defaultTempleCharismaDecr;
        this.defaultDeathCultCharismaReq = defaultDeathCultCharismaReq;
        this.defaultDivineRightMoneyReq = defaultDivineRightMoneyReq;
    }

    public boolean preach(Player player, Sect sect) {
        ArrayList<Villager> congregation = sect.getCongregation();        
        if (congregation == null) {
            return false;
        }

        if (congregation.size() > 0) {
            for (int i = 0; i < congregation.size(); i++) {
                Villager member = congregation.get(i);
                int scept = member.getScepticism();
                member.setScepticism(scept - defaultTempleSceptDecr);
            }
            return true;
        }
        return false;
    }

    public boolean offerSodaToAllMembers(Player player) {
        return player.getCharisma() >= defaultDeathCultCharismaReq;
    }

    public boolean buyTicketToParadiseIsland(Player player, Sect sect) {
        return sect.getBalance() >= defaultDivineRightMoneyReq;
    }
}
