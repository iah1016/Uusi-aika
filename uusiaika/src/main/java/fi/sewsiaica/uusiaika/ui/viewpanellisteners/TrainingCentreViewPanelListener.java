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
import java.util.Map;
import javax.swing.AbstractButton;

/**
 * The object of this class handles the events in the training centre view.
 *
 * @author iah1016
 */
public class TrainingCentreViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final DialoguePanel dialoguePanel;
    private final AbstractButton applyForCharismaCourseButton;
    private final AbstractButton applyForDebateCourseButton;
    private final AbstractButton returnToMapViewButton;
    private final AbstractButton endTurnButton;
    private Map<String, String> language;

    /**
     * The constructor is given an array of four AbstractButtons, GameFrame,
     * GameLogic, and DialoguePanel as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param dialoguePanel Displays the output and the dialogue of the game.
     * @param buttons Button [0] is for applying for a charisma course, [1] for
     * applying for a debate course, [2] returns to the map view, and [3] ends
     * the turn.
     */
    public TrainingCentreViewPanelListener(GameLogic gameLogic, GameFrame frame,
            DialoguePanel dialoguePanel, AbstractButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.dialoguePanel = dialoguePanel;
        this.applyForCharismaCourseButton = buttons[0];
        this.applyForDebateCourseButton = buttons[1];
        this.returnToMapViewButton = buttons[2];
        this.endTurnButton = buttons[3];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        language = gameLogic.getActiveLanguage();

        if (ae.getSource() == returnToMapViewButton) {
            dialoguePanel.resetText();
            gameFrame.changeViewPanel(PanelNames.MAP_VIEW);
        } else {
            if (ae.getSource() == applyForCharismaCourseButton) {
                charismaCourseSelected();
            } else if (ae.getSource() == applyForDebateCourseButton) {
                debateCourseSelected();
            } else if (ae.getSource() == endTurnButton) {
                endTurnActionPerformed();
            }
        }
    }

    private void endTurnActionPerformed() {
        if (gameLogic.endTurn()) {
            updateView();
        } else {
            gameLogic.endGame(1);
            dialoguePanel.resetText();
            gameFrame.changeViewPanel(PanelNames.GAME_OVER_VIEW);
        }
    }

    private void updateView() {
        gameFrame.changeViewPanel(PanelNames.TRAININGCENTRE_VIEW);
    }

    private void charismaCourseSelected() {
        if (gameLogic.trainingCentreActions('a')) {
            dialoguePanel.showText(language.get("charismaCourseOK")
                    + gameLogic.getActiveGame().getPlayer().getCharisma()
                    + "\n----");
        } else {
            dialoguePanel.showText(language.get("charismaCourseFail")
                    + "\n----");
        }
        updateView();
    }

    private void debateCourseSelected() {
        if (gameLogic.trainingCentreActions('b')) {
            dialoguePanel.showText(language.get("debateCourseOK")
                    + gameLogic.getActiveGame()
                            .getPlayer().getArgumentationSkills()
                    + "\n----");
        } else {
            dialoguePanel.showText(language.get("debateCourseFail")
                    + "\n----");
        }
        updateView();
    }

}
