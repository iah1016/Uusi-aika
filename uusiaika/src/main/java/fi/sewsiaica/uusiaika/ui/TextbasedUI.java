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

    public TextbasedUI(Scanner reader) {
        this.reader = reader;
        this.game = openingView();
        mainView();
    }
    
    private Game openingView() {
        System.out.print("Se suuri johtaja! Valitse nimesi: ");
        String playerName = reader.nextLine();
        System.out.print("Valitse liikkeesi nimi: ");
        String sectName = reader.nextLine();
        System.out.println("\n" + playerName + ", " + sectName + "-liikkeen yliukko");

        return new Game(playerName, sectName);
    }
    
    private void mainView() {
        
    }
}
