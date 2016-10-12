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
package fi.sewsiaica.uusiaika.ui.viewpanellisteners;

import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.ui.GameFrame;
import fi.sewsiaica.uusiaika.ui.PanelNames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractButton;

/**
 * The object of this class handles the events in the door-to-door conversion
 * view. The villager selection is not yet implemented.
 *
 * @author iah1016
 */
public class DoorToDoorViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final AbstractButton persuasionButton;
    private final AbstractButton sermonButton;
    private final AbstractButton accusationButton;
    private final AbstractButton nextTargetButton;
    private final AbstractButton returnToMapViewButton;
    private final AbstractButton endTurnButton;

    /**
     * The constructor is given an array of five AbstractButtons, GameFrame, and
     * GameLogic as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param buttons Button [0] is for the persuasion, [1] for the sermon, [2]
     * for the accusation, [3] changes the target, [4] returns to the map view,
     * and [5] ends the turn.
     */
    public DoorToDoorViewPanelListener(GameLogic gameLogic, GameFrame frame,
            AbstractButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.persuasionButton = buttons[0];
        this.sermonButton = buttons[1];
        this.accusationButton = buttons[2];
        this.nextTargetButton = buttons[3];
        this.returnToMapViewButton = buttons[4];
        this.endTurnButton = buttons[5];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == returnToMapViewButton) {
            emptyTargetVillagerList();
            gameFrame.changeViewPanel(PanelNames.MAP_VIEW);
        } else {
            if (ae.getSource() == persuasionButton) {
                persuasionSelected();
            } else if (ae.getSource() == sermonButton) {
                sermonSelected();
            } else if (ae.getSource() == accusationButton) {
                accusationSelected();
            } else if (ae.getSource() == nextTargetButton) {
                removeFirstTargetIfTargetListNotEmpty();
            } else if (ae.getSource() == endTurnButton) {
                gameLogic.endTurn();
            }
            updateView();
        }
    }

    private void updateView() {
        gameFrame.changeViewPanel(PanelNames.DOORTODOOR_VIEW);
    }

    private void emptyTargetVillagerList() {
        gameLogic.getActiveGame().setTargetVillagers(new ArrayList<>());
    }

    private void removeFirstTargetIfTargetListNotEmpty() {
        if (!noTargetsLeft()) {
            getTargetVillagers().remove(0);
        }
    }

    private boolean noTargetsLeft() {
        if (getTargetVillagers().isEmpty()) {
            System.out.println("No targets left!");
            return true;
        }
        return false;
    }

    private void persuasionSelected() {
        if (!noTargetsLeft()) {
            Villager currentTarget = getTargetVillagers().get(0);

            if (gameLogic.conversion(currentTarget, 'a')) {
                System.out.println("Persuasion succeeds!");
                removeFirstTargetIfTargetListNotEmpty();
            } else {
                System.out.println("Nope. Not this time.");
            }
        }
    }

    private void sermonSelected() {
        if (!noTargetsLeft()) {
            Villager currentTarget = getTargetVillagers().get(0);

            if (gameLogic.conversion(currentTarget, 'b')) {
                System.out.println("Sermon succeeds!");
                removeFirstTargetIfTargetListNotEmpty();
            } else {
                System.out.println("Nope. Not this time.");
            }
        }
    }

    private void accusationSelected() {
        if (!noTargetsLeft()) {
            Villager currentTarget = getTargetVillagers().get(0);

            if (gameLogic.conversion(currentTarget, 'b')) {
                System.out.println("Accusation succeeds!");
                removeFirstTargetIfTargetListNotEmpty();
            } else {
                System.out.println("Nope. Not this time.");
            }
        }
    }

    private List<Villager> getTargetVillagers() {
        return gameLogic.getActiveGame().getTargetVillagers();
    }
}
