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

import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.ui.GameFrame;
import fi.sewsiaica.uusiaika.ui.PanelNames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;

/**
 * The object of this class handles the events in the game over view.
 *
 * @author iah1016
 */
public class GameOverViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final AbstractButton showHallOfFameButton;
    private final AbstractButton openingMenuViewButton;

    /**
     * The constructor is given an array of two AbstractButtons, GameFrame, and
     * GameLogic as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param buttons The buttons: [0] Show the Hall of Fame, [1] Return to the
     * Main menu.
     */
    public GameOverViewPanelListener(GameLogic gameLogic, GameFrame frame,
            AbstractButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.showHallOfFameButton = buttons[0];
        this.openingMenuViewButton = buttons[1];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == showHallOfFameButton) {
            gameFrame.changeViewPanel(PanelNames.HALL_OF_FAME_VIEW);
        } else if (ae.getSource() == openingMenuViewButton) {
            gameFrame.changeViewPanel(PanelNames.OPENING_MENU_VIEW);
        }
    }

}
