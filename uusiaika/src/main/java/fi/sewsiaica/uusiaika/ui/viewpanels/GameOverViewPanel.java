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
import fi.sewsiaica.uusiaika.ui.viewpanellisteners.GameOverViewPanelListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

/**
 * This class extends AbstractViewPanel; its object displays the game over view.
 *
 * @author iah1016
 */
public class GameOverViewPanel extends AbstractViewPanel {

    private final Dimension dimension;
    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
    private JPanel endMessagePanel;
    private JPanel buttonPanel;
    private final int endCondition;
    private final int finalScore;

    /**
     * Dimension, GameLogic, and GameFrame are given as parameters.
     *
     * @param dimension The dimensions of the panel.
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame GameFrame gives itself as parameter, so that the active
     * ViewPanel can be changed.
     * @param endCondition The end condition are: [0] the game has not ended,
     * [1] the maximum amount of turns has been reached, [2] the death cult
     * ending, [3] the paradise island ending.
     * @param finalScore The final score.
     */
    public GameOverViewPanel(Dimension dimension, GameLogic gameLogic,
            GameFrame frame, int endCondition, int finalScore) {
        super();
        this.dimension = dimension;
        this.gameLogic = gameLogic;
        this.gameFrame = frame;
        this.endCondition = endCondition;
        this.finalScore = finalScore;
        this.updateComponents();
    }

    @Override
    public void updateComponents() {
        String[] textsForButtons = {"Show the Hall of Fame",
            "Go back to the Main menu"};

        endMessagePanel = createEndMessageTextPanePanel();
        buttonPanel = super.getNewButtonPanel(textsForButtons);
        this.addSubPanelsToViewPanel();
    }

    @Override
    protected final ActionListener createActionListener(
            AbstractButton[] buttons) {
        return new GameOverViewPanelListener(gameLogic, gameFrame, buttons);
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        this.setLayout(new BorderLayout());
        this.add(endMessagePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.NORTH);
    }

    private JPanel createEndMessageTextPanePanel() {
        JPanel endMsgPanel = new JPanel();
        JEditorPane endMessageTextPane = new JEditorPane();
        endMessageTextPane.setEditable(false);
        endMessageTextPane.setText(pickEndMessage());
        endMessageTextPane.setPreferredSize(new Dimension(600, 400));
        endMessageTextPane.setBackground(Color.decode("#ebebe0"));

        endMsgPanel.setBackground(Color.decode("#a3c2c2"));
        endMsgPanel.add(endMessageTextPane);
        return endMsgPanel;
    }

    private String pickEndMessage() {
        String scoreString = "Your final score is: " + finalScore + ".";
        switch (endCondition) {
            case 1:
                return "You have reached the end without success. "
                        + scoreString;
            case 2:
                return "With you guidance, your flock will "
                        + "take the daring step to ascend to the next level. "
                        + scoreString;
            case 3:
                return "A one-way ticket to Paradise (some obscure "
                        + "island in the western Pacific) and only you are "
                        + "going.\n"
                        + "You have taught your flock well and now they can "
                        + "manage themselves.\nYou will take a reasonable "
                        + "reward of 100 percent of the Sect's balance with "
                        + "you. " + scoreString;
            default:
                return "Game over " + scoreString;
        }
    }
}
