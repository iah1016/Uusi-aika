package fi.sewsiaica.uusiaika.logic;

/**
 *
 * @author iah1016
 */
public class Player {

    private String name;
    private int charisma;
    private int argSkills;

    public Player(int charisma, int argSkills) {
        this.name = "0";
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

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getArgSkills() {
        return argSkills;
    }

    public void setArgSkills(int argSkills) {
        this.argSkills = argSkills;
    }

}
