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
import fi.sewsiaica.uusiaika.ui.viewpanellisteners.LanguageSettingsViewPanelListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JPanel;

/**
 * This class extends AbstractViewPanel; its object displays the language
 * settings menu view.
 *
 * @author iah1016
 */
public class LanguageSettingsViewPanel extends AbstractViewPanel {

    private final Dimension dimension;
    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
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
    public LanguageSettingsViewPanel(Dimension dimension, GameLogic gameLogic,
            GameFrame frame) {
        this.dimension = dimension;
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.updateComponents();
    }

    @Override
    public void updateComponents() {
        Map<String, String> language = gameLogic.getActiveLanguage();
        List<String> langNames = gameLogic.getNamesOfLanguages();

        String[] textsForButtons = new String[langNames.size() + 2];
        for (int i = 0; i < langNames.size(); i++) {
            textsForButtons[i] = langNames.get(i);
        }
        textsForButtons[langNames.size()]
                = language.get("loadLangFromFileButton");
        textsForButtons[langNames.size() + 1]
                = language.get("openingMenuViewButton");

        buttonPanel = super.getNewButtonPanel(textsForButtons);
        this.addSubPanelsToViewPanel();
    }

    @Override
    protected ActionListener createActionListener(AbstractButton[] buttons) {
        return new LanguageSettingsViewPanelListener(gameFrame, gameLogic,
                buttons);
    }

    @Override
    protected void addSubPanelsToViewPanel() {
        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.CENTER);
    }

}
