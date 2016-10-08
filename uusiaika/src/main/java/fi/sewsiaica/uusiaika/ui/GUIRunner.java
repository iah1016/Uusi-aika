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

/**
 * This class implements the Runnable interface and creates a new GameFrame (a
 * JFrame subclass) instance in its run()-method.
 *
 * @author iah1016
 */
public class GUIRunner implements Runnable {

    private final GameLogic gameLogic;
    private final String[] args;
    private GameFrame frame;

    /**
     * The constructor gets the main logic class and the String array of
     * arguments as parameters and sets them as its object variables.
     *
     * @param gameLogic The core class of the game logic.
     * @param args Not yet implemented.
     */
    public GUIRunner(GameLogic gameLogic, String[] args) {
        this.gameLogic = gameLogic;
        this.args = args;
    }

    @Override
    public void run() {
        frame = new GameFrame("Uusi aika", gameLogic, args);
        frame.setVisible(true);
    }

}
