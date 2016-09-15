package fi.sewsiaica.uusiaika.logic;

/**
 *
 * @author iah1016
 */
public class Player {

    private int charisma;
    private int argSkills;

    public Player(int charisma, int argSkills) {
        this.charisma = charisma;
        this.argSkills = argSkills;
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
