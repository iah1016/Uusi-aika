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
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class extends AbstractViewPanel; its object displays the opening menu of
 * the game.
 *
 * @author iah1016
 */
public class OpeningMenuViewPanel extends AbstractViewPanel {

    private final Dimension dimension;
    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
    private JPanel buttonPanel;

    /**
     * Dimension, GameLogic, and GameFrame are given as parameters.
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
        this.setViewPanelSettings();
    }

    @Override
    public final void setViewPanelSettings() {
        this.setPreferredSize(dimension);
        this.setBackground(Color.BLACK);

        updateComponents();
    }
    
    private void updateComponents() {
        AbstractButton[] buttons = this.createButtons();
        ActionListener actionListener = this.createActionListener(buttons);

        buttonPanel = super.createButtonPanel(buttons, actionListener);
        addSubPanelsToViewPanel();
    }

    @Override
    protected final AbstractButton[] createButtons() {
        AbstractButton[] buttons = new JButton[5];
        buttons[0] = new JButton("Start a new Game");
        buttons[1] = new JButton("Load a game");
        buttons[2] = new JButton("Settings");
        buttons[3] = new JButton("Hall of fame");
        buttons[4] = new JButton("Quit");
        return buttons;
    }

    @Override
    protected final ActionListener createActionListener(
            AbstractButton[] buttons) {
        return new OpeningMenuViewPanelListener(gameFrame, gameLogic, buttons);
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);
    }

}
