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
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The object of this class handles the events in the new game creation view.
 *
 * @author iah1016
 */
public class NewGameViewPanelListener implements ActionListener {

    private final GameLogic gameLogic;
    private final GameFrame gameFrame;
    private final JTextField textFieldPlayerName;
    private final JTextField textFieldSectName;
    private final AbstractButton createGameButton;
    private final AbstractButton configValuesFileButton;
    private final AbstractButton villagerNamesFileButton;
    private final AbstractButton villagerProfsFileButton;
    private final AbstractButton openingMenuViewButton;
    private final JFileChooser fileChooser;
    private final File[] configFiles;

    /**
     * The constructor is given five JTextFields, an array of one
     * AbstractButton, GameFrame, and GameLogic as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param textFieldPlayerName The player name given by the player.
     * @param textFieldSectName The sect name given by the player.
     * @param buttons [0] createGameButton creates a new active game.
     */
    public NewGameViewPanelListener(GameLogic gameLogic, GameFrame frame,
            JTextField textFieldPlayerName, JTextField textFieldSectName,
            AbstractButton[] buttons) {
        this.gameLogic = gameLogic;
        this.gameFrame = frame;
        this.textFieldPlayerName = textFieldPlayerName;
        this.textFieldSectName = textFieldSectName;
        this.createGameButton = buttons[0];
        this.configValuesFileButton = buttons[1];
        this.villagerNamesFileButton = buttons[2];
        this.villagerProfsFileButton = buttons[3];
        this.openingMenuViewButton = buttons[4];
        this.configFiles = new File[3];
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

        if (ae.getSource() == createGameButton) {
            createGameSelected();
        } else if (ae.getSource() == configValuesFileButton) {
            fileChooserDialog(0, "Choose the configuration file");
        } else if (ae.getSource() == villagerNamesFileButton) {
            fileChooserDialog(1, "Choose the villager names file");
        } else if (ae.getSource() == villagerProfsFileButton) {
            fileChooserDialog(2, "Choose the villager professions file");
        } else if (ae.getSource() == openingMenuViewButton) {
            gameFrame.changeViewPanel(PanelNames.OPENING_MENU_VIEW);
        }
    }

    private void fileChooserDialog(int configFileID, String title) {
        fileChooser.setDialogTitle(title);
        int returnVal = fileChooser.showOpenDialog(gameFrame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            configFiles[configFileID] = fileChooser.getSelectedFile();
            System.out.println(configFiles[configFileID].getName());
        } else {
            System.out.println("Cancelled.");
        }
    }

    private void updateConfigFilesInGameLogic() {
        for (int i = 0; i < configFiles.length; i++) {
            gameLogic.getConfigFiles()[i] = configFiles[i];
        }
    }

    private void createGameSelected() {
        String[] names = new String[2];
        names[0] = textFieldPlayerName.getText();
        names[1] = textFieldSectName.getText();

        if (nameLengthsAreNotOverLimit(names, 25)) {
            tryCreatingNewGame(names);
        } else {
            System.out.println("A name can be max 25 characters long.");
        }
    }

    private boolean nameLengthsAreNotOverLimit(String[] names, int limit) {
        return names[0].length() <= limit && names[1].length() <= limit;
    }

    private void tryCreatingNewGame(String[] names) {
        try {
            updateConfigFilesInGameLogic();
            if (gameLogic.newGame(names)) {
                gameFrame.changeViewPanel(PanelNames.MAP_VIEW);
            } else {
                System.out.println("Invalid Config file");
                gameFrame.changeViewPanel(PanelNames.NEW_GAME_VIEW);
            }
        } catch (Exception e) {
            System.out.println("File(s) cannot be read.");
        }
    }

}
