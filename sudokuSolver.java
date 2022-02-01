public class sudokuSolver {

    private static final int SIZE = 9;

    public static void main(String[] args){

        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        printBoard(board);

        if (solveBoard(board)){
            System.out.println("Solved correctly!");
        }
        else{
            System.out.println("Not possible!");
        }

        printBoard(board);
    }

    private static void printBoard(int[][] board){
        for (int row = 0; row < SIZE; row++){
            if (row % 3 == 0 && row != 0){
                System.out.println("-------------------");
            }
            for (int column = 0; column < SIZE; column++){
                if (column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInRow(int[][] board, int num, int row){
        for (int i = 0; i < SIZE; i++){
            if (board[row][i] == num){
                return true;
            }
        }
        return false;
    }

    private static boolean isInColumn(int[][] board, int num, int column){
        for (int i = 0; i < SIZE; i++){
            if (board[i][column] == num){
                return true;
            }
        }
        return false;
    }

    private static boolean isInBox(int[][] board, int num, int row, int column){
        int rowStart = row - row % 3;
        int columnStart = column - column % 3;
        for (int i = rowStart; i < rowStart + 3; i++){
            for (int j = columnStart; j < columnStart + 3; j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static  boolean isValidPlace(int[][] board, int num, int row, int column){
        return !isInRow(board, num, row) &&
                !isInColumn(board, num, column) &&
                !isInBox(board, num, row, column);
    }

    private static boolean solveBoard(int[][] board){
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValidPlace(board, num, row, column)) {
                            board[row][column] = num;
                            if (solveBoard(board)) {
                                return true;
                            }
                            else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}