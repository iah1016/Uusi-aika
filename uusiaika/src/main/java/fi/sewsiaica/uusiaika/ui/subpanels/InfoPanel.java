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
package fi.sewsiaica.uusiaika.ui.subpanels;

import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 * The info panel shows the following data: Turn number, Player name, Sect name,
 * Sect balance, and the number of members in the sect.
 *
 * @author iah1016
 */
public class InfoPanel extends JPanel {

    private final GameLogic gameLogic;

    /**
     * The class gets all the information from ActiveGame via GameLogic. The
     * latter is given as a parameter to the constructor.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     */
    public InfoPanel(GameLogic gameLogic) {
        super();
        this.gameLogic = gameLogic;
        showGameInfo();
    }

    private void showGameInfo() {
        ActiveGame activeGame = gameLogic.getActiveGame();

        if (activeGame != null) {
            System.out.println(activeGame.toString());

            JTextPane text = new JTextPane();
            text.setText(activeGame.toString());
            add(text);
        }
    }

}
