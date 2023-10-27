package lab2;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String folderPath = "src" + File.separator + "lab2" + File.separator + "working_folder";
        DocumentMonitor monitor = new DocumentMonitor(folderPath);
        monitor.startMonitoring();

        System.out.println("Document Monitor");
        System.out.println("Available commands:");
        System.out.println("1. commit");
        System.out.println("2. info <filename>");
        System.out.println("3. status");
        System.out.println("4. exit");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("commit")) {
                monitor.commit();
            } else if (command.startsWith("info ")) {
                String fileName = command.substring(5);
                monitor.printFileInfo(fileName);
            } else if (command.equals("status")) {
                monitor.printStatus();
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting Document Monitor.");
    }
}