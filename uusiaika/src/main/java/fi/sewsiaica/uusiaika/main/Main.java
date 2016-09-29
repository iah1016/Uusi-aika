package fi.sewsiaica.uusiaika.main;

import fi.sewsiaica.uusiaika.config.Config;
import fi.sewsiaica.uusiaika.logic.*;
import fi.sewsiaica.uusiaika.ui.TextbasedUI;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author iah1016
 */
public class Main {

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
