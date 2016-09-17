package fi.sewsiaica.uusiaika.ui;

import java.util.Scanner;
import fi.sewsiaica.uusiaika.logic.*;
import java.util.HashSet;

/**
 *
 * @author iah1016
 */
public class TextbasedUI {

    private HashSet<Character> allAllowedChars;
    private Scanner reader;
    private Game game;

    public TextbasedUI(Scanner reader) {
        this.reader = reader;
        String chars = "abcdefghijklmnopqrstuvwxyzåäöüáéóúàèòù ABCDEFGHIJKLMN"
                + "OPQRSTUVWXYZÅÄÖÜÁÉÓÚÀÈÒÙ0123456789.,:;-_+!?&()/";
        this.allAllowedChars = createAllowedChars(chars);
    }

    public void openingView() {
        String[] playerAndSectNames = new String[2];

        System.out.print("Se suuri johtaja! Valitse nimesi: ");
        playerAndSectNames[0] = readInput(allAllowedChars);
        System.out.print("Valitse liikkeesi nimi: ");
        playerAndSectNames[1] = readInput(allAllowedChars);

        game.initGame(playerAndSectNames);
    }

    public void mainMenu() {
        while (true) {
            System.out.println("Päävalikko:");
            System.out.println("(a)  käännytysvalikkoon");
            System.out.println("(x)  lopeta");
            String option = readInput(createAllowedChars("ax"));
            if (option.equals("x")) {
                break;
            } else if (option.equals("a")) {
                conversionMenu(chooseVillagerMenu());
            }
        }
    }

    public Villager chooseVillagerMenu() {
        int index = 0;
        while (true) {
            int highest = game.getVillagers().size();
            System.out.println("valitse kyläläinen: 1-" + highest);
            String value = readInput(createAllowedChars("0123456789"));

            if (validNumberGivenAsString(value)) {
                index = Integer.parseInt(value) - 1;
                if (index < highest) {
                    Villager chosenOne = game.getVillagers().get(index);

                    if (!game.getVillagers().get(index).isInSect()) {
                        System.out.println("kyläläinen " + chosenOne.getName()
                                + " valittu ");
                        return chosenOne;
                    } else {
                        System.out.println(chosenOne.getName() + " on jo jäsen!");
                    }
                }
            }
        }
    }

    public void conversionMenu(Villager villager) {
        boolean ongoing = true;

        while (ongoing) {
            System.out.println("Käännytysvalikko:");
            System.out.println("(a)  lepertele keveitä");
            System.out.println("(b)  kerro pyhästä kalasta");
            System.out.println("(c)  syytä syntisyydestä");
            System.out.println("(m)  palaa päävalikkoon");
            String option = readInput(createAllowedChars("abcm"));
            if (option.equals("m")) {
                ongoing = false;
            } else if (option.equals("a")
                    || option.equals("b")
                    || option.equals("c")) {
                //a  persuasion
                //b  sermon
                //c  accusation
                ongoing = game.conversion(villager, option);
                if (!ongoing && !villager.isInSect()) {
                    System.out.println("eipä onnistanut");
                } else if (!ongoing && villager.isInSect()) {
                    System.out.println("Onnittelut! " + villager.getName()
                            + " on liittynyt liikkeen jäseneksi!");
                }
            }
        }
    }

    public boolean validNumberGivenAsString(String string) {
        if (string == null) {
            return false;
        }
        if (string.equals("") || string.charAt(0) == '0') {
            return false;
        }
        return true;
    }

    public String readInput(HashSet<Character> allowed) {
        String s = "";

        while (true) {
            s = reader.nextLine();

            if (s == null) {
                System.out.println("tyhjä rivi");
            } else if (s.equals("")) {
                System.out.println("tyhjä rivi");
            } else if (checkIfAllowedCharacters(s, allowed)) {
                return s;
            } else {
                System.out.println("kiellettyjä merkkejä");
            }
        }
    }

    public boolean checkIfAllowedCharacters(String string,
            HashSet<Character> allowed) {
        for (int i = 0; i < string.length(); i++) {
            if (!allowed.contains(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public HashSet<Character> createAllowedChars(String chars) {

        HashSet<Character> allowed = new HashSet<Character>();
        for (int i = 0; i < chars.length(); i++) {
            allowed.add(chars.charAt(i));
        }
        return allowed;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
