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
    private final JTextArea infoMessage;
    private String showInfoMessage;

    /**
     * The class gets all the information from ActiveGame via GameLogic. The
     * latter is given as a parameter to the constructor.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param showInfoMessage The info message displayed in the JTextArea.
     */
    public VillagerListPanel(GameLogic gameLogic, String showInfoMessage) {
        super(gameLogic);
        this.gameLogic = gameLogic;
        this.showInfoMessage = showInfoMessage;
        infoMessage = new JTextArea(10, 1);
        super.addContentOnlyIfActiveGameIsNotNull();
    }

    @Override
    protected void addContents() {
        Object[] allVillagers
                = gameLogic.getActiveGame().getVillagers().toArray();

        this.setLayout(new BorderLayout());
        this.addListScroller(allVillagers);
        infoMessage.setText(showInfoMessage);
        this.applyInfoMessageSettings();
        add(infoMessage, BorderLayout.SOUTH);
    }

    private void addListScroller(Object[] allVillagers) {
        JList list = new JList(allVillagers);
        applyJListSettings(list);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(200, 300));
        add(listScroller, BorderLayout.CENTER);
    }

    /**
     * Displays the output text in the JTextArea.
     *
     * @param outputText The output text.
     */
    public void showText(String outputText) {
        showInfoMessage = outputText;
        infoMessage.setText(showInfoMessage);
    }

    private void applyJListSettings(JList list) {
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setCellRenderer(new CustomCellRendererForVillagerListPanel(
                gameLogic.getActiveGame().getVillagers()));
        list.getSelectionModel().addListSelectionListener(
                new VillagerListPanelListener(gameLogic));

        list.setLayoutOrientation(JList.VERTICAL);
        list.setBackground(Color.decode("#d1d1e0"));
    }

    private void applyInfoMessageSettings() {
        infoMessage.setEditable(false);
        infoMessage.setWrapStyleWord(true);
        infoMessage.setLineWrap(true);
        infoMessage.setForeground(Color.decode("#c3d5d5"));
        infoMessage.setBackground(Color.decode("#1f2e2e"));
        Font font = infoMessage.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        infoMessage.setFont(boldFont);
    }
}
