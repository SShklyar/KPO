import java.util.Scanner;

public class Main {
    static long time;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Reversi game!");
        int size = 8;
        Board board = new Board(size);
        Person person = new Person();
        Computer computer = new Computer();
        System.out.println("Computer plays (X/O): ");
        String userSelection = input.nextLine();
        if (userSelection.charAt(0) == 'X') {
            board.setUserColor(Color.WHITE);
            person.setPiece(new WhitePiece());
            computer.setPiece(new BlackPiece());
        } else if (userSelection.charAt(0) == 'O') {
            person.setPiece(new BlackPiece());
            computer.setPiece(new WhitePiece());
            board.setUserColor(Color.BLACK);
        } else {
            System.out.println("Your input is illegal.");
            System.exit(0);
        }
        time = System.currentTimeMillis();
        System.out.println(board);
        boolean computerTurn = !(board.getUserColor() == Color.BLACK);
        while (board.userCount + board.comCount == 0 || board.userCount + board.comCount < board.size * board.size &&
                board.comCount != 0 && board.userCount != 0) {
            if (computerTurn) {
                if (computer.placeable(board)) computer.place(board);
                else if (!person.placeable(board)) break;
            } else if (person.placeable(board)) person.place(board);
            computerTurn = !computerTurn;
        }
        board.finish();
    }
}
