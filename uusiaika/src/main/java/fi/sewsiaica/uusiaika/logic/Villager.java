package fi.sewsiaica.uusiaika.logic;

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
    private int noOfPersuations;
    private int noOfSermons;
    private int noOfAccusations;

    public Villager(String name, boolean inSect, int scepticism, int selfEsteem, int selfAwareness, int argSkills, String profession) {
        this.inSect = inSect;
        this.name = name;
        this.scepticism = scepticism;
        this.selfEsteem = selfEsteem;
        this.selfAwareness = selfAwareness;
        this.argSkills = argSkills;
        this.profession = profession;
        this.noOfPersuations = 0;
        this.noOfSermons = 0;
        this.noOfAccusations = 0;
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

    public int getNoOfPersuations() {
        return noOfPersuations;
    }

    public void setNoOfPersuations(int noOfPersuations) {
        this.noOfPersuations = noOfPersuations;
    }

    public int getNoOfSermons() {
        return noOfSermons;
    }

    public void setNoOfSermons(int noOfSermons) {
        this.noOfSermons = noOfSermons;
    }

    public int getNoOfAccusations() {
        return noOfAccusations;
    }

    public void setNoOfAccusations(int noOfAccusations) {
        this.noOfAccusations = noOfAccusations;
    }
    
    
}
