import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public char[][] board;
    public char player1;
    public char player2;
    public boolean gameEnd;

    public TicTacToe(){
        board = new char[3][3];
        player1 = 'X';
        player2 = 'O';
        gameEnd = false;
        initializeBoard();
    }

    private void initializeBoard(){
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                board [i][j] = '-';
            }
        }
    }

    private void printBoard(){
        System.out.println("-------------");
        for(int i =0; i<3;i++){
            System.out.print("| ");
            for(int j=0;j<3;j++){
                System.out.print(board[i][j]+" | ");
            }
            System.out.println("\n-------------");
        }
    }

    private boolean isMoveValid(int[]position){
        if(position[0]<0||position[1]<0||position[0]>2||position[1]>2){
            return false;
        } else if (board[position[0]][position[1]] != '-') {
            return false;
        }
        return true;
    }

    private boolean isNum(String input){
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void makeMove(int[]position, int player){
        if(player==1){
            board [position[0]][position[1]] = player1;
        }
        else {
            board [position[0]][position[1]] = player2;
        }

    }

    private int[] cpuMove(){
        //position is row, column order
        int[] position = new int [2];
        Random random = new Random();
        int row = random.nextInt(3);
        int col = random.nextInt(3);
        position[0]=row;
        position[1]= col;
        return position;
    }

    private boolean boardFull(char[][] board){
        for(char []i:board){
            for(char j:i){
                if(j=='-'){
                    return false;
                }
            }
        }
        return true;
    }

    private int winner(){
        //check rows
        for(int i=0;i<3;i++){
            if (board[i][0] == player1 && board[i][1] == player1 && board[i][2] == player1) {
                return 1;
            }
            if (board[i][0] == player2 && board[i][1] == player2 && board[i][2] == player2) {
                return 2;
            }
        }

        //check columns
        for(int i=0;i<3;i++){
            if(board[0][i]==player1&&board[1][i]==player1&&board[2][i]==player1){
                return 1;
            }
            if(board[0][i]==player2&&board[1][i]==player2&&board[2][i]==player2){
                return 2;
            }
        }

        //check diagonal
        if(board[0][0]==player1&&board[1][1]==player1&&board[2][2]==player1){
            return 1;
        }
        if(board[0][2]==player1&&board[1][1]==player1&&board[2][0]==player1){
            return 1;
        }
        if(board[0][0]==player2&&board[1][1]==player2&&board[2][2]==player2){
            return 2;
        }
        if(board[0][2]==player2&&board[1][1]==player2&&board[2][0]==player2){
            return 2;
        }


        return 0;
    }

    public int[] position(){
        int[] position = new int[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your horizontal position (0-2):");
        String hinput = scanner.next();
        while(isNum(hinput)==false){
            System.out.println("Please enter your position again, and make sure you are entering a number between 0-2");
            hinput = scanner.next();
        }
        System.out.println("Please enter your vertical position(0-2):");
        String vinput = scanner.next();
        while(isNum(vinput)==false){
            System.out.println("Please enter your position again, and make sure you are entering a number between 0-2");
            vinput = scanner.next();
        }
        position[0]= Integer.parseInt(vinput);
        position[1]= Integer.parseInt(hinput);
        return position;

    }

    public void sp(){
        Scanner scanner = new Scanner(System.in);
        int[] position = new int[2];
        boolean full = false;
        int winner = 0;
        while(full==false&&winner!=1&&winner!=2){
            //player moves
            printBoard();
            position = position();
            while(isMoveValid(position)==false){
                System.out.println("The move is invalid, please enter your moves again:");
                position = position();
            }
            makeMove(position,1);
            full = boardFull(board);
            winner = winner();
            printBoard();

            //cpu moves
            boolean cpuValid = false;
            int[] cpuPosition = new int[2];
            while(cpuValid==false){
                cpuPosition = cpuMove();
                cpuValid = isMoveValid(cpuPosition);
            }
            makeMove(cpuPosition,2);
            full = boardFull(board);
            winner = winner();

        }
        if(winner==2){
            System.out.println("You lost! :( Thanks for playing!");
        } else if (winner==1) {
            System.out.println("You won! Thanks for playing!"); ;
        } else {
            System.out.println("The game was a draw! Thanks for playing!");
        }
    }

    public void mp(){
        Scanner scanner = new Scanner(System.in);
        int[] position = new int[2];
        //player 1
        boolean full = false;
        int winner = 0;
        whileloop:
        while(full==false&&winner!=1&&winner!=2) {
            //player 1
            printBoard();
            System.out.println("Player 1, it is your turn! You will be X");
            position = position();
            while(isMoveValid(position)==false){
                System.out.println("The move is invalid, please enter your moves again:");
                position = position();
            }
            makeMove(position,1);
            full = boardFull(board);
            winner = winner();
            if(full||winner!=0){
                break whileloop;
            }
            printBoard();

            //player 2

            System.out.println("Player 2, it is your turn! You will be O");
            position = position();
            while (isMoveValid(position) == false) {
                System.out.println("The move is invalid, please enter your moves again:");
                position = position();
            }
            makeMove(position, 2);
            full = boardFull(board);
            winner = winner();
        }
        if(winner==2){
            System.out.println("Player 2 won! Thanks for playing!");
        } else if (winner==1) {
            System.out.println("Player 1 won! Thanks for playing!"); ;
        } else {
            System.out.println("The game was a draw! Thanks for playing!");
        }
    }


    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        System.out.println("Welcome to the tic tac toe game, if you want to play single player, enter 1, if you want play with another player, enter 2");
        String input = scanner.next();

        if(!input.equals("1")&&input.equals("2")){
            while(!input.equals("1")&&!input.equals("2")){
                System.out.println("Please make sure that you are entering a number!");
                System.out.println("Please re enter your number, if you want to play single player, enter 1, if you want play with another player, enter 2");
                input = scanner.next();
            }
        }

        if(input.equals("1")){
            sp();
        } else if (input.equals("2")) {
            mp();
        }
        scanner.close();
    }

}
