package pl.sda.rafal.zientara.swing.ticTacToe;

public class TicTacToe {

    public static final int BOARD_SIZE = 3;
    private FieldStatus[][] board = new FieldStatus[BOARD_SIZE][BOARD_SIZE];
    private boolean isOTurn = false;


    public FieldStatus getFieldStatus(int col, int row) {
        return board[row][col];
    }

    public TicTacToe() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
            board[i][j] = FieldStatus.EMPTY;
            }
        }
    }

    public void action(int col, int row) {
        GameResult result = checkResult();
        if (result != GameResult.PENDING){
            throw new IllegalStateException("Go home");
        }

        FieldStatus status = getFieldStatus(col, row);
        if (status != FieldStatus.EMPTY){
            throw new IllegalStateException("Pole zajęte");
        }
        if (isOTurn) {
            board[row][col] = FieldStatus.O;
            isOTurn = false;
        }else {
            board[row][col] = FieldStatus.X;
            isOTurn = true;
        }
    }


    public void printBoard() {
        // X|O|X
        //+-+-+
        // X|O|X
        //+-+-+
        // X|O|X
        for (int i=0; i<BOARD_SIZE; i++){
            for (int j=0; j<BOARD_SIZE; j++){
                FieldStatus status= getFieldStatus(j,i);
                System.out.print(status.getText());
                if (j<BOARD_SIZE - 1){
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i< BOARD_SIZE - 1) {
                System.out.println("-+-+-");
            }
        }


    }

    public GameResult checkResult() {
        if (isHorizontalWin(FieldStatus.X)) {
                return GameResult.PLAYER_X_WIN;
            }

        if (isVerticalWin(FieldStatus.X)) {
                return GameResult.PLAYER_X_WIN;
            }
        if (isDiagonalLeftWin(FieldStatus.X)){
            return GameResult.PLAYER_X_WIN;
        }

        if (isDiagonalRightWin(FieldStatus.X)){
            return GameResult.PLAYER_X_WIN;
        }

        if (isBoardFull()){
            return GameResult.DRAW;
        }

        return GameResult.PENDING;
    }

    private boolean isBoardFull() {
        for (int i=0; i<BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++){
                FieldStatus fieldStatus = getFieldStatus(i,j);
                if (fieldStatus == FieldStatus.EMPTY){
                    return false;
                }
            }
        }
        return true;
        }


    private boolean isDiagonalRightWin(FieldStatus player) {
        FieldStatus pos1 = getFieldStatus(2, 0);
        FieldStatus pos2 = getFieldStatus(1, 1);
        FieldStatus pos3 = getFieldStatus(0, 2);

        return pos1 == player &&
                pos2 == player &&
                pos3 == player;
    }

    private boolean isDiagonalLeftWin(FieldStatus player) {
            FieldStatus pos1 = getFieldStatus(0, 0);
            FieldStatus pos2 = getFieldStatus(1, 1);
            FieldStatus pos3 = getFieldStatus(2, 2);

        return pos1 == player &&
                pos2 == player &&
                pos3 == player;
    }


    // shift + f6, zmiana tej samej zmiennej w wielu miejscach

    private boolean isHorizontalWin(FieldStatus player){
        for (int i=0; i<BOARD_SIZE; i++){ //i to row/wiersz
            FieldStatus col1 = getFieldStatus(0, i);
            FieldStatus col2 = getFieldStatus(1, i);
            FieldStatus col3 = getFieldStatus(2, i);

            if (col1 == player &&
                    col2 == player &&
                    col3 == player) {
                return true;
            }
        }
        return false;
    }

    private boolean isVerticalWin(FieldStatus player){
        for (int i=0; i<BOARD_SIZE; i++){ //i to row/wiersz
            FieldStatus col1 = getFieldStatus(i, 0);
            FieldStatus col2 = getFieldStatus(i, 1);
            FieldStatus col3 = getFieldStatus(i, 2);

            if (col1 == player &&
                    col2 == player &&
                    col3 == player) {
                return true;
            }
        }
        return false;
    }




}
