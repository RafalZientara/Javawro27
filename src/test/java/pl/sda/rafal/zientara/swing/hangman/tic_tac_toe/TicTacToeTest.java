package pl.sda.rafal.zientara;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {
// aby dodać plik testowy wchodzimy na nazwę metody - alt+enter - wybieramy createTest a następnie fix, i tworzy się taka sama clasa z nazwą test
TicTacToe game;

    @BeforeEach // wywołanie przed kazdym testem
    public void setup() {
        //given
        System.out.println("setup");
        game = new TicTacToe();
    }
    @Test
    public void boardIsEmptyAtStart(){
        //given

        //when
        FieldStatus status = game.getFieldStatus(0,0);

        //then
        assertEquals(FieldStatus.EMPTY, status);
    }
    @Test
    public void firstActionOnBoardIsX() {
        //given

        //when
        game.action(0, 0);
        FieldStatus status = game.getFieldStatus(0, 0);

        //then
        assertEquals(FieldStatus.X, status);
    }

        @Test
    public void secondActionOnBoardIsO(){
        //given

        //when
        game.action(0,0);
        game.action(0,1);
        FieldStatus status = game.getFieldStatus(0,1);

        game.printBoard();
        //then
        assertEquals(FieldStatus.O, status);

    }

    @Test
    public void actionShouldThrowIfFieldIsTaken() {
        //given
        game.action(0, 0);

        //when
        assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                game.action(0, 0);
            }
        });

    }

    @Test
    public void playerXCanWinHorizontally(){
        //wstawienie na zmianę O i X
        game.action(0,0);
        game.action(0,1);
        game.action(1,0);
        game.action(1,1);
        game.action(2,0);
        game.printBoard();

        //when
        GameResult result = game.checkResult();

        assertEquals(GameResult.PLAYER_X_WIN, result);
    }

    @Test
    public void playerXCanWinVertically(){
        //wstawienie na zmianę O i X
        game.action(1,0);
        game.action(0,1);
        game.action(1,1);
        game.action(2,1);
        game.action(1,2);
        game.printBoard();

        //when
        GameResult result = game.checkResult();

        assertEquals(GameResult.PLAYER_X_WIN, result);
    }

    @Test
    public void playerXCanWinDiagonallyFromLeft(){
        game.action(0,0);
        game.action(0,1);
        game.action(1,1);
        game.action(1,2);
        game.action(2,2);
        game.printBoard();

        GameResult result = game.checkResult();

        assertEquals(GameResult.PLAYER_X_WIN, result);

    }

    @Test
    public void playerXCanWinDiagonallyFromRight(){
        game.action(2,0);
        game.action(1,0);
        game.action(1,1);
        game.action(2,1);
        game.action(0,2);
        game.printBoard();

        GameResult result = game.checkResult();

        assertEquals(GameResult.PLAYER_X_WIN, result);

    }

    @Test
    public void draw(){
        game.action(1,1);//x1
        game.action(2,1);//o2
        game.action(2,0);//x3
        game.action(0,2);//o4
        game.action(1,2);//x5
        game.action(1,0);//o6
        game.action(2,2);//x7
        game.action(0,0);//o8
        game.action(0,1);//x9
        game.printBoard();

        GameResult result = game.checkResult();

        assertEquals(GameResult.DRAW, result);

    }


}