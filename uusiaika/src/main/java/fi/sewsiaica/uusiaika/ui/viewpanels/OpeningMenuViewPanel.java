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
import fi.sewsiaica.uusiaika.ui.eventlisteners.OpeningMenuViewPanelListener;
import java.awt.*;
import javax.swing.*;

/**
 * This class extends JPanel; its object displays the opening menu of the game.
 *
 * @author iah1016
 */
public class OpeningMenuViewPanel extends JPanel {

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
    public OpeningMenuViewPanel(Dimension dimension, GameLogic gameLogic,
            GameFrame frame) {
//        super(new BorderLayout());
        super();
        this.gameLogic = gameLogic;
        this.gameFrame = frame;
        this.dimension = dimension;
        this.setPreferredSize(dimension);
        this.setBackground(Color.BLACK);
        addComponents();
    }

    private JButton[] createButtons() {
        JButton[] buttons = new JButton[5];
        buttons[0] = new JButton("Start a new Game");
        buttons[1] = new JButton("Load a game");
        buttons[2] = new JButton("Settings");
        buttons[3] = new JButton("Hall of fame");
        buttons[4] = new JButton("Quit");
        return buttons;
    }

    private void addComponents() {
        JButton[] buttons = createButtons();
        OpeningMenuViewPanelListener openingMenuViewPanelListener
                = new OpeningMenuViewPanelListener(
                        gameFrame, gameLogic, buttons);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(openingMenuViewPanelListener);
            JButton currentButton = buttons[i];
            add(currentButton);
        }
    }

}
