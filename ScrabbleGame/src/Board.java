
public class Board {
    private int length;
    private String[][] board;

    public Board(int length) {
        this.length = length;
        this.board = new String[length][length];
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public void printBoard() {
        for (int i = 0; i < this.length; i++) {
            if (i == 0) {
                System.out.print("   ");
            }
            if (i % 2 == 1) {
                System.out.print((char) (i + 97) + "  ");
            } else {
                System.out.print((char) (i + 97) + "   ");
            }
        }
        System.out.println();
        for (int i = 0; i < this.length; i++) {
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < this.length; j++) {
                System.out.print(this.board[i][j] + "  ");
            }
            System.out.println();
        }

    }
}

