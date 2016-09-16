package fi.sewsiaica.uusiaika.ui;

import java.util.Scanner;
import fi.sewsiaica.uusiaika.logic.*;

/**
 *
 * @author iah1016
 */
public class TextbasedUI {

    private Scanner reader;
    private Game game;

    public TextbasedUI(Scanner reader, Game game) {
        this.reader = reader;
        this.game = game;
    }

    public void openingView() {
        System.out.print("Se suuri johtaja! Valitse nimesi: ");
        String playerName = reader.nextLine();
        System.out.print("Valitse liikkeesi nimi: ");
        String sectName = reader.nextLine();
        System.out.println("\n" + playerName + ", " + sectName + "-liikkeen yliukko");

        // temp
        for (int i = 0; i < game.getVillagers().size(); i++) {
            Villager v = game.getVillagers().get(i);
            System.out.println(v.getName() + " on " + v.getProfession());
        }

    }
}
