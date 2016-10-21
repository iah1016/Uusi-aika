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
import fi.sewsiaica.uusiaika.ui.subpanels.DialoguePanel;
import fi.sewsiaica.uusiaika.ui.viewpanellisteners.NewGameViewPanelListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.AbstractButton;
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
    private final DialoguePanel dialoguePanel;
    private JPanel buttonPanel;
    private JPanel textFieldPanel;
    private JPanel darkPlaceHolderPanel;

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
        this.dialoguePanel = new DialoguePanel();
        this.updateComponents();
    }

    @Override
    public final void updateComponents() {
        Map<String, String> language = gameLogic.getActiveLanguage();
        String[] textsForButtons = createTextsForButtons(language);

        textFieldPanel = new JPanel();
        applyTextFieldPanelSettings(textFieldPanel);
        // The method that creates JTextFields also add them to textFieldPanel.
        buttonPanel = super.getNewButtonPanel(textsForButtons);
        darkPlaceHolderPanel = new JPanel();
        darkPlaceHolderPanel.setBackground(Color.decode("#33001a"));
        this.addSubPanelsToViewPanel();
    }

    private String[] createTextsForButtons(Map<String, String> language) {
        String[] textsForButtons = {
            language.get("createGameButton"),
            language.get("configValuesFileButton"),
            language.get("villagerNamesFileButton"),
            language.get("villagerProfsFileButton"),
            language.get("openingMenuViewButton")};
        return textsForButtons;
    }

    private void applyTextFieldPanelSettings(JPanel jPanel) {
        jPanel.setBackground(Color.decode("#a3c2c2"));
    }

    @Override
    protected final ActionListener createActionListener(
            AbstractButton[] buttons) {
        JTextField playerJText
                = super.createJTextField(textFieldPanel, "Player", 22);
        JTextField sectJText
                = super.createJTextField(textFieldPanel, "Sect", 25);

        return new NewGameViewPanelListener(gameLogic, gameFrame,
                playerJText, sectJText, dialoguePanel, buttons);
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(textFieldPanel, BorderLayout.NORTH);
        this.add(darkPlaceHolderPanel, BorderLayout.CENTER);
        this.add(dialoguePanel, BorderLayout.EAST);
    }

}
