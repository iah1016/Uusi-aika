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

import fi.sewsiaica.uusiaika.generaltools.GeneralTools;
import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.ui.GameFrame;
import fi.sewsiaica.uusiaika.ui.viewpanellisteners.HallOfFameViewPanelListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

/**
 * This class extends AbstractViewPanel; its object displays the Hall of Fame
 * view.
 *
 * @author iah1016
 */
public class HallOfFameViewPanel extends AbstractViewPanel {

    private final Dimension dimension;
    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
    private JPanel hallOfFamePanel;
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
    public HallOfFameViewPanel(Dimension dimension, GameLogic gameLogic,
            GameFrame frame) {
        this.dimension = dimension;
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.updateComponents();
    }

    @Override
    public void updateComponents() {
        String[] textsForButtons = {"Go back to main menu"};

        hallOfFamePanel = createHallOfFamePanePanel();
        buttonPanel = super.getNewButtonPanel(textsForButtons);
        this.addSubPanelsToViewPanel();
    }

    @Override
    protected ActionListener createActionListener(AbstractButton[] buttons) {
        return new HallOfFameViewPanelListener(gameFrame, gameLogic, buttons);
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        this.setLayout(new BorderLayout());
        this.add(hallOfFamePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.NORTH);
    }

    private JPanel createHallOfFamePanePanel() {
        JPanel hallOfFamePanel = new JPanel();
        JEditorPane hallOfFameTextPane = new JEditorPane();
        hallOfFameTextPane.setEditable(false);
        hallOfFameTextPane.setText(constructHallOfFameString());
        hallOfFameTextPane.setPreferredSize(new Dimension(760, 400));
        hallOfFameTextPane.setBackground(Color.decode("#ebebe0"));

        hallOfFamePanel.setBackground(Color.decode("#a3c2c2"));
        hallOfFamePanel.add(hallOfFameTextPane);
        return hallOfFamePanel;
    }

    private String constructHallOfFameString() {
        GeneralTools generalTools = new GeneralTools();

        return generalTools.convertStringListToString(
                gameLogic.getHallOfFameList());
    }
}
