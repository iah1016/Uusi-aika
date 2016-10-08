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
package fi.sewsiaica.uusiaika.main;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.logic.GameLogic;
import fi.sewsiaica.uusiaika.ui.GUIRunner;
import java.util.Random;
import javax.swing.SwingUtilities;

/**
 * The Main class creates GameLogic and GUIRunner classes and starts the game. Random and
 Config classes are also created and given as parameters to the GameLogic class.
 *
 * @author iah1016
 */
public class Main {

    /**
     * Currently, any exception will end the game with the message "Ei natsaa".
     *
     * @param args Arguments, not yet implemented.
     */
    public static void main(String[] args) {

        try {
            Random random = new Random();
            Config config = new Config();
            GameLogic gameLogic = new GameLogic(random, config);
            
            GUIRunner gui = new GUIRunner(gameLogic, args);
            SwingUtilities.invokeLater(gui);
            
        } catch (Exception e) {
            System.out.println("Ei natsaa");
        }
    }

}
