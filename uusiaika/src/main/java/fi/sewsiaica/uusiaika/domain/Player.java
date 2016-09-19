package fi.sewsiaica.uusiaika.domain;

/**
 *
 * @author iah1016
 */
public class Player {

    private String name;
    private int charisma;
    private int argSkills;

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
