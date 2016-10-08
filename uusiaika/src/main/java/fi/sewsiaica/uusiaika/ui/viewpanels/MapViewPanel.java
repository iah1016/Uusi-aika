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
import fi.sewsiaica.uusiaika.ui.eventlisteners.MapViewPanelListener;
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
    private final GameFrame gameFrame;

    /**
     * Dimension, GameLogic, and GameFrame are given as parameters.
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

    private JButton[] createButtons() {
        JButton[] buttons = new JButton[5];
        buttons[0] = new JButton("Go to the temple");
        buttons[1] = new JButton("Go to the training centre");
        buttons[2] = new JButton("Door-to-door conversion");
        buttons[3] = new JButton("End turn");
        buttons[4] = new JButton("Go back to main menu (ends the game)");
        return buttons;
    }

    private void addComponents() {
        JButton[] buttons = createButtons();
        MapViewPanelListener mapViewPanelListener = new MapViewPanelListener(
                gameLogic, gameFrame, buttons);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(mapViewPanelListener);
            JButton currentButton = buttons[i];
            add(currentButton);
        }
    }
}
