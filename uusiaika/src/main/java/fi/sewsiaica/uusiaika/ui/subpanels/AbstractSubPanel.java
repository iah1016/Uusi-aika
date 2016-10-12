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

/**
 * This abstract class for SubPanels implements the ActiveGame null checker, in
 * other words, the sub panel will not add content to itself if ActiveGame is
 * null.
 *
 * @author iah1016
 */
public abstract class AbstractSubPanel extends JPanel {

    private final ActiveGame activeGame;

    /**
     * The class gets all the information from ActiveGame via GameLogic. The
     * latter is given as a parameter to the constructor.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     */
    public AbstractSubPanel(GameLogic gameLogic) {
        super();
        this.activeGame = gameLogic.getActiveGame();
    }

    /**
     * This method checks first that the ActiveGame object is not null and then
     * calls the addContents method.
     *
     */
    protected void addContentOnlyIfActiveGameIsNotNull() {
        if (activeGameIsNotNull()) {
            addContents();
        }
    }

    private boolean activeGameIsNotNull() {
        return activeGame != null;
    }

    /**
     * This method adds the content to the sub panel.
     *
     */
    protected abstract void addContents();
}
