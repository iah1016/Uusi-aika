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
package fi.sewsiaica.uusiaika.ui;

import fi.sewsiaica.uusiaika.ui.viewpanels.*;
import fi.sewsiaica.uusiaika.logic.GameLogic;
import java.awt.Dimension;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class extends the JFrame class and handles the organisation of the GUI.
 *
 * @author iah1016
 */
public class GameFrame extends JFrame {

    private final Map<PanelKeys, JPanel> viewPanelMap;
    private final GameLogic gameLogic;
    private final String[] args;
    private final Dimension dimension;

    /**
     * The constructor gets the main logic class and the String array of
     * arguments as parameters and sets them as its object variables. It also
     * creates a new instance of the Dimension class and a new EnumMap of all
     * the main panels in the game. Private method frameSettings contains all of
     * this frame's settings, other than setVisible(true), which is called from
     * GUIRunner.
     *
     * @param title The title of the game, shown in the title bar.
     * @param gameLogic The core class of the game logic.
     * @param args Not yet implemented.
     */
    GameFrame(String title, GameLogic gameLogic, String[] args) {
        super(title);
        this.gameLogic = gameLogic;
        this.args = args;
        this.dimension = new Dimension(800, 600);
        this.viewPanelMap = createViewPanelMap();
        frameSettings();
    }

    private void frameSettings() {
        this.setPreferredSize(dimension);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.changeViewPanel(PanelKeys.OPENING_MENU_VIEW);
    }

    private Map<PanelKeys, JPanel> createViewPanelMap() {
        Map<PanelKeys, JPanel> tempMap;
        tempMap = new EnumMap<>(PanelKeys.class);

        tempMap.put(PanelKeys.OPENING_MENU_VIEW,
                new OpeningMenuViewPanel(dimension, gameLogic, this));
        tempMap.put(PanelKeys.NEW_GAME_VIEW,
                new NewGameViewPanel(dimension, gameLogic, this));
        tempMap.put(PanelKeys.MAP_VIEW,
                new MapViewPanel(dimension, gameLogic, this));
        tempMap.put(PanelKeys.TEMPLE_VIEW,
                null);
        tempMap.put(PanelKeys.TRAININGCENTRE_VIEW,
                null);
        tempMap.put(PanelKeys.DOORTODOOR_VIEW,
                null);

        return tempMap;
    }

    /**
     * This method changes the visible viewPanel (JPanel subclass).
     *
     * @param keyForPanel The key is a String. Enum values are used to prevent
     * mistyping.
     */
    public void changeViewPanel(PanelKeys keyForPanel) {
        JPanel panel = viewPanelMap.get(keyForPanel);
        getContentPane().removeAll();
        getContentPane().add(panel);
        panel.revalidate();
        panel.repaint();
        pack();
        setVisible(true);
    }
}
