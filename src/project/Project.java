package project;

/**
 * Laura O
 */
import java.util.Scanner;

public class Project {

    public static void test() {

        //Used for testing individual methods.
        //InputSeparate a = new InputSeparate();
        //System.out.println(a.inputNumbers());
        //System.out.println(a.searchMedSchool("amg"));
        //System.out.println(a.statMatch(220, 210, "y", "amg"));
        //System.out.println(a.lowestToHighestStep1());
        //System.out.println(a.findByField("pediatrics"));
        //System.out.println(a.findBySchool("amg"));
        //System.out.println(a.searchStep1(210));
        //System.out.println(a.searchStep2(220));
        Integer choice;
        Integer cycleNum = 0;

        Scanner scanner = new Scanner(System.in);
        InputSeparate a = new InputSeparate();

        do {
            System.out.println("Choose option:\n");
            System.out.println("1: Enter my stats for matches \n2. Display by lowest to highest score \n3. Search by score"
                    + "\n4. Search by type of medical school \n5. Search by field \n6. Finished\n");
            choice = Integer.parseInt(scanner.nextLine());

            if (!(choice >= 1 && choice <= 6)) {
                System.out.println("\nInvalid input.\n");
                cycleNum = -1; // created for allowing do-while loop to continue despite wrong input.
            }

            if (choice == 6) {
                System.out.println("\nDone.");
                break;
            } else if (choice == 1) {
                int step1;
                try {
                    System.out.println("\nEnter your Step 1 score:");
                    step1 = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("\nEnter your Step 1 score:");
                    step1 = Integer.parseInt(scanner.nextLine());
                }

                int step2;
                try {
                    System.out.println("\nEnter your Step 2 score:");
                    step2 = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("\nEnter your Step 2 score:");
                    step2 = Integer.parseInt(scanner.nextLine());
                }

                String citizen;
                do {
                    System.out.println("\nAre you a US citizen? Y/N:");
                    citizen = scanner.nextLine();
                } while (!(citizen.equalsIgnoreCase("y") || citizen.equalsIgnoreCase("n")));

                String medSchool;
                do {
                    System.out.println("\nAMG, FMG, or IMG?");
                    medSchool = scanner.nextLine();
                } while (!(medSchool.equalsIgnoreCase("amg") || medSchool.equalsIgnoreCase("fmg")
                        || medSchool.equalsIgnoreCase("img")));

                System.out.println(a.statMatch(step1, step2, citizen, medSchool) + "\n");
            } else if (choice == 2) {
                int step;
                do {
                    System.out.println("\nPress 1 for Step 1 or 2 for Step 2:");
                    step = Integer.parseInt(scanner.nextLine());
                    if (step == 1) {
                        System.out.println(a.lowestToHighestStep1() + "\n");
                    } else if (step == 2) {
                        System.out.println(a.lowestToHighestStep2() + "\n");
                    }
                } while (!(step == 1 || step == 2));
            } else if (choice == 3) {
                int step;
                do {
                    System.out.println("\nPress 1 for Step 1 or 2 for Step 2:");
                    step = Integer.parseInt(scanner.nextLine());
                    int searchScore;

                    if (step == 1) {
                        try {
                            System.out.println("\nEnter score:");
                            searchScore = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("\nEnter score:");
                            searchScore = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.println(a.searchStep1(searchScore) + "\n");
                    } else if (step == 2) {
                        try {
                            System.out.println("\nEnter score:");
                            searchScore = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("\nEnter score:");
                            searchScore = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.println(a.searchStep2(searchScore) + "\n");
                    }
                } while (!(step == 1 || step == 2));
            } else if (choice == 4) {
                String medSchool;
                do {
                    System.out.println("\nAMG, FMG, or IMG?");
                    medSchool = scanner.nextLine();
                } while (!(medSchool.equalsIgnoreCase("amg") || medSchool.equalsIgnoreCase("fmg")
                        || medSchool.equalsIgnoreCase("img")));
                System.out.println(a.findBySchool(medSchool) + "\n");
            } else if (choice == 5) {
                String field;
                try {
                    System.out.println("\nWhat field?");
                    field = scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("\nWhat field?");
                    field = scanner.nextLine();
                }
                System.out.println(a.findByField(field) + "\n");
            }

        } while (choice >= 1 && choice <= 6 || cycleNum == -1);
    }

}
