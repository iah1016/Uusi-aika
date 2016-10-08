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
import fi.sewsiaica.uusiaika.ui.GameFrame;
import fi.sewsiaica.uusiaika.ui.PanelKeys;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class extends JPanel; its object displays the creation of a new game.
 *
 * @author iah1016
 */
public class NewGameViewPanel extends JPanel {

    private final Dimension dimension;
    private final GameLogic gameLogic;

    /**
     * Temporarily a public object variable.
     */
    GameFrame gameFrame;

    /**
     * Dimension, GameLogic, and GameFrame are given as parameters. GameFrame is
     * temporarily a public object variable.
     *
     * @param dimension The dimensions of the panel.
     * @param gameLogic The core logic of the game, through which the other
     * logic parts are called.
     * @param frame GameFrame gives itself as parameter, so that the active
     * ViewPanel can be changed.
     */
    public NewGameViewPanel(Dimension dimension, GameLogic gameLogic,
            GameFrame frame) {
        super();
        this.dimension = dimension;
        this.gameFrame = frame;
        this.gameLogic = gameLogic;
        this.setPreferredSize(dimension);
        this.setBackground(Color.BLUE);
        addComponents();
    }

    private void addComponents() {
        JTextField tf = new JTextField();
        tf.setText("Player name here");
        tf.setForeground(Color.BLUE);
        add(tf);

        JTextField tf2 = new JTextField();
        tf2.setText("Sect name here");
        tf2.setForeground(Color.BLUE);
        add(tf2);

        String[] names = new String[2];

        JButton button1 = new JButton("Test 1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                names[0] = tf.getText();
                names[1] = tf2.getText();
                try {
                    gameLogic.newGame(names, "", "", "");
                    gameFrame.changeViewPanel(PanelKeys.MAP_VIEW);
                } catch (FileNotFoundException e) {
                    System.out.println("ei natsaa");
                }
            }
        });
        add(button1);
    }

}
