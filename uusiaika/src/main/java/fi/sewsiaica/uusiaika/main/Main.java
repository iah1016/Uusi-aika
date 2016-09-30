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
import fi.sewsiaica.uusiaika.logic.*;
import fi.sewsiaica.uusiaika.ui.TextbasedUI;
import java.util.Random;
import java.util.Scanner;

/**
 * The Main class creates Game and GUI classes and starts the game. Random and
 * Config classes are also created and given as parameters to the Game class.
 *
 * @author iah1016
 */
public class Main {

    /**
     * Currently, any exception will end the game with the message "Ei natsaa".
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            // To be used before the implementation of the GUI.
            Scanner reader = new Scanner(System.in, "ISO-8859-1");
            TextbasedUI tui = new TextbasedUI(reader);

            Random random = new Random();
            Config config = new Config();

            Game game = new Game(random, config);

//            tui.setGame(game);
//            tui.openingView();
//            tui.mainMenu();
            //
        } catch (Exception e) {
            System.out.println("Ei natsaa");
        }
    }

}
