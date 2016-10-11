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

import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JPanel;

/**
 * The abstract view panel currently contains the implementation of the
 * addButtons method.
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
     * Adds the given buttons to the view panel and to the corresponding
     * ActionListener object.
     *
     * @param buttons An array of AbstractButtons.
     * @param actionListener The ActionListener for the view panel subclass.
     */
    protected void addButtons(AbstractButton[] buttons,
            ActionListener actionListener) {

        for (AbstractButton button : buttons) {
            button.addActionListener(actionListener);
            add(button);
        }
    }

    /**
     * This method contains the panel settings.
     */
    protected abstract void setPanelSettings();

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

}
