package cinema;

import java.util.Scanner;

public class Cinema {

    private static int purchasedTickets;
    private static int totalSeats;
    private static int totalIncome;
    private static int currentIncome;


    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        totalSeats = rows * seats;
        totalIncome = rows * seats <= 60 ? rows * seats * 10 : rows / 2 * seats * 10 +
                (rows - rows / 2) * seats * 8;

        char[][] matrix = new char[rows + 1][seats + 1];

        populateCinema(matrix, rows + 1, seats + 1) ;

        showMenu(matrix, rows + 1, seats + 1);

    }

    private static void showMenu(char[][] matrix, int rows, int seats) {
        Scanner scanner = new Scanner(System.in);


        String menu = """
                
               1. Show the seats
               2. Buy a ticket
               3. Statistics
               0. Exit""";

        System.out.println(menu);
        while (scanner.hasNext()) {

            switch (scanner.nextInt()) {
                case 1:
                    printCinema(matrix, rows, seats);
                    break;
                case 2:
                    calculateTicketPrice(matrix, rows, seats);
                    break;
                case 3:
                    showStatistics();
                    break;
                default:
                    return;
            }
            System.out.println(menu);
        }

    }

    private static void showStatistics() {

        System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
        System.out.printf("Percentage: %.2f", 100.0 * purchasedTickets / totalSeats);
        System.out.println("%");
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);

    }
    private static void calculateTicketPrice(char[][] matrix, int rows, int seats) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();


        if (row > rows - 1 || seat > seats - 1) {
            System.out.println("Wrong input!\n");
            calculateTicketPrice(matrix, rows, seats);

        } else if (matrix[row][seat] == 'B') {
            System.out.println("That ticket has already been purchased!\n");
            calculateTicketPrice(matrix, rows, seats);

        } else {

            purchasedTickets++;
            matrix[row][seat] = 'B';
            if (rows * seats <= 60 || row <= (rows - 1) / 2) {
                currentIncome += 10;
                System.out.println("\nTicket price: $" + 10);

            } else {
                currentIncome += 8;
                System.out.println("\nTicket price: $" + 8);

            }
        }
    }

    private static void printCinema(char[][] matrix, int rows, int seats) {

        System.out.println("\nCinema:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {

                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void populateCinema(char[][] matrix, int rows, int seats) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {

                if (i == 0) {
                    matrix[i][j] = (char) ('0' + j);
                } else if (j == 0) {
                    matrix[i][j] = (char) ('0' + i);
                } else {
                    matrix[i][j] = 'S';
                }
            }
        }
        matrix[0][0] = ' ';
    }
}