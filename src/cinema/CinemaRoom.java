package cinema;

import java.util.Locale;

import static cinema.CinemaApp.readInt;

public class CinemaRoom {
    private final String[][] cinema;
    private final int rows;
    private final int seats;
    private int purchasedTickets = 0;
    private int currentIncome = 0;

    public CinemaRoom(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
        this.cinema = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = "S";
            }
        }
    }

    public void printCinema() {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }

    }

    public boolean isValidSeat(int row, int seat) {
        return row >= 1 && row <= rows && seat >= 1 && seat <= seats;
    }

    public void buyTicket(){
        while (true) {
            int row = readInt("\nEnter a row number:");
            int seat = readInt("Enter a seat number in that row:");

            if (!isValidSeat(row, seat)) {
                System.out.println("\nWrong input!");
                continue;
            }

            if (cinema[row - 1][seat - 1].equals("B")) {
                System.out.println("\nThat ticket has already been purchased!");
                continue;
            } else {
                int price = calculateTicketPrice(row);
                System.out.println("\nTicket price: $" + price);

                cinema[row - 1][seat - 1] = "B";
                purchasedTickets++;
                currentIncome += price;
                break;
            }
        }
    }

    public int calculateTicketPrice(int row) {
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            return 10;
        }
        int frontRows = rows / 2;
        return row <= frontRows ? 10 : 8;
    }

    public int calculateTotalIncome() {
        int totalSeats = rows * seats;

        if (totalSeats <= 60) {
            return totalSeats * 10;
        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;
            return frontRows * seats * 10 + backRows * seats * 8;
        }
    }

    public void showStatistics() {
        int totalSeats = rows * seats;
        double percentage = purchasedTickets * 100.00 / totalSeats;

        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf(Locale.US, "Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + calculateTotalIncome());

    }
}

