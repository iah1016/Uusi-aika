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
package fi.sewsiaica.uusiaika.ui.subpanels;

import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.ui.subpanellisteners.VillagerListPanelListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/**
 * VillagerListPanel shows all the villagers in a list.
 *
 * @author iah1016
 */
public class VillagerListPanel extends AbstractSubPanel {

    private final GameLogic gameLogic;

    /**
     * The class gets all the information from ActiveGame via GameLogic. The
     * latter is given as a parameter to the constructor.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     */
    public VillagerListPanel(GameLogic gameLogic) {
        super(gameLogic);
        this.gameLogic = gameLogic;
        addContentOnlyIfActiveGameIsNotNull();
    }

    @Override
    protected void addContents() {
        Object[] allVillagers
                = gameLogic.getActiveGame().getVillagers().toArray();

        this.setLayout(new BorderLayout());
        this.addListScroller(allVillagers);
        this.addInfoMessage();
    }

    private void addListScroller(Object[] allVillagers) {
        JList list = new JList(allVillagers);

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.getSelectionModel().addListSelectionListener(
                new VillagerListPanelListener(gameLogic));
        applyJListSettings(list);

        JScrollPane listScroller = new JScrollPane(list);
        
        listScroller.setPreferredSize(new Dimension(180, 300));
        add(listScroller, BorderLayout.CENTER);
    }

    private void addInfoMessage() {
        JTextArea infoMessage = new JTextArea(10, 1);
        infoMessage.setText("Choose target villagers"
                + "\nfor door-to-door conversion.");

        applyInfoMessageSettings(infoMessage);
        add(infoMessage, BorderLayout.SOUTH);
    }

    private void applyJListSettings(JList list) {
        list.setLayoutOrientation(JList.VERTICAL);
        list.setBackground(Color.decode("#d1d1e0"));
    }
    
    private void applyInfoMessageSettings(JTextArea infoMessage) {
        infoMessage.setEditable(false);
        infoMessage.setBackground(Color.decode("#a3c2c2"));
        Font font = infoMessage.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        infoMessage.setFont(boldFont);
    }
}
