import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Math.min;

public class AlphabetGenerator {

    private final static int ALPHABET_LEN = 26;
    private int[][] letterDependence;
    private boolean[] letterInAlphabet;
    private int[] dfsColor; // 0 - white, 1 - grey, 2 - black
    private LinkedList<Integer> alphPart;
    private LinkedList<Integer> alphSequence;

    public String generateAlphabet() {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        ArrayList<String> inputStr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            inputStr.add(scanner.nextLine());
        }
        if (!setLetterDependence(inputStr)) {
            return "Impossible";
        }
        if (!generateSequence()) {
            return "Impossible";
        }
        return sequenceToString();
    }

    private boolean setLetterDependence(ArrayList<String> sortedStrings) {
        letterDependence = new int[ALPHABET_LEN][ALPHABET_LEN];

        for (int i = 1; i < sortedStrings.size(); i++) {
            int minLen = min(sortedStrings.get(i - 1).length(), sortedStrings.get(i).length());
            int j;

            for (j = 0; j < minLen; j++) {
                int from = sortedStrings.get(i - 1).charAt(j) - 'a';
                int to = sortedStrings.get(i).charAt(j) - 'a';
                if (from != to) {
                    letterDependence[from][to]++;
                    break;
                }
            }
            if (j == minLen && sortedStrings.get(i).length() < sortedStrings.get(i - 1).length()) {
                return false;
            }
        }
        return true;
    }

    private boolean generateSequence() {
        letterInAlphabet = new boolean[ALPHABET_LEN];
        alphPart = new LinkedList<>();
        alphSequence = new LinkedList<>();

        for (int i = 0; i < ALPHABET_LEN; i++) {
            if (columnSum(i, letterDependence) == 0) {
                dfsColor = new int[ALPHABET_LEN];
                alphPart.clear();
                if (dfs(i)) {
                    alphSequence.addAll(alphPart);
                } else {
                    return false;
                }
            }
        }
        for (boolean bool : letterInAlphabet) {
            if (!bool) {
                return false;
            }
        }
        return true;
    }

    private int columnSum(int columnIndex, int[][] array) {
        int sum = 0;

        for (int[] from_letters : array) {
            sum += from_letters[columnIndex];
        }
        return sum;
    }

    private boolean dfs(int startLetter) {
        dfsColor[startLetter] = 1;

        for (int i = 0; i < ALPHABET_LEN; i++) {
            if (letterDependence[startLetter][i] != 0) {
                if (dfsColor[i] == 0) {
                    if (!dfs(i))
                        return false;
                } else if (dfsColor[i] == 1) {
                    return false;
                }
            }
        }
        dfsColor[startLetter] = 2;
        letterInAlphabet[startLetter] = true;
        alphPart.addFirst(startLetter);
        return true;
    }

    private String sequenceToString() {
        StringBuilder str = new StringBuilder();

        for (Integer letter : alphSequence) {
            str.append(Character.toChars(letter + 'a'));
        }
        return String.valueOf(str);
    }
}
