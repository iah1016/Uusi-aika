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
package fi.sewsiaica.uusiaika.ui.viewpanels;

import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.ui.GameFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class extends JPanel; All the other views of the ActiveGame are
 * reachable from the MapView.
 *
 * @author iah1016
 */
public class MapViewPanel extends JPanel {

    private final Dimension dimension;
    private final GameLogic gameLogic;

    /**
     * Temporarily a public object variable.
     */
    GameFrame gameFrame;

    /**
     * Dimension, GameLogic, and GameFrame are given as parameters. GameFrame is
     * temporarily a public object variable.
     *
     * @param dimension The dimensions of the panel.
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame GameFrame gives itself as parameter, so that the active
     * ViewPanel can be changed.
     */
    public MapViewPanel(Dimension dimension, GameLogic gameLogic,
            GameFrame frame) {
        super();
        this.dimension = dimension;
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.setPreferredSize(dimension);
        this.setBackground(Color.WHITE);
        addComponents();
    }

    private void addComponents() {
        JButton templeButton = new JButton("Go to the temple");
        add(templeButton);
        JButton trCentreButton = new JButton("Go to the training centre");
        add(trCentreButton);
        JButton conversionButton = new JButton("Door-to-door conversion");
        add(conversionButton);
        JButton endTurnButton = new JButton("End turn");
        add(endTurnButton);
        JButton openingViewButton = new JButton("Go back to main menu"
                + " (ends the game)");
        add(openingViewButton);
    }
}
