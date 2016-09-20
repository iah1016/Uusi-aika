package fi.sewsiaica.uusiaika.domain;

/**
 *
 * @author iah1016
 */
public class Villager {

    private String name;
    private boolean inSect;
    private int scepticism;
    private int selfEsteem;
    private int selfAwareness;
    private int argSkills;
    private String profession;
    private int numberOfPersuations;
    private int numberOfSermons;
    private int numberOfAccusations;

    public Villager(String name, boolean inSect, int scepticism, int selfEsteem, int selfAwareness, int argSkills, String profession) {
        this.inSect = inSect;
        this.name = name;
        this.scepticism = scepticism;
        this.selfEsteem = selfEsteem;
        this.selfAwareness = selfAwareness;
        this.argSkills = argSkills;
        this.profession = profession;
        this.numberOfPersuations = 0;
        this.numberOfSermons = 0;
        this.numberOfAccusations = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isInSect() {
        return inSect;
    }

    public int getScepticism() {
        return scepticism;
    }

    public int getSelfEsteem() {
        return selfEsteem;
    }

    public int getSelfAwareness() {
        return selfAwareness;
    }

    public int getArgSkills() {
        return argSkills;
    }

    public String getProfession() {
        return profession;
    }

    public void setInSect(boolean inSect) {
        this.inSect = inSect;
    }

    public void setScepticism(int value) {
        if (value >= 0) {
            this.scepticism = value;
        }
    }

    public void setSelfEsteem(int value) {
        if (value >= 0) {
            this.selfEsteem = value;
        }
    }

    public void setSelfAwareness(int value) {
        if (value >= 0) {
            this.selfAwareness = value;
        }
    }

    public void setArgSkills(int value) {
        if (value >= 0) {
            this.argSkills = value;
        }
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getNumberOfPersuasions() {
        return numberOfPersuations;
    }

    public void setNumberOfPersuations(int numberOfPersuations) {
        this.numberOfPersuations = numberOfPersuations;
    }

    public int getNumberOfSermons() {
        return numberOfSermons;
    }

    public void setNumberOfSermons(int numberOfSermons) {
        this.numberOfSermons = numberOfSermons;
    }

    public int getNumberOfAccusations() {
        return numberOfAccusations;
    }

    public void setNumberOfAccusations(int numberOfAccusations) {
        this.numberOfAccusations = numberOfAccusations;
    }

}
