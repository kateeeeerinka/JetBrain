package cinema;
import java.util.Scanner;

public class Cinema {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();

        String choose = """
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                """;

        char[][] rows = new char[numRows][numSeats];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numSeats; j++) {
                rows[i][j] = 'S';
            }
        }

        while (true) {
            System.out.println();
            System.out.print(choose);
            int n = scanner.nextInt();
            if (n == 1) {
                printSeats(rows);
            } else if (n == 2) {
                BuyTicket(rows);
            } else if (n == 3) {
                Statistic(rows);
            } else {
                break;

            }

        }
    }

    ////////////////////////////////////////////////////Вивід квитків:
    public static void printSeats(char[][] seats) {
        System.out.println("\n" + "Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    /////////////////////////////////////////////////////////////////////
public static int Price( int totalSeats, char[][] seats, int row1) {

    int money;
    if (totalSeats <= 60) {
        money = 10;
    } else if (totalSeats > 60 && row1 <= seats[0].length / 2) {
        money = 10;
    } else {
        money = 8;
    }
    return money;
}
    /////////////////////////////////////////2
    public static void BuyTicket(char[][] seats) {
        Scanner scanner = new Scanner(System.in);
        int row1 = 0;
        int seat = 0;
        while (true) {
            System.out.println("\n" + "Enter a row number:");
            row1 = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();

            int totalSeats = seats[0].length * seats.length;
            if (row1 >= 1 && row1 <= seats.length && seat >= 1 && seat <= seats[0].length && seats[row1-1][seat-1] == 'S') {
                seats[row1 - 1][seat - 1] = 'B';
                System.out.println("\n" + "Ticket price: " + "$" + Price(totalSeats, seats, row1));
                System.out.println(" ");
                break;
            } else if (row1 >= 1 && row1 <= seats.length && seat >= 1 && seat <= seats[0].length && seats[row1-1][seat-1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                System.out.println("Wrong input!");
            }
        }

        }


    /////////////////////////////////////////////////////////////////////////////
    public static int totalIncome(int R, int S){

    int totalSeats = R * S;
    int money;
       if(totalSeats <=60){
        money = totalSeats * 10;
    }else{
        money = ((R / 2) * S * 10) + ((R- (R / 2)) * S * 8);
    }
      return money;
}
    ///////////////////////////// statistic:
    public static void Statistic(char[][] seats) {
        int tickets = 0;
        int money = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 'B') {
                    tickets++;
                    money += Price(seats.length * seats[0].length, seats, i+1);
                }
            }
        }

        int total = seats.length * seats[0].length;
        double percent = (double) tickets / total;
        String formattedPercent = String.format("%.2f", percent * 100.0).replace(',', '.');

        System.out.println();
        System.out.println("Number of purchased tickets: " + tickets);
        System.out.println("Percentage: " + formattedPercent + "%");
        System.out.println("Current income: $" + money);
        System.out.println("Total income: $" + totalIncome(seats.length, seats[0].length));
    }



    }
