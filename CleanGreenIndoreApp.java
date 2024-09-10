package com.example.CleanGreenIndore;

import java.util.Scanner;

public class CleanGreenIndoreApp {

    public static void main(String[] args) {
        // Play welcome music when the app starts
        AudioPlayer ad=new AudioPlayer();
        ad.playSong("C:\\Users\\lenovo\\Desktop\\2018030953final.wav");
    ProgramService programService = new ProgramService();
        VolunteerService volunteerService = new VolunteerService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("** Clean Green Indore **");
            System.out.println("1. Program Operations");
            System.out.println("2. Volunteer Operations");
            System.out.println("3. Exit");
            System.out.println("Enter your choice:");

            int mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    // Program Operations Submenu
                    programOperationsMenu(programService, scanner);
                    break;
                case 2:
                    // Volunteer Operations Submenu
                    volunteerOperationsMenu(volunteerService, scanner);
                    break;
                case 3:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
        }
    }

    // Submenu for Program Operations
    public static void programOperationsMenu(ProgramService programService, Scanner scanner) {
        while (true) {
            System.out.println("** Program Operations **");
            System.out.println("1. Add New Program");
            System.out.println("2. Display All Programs");
            System.out.println("3. Update Program");
            System.out.println("4. Delete Program");
            System.out.println("5. Back to Main Menu");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    programService.addNewProgram();
                    break;
                case 2:
                    programService.displayAllPrograms();
                    break;
                case 3:
                    programService.updateProgram();
                    break;
                case 4:
                    programService.deleteProgram();
                    break;
                case 5:
                    return;  // Back to main menu
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
        }
    }

    // Submenu for Volunteer Operations
    public static void volunteerOperationsMenu(VolunteerService volunteerService, Scanner scanner) {
        while (true) {
            System.out.println("** Volunteer Operations **");
            System.out.println("1. Add New Volunteer");
            System.out.println("2. Display All Volunteers");
            System.out.println("3. Update Volunteer");
            System.out.println("4. Delete Volunteer");
            System.out.println("5. Back to Main Menu");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    volunteerService.addNewVolunteer();
                    break;
                case 2:
                    volunteerService.displayAllVolunteers();
                    break;
                case 3:
                    volunteerService.updateVolunteer();
                    break;
                case 4:
                    volunteerService.deleteVolunteer();
                    break;
                case 5:
                    return;  // Back to main menu
                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
        }
    }
}