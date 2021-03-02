package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        List<String> randomList = new ArrayList<>(List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b",
                "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z"));
        System.out.println("Input the length of the secret code:");
        int secretKeyLength = 0;
        try {
            secretKeyLength = scanner.nextInt();
        } catch (Exception exception) {
            System.out.printf("Error: \"%s\" isn't a valid number.", secretKeyLength);
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        int symbol = scanner.nextInt();

        if (secretKeyLength > symbol) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique " +
                    "symbols.", secretKeyLength, symbol);
            System.exit(0);
        } else if (symbol > 36) {
            System.out.printf("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }

        StringBuilder stringBuilder = new StringBuilder();
        int bulls = 0, cows = 0, turn = 1;
        String userGuess, secretKey;

        if (secretKeyLength > 0 && symbol > 1) {
            for (String s : randomList.subList(0, symbol)) {
                stringBuilder.append(s);
            }

            secretKey = stringBuilder.toString();
            String stars = new String(new char[secretKeyLength]).replace("\0", "*");

            if (symbol < 11) {
                System.out.printf("The secret is prepared: %s (%s-%s).\n", stars, randomList.get(0), randomList.get(symbol - 1));
            } else {
                System.out.printf("The secret is prepared: %s (%s-%s, %s-%s).\n", stars, randomList.get(0), randomList.get(9),
                        randomList.get(10), randomList.get(symbol - 1));
            }

            System.out.println("Okay, let's start a game!");

            while (true) {
                System.out.printf("Turn %d:\n", turn);
                userGuess = scanner.next();

                if (userGuess.equals(secretKey)) {
                    bulls = secretKey.length();
                } else {
                    for (int i = 0; i < userGuess.length(); i++) {
                        if (secretKey.contains(String.valueOf(userGuess.charAt(i)))) {
                            cows++;
                        }
                        if (secretKey.charAt(i) == userGuess.charAt(i)) {
                            bulls++;
                        }
                    }
                }

                if (bulls >= secretKeyLength) {
                    System.out.printf("Grade: %d bulls\n", bulls);
                    System.out.println("Congratulations! You guessed the secret code.");
                    break;
                } else {
                    if (bulls > 1 && cows > 1) {
                        System.out.printf("Grade: %d bulls and %d cows\n", bulls, cows);
                    } else if (bulls > 1 && cows <= 1) {
                        System.out.printf("Grade: %d bulls and %d cow\n", bulls, cows);
                    } else if (bulls <= 1 && cows > 1) {
                        System.out.printf("Grade: %d bull and %d cows\n", bulls, cows);
                    } else {
                        System.out.printf("Grade: %d bull and %d cow\n", bulls, cows);
                    }
                    turn++;
                    bulls = 0;
                    cows = 0;
                }
            }
        } else {
            System.out.println("error");
        }
    }
}
