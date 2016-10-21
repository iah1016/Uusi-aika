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
import fi.sewsiaica.uusiaika.ui.subpanels.*;
import fi.sewsiaica.uusiaika.ui.viewpanellisteners.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.AbstractButton;
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
    private final DialoguePanel dialoguePanel;
    private JPanel buttonPanel;
    private JPanel infoPanel;
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
    public TrainingCentreViewPanel(Dimension dimension, GameLogic gameLogic,
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

        buttonPanel = super.getNewButtonPanel(textsForButtons);
        infoPanel = new InfoPanel(gameLogic);
        darkPlaceHolderPanel = new JPanel();
        darkPlaceHolderPanel.setBackground(Color.decode("#33001a"));
        this.addSubPanelsToViewPanel();
    }

    private String[] createTextsForButtons(Map<String, String> language) {
        String[] textsForButtons = {
            language.get("applyForCharismaCourseButton"),
            language.get("applyForDebateCourseButton"),
            language.get("returnToMapViewButton"),
            language.get("endTurnButton")};
        return textsForButtons;
    }

    @Override
    protected final ActionListener createActionListener(
            AbstractButton[] buttons) {
        return new TrainingCentreViewPanelListener(gameLogic, gameFrame,
                dialoguePanel, buttons);
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(darkPlaceHolderPanel, BorderLayout.CENTER);
        this.add(dialoguePanel, BorderLayout.EAST);
    }

}
