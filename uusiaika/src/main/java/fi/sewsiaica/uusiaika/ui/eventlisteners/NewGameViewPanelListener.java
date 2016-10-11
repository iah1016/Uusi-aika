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
import java.io.FileNotFoundException;
import javax.swing.AbstractButton;
import javax.swing.JTextField;

/**
 * The object of this class handles the events in the new game creation view.
 *
 * @author iah1016
 */
public class NewGameViewPanelListener implements ActionListener {

    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
    private final JTextField textFieldPlayerName;
    private final JTextField textFieldSectName;
    private final AbstractButton createGameButton;

    /**
     * The constructor is given two JTextFields, an array of one AbstractButton,
     * GameFrame, and GameLogic as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param textFieldPlayerName The player name given by the player.
     * @param textFieldSectName The sect name given by the player.
     * @param buttons [0] createGameButton creates a new active game.
     */
    public NewGameViewPanelListener(GameLogic gameLogic, GameFrame frame,
            JTextField textFieldPlayerName, JTextField textFieldSectName,
            AbstractButton[] buttons) {
        this.gameLogic = gameLogic;
        this.gameFrame = frame;
        this.textFieldPlayerName = textFieldPlayerName;
        this.textFieldSectName = textFieldSectName;
        this.createGameButton = buttons[0];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String[] names = new String[2];

        names[0] = textFieldPlayerName.getText();
        names[1] = textFieldSectName.getText();
        // Implement the config file enquiry.

        if (ae.getSource() == createGameButton) {
            try {
                gameLogic.newGame(names, "", "", "");
                gameFrame.changeViewPanel(PanelNames.MAP_VIEW);
            } catch (FileNotFoundException e) {
                System.out.println("ei natsaa");
            }
        }
    }

}
