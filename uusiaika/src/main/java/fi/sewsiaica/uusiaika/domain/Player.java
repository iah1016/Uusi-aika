package fi.sewsiaica.uusiaika.domain;

/**
 * You, the Holy Controller of this Universe. You will have the name, the
 * charisma and the argumentation skills to conquer the world. Well, you have a
 * name, at least.
 *
 * @author iah1016
 */
public class Player {

    private String name;
    private int charisma;
    private int argSkills;

    /**
     *
     * @param name The name is given by the user via GUI.
     * @param charisma Default value from Config.intValues.
     * @param argSkills Default value from Config.intValues.
     */
    public Player(String name, int charisma, int argSkills) {
        this.name = name;
        this.charisma = charisma;
        this.argSkills = argSkills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int value) {
        if (value >= 0) {
            this.charisma = value;
        }
    }

    public int getArgSkills() {
        return argSkills;
    }

    public void setArgSkills(int value) {
        if (value >= 0) {
            this.argSkills = value;
        }
    }

}
