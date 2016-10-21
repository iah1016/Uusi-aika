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
package fi.sewsiaica.uusiaika.ui.viewpanellisteners;

import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.ui.GameFrame;
import fi.sewsiaica.uusiaika.ui.PanelNames;
import fi.sewsiaica.uusiaika.ui.subpanels.DialoguePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;

/**
 * The object of this class handles the events in the temple view.
 *
 * @author iah1016
 */
public class TempleViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final DialoguePanel dialoguePanel;
    private final AbstractButton preachButton;
    private final AbstractButton offerSodaButton;
    private final AbstractButton buyTicketButton;
    private final AbstractButton returnToMapViewButton;
    private final AbstractButton endTurnButton;

    /**
     * The constructor is given an array of five AbstractButtons, GameFrame,
     * GameLogic, and DialoguePanel as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param dialoguePanel Displays the output and the dialogue of the game.
     * @param buttons Buttons 0-2 call GameLogic's templeActions method with the
     * option to preach, offer soda to everyone, or buy a one-way ticket
     * respectively. The latter two will end the game if the conditions are met.
     * [3] changes the active view to the map view and [4] ends the turn.
     */
    public TempleViewPanelListener(GameLogic gameLogic, GameFrame frame,
            DialoguePanel dialoguePanel, AbstractButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.dialoguePanel = dialoguePanel;
        this.preachButton = buttons[0];
        this.offerSodaButton = buttons[1];
        this.buyTicketButton = buttons[2];
        this.returnToMapViewButton = buttons[3];
        this.endTurnButton = buttons[4];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == returnToMapViewButton) {
            dialoguePanel.resetText();
            gameFrame.changeViewPanel(PanelNames.MAP_VIEW);
        } else if (ae.getSource() == preachButton) {
            preachSelected();
        } else if (ae.getSource() == offerSodaButton) {
            offerSodaSelected();
        } else if (ae.getSource() == buyTicketButton) {
            buyTicketSelected();
        } else if (ae.getSource() == endTurnButton) {
            if (gameLogic.endTurn()) {
                updateView();
            } else {
                gameLogic.endGame(1);
                gameFrame.changeViewPanel(PanelNames.GAME_OVER_VIEW);
            }
        }
    }

    private void updateView() {
        gameFrame.changeViewPanel(PanelNames.TEMPLE_VIEW);
    }

    private void preachSelected() {
        if (gameLogic.templeActions('a')) {
            dialoguePanel.showText("Praise the Holy Fish! "
                    + "Your performance was a success. "
                    + "Everyone is a little less sceptical now."
                    + "\n----");
        } else {
            dialoguePanel.showText("A solid perfomance.\n"
                    + "Too bad there was no one here to hear it!"
                    + "\n----");
        }
        updateView();
    }

    private void offerSodaSelected() {
        if (gameLogic.templeActions('b')) {
            gameLogic.endGame(2);
            gameFrame.changeViewPanel(PanelNames.GAME_OVER_VIEW);
        } else {
            dialoguePanel.showText("You lack charisma to pull this off."
                    + "\n----");
            updateView();
        }
    }

    private void buyTicketSelected() {
        if (gameLogic.templeActions('c')) {
            gameLogic.endGame(3);
            gameFrame.changeViewPanel(PanelNames.GAME_OVER_VIEW);
        } else {
            dialoguePanel.showText("There's not enough money on the account."
                    + "\n----");
            updateView();
        }
    }
}
