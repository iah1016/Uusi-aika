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
import fi.sewsiaica.uusiaika.ui.eventlisteners.TrainingCentreViewPanelListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class extends AbstractViewPanel; its object displays the training centre
 * view.
 *
 * @author iah1016
 */
public class TrainingCentreViewPanel extends AbstractViewPanel {

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
    public TrainingCentreViewPanel(Dimension dimension, GameLogic gameLogic,
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
        AbstractButton[] buttons = this.createButtons();
        ActionListener actionListener = this.createActionListener(buttons);

        buttonPanel = super.createButtonPanel(buttons, actionListener);
        infoPanel = createGameInfoPanel(gameLogic);
        addSubPanelsToViewPanel();
    }

    @Override
    protected final AbstractButton[] createButtons() {
        AbstractButton[] buttons = new JButton[4];
        buttons[0] = new JButton("Apply for a charisma course");
        buttons[1] = new JButton("Apply for a debate course");
        buttons[2] = new JButton("Go back to the Map view");
        buttons[3] = new JButton("End turn");
        return buttons;
    }

    @Override
    protected final ActionListener createActionListener(
            AbstractButton[] buttons) {
        return new TrainingCentreViewPanelListener(gameLogic, gameFrame,
                buttons);
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        setLayout(new BorderLayout());
        add(infoPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

}
