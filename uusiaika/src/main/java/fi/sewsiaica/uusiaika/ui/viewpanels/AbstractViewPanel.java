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
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
     * This method contains the view panel settings.
     */
    public abstract void setViewPanelSettings();

    /**
     * This method returns a new Button subpanel (JPanel subclass) which
     * contains AbstractButtons attached to the corresponding ActionListener. It
     * uses the following methods: createButtons, createActionListener,
     * createButtonPanel.
     *
     * @param textsForButtons The texts of the Buttons given as a string array.
     * @return Returns the JPanel which contains the buttons.
     */
    protected JPanel getNewButtonPanel(String[] textsForButtons) {

        AbstractButton[] buttons = createButtons(textsForButtons);
        ActionListener actionListener = createActionListener(buttons);
        JPanel buttonPanel = createButtonPanel(buttons, actionListener);

        return buttonPanel;
    }

    private JPanel createButtonPanel(AbstractButton[] buttons,
            ActionListener actionListener) {
        JPanel buttonPanel = new JPanel();

        for (AbstractButton button : buttons) {
            button.addActionListener(actionListener);
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    private AbstractButton[] createButtons(String[] textsForButtons) {
        AbstractButton[] buttons = new JButton[textsForButtons.length];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(textsForButtons[i]);
        }
        return buttons;
    }

    /**
     * The method creates a new JTextField and then adds it to the given
     * textFieldPanel (JPanel subclass).
     *
     * @param textFieldPanel A JPanel subclass object.
     * @param text The default text displayed in the text field.
     * @param length The length of the text field.
     * @return Returns a JTextField object.
     */
    protected JTextField createJTextField(JPanel textFieldPanel,
            String text, int length) {
        JTextField textField = new JTextField(length);
        textField.setText(text);

        textFieldPanel.add(textField);
        return textField;
    }

    /**
     * This method creates the ActionListener for the getNewButtonPanel method.
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
