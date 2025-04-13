package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();

        int totalSeats = rows * seatsInRow;
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

    }
}
