package project;

/**
 * Laura O
 */
public class Residency {

    private String name;
    private String field;
    private int step1;
    private int step2;
    private String citizen;
    private String medSchool;

    public Residency(String name, String field, int step1, int step2, String citizen, String medSchool) {
        this.name = name;
        this.field = field;
        this.step1 = step1;
        this.step2 = step2;
        this.citizen = citizen;
        this.medSchool = medSchool;
    }

    public String getName() {
        return this.name;
    }

    public String getField() {
        return this.field;
    }

    public int getStep1() {
        return this.step1;
    }

    public int getStep2() {
        return this.step2;
    }

    public String getCitizen() {
        return this.citizen;
    }

    public String getSchool() {
        return this.medSchool;
    }

    public String toString() {
        return getName() + "," + getField() + ", " + getStep1() + "," + getStep2() + "," + getCitizen() + "," + getSchool();
    }
}
