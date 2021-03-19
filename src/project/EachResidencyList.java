package project;

import java.util.ArrayList;

/**
 * Laura O
 */
public class EachResidencyList {

    ArrayList<String> eachArr = new ArrayList<>();

    public EachResidencyList() {
    }

    /**
     * From Residency, it will store each variable as elements of this
     * arraylist.
     */
    public void addResidency(Residency residency) {
        String name = residency.getName();
        String field = residency.getField();
        String step1 = String.valueOf(residency.getStep1());
        String step2 = String.valueOf(residency.getStep2());
        String citizen = residency.getCitizen();
        String school = residency.getSchool();
        eachArr.add(name);
        eachArr.add(field);
        eachArr.add(step1);
        eachArr.add(step2);
        eachArr.add(citizen);
        eachArr.add(school);
    }

    public String getName() {
        return eachArr.get(0);
    }

    public String getField() {
        return eachArr.get(1);
    }

    public String getStep1() {
        return eachArr.get(2);
    }

    public String getStep2() {
        return eachArr.get(3);
    }

    public String getCitizen() {
        return eachArr.get(4);
    }

    public String getSchool() {
        return eachArr.get(5);
    }

    public String toString() {
        String line = "" + eachArr + "\n";
        return line;
    }
}
