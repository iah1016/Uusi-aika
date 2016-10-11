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
import fi.sewsiaica.uusiaika.ui.subpanels.InfoPanel;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JPanel;

/**
 * The abstract view panel currently contains the implementation of the
 * createButtonPanel method.
 *
 * @author iah1016
 */
public abstract class AbstractViewPanel extends JPanel {

    /**
     * Calls the parent constructor with no arguments.
     *
     */
    public AbstractViewPanel() {
        super();
    }

    /**
     * This method will create a new InfoPanel instance (JPanel subclass).
     *
     * @param gameLogic GameLogic is given as a parameter.
     * @return Returns the info panel.
     */
    public JPanel createGameInfoPanel(GameLogic gameLogic) {
        return new InfoPanel(gameLogic);
    }

    /**
     * Adds the given buttons to a new JPanel, which it returns, and the
     * corresponding ActionListener object to the buttons.
     *
     * @param buttons An array of AbstractButtons.
     * @param actionListener The ActionListener for the buttons.
     * @return Returns the JPanel which contains the buttons.
     */
    protected JPanel createButtonPanel(AbstractButton[] buttons,
            ActionListener actionListener) {
        JPanel buttonPanel = new JPanel();

        for (AbstractButton button : buttons) {
            button.addActionListener(actionListener);
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    /**
     * This method contains the view panel settings.
     */
    public abstract void setViewPanelSettings();

    /**
     * The method for creating an array of AbstractButtons.
     *
     * @return returns the array of AbstractButtons.
     */
    protected abstract AbstractButton[] createButtons();

    /**
     * This method creates the ActionListener for the view panel subclass.
     *
     * @param buttons The AbstractButtons are given to the ActionListener's
     * constructor as a parameter.
     * @return Returns the ActionListener.
     */
    protected abstract ActionListener createActionListener(
            AbstractButton[] buttons);

    /**
     * This method adds the sub panels to the view panel and makes them visible.
     *
     */
    protected abstract void addSubPanelsToViewPanel();
}
