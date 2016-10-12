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
import java.io.File;
import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The object of this class handles the events in the load a game view.
 *
 * @author iah1016
 */
public class LoadGameViewPanelListener implements ActionListener {

    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
    private final AbstractButton loadGameButton;
    private final JFileChooser fileChooser;
    private File saveFile;

    /**
     * The constructor is given one AbstractButton, GameFrame, and GameLogic as
     * parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param buttons [0] loadGameButton Loads a game from a save file.
     */
    public LoadGameViewPanelListener(GameLogic gameLogic, GameFrame frame,
            AbstractButton[] buttons) {
        this.gameLogic = gameLogic;
        this.gameFrame = frame;
        this.loadGameButton = buttons[0];
        this.fileChooser = new JFileChooser();
        fileChooserSettings();
    }

    private void fileChooserSettings() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".txt files", "txt");
        fileChooser.setFileFilter(filter);

        String gameDir = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(gameDir));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loadGameButton) {
            loadGameSelected();
        }
    }
    
    private void loadGameSelected() {
        if (fileChosenWithFileChooser()) {
            if (loadingGameSucceeds()) {
                System.out.println(saveFile.getName() + " loaded.");
                gameFrame.changeViewPanel(PanelNames.MAP_VIEW);
            } else {
                System.out.println(saveFile.getName()
                        + " is an invalid save file.");
                gameFrame.changeViewPanel(PanelNames.OPENING_MENU_VIEW);
            }
        }
    }

    private boolean fileChosenWithFileChooser() {
        int returnVal = fileChooser.showOpenDialog(gameFrame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            saveFile = fileChooser.getSelectedFile();
            System.out.println(saveFile.getName());
            return true;
        }
        System.out.println("Cancelled.");
        return false;
    }

    private boolean loadingGameSucceeds() {
        return gameLogic.loadGame(saveFile);
    }
}
