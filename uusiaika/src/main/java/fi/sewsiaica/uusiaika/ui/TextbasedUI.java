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
    }

    public void openingView() {
        String[] playerAndSectNames = new String[2];

        System.out.print("Se suuri johtaja! Valitse nimesi: ");
        playerAndSectNames[0] = reader.nextLine();
        System.out.print("Valitse liikkeesi nimi: ");
        playerAndSectNames[1] = reader.nextLine();

        game.initGame(playerAndSectNames);
    }

    public void mainMenu() {
        while (true) {
            System.out.println("Päävalikko:");
            System.out.println("(a)  käännytysvalikkoon");
            System.out.println("(x)  lopeta");
            String option = reader.nextLine();
            if (option.equals("x")) {
                break;
            } else if (option.equals("a")) {
                conversionMenu(chooseVillagerMenu());
            } else {
                System.out.println("Väärä komento!");
            }
        }
    }

    public Villager chooseVillagerMenu() {
        int index = 0;
        while (true) {
            System.out.println("valitse kyläläinen: 1-" + game.getVillagers().size());
            String value = reader.nextLine();
            if (value.matches("^[0-9]+$")) {
                index = Integer.parseInt(value) - 1;
                if (index >= 0 && index < game.getVillagers().size()) {
                    System.out.println("kyläläinen " + game.getVillagers().get(index).getName() + " valittu.");
                    return game.getVillagers().get(index);
                } else {
                    System.out.println("virheellinen luku");
                }
            } else {
                System.out.println("syötä mieluummin numero");
            }

        }
    }

    public void conversionMenu(Villager villager) {
        boolean inTheLoop = true;

        while (inTheLoop) {
            System.out.println("Käännytysvalikko:");
            System.out.println("(a)  lepertele keveitä");
            System.out.println("(b)  kerro pyhästä kalasta");
            System.out.println("(c)  syytä syntisyydestä");
            System.out.println("(m)  palaa päävalikkoon");
            String option = reader.nextLine();
            if (option.equals("m")) {
                System.out.println("ei sitten");
                inTheLoop = false;
            } else if (option.equals("a") || 
                    option.equals("b") ||
                    option.equals("c")) {
                //a  charisma vs. selfAwareness + scepticism
                //b  charisma + argSkills vs. argSkills vs. scepticism
                //c  charisma vs. selfEsteem + argSkills
                inTheLoop = game.conversion(villager, option);
            } else {
                System.out.println("Väärä komento!");
                inTheLoop = true;
            }
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
