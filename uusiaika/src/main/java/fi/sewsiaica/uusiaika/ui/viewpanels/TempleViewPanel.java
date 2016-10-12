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
import fi.sewsiaica.uusiaika.ui.viewpanellisteners.TempleViewPanelListener;
import fi.sewsiaica.uusiaika.ui.subpanels.InfoPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JPanel;

/**
 * This class extends AbstractViewPanel; its object displays the temple view.
 *
 * @author iah1016
 */
public class TempleViewPanel extends AbstractViewPanel {

    private final Dimension dimension;
    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
    private JPanel buttonPanel;
    private JPanel infoPanel;

    /**
     * Dimension, GameLogic, and GameFrame are given as parameters.
     *
     * @param dimension The dimensions of the panel.
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame GameFrame gives itself as parameter, so that the active
     * ViewPanel can be changed.
     */
    public TempleViewPanel(Dimension dimension, GameLogic gameLogic,
            GameFrame frame) {
        super();
        this.dimension = dimension;
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.setViewPanelSettings();
    }

    @Override
    public final void setViewPanelSettings() {
        this.setPreferredSize(dimension);
        this.setBackground(Color.WHITE);

        updateComponents();
    }

    private void updateComponents() {
        String[] textsForButtons = {"Preach to the congregation",
            "Offer soda to everyone",
            "Buy a one-way ticket to a paradise island",
            "Go back to the Map view", "End turn"};
        
        buttonPanel = super.getNewButtonPanel(textsForButtons);
        infoPanel = new InfoPanel(gameLogic);
        this.addSubPanelsToViewPanel();
    }

    @Override
    protected final ActionListener createActionListener(
            AbstractButton[] buttons) {
        return new TempleViewPanelListener(gameLogic, gameFrame, buttons);
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
    }
}
