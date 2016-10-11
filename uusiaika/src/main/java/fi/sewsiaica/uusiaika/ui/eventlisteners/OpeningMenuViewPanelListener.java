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
import fi.sewsiaica.uusiaika.ui.PanelNames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;

/**
 * The object of this class handles the events in the opening menu view.
 *
 * @author iah1016
 */
public class OpeningMenuViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final AbstractButton newGameButton;
    private final AbstractButton loadGameButton;
    private final AbstractButton settingsButton;
    private final AbstractButton hallOfFameButton;
    private final AbstractButton quitButton;

    /**
     * The constructor is given an array of five AbstractButtons, GameFrame, and
     * GameLogic as parameters.
     *
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param buttons The buttons are as follows: [0] new game, [1] load a game,
     * [2] view settings, [3] view the hall of fame, [4] quit the game.
     */
    public OpeningMenuViewPanelListener(GameFrame frame, GameLogic gameLogic,
            AbstractButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.newGameButton = buttons[0];
        this.loadGameButton = buttons[1];
        this.settingsButton = buttons[2];
        this.hallOfFameButton = buttons[3];
        this.quitButton = buttons[4];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == newGameButton) {
            gameFrame.changeViewPanel(PanelNames.NEW_GAME_VIEW);
        } else if (ae.getSource() == loadGameButton) {
            System.out.println("Load game not yet implemented.");
        } else if (ae.getSource() == settingsButton) {
            System.out.println("Settings not yet implemented.");
        } else if (ae.getSource() == hallOfFameButton) {
            System.out.println("Hall of Fame not yet implemented.");
        } else if (ae.getSource() == quitButton) {
            System.exit(0);
        }
    }

}
