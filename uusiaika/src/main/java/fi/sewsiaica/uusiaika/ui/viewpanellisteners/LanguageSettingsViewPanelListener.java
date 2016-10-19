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
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The object of this class handles the events in the language settings view.
 *
 * @author iah1016
 */
public class LanguageSettingsViewPanelListener implements ActionListener {

    private final GameFrame gameFrame;
    private final GameLogic gameLogic;
    private final AbstractButton[] languageButtons;
    private final AbstractButton loadFromFileButton;
    private final AbstractButton openingMenuViewButton;
    private final JFileChooser fileChooser;

    /**
     * The constructor is given an AbstractButton array, GameFrame, and
     * GameLogic as parameters.
     *
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame The core class of the GUI. It controls which view panel is
     * shown.
     * @param buttons The second to last button is the load from a file button,
     * the last one is the opening menu button; the buttons before them are the
     * language buttons: English, Finnish etc.
     */
    public LanguageSettingsViewPanelListener(GameFrame frame,
            GameLogic gameLogic, AbstractButton[] buttons) {
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.loadFromFileButton = buttons[buttons.length - 2];
        this.openingMenuViewButton = buttons[buttons.length - 1];
        this.languageButtons = new AbstractButton[buttons.length - 2];
        this.fileChooser = new JFileChooser();
        fileChooserSettings();
        addLanguagesToLanguageButtonsArray(buttons);
    }

    private void fileChooserSettings() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                ".txt files", "txt");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Open a language file");

        String gameDir = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(gameDir));
    }

    private void addLanguagesToLanguageButtonsArray(AbstractButton[] buttons) {
        for (int i = 0; i < languageButtons.length; i++) {
            languageButtons[i] = buttons[i];
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == openingMenuViewButton) {
            gameFrame.changeViewPanel(PanelNames.OPENING_MENU_VIEW);
        } else if (ae.getSource() == loadFromFileButton) {
            loadFromFileAction(ae);
        } else {
            goThroughLanguageButtonActions(ae);
        }
    }

    private void loadFromFileAction(ActionEvent ae) {
        int returnVal = fileChooser.showOpenDialog(gameFrame);
        File file;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            System.out.println(file.getName());
            loadCustomLanguage(file);
        } else {
            System.out.println("Cancelled.");
        }
        gameFrame.changeViewPanel(PanelNames.LANGUAGESETTINGS_VIEW);
    }

    private void loadCustomLanguage(File file) {
        if (gameLogic.changeCustomLanguage(file)) {
            List<String> languageNameList = gameLogic.getNamesOfLanguages();
            int customLanguageIndex = languageNameList.size() - 1;

            gameLogic.setActiveLanguage(languageNameList.get(
                    customLanguageIndex));
            System.out.println(gameLogic.getActiveLanguage().get("language"));
        } else {
            System.out.println("nope");
        }
    }

    private void goThroughLanguageButtonActions(ActionEvent ae) {
        for (AbstractButton languageButton : languageButtons) {
            if (ae.getSource() == languageButton) {
                gameLogic.setActiveLanguage(languageButton.getText());
                System.out.println(
                        gameLogic.getActiveLanguage().get("language"));
            }
        }
        gameFrame.changeViewPanel(PanelNames.LANGUAGESETTINGS_VIEW);
    }
}
