public class TicTacToe {
    private final int n;
    private final int[] rowSum, colSum;
    private int diagSum, revDiagSum;
    private int winner;

    public TicTacToe(int n) {
        this.n = n;
        rowSum = new int[n];
        colSum = new int[n];
    }

    // Makes a move at (row, col) by player (1 or 2).
    // Returns 0 if no one wins, 1 if player 1 wins, 2 if player 2 wins.
    public int move(int player, int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException("Invalid move: out of bounds");
        }

        int toAdd = (player == 1) ? 1 : -1;

        rowSum[row] += toAdd;
        colSum[col] += toAdd;

        if (row == col) {
            diagSum += toAdd;
        }
        if (row + col == n - 1) {
            revDiagSum += toAdd;
        }

        // Check win conditions in O(1)
        if (Math.abs(rowSum[row]) == n ||
            Math.abs(colSum[col]) == n ||
            Math.abs(diagSum) == n ||
            Math.abs(revDiagSum) == n) {
            winner=player;
        }

        return winner; // no winner yet
    }
    public int getWinner(){
        return winner;
    }
}
//Instead of storing the entire board, we maintain counts for each row, column, and both diagonals.
//player 1 adds +1, player 2 adds -1. If any count reaches n or -n, that player wins. This reduces space complexity to O(n) and keeps move operation O(1).
