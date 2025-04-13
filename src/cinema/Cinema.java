package cinema;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);

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
                case 0:
                    return;
                default:
                    System.out.println("Invalid option selected. Try again.");
            }
        }

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
        System.out.println("0. Exit");

    }
    public static void buyTicket(String[][] cinema, int rows, int seats){
            System.out.println("\nEnter a row number:");
            int rowSelected = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatSelected = scanner.nextInt();

            //ticket price
            int price = calculateTicketPrice(rows, seats, rowSelected);
            System.out.println("\nTicket price: $" + price);


            //print cinema scheme
            cinema[rowSelected - 1][seatSelected - 1] = "B";
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
        /*
        int totalSeats = rows * seats;
        int income;

        if (totalSeats <= 60){
            income = totalSeats * 10;

        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;

            income = frontRows * seatsInRow * 10 + backRows * seatsInRow * 8 ;
        }

        System.out.println("Total income:");
        System.out.println("$" + income);

         */

    }
}
