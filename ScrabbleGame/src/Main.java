import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String FILE_ADDRESS = "//Users//ameerali//Desktop//java assignment//ScrabbleGame//src//";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Board board = null;
        System.out.println("------ Scrabble Game------");
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        System.out.println("Please enter your choice (l/d):");
        String choice = scanner.nextLine();
        ArrayList<String> wordList = readTheWordList();
        boolean run = true;
        while (run) {
            if (choice.equals("l")) {
                board = readDateFromUserFile(scanner);
                run = false;
            } else if (choice.equals("d")) {
                String fileName = FILE_ADDRESS + "default_Board.txt";
                board = readDataFromDefaultFile(fileName);
                board.printBoard();
                run = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        int computerScore = 0;
        int humanScore = 0;
        showScore(computerScore, humanScore);
        String player = "Human";
        String play = "play";
        ArrayList<String> humanTiles = new ArrayList<>();
        int pass = 0;
        humanTiles = getTiles(humanTiles);
        boolean firstMove = true;
        Board tmp = board;
        while (play.equals("play")) {
            if (player.equals("Human")) {
                Map<Character, Integer> characterWithScore = new HashMap<>();
                System.out.println("It's your turn! Your tiles:");
                String command = "";
                String[] arr = command.split(",");
                while (true) {
                    for (int i = 0; i < humanTiles.size(); i++) {
                        System.out.print("[" + humanTiles.get(i) + "]");
                        if (i != humanTiles.size() - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                    command = scanner.nextLine();
                    if (command.equals("pass")) {
                        System.out.println("Human player passed. Computer player's turn.");
                        pass++;
                        break;
                    }
                    arr = command.split(",");
                    boolean allCharacterPresent = true;
                    for (int i = 0; i < arr[0].length(); i++) {
                        char c = String.valueOf(arr[0].charAt(i)).toUpperCase().charAt(0);
                        boolean present = false;
                        for (int j = 0; j < humanTiles.size(); j++) {
                            if (humanTiles.get(j).charAt(0) == c) {
                                present = true;
                                break;
                            }
                        }
                        if (!present) {
                            allCharacterPresent = false;
                            break;
                        }
                    }
                    System.out.println("All character present: " + allCharacterPresent);
                    if (!allCharacterPresent) {
                        System.out.println("You don't have all the characters to make this word. Please enter a valid word:");
                        continue;
                    }
                    if (wordList.contains(arr[0].toLowerCase())) {
                        if (!checkForValidMove(command, board)) {
                            System.out.println("This is not a valid move.\n" + "Please enter your move with letter sequence, position, and\n" + "direction (d for down, r for right) separated by commas.\n" + "Entering just two commas passes.\n");
                            continue;
                        }

                        for (int i = 0; i < arr[0].length(); i++) {
                            for (int j = 0; j < humanTiles.size(); j++) {
                                if (humanTiles.get(j).charAt(0) == arr[0].charAt(i)) {
                                    characterWithScore.put(String.valueOf(arr[0].charAt(i)).toUpperCase().charAt(0), Integer.parseInt(humanTiles.get(j).substring(1)));
                                    humanTiles.remove(j);
                                    break;
                                }
                            }
                        }
                        if (firstMove) {

                            int r = arr[1].charAt(0) - 'a';
                            int c = Integer.parseInt(arr[1].substring(1));
                            // find center of the board
                            int x = board.getBoard().length / 2;
                            int y = board.getBoard()[0].length / 2;
                            boolean isCenter = false;
                            if (r != x && c != y) {
                                // not a center
                                System.out.println("First move should be in the center of the board. Please enter a valid word:");
                                continue;
                            }
                            firstMove = false;
                            // if r move
                            if (arr[2].charAt(0) == 'r') {
                                for (int i = 0; i < arr[0].length(); i++) {
                                    humanScore += characterWithScore.get(arr[0].charAt(i)) * tmp.getBoard()[r][c + i].charAt(1) - '0';
                                    board.getBoard()[r][c + i] = String.valueOf(arr[0].charAt(i));
                                }
                            } else {
                                for (int i = 0; i < arr[0].length(); i++) {
                                    humanScore += characterWithScore.get(arr[0].charAt(i)) * tmp.getBoard()[i][c].charAt(1) - '0';
                                    board.getBoard()[r + i][c] = String.valueOf(arr[0].charAt(i));
                                }
                            }
                        } else {
                            int r = arr[1].charAt(0) - 'a';
                            int c = Integer.parseInt(arr[1].substring(1));
                            // direction
                            char direction = arr[2].charAt(0);
                            if (arr[2].charAt(0) == 'r') {
                                for (int i = 0; i < arr[0].length(); i++) {
                                    humanScore += characterWithScore.get(arr[0].charAt(i)) * tmp.getBoard()[r][c + i].charAt(1) - '0';
                                    board.getBoard()[r][c + i] = String.valueOf(arr[0].charAt(i));
                                }
                            } else {
                                for (int i = 0; i < arr[0].length(); i++) {
                                    humanScore += characterWithScore.get(arr[0].charAt(i)) * tmp.getBoard()[i][c].charAt(1) - '0';
                                    board.getBoard()[r + i][c] = String.valueOf(arr[0].charAt(i));
                                }
                            }
                        }
                        if (arr[0].length() == 7) {
                            humanScore += 70;
                        }
                        break;
                    } else {
                        System.out.println("This is not a valid word. Please enter a valid word:");
                    }
                }
                arr[0] = arr[0].toUpperCase();
                player = "Computer";
            } else {
                System.out.println("It's computer player's turn.");
                int random = (int) (Math.random() * wordList.size());
                String word = wordList.get(random);
                int[] wordValue = new int[word.length()];
                for (int i = 0; i < word.length(); i++) {
                    wordValue[i] = (int) (Math.random() * 7) + 1;
                }
                // generate random position from 0 to 14
                int r = (int) (Math.random() * 15);
                int c = (int) (Math.random() * 15);
                // generate random direction
                int direction = (int) (Math.random() * 2);
                if (direction == 0) {
                    if (c + word.length() > 15) {
                        c = c - word.length();
                    }
                    for (int i = 0; i < word.length(); i++) {
                        computerScore += wordValue[i] * tmp.getBoard()[r][c + i].charAt(1) - '0';
                        board.getBoard()[r][c + i] = String.valueOf(word.charAt(i));
                    }
                } else {
                    if (r + word.length() > 15) {
                        r = r - word.length();
                    }
                    for (int i = 0; i < word.length(); i++) {
                        computerScore += wordValue[i] * tmp.getBoard()[r + i][c].charAt(1) - '0';
                        board.getBoard()[r + i][c] = String.valueOf(word.charAt(i));
                    }
                }

                player = "Human";
            }
            humanTiles = getTiles(humanTiles);
            if (pass == 2) {
                System.out.println("Game is passed by two times. Game is over.");
                play = "stop";
            }
            System.out.println("--------------------");
            System.out.println("Now the Score is:");
            showScore(computerScore, humanScore);
        }
        if (computerScore > humanScore) {
            System.out.println("Computer player wins!");
        } else if (computerScore < humanScore) {
            System.out.println("Human player wins!");
        } else {
            System.out.println("It's a draw!");
        }

    }

    private static boolean checkForValidMove(String command, Board board) {
        String[] arr = command.split(",");
        String place = arr[1];
        if (arr[2].equals("r") || arr[2].equals("d")) {
            char ch = place.charAt(0);
            if (ch >= 'a' && ch <= 'z') {
                int number = Integer.parseInt(place.substring(1));
                return number >= 1 && number <= 15;
            }
            return false;
        }
        return false;
    }


    private static ArrayList<String> getTiles(ArrayList<String> humanTiles) {
        while (humanTiles.size() < 7) {
            int number = (int) (Math.random() * 26);
            char ch = (char) (number + 65);
            int number2 = (int) (Math.random() * 7) + 1;
            String tile = ch + "" + number2;
            if (!humanTiles.contains(tile)) {
                humanTiles.add(tile);
            }
        }
        return humanTiles;
    }

    private static ArrayList<String> readTheWordList() throws IOException {
        String fileName = FILE_ADDRESS + "wordList.txt";
        ArrayList<String> wordList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        String st;
        while ((st = br.readLine()) != null) {
            wordList.add(st);
        }
        return wordList;
    }

    private static void showScore(int computerScore, int humanScore) {
        System.out.println("The result is: ");
        System.out.println("Human player score:" + computerScore);
        System.out.println("Computer player score: " + humanScore);
    }

    private static Board readDateFromUserFile(Scanner sc) throws IOException {
        System.out.println("Please enter the file name of the board:");
        boolean run = true;
        while (run) {
            String fileName = sc.nextLine();
            fileName = FILE_ADDRESS + fileName;
            File file = new File(fileName);
            if (file.exists()) {
                return readDataFromDefaultFile(fileName);
            } else {
                System.out.println("This is not a valid file. Please enter the file name of the board:");
            }
        }
        return null;
    }

    private static Board readDataFromDefaultFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        Board board = null;
        boolean firstLine = true;
        int i = 0;
        while ((st = br.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                board = new Board(Integer.parseInt(st));
            } else {
                String[] arr = st.split(" ");
                for (int j = 0; j < arr.length; j++) {
                    board.getBoard()[i][j] = arr[j].trim();
                }
                i++;
            }
        }
        return board;
    }
}


