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
public class Temple {

    private Map<String, Integer> intValues;
    private int templeSceptDecr;
    private int deathCultCharismaReq;
    private int divineRightMoneyReq;

    public Temple(Map<String, Integer> intValues) {
        this.intValues = intValues;
        this.templeSceptDecr = intValues.get("templeSceptDecr");
        this.deathCultCharismaReq = intValues.get("templeDeathCultCharismaReq");
        this.divineRightMoneyReq = intValues.get("templeDivineRightMoneyReq");
    }

    public boolean preach(Player player, Sect sect) {
        List<Villager> congregation = sect.getCongregation();        
        if (congregation == null) {
            return false;
        }

        if (congregation.size() > 0) {
            for (int i = 0; i < congregation.size(); i++) {
                Villager member = congregation.get(i);
                int scept = member.getScepticism();
                member.setScepticism(scept - templeSceptDecr);
            }
            return true;
        }
        return false;
    }

    public boolean offerSodaToAllMembers(Player player) {
        return player.getCharisma() >= deathCultCharismaReq;
    }

    public boolean buyTicketToParadiseIsland(Player player, Sect sect) {
        return sect.getBalance() >= divineRightMoneyReq;
    }
}
