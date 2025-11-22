package cinema;

import java.util.Locale;

import static cinema.CinemaApp.readInt;

public class CinemaRoom {
    private final String[][] cinema;
    private final int rows;
    private final int seatsPerRow;
    private final int totalSeats;
    private static final int LIMIT_SEATS_SMALL_ROOM = 60;
    private static final int PRICE_FULL = 10;
    private static final int PRICE_DISCOUNT = 8;
    private int purchasedTickets = 0;
    private int currentIncome = 0;


    public CinemaRoom(int rows, int seatsPerRow) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.totalSeats = rows * seatsPerRow;
        this.cinema = new String[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                cinema[i][j] = "S";
            }
        }

    }

    public void printCinema() {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }

    }

    public boolean isValidSeat(int row, int seat) {
        return row >= 1 && row <= rows && seat >= 1 && seat <= seatsPerRow;
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
        if (totalSeats <= LIMIT_SEATS_SMALL_ROOM) {
            return PRICE_FULL;
        }
        int frontRows = rows / 2;
        return row <= frontRows ? PRICE_FULL : PRICE_DISCOUNT;
    }

    public int calculateTotalIncome() {
        if (totalSeats <= LIMIT_SEATS_SMALL_ROOM) {
            return totalSeats * PRICE_FULL;
        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;
            return frontRows * seatsPerRow * PRICE_FULL + backRows * seatsPerRow * PRICE_DISCOUNT;
        }
    }

    public void showStatistics() {

        double percentage = purchasedTickets * 100.00 / totalSeats;

        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf(Locale.US, "Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + calculateTotalIncome());

    }
}

