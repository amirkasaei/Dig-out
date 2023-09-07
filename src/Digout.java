import java.util.Scanner;

public class Digout {
//header
    static void header(){
        System.out.println("\t\t\t   DigOut\n___________________________________");
    }
//rock gravity
    static void rock(char[] board, byte row, byte col){
        for (byte i = (byte) (row*col-1); i >= col; i--) {
            if (board[i-col] == '@'){
                for (byte j = i; j < row*col ; j+=col) {
                    if (board[j] == ' ') {
                        board[j-col] = ' ';
                        board[j] = '@';
                    }
                    else
                        break;
                }
            }
        }
    }
//wrong function
    static void wrong(){
        System.out.print("Wrong Function!\nEnter another function: ");
    }
//board generator
    static void generateboard(char[] board, byte row, byte col, byte coin){
        //board generating
        char[] symbols = {'@','#',' '};
        byte r=0;
        for (byte i = 0; i < row*col; i++) {
            if (r > row) {
                symbols[0] = '#';
            }
            board[i]= symbols[(byte)(3*Math.random())];
            if (board[i] == '@') {
                r++;
            }
        }
        //rock checking
        rock(board, row, col);
        //coin generating
        byte index;
        while (coin > 0) {
            index = (byte)(row*col*Math.random());
            if (index != (row-1)*col && board[index] != '$') {
                board[index] = '$';
                coin--;
            }
        }
        //player char
        board[(row-1)*col] = '!';
    }
//board printer
    static void printBoard(char[] board,byte row,byte col) {
        //top of board
        for (byte i = 0; i < col; i++) {
            if (i == 0)
                System.out.print(" _");
            else
                System.out.print('_');
        }
        System.out.println();

        //left and right side of the board and the board itself
        for (byte i = 0; i < row*col; i++) {
            if (i%col == 0)
                System.out.print("|");
            if (i%col == col-1)
                System.out.println(board[i] + "|");
            else
                System.out.print(board[i]);
        }

        //bottom of the board
        for (byte i = 0; i < col; i++) {
            if (i == 0)
                System.out.print(" _");
            else
                System.out.print('_');
        }
        System.out.println();
    }
//game process
    static void game(String name){
        Scanner scanner = new Scanner(System.in);

        //number of rows and columns
        System.out.print("\nEnter the board rows & columns: ");
        byte row = scanner.nextByte();
        byte col = scanner.nextByte();
        byte place = (byte) ((row-1)*col);

        //number of coins
        System.out.print("\nplease Enter the number of coins: ");
        byte coin = scanner.nextByte();

        //board creating (not an impossible one)
        char[] board = new char[row*col];
        do {
            generateboard(board,row,col,coin);
        } while (board[place-col] == '@' && board[place+1] == '@');

        //game play
        header();
        printBoard(board,row, col);
        char func;
        //use for preventing double "enter function" string in wrong entrance
        boolean wrongfunc = false;

        do {
            if (!wrongfunc) {
                System.out.print("Enter the function: ");
            }
            wrongfunc = false;

            //game functions
            func = scanner.next().charAt(0);
            switch (func){
                //up
                case 'U':
                    //wrong function
                    try {
                        if (place-col < 0 || ((board[place-col] != ' ')&&(board[place-col] != '$')) || board[place-2*col] == '@') {
                            wrong();
                            wrongfunc = true;
                            continue;
                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                    //coin counter
                    if (board[place-col] == '$') {
                        coin--;
                    }
                    //move
                    board[place] = ' ';
                    place -= col;
                    board[place] = '!';
                    break;

                //down
                case 'D':
                    //wrong function
                    try {
                        if (place+col > row*col-1 || ((board[place+col] != ' ')&&(board[place+col] != '$'))) {
                            wrong();
                            wrongfunc = true;
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //coin counter
                    if (board[place+col] == '$') {
                        coin--;
                    }
                    //move
                    board[place] = ' ';
                    place += col;
                    board[place] = '!';
                    break;

                //left
                case 'L':
                    //wrong function
                    try {
                        if ((place-1)%col == col-1 || ((board[place-1] != ' ')&&(board[place-1] != '$'))) {
                            wrong();
                            wrongfunc = true;
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //coin counter
                    if (board[place-1] == '$'){
                        coin--;
                    }
                    //move
                    board[place] = ' ';
                    place--;
                    board[place] = '!';
                    break;

                //right
                case 'R':
                    //wrong function
                    try {
                        if ((place+1)%col == 0|| ((board[place+1] != ' ')&&(board[place+1] != '$'))) {
                            wrong();
                            wrongfunc = true;
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //coin counter
                    if (board[place+1] == '$'){
                        coin--;
                    }
                    //move
                    board[place] = ' ';
                    place++;
                    board[place] = '!';
                    break;

                //dig up
                case 'u':
                    //wrong function
                    try {
                        if (place-col < 0 || board[place-col] != '#' || board[place-2*col] == '@') {
                            wrong();
                            wrongfunc = true;
                            continue;
                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                    //move
                    board[place-col] = ' ';
                    break;

                //dig down
                case 'd':
                    //wrong function
                    try {
                        if (place+col > row*col-1 || board[place+col] != '#') {
                            wrong();
                            wrongfunc = true;
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //move
                    board[place+col] = ' ';
                    break;

                //dig left
                case 'l':
                    //wrong function
                    try {
                        if ((place-1)%col == col-1 || board[place-1] != '#') {
                            wrong();
                            wrongfunc = true;
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //move
                        board[place-1] = ' ';
                        break;

                //dig right
                case 'r':
                    //wrong function
                    try {
                        if ((place+1)%col == 0 || board[place+1] != '#') {
                            wrong();
                            wrongfunc = true;
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //move
                    board[place+1] = ' ';
                    break;

                //wrong entrance function
                default:
                    wrong();
                    wrongfunc = true;
                    continue;
            }
            
            //rock checking
            rock(board, row, col);

            printBoard(board,row, col);
        } while (coin > 0);

        //win
        if (coin == 0) {
            System.out.println("\t\tCongratulation " + name + "!!\n\n");
        }

        //play again
        System.out.println("  Do You Want To Play Again?\n\t1.YES\t\t\t2.NO");
        byte playagain = scanner.nextByte();
        switch (playagain){
            case 1:
                game(name);
            case 2:
                return;
            default:
                System.out.println("Wrong Entrance!");
                break;
        }
    }
//menu
    static void menu(){
        Scanner scanner = new Scanner(System.in);
        header();
        System.out.println(" 1.Start\t\t\t\t\t2.Exit");
        System.out.print("\nPlease Enter The Number You Want: ");
        byte menu = scanner.nextByte();
        switch (menu){
            //start game
            case 1:
                //player name
                System.out.print("\nEnter Your Name Please: ");
                String name = scanner.next();
                //game process
                game(name);
                break;

            //exit game
            case 2:
                return;
        }
    }

    public static void main(String[] args) {
        menu();
    }
}