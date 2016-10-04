/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.config.defaultvalues;

/**
 * This enum class contains the default list of names for villagers. This list
 * will soon be much greater.
 *
 * @author iah1016
 */
public enum DefaultVilNames {
    VILLAGER01("Jaakko P"),
    VILLAGER02("Harri H"),
    VILLAGER03("Mikko M"),
    VILLAGER04("Teemu P"),
    VILLAGER05("Ilona R"),
    VILLAGER06("Taina E"),
    VILLAGER07("Marika M"),
    VILLAGER08("Robert F"),
    VILLAGER09("Cecilia C"),
    VILLAGER10("Oleg M");
    private final String name;

    private DefaultVilNames(String name) {
        this.name = name;
    }

    public String vilName() {
        return name;
    }
}
