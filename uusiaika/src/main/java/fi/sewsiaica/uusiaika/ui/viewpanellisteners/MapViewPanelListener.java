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
import fi.sewsiaica.uusiaika.ui.subpanels.VillagerListPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The object of this class handles the events in the map view. It currently
 * implements the ActionListener interface, though this may change.
 *
 * @author iah1016
 */
public class MapViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final VillagerListPanel villagerListPanel;
    private final AbstractButton templeButton;
    private final AbstractButton trainingCentreButton;
    private final AbstractButton doorToDoorConversionButton;
    private final AbstractButton endTurnButton;
    private final AbstractButton saveGameButton;
    private final AbstractButton openingMenuViewButton;
    private final JFileChooser fileChooser;
    private Map<String, String> language;
    private File saveFile;

    /**
     * The constructor is given an array of six AbstractButtons, GameFrame,
     * GameLogic, and VillagerListPanel as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param villagerListPanel The panel that shows the output in this view
     * panel.
     * @param buttons The current ViewPanel changes to [0] TEMPLE_VIEW, [1]
     * TRAININGCENTRE_VIEW, [2] DOORTODOOR_VIEW (conversion), [3] Ends the turn
     * via GameLogic, [4] Saves the game via GameLogic, [5]
     * openingMenuViewButton The current ViewPanel changes to OPENING_MENU_VIEW,
     * thus ending the active game.
     */
    public MapViewPanelListener(GameLogic gameLogic, GameFrame frame,
            VillagerListPanel villagerListPanel, AbstractButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.villagerListPanel = villagerListPanel;
        this.templeButton = buttons[0];
        this.trainingCentreButton = buttons[1];
        this.doorToDoorConversionButton = buttons[2];
        this.endTurnButton = buttons[3];
        this.saveGameButton = buttons[4];
        this.openingMenuViewButton = buttons[5];
        this.fileChooser = new JFileChooser();
        this.language = gameLogic.getActiveLanguage();
        fileChooserSettings();
    }

    private void fileChooserSettings() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".txt files", "txt");
        fileChooser.setFileFilter(filter);

        fileChooser.setDialogTitle(language.get("saveFileLocationTitle"));

        String gameDir = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(gameDir));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == doorToDoorConversionButton) {
            goDoorToDoorIfTargetVillagerListIsNotEmpty();
        } else if (ae.getSource() == templeButton) {
            emptyTargetVillagerList();
            gameFrame.changeViewPanel(PanelNames.TEMPLE_VIEW);
        } else if (ae.getSource() == trainingCentreButton) {
            emptyTargetVillagerList();
            gameFrame.changeViewPanel(PanelNames.TRAININGCENTRE_VIEW);
        } else if (ae.getSource() == endTurnButton) {
            endTurnActionPerformed();
        } else if (ae.getSource() == saveGameButton) {
            saveGameSelected();
        } else if (ae.getSource() == openingMenuViewButton) {
            emptyTargetVillagerList();
            gameFrame.changeViewPanel(PanelNames.OPENING_MENU_VIEW);
        }
    }

    private void endTurnActionPerformed() {
        if (gameLogic.endTurn()) {
            gameFrame.changeViewPanel(PanelNames.MAP_VIEW);
        } else {
            gameLogic.endGame(1);
            gameFrame.changeViewPanel(PanelNames.GAME_OVER_VIEW);
        }
    }

    private void saveGameSelected() {
        if (fileChosenWithFileChooser()) {
            if (savingGameSucceeds()) {
                System.out.println("Saved as " + saveFile.getName() + ".");
            } else {
                System.out.println("Unable to save to the selected "
                        + "location.");
            }
        }
        gameFrame.changeViewPanel(PanelNames.MAP_VIEW);
    }

    private boolean fileChosenWithFileChooser() {
        int returnVal = fileChooser.showSaveDialog(gameFrame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            saveFile = fileChooser.getSelectedFile();
            villagerListPanel.showText(saveFile.getName());
            return true;
        }
        return false;
    }

    private boolean savingGameSucceeds() {
        return gameLogic.saveGame(saveFile);
    }

    private void goDoorToDoorIfTargetVillagerListIsNotEmpty() {
        int targetVillagerListSize
                = gameLogic.getActiveGame().getTargetVillagers().size();

        if (targetVillagerListSize != 0) {
            gameFrame.changeViewPanel(PanelNames.DOORTODOOR_VIEW);
        } else {
            villagerListPanel.showText(language.get("noTargetsSelected"));
        }
    }

    private void emptyTargetVillagerList() {
        gameLogic.getActiveGame().setTargetVillagers(new ArrayList<>());
    }

}
