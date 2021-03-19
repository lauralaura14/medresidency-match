package project;

import java.util.ArrayList;

/**
 * Laura O
 */
public class EntireResidencyList {

    ArrayList<EachResidencyList> completeList = new ArrayList<>();

    public EntireResidencyList() {
    }

    /**
     * Will add EachResidencyList into this arraylist to create a list of all
     * the residency arraylists.
     */
    public void addToEntireList(EachResidencyList residencyArr) {
        completeList.add(residencyArr);
    }

    public int getSize() {
        return completeList.size();
    }

    public EachResidencyList specificArray(int i) {
        return completeList.get(i);
    }

    public String toString() {
        String line = "\n" + completeList;
        return line.replace(", [", "").replace("]", "").replace("[", "");
    }

}
