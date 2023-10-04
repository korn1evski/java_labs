package lab1.helpers;

import lab1.enums.StudyField;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInputHelper {
    public static StudyField promptForStudyField(String message, Scanner scanner) {
        StudyField result = null;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                result = StudyField.valueOf(input);
                isValidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid StudyField.");
            }
        }
        return result;
    }

    public static int promptForValidInteger(String message, Scanner scanner) {
        int result = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                result = Integer.parseInt(input);
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return result;
    }

    public static String promptForValidEmail(String message, Scanner scanner) {
        String result = null;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                if (isValidEmail(input)) {
                    result = input;
                    isValidInput = true;
                } else {
                    throw new IllegalArgumentException("Invalid email address. Please enter a valid email.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    public static String[] promptForBirthday(String message, Scanner scanner){
        String[] result = null;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                result = input.split("\\.");
                if(result.length != 3)
                    throw new IllegalArgumentException();
                isValidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter a valid StudyField.");
            }
        }
        return result;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
}
