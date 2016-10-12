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
package fi.sewsiaica.uusiaika.ui.subpanellisteners;

import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The object of this class handles the VillageListPanel selections.
 *
 * @author iah1016
 */
public class VillagerListPanelListener implements ListSelectionListener {

    private final GameLogic gameLogic;

    /**
     * The constructor is given GameLogic as a parameter.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     */
    public VillagerListPanelListener(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        ListSelectionModel lsm = (ListSelectionModel) lse.getSource();
        
        ActiveGame activeGame = gameLogic.getActiveGame();
        List<Villager> allVillagers = activeGame.getVillagers();
        List<Villager> tempTargets = new ArrayList<>();

        for (int i = 0; i <= lsm.getMaxSelectionIndex(); i++) {
            if (lsm.isSelectedIndex(i)) {
                Villager villager = allVillagers.get(i);
                
                if (villagerIsNotMember(villager)) {
                    tempTargets.add(villager);
                }
            }
        }
        activeGame.setTargetVillagers(tempTargets);
    }
    
    private boolean villagerIsNotMember(Villager villager) {
        return !villager.isInSect();
    }

}
