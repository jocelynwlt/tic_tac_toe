import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the tic tac toe game, if you want to play single player, enter 1, if you want play with another player, enter 2");
        String input = scanner.next();

    }
    public int[][] board;

    public void initializeBoard(){
        for(int i=0; i<3;i++){
            for(int j=0;j<i;j++){

            }
        }
    }
}
