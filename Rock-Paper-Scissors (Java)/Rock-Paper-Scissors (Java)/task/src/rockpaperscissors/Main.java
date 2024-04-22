package rockpaperscissors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the username and initialize the score
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine().trim();
        int userScore = readUserScore(userName);

        // Greet the user
        System.out.println("Hello, " + userName);

        while (true) {

            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equals("!exit")) {
                System.out.println("Bye!");
                break;
            } else if (userInput.equals("!rating")) {
                System.out.println("Your rating: " + userScore);
            } else if (isValidChoice(userInput)) {
                playGame(userInput, userScore);
            } else {
                System.out.println("Invalid input");
            }
        }

        scanner.close();
    }

    private static int readUserScore(String userName) {
        try {
            File ratingFile = new File("rating.txt");
            Scanner fileScanner = new Scanner(ratingFile);

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);

                if (name.equals(userName)) {
                    return score;
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            // File not found, use default score of 0
        }

        return 0;
    }

    private static void playGame(String userChoice, int userScore) {
        String computerChoice = getRandomChoice();
        System.out.println("The computer chose " + computerChoice);

        int result = determineResult(userChoice, computerChoice);

        if (result == 0) {
            System.out.println("There is a draw (" + userChoice + ")");
            userScore += 50;
        } else if (result == 1) {
            System.out.println("Well done. The computer chose " + computerChoice + " and failed");
            userScore += 100;
        } else {
            System.out.println("Sorry, but the computer chose " + computerChoice);
        }

        // Print the user's updated score
        System.out.println("Your rating: " + userScore);
    }

    private static int determineResult(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return 0; // Draw
        } else if ((userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))) {
            return 1; // Win
        } else {
            return -1; // Loss
        }
    }

    private static boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors");
    }

    private static String getRandomChoice() {
        Random random = new Random();
        int choiceIndex = random.nextInt(3);

        switch (choiceIndex) {
            case 0:
                return "rock";
            case 1:
                return "paper";
            case 2:
                return "scissors";
            default:
                return "unknown";
        }
    }
}
