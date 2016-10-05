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
import java.util.List;
import java.util.Map;

/**
 * The temple is where the magic happens. The winning conditions are checked
 * here. Preaching to the congregation is also needed to keep the overall
 * scepticism low.
 *
 * @author iah1016
 */
public class Temple {

    private final Map<String, Integer> intValues;
    private final int templeSceptDecr;
    private final int deathCultCharismaReq;
    private final int divineRightMoneyReq;

    /**
     * The constructor set the values of templeSceptDecr, deathCultCharismaReq,
     * and divineRightMoneyReq to its object variable.
     *
     * @param intValues Includes all the variable values of the game.
     */
    public Temple(Map<String, Integer> intValues) {
        this.intValues = intValues;
        this.templeSceptDecr = intValues.get("templeSceptDecr");
        this.deathCultCharismaReq = intValues.get("templeDeathCultCharismaReq");
        this.divineRightMoneyReq = intValues.get("templeDivineRightMoneyReq");
    }

    /**
     * As in every turn the members' scepticism increases, some impeding
     * procedures ought to be carried out. Bring your A-game and deliver a
     * lights-out-passionate performance to your precious flock.
     *
     * @param player You the man (or woman).
     * @param sect Your greatest achievement.
     * @return Currently, returns true unless the congregation size is zero.
     */
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

    /**
     * This is it. No turning back. If your charisma is high enough, everyone is
     * going to ascend to the next level.
     *
     * @param player The chief him- or herself.
     * @return If you've high enough charisma, you win. If not, nothing happens.
     */
    public boolean offerSodaToAllMembers(Player player) {
        return player.getCharisma() >= deathCultCharismaReq;
    }

    /**
     * A one-way ticket to Paradise (some obscure island in the western Pacific)
     * and only you are going. You have taught your flock well and now they can
     * manage themselves. You will take a reasonable reward of 100 percent of
     * the Sect's balance with you.
     *
     * @param player The grand guru.
     * @param sect Meh. They will do fine.
     * @return If there is enough moolah in the bank, then you win. If not, then
     * nothing happens.
     */
    public boolean buyTicketToParadiseIsland(Player player, Sect sect) {
        return sect.getBalance() >= divineRightMoneyReq;
    }
}
