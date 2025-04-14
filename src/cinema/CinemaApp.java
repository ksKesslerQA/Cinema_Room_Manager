package cinema;

import java.util.Scanner;

public class CinemaApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int rows = readInt("Enter the number of rows:");
        int seats = readInt("Enter the number of seats in each row:");

        CinemaRoom cinemaRoom = new CinemaRoom(rows, seats);

        while (true) {
            printMenu();
            int optionSelected = readInt("Select an option:");

            switch (optionSelected) {
                case 1:
                    cinemaRoom.printCinema();
                    break;
                case 2:
                    cinemaRoom.buyTicket();
                    break;
                case 3:
                    cinemaRoom.showStatistics();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option selected. Try again.");
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

    }

    public static int readInt(String text) {
        while (true) {
            System.out.println(text);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
}
