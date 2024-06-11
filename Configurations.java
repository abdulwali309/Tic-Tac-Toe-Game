public class Configurations {
    private char[][] board;
    private int boardSize;
    private int toWin; // Represents the number of consecutive tiles needed to win
    private int maxLevels;

    public Configurations(int boardSize, int toWin, int maxLevels) {
        this.boardSize = boardSize;
        this.toWin = toWin;
        this.maxLevels = maxLevels;
        this.board = new char[boardSize][boardSize];
        // Initialize the board with empty spaces
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public HashDictionary createDictionary() {
        // You can select the size of the hash table here
        return new HashDictionary(10000); // Adjust size as needed
    }

    public int repeatedConfiguration(HashDictionary hashTable) {
        StringBuilder sb = new StringBuilder();
        // Convert board to a string configuration
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[i][j]);
            }
        }
        String config = sb.toString();
        // Check if the configuration exists in the hash table
        return hashTable.get(config);
    }

    public void addConfiguration(HashDictionary hashDictionary, int score) {
        StringBuilder sb = new StringBuilder();
        // Convert board to a string configuration
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sb.append(board[i][j]);
            }
        }
        String config = sb.toString();
        // Add the configuration to the hash table
        hashDictionary.put(new Data(config, score));
    }

    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    public boolean squareIsEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    public boolean wins(char symbol) {
        // Check horizontal lines
        for (int row = 0; row < board.length; row++) {
            int count = 0;
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == symbol) {
                    count++;
                    if (count >= toWin) {
                        return true; // Winning condition met
                    }
                } else {
                    count = 0; // Reset count if consecutive sequence breaks
                }
            }
        }

        // Check vertical lines
        for (int col = 0; col < board[0].length; col++) {
            int count = 0;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] == symbol) {
                    count++;
                    if (count >= toWin) {
                        return true; // Winning condition met
                    }
                } else {
                    count = 0; // Reset count if consecutive sequence breaks
                }
            }
        }

        // Check main diagonal (top-left to bottom-right)
        for (int i = 0; i <= board.length - toWin; i++) {
            for (int j = 0; j <= board[i].length - toWin; j++) {
                int count = 0;
                for (int l = 0; l < toWin; l++) {
                    if (board[i + l][j + l] == symbol) {
                        count++;
                        if (count >= toWin) {
                            return true; // Winning condition met
                        }
                    } else {
                        count = 0; // Reset count if consecutive sequence breaks
                    }
                }
            }
        }

        // Check anti-diagonal (top-right to bottom-left)
        for (int i = 0; i <= board.length - toWin; i++) {
            for (int j = toWin - 1; j < board[i].length; j++) {
                int count = 0;
                for (int l = 0; l < toWin; l++) {
                    if (board[i + l][j - l] == symbol) {
                        count++;
                        if (count >= toWin) {
                            return true; // Winning condition met
                        }
                    } else {
                        count = 0; // Reset count if consecutive sequence breaks
                    }
                }
            }
        }

        return false; // No winning condition met
    }

    public boolean isDraw() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    return false; // If any empty space is found, the game is not a draw
                }
            }
        }
        return true; // If no empty spaces are found, the game is a draw
    }

    public int evalBoard() {
        if (wins('O')) {
            return 3; // Computer wins
        } else if (wins('X')) {
            return 0; // Human wins
        } else if (isDraw()) {
            return 2; // Draw
        } else {
            return 1; // Undecided
        }
    }
}