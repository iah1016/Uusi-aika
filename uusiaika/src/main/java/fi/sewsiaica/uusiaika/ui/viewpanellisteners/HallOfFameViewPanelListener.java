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
 * The object of this class handles the events in the Hall of Fame view.
 *
 * @author iah1016
 */
public class HallOfFameViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final AbstractButton openingMenuViewButton;

    /**
     * The constructor is given an array of one AbstractButton, GameFrame, and
     * GameLogic as parameters.
     *
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param buttons The button: [0] go back to the opening menu.
     */
    public HallOfFameViewPanelListener(GameFrame frame, GameLogic gameLogic,
            AbstractButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.openingMenuViewButton = buttons[0];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == openingMenuViewButton) {
            gameFrame.changeViewPanel(PanelNames.OPENING_MENU_VIEW);
        }
    }

}
