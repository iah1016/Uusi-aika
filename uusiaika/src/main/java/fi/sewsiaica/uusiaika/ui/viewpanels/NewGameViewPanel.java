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
import fi.sewsiaica.uusiaika.ui.eventlisteners.NewGameViewPanelListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class extends AbstractViewPanel; its object displays the creation of a
 * new game.
 *
 * @author iah1016
 */
public class NewGameViewPanel extends AbstractViewPanel {

    private final Dimension dimension;
    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
    private JPanel buttonPanel;
    private JPanel textFieldPanel;

    /**
     * Dimension, GameLogic, and GameFrame are given as parameters.
     *
     * @param dimension The dimensions of the panel.
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame GameFrame gives itself as parameter, so that the active
     * ViewPanel can be changed.
     */
    public NewGameViewPanel(Dimension dimension, GameLogic gameLogic,
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
        this.setBackground(Color.BLACK);

        updateComponents();
    }
    
    private void updateComponents() {
        this.textFieldPanel = new JPanel();

        AbstractButton[] buttons = this.createButtons();
        ActionListener actionListener = this.createActionListener(buttons);

        buttonPanel = super.createButtonPanel(buttons, actionListener);
        addSubPanelsToViewPanel();
    }

    @Override
    protected final AbstractButton[] createButtons() {
        AbstractButton[] buttons = new JButton[1];
        buttons[0] = new JButton("Create a new game");
        return buttons;
    }

    @Override
    protected final ActionListener createActionListener(
            AbstractButton[] buttons) {
        return new NewGameViewPanelListener(gameLogic, gameFrame,
                createJTextField("Player"), createJTextField("Sect"), buttons);
    }

    private JTextField createJTextField(String text) {
        JTextField textField = new JTextField(18);
        textField.setText(text);
        textField.setForeground(Color.BLUE);
        textFieldPanel.add(textField);
        return textField;
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);
        add(textFieldPanel, BorderLayout.LINE_START);
    }

}
