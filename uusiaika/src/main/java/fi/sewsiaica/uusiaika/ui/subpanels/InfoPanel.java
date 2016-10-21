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

import fi.sewsiaica.uusiaika.domain.Villager;
import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.logic.activegame.ActiveGame;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JTextPane;

/**
 * The info panel shows the following data: Turn number, Player name, Sect name,
 * Sect balance, and the number of members in the sect.
 *
 * @author iah1016
 */
public class InfoPanel extends AbstractSubPanel {

    private final GameLogic gameLogic;
    private Map<String, String> language;

    /**
     * The class gets all the information from ActiveGame via GameLogic. The
     * latter is given as a parameter to the constructor.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     */
    public InfoPanel(GameLogic gameLogic) {
        super(gameLogic);
        this.gameLogic = gameLogic;
        super.addContentOnlyIfActiveGameIsNotNull();
    }

    @Override
    protected void addContents() {
        JTextPane text = new JTextPane();
        text.setText(buildShownString());
        add(text);

        text.setForeground(Color.WHITE);
        text.setBackground(Color.decode("#52527a"));
        setBackground(Color.decode("#52527a"));
    }

    private String buildShownString() {
        language = gameLogic.getActiveLanguage();
        ActiveGame activeGame = gameLogic.getActiveGame();
        List<Villager> targetVillagers = activeGame.getTargetVillagers();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createActiveGameInfoString(activeGame));
        stringBuilder.append(createTargetVillagersInfoString(targetVillagers));

        return stringBuilder.toString();
    }

    private String createActiveGameInfoString(ActiveGame activeGame) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(language.get("turnInfoPanel"))
                .append(activeGame.getNumberOfTurns())
                .append("  ").append(activeGame.getPlayer().getName())
                .append("  ").append(activeGame.getSect().getName())
                .append("  ").append(language.get("balanceInfoPanel"))
                .append(activeGame.getSect().getBalance())
                .append("  ").append(language.get("membersInfoPanel"))
                .append(activeGame.getSect().getCongregation().size());
        
        return stringBuilder.toString();
    }

    private String createTargetVillagersInfoString(
            List<Villager> targetVillagers) {
        StringBuilder stringBuilder = new StringBuilder();

        if (!targetVillagers.isEmpty()) {
            int totalTargets = targetVillagers.size();
            String nextTarget = targetVillagers.get(0).toString();
            stringBuilder
                    .append("  ").append(language.get("currTargetInfoPanel"))
                    .append(nextTarget)
                    .append("  ").append(language.get("totalTargetsInfoPanel"))
                    .append(totalTargets);
        }
        return stringBuilder.toString();
    }
}
