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
    private int endCondition;

    /**
     * Dimension, GameLogic, and GameFrame are given as parameters. The end
     * condition are: [0] the game has not ended, [1] the maximum amount of
     * turns has been reached, [2] the death cult ending, [3] the paradise
     * island ending.
     *
     * @param dimension The dimensions of the panel.
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame GameFrame gives itself as parameter, so that the active
     * ViewPanel can be changed.
     */
    public GameOverViewPanel(Dimension dimension, GameLogic gameLogic,
            GameFrame frame) {
        super();
        this.dimension = dimension;
        this.gameLogic = gameLogic;
        this.gameFrame = frame;
        this.endCondition = 0;
        this.updateComponents();
    }

    @Override
    public void updateComponents() {
        String[] textsForButtons = {"Go back to the Main menu"};

        endMessagePanel = createEndMessageTextPanePanel();
        buttonPanel = super.getNewButtonPanel(textsForButtons);
        this.addSubPanelsToViewPanel();
        getEndCondition();
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

        endMsgPanel.add(endMessageTextPane);
        return endMsgPanel;
    }

    private String pickEndMessage() {
        //
        System.out.println("Game ending condition (GameOverViewPanel): "
                + endCondition);
        //
        switch (endCondition) {
            case 1:
                return "You have reached the end without success.";
            case 2:
                return "With you guidance, your flock will "
                        + "take the daring step to ascend to the next level.";
            case 3:
                return "A one-way ticket to Paradise (some obscure "
                        + "island in the western Pacific) and only you are "
                        + "going.\n"
                        + "You have taught your flock well and now they can "
                        + "manage themselves.\nYou will take a reasonable "
                        + "reward of 100 percent of the Sect's balance with "
                        + "you.";
            default:
                return "Game over";
        }
    }

    /**
     * This should update the ending condition but is not currently functioning.
     *
     * @return Returns the value from the active game or 0 if the active game is
     * null.
     */
    public int getEndCondition() {
        if (gameLogic.getActiveGame() != null) {
            return gameLogic.getActiveGame().getGameEndingCondition();
        }
        return 0;
    }
}
