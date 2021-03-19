package project;

/**
 * Laura O
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputSeparate {

    private BufferedReader reader;
    private EntireResidencyList completeList;
    private int list[];
    private int schoolType[];
    EntireResidencyList filteredList;

    public InputSeparate() {
    }

    /**
     * Will read the file being input and divide each line into a string. Then
     * each string line will be stored in an arraylist (divideString) with
     * word/number between each comma as a new element. Then, each element of
     * this arraylist will be stored into the Residency parameters.
     */
    public EntireResidencyList inputNumbers() {
        completeList = new EntireResidencyList();
        try {
            reader = new BufferedReader(new FileReader("../Input.txt"));
            String line;
            ArrayList<String> divideString;
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.length() > 0) {

                        // this is done so that blank spaces between commas will not be included into the element.
                        divideString = new ArrayList<>(Arrays.asList(line.split("\\s*,\\s*")));

                        // must convert both to int for Residency parameters.
                        int step1Int = Integer.parseInt(divideString.get(2));
                        int step2Int = Integer.parseInt(divideString.get(3));

                        // create Residency object.
                        Residency residency = new Residency(divideString.get(0), divideString.get(1), step1Int, step2Int, divideString.get(4), divideString.get(5));

                        // create this arraylist to make residency into an arraylist
                        EachResidencyList eachResArr = new EachResidencyList();
                        eachResArr.addResidency(residency);

                        // add each arraylist into an arraylist.
                        completeList.addToEntireList(eachResArr);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            Logger.getLogger(InputSeparate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return completeList;
    }

    /**
     * Input information into the parameters to find residency matches.
     */
    public String statMatch(int step1, int step2, String citizen, String medSchool) {
        inputNumbers();
        int weightCount;
        EntireResidencyList applyList = new EntireResidencyList();

        // calls this method to filter the programs based on type of med school (argument: AMG/FMG/IMG). Then the
        // matching residency program arrays will be added to filteredList within the searchMedSchool(medSchool).
        searchMedSchool(medSchool);

        // From the returned filteredList -- for each residency array, if the Step scores fulfills the if condition,
        // give a weight of 100.
        for (int k = 0; k < filteredList.getSize(); ++k) {
            weightCount = 0;
            int reqStep1 = Integer.parseInt(filteredList.specificArray(k).getStep1());
            int reqStep2 = Integer.parseInt(filteredList.specificArray(k).getStep2());
            if (reqStep1 <= step1 && reqStep2 <= step2) {
                weightCount += 100;
            } /**
             * When user's Step 1 score meets the requirement, and Step 2 score
             * is less than the required score yet within a 10-point range (and
             * equal or above the passing score of 209), give weight of 95. In
             * this situation, being a US citizen will give the boost
             * (weightCount = 5) to make weightCount a total of 100, meaning
             * that this particular school will still be recommended despite not
             * meeting the Step 2 cutoff.
             */
            else if (reqStep1 <= step1 && reqStep2 > step2) {
                if (step2 >= (reqStep2 - 10) && step2 >= 209) {
                    weightCount += 95;
                }
            }

            if (citizen.equalsIgnoreCase("Y")) {
                weightCount += 5;
            }

            // If weight is at least 100, add the residency list into applyList. applyList has all the residency programs
            // that match the user's stats.
            if (weightCount >= 100) {
                applyList.addToEntireList(filteredList.specificArray(k));
            }
        }
        if (applyList.getSize() == 0) {
            return "\n\n-- No matching programs.";
        }
        return "\n\nMatching programs: \n" + applyList;
    }

    /*
     * Orders programs from lowest to highest Step 1 score.
     */
    public String lowestToHighestStep1() {
        inputNumbers();
        HashSet<Integer> step1HashList = new HashSet<>();

        // Get the Step 1 score from every residency list and add to step1HashList. HashSet is used to remove any
        // duplicate scores.
        for (int i = 0; i < completeList.getSize(); ++i) {
            Integer temp = Integer.parseInt(completeList.specificArray(i).getStep1());
            step1HashList.add(temp);
        }

        int[] step1Array = new int[step1HashList.size()];
        int index = 0;

        // Convert to array.
        for (Integer each : step1HashList) {
            step1Array[index++] = each;
        }

        // Use quickSort method to arrange the elements (Step 1 scores) in the step1Array from least to greatest.
        quickSort(step1Array, 0, step1Array.length - 1);

        EntireResidencyList step1AllList = new EntireResidencyList();

        // For every step1Array element, go through completeList to find each residency Step 1 score and see if it matches
        // the step1Array element. If it does, then add the residency list from completeList to step1AllList.
        // Do this comparison for the entire length of step1Array.
        for (int i = 0; i < step1Array.length; ++i) {
            for (int j = 0; j < completeList.getSize(); ++j) {
                int step1Score1 = Integer.parseInt(completeList.specificArray(j).getStep1());
                int step1Score2 = step1Array[i];
                if (step1Score1 == step1Score2) {
                    step1AllList.addToEntireList(completeList.specificArray(j));
                }
            }
        }

        return "" + step1AllList;
    }

    /**
     * Orders programs from lowest to highest Step 2 score.
     */
    public String lowestToHighestStep2() {
        inputNumbers();
        HashSet<Integer> step1HashList = new HashSet<>();

        // Get the Step 2 score from every residency list and add to step1HashList. HashSet is used to remove any
        // duplicate scores.
        for (int i = 0; i < completeList.getSize(); ++i) {
            Integer temp = Integer.parseInt(completeList.specificArray(i).getStep2());
            step1HashList.add(temp);
        }

        int[] step2Array = new int[step1HashList.size()];
        int index = 0;

        // Convert to array.
        for (Integer each : step1HashList) {
            step2Array[index++] = each;
        }

        // Use quickSort method to arrange the elements (Step 2 scores) in the step2Array from least to greatest.
        quickSort(step2Array, 0, step2Array.length - 1);

        EntireResidencyList step2AllList = new EntireResidencyList();

        // For every step1Array element, go through completeList to find each residency Step 2 score and see if it matches
        // the step2Array element. If it does, then add the residency list from completeList to step2AllList.
        // Do this comparison for the entire length of step2Array.
        for (int i = 0; i < step2Array.length; ++i) {
            for (int j = 0; j < completeList.getSize(); ++j) {
                int step2Score1 = Integer.parseInt(completeList.specificArray(j).getStep2());
                int step2Score2 = step2Array[i];
                if (step2Score1 == step2Score2) {
                    step2AllList.addToEntireList(completeList.specificArray(j));
                }
            }
        }

        return "" + step2AllList;
    }

    /**
     * Searches for residencies that have Step 1 score less than or equal to
     * user input score.
     */
    public String searchStep1(int score) {
        inputNumbers();

        EntireResidencyList searchStep1List = new EntireResidencyList();

        for (int i = 0; i < completeList.getSize(); ++i) {

            int reqScore = Integer.parseInt(completeList.specificArray(i).getStep1());

            // User can apply to residencies that match the input score or have required scores less than the input score.
            if (reqScore <= score) {
                searchStep1List.addToEntireList(completeList.specificArray(i));
            }
        }
        if (searchStep1List.getSize() == 0) {
            return "\n\n-- No matching programs. Need higher score. 194 is the passing score.";
        }
        return "" + searchStep1List;
    }

    /**
     * Searches for residencies that have Step 2 score less than or equal to
     * user input score.
     */
    public String searchStep2(int score) {
        inputNumbers();

        EntireResidencyList searchStep2List = new EntireResidencyList();

        for (int i = 0; i < completeList.getSize(); ++i) {

            int reqScore = Integer.parseInt(completeList.specificArray(i).getStep2());

            // User can apply to residencies that match the input score or have required scores less than the input score.
            if (reqScore <= score) {
                searchStep2List.addToEntireList(completeList.specificArray(i));
            }
        }
        if (searchStep2List.getSize() == 0) {
            return "\n\n-- No matching programs. Need higher score. 209 is the passing score.";
        }
        return "" + searchStep2List;
    }

    /**
     * Finds residencies that match the medSchool argument (AMG/FMG/IMG).
     */
    public String findBySchool(String medSchool) {
        if (searchMedSchool(medSchool).getSize() == 0) {
            return "\n\n-- No matching programs.";
        }
        return "" + searchMedSchool(medSchool);
    }

    /**
     * Search by field based on user input.
     */
    public String findByField(String field) {
        inputNumbers();
        EntireResidencyList fieldList = new EntireResidencyList();

        // if residency list contains field that matches user input, then add that residency list to fieldList.
        for (int i = 0; i < completeList.getSize(); ++i) {
            if (completeList.specificArray(i).getField().equalsIgnoreCase(field)) {
                fieldList.addToEntireList(completeList.specificArray(i));
            }
        }
        if (fieldList.getSize() == 0) {
            return "\n\n-- No matching programs.";
        }
        return "" + fieldList;
    }

    /**
     * This filters residencies based on the type of medical school graduate
     * (AMG/FMG/IMG).
     */
    public EntireResidencyList searchMedSchool(String medSchool) {
        inputNumbers();
        filteredList = new EntireResidencyList();

        /**
         * Get the school type from each residency list. "/" splits the
         * getSchool() String to individual elements of the arrayList called
         * medSchoolList.
         */
        for (int i = 0; i < completeList.getSize(); ++i) {
            String school = completeList.specificArray(i).getSchool();

            // Output example: [AMG, FMG]
            ArrayList<String> medSchoolList = new ArrayList<>(Arrays.asList(school.split("\\s*/\\s*")));
            int[] countSchoolList = new int[medSchoolList.size()]; //

            /**
             * Accompanying each residency and its medSchoolList is a
             * countSchoolList array. Each school type is represented by a
             * number (AMG = 1, FMG = 2, IMG = 3). Depending on the school type
             * that exists, its number representation will be added into the
             * countSchoolList array. Output example: [1, 2] (if medSchoolList =
             * [AMG, FMG])
             */
            for (int j = 0; j < medSchoolList.size(); ++j) {
                if (medSchoolList.get(j).equalsIgnoreCase("amg")) {
                    countSchoolList[j] = 1;
                } else if (medSchoolList.get(j).equalsIgnoreCase("fmg")) {
                    countSchoolList[j] = 2;
                } else if (medSchoolList.get(j).equalsIgnoreCase("img")) {
                    countSchoolList[j] = 3;
                }
            }

            /**
             * Depending on the user input, the corresponding number will be
             * searched in the countSchoolList array, using a linear search. If
             * the user medSchool input is "AMG," the matching number is "1" as
             * seen earlier, so it will be searched in the countSchoolList. If
             * it exists, that means that the current residency list has AMG in
             * its criteria, so this list is added into the filteredList. Same
             * occurs with "FMG" and "IMG.
             */
            if (medSchool.equalsIgnoreCase("amg")) {
                if (schoolSearch(countSchoolList, 1)) {
                    filteredList.addToEntireList(completeList.specificArray(i));
                }
            } else if (medSchool.equalsIgnoreCase("fmg")) {
                if (schoolSearch(countSchoolList, 2)) {
                    filteredList.addToEntireList(completeList.specificArray(i));
                }
            } else if (medSchool.equalsIgnoreCase("img")) {
                if (schoolSearch(countSchoolList, 3)) {
                    filteredList.addToEntireList(completeList.specificArray(i));
                }
            }
        }
        return filteredList;
    }

    /**
     * Linear search method.
     */
    public boolean schoolSearch(int inputList[], int element) {
        for (int i = 0; i < inputList.length; ++i) {
            if (element == inputList[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Quicksort using recursion.
     */
    public void quickSort(int inputList[], int left, int right) {
        list = inputList;
        if (left >= right) {
            return;
        } else {
            int pIndex = partition(list, left, right);
            // Recursion
            quickSort(list, left, pIndex - 1);
            quickSort(list, pIndex + 1, right);
        }
    }

    /**
     * Partition used in quickSort.
     */
    public int partition(int list[], int left, int right) {
        int pivotPoint = list[right];
        int i = left - 1;

        for (int j = left; j < right; ++j) {
            if (list[j] < pivotPoint) {
                ++i;
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }
        int temp = list[i + 1];
        list[i + 1] = list[right];
        list[right] = temp;
        return i + 1;
    }

}
