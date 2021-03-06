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
import fi.sewsiaica.uusiaika.ui.viewpanellisteners.MapViewPanelListener;
import fi.sewsiaica.uusiaika.ui.subpanels.InfoPanel;
import fi.sewsiaica.uusiaika.ui.subpanels.VillagerListPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JPanel;

/**
 * This class extends AbstractViewPanel; All the other views of the ActiveGame
 * are reachable from the MapView.
 *
 * @author iah1016
 */
public class MapViewPanel extends AbstractViewPanel {

    private final Dimension dimension;
    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JPanel darkPlaceHolderPanel;
    private VillagerListPanel villagerListPanel;

    /**
     * Dimension, GameLogic, and GameFrame are given as parameters.
     *
     * @param dimension The dimensions of the panel.
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame GameFrame gives itself as parameter, so that the active
     * ViewPanel can be changed.
     */
    public MapViewPanel(Dimension dimension, GameLogic gameLogic,
            GameFrame frame) {
        super();
        this.dimension = dimension;
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.updateComponents();
    }

    @Override
    public final void updateComponents() {
        Map<String, String> language = gameLogic.getActiveLanguage();
        String[] textsForButtons = createTextsForButtons(language);
        String showInfoMessage = language.get("mapViewInfo1")
                + "\n\n" + language.get("mapViewInfo2");
        
        villagerListPanel = new VillagerListPanel(gameLogic, showInfoMessage);
        buttonPanel = super.getNewButtonPanel(textsForButtons);
        infoPanel = new InfoPanel(gameLogic);
        darkPlaceHolderPanel = new JPanel();
        darkPlaceHolderPanel.setBackground(Color.decode("#33001a"));
        this.addSubPanelsToViewPanel();
    }

    private String[] createTextsForButtons(Map<String, String> language) {
        String[] textsForButtons = {
            language.get("templeButton"),
            language.get("trainingCentreButton"),
            language.get("doorToDoorConversionButton"),
            language.get("endTurnButton"),
            language.get("saveGameButton"),
            language.get("openingMenuViewButton2")};
        return textsForButtons;
    }

    @Override
    protected final ActionListener createActionListener(
            AbstractButton[] buttons) {
        return new MapViewPanelListener(gameLogic, gameFrame,
                villagerListPanel, buttons);
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(darkPlaceHolderPanel, BorderLayout.CENTER);
        this.add(villagerListPanel, BorderLayout.EAST);
    }

}
