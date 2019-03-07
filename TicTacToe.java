import java.util.Scanner;
import java.io.File;
import java.util.InputMismatchException;
public class TicTacToe
{
    private static final String BLANK = "  -";
    private static final String PLAYER_ONE = "  X";
    private static final String PLAYER_TWO = "  O";
    private static Scanner in;
    
    private int length = 4;
    
    String[][] board;
    
    public static void main(){
        TicTacToe a = new TicTacToe();
        int x = 4;
        a.createBoard(x);
        a.startProgram();
    }
    
    public void createBoard (int x) {
        
        board = new String[x][x];
        for (int row = 1; row < x; row++) {
            for (int col = 1; col < x; col++) {
                board[row][col] = BLANK;
            }
        }
        
    }
    
    public void printBoard() {
        for (int row = 0; row < 1; row++) {
            System.out.print("   ");
            for (int col = 1; col < board[0].length; col++) {
                System.out.print("  " + col);
            }
        }
        System.out.println();
        for (int row = 1; row < board.length; row++) {
            if (row < 10) {
                System.out.print(row + "  ");
            } else {
                System.out.print(row + " ");
            }
            
            
            for(int col = 1; col < board[0].length; col++) {
                System.out.print(board[row][col]);
                
            }
            System.out.println();
         
            
        }
        System.out.println();
        
    }
    
    public void resetBoard() {
        for (int row = 1; row < board.length; row++) {
            for (int col = 1; col < board[0].length; col++) {
                board[row][col] = BLANK;
            }
        }
    }
    
