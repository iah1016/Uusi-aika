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

    public Villager(String name, boolean inSect, int scepticism, int selfEsteem, int selfAwareness, int argSkills, String profession) {
        this.inSect = inSect;
        this.name = name;
        this.scepticism = scepticism;
        this.selfEsteem = selfEsteem;
        this.selfAwareness = selfAwareness;
        this.argSkills = argSkills;
        this.profession = profession;
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

    public void setScepticism(int scepticism) {
        this.scepticism = scepticism;
    }

    public void setSelfEsteem(int selfEsteem) {
        this.selfEsteem = selfEsteem;
    }

    public void setSelfAwareness(int selfAwareness) {
        this.selfAwareness = selfAwareness;
    }

    public void setArgSkills(int argSkills) {
        this.argSkills = argSkills;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
