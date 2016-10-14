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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;

/**
 * The object of this class handles the events in the training centre view.
 *
 * @author iah1016
 */
public class TrainingCentreViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final AbstractButton applyForCharismaCourseButton;
    private final AbstractButton applyForDebateCourseButton;
    private final AbstractButton returnToMapViewButton;
    private final AbstractButton endTurnButton;

    /**
     * The constructor is given an array of four AbstractButtons, GameFrame, and
     * GameLogic as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param buttons Button [0] is for applying for a charisma course, [1] for
     * applying for a debate course, [2] returns to the map view, and [3] ends
     * the turn.
     */
    public TrainingCentreViewPanelListener(GameLogic gameLogic, GameFrame frame,
            AbstractButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.applyForCharismaCourseButton = buttons[0];
        this.applyForDebateCourseButton = buttons[1];
        this.returnToMapViewButton = buttons[2];
        this.endTurnButton = buttons[3];
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == returnToMapViewButton) {
            gameFrame.changeViewPanel(PanelNames.MAP_VIEW);
        } else {
            if (ae.getSource() == applyForCharismaCourseButton) {
                charismaCourseSelected();
            } else if (ae.getSource() == applyForDebateCourseButton) {
                debateCourseSelected();
            } else if (ae.getSource() == endTurnButton) {
                if (gameLogic.endTurn()) {
                    updateView();
                } else {
                    gameLogic.getActiveGame().setGameEndingCondition(1);
                    gameFrame.changeViewPanel(PanelNames.GAME_OVER_VIEW);
                }
            }
        }
    }

    private void updateView() {
        gameFrame.changeViewPanel(PanelNames.TRAININGCENTRE_VIEW);
    }

    private void charismaCourseSelected() {
        if (gameLogic.trainingCentreActions('a')) {
            System.out.println("Your charisma level is now: "
                    + gameLogic.getActiveGame().getPlayer().getCharisma());
        } else {
            System.out.println("No success.");
        }
        updateView();
    }

    private void debateCourseSelected() {
        if (gameLogic.trainingCentreActions('b')) {
            System.out.println("Your argumentation skill level is now: "
                    + gameLogic.getActiveGame()
                            .getPlayer().getArgumentationSkills());
        } else {
            System.out.println("No success.");
        }
        updateView();
    }

}
