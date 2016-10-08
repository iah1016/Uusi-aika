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
    private final JButton trCentreButton;
    private final JButton conversionButton;
    private final JButton endTurnButton;
    private final JButton openingMenuViewButton;

    /**
     * The constructor is given five JButtons, GameFrame, and GameLogic as
     * parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param templeButton The current ViewPanel changes to TEMPLE_VIEW.
     * @param trCentreButton The current ViewPanel changes to
     * TRAININGCENTRE_VIEW.
     * @param conversionButton The current ViewPanel changes to DOORTODOOR_VIEW.
     * @param endTurnButton Ends the turn via GameLogic.
     * @param openingMenuViewButton The current ViewPanel changes to
     * OPENING_MENU_VIEW.
     */
    public MapViewPanelListener(GameLogic gameLogic, GameFrame frame,
            JButton templeButton, JButton trCentreButton,
            JButton conversionButton, JButton endTurnButton,
            JButton openingMenuViewButton) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.templeButton = templeButton;
        this.trCentreButton = trCentreButton;
        this.conversionButton = conversionButton;
        this.endTurnButton = endTurnButton;
        this.openingMenuViewButton = openingMenuViewButton;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == templeButton) {
            System.out.println("temppeli");
        } else if (ae.getSource() == trCentreButton) {
            System.out.println("tr");
        } else if (ae.getSource() == conversionButton) {
            System.out.println("conversion");
        } else if (ae.getSource() == endTurnButton) {
            gameLogic.endTurn();
            System.out.println(gameLogic.getNumberOfTurns());
        } else if (ae.getSource() == openingMenuViewButton) {
            gameFrame.changeViewPanel(PanelKeys.OPENING_MENU_VIEW);
        }
    }

}
