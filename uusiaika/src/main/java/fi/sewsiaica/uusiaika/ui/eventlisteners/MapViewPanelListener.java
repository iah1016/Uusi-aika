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
package fi.sewsiaica.uusiaika.ui.eventlisteners;

import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.ui.GameFrame;
import fi.sewsiaica.uusiaika.ui.PanelKeys;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * The object of this class handles the events in the map view. It currently
 * implements the ActionListener interface, though this may change.
 *
 * @author iah1016
 */
public class MapViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final JButton templeButton;
    private final JButton trainingCentreButton;
    private final JButton doorToDoorConversionButton;
    private final JButton endTurnButton;
    private final JButton openingMenuViewButton;

    /**
     * The constructor is given an array of five JButtons, GameFrame, and
     * GameLogic as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param buttons The current ViewPanel changes to [0] TEMPLE_VIEW, [1]
     * TRAININGCENTRE_VIEW, [2] DOORTODOOR_VIEW (conversion), [3] Ends the turn
     * via GameLogic, [4] openingMenuViewButton The current ViewPanel changes to
     * OPENING_MENU_VIEW, thus ending the active game.
     */
    public MapViewPanelListener(GameLogic gameLogic, GameFrame frame,
            JButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.templeButton = buttons[0];
        this.trainingCentreButton = buttons[1];
        this.doorToDoorConversionButton = buttons[2];
        this.endTurnButton = buttons[3];
        this.openingMenuViewButton = buttons[4];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == templeButton) {
            System.out.println("temppeli");
        } else if (ae.getSource() == trainingCentreButton) {
            System.out.println("tr");
        } else if (ae.getSource() == doorToDoorConversionButton) {
            System.out.println("conversion");
        } else if (ae.getSource() == endTurnButton) {
            gameLogic.endTurn();
            System.out.println(gameLogic.getNumberOfTurns());
        } else if (ae.getSource() == openingMenuViewButton) {
            gameFrame.changeViewPanel(PanelKeys.OPENING_MENU_VIEW);
        }
    }

}
