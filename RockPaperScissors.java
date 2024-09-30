import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {

    // Enum for selections
    enum Selection {
        NONE, ROCK, PAPER, SCISSORS
    }

    // Enum for results
    enum Result {
        DRAW, WON, LOST, ERROR
    }

    // Global variables to store previous and current user input
    static int prevInput = 0;
    static int currInput;

    // Figure out what the selection is from the specified integer
    public static Selection getSelectionForInteger(int inputInteger) {
        switch (inputInteger) {
            case 1:
                return Selection.ROCK;
            case 2:
                return Selection.PAPER;
            case 3:
                return Selection.SCISSORS;
            default:
                return Selection.NONE;
        }
    }

    // Returns a random number between min and max, inclusive of both
    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1); // generate random number between 1 and 3
    }

    // Returns the selection for the AI according to a certain strategy
    public static Selection getAISelection() {
        if (prevInput == currInput) {
            switch (currInput) {
                case 1:
                    return Selection.PAPER;
                case 2:
                    return Selection.SCISSORS;
                case 3:
                    return Selection.ROCK;
            }
        }
        prevInput = currInput;
        int number = getRandomNumber(1, 3);
        return getSelectionForInteger(number);
    }

    // Compares the user's selection to the AI's selection and determines the result of the round
    public static Result getResult(Selection userSelection, Selection aiSelection) {
        if (userSelection == aiSelection) {
            return Result.DRAW;
        } else if (userSelection == Selection.ROCK && aiSelection == Selection.PAPER) {
            return Result.LOST;
        } else if (userSelection == Selection.ROCK && aiSelection == Selection.SCISSORS) {
            return Result.WON;
        } else if (userSelection == Selection.PAPER && aiSelection == Selection.ROCK) {
            return Result.WON;
        } else if (userSelection == Selection.PAPER && aiSelection == Selection.SCISSORS) {
            return Result.LOST;
        } else if (userSelection == Selection.SCISSORS && aiSelection == Selection.ROCK) {
            return Result.LOST;
        } else if (userSelection == Selection.SCISSORS && aiSelection == Selection.PAPER) {
            return Result.WON;
        } else {
            return Result.ERROR;
        }
    }

    // Display instructions for the game
    public static void showInitialUI(int roundNum) {
        System.out.println("\nRound #" + roundNum + ":\n");
        System.out.println("Enter the number associated with the option below to select your move:");
        System.out.println("1. Rock");
        System.out.println("2. Paper");
        System.out.println("3. Scissors");
        System.out.println("\nQ. Quit the game");
        System.out.print("> ");
    }

    // Displays the appropriate string for each Selection
    public static void displaySelection(Selection selection) {
        switch (selection) {
            case ROCK:
                System.out.println("ROCK");
                break;
            case PAPER:
                System.out.println("PAPER");
                break;
            case SCISSORS:
                System.out.println("SCISSORS");
                break;
            default:
                System.out.println("NONE");
                break;
        }
    }

    // Displays an appropriate string for each Result
    public static void displayResult(Result result) {
        switch (result) {
            case WON:
                System.out.println("\n\n YOU have WON this round");
                break;
            case LOST:
                System.out.println("\n\n The COMPUTER has WON this round");
                break;
            case DRAW:
                System.out.println("\n\n This round is a DRAW");
                break;
            default:
                System.out.println("\n\nOops! Something has gone wrong!");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int roundNum = 1;

        Selection playerSelection;
        Selection aiSelection;

        System.out.println("Welcome to a game of Rock, Paper, Scissors!\n");

        while (true) {
            showInitialUI(roundNum);

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                System.exit(0);
            }

            int playerInputInt = Integer.parseInt(input);
            currInput = playerInputInt;

            playerSelection = getSelectionForInteger(playerInputInt);
            System.out.print("\nYou have Selected: ");
            displaySelection(playerSelection);

            aiSelection = getAISelection();
            System.out.print("\nThe Computer has Selected: ");
            displaySelection(aiSelection);

            Result result = getResult(playerSelection, aiSelection);
            displayResult(result);

            System.out.println("\nWould you like to have another round?");
            System.out.println("Press 'y' for Yes and 'n' for No.");
            String playGame = scanner.nextLine();

            if (!playGame.equalsIgnoreCase("y")) {
                break;
            }

            roundNum++;
        }

        System.out.println("\nThank you for playing!\n");
        scanner.close();
    }
}
