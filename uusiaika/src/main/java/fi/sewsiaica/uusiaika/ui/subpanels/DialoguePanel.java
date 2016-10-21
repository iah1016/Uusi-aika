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
package fi.sewsiaica.uusiaika.ui.subpanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * This panel will display the output and the dialogue of the game.
 *
 * @author iah1016
 */
public class DialoguePanel extends JPanel {

    private final JTextArea textPane;

    /**
     * The constructor calls the addContents method.
     */
    public DialoguePanel() {
        super();
        this.textPane = new JTextArea();
        this.textPane.setWrapStyleWord(true);
        this.textPane.setLineWrap(true);
        this.textPane.setEditable(false);
        this.addContents();
    }

    private void addContents() {
        JScrollPane outputPane = new JScrollPane(textPane,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputPane.setPreferredSize(new Dimension(200, 313));
        this.setPreferredSize(new Dimension(200, 300));
        this.setLayout(new BorderLayout());
        add(outputPane, BorderLayout.NORTH);

        textPane.setBackground(Color.decode("#d1d1e0"));
        setBackground(Color.decode("#1f2e2e"));
    }

    /**
     * Appends the text in the dialogue panel.
     *
     * @param outputText The output text to be displayed.
     */
    public void showText(String outputText) {
        textPane.append(outputText + "\n");
    }

    /**
     * Resets the text shown in the dialogue panel.
     */
    public void resetText() {
        textPane.setText("");
    }
}
