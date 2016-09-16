package fi.sewsiaica.uusiaika.main;

import fi.sewsiaica.uusiaika.logic.*;
import fi.sewsiaica.uusiaika.ui.TextbasedUI;
import java.util.Scanner;

/**
 *
 * @author iah1016
 */
public class Main {

    public static void main(String[] args) {

        try {
            // To be used before the implementation of the GUI.
            Scanner reader = new Scanner(System.in);

            TextbasedUI tui = new TextbasedUI(reader);
            //

        } catch (Exception e) {
            System.out.println("Ei natsaa");
        }
    }

}
