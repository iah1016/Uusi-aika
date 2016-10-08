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
package fi.sewsiaica.uusiaika.ui;

import fi.sewsiaica.uusiaika.logic.GameLogic;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This class extends the JFrame class and handles the organisation of the GUI.
 *
 * @author iah1016
 */
public class GameFrame extends JFrame {

    private final GameLogic gameLogic;
    private final String[] args;
    private final Dimension dimension;

    /**
     * The constructor gets the main logic class and the String array of
     * arguments as parameters and sets them as its object variables.
     *
     * @param title The title of the game, shown in the title bar.
     * @param gameLogic The core class of the game logic.
     * @param args Not yet implemented.
     */
    GameFrame(String title, GameLogic gameLogic, String[] args) {
        super(title);
        this.gameLogic = gameLogic;
        this.args = args;
        this.dimension = new Dimension(800, 600);
        constructFrame();
    }

    private void constructFrame() {
        setPreferredSize(dimension);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        openOpeningMenuPanel();
    }
    
    private void openOpeningMenuPanel() {
        OpeningMenuPanel opMP = new OpeningMenuPanel(dimension);
        add(opMP);
    }
}
