package cinema;

import java.util.Objects;
import java.util.Scanner;
import java.util.Locale;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    static int purchasedTickets = 0;
    static int currentIncome = 0;

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        String[][] cinema = createCinema(rows, seats);

        while (true) {
            printMenu();
            int optionSelected = scanner.nextInt();

            switch (optionSelected) {
                case 1:
                    printCinema(cinema);
                    break;
                case 2:
                    buyTicket(cinema, rows, seats);
                    break;
                case 3:
                    showStatistics(rows, seats);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option selected. Try again.");
            }
        }

    }

    private static void showStatistics(int rows,int seats) {
        int totalSeats = rows * seats;
        int totalIncome;

        if (totalSeats <= 60){
            totalIncome = totalSeats * 10;

        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;

            totalIncome = frontRows * seats * 10 + backRows * seats * 8 ;
        }
        double percentagePurchTickets = purchasedTickets * 100.00 / totalSeats;

        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf(Locale.US, "Percentage: %.2f%%\n", percentagePurchTickets);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);

    }

    public static String[][] createCinema(int rows,int seats){
        String[][] cinema = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S";
            }
        }
        return cinema;
    }

    public static void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

    }

    public static void buyTicket(String[][] cinema, int rows, int seats){
        while (true) {
            System.out.println("\nEnter a row number:");
            int rowSelected = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatSelected = scanner.nextInt();

            if (rowSelected < 1 || rowSelected > rows || seatSelected < 1 || seatSelected > seats) {
                System.out.println("\nWrong input!");
                continue;
            }

            if (Objects.equals(cinema[rowSelected - 1][seatSelected - 1], "S")) {

                int price = calculateTicketPrice(rows, seats, rowSelected);
                System.out.println("\nTicket price: $" + price);

                cinema[rowSelected - 1][seatSelected - 1] = "B";
                purchasedTickets += 1;
                currentIncome += price;
                break;
            } else {
                System.out.println("\nThat ticket has already been purchased!");
            }
        }
    }

    public static int calculateTicketPrice(int rows, int seats, int rowSelected) {
            int totalSeats = rows * seats;
            if (totalSeats <= 60) {
                return 10;
            }
            int frontRows = rows / 2;
            return rowSelected <= frontRows ? 10 : 8;
        }

    public static void printCinema(String[][] cinema) {
            System.out.println("\nCinema:");
            System.out.print("  ");
            for (int i = 1; i <= cinema[0].length; i++) {  //seats
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < cinema.length; i++) { //rows
                System.out.print((i + 1) + " ");
                for (int j = 0; j < cinema[i].length; j++) {
                    System.out.print(cinema[i][j] + " ");
                }
                System.out.println();
            }

    }
}