    public Coordinate inputCoord() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter row: ");
        int row = reader.nextInt();
        System.out.println("Enter col: ");
        int col = reader.nextInt();
        reader.close();
        return new Coordinate(row, col);
    }
    
    public void alterBoardSize() {
        System.out.println("Input board size: ");
        Scanner reader = new Scanner(System.in);
        boolean success = false;
        int size = 0;
        while (!success) {
            try {
                int s = reader.nextInt();
                success = true;
                size = s;
            } catch (InputMismatchException e) {
                reader.next();
                System.out.println("Invalid size. Try again");

                
            }
        }
        reader.close();
        if (size < 3) {
            System.out.println("Invalid size. Try again.");
            alterBoardSize();
        }
        
        System.out.println();
        System.out.println("Creating " + size + "x" + size + " board.");
        createBoard(size + 1);
        printBoard();
    }
    
    public void startProgram() {
        int option = -1;
        printBoard();
        boolean success = false;
        while (option != 5) {
            System.out.println("Choose an option: ");
            System.out.println("1: Single Player ");
            System.out.println("2: Co-op ");
            System.out.println("3: Reset ");
            System.out.println("4: Alter Board Size ");
            System.out.println("5: Quit ");
            Scanner reader = new Scanner(System.in);
            
            option = reader.nextInt();
            reader.close();
            
            if (option == 1) {
                start1PlayerGame();
                resetBoard();
            } else if (option == 2) {
                start2PlayerGame();
                resetBoard();
            } else if (option == 3) {
                resetBoard();
                printBoard();
            }else if (option == 4) {
                alterBoardSize();
            } else if (option == 5) {
                System.out.println("Goodbye.");
            }else {
                System.out.println("Invalid option.");
            }
        }
    }
    
    public void start1PlayerGame() {
        
        int rounds = ((board.length - 1)*(board.length - 1));

        for (int i = 0; i < rounds; i++) {
            if (i % 2 == 0) {
                System.out.println("Player 1 Choose Coordinate: ");
                playerTurn(PLAYER_ONE);
                printBoard();
                System.out.println();
                if (checkPlayerVictory(PLAYER_ONE) == true) {
                    System.out.println("Player Wins!!!!!!!!");
                    break;
                }
            } else {
                System.out.println("Computer Choosing Coordinate... ");
                computerTurn();
                printBoard();
                if (checkPlayerVictory(PLAYER_TWO) == true) {
                    System.out.println("Computer Wins!!!!!!!! You are a disappointment to your species.");
                    break;
                }
            }
            if (i == rounds - 1) {
                System.out.println("Nobody wins. Draw!!!!!!!!");
            }
        }
    }
    
    public void start2PlayerGame() {
        
        int rounds = ((board.length - 1)*(board.length - 1));
        
        for (int i = 0; i < rounds; i++) {
            if (i % 2 == 0) {
                System.out.println("Player 1 Choose Coordinate: ");
                
                playerTurn(PLAYER_ONE);
                printBoard();
                System.out.println();
                if (checkPlayerVictory(PLAYER_ONE) == true) {
                    System.out.println("Player 1 Wins!!!!!!!!");
                    break;
                }
            } else {
                System.out.println("Player 2 Choose Coordinate: ");
                playerTurn(PLAYER_TWO);
                printBoard();
                System.out.println();
                if (checkPlayerVictory(PLAYER_TWO) == true) {
                    System.out.println("Player 2 Wins!!!!!!!!");
                    break;
                }
            }
            if (i == rounds - 1) {
                System.out.println("Rounds: " + ((board.length - 1)^2));
                System.out.println("Nobody wins. Draw!!!!!!!!");
            }
        }
        
    }
    
    public void playerTurn(String player) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Row: ");
        boolean success = false; 
        int r = 0;
        int c = 0;
        while (!success) {
            try {
                int row = reader.nextInt();
                success = true;
                r = row;
            } catch (InputMismatchException e) {
                reader.next();
                System.out.println("Invalid coordinate. Try again");
                System.out.println("Row: ");
                
            }
        }
        System.out.print("Col: ");
        success = false;
        while (!success) {
            try {
                int col = reader.nextInt();
                success = true;
                c = col;
            } catch (InputMismatchException e) {
                reader.next();
                System.out.println("Invalid coordinate. Try again");
                System.out.println("Col: ");
            }
        }
       
        System.out.println();
        if (isValidCoord(r, c) && board[r][c] == BLANK) {
            board[r][c] = player;
        } else {
            System.out.println("Invalid coordinate. Try again");
            playerTurn(player);
        }
    }
    
    
    public void computerTurn() {
        
        Coordinate winningCoord = checkImminentVictory(PLAYER_ONE);
        
        if (winningCoord != null) {
            board[winningCoord.getRow()][winningCoord.getCol()] = PLAYER_TWO;
            return;
        }
        
        winningCoord = checkImminentVictory(PLAYER_TWO);
        
        if (winningCoord != null) {
            board[winningCoord.getRow()][winningCoord.getCol()] = PLAYER_TWO;
            return;
        }
        
        int row = 0;
        int col = 0;
        
        while(!isValidCoord(row, col) || board[row][col] != BLANK) {
            row = (int)((Math.random()*board.length - 1) + 1);
            col = (int)((Math.random()*board[0].length - 1) + 1);
        }
        
        //if (row != 0 && col != 0) {
            board[row][col] = PLAYER_TWO;
        //}
        
        
        
    }
    
    public Coordinate checkImminentVictory(String player) {
        int blankCount = 0;
        Coordinate winningCoord = null; 
        
        // check rows
        for (int row = 1; row < board.length; row++) {
            for (int col = 1; col < board[0].length; col++) {
                if (board[row][col] == player) {
                    blankCount = 0;
                    break;
                } else if (board[row][col] == BLANK) {
                    blankCount++;
                    winningCoord = new Coordinate(row, col);
                }
            }
            
            if(blankCount == 1) {
                return winningCoord;
            }
        }
        
        blankCount = 0;
        winningCoord = null;
        
        // check cols
        for (int col = 1; col < board[0].length; col++) {
            for (int row = 1; row < board.length; row++) {
                if (board[row][col] == player) {
                    blankCount = 0;
                    break;
                } else if (board[row][col] == BLANK) {
                    blankCount++;
                    winningCoord = new Coordinate(row, col);
                }
            }
            
            if(blankCount == 1) {
                return winningCoord;
            }
        }
        
        blankCount = 0;
        winningCoord = null;
        
        // check diagonal: top left - bot right
        for (int row = 1; row < board.length; row++) {
            if (board[row][row] == player) {
                blankCount = 0;
                break;
            } else if (board[row][row] == BLANK){
                blankCount++;
                winningCoord = new Coordinate(row, row);
            }
            
        }
        
        if(blankCount == 1) {
            return winningCoord;
        }
        
        blankCount = 0;
        winningCoord = null;
        
        // check diagonal: bot left - top right
        int col = 1;
        for (int row = board.length - 1; row > 0; row--) {
            if (board[row][col] == player) {
                blankCount = 0;
                break;
            } else if (board[row][col] == BLANK) {
                blankCount++;
                winningCoord = new Coordinate(row, col);
            }
            col++;
        }
        
        if(blankCount == 1) {
            return winningCoord;
        }
        
        return null;
    }
    
    
    
    public boolean checkPlayerVictory(String player) {
        for (int row = 1; row < board.length; row++) {
            for (int col = 1; col < board[0].length; col++) {
                if (board[row][col] != player) {
                    break;
                } else if (col == board.length - 1) {
                    return true;
                }
            }
        }
        
        for (int col = 1; col < board[0].length; col++) {
            for (int row = 1; row < board.length; row++) {
                if (board[row][col] != player) {
                    break;
                } else if (row == board.length - 1) {
                    return true;
                }
            }
        }
        
        for (int row = 1; row < board.length; row++) {
            if (board[row][row] != player) {
                break;
            } else if (row == board.length - 1){
                return true;
            }
            
        }
        
        int col = 1;
        for (int row = board.length - 1; row > 0; row--) {
            if (board[row][col] != player) {
                break;
            } else if (row == 1) {
                return true;
            }
            col++;
        }
        return false;
    }
    
    
    public boolean isValidCoord(int x, int y) {          
        
        if (x < 1 || x > board[0].length - 1 || y < 1 || y > board.length - 1) {
            return false;
        } else{
            return true;
        }
    }
    
    
    
}
