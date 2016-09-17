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
            //Later from a file
            String[] namesForVillagers = {"Jaakko P", "Harri H", "Mikko M", "Teemu P",
                "Ilona R", "Taina E", "Marika M", "Robert F", "Cecilia C", "Oleg M"};
            String[] professions = {"Kauppias", "Leipuri", "Opettaja", "Postinjakaja",
                "Lääkäri", "Radiojuontaja", "Poliisi", "Bussikuski", "Putkimies",
                "Poliitikko", "Tutkija", "Apteekkari", "AD", "Toimitusjohtaja"};

            // To be used before the implementation of the GUI.
            Scanner reader = new Scanner(System.in, "ISO-8859-1");
            TextbasedUI tui = new TextbasedUI(reader);
            Game game = new Game(namesForVillagers, professions);
            tui.setGame(game);
            tui.openingView();
            tui.mainMenu();
            //

        } catch (Exception e) {
            System.out.println("Ei natsaa");
        }
    }

}
